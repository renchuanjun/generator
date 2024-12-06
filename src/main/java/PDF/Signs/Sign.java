package PDF.Signs;


import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import com.itextpdf.text.pdf.security.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Objects;


/**
 * @Description:
 * @author: 任传君
 * @date: 2022.07.18
 */
public class Sign {

    /**
     * @param src             需要签章的pdf文件路径
     * @param dest            签完章的pdf文件路径
     * @param chain           证书链
     * @param img             印章图片
     * @param pk              签名私钥
     * @param digestAlgorithm 摘要算法名称，例如SHA-1
     * @param provider        密钥算法提供者，可以为null
     * @param subfilter       数字签名格式，itext有2种
     * @param reason          签名的原因，显示在pdf签名属性中
     * @param location        签名的地点，显示在pdf签名属性中
     * @throws GeneralSecurityException
     * @throws IOException
     * @throws DocumentException
     */
    public static void sign(String src, String dest, String img, Certificate[] chain, PrivateKey pk, String digestAlgorithm,
                            MakeSignature.CryptoStandard subfilter, String reason, String location) throws GeneralSecurityException, IOException, DocumentException {
        PdfReader pdfReader = new PdfReader(src);


        Image image = Image.getInstance(img);

        FileOutputStream fileOutputStream = new FileOutputStream(dest);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        /**
         * 1 参数依次为：文件名、文件输入流、文件版本号、临时文件、是否可以追加签名
         *  1.1 false的话，pdf文件只允许被签名一次，多次签名，最后一次有效
         *  1.2 true的话，pdf可以被追加签名，验签工具可以识别出每次签名之后文档是否被修改
         */
        PdfStamper stamper = PdfStamper.createSignature(pdfReader, fileOutputStream, '\0', null, false);

//        PdfStamper stamper1 = PdfStamper.createSignature(pdfReader, baos, '\0', null, false);

        // 获取数字签章属性对象，设定数字签章的属性
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setReason(reason);
        appearance.setLocation(location);

        //NOT_CERTIFIED 不会导致pdf上其他签章无效
        appearance.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);


        /**
         * 1 三个参数依次为：设置签名的位置、页码、签名域名称，多次追加签名的时候，签名域名称不能一样
         *  1.1 签名的位置四个参数：印章左下角的X、Y轴坐标，印章右上角的X、Y轴坐标,
         * 		这个位置是相对于PDF页面的位置坐标，即该坐标距PDF当前页左下角的坐标
         */
        String keyWords = "总体判定结果";

        float llx = 0f;
        float lly = 0f;
        float signImageWidth = image.getWidth();
        float signImageHeight = image.getHeight();
        float signImageHeightSocale = 85 / signImageWidth * signImageHeight;

        int pageSize = pdfReader.getNumberOfPages();
        KeyWordInfo keyWordInfo = getKeyWordLocation(pageSize, keyWords, pdfReader);


        //Rectangle rectangle = pdfReader.getPageSize(pageSize);
        //float width = rectangle.getWidth();
        //llx = keyWordInfo.getY() + (float) keyWordInfo.getHeight();
        //lly = width - keyWordInfo.getX() - signImageHeightSocale / 2;

        llx = keyWordInfo.getX() + (float) keyWordInfo.getWidth() * keyWords.length();
        lly = keyWordInfo.getY() - signImageHeightSocale / 2;


        float urx = llx + 85;
        float ury = lly + signImageHeightSocale;
        //appearance.setVisibleSignature(new Rectangle(100, 100, 200, 200), 1, "sign");

        appearance.setVisibleSignature(new Rectangle(llx, lly, urx, ury), pageSize, "sign");


