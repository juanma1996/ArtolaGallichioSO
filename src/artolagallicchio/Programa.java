package artolagallicchio;


public class Programa extends Thread{
    
    private String nombre;
    private String[] instrucciones;
    private Usuario usuario;
    
    public Programa(String nombre, String[] instrucciones,Usuario usuario){
        this.nombre = nombre;
        this.instrucciones = instrucciones;
        this.usuario = usuario;
    }
    
    public void run() {
        try {
            for (int i = 0; i < instrucciones.length; i++) {
                Thread.sleep(2200);
                System.out.println("La instrucción " + instrucciones[i] + " se esta ejecutando por el usuario " + usuario.toString());
                Thread.sleep(700);
                System.out.println("La instrucción " + instrucciones[i] + " se ejecutó por el usuario " + usuario.toString());
            }
        } catch (InterruptedException ie) {
        }
    }
}
