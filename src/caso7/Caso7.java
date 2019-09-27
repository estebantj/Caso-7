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
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Caso7 {
    public static String data;
    public static String key;
    public static ArrayList<Caracter> caracteres;
    public static ArrayList<Caracter> digitos;
    public static int cantLetras;
    public static int cantDigitos;
    public static SecretKeySpec secretKey;
    
    private static void crearCaracteres() {
		char charV = 'a';
		for (int i=1;i<=cantLetras;i++) {
			caracteres.add(new Caracter(String.valueOf(charV), ThreadLocalRandom.current().nextDouble()));
			charV ++;
		}
		for (int i=1; i<=9; i++) {
			digitos.add(new Caracter(Integer.toString(i), ThreadLocalRandom.current().nextDouble() ));
		}
	}
    
    private static void ordenar() {
    	Collections.sort(caracteres, Collections.reverseOrder());
    	Collections.sort(digitos, Collections.reverseOrder());
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
        	return new String("-1");
        }
        return new String(output);
    }
    
    public static void main(String[] args) {
    	secretKey = null;
        data  = "xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=";
        key = "29dh120_dk1_3";
        cantLetras = 26;
        cantDigitos = 10;
        caracteres = new ArrayList<>();
        digitos = new ArrayList<>();
        crearCaracteres();
        ordenar();
        Logica logic = new Logica();
        for(int intento=1;intento<=20;intento++) {
        	System.out.println("---------------- Intento #"+intento+"-------------------");
        	logic.crearSubListas(caracteres);	
        	logic.tanteo();
        }
    }
}
