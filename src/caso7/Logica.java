package caso7;

import java.util.ArrayList;
import java.util.Random;

public class Logica {
	public static ArrayList<ArrayList<String>> listaDividida = new ArrayList<>();
	
	public static void crearSubListas(ArrayList<String> caracteres) {
		for (int i = 0; i < caracteres.size(); i++) {
		    ArrayList<String> sublist;
		    if (i % 7 == 0) {
		        sublist = new ArrayList<String>();
		        listaDividida.add(sublist);
		    } else {
		        sublist = listaDividida.get(listaDividida.size() - 1);
		    }
		    sublist.add(caracteres.get(i));
		}
		tantear();
	}
	
	public static void tantear() {
		boolean llaveEncontrada = false;
		String resultado = "";
		Random r = new Random();
		int canTanteos = 0;
		while (!llaveEncontrada) {
			ArrayList<String> actual = listaDividida.get(r.nextInt(listaDividida.size()));
			for (int i = 0; i < actual.size(); i++) {
				String charV = actual.get(r.nextInt(actual.size()));
				String numV = Caso7.digitos.get(r.nextInt(Caso7.digitos.size()));

				String nCopia = Caso7.key.substring(0,7) + charV + Caso7.key.substring(8,11) + numV + Caso7.key.substring(12);
                
				resultado = Caso7.decrypt(Caso7.data, nCopia);
				System.out.println("Intento numero " + canTanteos + ": " + resultado);
                System.out.println("Letra usada: " + charV + ", numero usado: " + numV);
                
				canTanteos++;
				if(!resultado.equals("-1")) {
					llaveEncontrada = true;
					break;
				}
			}
		}
		System.out.println("Cantidad de tanteos: " + canTanteos);
	}
	
	public static void crearLlavesAntes(String key, String data) {
    	char charV = 'a';
        int intentos = 0;
        for(int i = 0; i < 26; i++){
            String nCopia = key.substring(0,7) + charV + key.substring(8);
            for(int j = 0; j < 10; j++){
                String nCopia2 = nCopia.substring(0,11) + j + nCopia.substring(12);
                try{
                    System.out.println("Intento numero " + i + "." + j + ": " + Caso7.decrypt(data, nCopia2));
                }
                catch(NullPointerException e){
                    //System.out.println("ERROR: " + nCopia2);
                }
                intentos++;
            }
            charV++;
        }
        System.out.println("Numero de intentos: " + intentos);
    }
}
