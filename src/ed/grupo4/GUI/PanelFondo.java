package ed.grupo4.GUI;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import jdk.jfr.RecordingState;

public class PanelFondo extends PanelImagen{
    private int logoX;
        private int logoY;
        private int logoXCentro;
        private int logoXEsquinaIzq;
        private double rad;
        private double velocidad;
        private long tiempoDeRetraso;
        private Thread animacionMoverACentro;
        private Thread animacionMoverAEsquinaIzq;
        private Image logo;
        
        public PanelFondo(String direcciondeimagen) {
            super(direcciondeimagen);
            logo = new ImageIcon(getClass().getResource("/ed/grupo4/resources/images/icono_casa.png")).getImage();
            logoXCentro=(1280-logo.getWidth(null))/2;
            logoXEsquinaIzq=0;
            logoX=logoXCentro;
            logoY=0;
            rad=Math.PI/2;
            velocidad=0.06;
            tiempoDeRetraso=1000*1/60;
        }
        
        @Override
        public void paint(Graphics g){
            super.paint(g);
            g.drawImage(logo, logoX, logoY, null);
        }
        public void moverLogoAlCentro(){
            animacionMoverACentro=new Thread(new Runnable() {
                @Override
                public void run() {
                    int desp=logoXCentro-logoXEsquinaIzq;
                    while(rad<Math.PI/2){
                        rad+=velocidad;
                        rad=rad>Math.PI/2?Math.PI/2:rad;
                        logoX=logoXEsquinaIzq+(int)(desp*Math.sin(rad));
                        try {
                            Thread.sleep(tiempoDeRetraso);
                            repaint();
                        } catch (InterruptedException ex) {
                        }
                    }
                }
            },"Animacion mover a centro");
            animacionMoverACentro.start();
        }
        public void moverLogoAEsquinaIzquierda(){
            animacionMoverAEsquinaIzq=new Thread(new Runnable() {
                @Override
                public void run() {
                    int desp=logoXCentro-logoXEsquinaIzq;
                    while(rad>0){
                        rad-=velocidad;
                        rad=rad<0?0:rad;
                        logoX=logoXCentro-(int)(desp*(Math.cos(rad)));
                        try {
                            Thread.sleep(tiempoDeRetraso);
                            repaint();
                        } catch (InterruptedException ex) {
                        }
                    }
                }
            },"Animacion mover a esquina izquierda");
            animacionMoverAEsquinaIzq.start();
        }
}
