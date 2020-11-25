package artolagallicchio;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArtolaGallicchio {
    
    public static void main(String[] args) {
        try{
            Memoria memoria = null;
            boolean opcionCorrecta = false;
            boolean opcionIngresada = false;
            int opcionMenu = 0;
            Scanner in = new Scanner(System.in);
            
            System.out.println("\n Hola, bienvenido al simulador de Artola y Gallicchio");
            System.out.println("\n Para comenzar la simulación debes ingresar la opción de contexto a simular deseada");
            System.out.println("\n Actualmente contamos con tres escenarios disponibles que se enumeran del 1 al 3.");
            
            while(!opcionCorrecta){
                System.out.println("\n Elija el deseado: ");                              
                while (!opcionIngresada){
                    in = new Scanner(System.in);
                    try {
                        opcionMenu = in.nextInt();
                        opcionIngresada = true;
                    } catch (Exception ex) {
                        System.out.println("\n Ingrese un numero entero! \n");
                    }
                }               
                switch (opcionMenu) {
                    case 1:
                        memoria = creacionEscenario1();
                        opcionCorrecta = true;
                        break;
                    case 2:
                        memoria = creacionEscenario2();
                        opcionCorrecta = true;
                        break;
                    case 3:
                        memoria = creacionEscenario3();
                        opcionCorrecta = true;
                        break;
                    default:
                        break;
                }
            }
                          
            ColaDeEspera colaDeEspera = memoria.getCola();
            ColasDeEstado colaDeEstado = new ColasDeEstado(colaDeEspera.getTail(), colaDeEspera.getProgramasColaDeEspera());
            for (int i = 0; i < colaDeEspera.getProgramasColaDeEspera().length; i++) {
                colaDeEspera.getProgramasColaDeEspera()[i].setColaDeEstado(colaDeEstado);
            }
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
    
    public static Memoria creacionEscenario1(){
            Particion particion0 = new Particion(20, 0);
            Particion particion1 = new Particion(30, 1);
            Particion particion2 = new Particion(50, 2);
            
            Particion[] particiones = new Particion[]{particion0,particion1,particion2};
            Memoria memoria = new Memoria(particiones,10);
            
            String[] permisosUsuario = {"X1","I3"};
            Usuario nuevoUsuario = new Usuario("Arto",permisosUsuario);

            String[] permisosUsuarioDos = {"I3","X1"};
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
            
            Instruccion pedirRec0 = new Instruccion("P",4000,"Pedir recurso", recurso0);
            Instruccion usarRec0 = new Instruccion("U",5000,"Usar recurso", recurso0);
            Instruccion liberarRec0 = new Instruccion("L",1500,"Liberar recurso", recurso0);
            
            Instruccion pedirRec1 = new Instruccion("P",4000,"Pedir recurso", recurso1);
            Instruccion usarRec1 = new Instruccion("U",5000,"Usar recurso", recurso1);
            Instruccion liberarRec1 = new Instruccion("L",1500,"Liberar recurso", recurso1);
            
            Instruccion pedirRec2 = new Instruccion("P",4000,"Pedir recurso", recurso2);
            Instruccion usarRec2 = new Instruccion("U",5000,"Usar recurso", recurso2);
            Instruccion liberarRec2 = new Instruccion("L",1500,"Liberar recurso", recurso2);
            
            Instruccion pedirRec3 = new Instruccion("P",4000,"Pedir recurso", recurso3);
            Instruccion usarRec3 = new Instruccion("U",5000,"Usar recurso", recurso3);
            Instruccion liberarRec3 = new Instruccion("L",1500,"Liberar recurso", recurso3);           
            
            Instruccion[] instrucciones1 = {pedirRec0,pedirRec1, usarRec1,pedirRec1,usarRec0,liberarRec0,liberarRec1,pedirRec1};                                 
            Instruccion[] instrucciones2 = {pedirRec1,pedirRec0,usarRec1,liberarRec1};
            Instruccion[] instrucciones3 = {pedirRec1,pedirRec0,usarRec1,liberarRec1};
            Instruccion[] instrucciones4 = {pedirRec1,pedirRec0,usarRec1,liberarRec1};
            Instruccion[] instrucciones5 = {pedirRec2,pedirRec2,pedirRec3,usarRec3,usarRec2,liberarRec2, liberarRec3};
            Instruccion[] instrucciones6 = {pedirRec2,pedirRec0,usarRec1,liberarRec1,usarRec1,liberarRec1};
            
            ColaDeEspera colaDeEspera = memoria.getCola();
            
            Programa programa0 = new Programa("Proceso 1",instrucciones1,nuevoUsuario,"I3", memoria,0,"\u001b[31m",0);
            Programa programa1 = new Programa("Proceso 2",instrucciones2, nuevoUsuarioDos,"I3", memoria,0,"\u001b[32m",1);
            Programa programa2 = new Programa("Proceso 3",instrucciones3, nuevoUsuario,"I3", memoria,0,"\u001b[33m",2);
            Programa programa3 = new Programa("Proceso 4",instrucciones4, nuevoUsuarioTres,"I3", memoria,0,"\u001b[34m",3);
            Programa programa4 = new Programa("Proceso 5",instrucciones5,nuevoUsuarioDos,"X1", memoria,0,"\u001b[35m",4);
            Programa programa5 = new Programa("Proceso 6",instrucciones5,nuevoUsuarioDos,"X1", memoria,0,"\u001b[36m",5);
            Programa programa6 = new Programa("Proceso 7",instrucciones1,nuevoUsuarioDos,"X1", memoria,0,"\u001b[41m",6);
            Programa programa7 = new Programa("Proceso 8",instrucciones6,nuevoUsuarioDos,"F5", memoria,0,"\u001b[43m",7);
                                    
            colaDeEspera.enqueue(programa0);
            colaDeEspera.enqueue(programa1);
            colaDeEspera.enqueue(programa2);
            colaDeEspera.enqueue(programa3);
            colaDeEspera.enqueue(programa4);
            colaDeEspera.enqueue(programa5);
            colaDeEspera.enqueue(programa6);
            colaDeEspera.enqueue(programa7);
                       
            memoria.setCola(colaDeEspera);
            
            return memoria;
    }
    
    public static Memoria creacionEscenario2(){
            Particion particion0 = new Particion(50, 0);
            Particion particion1 = new Particion(80, 1);
            Particion particion2 = new Particion(90, 2);
            
            Particion[] particiones = new Particion[]{particion0,particion1,particion2};
            Memoria memoria = new Memoria(particiones,20);
            
            String[] permisosUsuario = {"X1","I3"};
            Usuario nuevoUsuario = new Usuario("Arto",permisosUsuario);

            String[] permisosUsuarioDos = {"I3","X1","F5"};
            Usuario nuevoUsuarioDos = new Usuario("Juanma",permisosUsuarioDos);       
            
            String[] permisosUsuarioTres = {"I3","X1","F5"};
            Usuario nuevoUsuarioTres = new Usuario("Angel",permisosUsuarioTres);
            
            String[] permisosUsuarioCuatro = {"I3","X1","F5"};
            Usuario nuevoUsuarioCuatro = new Usuario("Lucia",permisosUsuarioCuatro);

            Monitor monitor0 = new Monitor();
            Monitor monitor1 = new Monitor();
            Monitor monitor2 = new Monitor();
            Monitor monitor3 = new Monitor();
            Monitor monitor4 = new Monitor();

            Recurso recurso0 = new Recurso("Imagen",monitor0,"X1");
            Recurso recurso1 = new Recurso("Impresora 2",monitor1,"I3");
            Recurso recurso2 = new Recurso("Archivo de texto",monitor2,"I3");
            Recurso recurso3 = new Recurso("Impresora 1",monitor3,"X1");
            Recurso recurso4 = new Recurso("Otro Recurso RSR",monitor4,"F5");
            
            Instruccion pedirRec0 = new Instruccion("P",4000,"Pedir recurso", recurso0);
            Instruccion usarRec0 = new Instruccion("U",5000,"Usar recurso", recurso0);
            Instruccion liberarRec0 = new Instruccion("L",1500,"Liberar recurso", recurso0);
            
            Instruccion pedirRec1 = new Instruccion("P",4000,"Pedir recurso", recurso1);
            Instruccion usarRec1 = new Instruccion("U",5000,"Usar recurso", recurso1);
            Instruccion liberarRec1 = new Instruccion("L",1500,"Liberar recurso", recurso1);
            
            Instruccion pedirRec2 = new Instruccion("P",4000,"Pedir recurso", recurso2);
            Instruccion usarRec2 = new Instruccion("U",5000,"Usar recurso", recurso2);
            Instruccion liberarRec2 = new Instruccion("L",1500,"Liberar recurso", recurso2);
            
            Instruccion pedirRec3 = new Instruccion("P",4000,"Pedir recurso", recurso3);
            Instruccion usarRec3 = new Instruccion("U",5000,"Usar recurso", recurso3);
            Instruccion liberarRec3 = new Instruccion("L",1500,"Liberar recurso", recurso3);
            
            Instruccion pedirRec4 = new Instruccion("P",4000,"Pedir recurso", recurso4);
            Instruccion usarRec4 = new Instruccion("U",5000,"Usar recurso", recurso4);
            Instruccion liberarRec4 = new Instruccion("L",1500,"Liberar recurso", recurso4);
            
            Instruccion[] instrucciones1 = {pedirRec0,pedirRec1, usarRec1,pedirRec1,usarRec0,liberarRec0,liberarRec1,pedirRec1};                                 
            Instruccion[] instrucciones2 = {pedirRec1,pedirRec0,usarRec1,liberarRec1,pedirRec4,usarRec4,usarRec4,usarRec4,liberarRec4};
            Instruccion[] instrucciones3 = {pedirRec1,pedirRec0,usarRec1,liberarRec1};
            Instruccion[] instrucciones4 = {pedirRec1,pedirRec0,usarRec1,liberarRec1};
            Instruccion[] instrucciones5 = {pedirRec2,pedirRec2,pedirRec3,usarRec3,usarRec2,liberarRec2, liberarRec3};
            Instruccion[] instrucciones6 = {pedirRec2,pedirRec0,usarRec1,liberarRec1,usarRec1,liberarRec1};
            Instruccion[] instrucciones7 = {pedirRec2,pedirRec0,pedirRec4,usarRec1,usarRec4,liberarRec1,liberarRec4,usarRec1,liberarRec1};
            
            ColaDeEspera colaDeEspera = memoria.getCola();
            
            Programa programa0 = new Programa("Proceso 1",instrucciones1,nuevoUsuario,"I3", memoria,0,"\u001b[31m",0);
            Programa programa1 = new Programa("Proceso 2",instrucciones2, nuevoUsuarioDos,"I3", memoria,0,"\u001b[32m",1);
            Programa programa2 = new Programa("Proceso 3",instrucciones3, nuevoUsuario,"I3", memoria,0,"\u001b[33m",2);
            Programa programa3 = new Programa("Proceso 4",instrucciones4, nuevoUsuarioTres,"I3", memoria,0,"\u001b[34m",3);
            Programa programa4 = new Programa("Proceso 5",instrucciones5,nuevoUsuarioDos,"X1", memoria,0,"\u001b[35m",4);
            Programa programa5 = new Programa("Proceso 6",instrucciones1,nuevoUsuarioDos,"X1", memoria,0,"\u001b[36m",5);
            Programa programa6 = new Programa("Proceso 7",instrucciones1,nuevoUsuarioDos,"X1", memoria,0,"\u001b[41m",6);
            Programa programa7 = new Programa("Proceso 8",instrucciones6,nuevoUsuarioDos,"X1", memoria,0,"\u001b[43m",7);
            Programa programa8 = new Programa("Proceso 9",instrucciones7,nuevoUsuarioDos,"X1", memoria,0,"\u001b[44m",8);
            Programa programa9 = new Programa("Proceso 10",instrucciones6,nuevoUsuarioDos,"X1", memoria,0,"\u001b[45m",9);
                                    
            colaDeEspera.enqueue(programa0);
            colaDeEspera.enqueue(programa1);
            colaDeEspera.enqueue(programa2);
            colaDeEspera.enqueue(programa3);
            colaDeEspera.enqueue(programa4);
            colaDeEspera.enqueue(programa5);
            colaDeEspera.enqueue(programa6);
            colaDeEspera.enqueue(programa7);
            colaDeEspera.enqueue(programa8);
            colaDeEspera.enqueue(programa9);
                       
            memoria.setCola(colaDeEspera);
            
            return memoria;
    }
    
    public static Memoria creacionEscenario3(){
            Particion particion0 = new Particion(20, 0);
            Particion particion1 = new Particion(30, 1);
            Particion particion2 = new Particion(50, 2);
            
            Particion[] particiones = new Particion[]{particion0,particion1,particion2};
            Memoria memoria = new Memoria(particiones,10);
            
            String[] permisosUsuario = {"X1","I3"};
            Usuario nuevoUsuario = new Usuario("Arto",permisosUsuario);

            String[] permisosUsuarioDos = {"I3","X1"};
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
            Recurso recurso3 = new Recurso("Imagen",monitor3,"J1");
            
            Instruccion pedirRec0 = new Instruccion("P",4000,"Pedir recurso", recurso0);
            Instruccion usarRec0 = new Instruccion("U",5000,"Usar recurso", recurso0);
            Instruccion liberarRec0 = new Instruccion("L",1500,"Liberar recurso", recurso0);
            
            Instruccion pedirRec1 = new Instruccion("P",4000,"Pedir recurso", recurso1);
            Instruccion usarRec1 = new Instruccion("U",5000,"Usar recurso", recurso1);
            Instruccion liberarRec1 = new Instruccion("L",1500,"Liberar recurso", recurso1);
            
            Instruccion pedirRec2 = new Instruccion("P",4000,"Pedir recurso", recurso2);
            Instruccion usarRec2 = new Instruccion("U",5000,"Usar recurso", recurso2);
            Instruccion liberarRec2 = new Instruccion("L",1500,"Liberar recurso", recurso2);
            
            Instruccion pedirRec3 = new Instruccion("P",4000,"Pedir recurso", recurso3);
            Instruccion usarRec3 = new Instruccion("U",5000,"Usar recurso", recurso3);
            Instruccion liberarRec3 = new Instruccion("L",1500,"Liberar recurso", recurso3);           
            
            Instruccion[] instrucciones1 = {pedirRec0,pedirRec1, usarRec1,pedirRec1,usarRec0,liberarRec0,liberarRec1,pedirRec1};                                 
            Instruccion[] instrucciones2 = {pedirRec1,pedirRec0,usarRec1,liberarRec1};
            Instruccion[] instrucciones3 = {pedirRec1,pedirRec0,usarRec1,liberarRec1};
            Instruccion[] instrucciones4 = {pedirRec1,pedirRec0,usarRec1,liberarRec1};
            Instruccion[] instrucciones5 = {pedirRec2,pedirRec2,pedirRec3,usarRec3,usarRec2,liberarRec2, liberarRec3};
            Instruccion[] instrucciones6 = {pedirRec2,pedirRec0,usarRec1,liberarRec1,usarRec1,liberarRec1};
            
            ColaDeEspera colaDeEspera = memoria.getCola();
            
            Programa programa0 = new Programa("Proceso 1",instrucciones1,nuevoUsuario,"I3", memoria,0,"\u001b[31m",0);
            Programa programa1 = new Programa("Proceso 2",instrucciones2, nuevoUsuarioDos,"I3", memoria,0,"\u001b[32m",1);
            Programa programa2 = new Programa("Proceso 3",instrucciones3, nuevoUsuario,"I3", memoria,0,"\u001b[33m",2);
            Programa programa3 = new Programa("Proceso 4",instrucciones4, nuevoUsuarioTres,"I3", memoria,0,"\u001b[34m",3);
            Programa programa4 = new Programa("Proceso 5",instrucciones5,nuevoUsuarioDos,"X1", memoria,0,"\u001b[35m",4);
            Programa programa5 = new Programa("Proceso 6",instrucciones6,nuevoUsuarioDos,"H7", memoria,0,"\u001b[36m",5);
            Programa programa6 = new Programa("Proceso 7",instrucciones1,nuevoUsuarioDos,"X1", memoria,0,"\u001b[41m",6);
            Programa programa7 = new Programa("Proceso 8",instrucciones6,nuevoUsuarioDos,"F5", memoria,0,"\u001b[43m",7);
                                    
            colaDeEspera.enqueue(programa0);
            colaDeEspera.enqueue(programa1);
            colaDeEspera.enqueue(programa2);
            colaDeEspera.enqueue(programa3);
            colaDeEspera.enqueue(programa4);
            colaDeEspera.enqueue(programa5);
            colaDeEspera.enqueue(programa6);
            colaDeEspera.enqueue(programa7);
                       
            memoria.setCola(colaDeEspera);
            
            return memoria;
    }
}
