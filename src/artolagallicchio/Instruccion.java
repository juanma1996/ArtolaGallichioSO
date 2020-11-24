package artolagallicchio;

public class Instruccion {
   private String instruccion;
   private int tiempoDeEjecucion;
   private String descripcion;
   private Recurso recurso;
   
   public Instruccion (String instruccion, int tiempoDeEjecucion, String descripcion, Recurso recurso){
       this.instruccion = instruccion;
       this.tiempoDeEjecucion = tiempoDeEjecucion;
       this.descripcion = descripcion;
       this.recurso = recurso;
   }
   
   public String getInstruccion(){
       return this.instruccion;
   }
   
   public String getDescripcion(){
       return this.descripcion;
   }
   
   public int getTiempoDeEjecucion(){
       return this.tiempoDeEjecucion;
   }
   
   public Recurso getRecurso(){
       return this.recurso;
   }
   
   @Override
   public String toString(){
    return this.descripcion;
   }
}
