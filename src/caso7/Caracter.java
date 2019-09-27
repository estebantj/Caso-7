package caso7;

public class Caracter implements Comparable< Caracter > {
	String caracter;
	double probabilidad;
	
	Caracter(String pCaracter, double pProba) {
		caracter=  pCaracter;
		probabilidad = pProba;
	}
		
	public String getCaracter() {
		return caracter;
	}

	public double getProba() {
		return probabilidad;
	}

	public void setProba(double proba) {
		this.probabilidad = proba;
	}

	@Override
	public String toString() {
		return "Caracter [caracter=" + caracter + ", proba=" + probabilidad + "]";
	}

	@Override
	public int compareTo(Caracter e) {
		if (this.getProba() < e.getProba()) return -1;
		if (this.getProba() > e.getProba()) return 1;
		return 0;
	}
}
