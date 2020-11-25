package artolagallicchio;

public class EstadoPrograma {
    private String nombre;
    private int idPrograma;
    private int cantInstruccionesEjecutadas;
    private int cantInstruccionesTotales;
    private boolean terminadoPorPermisosRecurso;
    private boolean terminadoPorPermisosPrograma;
    private boolean terminado;
    
    public EstadoPrograma(String nombre, int idPrograma, int cantInstruccionesEjecutadas, int cantInstruccionesTotales){
        this.nombre = nombre;
        this.idPrograma = idPrograma;
        this.cantInstruccionesEjecutadas = cantInstruccionesEjecutadas;
        this.cantInstruccionesTotales = cantInstruccionesTotales;
    }
    
    public int porcentajeCompletado (){
        return (cantInstruccionesEjecutadas*100/cantInstruccionesTotales);
    }
    
    public void setCantInstruccionesEjecutadas(int cantInstruccionesEjecutadas){
        this.cantInstruccionesEjecutadas = cantInstruccionesEjecutadas;
    }
    
    public void setTerminadoPorPermisosRecurso(){
        this.terminadoPorPermisosRecurso = true;
    }
    
    public void setTerminadoPorPermisosrograma(){
        this.terminadoPorPermisosPrograma = true;
    }
    
    public void setTerminado(){
        this.terminado = true;
    }   
    
    @Override
    public String toString(){
       int porcentaje = porcentajeCompletado();
       String res = "Proceso: " + nombre + " se ejecutó en un " + porcentaje + "% ";
        if (terminadoPorPermisosRecurso) {
            res += "y se encuentra interrumpido debido a falta de permisos en recurso.";
        }else if(terminadoPorPermisosPrograma){
            res += "y se encuentra interrumpido debido a falta de permisos en programa.";
        }else if(terminado){
            res += "y se encuentra ejecutado completamente.";
        }
       
       return res;
   }
}
