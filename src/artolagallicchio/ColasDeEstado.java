package artolagallicchio;

public class ColasDeEstado {
    EstadoPrograma[] colaDeEjecucion;
    
    public ColasDeEstado(int cantProcesos, Programa[] programas){
        this.colaDeEjecucion = new EstadoPrograma[cantProcesos];
        for (int i = 0; i < programas.length; i++) {
            Programa programa = programas[i];
            EstadoPrograma estadoPrograma = new EstadoPrograma(programa.toString(), programa.getIdProceso(), programa.getCantInstruccionesEjecutadasTotales(), programa.getCantInstruccionesTotales());
            colaDeEjecucion[i] = estadoPrograma;
        }
    }
    
    public EstadoPrograma[] getColaDeEjecucion(){
        return this.colaDeEjecucion;
    }
    
    @Override
    public String toString(){
        String res="";
        res = "Estado de ejecución actual: \n";
        for (int i = 0; i < this.colaDeEjecucion.length; i++) {
            res += this.colaDeEjecucion[i].toString() + "\n";
        }
       return res;
   }
}
