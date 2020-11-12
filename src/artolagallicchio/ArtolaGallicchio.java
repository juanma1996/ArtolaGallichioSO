package artolagallicchio;

public class ArtolaGallicchio {
    public static void main(String[] args) {
        
        Usuario nuevoUsuario = new Usuario("Arto");
        
        String[] instrucciones0 = {"A","B","C"};
        String[] instrucciones1 = {"A","H","D"};
        String[] instrucciones2 = {"S"};
        
        int cantProcesos = 3;
        Programa[] procesos = new Programa[3];
        
        procesos[0] = new Programa("primero",instrucciones0, nuevoUsuario);
        procesos[0].start();
        
        procesos[1] = new Programa("segundo",instrucciones1, nuevoUsuario);
        procesos[1].start();      
        
        procesos[2] = new Programa("tercero",instrucciones2, nuevoUsuario);
        procesos[2].start();    
    }   
}
