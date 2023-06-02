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
        this.cabeza = null;
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
        cabeza = raiz.getDerecha();
    }

    //Para imprimir la lista (son para las pruebas)
    public void imprimir() {
        Nodo aux = raiz;
        aux = aux.getDerecha();
        if (aux.getCaracter() != 'b') {
            while (aux.getCaracter() != 'b') {
                System.out.print(aux.getCaracter() + " ");
                aux = aux.getDerecha();
            }
        } else {
            System.out.println("La lista esta vacia");
        }
    }

    public void imprimirCompleta() {
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
}