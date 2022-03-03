package mx.gob.oadprs.sicosel.utils;
import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Configuration
public class SeguridadLogin {

    @Value("${app.rsa.privateKey}")
    private String privateKey;

    private static String PRIVATE_KEY;

    @Value("${app.rsa.privateKey}")
    public void setNameStatic(String privateKey){
        SeguridadLogin.PRIVATE_KEY = privateKey;
    }

    public static String desencriptarRSA(String contrasenia) throws Exception {

        // Obtenemos la llave privada
        PrivateKey key = getPrivateKey(PRIVATE_KEY);
        // Establecemos el algoritmo de cifrado
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        // Decodificamos la cadena cifrada
        byte[] cipherContentBytes = Base64.getDecoder().decode(contrasenia.getBytes());
        // Deciframos el contenido de la cadena
        byte[] decryptedContent = cipher.doFinal(cipherContentBytes);
        String decoded = new String(decryptedContent);
        return decoded;
    }

    public static PrivateKey getPrivateKey(String base64PrivateKey) throws Exception{
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = null;

        // Eliminamos cabecera, pie y saltos de linea
        base64PrivateKey = base64PrivateKey
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PRIVATE KEY-----", "");

        keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        keyFactory = KeyFactory.getInstance("RSA");
        privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }


}
