import java.util.Scanner;
import java.io.File;

public class Prueba {
    public static void main(String[] args) {
        
    //     Transicion transicion = new Transicion();
    //     Scanner sc = new Scanner(System.in);

    //     System.out.println("Ingrese el estado: ");
    //     int estado = sc.nextInt();
    //     sc.nextLine();

    //     System.out.println("Ingrese el caracter a escribir: ");
    //     char escribir;

    //     try {
    //         escribir = sc.nextLine().charAt(0);
    //     } catch (Exception e) {
    //         escribir = ' '; // O asignar otro valor por defecto en caso de entrada inválida
    //     }

    //     System.out.println("Ingrese el movimiento: ");
    //     String movimiento = sc.nextLine();

    //     sc.close();

    //     transicion.setEstado(estado);
    //     transicion.setEscribir(escribir);
    //     try {
    //         //valueOf convierte el valor de "movimiento" en un valor valido para el enum
    //         Transicion.Movimiento movimientoEnum = Transicion.Movimiento.valueOf(movimiento.toUpperCase());
    //         transicion.setMovimiento(movimientoEnum);
    //     } catch (IllegalArgumentException e) {
    //         System.out.println("Movimiento invalido");
    //     }

    //     System.out.println("Estado: " + transicion.getEstado());
    //     System.out.println("Escribir: " + transicion.getEscribir());
    //     System.out.println("Movimiento: " + transicion.getMovimiento());

    // Lista lista = new Lista();
    // for(int i = 0; i < 10; i++) {
    //     lista.insertar2();
    // }
    
    // // lista.imprimir();
    // sc.close();
    Scanner sc = new Scanner(System.in);

    String rutaComplemento = "MT_complemento.txt";
    File archivoComplemento = new File(rutaComplemento);

    Lista listaDoble = new Lista();


    MDT mdt = new MDT(archivoComplemento);
    // mdt.imprimirMDT();

    System.out.println("Escriba una cadena: ");
    String cadena = sc.nextLine();

    listaDoble.escribirCadena(cadena);

    //listaDoble.imprimirCompleta();

    mdt.validar(listaDoble);
    
    
    sc.close();


    
    
    }
}