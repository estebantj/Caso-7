package caso7;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Caso7 {
	
    public static ArrayList<String> caracteres;
    public static ArrayList<String> numeros;
    public static SecretKeySpec secretKey;
    public static String data;
    public static String key;
    
    Caso7() {
    	data  = "xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=";
    	caracteres = new ArrayList<>(Arrays.asList("abcdefghijklmnopqrstuvwxyz".split("")));
        numeros = new ArrayList<>(Arrays.asList("0123456789".split("")));
        key = "29dh120_dk1_3";
        secretKey = null;
    }
     
    private static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            byte[] local_key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            local_key = sha.digest(local_key);
            local_key = Arrays.copyOf(local_key, 16);
            secretKey = new SecretKeySpec(local_key, "AES");
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String decrypt(String input, String key) {
        byte[] output = null;
        try {
            java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            output = cipher.doFinal(decoder.decode(input));
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            //System.out.println(e.toString());
        	return new String("-1");
        }
        return new String(output);
    }
    
    public static void main(String[] args) {
    	Caso7 caso = new Caso7();
        Logica.crearSubListas(caracteres, numeros);
    }
}
