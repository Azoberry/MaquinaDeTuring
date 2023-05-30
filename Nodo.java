public class Nodo {

    private char caracter;
    private Nodo derecha; //siguiente
    private Nodo izquierda; //anterior

    public Nodo(char caracter) {
        this.caracter = caracter;
        this.derecha = null;
        this.izquierda = null;
    }

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    // public String validarNodo(String cadena) {
    //     try {
    //         return this.dato.validar(cadena);
    //     } catch (Exception e) {
    //         return "ERROR";
    //     }
    // }

    // public String toStringNodo() {
    //     try {
    //         return this.dato.toString();
    //     } catch (Exception e) {
    //         return "ERROR";
    //     }
    // }
}
