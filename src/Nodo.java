
public class Nodo implements Comparable<Nodo>{
	private int[] asignacion;
	private int k;
	private boolean[] sentados;
	private int preferencia;
	private int pref_opt;
	/**
	 * 
	 * @return beneficio real
	 */
	public int getPreferencia(){
		return preferencia;
	}
	/**
	 * 
	 * @return beneficio optimista
	 */
	public int getPrefOpt(){
		return pref_opt;
	}
	/**
	 * Permite asignar al nodo un nuevo beneficio optimista
	 * @param opt beneficio a establecer
	 */
	public void setOptimista(int opt){
		pref_opt=opt;
	}
	/**
	 * Constructor de un nodo vacío
	 * @param n numero de comensales
	 */
	public Nodo(int n) {
		k=1;
		sentados=new boolean [n];
		asignacion=new int[n];
		asignacion[0]=0;
		preferencia=0;
		for (int i=1;i<n;i++)
			sentados[i]=false;
		sentados[0]=true;
		}
	/**
	 * Constructor de un nodo por copia de parámetros
	 * @param solucion 
	 * @param neK
	 * @param newAsignados
	 * @param pre
	 * @param op
	 */
	public Nodo(int[] solucion, int neK, boolean[] newAsignados, int pre, int op) {
		asignacion=solucion;
		k=neK;
		sentados=newAsignados;
		preferencia=pre;
		pref_opt=preferencia+op;
	}
	/**
	 * Permite obtener un nodo a partir de otro al que expande
	 * @param comensal a  sentar en la siguiente posicion
	 * @param pref preferencua entre el nuevo comensal y el ultimo sentado
	 * @param opt estimacion optimista
	 * @return nodo generado
	 */
	public Nodo expande(int comensal,int pref,int opt){
		boolean[] newAsignados=sentados.clone();
		newAsignados[comensal]=true;
		int[] soluc=asignacion.clone();
		soluc[k]=comensal;
		return new Nodo(soluc,k+1,newAsignados,preferencia+pref,opt);
	}
	@Override
	public int compareTo(Nodo o) {
		return Integer.compare(o.getPrefOpt(),pref_opt);
	}
	/**
	 * 
	 * @return numero de comensales sentados
	 */
	public int getK(){
		return k;
	}
	/**
	 * 
	 * @return vector con las asignaciones
	 */
	public int[] getAsignacion(){
		return asignacion;
	}
	/**
	 * 
	 * @return vector con los comensales sentados
	 */
	public boolean[] getSentados(){
		return sentados;
	}
	public boolean asignado(int i) {
		return sentados[i];
	}
	/**
	 * Marca un comensal como no asignado
	 * @param i comensal
	 */
	public void desmarca(int i) {
		sentados[i]=false;
	}
	/**
	 * Suma las afinidades del primer y último comensal
	 * @param priYult
	 */
	public void sumaPref(int priYult) {
		preferencia+=priYult;
		
	}
	@Override
	public String toString(){
		String asignados="asignados: [";
		String solution="solucion: [";
		for(int i=0;i<asignados.length();i++){
			asignados+=" ,"+sentados[i];
			solution+=" ,"+asignacion[i];
		}
		asignados+="]";solution+="]";
		return "k: "+k+System.getProperty("line.separator")
		+	"preferencia optimista:" +pref_opt+System.getProperty("line.separator")
			+	"preferencia:" +preferencia+System.getProperty("line.separator")
			+	"solucion: "+solution+System.getProperty("line.separator")
			+ 	"asignados: "+asignados+System.getProperty("line.separator");
	}
}