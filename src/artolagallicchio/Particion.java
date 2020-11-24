package artolagallicchio;


public class Particion {
    private int tamaño;
    private Instruccion[] instrucciones;
    private boolean enUso;
    private int id;
    
    public Particion(int tamaño, int idParticion){
        this.tamaño = tamaño;
        this.instrucciones = new Instruccion[tamaño];
        this.enUso = false;
        this.id = idParticion;
    }
    
    public boolean esParticionDisponible(int tamaño){
        return (!this.enUso && tamaño <= this.tamaño);
    }
    
    public void setIntruccionesAEjecutar(Instruccion[] instrucciones){
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

    Instruccion[] getInstrucciones() {
        return this.instrucciones;
    }
    
    public boolean esParticionEnUso(){
        return this.enUso;
    }
    
    @Override
    public String toString(){
       String res = "Partición: " + this.id;
       if (this.enUso) {
         res += " en uso ";
         res += " Cant instrucciones: " + this.instrucciones.length;
       }else{
           res += " esta libre ";  
       }
       return res;
    }
}
