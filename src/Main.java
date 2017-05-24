import java.util.Calendar;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner teclado=new Scanner(System.in);
		System.out.println("¿Cuantos comensales son?");
		int comensales = teclado.nextInt();
		int afinidad[][]=new int[comensales][comensales];
		for (int i=0;i<comensales;i++){
			for (int j=0;j<i;j++){
				System.out.println("¿Cual es"
						+ "la afinidad entre el comensal nº "
						+ i+" y el nº "+j);
				int aux=teclado.nextInt();
				afinidad[i][j]=aux;
			}
		}
		teclado.close();
		Resolutor resolutor=new Resolutor(comensales,afinidad);
		long tI=Calendar.getInstance().getTimeInMillis();
		Solucion solucion = resolutor.resuelve(TiposPoda.Efectiva);
		long tO=Calendar.getInstance().getTimeInMillis();
		System.out.println(solucion);
		solucion.printInfoNodos(System.out,tO-tI,TiposPoda.Efectiva);
		long tEmp=tO-tI;
		System.out.println("Tiempo empleado :"+tEmp);
		tI=Calendar.getInstance().getTimeInMillis();
		Solucion solucion2 = resolutor.resuelve(TiposPoda.Eficiente);
		tO=Calendar.getInstance().getTimeInMillis();
		System.out.println(solucion2);
		solucion2.printInfoNodos(System.out,tO-tI,TiposPoda.Eficiente);
		tEmp=tO-tI;
		System.out.println("Tiempo empleado :"+tEmp);
	}

}
