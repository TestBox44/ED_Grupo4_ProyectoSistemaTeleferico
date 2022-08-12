package ed.grupo4.util;

import ed.grupo4.model.*;
import java.util.Scanner;
import java.lang.Thread;  

class Estacion{
    private Pasajero [][] ColaPasajeros;
    private int aforo=100;   //Describe la capacidad maxima de la estacion
    private int pasajerosenestacion; //Describe la cantidad de personas actualmente en la estacion
    private int [] tamanio;  //Describe el tamaño de cada cola
    private Cabina[] cabinas; //Almacena todas las cabinas disponibles en la estacion
    private long [] tiempoactual;
    
    public Estacion(){
        ColaPasajeros=new Pasajero[0][0];
        cabinas=new Cabina[0];
        tamanio=new int[0];
        tiempoactual=new long[0];
    }
    //Metodos para la Gestion de cabinas
    public void agregarCabina(int pesomaximo, int numerodeasientos){
        Cabina[] nuevalistaCabina = new Cabina[cabinas.length+1];
        long[] nuevotiempoactual=new long[cabinas.length+1];
        for (int i = 0; i < cabinas.length; i++) {
            nuevalistaCabina[i]=cabinas[i];
            nuevotiempoactual[i]=tiempoactual[i];
        }
        nuevalistaCabina[cabinas.length]=new Cabina(pesomaximo,numerodeasientos);
        nuevotiempoactual[cabinas.length]=-1;
        cabinas=nuevalistaCabina;
        tiempoactual=nuevotiempoactual;
        agregarColadePasajeros();
    }

    public void modificarCabina(){
        int index,nuevoNumAsientos;
        float nuevoPeso;
        Scanner entrada=new Scanner(System.in);
        System.out.print("\t-> Ingrese la cabina a modificar: "); //pide el indice de la cabina
        index= entrada.nextInt()-1;
        System.out.print("\t-> Ingrese el nuevo peso que puede soportar la cabina: ");
        nuevoPeso = entrada.nextFloat();
        System.out.print("\t-> Ingrese la nueva cantidad de asientos de la cabina: ");
        nuevoNumAsientos = entrada.nextInt();
        cabinas[index]= new Cabina(nuevoPeso, nuevoNumAsientos);
    }
    public void eliminarCabina(){
        int index;
        int op;
         Scanner teclado=new Scanner(System.in);
        if (cabinas.length>0){
            System.out.print("\n\n\tIngrese el numero de cabina: ");
            index=teclado.nextInt()-1;
            if(index>=0&&index<cabinas.length&&cabinas[index].getPesoAcumulado()==0){
                System.out.println("\n\n\t****************************************");
                System.out.println("\t\t  CONFIRMACION DE DATOS");
                System.out.println("\t****************************************");
                Cabina nuevacabina=cabinas[index];
                System.out.println("\tCabina: 0"+(index+1));
                System.out.println("\tPeso Maximo: "+nuevacabina.getPesoMaximo());
                System.out.println("\tAsientos Maximos: "+nuevacabina.getMaxAsientos());
                do {
                    System.out.print("\t¿Confirmar operacion?[y=1/n=0]:");
                    op=teclado.nextInt();
                } while (op<0||op>1);
                Cabina[] nuevaListaCabinas = new Cabina[cabinas.length-1];
                int j=nuevaListaCabinas.length-1;
                for (int i = cabinas.length-1; i >= 0; i--) {
                    if(i!=index){
                        nuevaListaCabinas[j]=cabinas[i];
                        j--;
                    }
                }
                System.out.println("\nCabina eliminada exitosamente!");
                cabinas=nuevaListaCabinas;
                eliminarColadePasajeros(index);
            }else{
                System.out.println("\tNo es posible eliminar esta cabina...");
            }
        }else{
            System.out.println("\tLa lista de cabinas esta vacía");
        }
        try{ Thread.sleep(1250);
        }catch(Exception e){} 
    }

