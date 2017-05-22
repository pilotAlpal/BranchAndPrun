
import java.util.PriorityQueue;
/**
 * 
 * @author pilotAlpal
 *
 */
public class Resolutor {
	private int comensales;
	private int afinidad[][];
	/**
	 * Constructor de la clase 
	 * @param nComens número de comensales
	 * @param af función de afinidad entre los comensales
	 */
	public Resolutor(int nComens,int [][] af){
		comensales=nComens;
		afinidad=af;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return afinidad entre dos comensales
	 */
	private int afinidad(int x, int y){
		if (x>y)
			return afinidad[x][y];
		return afinidad[y][x];
	}
	/**
	 * 
	 * @return una solucion al problema de maximizar las afinidades en una mesa circular
	 */
	public Solucion resuelve(){
		 PriorityQueue<Nodo> cola;
		 int opt[]=new int [comensales];
		 int pes[]=new int [comensales];
		 int solMejor[]=new int [comensales];
		 for(int i=0;i<comensales;i++)
			 solMejor[i]=i;
		 int expandidos=0,podados=0,noFactibles=0;
		 completaEstimaciones(opt,pes);
		 Nodo x,y;
		 y=new Nodo(comensales);
		 y.setOptimista(opt[0]);
		 cola=new PriorityQueue<Nodo>();
		 cola.add(y);
		 int prefMejor=pes[0];
		 while(!cola.isEmpty()&& cola.peek().getPrefOpt()>=prefMejor){
			 y=cola.poll();
			 for(int i=1;i<comensales;i++){
				 if(!y.asignado(i)){
					 x=y.expande(i, afinidad(i,y.getAsignacion()[y.getK()-1]), opt[y.getK()]);
					 expandidos++;
					 if(esSolucion(x)){ 
						 x.sumaPref(afinidad(x.getAsignacion()[0], x.getAsignacion()[comensales-1]));
						 if(x.getPreferencia()>=prefMejor){
							 prefMejor=x.getPreferencia();
							 solMejor=x.getAsignacion();
						 }
					 }
					 else{ 
						 if (x.getPrefOpt()>prefMejor){
							 cola.add(x);
							 prefMejor=Integer.max(prefMejor, x.getPreferencia()+pes[x.getK()-1]);
						 }
						 else podados++;
					 }
				 }
				 else noFactibles++;
			 }
		 }
		 return new Solucion(solMejor,prefMejor,expandidos,podados,noFactibles);
	}


/**
 * Rellena dos vectores con la afinidad máxima y mínima posibles en función del
 * número de comensales que quedan por sentar
 * @param opt
 * @param pes
 */
	private void completaEstimaciones(int[] opt, int[] pes) {
		int[] mejores=new int[comensales];
		int[] peores=new int[comensales];
		for (int i=0;i<comensales;i++){
			int mej=0;int pe=0;
			if(i==0){
				mej=1;pe=1;
			}
			for(int j=1;j<comensales;j++)if(j!=i){
				if(afinidad(i,j)>afinidad(i,mej))
					mej=j;
				if(afinidad(i,j)<afinidad(i,pe))
					pe=j;
			}
			mejores[i]=afinidad(i,mej);
			peores[i]=afinidad(i,pe);
			opt[i]=0;
			pes[i]=0;
		}
		int mePri=mejores[0];
		int pePri=peores[0];
		heapSort(mejores);
		heapSort(peores);
		opt[comensales-1]+=mejores[0]+mePri;
		pes[comensales-1]+=peores[comensales-1]+pePri;
		for(int i=comensales-2;i>=0;i--){
			opt[i]+=mejores[comensales-1-i]+opt[i+1];
			pes[i]+=peores[i]+pes[i+1];
		}
	}
	/**
	 * Ordena un vector que recibe en forma de motículo de mínimos
	 * Utiliza el método del motículo
	 * @param vect
	 * @return
	 */
	private void heapSort(int[] vect) {
		int n=vect.length;
		for(int i=n/2;i>0;i--)
			hundir(vect,i,n);
		for(int i=n;i>1;i--){
			permutar(vect,1,i);
			hundir(vect,1,i-1);
		}
	}

	/**
	 * Intercambia en vect los valores de las posiciones i-1 y j-1 
	 * @param vect
	 * @param i
	 * @param j
	 */
	private void permutar(int[] vect, int i, int j) {
		int aux=vect[i-1];
		vect[i-1]=vect[j-1];
		vect[j-1]=aux;
	}
	private void hundir(int[] vect, int j, int k) {
		int m;
		boolean fin=false;
		while(2*j<=k&&!fin){
			if((2*j+1)<=k&&vect[2*j]<vect[2*j-1])
				m=2*j+1;
			else 
				m=2*j;
			if(vect[j-1]>vect[m-1]){
				permutar(vect,j,m);
				j=m;
			}
			else fin=true;
		}
	}
	private boolean esSolucion(Nodo x) {	
		return x.getK()==comensales;
	}
	public int getNumComensales(){
		return comensales;
	}
}
