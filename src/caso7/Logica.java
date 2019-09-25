package caso7;

import java.util.ArrayList;
import java.util.Random;

public class Logica {
	public static String tanteo;
	
	public static ArrayList<ArrayList<String>> listaDividida = new ArrayList<>();
	
	public static void crearSubListas(ArrayList<String> caracteres) {
		System.out.println("Prueba Antes de la division: " + caracteres.get(1));
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
		Random r = new Random();
		int cont = 0;
		while (!llaveEncontrada) {
			ArrayList<String> actual = listaDividida.get(r.nextInt(listaDividida.size()));
			String charV = actual.get(r.nextInt(actual.size()));
			String numV = Caso7.digitos.get(r.nextInt(Caso7.digitos.size()));
			for (int i = 0; i < actual.size(); i++) {
				try{
					String nCopia = Caso7.key.substring(0,7) + charV + Caso7.key.substring(8,11) + numV + Caso7.key.substring(12);
                    System.out.println("Intento numero " + cont + ": " + Caso7.decrypt(Caso7.data, nCopia));
                }
                catch(NullPointerException e){
                    //System.out.println("ERROR: " + nCopia2);
                }
				cont++;
			}
		}
	}
}
