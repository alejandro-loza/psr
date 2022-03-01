package mx.gob.oadprs.sicosel.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static org.apache.tomcat.util.codec.binary.Base64.decodeBase64;
import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64;

public class SeguridadLogin {

    private static String key = "Sicosel2.Infotec";
    private static String c1 = "AES/OFB32/PKCS5Padding";
    private static String iv = "0123456789ABCDEF";
    private static final String algoritmo = "AES";


    public static String encriptarAES(String contrasenia) throws Exception {
        Cipher cipher = Cipher.getInstance(c1);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algoritmo);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        try {
            cipher.init(cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encriptada = cipher.doFinal(contrasenia.getBytes());

            String s = new String(encodeBase64(encriptada));
            System.out.println(contrasenia + " encriptada es " + encriptada+ " base64 " +s);

            return s;
        }catch (Exception e){
            return null;
        }
    }

    public static String desencriptarAES(String contrasenia) throws Exception {
        Cipher cipher = Cipher.getInstance(c1);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algoritmo);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        byte[] encriptada = decodeBase64(contrasenia);

        try{

            cipher.init(cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] desencriptada = cipher.doFinal(encriptada);

            System.out.println(contrasenia + "desencriptada es " + desencriptada);
            return new String(desencriptada);
        }catch (Exception e){
            System.out.println("Excepcion es " + e.toString());
            return null;
        }
    }

    public static String codificar64(String contrasenia) throws Exception {
        String encriptada = new String(encodeBase64(contrasenia.getBytes()));
        System.out.println(contrasenia + " encriptada es " + encriptada);

        return encriptada.toString();
    }

    public static String decodificar64(String contrasenia) throws Exception {
        byte[] desencriptada = decodeBase64(contrasenia);

        System.out.println(contrasenia + "desencriptada es " + desencriptada);
        return new String(desencriptada);
    }

}

