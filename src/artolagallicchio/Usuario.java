package artolagallicchio;

public class Usuario {
   private String nombre ;
   private String[] permisos;
   
   public Usuario(String nombre, String[] permisos){
       this.nombre = nombre;
       this.permisos = permisos;
   }
   
   public boolean TienePermisos(String permiso){
       boolean tienePermiso = false;
       for (int i = 0; i < this.permisos.length && !tienePermiso; i++) {
           if (this.permisos[i].equalsIgnoreCase(permiso)) {
               tienePermiso = true;
           }
       }
       return tienePermiso;
   }
   
   @Override
   public String toString(){
       return this.nombre;
   }
}
