package ed.grupo4.model;

public class Cabina{
    private Pasajero[] pasajerosabordo;
    private String identificador;
    private String destino;
    private int asientosocupados;
    private float pesoacumulado;
    private float pesomaximo;
    private int maxasientos;
    private boolean lleno;
    private boolean viajando;

    public Cabina(){
        lleno=false;
        pesomaximo=400f;
        pesoacumulado=0;
        maxasientos=10;
        asientosocupados=0;
        identificador="";
        destino="";
        viajando=false;
        pasajerosabordo=new Pasajero[maxasientos];
    }

    public Cabina(String identificador, float pesomaximo, int maxasientos,String destino){
        this.pesomaximo=pesomaximo;
        this.maxasientos=maxasientos;
        this.identificador=identificador;
        this.destino=destino;
        lleno=false;
        pesoacumulado=0;
        asientosocupados=0;
        viajando=false;
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
    
    public String getIdentificador(){
        return identificador;
    }

    public void setIdentificador(String identificador){
        this.identificador=identificador;
    }
    
    public String getDestino(){
        return destino;
    }

    public void setDestino(String destino){
        this.destino=destino;
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
    
    public boolean getViajando(){
        return viajando;
    }
    
    public void setViajando(boolean viajando){
        this.viajando=viajando;
    }

    public void vaciarCabina(){
        pasajerosabordo=new Pasajero[maxasientos];
        pesoacumulado=0;
        asientosocupados=0;
    }
}