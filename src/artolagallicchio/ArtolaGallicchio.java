package artolagallicchio;

public class ArtolaGallicchio {
    public static void main(String[] args) {
        
        String[] permisosUsuario = {"X1","I3"};
        Usuario nuevoUsuario = new Usuario("Arto",permisosUsuario);
        
        String[] permisosUsuarioDos = {"I3"};
        Usuario nuevoUsuarioDos = new Usuario("Juanma",permisosUsuarioDos);
        
        String[] instrucciones0 = {"S0","S1","L0","S0","L0","S0","L1","L0","S0","S1","L1","L0"};
        String[] instrucciones1 = {"S1","L1","S0","L0"};
        String[] instrucciones2 = {"S1","S0","L0","L1","S0","L0","S1","S0","L0","L1","S0","L0"};
        
        
        Monitor monitor0 = new Monitor();
        Monitor monitor1 = new Monitor();
        Monitor monitor2 = new Monitor();
        
        Recurso recurso0 = new Recurso("recurso0",monitor0,"X1");
        Recurso recurso1 = new Recurso("recurso1",monitor1,"I3");
        Recurso recurso2 = new Recurso("recurso2",monitor2,"I3");
        Recurso[] recursos = {recurso0,recurso1,recurso2};
        
        int cantProcesos = 3;
        Programa[] procesos = new Programa[cantProcesos];
        
        procesos[0] = new Programa("primero",instrucciones0,nuevoUsuario,recursos,"I3");
        procesos[0].start();
        
        procesos[1] = new Programa("segundo",instrucciones1, nuevoUsuario,recursos,"I3");
        procesos[1].start();    
        
        procesos[2] = new Programa("tercero",instrucciones2, nuevoUsuarioDos,recursos,"I3");
        procesos[2].start(); 
    }   
}
