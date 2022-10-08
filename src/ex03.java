import java.util.ArrayList;
import java.util.Hashtable;


import javax.swing.JOptionPane;

public class ex03 {
	public static void main (String[] args) {
		Hashtable<String, Double> controlProductos=new Hashtable<String,Double>();
		Hashtable<String,Integer> numeroProductos=new Hashtable<String,Integer>();
		//Iniciamos la base de datos de precios
		controlProductos=crearBaseDatos(controlProductos);
		//y la de Stock
		numeroProductos=crearBaseDatosStock(numeroProductos);
		//Creamos un hashtable para almacenar el diccionario de datos
		boolean check=true;
		int i=0;
		ArrayList<String> arrayProductos = new ArrayList<>();
		//Haremos un bucle infinito para no parar de introducir datos hasta que el usuario lo decida
		while(check) {
		//Menu de opciones
			System.out.println("Escribe un numero para elegir que hacer");
			String eleccion=JOptionPane.showInputDialog("1.Introducir datos	2.Listar datos	3.Consultar datos		4.Añadir productos"
					+"	5.Listar stock	6.Stock de un producto		7.Salir");
			switch (eleccion) {
			case "1":
			//Llamamos al método para añadir a la base de datos el producto y su precio
			controlProductos=añadirBaseDatos(controlProductos);
			break;
			case "2":
			//Listamos todos los productos y precio
				controlProductos.forEach((key,value)->{
					System.out.println("Nombre del producto "+key);
					System.out.println("Precio "+value);
				});
			break;
			case "3":
			//Introducimos un producto para saber cuanto cuesta
				String consulta=JOptionPane.showInputDialog("De que producto quieres conocer la cantidad");
				System.out.println("Hay un numero de "+controlProductos.get(consulta)+ " del producto "+consulta);
			break;
			//Añadir productos al carro
			case "4":
				numeroProductos=numeroProductos(numeroProductos);
			break;
			case "5":
				numeroProductos.forEach((key,value)->{
					System.out.println("Nombre del producto "+key);
					System.out.println("Cantidad "+value);
				});			
			break;
			case "6":
				String consultaCantidad=JOptionPane.showInputDialog("De que producto quieres conocer la cantidad");
				System.out.println("Hay un numero de "+numeroProductos.get(consultaCantidad)+ " del producto "+consultaCantidad);
			break;
			case "7":
			//Salimos del menu
			check=false;
			break;
			}
		}
	}
	//Método para crear base de datos de precios
	public static Hashtable<String,Double> crearBaseDatos(Hashtable<String,Double> controlProductos){
		controlProductos.put("leche", 1.2);
		controlProductos.put("huevos",0.85);
		controlProductos.put("pizza",2.3);
		controlProductos.put("detergente",3.2);
		controlProductos.put("papel de cocina",1.5);
		controlProductos.put("cerveza",0.75);
		controlProductos.put("helado",2.4);
		controlProductos.put("libro",24.0);
		controlProductos.put("juguete",35.99);
		controlProductos.put("tablet",229.99);
		return controlProductos;

	}
	
	//Método para crear base de datos de Stock
	public static Hashtable<String,Integer> crearBaseDatosStock(Hashtable<String,Integer> controlProductos){
		controlProductos.put("leche", 250);
		controlProductos.put("huevos",200);
		controlProductos.put("pizza",100);
		controlProductos.put("detergente",280);
		controlProductos.put("papel de cocina",300);
		controlProductos.put("cerveza",500);
		controlProductos.put("helado",300);
		controlProductos.put("libro",100);
		controlProductos.put("juguete",80);
		controlProductos.put("tablet",50);
		return controlProductos;

	}
	//Método para añadir un producto y precio
	public static Hashtable<String,Double> añadirBaseDatos(Hashtable<String,Double> controlProductos){
		String producto=JOptionPane.showInputDialog("Introduce un producto para añadir al diccionario de datos");
		String precioString=JOptionPane.showInputDialog("Introduce precio");
		Double precio =Double.parseDouble(precioString);
		controlProductos.put(producto, precio);
		return controlProductos;

	}
	//Método para añadir un producto y cantidad
	public static Hashtable<String,Integer> numeroProductos(Hashtable<String,Integer> numeroProductos){
		String producto=JOptionPane.showInputDialog("Introduce un producto para añadir al diccionario de datos");
		String cantidadString=JOptionPane.showInputDialog("Introduce cantidad");
		Integer cantidad =Integer.parseInt(cantidadString);
		numeroProductos.put(producto, cantidad);
		return numeroProductos;

	}
}
