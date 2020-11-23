package artolagallicchio;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Programa extends Thread{
    
    private String nombre;
    private String[] instrucciones;
    private Usuario usuario;
    private Recurso[] recursos;
    private int cantInstruccionesEjecutadasTotales;
    private int cantInstruccionesEjecutadas;
    private String permisoRequerido;
    private Memoria memoria;
    private int quantum;
    private int idParticionAsignada;
    
    public Programa(String nombre, String[] instrucciones,Usuario usuario,Recurso[] recursos,String permisoRequerido, Memoria memoria, int cantInstruccionesEjecutadasTotales){
        this.nombre = nombre;
        this.instrucciones = instrucciones;
        this.usuario = usuario;
        this.recursos = recursos;
        this.cantInstruccionesEjecutadas = 0;
        this.cantInstruccionesEjecutadasTotales = cantInstruccionesEjecutadasTotales;
        this.permisoRequerido = permisoRequerido;
        this.memoria = memoria;
    }
    
    public int getTamañoNecesario(){
        return instrucciones.length - cantInstruccionesEjecutadasTotales;
    }
    
    public String[] getInstrucciones(){
        String [] instruccionesRestantes = new String[this.instrucciones.length - this.cantInstruccionesEjecutadasTotales];
        int principio = 0;
        for (int i = this.cantInstruccionesEjecutadasTotales; i < this.instrucciones.length; i++) {
            instruccionesRestantes[principio] = this.instrucciones[this.cantInstruccionesEjecutadasTotales];
            principio++;
        }
        return instruccionesRestantes;
    }
    
    public void run() {
        try {
            this.cantInstruccionesEjecutadas = 0;
            this.quantum = 10;
            if (!usuario.TienePermisos(this.permisoRequerido)) {
                System.out.println("El usuario no tiene permisos para ejecutar este programa");
                this.finalize();
            }
            String[] instruccionesAEjecutar = this.memoria.getParticion(this.idParticionAsignada).getInstrucciones();
            for (int i = 0; i < instruccionesAEjecutar.length && this.quantum > 0; i++) {
                System.out.println("La instrucción " + instruccionesAEjecutar[i] + " se esta ejecutando por el programa " + nombre + " por el usuario " + usuario.toString());
                Thread.sleep(1000);
                switch(instruccionesAEjecutar[i]) {
                    case "S0":
                        this.quantum = this.quantum - 4;
                        recursos[0].instruccion("S",usuario,this);
                        break;
                    case "L0":
                        this.quantum = this.quantum - 6;
                        recursos[0].instruccion("L",usuario,this);
                        break;
                    case "S1":
                        this.quantum = this.quantum - 2;
                        recursos[1].instruccion("S",usuario,this);
                        break;
                    case "L1":
                        this.quantum = this.quantum - 3;
                        recursos[1].instruccion("L",usuario,this);
                        break;    
                    default:
                }
                Thread.sleep(700);
                this.cantInstruccionesEjecutadas++;
                this.cantInstruccionesEjecutadasTotales++;
                System.out.println("La instrucción " + instruccionesAEjecutar[i] + " se ejecutó por el programa " + nombre + " por el usuario " + usuario.toString());
            }
            this.memoria.liberarParticion(idParticionAsignada);
            this.memoria.imprimirMemoria();
            if (this.cantInstruccionesEjecutadas < instruccionesAEjecutar.length) {
                this.memoria.getCola().imprimirCola();
                System.out.println("El programa " + nombre + " TERMINÓ POR QUANTUM Y ERA ejecutado por el usuario " + usuario.toString());
                Programa nuevoPrograma = new Programa(this.nombre,this.instrucciones,this.usuario,this.recursos,this.permisoRequerido,this.memoria,this.cantInstruccionesEjecutadasTotales);
                this.memoria.getCola().enqueue(nuevoPrograma);
                this.memoria.imprimirMemoria();
                this.memoria.getCola().imprimirCola();
            }
            this.finalize();
        }catch (InterruptedException ie) {
            this.memoria.liberarParticion(idParticionAsignada);
            System.out.println("El programa " + nombre + " se interrumpe ya que el usuario: " + usuario.toString() + " no tiene los permisos necesarios.");
            this.interrupt();
        }catch (Exception e) {
            this.memoria.liberarParticion(idParticionAsignada);
            this.interrupt();
        } catch (Throwable ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String toString(){
       return this.nombre;
   }

    void setParticionAsignada(int id) {
        this.idParticionAsignada = id;
    }
}
