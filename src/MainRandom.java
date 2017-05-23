import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class MainRandom {
	private static final String outputPref="random";
	private static final String timesPref ="time";
	private static final String extension =".txt";
	public static void main(String[] args) {
		int problemas,minComensales,maxComensales,maxAfi;
		Scanner teclado=new Scanner(System.in);
		System.out.println("¿Cuantos problemas quiere generar?");
		problemas=teclado.nextInt();
		System.out.println("¿Cual es el numero de comensales del menor problema a generar?");
		minComensales=teclado.nextInt();
		System.out.println("¿Cual es el numero de comensales del mayor problema a generar?");
		maxComensales=teclado.nextInt();
		System.out.println("¿Cual es la afinidad máxima que habra entre dos comensales?");
		maxAfi=teclado.nextInt();
		teclado.close();
		try {
			PrintStream timeStream=new PrintStream(timesPref+extension);
			PrintStream outputStream=new PrintStream(outputPref+extension);
			for(int prob=0;prob<problemas;prob++){
				Resolutor resolutor = generaProblemaAleatorio(minComensales,maxComensales,maxAfi);
				long tI=Calendar.getInstance().getTimeInMillis();
				Solucion sol=resolutor.resuelve(TiposPoda.Efectiva);
				long tF=Calendar.getInstance().getTimeInMillis();
				imprimeDatos(prob,resolutor.getNumComensales(),timeStream,TiposPoda.Efectiva);
				imprimeResultados(prob,sol,tF-tI,outputStream,timeStream,TiposPoda.Efectiva);
				tI=Calendar.getInstance().getTimeInMillis();
				Solucion sol2=resolutor.resuelve(TiposPoda.Eficiente);
				tF=Calendar.getInstance().getTimeInMillis();
				imprimeDatos(prob,resolutor.getNumComensales(),timeStream,TiposPoda.Eficiente);
				imprimeResultados(prob,sol2,tF-tI,outputStream,timeStream,TiposPoda.Eficiente);
				//sol.printInfoNodos(timeStream,tF-tI);
			}
			timeStream.close();
			outputStream.close();
			System.out.println("Soluciones generadas en "+outputPref+extension);
			System.out.println("Tiempos de la ejecución guardados en "+timesPref+extension);
		} catch (FileNotFoundException e) {
			System.err.println("Archivo no encontrado");
		}
	}
	private static Resolutor generaProblemaAleatorio(int minC,int maxC,int afi){
		Random r=new Random();
		int t=r.nextInt(maxC-minC)+minC;
		int afinidad[][]=new int[t][t];
		for (int i=0;i<t;i++){
			for (int j=0;j<i;j++){
				int aux=r.nextInt(afi);
				afinidad[i][j]=aux;
			}
		}
		return new Resolutor(t,afinidad);
	}
	private static void imprimeResultados(int p,Solucion s,long lasted,PrintStream out,PrintStream ts, TiposPoda tPoda){
		out.println(tPoda);
		out.println("Problema: "+p);
		out.println(s);
		out.println();
		s.printInfoNodos(ts, lasted,tPoda);
		ts.println();
	}
	private static void imprimeDatos(int p,int c,PrintStream out, TiposPoda tPoda){
		out.println(tPoda);
		out.print("Problema: "+p);
		out.println(", Comensales: "+c);
	}
}