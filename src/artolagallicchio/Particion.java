package artolagallicchio;


public class Particion {
    private int tamaño;
    private int limiteInferior;
    private int limiteSuperior;
    private String[] instrucciones;
    private boolean enUso;
    private int id;
    
    public Particion(int tamaño, int limiteInferior,int limiteSuperior, int idParticion){
        this.tamaño = tamaño;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.instrucciones = new String[tamaño];
        this.enUso = false;
        this.id = idParticion;
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

    int getId() {
        return this.id;
    }

    void liberarParticion() {
        this.enUso = false;
        this.instrucciones = null;
    }

    String[] getInstrucciones() {
        return this.instrucciones;
    }
}
