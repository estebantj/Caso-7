package caso7;

import java.util.ArrayList;

public class Logica {
	public static String tanteo;
	
	public static ArrayList<ArrayList<String>> lista = new ArrayList<>();
	
	public static void tantear(ArrayList<String> caracteres, ArrayList<String> digitos) {
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
		
		System.out.println("Prueba Despues de la division: " + lista.get(0).get(1));
		System.out.println("Tamaño de la ultima: " + lista.get(3).size());
	}
}