    //Metodos para la Gestion de colas de Pasajeros

    public void agregarColadePasajeros(){
        Pasajero[][] nuevaListaDeCola = new Pasajero[tamanio.length+1][];
        int[] nuevotamanio=new int[tamanio.length+1];
        for (int i = 0; i < tamanio.length; i++) {
            nuevaListaDeCola[i]=ColaPasajeros[i];
            nuevotamanio[i]=tamanio[i];
        }
        nuevaListaDeCola[tamanio.length]=new Pasajero[0];
        nuevotamanio[tamanio.length]=0;
        ColaPasajeros=nuevaListaDeCola;
        tamanio=nuevotamanio;
    }

    public void eliminarColadePasajeros(int numerodecola){
        Pasajero[][] nuevaListaDeCola = new Pasajero[tamanio.length-1][];
        int[] nuevotamanio=new int[tamanio.length-1];
        int j=0;
        for (int i = 0; i < tamanio.length; i++) {
            if( i!= numerodecola){
                nuevaListaDeCola[j]=ColaPasajeros[i];
                nuevotamanio[j]=tamanio[i];
                j++;
            }
        }
        ColaPasajeros=nuevaListaDeCola;
        pasajerosenestacion-=tamanio[numerodecola];
        tamanio=nuevotamanio;
    }
    
    public void agregarPasajero(){
        int op=0;
        int numerodecola;
        do {
            if(pasajerosenestacion<100){
                if(tamanio.length>0){
                    System.out.println("\n\n\t***************************************");
                    System.out.println("\t\t   INGRESO DE DATOS");
                    System.out.println("\t***************************************");
                    Pasajero nuevopasajero=new Pasajero("",0,0);
                    Scanner entrada=new Scanner(System.in);
                    //Le pide el nombre del pasajero
                    System.out.print("\t-> Escribe el nombre del pasajero: ");
                    nuevopasajero.setNombre(entrada.nextLine());
                    //Le pide edad del pasajero
                    System.out.print("\t-> Escribe edad del pasajero: ");
                    nuevopasajero.setEdad(entrada.nextInt());
                    //Le pide el peso del pasajero
                    System.out.print("\t-> Escribe el peso del pasajero: ");
                    nuevopasajero.setPeso(entrada.nextFloat());
                    do {
                        System.out.print("\t-> Escribe la cabina a utilizar: ");
                        numerodecola=entrada.nextInt()-1;
                        if(tamanio.length<=numerodecola||numerodecola<0)
                            System.out.println("\n\n\tLa cabina ingresada no existe");
                        if(cabinas[numerodecola].getLleno())
                            System.out.printf("\n\n\tLa cola %n esta llena!\n",numerodecola);
                    } while (cabinas[numerodecola].getLleno()||tamanio.length<=numerodecola||numerodecola<0);
                    System.out.println("\n\n\t****************************************");
                    System.out.println("\t\t  CONFIRMACION DE DATOS");
                    System.out.println("\t****************************************");
                    System.out.println("\tNombre: "+nuevopasajero.getNombre());
                    System.out.println("\tNumero de cola: "+numerodecola);
                    if (nuevopasajero.getEdad()>18){
                        System.out.println("\tTipo: Mayor de edad");
                        System.out.println("\tTarifa: S/.20");
                    }
                    else{
                        System.out.println("\tTipo: Menor de edad");
                        System.out.println("\tTarifa: S/.15");
                    }
                    do {
                        System.out.print("\t¿Confirmar operacion?[y=1/n=0]:");
                        op=entrada.nextInt();
                    } while (op<0||op>1);
                    if(op==1){
                        if (tamanio[numerodecola]>0)
                            nuevopasajero.setTicket(ColaPasajeros[numerodecola][tamanio[numerodecola]-1].getTicket()+1);
                        else 
                            nuevopasajero.setTicket(1);
                        encolarPasajero(numerodecola,nuevopasajero);
                        System.out.println("\t¡Pasajero agregado a la cola!");
                    }else{
                        System.out.println("\t¡Operacion cancelada!");
                    }
                    try{ Thread.sleep(1250);
                    }catch(Exception e){}
                    do {
                        System.out.print("\t¿Desea continuar agregando pasajeros?[y=1/n=0]:");
                        op=entrada.nextInt();
                    } while (op<0||op>1);
                }else{
                    System.out.println("\t¡No hay colas disponibles!");
                }
            }
            else {
                System.out.println("\t¡No hay mas espacio para agregar pasajeros!");
                try{ Thread.sleep(1250);
                }catch(Exception e){}
                op=0;
            }
        } while (op==1);
    }

