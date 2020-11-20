package artolagallicchio;


public class Programa extends Thread{
    
    private String nombre;
    private String[] instrucciones;
    private Usuario usuario;
    private Recurso[] recursos;
    private int cantInstruccionesEjecutadas;
    private String permisoRequerido;
    
    public Programa(String nombre, String[] instrucciones,Usuario usuario,Recurso[] recursos,String permisoRequerido){
        this.nombre = nombre;
        this.instrucciones = instrucciones;
        this.usuario = usuario;
        this.recursos = recursos;
        this.cantInstruccionesEjecutadas = 0;
        this.permisoRequerido = permisoRequerido;
    }
    
    public void run() {
        try {
            if (!usuario.TienePermisos(this.permisoRequerido)) {
                System.out.println("El usuario no tiene permisos para ejecutar este programa");
                throw new Exception("El usuario no tiene permisos para ejecutar este programa");
            }
            for (int i = 0; i < instrucciones.length; i++) {
                System.out.println("La instrucción " + instrucciones[i] + " se esta ejecutando por el programa " + nombre + " por el usuario " + usuario.toString());
                Thread.sleep(1000);
                switch(instrucciones[i]) {
                    case "S0":
                        recursos[0].instruccion("S",usuario,this);
                        break;
                    case "L0":
                        recursos[0].instruccion("L",usuario,this);
                        break;
                    case "S1":
                        recursos[1].instruccion("S",usuario,this);
                        break;
                    case "L1":
                        recursos[1].instruccion("L",usuario,this);
                        break;    
                    default:
                }
                Thread.sleep(700);
                this.cantInstruccionesEjecutadas++;
                System.out.println("La instrucción " + instrucciones[i] + " se ejecutó por el programa " + nombre + " por el usuario " + usuario.toString());
            }
        }catch (InterruptedException ie) {
        }catch (Exception e) {
            System.out.println("El programa " + nombre + " se interrumpe ya que el usuario: " + usuario.toString() + " no tiene los permisos necesarios.");
            this.interrupt();
        }
    }
    
    @Override
    public String toString(){
       return this.nombre;
   }
}
