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
    
    public void instruccion (String instruccion,Usuario usuario, Programa programa) throws Exception, Throwable{
            if (!usuario.TienePermisos(this.permisoRequerido)) {
                programa.imprimirConColor("El usuario no tiene permisos para ejecutar este recurso");
                throw new InterruptedException("NotPermissionRequired");
            }
            //programa.imprimirConColor("El recurso " + nombre + " se comenzo a leer/escribir por el programa " + programa.toString() + " y por el usuario: " + usuario.toString());
            switch(instruccion) {
                case "S":
                    monitor.WaitNoEscritores();
                    Thread.sleep(3000);
                    programa.imprimirConColor("El recurso " + nombre + " esta tomado por el programa " + programa.toString() + "  y tiene valor: " + valor);
                    valor++;
                    Thread.sleep(1000);
                    programa.imprimirConColor("El recurso " + nombre + " es liberado por el programa " + programa.toString() + " y tiene valor: " + valor);
                    monitor.SignalNoEscritores();
                    break;
                case "L":
                    Thread.sleep(1000);
                    programa.imprimirConColor("El recurso " + nombre + " espera leerlo tomado por el programa " + programa.toString());
                    monitor.WaitNoLectores();              
                    if (valor % 2 == 0) {
                        programa.imprimirConColor("El recurso " + nombre + " esta tomado por el programa " + programa.toString() + " y es par con valor: " + valor);
                    }
                    Thread.sleep(1500);
                    programa.imprimirConColor("El recurso " + nombre + " es liberado por el programa lector " + programa.toString());
                    monitor.SignalNoLectores();
                    break;
                case "ultima":                    
                    programa.imprimirConColor("EJECUTAMOS COMPLETO el programa " + programa.toString());                   
                default:
            }
    }

}