    public void encolarPasajero(int numerodecola, Pasajero nuevoPasajero){
        Pasajero[] nuevaColaPasajeros = new Pasajero[tamanio[numerodecola]+1];
        for (int i = 0; i < tamanio[numerodecola]; i++) {
            nuevaColaPasajeros[i]=ColaPasajeros[numerodecola][i];
        }
        nuevaColaPasajeros[tamanio[numerodecola]]=nuevoPasajero;
        ColaPasajeros[numerodecola]=nuevaColaPasajeros;
        pasajerosenestacion++;
        tamanio[numerodecola]++;
    }

    public Pasajero desencolarPasajero(int numerodecola){
        Pasajero[] nuevaColaPasajeros = new Pasajero[tamanio[numerodecola]-1];
        for (int i = 0; i < tamanio[numerodecola]-1; i++) {
            nuevaColaPasajeros[i]=ColaPasajeros[numerodecola][i+1];
        }
        Pasajero pasajerodesencolado=ColaPasajeros[numerodecola][0];
        ColaPasajeros[numerodecola]=nuevaColaPasajeros;
        tamanio[numerodecola]--;
        pasajerosenestacion--;
        return pasajerodesencolado;
    }

    public void eliminarPasajero(){
        int index;
        int numerodecola;
        int op;
        Scanner teclado=new Scanner(System.in);
        System.out.print("\n\n\tIngrese la cola del pasajero: ");
        numerodecola=teclado.nextInt()-1;
        if (tamanio[numerodecola]>0){
            System.out.print("\n\n\tIngrese la posicion del pasajero: ");
            index=teclado.nextInt()-1;
            if(index>=0&&index<tamanio[numerodecola]){
                System.out.println("\n\n\t****************************************");
                System.out.println("\t\t  CONFIRMACION DE DATOS");
                System.out.println("\t****************************************");
                Pasajero nuevopasajero=ColaPasajeros[numerodecola][index];
                System.out.println("\tNumero de cola: "+numerodecola);
                System.out.println("\tNombre: "+nuevopasajero.getNombre());
                if (nuevopasajero.getEdad()>18){
                    System.out.println("\tTipo: Mayor de edad");
                    System.out.println("\tTarifa: S/.20");
                }
                else{
                    System.out.println("\tTipo: Menor de edad");
                    System.out.println("\tTarifa: S/.15");
                }
                do {
                    System.out.print("\t¿Confirmar operacion?[y=1/n=0]:");
                    op=teclado.nextInt();
                } while (op<0||op>1);
                Pasajero[] nuevacolapasajeros = new Pasajero[tamanio[numerodecola]-1];
                int j=nuevacolapasajeros.length-1;
                for (int i = tamanio[numerodecola]-1; i >= 0; i--) {
                    if(i!=index){
                        nuevacolapasajeros[j]=ColaPasajeros[numerodecola][i];
                        j--;
                    }
                }
                pasajerosenestacion--;
                System.out.println("\t¡Pasajero eliminado exitosamente!");
                ColaPasajeros[numerodecola]=nuevacolapasajeros;
                tamanio[numerodecola]--;
            }else{
                System.out.println("\tEl indice de pasajero esta fuera de los limites...");
            }
        }else{
            System.out.println("\tLa cola de pasajeros esta vacía");
        }
        try{ Thread.sleep(1250);
        }catch(Exception e){}
    }

