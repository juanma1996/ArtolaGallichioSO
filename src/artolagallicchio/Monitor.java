package artolagallicchio;


public class Monitor {
    private boolean bloqueoEscritor = false;
    private boolean bloqueoLector = false;
    


    public synchronized void WaitNoEscritores(int quantum)
        throws InterruptedException {
        while (bloqueoEscritor || bloqueoLector) {//Un escritor quiere entrar y tiene que esperar a que no haya un escritor ni un lector
            // Espera al signal
            wait(quantum);
            throw new InterruptedException();
        }
        bloqueoEscritor = true;
    }
    
    public synchronized void WaitNoLectores(int quantum)
        throws InterruptedException {
        while (bloqueoEscritor) { //Un lector quiere entrar y tiene que esperar a que no haya un escritor
            wait(quantum);
            throw new InterruptedException();
        }//Hasta que en un momento lo dejan entrar 
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
