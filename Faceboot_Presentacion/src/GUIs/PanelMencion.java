/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUIs;

import Dominio.Publicacion;
import Dominio.Usuario;
import Dominio.UsuarioEtiquetado;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import utils.Conexion;

/**
 *
 * @author Fernando
 */
public class PanelMencion extends javax.swing.JPanel {

    private Publicacion publicacionConMencion;
    private String usuarioJson;
    private Conexion conexion;
    
    /**
     * Creates new form PanelMenciones
     */
    public PanelMencion(Publicacion publicacionConMencion, String usuarioJson, Conexion conexion) {
        initComponents();
        this.publicacionConMencion = publicacionConMencion;
        this.usuarioJson = usuarioJson;
        this.conexion = conexion;
        Usuario usuario = null;
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            usuario = objectMapper.readValue(usuarioJson, Usuario.class);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        this.lblTextoMencion.setText(publicacionConMencion.getUsuario().getNombre()+" lo ha mencionado en una publicación");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTextoMencion = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        lblTextoMencion.setText("textoMencion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTextoMencion)
                .addContainerGap(311, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lblTextoMencion)
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTextoMencion;
    // End of variables declaration//GEN-END:variables
}