        /**
         * 用于盖章的印章图片，引包的时候要引入itext包的image
         */
        appearance.setSignatureGraphic(image);
        /**
         * 设置认证等级，共4种，分别为：
         *  NOT_CERTIFIED、CERTIFIED_NO_CHANGES_ALLOWED、
         *  CERTIFIED_FORM_FILLING 和 CERTIFIED_FORM_FILLING_AND_ANNOTATIONS
         *
         * 需要用哪一种根据业务流程自行选择
         */
        appearance.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
        /**
         * 印章的渲染方式，同样有4种：
         *  DESCRIPTION、NAME_AND_DESCRIPTION,
         *  GRAPHIC_AND_DESCRIPTION,GRAPHIC;
         * 这里选择只显示印章
         */
        appearance.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);

        /**
         * 算法主要为：RSA、DSA、ECDSA
         * 摘要算法,这里的itext提供了2个用于签名的接口，可以自己实现
         */
        ExternalDigest digest = new BouncyCastleDigest();
        /**
         * 签名算法，参数依次为：证书秘钥、摘要算法名称，例如MD5 | SHA-1 | SHA-2.... 以及 提供者
         */
        ExternalSignature signature = new PrivateKeySignature(pk, digestAlgorithm, null);
        /**
         * 最重要的来了,调用itext签名方法完成pdf签章
         */
        MakeSignature.signDetached(appearance, digest, signature, chain, null, null, null, 0, subfilter);
    }


    /**
     * 查找关键字定位
     *
     * @param numberOfPages
     * @param keyWords      关键字
     * @param reader
     * @return
     * @throws IOException
     */
    private static KeyWordInfo getKeyWordLocation(Integer numberOfPages, final String keyWords, PdfReader reader) throws IOException {
        PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(
                reader);
        final KeyWordInfo keyWordInfo = new KeyWordInfo();
        pdfReaderContentParser.processContent(numberOfPages, new RenderListener() {
            @Override
            public void renderText(TextRenderInfo textRenderInfo) {
                String text = textRenderInfo.getText(); // 整页内容

                if (null != text && text.contains(keyWords)) {
                    Rectangle2D.Float boundingRectange = textRenderInfo.getBaseline().getBoundingRectange();
                    float leftY = (float) boundingRectange.getMinY() - 1;
                    float rightY = (float) boundingRectange.getMaxY() + 1;
                    System.out.println(boundingRectange.x + "--" + boundingRectange.y + "---");

                    keyWordInfo.setHeight(rightY - leftY);
                    keyWordInfo.setWidth((rightY - leftY) * keyWords.length());
                    keyWordInfo.setX(boundingRectange.x);
                    keyWordInfo.setY(boundingRectange.y);
                }
            }

            @Override
            public void renderImage(ImageRenderInfo arg0) {
            }

            @Override
            public void endTextBlock() {
            }

            @Override
            public void beginTextBlock() {
            }
        });
        return keyWordInfo;
    }



    public static final String KEYSTORE = "D:\\MyFirstApp.keystore";
    // 之前生成的keystory密码
    public static final char[] PASSWORD = "123456".toCharArray();

    public static final String IMG = "D:\\公章1.png";

    // 需要签名的PDF路径
    public static final String SRC = "D:\\bae937c37cad4fdfb64b22fded938f80.pdf";

    // 完成签名的PDF路径
    public static final String OUTPUT_SRC = "D:\\abc-sign2.pdf";



    public static void main(String[] args) throws IOException, DocumentException, GeneralSecurityException {
        //读取keystore ，获得私钥和证书链
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(KEYSTORE), PASSWORD);
        String alias = (String) keyStore.aliases().nextElement();
        PrivateKey PrivateKey = (PrivateKey) keyStore.getKey(alias, PASSWORD);
        Certificate[] chain = keyStore.getCertificateChain(alias);


        sign(SRC, String.format(OUTPUT_SRC, 3), IMG, chain, PrivateKey, DigestAlgorithms.SHA1, MakeSignature.CryptoStandard.CMS, "Test", "平台运营方电子签章盖章处");
        System.out.println("签章完成");
    }


}