    public boolean estaColaVacia(int numerodecola){
        return ColaPasajeros[numerodecola].length==0;
    }

    public void vaciarColaPasajeros(int numerodecola){
        ColaPasajeros[numerodecola]=new Pasajero[0];
    }

    public int tamanioColaPasajeros(int numerodecola){
        return tamanio[numerodecola];
    }

    public int numerodecolas(){
        return tamanio.length;
    }

    public void mostrarColaPasajeros(int nroCola){
        if(!estaColaVacia(nroCola)){
            System.out.println("Cola de la Cabina "+(nroCola+1));
            System.out.println(String.format("\n\n\t%10s\t%6s\t%20s\t%6s","ORDEN","TICKET","NOMBRE","TARIFA"));
            System.out.println("\t-----------------------------------------------------------------");
            for (int i = 0; i < tamanio[nroCola]; i++) {
                int tarifa;
                Pasajero pasajeroactual=ColaPasajeros[nroCola][i];
                if (pasajeroactual.getEdad()>18) {
                    tarifa=20;
                }
                else {
                    tarifa = 15;
                }
                System.out.println(String.format("\t%10d\t%06d\t%20s\t%6s",i+1,pasajeroactual.getTicket(),pasajeroactual.getNombre(),new String("S/."+tarifa)));
            }
            System.out.println("\n\n");
        }else{
            System.out.println("\t¡Lo cola no tiene ningun pasajero!");
        }
        try{ Thread.sleep(1250);
        }catch(Exception e){}
    }

    public void abordarPasajeros(int numerodecola){
        if(tiempoactual[numerodecola]==-1||System.currentTimeMillis()-tiempoactual[numerodecola]>=30000){
            if(tamanio[numerodecola]>0){
                if(!cabinas[numerodecola].getLleno()){
                tiempoactual[numerodecola]=-1;
                int cantpasajeros=0;
                int aux = tamanio[numerodecola];
                //Cabecera
                System.out.println("\n\n\t****************************************");
                System.out.println("\t\t   LISTA DE ABORDAJE");
                System.out.println("\t****************************************");
                while(cantpasajeros<cabinas[numerodecola].getMaxAsientos()&&cantpasajeros<aux){
                    if(cabinas[numerodecola].getPesoMaximo() > cabinas[numerodecola].getPesoAcumulado() + ColaPasajeros[numerodecola][0].getPeso()){
                        cabinas[numerodecola].setPesoAcumulado(cabinas[numerodecola].getPesoAcumulado()+ColaPasajeros[numerodecola][0].getPeso());
                        Pasajero pasajeroactual = desencolarPasajero(numerodecola);
                        System.out.println(String.format("\tNro %06d\t%20s",pasajeroactual.getTicket(),pasajeroactual.getNombre()));
                        cabinas[numerodecola].subirPasajero(pasajeroactual);
                        pasajerosenestacion--;
                        try{ Thread.sleep(2000);
                        }catch(Exception e){}
                    }else{
                        cabinas[numerodecola].setLleno(true);
                    }
                    cantpasajeros++; 
                }
                }else{
                System.out.println("¡La cabina del teleférico ya alcanzó su tope!");
                }
            }else{
                System.out.println("¡La cola de pasajeros esta vacia!");
            }
        }else{
            float tiempo=(float)(tiempoactual[numerodecola]-System.currentTimeMillis()+30000)/1000;
            System.out.println("\t\tLa cabina esta uso");
            System.out.printf("\t\tTiempo restante: %3.2f segundos",tiempo);
        }
        
    }

    public void iniciarPaseo(int numerodecola){
        cabinas[numerodecola].vaciarCabina();
        System.out.println("\tIniciando paseo...");
        try{ Thread.sleep(2000);
        }catch(Exception e){}
        tiempoactual[numerodecola]=System.currentTimeMillis();
        cabinas[numerodecola].setLleno(false);
    }
    
    

}