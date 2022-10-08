import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Hashtable;

public class ex01 {
	
	public static void main (String[] args) {
	int i=1;
	int numAlumnos=0;
	ArrayList<Double>notas = null;
	double notaMediaAlumno;
	Hashtable<String,Double> notasFinales=new Hashtable<String,Double>();
	boolean check=false;
	

	//Pedimos el número de alumnos de la clase comprobando que sea uno válido
		while(!check) {
		String numAlumnosString = JOptionPane.showInputDialog("¿Cuantos alumnos hay?");
		numAlumnos = Integer.parseInt(numAlumnosString);
			if(numAlumnos<0) {
				System.out.println("Introduce un numero de alumnos correcto");
			}else {
				check=true;
			}
		}
		
		while(i<numAlumnos+1) {
	//Llamamos al método para introducir las notas
			notas=introducirNotas(numAlumnos,i);
	//Llamamos al método para hacer la media del alumno
			notaMediaAlumno=notaMedia(notas);
	//Añadimos a un hashtable el numero del alumno y su nota media.
			notasFinales.put("Media del alumno "+i,notaMediaAlumno);
			i++;
		}
	//Llamamos al método para listar la hashtable completa
	diccionarioNotas(notasFinales);
	
	}
	
	//Introducimos todas las notas de cada alumno y las vamos a guardar en un arraylist.
	public static ArrayList<Double> introducirNotas (int numAlumnos,int i) {
		int a=1;
		int numAlumno=i;
		String numNotasString = JOptionPane.showInputDialog("¿Cuantas notas quieres introducir para el alumno "+numAlumno+"?");
		int numNotas = Integer.parseInt(numNotasString);
		ArrayList<Double> listaNotas=new ArrayList<>();
		while(a<numNotas+1) {
			String notaString = JOptionPane.showInputDialog("Nota numero "+a);
			double nota = Double.parseDouble(notaString);
			//Controlaremos que las notas estén en un rango válido de 1 a 10.
			if(nota<0 || nota>10) {
				System.out.println("Introduce una nota valida, del 1 al 10");
			}else {
			listaNotas.add(nota);
			a++;
			}
		}
		//for para mostrar por pantalla los datos que hemos introducido
		for(Object o:listaNotas) {
			System.out.println(o);
		}
		return listaNotas;
	}
	
	//Recorremos todo el arraylist para agarrar cada nota una a una y poder hacer la media
	public static double notaMedia(ArrayList<Double> notas) {
		int a=0;
		double sumaNotas=0;
		double nota;
		double media;
		int numNotas=notas.size();
		while(a<numNotas) {
			nota=notas.get(a);
			sumaNotas=sumaNotas+nota;
			a++;
		}
		media=sumaNotas/numNotas;
		return media;
	}
	
	//Listamos la tabla con todos los datos finales
	public static void diccionarioNotas (Hashtable<String,Double> notasFinales) {
			System.out.println(notasFinales);
	}
}
