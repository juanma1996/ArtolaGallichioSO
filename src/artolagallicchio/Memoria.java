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
            }
        }
        return encontreDisponible;
    }
      
    public ColaDeEspera getCola(){
        return this.colaDeEspera;
    }
}
