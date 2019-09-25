package caso7;

import java.util.ArrayList;
import java.util.Random;

public class Logica {
	public static String tanteo;
	
	public static ArrayList<ArrayList<String>> lista = new ArrayList<>();
	
	public static void crearSubListas(ArrayList<String> caracteres, ArrayList<String> digitos) {
		System.out.println("Prueba Antes de la division: " + caracteres.get(1));
		for (int i = 0; i < caracteres.size(); i++) {
		    ArrayList<String> sublist;
		    if (i % 7 == 0) {
		        sublist = new ArrayList<String>();
		        lista.add(sublist);
		    } else {
		        sublist = lista.get(lista.size() - 1);
		    }
		    sublist.add(caracteres.get(i));
		}
		
		System.out.println("Prueba Despues de la division: " + lista);
	}
	
	public void tantear() {
		boolean llaveEncontrada = false;
		Random r = new Random();
		while (!llaveEncontrada) {
			ArrayList<String> actual = lista.get(r.nextInt(lista.size()));
			for (int i=0; i<4; i++) {
				
			}
		}
	}
}
