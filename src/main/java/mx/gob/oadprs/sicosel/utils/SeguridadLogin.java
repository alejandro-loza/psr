package mx.gob.oadprs.sicosel.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static org.apache.tomcat.util.codec.binary.Base64.decodeBase64;
import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64;

public class SeguridadLogin {

    private static String key = "Sicosel2.Infotec"; //TODO PONER EN application.yaml
    private static String c1 = "AES/OFB32/PKCS5Padding";//TODO PONER EN application.yaml
    private static String iv = "0123456789ABCDEF";//TODO PONER EN application.yaml
    private static final String algoritmo = "AES";//TODO PONER EN application.yaml


    public static String encriptarAES(String contrasenia) throws Exception {
        Cipher cipher = Cipher.getInstance(c1);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algoritmo);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encriptada = cipher.doFinal(contrasenia.getBytes());
        return new String(encodeBase64(encriptada));

    }

    public static String desencriptarAES(String contrasenia) throws Exception {
        Cipher cipher = Cipher.getInstance(c1);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algoritmo);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        return new String(cipher.doFinal( decodeBase64(contrasenia)));
    }

    public static String codificar64(String contrasenia){
        return new String(encodeBase64(contrasenia.getBytes()));
    }

    public static String decodificar64(String contrasenia){
        return new String( decodeBase64(contrasenia));
    }

}

