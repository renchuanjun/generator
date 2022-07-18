package PDF;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.security.Key;

import java.security.KeyStore;

import java.security.cert.Certificate;

import java.util.Enumeration;


/**
 * @Description:  生成 pfx
 * @author: 任传君
 * @date: 2022.07.18
 */
public class JKS2PFX {

    public static final String PKCS12 = "PKCS12";

    public static final String JKS = "JKS";

    public static final String PFX_KEYSTORE_FILE = "D://test.pfx";

    public static final String KEYSTORE_PASSWORD = "123123";

    public static final String JKS_KEYSTORE_FILE = "D://test.jks";

    /**
     * 实现功能描述：
     * window系统运行cmd命令
     *
     *
     * 跳转到${javahome}/bin路径
     *
     *
     * 执行命令keytool -genkey -alias test（别名） -keypass 123123（私钥密码） -keyalg RSA（算法） -sigalg sha256withrsa（算法小类） -keysize 1024（密钥长度） -validity 365（有效期） -keystore d:/test.jks（生成路径） -storepass 123123（主密码）
     *
     *
     * 或直接执行（不带注释说明）：keytool -genkey -alias test -keypass 123123 -keyalg RSA -sigalg sha256withrsa -keysize 1024 -validity 365 -keystore d:/test.jks -storepass 123123
     * @param:
     * @author: 任传君
     * @date: 2022-07-18 10:03
     */
    public static void coverToPfx() throws Exception {
        KeyStore inputKeyStore = KeyStore.getInstance("JKS");
        FileInputStream fis = new FileInputStream(JKS_KEYSTORE_FILE);
        char[] nPassword = null;
        if ((KEYSTORE_PASSWORD == null) || KEYSTORE_PASSWORD.trim().equals("")) {
            nPassword = null;
        } else {
            nPassword = KEYSTORE_PASSWORD.toCharArray();
        }
        inputKeyStore.load(fis, nPassword);
        fis.close();
        KeyStore outputKeyStore = KeyStore.getInstance("PKCS12");
        outputKeyStore.load(null, KEYSTORE_PASSWORD.toCharArray());
        Enumeration enums = inputKeyStore.aliases();
        while (enums.hasMoreElements()) {
            String keyAlias = (String) enums.nextElement();
            System.out.println("alias=[" + keyAlias + "]");
            if (inputKeyStore.isKeyEntry(keyAlias)) {
                Key key = inputKeyStore.getKey(keyAlias, nPassword);
                Certificate[] certChain = inputKeyStore
                        .getCertificateChain(keyAlias);
                outputKeyStore.setKeyEntry(keyAlias, key, KEYSTORE_PASSWORD

                        .toCharArray(), certChain);
            }
        }
        FileOutputStream out = new FileOutputStream(PFX_KEYSTORE_FILE);
        outputKeyStore.store(out, nPassword);
        out.close();
    }

    public static void main(String[] args) throws Exception {
        coverToPfx();
    }
}
