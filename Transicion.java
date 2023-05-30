public class Transicion {
    private int estado;
    private char escribir;
    private Movimiento movimiento;

    public enum Movimiento {
        L, //Left (izquierda)
        R, //Right (derecha)
        S //Stop (detener)
    }

    public Transicion (){}

    public Transicion(int estado, char escribir, Movimiento movimiento) {
        this.estado = estado;
        this.escribir = escribir;
        this.movimiento = movimiento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public char getEscribir() {
        return escribir;
    }

    public void setEscribir(char escribir) {
        this.escribir = escribir;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

}
