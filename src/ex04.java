import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


import javax.swing.JOptionPane;

public class ex04 {
	public static void main (String[] args) {
		Hashtable<String, Double> controlProductos=new Hashtable<String,Double>();
		Hashtable<String,Integer> numeroProductos=new Hashtable<String,Integer>();
		//Iniciamos la base de datos de precios
		controlProductos=crearBaseDatos(controlProductos);
		//y la de Stock
		numeroProductos=crearBaseDatosStock(numeroProductos);
		boolean check=true;
		while(check) {
		//Creamos un panel de control para elegir la gestion
		String[] opciones= {"Gestionar caja", "Gestionar stock","Parar"};
		String gestion= (String) JOptionPane.showInputDialog(null,"Elige una opción","Personas",
				JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
		System.out.println(gestion);
		//Elegimos la gestión de stock
		if(gestion=="Gestionar stock") {
		boolean check2=true;
		int i=0;
		ArrayList<String> arrayProductos = new ArrayList<>();
		//Haremos un bucle infinito para no parar de introducir datos hasta que el usuario lo decida
		while(check2) {
		//Menu de opciones
			System.out.println("Escribe un numero para elegir que hacer");
			String eleccion=JOptionPane.showInputDialog("1.Introducir datos	2.Listar datos	3.Consultar datos	4.Añadir productos a stock"
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
			check2=false;
			break;
			}
			}
		}
		//Elegimos la gestión de caja
		if(gestion=="Gestionar caja") {
			double precioCarro=transaccion(controlProductos,numeroProductos);
			pagar(precioCarro);
		}
		if(gestion=="Parar") {
			check=false;
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
		String producto=JOptionPane.showInputDialog("Introduce un producto para añadir al stock");
		String cantidadString=JOptionPane.showInputDialog("Introduce cantidad");
		Integer cantidad =Integer.parseInt(cantidadString);
		numeroProductos.put(producto, cantidad);
		return numeroProductos;

	}
	//Método para gestionar toda la transaccion
	public static double transaccion(Hashtable<String,Double>controlProductos,Hashtable<String,Integer> numeroProductos) {
		int cantidadProducto=0;
		double precioProducto=0;
		double precioGrupoProducto=0;
		double precioCarro=0;
		boolean clienteComprando=true;
		int a=0;
		int numProductos=0;
		boolean check=false;
		String stop="stop";
		String eleccion;

		controlProductos.forEach((key,value)->{
			System.out.println("Nombre del producto "+key);
			System.out.println("Precio "+value);
		});
		a=0;
		//Hacemos un while que dure hasta que el usuario escriba stop para completar su compra.
		while(clienteComprando) {
			eleccion = JOptionPane.showInputDialog("Escribe un producto o stop");
			//En el momento que escribimos stop la transaccion finaliza y se informa del precio y nº de productos
				if(eleccion.equals(stop)) {
					System.out.println("Compra completada");
					clienteComprando=false;
				}else{
					check=false;
					//Pedimos el numero de unidades del producto elegido.
					while(!check) {
						String cantidadProductoString = JOptionPane.showInputDialog("¿Cuantas unidades de "+eleccion+" quieres añadir?");
						cantidadProducto = Integer.parseInt(cantidadProductoString);
						System.out.println(" cantidad"+cantidadProducto);
						//Validacion de datos para numeros negativos
						if(cantidadProducto<0) {
							System.out.println("Introduce una cantidad mayor a 0");
						//Comprobamos que haya suficiente Stock del producto para poder seguir con la compra
						}if(cantidadProducto>numeroProductos.get(eleccion)) {
							System.out.println("No hay suficientes productos en tienda para su compra");
						}if(cantidadProducto>0 && cantidadProducto<numeroProductos.get(eleccion)){
							check=true;
							numProductos=numProductos+cantidadProducto;
						}
					}
						
					//Aquí haremos todos los cálculos sobre precios y cantidades
					precioProducto=controlProductos.get(eleccion);
					precioGrupoProducto=precioProducto*cantidadProducto;
					System.out.println("Precio de "+eleccion+" "+precioProducto);
							System.out.println("Precio de todas las unidades: "+precioGrupoProducto);
						
				precioCarro=precioCarro+precioGrupoProducto;
				System.out.println("Total acumulado "+precioCarro);
				}
			a=0;
		}
		
		System.out.println("Precio total de la compra "+precioCarro+"	Numero de productos comprados: "+numProductos);
	return precioCarro;
	}
	//Método para pagar
	public static void pagar (double precioCarro) {
		boolean check=true;
		while(check) {
			//Le decimos al usuario el total de su compra y le preguntamos con cuanto dinero va a pagar
			String dineroClienteString = JOptionPane.showInputDialog("El total es de "+precioCarro+". Con cuanto dinero pagará?");
			double dineroCliente = Double.parseDouble(dineroClienteString);
			if(dineroCliente<precioCarro) {
				//Si no tiene suficiente dinero le dará un aviso
				System.out.println("No le alcanza. Por favor, introduzca otra cantidad.");
			}

			else {
				//En caso de que introduzca una cantidad válida se le devolverá el cambio.
			double cambio=dineroCliente-precioCarro;
			System.out.println("Se le devolverá "+cambio+ " de sus "+dineroCliente+" euros.");
			check=false;
			}
		}
	}
	
}