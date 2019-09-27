package caso7;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Logica {
	
	public static ArrayList<ArrayList<Caracter>> listaDividida = new ArrayList<>();
	public static ArrayList<String[]> combinacionesRealizadas = new ArrayList<>();
	
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
			// Primero se toma una de las sublistas de la listaDivididida y se elimina de esta para evitar repetir combinaciones
			int pos = ThreadLocalRandom.current().nextInt(0, listaDividida.size());
			ArrayList<Caracter> actual = listaDividida.get(pos);
			listaDividida.remove(pos);
			// Por cada caracter de la sublista se hacen cuatro intentos
			for (Caracter caracterActual:actual) {
				ArrayList<String> digitosUtilizados = new ArrayList<>();
				for (int intento=0; intento<4; intento++) {
					Caracter numV = Caso7.digitos.get(ThreadLocalRandom.current().nextInt(0, Caso7.digitos.size()));
					/*
					 	Para evitar que se repita un intento se sigue realizando un random hasta obtener un digito que no ha sido utilizado
					 con el caracter
					*/
					while (digitosUtilizados.contains(numV.getCaracter()) ) {
						numV = Caso7.digitos.get(ThreadLocalRandom.current().nextInt(0, Caso7.digitos.size()));
						if (!digitosUtilizados.contains(numV.getCaracter())) {
							break;
						}
					}
					digitosUtilizados.add(numV.getCaracter());
					String[] combinacion = {caracterActual.getCaracter(), numV.getCaracter()};
					combinacionesRealizadas.add(combinacion);
					
					String nCopia = Caso7.key.substring(0,7) + caracterActual.getCaracter() 
						+ Caso7.key.substring(8,11) + numV.getCaracter() + Caso7.key.substring(12);
	                
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
		if (!llaveEncontrada) {
			System.out.println("Llave no encontrada: "+canTanteos);
			/*
			for (String[] combinacion: combinacionesRealizadas) {
				System.out.println(combinacion[0]+" "+combinacion[1]);
			}
			*/
			System.out.println("La clave se encuentra en las siguientes combinaciones: ");
			char charV = 'a';
			for (int i=1;i<Caso7.cantLetras;i++) {
				for (int j=1;j<Caso7.cantDigitos;j++) {
					String[] combinacion = {String.valueOf(charV), Integer.toString(j)}; 
					if (!combinacionesRealizadas.contains(combinacion)) {
						System.out.print("{"+charV+", "+j+"}");
					}
				}
				charV++;
			}
		}
	}
}
