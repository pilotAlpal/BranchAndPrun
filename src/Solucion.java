import java.io.PrintStream;

public class Solucion {
	private int[] asignacion;
	private int preferencia;
	private int expandidos;
	private int podados;
	private int noFactibles;
	/**
	 * Constructor por copia
	 * @param solMejor asignacion de comensales 
	 * @param prefMejor afinidad obtenida
	 * @param noFactibles 
	 * @param podados 
	 * @param expandidos 
	 */
	public Solucion(int[] solMejor, int prefMejor, int exps, int ps, int noFs) {
		asignacion=solMejor;
		preferencia=prefMejor;
		expandidos=exps;
		podados=ps;
		noFactibles=noFs;
	}
	
	@Override
	public String toString(){
		String asientos="";
		for(int i=0;i<asignacion.length;i++){
			asientos+="Comensal"+asignacion[i]+" ->";
		}
		return"Preferencia maxima"
				+ " obtenida "+preferencia
				+System.getProperty("line.separator")
				+asientos;
	}
	public void printInfoNodos(PrintStream o,long timeLasted){
		o.print("Nodos expandidos: "+expandidos);
		o.print(",podados: "+podados);
		o.println(",no factibles :"+noFactibles);
		o.println("Tiempo empleado :"+timeLasted+" milisegundos");
		long tPn=1000*timeLasted/expandidos;
		o.println("Tiempo por nodo: "+tPn+" microsegundos por nodo expandido");
	}

}
