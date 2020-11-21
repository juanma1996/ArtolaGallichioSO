package artolagallicchio;


public class Particion {
    private int tamaño;
    private int limiteInferior;
    private int limiteSuperior;
    private String[] instrucciones;
    private boolean enUso;
    
    public Particion(int tamaño, int limiteInferior,int limiteSuperior){
        this.tamaño = tamaño;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.instrucciones = new String[tamaño];
        this.enUso = false;
    }
    
    public boolean esParticionDisponible(int tamaño){
        return (!this.enUso && tamaño <= this.tamaño);
    }
    
    public void setIntruccionesAEjecutar(String[] instrucciones){
        this.instrucciones = instrucciones;
    }
    
    public void particionEnUso(){
        this.enUso = true;
    }
}
