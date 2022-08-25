package ed.grupo4.util;

import ed.grupo4.model.*;
import java.util.Scanner;
import java.lang.Thread;  

public class Estacion{
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
    public void agregarCabina(String indentificador, float pesomaximo, int numerodeasientos,String destino){
        Cabina[] nuevalistaCabina = new Cabina[cabinas.length+1];
        long[] nuevotiempoactual=new long[cabinas.length+1];
        for (int i = 0; i < cabinas.length; i++) {
            nuevalistaCabina[i]=cabinas[i];
            nuevotiempoactual[i]=tiempoactual[i];
        }
        nuevalistaCabina[cabinas.length]=new Cabina(indentificador,pesomaximo,numerodeasientos,destino);
        nuevotiempoactual[cabinas.length]=-1;
        cabinas=nuevalistaCabina;
        tiempoactual=nuevotiempoactual;
        agregarColadePasajeros();
    }

    public void modificarCabina(int index, String indentificador, int numeroAsientos, float nuevoPeso,String destino){
        cabinas[index]= new Cabina(indentificador,nuevoPeso, numeroAsientos,destino);
    }
    public void eliminarCabina(int index){
        Cabina[] nuevaListaCabinas = new Cabina[cabinas.length-1];
        int j=nuevaListaCabinas.length-1;
        for (int i = cabinas.length-1; i >= 0; i--) {
            if(i!=index){
                nuevaListaCabinas[j]=cabinas[i];
                j--;
            }
        }
        cabinas=nuevaListaCabinas;
        eliminarColadePasajeros(index); 
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
    
    public void agregarPasajero(int numeroDeCola, String nombre, int edad, float peso, String ticket){
        if(pasajerosenestacion<aforo){
            if(tamanio.length>0){
                Pasajero nuevopasajero=new Pasajero("",0,0);
                nuevopasajero.setNombre(nombre);
                nuevopasajero.setEdad(edad);
                nuevopasajero.setPeso(peso);
                nuevopasajero.setTicket(ticket);
                encolarPasajero(numeroDeCola,nuevopasajero);
            }else{
                System.out.println("¡No hay colas disponibles!");
            }
        }
        else {
            System.out.println("¡No hay mas espacio para agregar pasajeros!");
        }
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

    public void eliminarPasajero(int numeroDeCola, int indicePasajero){
        if (tamanio[numeroDeCola]>0){
            if(indicePasajero>=0&&indicePasajero<tamanio[numeroDeCola]){
                Pasajero[] nuevacolapasajeros = new Pasajero[tamanio[numeroDeCola]-1];
                int j=nuevacolapasajeros.length-1;
                for (int i = tamanio[numeroDeCola]-1; i >= 0; i--) {
                    if(i!=indicePasajero){
                        nuevacolapasajeros[j]=ColaPasajeros[numeroDeCola][i];
                        j--;
                    }
                }
                pasajerosenestacion--;
                ColaPasajeros[numeroDeCola]=nuevacolapasajeros;
                tamanio[numeroDeCola]--;
            }else{
                System.out.println("El indice de pasajero esta fuera de los limites...");
            }
        }else{
            System.out.println("La cola de pasajeros esta vacía");
        }
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
    
    public Cabina getCabina(int indice){
        return cabinas[indice];
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
    }

    public void abordarPasajeros(int numerodecola){
        if(tiempoactual[numerodecola]==-1||System.currentTimeMillis()-tiempoactual[numerodecola]>=30000){
            if(tamanio[numerodecola]>0){
                if(!cabinas[numerodecola].getLleno()){
                tiempoactual[numerodecola]=-1;
                int cantpasajeros=0;
                int aux = tamanio[numerodecola];
                while(cantpasajeros<cabinas[numerodecola].getMaxAsientos()&&cantpasajeros<aux){
                    if(cabinas[numerodecola].getPesoMaximo() > cabinas[numerodecola].getPesoAcumulado() + ColaPasajeros[numerodecola][0].getPeso()){
                        cabinas[numerodecola].setPesoAcumulado(cabinas[numerodecola].getPesoAcumulado()+ColaPasajeros[numerodecola][0].getPeso());
                        Pasajero pasajeroactual = desencolarPasajero(numerodecola);
                        cabinas[numerodecola].subirPasajero(pasajeroactual);
                        pasajerosenestacion--;
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
            System.out.println("La cabina esta uso");
            System.out.printf("Tiempo restante: %3.2f segundos",tiempo);
        }
        
    }

    public void iniciarPaseo(int numerodecola){
        cabinas[numerodecola].vaciarCabina();
        System.out.println("\tIniciando paseo...");
        tiempoactual[numerodecola]=System.currentTimeMillis();
        cabinas[numerodecola].setLleno(false);
    }
    
    

}