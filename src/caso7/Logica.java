package caso7;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Logica {
	
	public static ArrayList<ArrayList<Caracter>> listaDividida = new ArrayList<>();
	
	public static void crearSubListas(ArrayList<Caracter> pCaracteres) {
		for (int i = 0; i < pCaracteres.size(); i++) {
		    ArrayList<Caracter> sublist;
		    if (i % 7 == 0) {
		        sublist = new ArrayList<>();
		        listaDividida.add(sublist);
		    } else {
		        sublist = listaDividida.get(listaDividida.size() - 1);
		    }
		    sublist.add(pCaracteres.get(i));
		}
	}
	
	public static void tanteo() {
		boolean llaveEncontrada = false;
		String resultado = "";
		int canTanteos = 1;
		while (!llaveEncontrada && listaDividida.size() != 0) {
			int pos = ThreadLocalRandom.current().nextInt(0, listaDividida.size());
			ArrayList<Caracter> actual = listaDividida.get(pos);
			listaDividida.remove(pos);
			for (Caracter caracter:actual) {
				for (int intento=0; intento<4; intento++) {
					String numV = Caso7.digitos.get(ThreadLocalRandom.current().nextInt(0, Caso7.digitos.size())).getCaracter();
					//System.out.println(numV);
					String nCopia = Caso7.key.substring(0,7) + caracter.getCaracter() + Caso7.key.substring(8,11) + numV + Caso7.key.substring(12);
	                
					resultado = Caso7.decrypt(Caso7.data, nCopia);
					if(!resultado.equals("-1") && resultado.equals(resultado.replaceAll("[^\\p{ASCII}]", ""))) {
	                	System.out.println("Intento numero " + canTanteos + ": " + resultado);
	                	//System.out.println("Letra usada: " + caracter + ", numero usado: " + numV);
						llaveEncontrada = true;
	                	break;
	                }
	                
					canTanteos++;
				}
				if (llaveEncontrada) break;
			}
		}
		if (!llaveEncontrada) System.out.println("Llave no encontrada: "+canTanteos);
	}
}
