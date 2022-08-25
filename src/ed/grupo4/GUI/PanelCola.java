/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ed.grupo4.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class PanelCola extends javax.swing.JPanel {

    private String direcciondeimagen;
    private Image imagen;
    
    private DefaultTableModel modeloDeTabla;
    
    public PanelCola() {
        initComponents();
        this.direcciondeimagen="/ed/grupo4/resources/images/Interfaz05/Panel05.1.png";
        imagen=new ImageIcon(PanelImagen.class.getResource(direcciondeimagen)).getImage();
        
        modeloDeTabla=(DefaultTableModel)tablaDeCola.getModel();
        ((DefaultTableCellRenderer)tablaDeCola.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
        
        tablaDeCola.getTableHeader().setDefaultRenderer(new RenderDeCabecera(tablaDeCola.getTableHeader().getDefaultRenderer()));
        
        tablaDeCola.getTableHeader().setBackground(Color.WHITE);
        tablaDeCola.getTableHeader().setReorderingAllowed(false);
        tablaDeCola.getTableHeader().setFont(new Font("Arial",Font.BOLD,12));
        tablaDeCola.getTableHeader().setForeground(new Color(0,51,102,255));
     
        
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jScrollPane1.setOpaque(false);
    }
    
    public DefaultTableModel getModeloDeTabla(){
        return modeloDeTabla;
    }
    
    public void setTituloDeCola(String titulo){
        lblTituloDeCola.setText(titulo);
    }
    
    @Override
    public void paint(Graphics g){
        g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDeCola = new javax.swing.JTable();
        lblTituloDeCola = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(376, 301));

        tablaDeCola.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tablaDeCola.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre de Pasajero", "Ticket"
            }
        ));
        tablaDeCola.setFocusable(false);
        tablaDeCola.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tablaDeCola.setRowHeight(32);
        tablaDeCola.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tablaDeCola);
        if (tablaDeCola.getColumnModel().getColumnCount() > 0) {
            tablaDeCola.getColumnModel().getColumn(0).setPreferredWidth(150);
        }

        lblTituloDeCola.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        lblTituloDeCola.setForeground(new java.awt.Color(0, 51, 102));
        lblTituloDeCola.setText("Cola de Cabina 1");

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 102));
        jButton2.setText("Eliminar");
        jButton2.setBorderPainted(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.setRequestFocusEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblTituloDeCola)
                        .addGap(127, 127, 127))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(lblTituloDeCola)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTituloDeCola;
    private javax.swing.JTable tablaDeCola;
    // End of variables declaration//GEN-END:variables
}