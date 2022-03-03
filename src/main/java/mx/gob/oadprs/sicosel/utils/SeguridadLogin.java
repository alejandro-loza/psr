package mx.gob.oadprs.sicosel.utils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
        String s = new String(encriptada);
        //System.out.println("Encriptado AES "+ );
        //byte[] bytes = encodeBase64(encriptada);
        return s; //new String(bytes);

    }

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    public String encryptMessage(byte[] message) throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedMessage = cipher.doFinal(message);
        return new String(encryptedMessage);
    }

    public String decryptMessage(byte[] encryptedMessage) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] clearMessage = cipher.doFinal(encryptedMessage);
        return new String(clearMessage);
    }

    public static String desencriptarAES(String contrasenia) throws Exception {
        Cipher cipher = Cipher.getInstance(c1);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algoritmo);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] input = decodeBase64(contrasenia);
        return new String(cipher.doFinal(input));
    }

    public static String codificar64(String contrasenia){
        return new String(encodeBase64(contrasenia.getBytes()));
    }

    public static String decodificar64(String contrasenia){
        return new String( decodeBase64(contrasenia));
    }

}

