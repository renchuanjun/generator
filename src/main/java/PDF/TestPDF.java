package PDF;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: 任传君
 * @date: 2022.07.07
 */
public class TestPDF {


    public static void main(String[] args) throws Exception {
        //html 转PDF
        /*StringBuffer textHtml = new StringBuffer();
        File file = new File("D:/workspace/generator/src/main/resources/aaa.html");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tempString = null;
        // 一次读入一行，直到读入null为文件结束
        while ((tempString = reader.readLine()) != null) {
            textHtml.append(tempString);
        }
        reader.close();
        writeToOutputStreamAsPDF(textHtml.toString());*/

        //PDF 水银
        String path = "D:\\abc-sign2水银.pdf";
        String input = "D:\\abc-sign2.pdf";
        setWatermark( path,  input);

    }

    private static void writeToOutputStreamAsPDF(String htmlStr) throws Exception {
        String targetFile = "D:/pdfDemo2.pdf";
        File targeFile = new File(targetFile);
        if (targeFile.exists()) {
            targeFile.delete();
        }

        //定义pdf文件尺寸，采用A4横切 左、右、上、下间距
        Document document = new Document(PageSize.A4, 25, 25, 15, 40);
//        PdfWriter writer = PdfWriter.getInstance(document, baos);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(targetFile));

        writer.addViewerPreference(PdfName.PRINTSCALING, PdfName.NONE);
        document.open();

        // CSS
        CSSResolver cssResolver = new StyleAttrCSSResolver();
        CssAppliers cssAppliers = new CssAppliersImpl(new XMLWorkerFontProvider() {
            @Override
            public Font getFont(String fontname, String encoding, boolean embedded, float size, int style, BaseColor color) {
                try {
                    //用于中文显示的Provider
                    BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                    return new Font(bfChinese, size, style);
                } catch (Exception e) {
                    return super.getFont(fontname, encoding, size, style);
                }
            }
        });

        //html
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        htmlContext.setImageProvider(new AbstractImageProvider() {
            @Override
            public Image retrieve(String src) {
                //支持图片显示
                int pos = src.indexOf("base64,");
                try {
                    if (src.startsWith("data") && pos > 0) {
                        byte[] img = Base64.decode(src.substring(pos + 7));
                        return Image.getInstance(img);
                    } else if (src.startsWith("http")) {
                        return Image.getInstance(src);
                    }
                } catch (BadElementException ex) {
                    return null;
                } catch (IOException ex) {
                    return null;
                }
                return null;
            }

            @Override
            public String getImageRootPath() {
                return null;
            }
        });


        // Pipelines
        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

        // XML Worker
        XMLWorker worker = new XMLWorker(css, true);
        XMLParser p = new XMLParser(worker);
        p.parse(new ByteArrayInputStream(htmlStr.getBytes()));

        document.close();

    }

    /**
     * 实现功能描述：PDF 水银
     *
     * @param:
     * @author: 任传君
     * @date: 2022-08-24 16:20
     */
    private static void setWatermark(String path, String input) throws Exception {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(path)));
        PdfReader pdfReader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(pdfReader, bos);
        int total = pdfReader.getNumberOfPages() + 1;
        PdfContentByte content;
        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        PdfGState gs = new PdfGState();

        for (int i = 1; i < total; i++) {
            //在内容上方增加水银
            content = stamper.getOverContent(i);
            //在内容下方增加水银
            content = stamper.getUnderContent(i);

            //文字水银
            content.beginText();
            content.setColorFill(BaseColor.DARK_GRAY);//颜色
            content.setFontAndSize(base, 15);//字体大小
            content.setTextMatrix(70, 200);//设置文本矩阵
            //具体位置 内容 x  y  坐标 旋转度
            content.showTextAligned(Element.ALIGN_CENTER, "王琪01", 300, 350, 300);
            content.showTextAligned(Element.ALIGN_TOP, "王琪02", 100, 150, 5);
            content.showTextAligned(Element.ALIGN_BOTTOM, "王琪03", 400, 400, 75);
            content.endText();


            //图片水银
            Image instance = Image.getInstance("D:\\logo.png");
            instance.setAbsolutePosition(10, 300);//绝对位置
            instance.scaleToFit(185, 240);//图片缩放比
            instance.setRotationDegrees(0);//旋转角度
            content.addImage(instance);
        }
        stamper.close();
    }
}
