package artolagallicchio;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ArtolaGallicchio {
    
    public static void main(String[] args) {
        try{
            Particion particion0 = new Particion(20, 0);
            Particion particion1 = new Particion(30, 1);
            Particion particion2 = new Particion(50, 2);
            
            Particion[] particiones = new Particion[]{particion0,particion1,particion2};
            Memoria memoria = new Memoria(particiones,10);
            
            String[] permisosUsuario = {"X1","I3"};
            Usuario nuevoUsuario = new Usuario("Arto",permisosUsuario);

            String[] permisosUsuarioDos = {"I3,X1"};
            Usuario nuevoUsuarioDos = new Usuario("Juanma",permisosUsuarioDos);       
            
            String[] permisosUsuarioTres = {"I3","X1","F5"};
            Usuario nuevoUsuarioTres = new Usuario("Angel",permisosUsuarioTres);

            Monitor monitor0 = new Monitor();
            Monitor monitor1 = new Monitor();
            Monitor monitor2 = new Monitor();
            Monitor monitor3 = new Monitor();

            Recurso recurso0 = new Recurso("Impresora 1",monitor0,"X1");
            Recurso recurso1 = new Recurso("Impresora 2",monitor1,"I3");
            Recurso recurso2 = new Recurso("Archivo de texto",monitor2,"I3");
            Recurso recurso3 = new Recurso("Imagen",monitor3,"X1");
            Recurso[] recursos = {recurso0,recurso1};
            
            Instruccion pedirRec0 = new Instruccion("P",4000,"Pedir recurso", recurso0);
            Instruccion usarRec0 = new Instruccion("U",5000,"Usar recurso", recurso0);
            Instruccion liberarRec0 = new Instruccion("L",1500,"Pedir recurso", recurso0);
            
            Instruccion pedirRec1 = new Instruccion("P",4000,"Pedir recurso", recurso1);
            Instruccion usarRec1 = new Instruccion("U",5000,"Usar recurso", recurso1);
            Instruccion liberarRec1 = new Instruccion("L",1500,"Pedir recurso", recurso1);
            
            Instruccion pedirRec2 = new Instruccion("P",4000,"Pedir recurso", recurso2);
            Instruccion usarRec2 = new Instruccion("U",5000,"Usar recurso", recurso2);
            Instruccion liberarRec2 = new Instruccion("L",1500,"Pedir recurso", recurso2);
            
            Instruccion pedirRec3 = new Instruccion("P",4000,"Pedir recurso", recurso3);
            Instruccion usarRec3 = new Instruccion("U",5000,"Usar recurso", recurso3);
            Instruccion liberarRec3 = new Instruccion("L",1500,"Pedir recurso", recurso3);
            
            
            Instruccion[] instrucciones1 = {pedirRec0,pedirRec1, usarRec1,pedirRec1,usarRec0,liberarRec0,liberarRec1,pedirRec1};                                 
            Instruccion[] instrucciones2 = {pedirRec1,pedirRec0,usarRec1,liberarRec1};
            Instruccion[] instrucciones3 = {pedirRec1,pedirRec0,usarRec1,liberarRec1};
            Instruccion[] instrucciones4 = {pedirRec1,pedirRec0,usarRec1,liberarRec1};
            Instruccion[] instrucciones5 = {pedirRec2,pedirRec2,pedirRec3,usarRec3,usarRec2,liberarRec2, liberarRec3};
            Instruccion[] instrucciones6 = {pedirRec2,pedirRec0,usarRec1,liberarRec1,usarRec1,liberarRec1};
            
            ColaDeEspera colaDeEspera = memoria.getCola();
            
            colaDeEspera.enqueue(new Programa("Proceso 1",instrucciones1,nuevoUsuario,recursos,"I3", memoria,0,"\u001b[31m"));
            colaDeEspera.enqueue(new Programa("Proceso 2",instrucciones2, nuevoUsuarioDos,recursos,"I3", memoria,0,"\u001b[32m"));
            colaDeEspera.enqueue(new Programa("Proceso 3",instrucciones3, nuevoUsuario,recursos,"I3", memoria,0,"\u001b[33m"));
            colaDeEspera.enqueue(new Programa("Proceso 4",instrucciones4, nuevoUsuarioTres,recursos,"I3", memoria,0,"\u001b[34m"));
            colaDeEspera.enqueue(new Programa("Proceso 5",instrucciones5,nuevoUsuarioDos,recursos,"X1", memoria,0,"\u001b[35m"));
            colaDeEspera.enqueue(new Programa("Proceso 6",instrucciones5,nuevoUsuarioDos,recursos,"X1", memoria,0,"\u001b[36m"));
            colaDeEspera.enqueue(new Programa("Proceso 7",instrucciones1,nuevoUsuarioDos,recursos,"X1", memoria,0,"\u001b[41m"));
            colaDeEspera.enqueue(new Programa("Proceso 8",instrucciones6,nuevoUsuarioDos,recursos,"F5", memoria,0,"\u001b[43m"));
            
            while (true){ 
                if (memoria.getCola().esVacio()) {                  
                    Thread.sleep(2000);
                } 
                while(!memoria.getCola().esVacio() && memoria.hayParticionDisponible()){
                    colaDeEspera = memoria.getCola();
                    colaDeEspera.imprimirCola();
                    memoria.imprimirMemoria();
                    Programa proceso = colaDeEspera.dequeue();
                    if (!memoria.asignarAParticion(proceso.getTamañoNecesario(), proceso)) {
                        proceso.imprimirConColor("No hay partición disponible para ejecutar el programa : " + proceso.toString() + " se encola el programa.");
                        colaDeEspera.enqueue(proceso);
                    }else{
                        proceso.imprimirConColor("SE ejecuta el PROCESO: " + proceso.toString());
                        proceso.start();
                    }
                    colaDeEspera.imprimirCola();
                }               
            }        
        }catch (Exception e) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, e);

        }
    }   
}
