import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;

public class MainFromFile {
	private final static String inputFile="entrada.txt";
	private static final String outFile="salida";
	public static void main(String[] args) {
		try {
			FileReader fReader=new FileReader(new File(inputFile));
			BufferedReader bReader=new BufferedReader(fReader);
			int prob=0;
			String linea=bReader.readLine();
			while(linea!=null){
				int comensales=Integer.parseInt(linea);			
				int afinidades[][]=new int [comensales][comensales];
				for(int i=0;i<comensales;i++){
					linea=bReader.readLine();
					String[]  vec=linea.split(",");
					afinidades[i] = vectsToInts(vec); 
				}
				PrintStream ps=new PrintStream(outFile+prob+".txt");
				Resolutor resolutor=new Resolutor(comensales, afinidades);
				long tI=Calendar.getInstance().getTimeInMillis();
				Solucion sol=resolutor.resuelve(TiposPoda.Eficiente);
				long tO=Calendar.getInstance().getTimeInMillis();
				ps.println(sol);
				sol.printInfoNodos(ps,tO-tI,TiposPoda.Eficiente);
				tI=Calendar.getInstance().getTimeInMillis();
				Solucion sol2=resolutor.resuelve(TiposPoda.Efectiva);
				tO=Calendar.getInstance().getTimeInMillis();
				ps.println(sol2);
				sol2.printInfoNodos(ps,tO-tI,TiposPoda.Efectiva);
				ps.close();
				linea=bReader.readLine();
				System.out.println("Salida "+prob+" generada en "+outFile+prob+".txt");
			}
			bReader.close();
			fReader.close();
		} catch (FileNotFoundException e) {
			System.err.println("Fichero no encontrado");
		} catch (IOException e) {
			System.err.println("Error abriendo el archivo");
		} catch (NumberFormatException e){
			System.err.println("Error parseando fichero");
		}

	}
	private static int[] vectsToInts(String[] vec) {
		int dev[]=new int [vec.length];
		for(int i=0;i<vec.length;i++)
			dev[i]=Integer.parseInt(vec[i]);
		return dev;
	}

}
