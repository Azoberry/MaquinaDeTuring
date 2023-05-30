import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*Este código define una clase llamada Lista, que implementa una lista
de objetos Nodo. Cada Nodo contiene un objeto Token que se utiliza 
para almacenar un patrón y el nombre correspondiente a un token del lenguaje a analizar. */

public class Lista {

    private Nodo raiz;
    private Nodo ultimo;

    // La clase Lista tiene métodos para insertar un nuevo Nodo en la lista,
    // imprimir la lista, validar un lexema según los patrones almacenados en la
    // lista,
    // vaciar la lista y leer patrones y lexemas de un archivo de texto.

    // El constructor Lista() inicializa los atributos raiz y ultimo a null.
    // La variable raiz representa el primer nodo de la lista y ultimo representa
    // el último nodo de la lista. Al inicializarlos en null, se está indicando que
    // la lista no tiene ningún nodo y por lo tanto está vacía.
    public Lista() {
        this.raiz = null;
        this.ultimo = null;
    }

    // El método empty() devuelve true si la lista está vacía
    public boolean empty() {
        return raiz == null;
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

    // El método insertar crea un nuevo nodo con los parámetros de entrada patron y
    // nombre,
    // y lo agrega al final de la lista.
    public void insertar(Token t) {

        Nodo nuevo = new Nodo(t);
        if (empty() == true) {
            raiz = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = ultimo.getSiguiente();
        }
    }

    // El método imprimir recorre la lista e imprime el contenido de cada Nodo.
    public void imprimir() {
        Nodo aux = raiz;
        if (empty() != true) {
            do {
                System.out.println(aux.toStringNodo());
                aux = aux.getSiguiente();
            } while (aux != null);
        } else {
            System.out.println("Esta vacia");
        }
    }

    // El método validarLexema recorre la lista buscando el patrón que coincide
    // con el lexema palabraLex. Si se encuentra un patrón válido, se devuelve
    // el nombre correspondiente a ese patrón. Si no se encuentra un patrón válido,
    // se devuelve "ERROR".
    public String validarLexema(String palabraLex) {
        Nodo aux = raiz;
        String miT = "ERROR";
        if (empty() != true) {
            miT = palabraLex;
            do {
                try {
                    miT = aux.validarNodo(palabraLex);
                    aux = aux.getSiguiente();
                } catch (Exception e) {
                    aux = aux.getSiguiente();
                }
            } while (aux != null && miT == "ERROR");
        }
        return miT;
    }

    // El método vaciar vacía la lista, mediante nulos.
    public void vaciar() {
        raiz = null;
        ultimo = null;
    }

    public void llenar(String carpeta) {
        try {
            File folder = new File(carpeta);
            File[] listOfFiles = folder.listFiles();
            int txtFilesCount = 0;
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    txtFilesCount++;
                }
            }
            File[] txtFilesArray = new File[txtFilesCount];
            int index = 0;
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    txtFilesArray[index] = file;
                    index++;
                }
            }

            for (File file : txtFilesArray) {
                String filePath = file.getPath().replaceAll("\\\\", "/");
                AFD afd = new AFD();
                afd.llenarAFD(filePath);
                Token tok = new Token(afd);
                insertar(tok);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // El método leer lee un archivo de texto que contiene lexemas
    // y los valida contra los patrones almacenados en la lista.
    // Si un lexema coincide con un patrón, se muestra el nombre
    // correspondiente a ese patrón.
    // Si no se encuentra un patrón válido, se muestra "ERROR".
    public void leer(String lenguaje) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File(lenguaje);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String sinEspacio = linea.trim();
                String[] palabras = sinEspacio.split("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if (!sinEspacio.isEmpty() && sinEspacio.charAt(0) != '#') {
                    for (int i = 0; i < palabras.length; i++) {
                        String patron = palabras[i];
                        System.out.println(validarLexema(patron));
                    }
                } else if (!sinEspacio.isEmpty() && sinEspacio.charAt(0) == '#') {
                }
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}