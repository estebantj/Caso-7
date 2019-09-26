package caso7;

public class Caracter implements Comparable< Caracter > {
	String caracter;
	double proba;
	
	Caracter(String pCaracter, double pProba) {
		caracter=  pCaracter;
		proba = pProba;
	}
		
	public String getCaracter() {
		return caracter;
	}

	public double getProba() {
		return proba;
	}

	public void setProba(double proba) {
		this.proba = proba;
	}

	@Override
	public String toString() {
		return "Caracter [caracter=" + caracter + ", proba=" + proba + "]";
	}

	@Override
	public int compareTo(Caracter e) {
		if (this.getProba() < e.getProba()) return -1;
		if (this.getProba() > e.getProba()) return 1;
		return 0;
	}
}
