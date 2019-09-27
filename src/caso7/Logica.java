package caso7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Logica {
	public static ArrayList<ArrayList<Probabilidades>> listaDividida = new ArrayList<>();
	public static ArrayList<Probabilidades> listaNumeros = new ArrayList<>();

	public static void crearSubListas(ArrayList<String> caracteres, ArrayList<String> digitos) {
		for (int i = 0; i < caracteres.size(); i++) {
		    ArrayList<Probabilidades> sublist;
		    if (i % 7 == 0) {
		        sublist = new ArrayList<Probabilidades>();
		        listaDividida.add(sublist);
		    } else {
		        sublist = listaDividida.get(listaDividida.size() - 1);
		    }
		    sublist.add(new Probabilidades(caracteres.get(i), ThreadLocalRandom.current().nextInt(caracteres.size(), caracteres.size()*20))); 
		}

		for (int i = 0; i < 10; i++) {
			listaNumeros.add(new Probabilidades(String.valueOf(i), ThreadLocalRandom.current().nextInt(digitos.size(), digitos.size()*10)));
		}
		
//		for(int i = 0; i < listaDividida.size(); i++)
//			System.out.println("Porcentaje Letra: " + listaDividida.get(i));
//    	for(int p = 0; p < listaNumeros.size(); p++)
//			System.out.println("Porcentaje Numero: " + listaNumeros.get(p));
		
		//tanteoDeLlaves();
		for(int i = 0; i < 10; i++) {
			tanteoDeLlaves();
		}
	}
	
	public static void tanteoDeLlaves() {
		int canTanteos = 0;
		boolean llaveEncontrada = false;
		String resultadoDesencriptado = "";
		
		while (!llaveEncontrada) {
			int posListaActual = ThreadLocalRandom.current().nextInt(listaDividida.size());
			
			ArrayList<Probabilidades> listaActualLetras = listaDividida.get(posListaActual);
			
			for (int caracter = 0; caracter < listaActualLetras.size(); caracter++) {
				for (int intento = 0; intento < 4; intento++) {
					
					Collections.sort(listaActualLetras);
					Collections.sort(listaNumeros); 
					
					String nCopia = Caso7.key.substring(0,7) + listaActualLetras.get(caracter).getCaracter() +
							Caso7.key.substring(8,11) + listaNumeros.get(intento).getCaracter() + Caso7.key.substring(12);
	                
					resultadoDesencriptado = Caso7.decrypt(Caso7.data, nCopia);
					
					int porCaracterActual = listaActualLetras.get(intento).getPrioridad();
                	int porNumeroActual = listaNumeros.get(posListaActual).getPrioridad();
                	
                	canTanteos++;
                	
	                if(!resultadoDesencriptado.equals("-1") && resultadoDesencriptado.equals(resultadoDesencriptado.replaceAll("[^\\p{ASCII}]", ""))) {
	                	
	                	System.out.println("Cantidad de intentos: " + canTanteos);
	                	
	                	if(porCaracterActual > 0) {
	                		listaActualLetras.get(intento).setPrioridad(ThreadLocalRandom.current().nextInt(0, porCaracterActual + 1));		                	
	                	}
	                	
	                	if(porNumeroActual > 0) {
	                		listaNumeros.get(posListaActual).setPrioridad(ThreadLocalRandom.current().nextInt(0, porNumeroActual + 1));
	                	}
	                	llaveEncontrada = true;
	                	
	                	break;
	                }
	                else {
//	                	System.out.println("Porcentaje Letra: " + porCaracterActual);
//	                	System.out.println("Porcentaje Numero: " + porNumeroActual);
	                	
	                	listaActualLetras.get(intento).setPrioridad(ThreadLocalRandom.current().nextInt(porCaracterActual, listaActualLetras.get(listaActualLetras.size()-1).getPrioridad()));
	                	
	                	listaNumeros.get(posListaActual).setPrioridad(ThreadLocalRandom.current().nextInt(porNumeroActual, listaNumeros.get(listaNumeros.size()-1).getPrioridad()));
	                	 
//	                	System.out.println("Porcentaje Letra: " + porCaracterActual);
//	                	System.out.println("Porcentaje Numero: " + porNumeroActual);
//	                	System.out.println("--------------------------------------");
	                }
				}
				if (llaveEncontrada) break;
			}
		}
	}
}
