package artolagallicchio;


public class Recurso {
    private String nombre;
    private int valor;
    private Monitor monitor;
    private Usuario usuario;
    
    public Recurso (String nombre,Monitor monitor){
        this.nombre = nombre;
        this.valor = 0;
        this.monitor = monitor;
    }
    
    public void instruccion (String instruccion,Usuario usuario, Programa programa){
        try{
            System.out.println("El recurso " + nombre + " se comenzo a leer/escribir por el programa " + programa.toString() + " y por el usuario: " + usuario.toString());
            switch(instruccion) {
                case "S":
                    monitor.WaitNoEscritores();
                    Thread.sleep(3000);
                    System.out.println("El recurso " + nombre + " esta tomado por el programa " + programa.toString() + "  y tiene valor: " + valor);
                    valor++;
                    Thread.sleep(1000);
                    System.out.println("El recurso " + nombre + "es liberado por el programa " + programa.toString() + " y tiene valor: " + valor);
                    monitor.SignalNoEscritores();
                    break;
                case "L":
                    Thread.sleep(1000);
                    System.out.println("El recurso " + nombre + " espera leerlo tomado por el programa " + programa.toString());
                    monitor.WaitNoLectores();              
                    if (valor % 2 == 0) {
                        System.out.println("El recurso " + nombre + "esta tomado por el programa " + programa.toString() + " y es par con valor: " + valor);
                    }
                    Thread.sleep(1500);
                    System.out.println("El recurso " + nombre + "es liberado por el programa lector " + programa.toString());
                    monitor.SignalNoLectores();
                    break;
                default:
            }
        }catch (InterruptedException ie) {
        }catch (Exception e) {
        }
    }

}
