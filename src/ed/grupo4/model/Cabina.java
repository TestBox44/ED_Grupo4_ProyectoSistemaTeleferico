package ed.grupo4.model;

public class Cabina{
    private Pasajero[] pasajerosabordo;
    private int asientosocupados;
    private float pesoacumulado;
    private float pesomaximo;
    private int maxasientos;
    private boolean lleno;

    public Cabina(){
        lleno=false;
        pesomaximo=400f;
        pesoacumulado=0;
        maxasientos=10;
        asientosocupados=0;
        pasajerosabordo=new Pasajero[maxasientos];
    }

    public Cabina(float pesomaximo, int maxasientos){
        this.pesomaximo=pesomaximo;
        this.maxasientos=maxasientos;
        lleno=false;
        pesoacumulado=0;
        asientosocupados=0;
        pasajerosabordo=new Pasajero[maxasientos];
    }

    public void subirPasajero(Pasajero nuevoPasajero){
        if(asientosocupados<maxasientos&&pesoacumulado<=pesomaximo){
            pasajerosabordo[asientosocupados]=nuevoPasajero;
            asientosocupados++;
        }else{
            System.out.println("¡La cabina esta llena!");
        }
    }

    public float getPesoMaximo(){
        return pesomaximo;
    }

    public void setPesoAcumulado(float peso){
        pesoacumulado=peso;
    }

    public float getPesoAcumulado(){
        return pesoacumulado;
    }

    public boolean getLleno(){
        return lleno;
    }

    public void setLleno(boolean lleno){
        this.lleno=lleno;
    }

    public int getMaxAsientos(){
        return maxasientos;
    }

    public void vaciarCabina(){
        pasajerosabordo=new Pasajero[maxasientos];
        pesoacumulado=0;
        asientosocupados=0;
    }
}