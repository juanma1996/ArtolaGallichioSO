package artolagallicchio;

public class Memoria {
    private Particion[] memoria;
    private ColaDeEspera colaDeEspera;
    
    public Memoria(Particion[] particiones, int tamaño){
        this.memoria = particiones;
        this.colaDeEspera = new ColaDeEspera(tamaño);
    }
    
    public boolean asignarAParticion(int tamañoNecesario, Programa programa){
        boolean encontreDisponible = false;
        for (int i = 0; i < this.memoria.length && !encontreDisponible; i++) {
            if (this.memoria[i].esParticionDisponible(tamañoNecesario)) {
                encontreDisponible = true;
                this.memoria[i].setIntruccionesAEjecutar(programa.getInstrucciones());
                this.memoria[i].particionEnUso();
                programa.setParticionAsignada(this.memoria[i].getId());
            }
        }
        return encontreDisponible;
    }
    
    public void liberarParticion(int idParticion){
        for (int i = 0; i < this.memoria.length; i++) {
            if (this.memoria[i].getId() == idParticion) {
                this.memoria[i].liberarParticion();
            }
        }
    }
      
    public ColaDeEspera getCola(){
        return this.colaDeEspera;
    }

    Particion getParticion(int idParticion) {
        Particion resultado = null;
        for (int i = 0; i < this.memoria.length; i++) {
            if (this.memoria[i].getId() == idParticion) {
                resultado = this.memoria[i];
            }
        }
        return resultado;
    }
    
    public void imprimirMemoria(){
        String imp = "Memoria: | "; 
        for (int i = 0; i < this.memoria.length; i++) { 
            imp += this.memoria[i].toString() + " | "; 
        } 
        System.out.println(imp); 
        System.out.println(""); 
    }    
}
