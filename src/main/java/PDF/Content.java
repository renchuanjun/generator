package PDF;

/**
 * @Description:
 * @author: 任传君
 * @date: 2022.07.18
 */
public class Content {
    //文件存放路径
    public static String BASE = "D:\\";

    // 创建的PDF
    public static String PDF_NEW = BASE + "/test1.pdf";
    /**
     * 签名的文件
     */
    public static String PDF_SIGNED = BASE + "/signature_1.pdf";
    // 证书,包含密钥
    public static final String PFX_PATH = BASE + "test.pfx";
    // 这是签名的密码
    public static final String PFX_PASS = "123123";
    // 签名的图
    public static final String STAMP_PATH = BASE + "test.gif";
    // 签名的图
    public static final String LOGO_PATH = BASE + "logo.png";
    // 解答的证书,公钥
    public static final String CER_PATH = BASE + "client.crt";
    // 密码
    public static byte[] OPEN_PASSWORD = "123123".getBytes();

}
