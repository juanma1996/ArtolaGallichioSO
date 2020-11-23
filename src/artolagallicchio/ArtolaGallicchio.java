package artolagallicchio;

public class ArtolaGallicchio {
    
    public static void main(String[] args) {
        try{
            Particion particion0 = new Particion(20, 0, 19, 0);
            Particion particion1 = new Particion(30, 20, 49, 1);
            Particion particion2 = new Particion(50, 50, 99, 2);
            
            Particion[] particiones = new Particion[]{particion0,particion1,particion2};
            Memoria memoria = new Memoria(particiones,50);
            
            String[] permisosUsuario = {"X1","I3"};
            Usuario nuevoUsuario = new Usuario("Arto",permisosUsuario);

            String[] permisosUsuarioDos = {"I3"};
            Usuario nuevoUsuarioDos = new Usuario("Juanma",permisosUsuarioDos);

            String[] instrucciones0 = {"S0","S1","L0","S0","L0","S0","L1","L0","S0","S1","L1","L0", "ultima"};
            String[] instrucciones1 = {"S1","L1","S0","L0","ultima"};
            String[] instrucciones2 = {"S1","S0","L0","L1","S0","L0","S1","S0","L0","L1","S0","L0","ultima"};


            Monitor monitor0 = new Monitor();
            Monitor monitor1 = new Monitor();
            Monitor monitor2 = new Monitor();

            Recurso recurso0 = new Recurso("recurso0",monitor0,"X1");
            Recurso recurso1 = new Recurso("recurso1",monitor1,"I3");
            Recurso recurso2 = new Recurso("recurso2",monitor2,"I3");
            Recurso[] recursos = {recurso0,recurso1,recurso2};
            
            ColaDeEspera colaDeEspera = memoria.getCola();
            
            colaDeEspera.enqueue(new Programa("primero",instrucciones0,nuevoUsuario,recursos,"I3", memoria,0,"\u001b[31m"));
            colaDeEspera.enqueue(new Programa("segundo",instrucciones1, nuevoUsuario,recursos,"I3", memoria,0,"\u001b[32m"));
            colaDeEspera.enqueue(new Programa("tercero",instrucciones2, nuevoUsuarioDos,recursos,"I3", memoria,0,"\u001b[35m"));
            while (true){
                if (memoria.getCola().esVacio()) {
                    Thread.sleep(10000);
                }                    
                while(!memoria.getCola().esVacio()){
                    colaDeEspera = memoria.getCola();
                    colaDeEspera.imprimirCola();
                    memoria.imprimirMemoria();
                    Programa proceso = colaDeEspera.dequeue();
                    if (!memoria.asignarAParticion(proceso.getTamañoNecesario(), proceso)) {
                        proceso.imprimirConColor("SE ENCOLA EL PROCESO: " + proceso.toString());
                        colaDeEspera.enqueue(proceso);
                    }else{
                        proceso.imprimirConColor("SE ejecuta el PROCESO: " + proceso.toString());
                        proceso.start();
                    }
                    colaDeEspera.imprimirCola();
                }
            }        
        }catch (Exception e) {
        }
    }   
}
