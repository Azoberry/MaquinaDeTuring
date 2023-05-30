import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class Lista {

    Scanner lectura = new Scanner(System.in); //Es para hacer pruebas

    private Nodo raiz;
    private Nodo ultimo;

    // El constructor Lista() inicializa los atributos raiz y ultimo a null.
    // La variable raiz representa el primer nodo de la lista y ultimo representa
    // el último nodo de la lista. Al inicializarlos en null, se está indicando que
    // la lista no tiene ningún nodo y por lo tanto está vacía.
    public Lista() {
        this.raiz = null;
        this.ultimo = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public Nodo getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }

    // El método empty() devuelve true si la lista está vacía
    public boolean empty() {
        return raiz == null;
    }
    
    // El método insertar crea un nuevo nodo con los parámetros de entrada patron y
    // nombre,
    // y lo agrega al final de la lista.
    public void insertar(char caracter) {

        Nodo nuevo = new Nodo(caracter);
        if (empty()) {
            raiz = nuevo;
            ultimo = nuevo;
            nuevo.setIzquierda(null);

        } else {
            ultimo.setDerecha(nuevo);
            nuevo.setIzquierda(ultimo);
            ultimo = nuevo;
        }
    }

    //Para insertar nodos manualmente (son para las pruebas)
    public void insertar2() {

        System.out.print("Ingrese el caracter: ");
        char caracter;

        try {
            caracter = lectura.nextLine().charAt(0);
        } catch (Exception e) {
            caracter = ' ';
        }

        Nodo nuevo = new Nodo(caracter);
        if (empty()) {
            raiz = nuevo;
            ultimo = nuevo;
            nuevo.setIzquierda(null);

        } else {
            ultimo.setDerecha(nuevo);
            nuevo.setIzquierda(ultimo);
            ultimo = nuevo;
        }
    }

    public void imprimir() {
        Nodo aux = raiz;
        if (!empty()) {
            while (aux != null) {
                System.out.print(aux.getCaracter() + " ");
                aux = aux.getDerecha();
            }
        } else {
            System.out.println("La lista esta vacia");
        }
    }

    // // El método validarLexema recorre la lista buscando el patrón que coincide
    // // con el lexema palabraLex. Si se encuentra un patrón válido, se devuelve
    // // el nombre correspondiente a ese patrón. Si no se encuentra un patrón válido,
    // // se devuelve "ERROR".
    // public String validarLexema(String palabraLex) {
    //     Nodo aux = raiz;
    //     String miT = "ERROR";
    //     if (empty() != true) {
    //         miT = palabraLex;
    //         do {
    //             try {
    //                 miT = aux.validarNodo(palabraLex);
    //                 aux = aux.getSiguiente();
    //             } catch (Exception e) {
    //                 aux = aux.getSiguiente();
    //             }
    //         } while (aux != null && miT == "ERROR");
    //     }
    //     return miT;
    // }

    // // El método vaciar vacía la lista, mediante nulos.
    // public void vaciar() {
    //     raiz = null;
    //     ultimo = null;
    // }

    // public void llenar(String carpeta) {
    //     try {
    //         File folder = new File(carpeta);
    //         File[] listOfFiles = folder.listFiles();
    //         int txtFilesCount = 0;
    //         for (File file : listOfFiles) {
    //             if (file.isFile() && file.getName().endsWith(".txt")) {
    //                 txtFilesCount++;
    //             }
    //         }
    //         File[] txtFilesArray = new File[txtFilesCount];
    //         int index = 0;
    //         for (File file : listOfFiles) {
    //             if (file.isFile() && file.getName().endsWith(".txt")) {
    //                 txtFilesArray[index] = file;
    //                 index++;
    //             }
    //         }

    //         for (File file : txtFilesArray) {
    //             String filePath = file.getPath().replaceAll("\\\\", "/");
    //             AFD afd = new AFD();
    //             afd.llenarAFD(filePath);
    //             Token tok = new Token(afd);
    //             insertar(tok);
    //         }

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // // El método leer lee un archivo de texto que contiene lexemas
    // // y los valida contra los patrones almacenados en la lista.
    // // Si un lexema coincide con un patrón, se muestra el nombre
    // // correspondiente a ese patrón.
    // // Si no se encuentra un patrón válido, se muestra "ERROR".
    // public void leer(String lenguaje) {
    //     File archivo = null;
    //     FileReader fr = null;
    //     BufferedReader br = null;
    //     try {
    //         archivo = new File(lenguaje);
    //         fr = new FileReader(archivo);
    //         br = new BufferedReader(fr);
    //         String linea;
    //         while ((linea = br.readLine()) != null) {
    //             String sinEspacio = linea.trim();
    //             String[] palabras = sinEspacio.split("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    //             if (!sinEspacio.isEmpty() && sinEspacio.charAt(0) != '#') {
    //                 for (int i = 0; i < palabras.length; i++) {
    //                     String patron = palabras[i];
    //                     System.out.println(validarLexema(patron));
    //                 }
    //             } else if (!sinEspacio.isEmpty() && sinEspacio.charAt(0) == '#') {
    //             }
    //         }
    //         fr.close();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}