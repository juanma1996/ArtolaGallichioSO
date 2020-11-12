package artolagallicchio;


public class Monitor {
    private boolean bloqueoEscritor = false;
    private boolean bloqueoLector = false;



    public synchronized void WaitNoEscritores()
        throws InterruptedException {
        while (bloqueoEscritor || bloqueoLector) {
            // Espera al signal
            wait();
        }
        bloqueoEscritor = true;
    }
    
    public synchronized void WaitNoLectores()
        throws InterruptedException {
        while (bloqueoEscritor) { //Un lector quiere entrar y tiene que esperar a que no haya un escritor
            wait();
        }                        //Hasta que en un momento lo dejan entrar 
        bloqueoLector = true;
    }

    public synchronized void SignalNoLectores() {
        bloqueoLector = false;
        //Deja entrar al lector esperando
         notifyAll();       
    }

    public synchronized void SignalNoEscritores() {
        bloqueoEscritor = false;
        notifyAll();
    }
}
