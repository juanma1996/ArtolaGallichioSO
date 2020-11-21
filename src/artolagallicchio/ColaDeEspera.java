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
    
    public void enqueue(Programa elem) throws Exception {
        // Check if the queue is full and throw exception
        if (head == tail && !empty) {
            throw new Exception("Cannot enqueue " + elem);
        }

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
        return this.empty;
    }
}
