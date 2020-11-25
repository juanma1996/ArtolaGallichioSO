package artolagallicchio;

public class ColaDeEspera {
    private Programa[] colaDeEspera;
    private int tamaño;             
    private int head      = 0;
    private int tail      = 0;
    private boolean empty = true;
    
    public ColaDeEspera(int tamaño){
        this.colaDeEspera = new Programa[tamaño];
        this.tamaño = tamaño;
    }
    
    public void enqueue(Programa elem) {
        // The queue has space left, enqueue the item
        colaDeEspera[tail] = elem;
        tail        = (tail + 1) % tamaño;
        empty       = false;
    }

    public Programa dequeue() throws Exception {
        // Check if queue is empty and throw exception
        if (empty) {
            throw new Exception("The queue is empty");
        }

        // The queue is not empty, return element
        Programa elem = colaDeEspera[head];
        head   = (head + 1) % tamaño;
        empty  = (head == tail);
        return elem;
    }
    
    public boolean esVacio(){
        return empty;
    }
    
    public Programa[] getProgramasColaDeEspera(){
        Programa [] programas = new Programa[this.tail];
        for (int i = 0; i < this.tail; i++) {
            programas[i] = this.colaDeEspera[i];
        }
        return programas;
    }
    
    public int getTail(){
        return this.tail;
    }

    public void imprimirCola() {
        String imp = "Cola de espera: | "; 
        imp += "se encuentra: ";
        if (empty) {
            imp+= "vacia ";
        }else{
            imp+="ocupada ";
        }
        imp += "Head en: " + head;
        imp += " Tail en: " + tail + " | ";
        for (int i = 0; i < tail; i++) {
            imp += this.colaDeEspera[i].toString() + " | "; 
        }
        System.out.println(imp); 
        System.out.println(""); 
    }
}
