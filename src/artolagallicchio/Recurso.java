package artolagallicchio;


public class Recurso {
    private String nombre;
    private int valor;
    private Monitor monitor;
    private Usuario usuario;
    private String permisoRequerido;
    
    public Recurso (String nombre,Monitor monitor,String permisoRequerido){
        this.nombre = nombre;
        this.valor = 0;
        this.monitor = monitor;
        this.permisoRequerido = permisoRequerido;
    }
    
    public void ejecutarInstruccion (String instruccion,Usuario usuario, Programa programa, int quantum) throws Exception, Throwable{
            if (!usuario.TienePermisos(this.permisoRequerido)) {
                programa.imprimirConColor("El usuario no tiene permisos para ejecutar este recurso");
                throw new UnsupportedOperationException("NotPermissionRequired");
            }
            switch(instruccion) {
                case "P":
                    Thread.sleep(3000);
                    monitor.WaitNoLectores(quantum);
                    programa.imprimirConColor("El recurso " + nombre + " esta tomado para lectura por el programa " + programa.toString() + "  y tiene valor: " + valor);
                    Thread.sleep(3000);  
                    monitor.SignalNoLectores();
                    programa.imprimirConColor("El recurso " + nombre + " es liberado para lectores por el programa " + programa.toString());
                    break;
                case "U":
                    programa.imprimirConColor("El recurso " + nombre + " quiere ser usado por el programa " + programa.toString());
                    monitor.WaitNoEscritores(quantum);
                    programa.imprimirConColor("El recurso " + nombre + " esta siendo usado por el programa " + programa.toString());
                    valor++;
                    Thread.sleep(4000);
                    programa.imprimirConColor("El recurso " + nombre + " se usó y se liberó por el programa " + programa.toString());
                    Thread.sleep(2000);
                    monitor.SignalNoEscritores();
                    break;
                case "D":
                    Thread.sleep(1500);
                    programa.imprimirConColor("El recurso " + nombre + " se liberó por el programa " + programa.toString());
                    monitor.SignalNoEscritores();
                    monitor.SignalNoLectores();
                    break;    
                case "A":
                    Thread.sleep(1500);
                    programa.imprimirConColor("El recurso " + nombre + " está realizando una acción sincrónica por el programa " + programa.toString());
            }
    }

}
