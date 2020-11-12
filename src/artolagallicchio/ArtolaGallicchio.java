package artolagallicchio;

public class ArtolaGallicchio {
    public static void main(String[] args) {
        
        Usuario nuevoUsuario = new Usuario("Arto");
        
        String[] instrucciones0 = {"S0","S1","L0","S0","L0","S0","L1","L0","S0","S1","L1","L0"};
        String[] instrucciones1 = {"S1","L1","S0","L0"};
        
        
        Monitor monitor0 = new Monitor();
        Monitor monitor1 = new Monitor();
        
        Recurso recurso0 = new Recurso("recurso0",monitor0);
        Recurso recurso1 = new Recurso("recurso1",monitor1);
        Recurso[] recursos = {recurso0,recurso1};
        
        int cantProcesos = 2;
        Programa[] procesos = new Programa[2];
        
        procesos[0] = new Programa("primero",instrucciones0,nuevoUsuario,recursos);
        procesos[0].start();
        
        procesos[1] = new Programa("segundo",instrucciones1, nuevoUsuario,recursos);
        procesos[1].start();             
    }   
}
