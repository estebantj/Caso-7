package caso7;

public class Probabilidades {
	public String caracter;
	public Double probabilidad;
	
	Probabilidades(String caracter, Double probabilidad){
		this.caracter = caracter;
		this.probabilidad = probabilidad;
	}
	
	public String getCaracter() {
		return caracter;
	}
	
	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}
	
	public Double getProbabilidad() {
		return probabilidad;
	}
	
	public void setProbabilidad(Double probabilidad) {
		this.probabilidad = probabilidad;
	}
}
