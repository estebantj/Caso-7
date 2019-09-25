package caso7;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Caso7 {
	public static SecretKeySpec secretKey;
    public static String data;
    public static String key;
    public static ArrayList<String> caracteres;
    public static ArrayList<String> digitos;
    
    Caso7() {
        secretKey = null;
        data  = "xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=";
        key = "29dh120_dk1_3";
        digitos = new ArrayList<>(Arrays.asList("0123456789".split("")));
        caracteres = new ArrayList<>(Arrays.asList("abcdefghijklmnopqrstuvwxyz0123456789".split("")));
        Collections.shuffle(caracteres);
        Collections.shuffle(digitos);
    }
    
    private void setKey(String myKey) {
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

    public String decrypt(String input, String key) {
        byte[] output = null;
        try {
            java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            setKey(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            output = cipher.doFinal(decoder.decode(input));
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            //System.out.println(e.toString());
        	return new String("-1");
        }
        return new String(output);
    }
    
    public void algoritmo() {
    	System.out.println(digitos);
    	
    }
    
    public static void main(String[] args) {
    	Caso7 caso = new Caso7();
        caso.algoritmo();
        
        /*
        char charV = 'a';
        int intentos = 0;
        for(int i = 0; i < 26; i++){
            String nCopia = key.substring(0,7) + charV + key.substring(8);
            for(int j = 0; j < 10; j++){
                String nCopia2 = nCopia.substring(0,11) + j + nCopia.substring(12);
                try{
                    System.out.println("Intento numero " + i + "." + j + ": " + caso.decrypt(data, nCopia2));
                }
                catch(NullPointerException e){
                    //System.out.println("ERROR: " + nCopia2);
                }
                intentos++;
            }
            charV++;
        }
        System.out.println("Numero de intentos: " + intentos);
        */
    }
}
