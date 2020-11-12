package artolagallicchio;

public class Usuario {
   private String nombre ;
   
   public Usuario(String nombre){
       this.nombre = nombre;
   }
   
   @Override
   public String toString(){
       return this.nombre;
   }
}
