public class Lista {

    private Nodo raiz;
    private Nodo ultimo;
    private Nodo cabeza;

    // El constructor Lista() inicializa los atributos raiz y ultimo a null.
    // El nodo raiz representa el primer nodo de la lista y ultimo representa
    // el último nodo de la lista. Al inicializarlos en null, se está indicando que
    // la lista no tiene ningún nodo y por lo tanto está vacía.
    public Lista() {
        this.raiz = null;
        this.ultimo = null;
        this.cabeza = raiz;
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

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    public void mover(Transicion.Movimiento movimiento) {
        if (!empty()) {
            switch (movimiento) {
                case R:
                    moverDerecha();
                    break;
                case L:
                    moverIzquierda();
                    break;
                case S:
                    break;
            }
        }
    }

    public void moverDerecha() {
        if (this.cabeza.getDerecha() != null) {
            this.cabeza = this.cabeza.getDerecha();
        }
    }

    public void moverIzquierda() {
        if (this.cabeza.getIzquierda() != null) {
            this.cabeza = this.cabeza.getIzquierda();
        }
    }

    public void escribir(char caracter) {
        this.cabeza.setCaracter(caracter);
    }

    public char obtenerCaracter() {
        return this.cabeza.getCaracter();
    }

    // El metodo empty() devuelve true si la lista está vacía
    public boolean empty() {
        return raiz == null;
    }
    
    //Metodo para insertar un caracter en el nodo
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

    //Metodo donde cada caracter de la cadena se ingresara a un nodo de la lista
    public void escribirCadena(String cadena) {
        char beta = 'b';
        insertar(beta);
        String cadenaSinEspacios = cadena.replaceAll("\\s", "");
        for (int i = 0; i < cadenaSinEspacios.length(); i++) {
            insertar(cadenaSinEspacios.charAt(i));
        }
        insertar(beta);
    }

    //Para imprimir la lista (son para las pruebas)
    public void imprimir() {
        Nodo aux = raiz;
        aux = aux.getDerecha();
        if (!empty()) {
            while (aux.getCaracter() != 'b') {
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