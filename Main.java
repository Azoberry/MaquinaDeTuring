import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    //===MAQUINA DE TURING PARA EL COMPLEMENTO===
    // String rutaComplemento = "MT_complemento.txt";
    // File archivoComplemento = new File(rutaComplemento);

    // MDT mdt1 = new MDT(archivoComplemento);
    // //mdt1.imprimirMDT();

    // Lista listaDoble1 = new Lista();

    // System.out.println("Escriba una cadena: ");
    // String cadena1 = sc.nextLine();

    // listaDoble1.escribirCadena(cadena1);

    // //listaDoble1.imprimirCompleta();

    // mdt1.validar(listaDoble1);

    //===MAQUINA DE TURING PARA LOS PARES DE 0 Y 1===
    String ruta01Par = "MT_01Par.txt";
    File archivo01Par = new File(ruta01Par);

    MDT mdt2 = new MDT(archivo01Par);
    //mdt2.imprimirMDT();

    Lista listaDoble2 = new Lista();

    System.out.println("Escriba una cadena: ");
    String cadena2 = sc.nextLine();

    listaDoble2.escribirCadena(cadena2);

    //listaDoble2.imprimirCompleta();

    mdt2.validar(listaDoble2);
    
    sc.close();
    }
}