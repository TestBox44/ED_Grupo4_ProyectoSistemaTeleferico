package ed.grupo4.model;

public class Pasajero{
    private String nombre;
    private float peso;
    private int edad;
    private String ticket;

    public Pasajero(String nombre, float peso, int edad){
        this.nombre=nombre;
        this.peso=peso;
        this.edad=edad;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    
    public String getNombre(){
        return nombre;
    }  
      
    public void setPeso(float peso){
        this.peso=peso;
    }

    public float getPeso(){
        return peso;
    }

    public void setEdad(int edad){
        this.edad=edad;
    }
    
    public int getEdad(){
        return edad;       
    }

    public void setTicket(String ticket){
        this.ticket=ticket;
    }
    
    public String getTicket(){
        return ticket;       
    }

    @Override
    public String toString(){
        return "Pasajero:  nombre:"+nombre+", edad: "+edad+", peso: "+peso+", ticket: "+ticket;
    }
}