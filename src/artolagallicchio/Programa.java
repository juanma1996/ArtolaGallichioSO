package artolagallicchio;

import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Programa extends Thread{
    
    private String nombre;
    private Instruccion[] instrucciones;
    private Usuario usuario;
    private int cantInstruccionesEjecutadasTotales;
    private int cantInstruccionesEjecutadas;
    private String permisoRequerido;
    private Memoria memoria;
    private int quantum;
    private int idParticionAsignada;
    private String colorProceso;
    private int idProceso;
    private ColasDeEstado colasDeEstado;
    
    public Programa(String nombre, Instruccion[] instrucciones,Usuario usuario,String permisoRequerido, Memoria memoria, int cantInstruccionesEjecutadasTotales,String colorProceso, int idProceso){
        this.nombre = nombre;
        this.instrucciones = instrucciones;
        this.usuario = usuario;
        this.cantInstruccionesEjecutadas = 0;
        this.cantInstruccionesEjecutadasTotales = cantInstruccionesEjecutadasTotales;
        this.permisoRequerido = permisoRequerido;
        this.memoria = memoria;
        this.colorProceso = colorProceso;
        this.idProceso = idProceso;
    }
    
    public int getTamañoNecesario(){
        return instrucciones.length - cantInstruccionesEjecutadasTotales;
    }
    
    public Instruccion[] getInstrucciones(){
        Instruccion [] instruccionesRestantes = new Instruccion[this.instrucciones.length - this.cantInstruccionesEjecutadasTotales];
        int principio = 0;
        for (int i = this.cantInstruccionesEjecutadasTotales; i < this.instrucciones.length; i++) {
            instruccionesRestantes[principio] = this.instrucciones[i];
            principio++;
        }
        return instruccionesRestantes;
    }
    
    public void run() {
        try {
            this.cantInstruccionesEjecutadas = 0;
            this.quantum = 10000;
            if (!usuario.TienePermisos(this.permisoRequerido)) {
                imprimirConColor("El usuario no tiene permisos para ejecutar este programa");
                this.colasDeEstado.getColaDeEjecucion()[this.idProceso].setTerminadoPorPermisosrograma();
                throw new UnsupportedOperationException();
            }
            Instruccion[] instruccionesAEjecutar = this.memoria.getParticion(this.idParticionAsignada).getInstrucciones();
            for (int i = 0; i < instruccionesAEjecutar.length && this.quantum > 0; i++) {
                instruccionesAEjecutar[i].getRecurso().ejecutarInstruccion(instruccionesAEjecutar[i].getInstruccion(), this.usuario, this, this.quantum);
                this.quantum = this.quantum - instruccionesAEjecutar[i].getTiempoDeEjecucion();
                this.cantInstruccionesEjecutadas++;
                this.cantInstruccionesEjecutadasTotales++;
                this.colasDeEstado.getColaDeEjecucion()[this.idProceso].setCantInstruccionesEjecutadas(this.cantInstruccionesEjecutadasTotales);
                Thread.sleep(2000);
            }
            this.memoria.liberarParticion(idParticionAsignada);
            if (this.cantInstruccionesEjecutadas < instruccionesAEjecutar.length) {
                imprimirConColor("El programa " + nombre + " TERMINÓ POR QUANTUM Y ERA ejecutado por el usuario " + usuario.toString());
                Programa nuevoPrograma = new Programa(this.nombre,this.instrucciones,this.usuario,this.permisoRequerido,this.memoria,this.cantInstruccionesEjecutadasTotales,this.colorProceso,this.idProceso);
                nuevoPrograma.setColaDeEstado(this.colasDeEstado);
                this.memoria.getCola().enqueue(nuevoPrograma);
            }else{
                imprimirConColor("El programa " + nombre + " se ejecuto completamente por el usuario: " + usuario.toString());
                System.out.println(this.colorProceso + this.colasDeEstado.toString() +  "\u001B[0m");
            }
        }catch (UnsupportedTemporalTypeException e) {
            this.memoria.liberarParticion(idParticionAsignada);
            this.colasDeEstado.getColaDeEjecucion()[this.idProceso].setTerminadoPorPermisosRecurso();
            imprimirConColor("El programa " + nombre + " se interrumpe ya que el usuario: " + usuario.toString() + " no tiene los permisos necesarios sobre el recurso.");
            this.interrupt();
        }catch (UnsupportedOperationException e) {
            this.memoria.liberarParticion(idParticionAsignada);
            imprimirConColor("El programa " + nombre + " se interrumpe ya que el usuario: " + usuario.toString() + " no tiene los permisos necesarios sobre el programa.");
            this.interrupt();
        }catch (InterruptedException ie) {
            this.memoria.liberarParticion(idParticionAsignada);
            imprimirConColor("El programa " + nombre + " terminó por Quantum y era ejecutado por el usuario " + usuario.toString());
            Programa nuevoPrograma = new Programa(this.nombre,this.instrucciones,this.usuario,this.permisoRequerido,this.memoria,this.cantInstruccionesEjecutadasTotales,this.colorProceso,this.idProceso);
            nuevoPrograma.setColaDeEstado(this.colasDeEstado);
            this.memoria.getCola().enqueue(nuevoPrograma);
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

    public void setParticionAsignada(int id) {
        this.idParticionAsignada = id;
    }
    
    public void imprimirConColor(String mensaje){
        System.out.println(this.colorProceso + mensaje +  "\u001B[0m");
    }
    
    public int getIdProceso(){
        return this.idProceso;
    }
    
    public int getCantInstruccionesTotales(){
        return this.instrucciones.length;
    }
    
    public int getCantInstruccionesEjecutadasTotales(){
        return this.cantInstruccionesEjecutadasTotales;
    }
    
    public void setColaDeEstado(ColasDeEstado colaDeEstado){
        this.colasDeEstado = colaDeEstado;
    }
}
