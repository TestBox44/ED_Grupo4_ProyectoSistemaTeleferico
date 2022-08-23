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
    
    public JPanel getPanelActual(){
        return panelActual;
    }
    
    public void setAnimando(boolean animando){
        this.animando=animando;
    }
    
    public boolean getAnimando(){
        return animando;
    }
    
    public void setPrincipal(JPanel panel){
        Component[] componentes = getComponents();
        for(int i=0;i<componentes.length;i++)
            componentes[i].setVisible(false);
        panel.setVisible(true);
        panel.setLocation(0, 0);
    }
}
