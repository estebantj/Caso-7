package caso7;

import java.util.ArrayList;
import java.util.Random;

public class Logica {
	public static ArrayList<ArrayList<Probabilidades>> listaDividida = new ArrayList<>();
	public static ArrayList<Probabilidades> listaNumeros = new ArrayList<>();
	public static Double probaCaracteres = 0.0384;
	public static Double probaNumeros = 0.1;
	//public static String numV = "";
	
	/*
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
		//tantear();
		while (true) {
			tanteo2();			
		}
	}*/
	
	public static void crearSubListas2(ArrayList<String> caracteres) {
		for (int i = 0; i < caracteres.size(); i++) {
		    ArrayList<Probabilidades> sublist;
		    if (i % 7 == 0) {
		        sublist = new ArrayList<Probabilidades>();
		        listaDividida.add(sublist);
		    } else {
		        sublist = listaDividida.get(listaDividida.size() - 1);
		    }
		    sublist.add(new Probabilidades(caracteres.get(i), probaCaracteres));
		}
		
		for (int i = 0; i < 10; i++) {
			listaNumeros.add(new Probabilidades(String.valueOf(i), probaNumeros));
		}
		
		//tantear();
		while(true) {
			tanteo3();
		}
	}
	
	public static void tanteo3() {
		boolean llaveEncontrada = false;
		String resultado = "";
		Random r = new Random();
		int canTanteos = 0;
		while (!llaveEncontrada) {
			int pos = r.nextInt(listaDividida.size());
			ArrayList<Probabilidades> actual = listaDividida.get(pos);
			for (int caracter = 0; caracter < actual.size(); caracter++) {
				for (int intento = 0; intento < 4; intento++) {
					/*
					 if(numV == "") {
						numV = Caso7.digitos.get(r.nextInt(Caso7.digitos.size()));
					}
					else {
						numV = listaNumeros.get(0).getCaracter();
						for(int numeros = 0; numeros < 10; numeros++) {
							
						}
					} 
					*/
					
					
					String numV = Caso7.digitos.get(r.nextInt(Caso7.digitos.size()));

					String nCopia = Caso7.key.substring(0,7) + actual.get(caracter).getCaracter() + Caso7.key.substring(8,11) + numV + Caso7.key.substring(12);
	                
					resultado = Caso7.decrypt(Caso7.data, nCopia);
					
					double tempCaracteres = listaDividida.get(pos).get(intento).getProbabilidad();
                	double tempNumeros = listaNumeros.get(pos).getProbabilidad();
                	
	                if(!resultado.equals("-1") && resultado.equals(resultado.replaceAll("[^\\p{ASCII}]", ""))) {
	                	System.out.println("Intento numero " + canTanteos + ": " + resultado);
	                	listaDividida.get(pos).get(intento).setProbabilidad(1+tempCaracteres);
	                	listaNumeros.get(pos).setProbabilidad(1+tempNumeros);
	                	llaveEncontrada = true;
	                	break;
	                }
	                else {
	                	listaDividida.get(pos).get(intento).setProbabilidad(Math.abs(1-tempCaracteres));
	                	listaNumeros.get(pos).setProbabilidad(Math.abs(1-tempNumeros));
	                }
					canTanteos++;
				}
				if (llaveEncontrada) break;
			}
		}
	}
	
	/*
	public static void tanteo2() {
		boolean llaveEncontrada = false;
		String resultado = "";
		Random r = new Random();
		int canTanteos = 0;
		while (!llaveEncontrada) {
			int pos = r.nextInt(listaDividida.size());
			ArrayList<String> actual = listaDividida.get(pos);
			for (String caracter:actual) {
				for (int intento = 0; intento < 4; intento++) {
					String numV = Caso7.digitos.get(r.nextInt(Caso7.digitos.size()));

					String nCopia = Caso7.key.substring(0,7) + caracter + Caso7.key.substring(8,11) + numV + Caso7.key.substring(12);
	                
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
	}
	
	
	public static void tantear() {
		boolean llaveEncontrada = false;
		String resultado = "";
		Random r = new Random();
		int canTanteos = 0;
		while (!llaveEncontrada && listaDividida.size() != 0) {
			int pos = r.nextInt(listaDividida.size());
			ArrayList<String> actual = listaDividida.get(pos);
			for (int i = 0; i < actual.size(); i++) {
				String charV = actual.get(r.nextInt(actual.size()));
				String numV = Caso7.digitos.get(r.nextInt(Caso7.digitos.size()));

				String nCopia = Caso7.key.substring(0,7) + charV + Caso7.key.substring(8,11) + numV + Caso7.key.substring(12);
                
				resultado = Caso7.decrypt(Caso7.data, nCopia);

                if(!resultado.equals("-1")) {
                	System.out.println("Intento numero " + canTanteos + ": " + resultado);
                	System.out.println("Letra usada: " + charV + ", numero usado: " + numV);
                	llaveEncontrada = true;
                }
                
				canTanteos++;
				if(!resultado.equals("-1")) {
					llaveEncontrada = true;
					break;
				}
			}
		}
		System.out.println("Cantidad de tanteos: " + canTanteos);
	}
	*/
	
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
