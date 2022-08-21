package ed.grupo4.GUI;

import java.awt.Component;
import javax.swing.JPanel;

public class PanelSlider extends JPanel{
    
    private boolean animando=false;
    private JPanel panelActual;
    
    
    public PanelSlider(){}
    
    public void setPanelActual(JPanel panelActual){
        this.panelActual=panelActual;
    }
    
    public void setPrincipal(JPanel panel){
        Component[] componentes = getComponents();
        for(int i=0;i<componentes.length;i++)
            componentes[i].setVisible(false);
        panel.setVisible(true);
        panel.setLocation(0, 0);
    }
    public void moverPanelDerecha(JPanel panel,int posInicio, int posFin, double velocidad,long delay){
        if(!animando&&posInicio<posFin){
            animando=true;
            new Thread(new Runnable() {
                    @Override
                    public void run() {
                        double rad=0;
                        int desp=posFin-posInicio;
                        while(rad<Math.PI/2){
                            rad+=velocidad;
                            rad=rad>Math.PI/2?Math.PI/2:rad;
                            panel.setLocation(posInicio+(int)(desp*Math.sin(rad)),panel.getY());
                            repaint();
                            try {
                                Thread.sleep(delay);
                            } catch (InterruptedException ex) {
                            }
                        }
                        animando=false;
                    }
                }).start();
        }else{
            System.out.println("No en posicion de animar");
        }
    }
    public void moverPanelIzquierda(JPanel panel,int posInicio, int posFin, double velocidad,long delay){
        if(!animando&&posInicio>posFin){
            animando=true;
            new Thread(new Runnable() {
                    @Override
                    public void run() {
                    double rad=Math.PI/2;
                    int desp=posInicio-posFin;
                    while(rad>0){
                        rad-=velocidad;
                        rad=rad<0?0:rad;
                        panel.setLocation(posInicio-(int)(desp*(Math.cos(rad))),panel.getY());
                        repaint();
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException ex) {
                        }
                    }
                    animando=false;
                }
                }).start();
        }else{
            System.out.println("No en posicion de animar");
        }
    }
}
