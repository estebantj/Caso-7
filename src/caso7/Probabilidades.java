package caso7;

public class Probabilidades implements Comparable< Probabilidades >{
	public String caracter;
	public Integer prioridad;
	
	Probabilidades(String caracter, Integer prioridad){
		this.caracter = caracter;
		this.prioridad = prioridad;
	}
	
	public String getCaracter() {
		return caracter;
	}
	
	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}
	
	public Integer getPrioridad() {
		return prioridad;
	}
	
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}
	
	@Override
    public int compareTo(Probabilidades o) {
        return this.getPrioridad().compareTo(o.getPrioridad());
    }
}
