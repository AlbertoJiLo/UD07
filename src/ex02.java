import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.JOptionPane;

public class ex02 {
	public static void main (String[] args) {
		//Crearemos una base de datos con un hashtable de los productos y sus precios
		Hashtable<String,Double>listaProductos= new Hashtable<String,Double>();
		listaProductos=crearBaseDatos();
		//Listamos todos los productos y su precio para poder iniciar la compra
		Set<String> keys = listaProductos.keySet();
        for(String key: keys){
            System.out.println("El precio de "+key+" es: "+listaProductos.get(key));
        }
        System.out.println("Comencemos el pago");
        //Iniciamos la transacción
		double precioCarro=transaccion(listaProductos);
		//Confirmamos con cuanto dinero pagará el cliente para darle el cambio.
		pagar(precioCarro);
		
		
	}
	//Base de datos de productos y precios.
	public static Hashtable<String,Double> crearBaseDatos(){
		
		Hashtable<String,Double>listaProductos=new Hashtable<String,Double>();
		listaProductos.put("leche", 1.2);
		listaProductos.put("huevos",0.85);
		listaProductos.put("pizza",2.3);
		listaProductos.put("detergente",3.2);
		listaProductos.put("papel de cocina",1.5);
		listaProductos.put("cerveza",0.75);
		listaProductos.put("helado",2.4);
		listaProductos.put("libro",24.0);
		listaProductos.put("juguete",35.99);
		listaProductos.put("tablet",229.99);
		
	
		return listaProductos;
	}
	//Método para gestionar toda la transaccion
	public static double transaccion(Hashtable<String,Double>listaProductos) {
		int cantidadProducto=0;
		double precioProducto=0;
		double precioTotalProducto=0;
		double precioGrupoProducto=0;
		double precioCarro=0;
		boolean clienteComprando=true;
		int a=0;
		int numProductos=0;
		String eleccion=null;
		String stop="stop";
		String[] productosValidos = new String[10];
		Enumeration<String> keys = listaProductos.keys();
		boolean check=false;
		boolean check2=false;
		int cuantasKeys=listaProductos.size();
		//Guardamos en productos válidos todos los listados para en el futuro poder controlar si el producto es correcto
		while(a<cuantasKeys) {
			productosValidos[a]=keys.nextElement();
			System.out.println("Producto "+a+"  " +productosValidos[a]);
			a++;
		}
		a=0;
		//Hacemos un while que dure hasta que el usuario escriba stop para completar su compra.
		while(clienteComprando) {
			eleccion = JOptionPane.showInputDialog("Escribe un producto o stop");
			//Recorremos toda la hashtable para saber si el producto coincide con lo escrito
			while(a<cuantasKeys) {
				eleccion=eleccion.toLowerCase();
				if(eleccion.equals(productosValidos[a]) || eleccion.equals(stop)) {
				check2=true;
				}
			a++;
			}
			if(check2) {
			//En el momento que escribimos stop la transaccion finaliza y se informa del precio y nº de productos
				if(eleccion.equals(stop)) {
					System.out.println("Compra completada");
					clienteComprando=false;
				}else{
					boolean positivo=false;
						while(!positivo) {
						String cantidadProductoString = JOptionPane.showInputDialog("¿Cuantas unidades de "+eleccion+" quieres añadir?");
						cantidadProducto = Integer.parseInt(cantidadProductoString);
						numProductos=numProductos+cantidadProducto;
						if(cantidadProducto<0) {
							System.out.println("Introduce una cantidad mayor a 0");
						}
						else {
							positivo=true;
						}
					
					}
					double IVA;
					//Establecemos el IVA segun el producto
					if(eleccion.contentEquals("libro")||eleccion.contentEquals("juguete" )|| eleccion.contentEquals("tablet")) {
						IVA=0.04;
					}else {
						IVA=0.21;
					}
					//Aquí haremos todos los cálculos sobre precios, cantidades e IVA
					System.out.println("IVA "+IVA);
					precioProducto=listaProductos.get(eleccion);
					precioTotalProducto=(precioProducto+(precioProducto*IVA));
					precioGrupoProducto=precioTotalProducto*cantidadProducto;
					System.out.println("Precio de la leche: "+precioProducto+" 			"
							+ "Precio+IVA= "+precioTotalProducto);
						if(cantidadProducto>1) {
							System.out.println("Precio de todas las unidades: "+precioGrupoProducto);
						}
				precioCarro=precioCarro+precioGrupoProducto;
				System.out.println("Total acumulado "+precioCarro);
				}
				//Control por si escribimos cualquier cosa que no sea un producto o stop
			}else if(!check) {
				System.out.println("Escribe un producto valido de la lista");
			}
			check2=false;
			a=0;
		}
		
		System.out.println("Precio total de la compra "+precioCarro+"	Numero de productos comprados: "+numProductos);
	return precioCarro;
	}
	//Transaccion final con devolucion del cambio.
	public static void pagar (double precioCarro) {
		boolean check=true;
		while(check) {
			String dineroClienteString = JOptionPane.showInputDialog("El total es de "+precioCarro+". Con cuanto dinero pagará?");
			double dineroCliente = Double.parseDouble(dineroClienteString);
			if(dineroCliente<precioCarro) {
				System.out.println("No le alcanza. Por favor, introduzca otra cantidad.");
			}

			else {
			double cambio=dineroCliente-precioCarro;
			System.out.println("Se le devolverá "+cambio+ " de sus "+dineroCliente+" euros.");
			check=false;
			}
		}
	}
}

