/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIs;

import Dominio.Publicacion;
import Dominio.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import eventManagers.EventManagerNotificacionConsultarPublicaciones;
import eventManagers.EventManagerNotificacionPublicacionRegistrado;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import utils.Conexion;
import utils.iEventListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import utils.Controlador;

/**
 *
 * @author Carlos
 */
public class FrmMuro extends javax.swing.JFrame implements iEventListener{

    private Conexion conexion;
    private String usuario;
    private EventManagerNotificacionPublicacionRegistrado eventManagerNotificacionPublicacionRegistrado;
    private EventManagerNotificacionConsultarPublicaciones eventManagerNotificacionConsultarPublicaciones;
    private boolean cerrar = true;
    
    /**
     * Creates new form FrmMuro
     */
    public FrmMuro(Conexion conexion, String usuario) {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/images/Icono.png")).getImage());
        this.conexion = conexion;
        this.usuario = usuario;
        this.eventManagerNotificacionPublicacionRegistrado = EventManagerNotificacionPublicacionRegistrado.getInstance(conexion, "notificarRegistroPublicacion");
        this.eventManagerNotificacionConsultarPublicaciones = EventManagerNotificacionConsultarPublicaciones.getInstance(conexion, "notificarConsultaPublicaciones");
        this.eventManagerNotificacionPublicacionRegistrado.subscribe("notificarRegistroPublicacion", this);
        this.eventManagerNotificacionConsultarPublicaciones.subscribe("notificarConsultaPublicaciones", this);
        this.conexion.enviarEventoPublicaciones("consultarPublicaciones", this.usuario, this.usuario);
    }
    

    
    private FrmMuro()
    {
    }
    
    @Override
    public void update(String eventType, String contenido, String usuarioJson) {
        Controlador.getInstance().mostrarMuro(contenido, panelMuro, conexion, usuario);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtEtiquetas = new javax.swing.JTextField();
        btnBuscarEtiqueta = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnEditarPerfil = new javax.swing.JButton();
        btnNotificaciones = new javax.swing.JButton();
        btnPublicarAlgo = new javax.swing.JButton();
        btnCancelarFiltro = new javax.swing.JButton();
        panelMuro = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Muro Principal");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogoMuro.png"))); // NOI18N

        btnBuscarEtiqueta.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        btnBuscarEtiqueta.setText("Buscar");
        btnBuscarEtiqueta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarEtiqueta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEtiquetaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel2.setText("Busqueda por etiquetas");

        btnEditarPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconoPerfil.jpg"))); // NOI18N
        btnEditarPerfil.setBorder(null);
        btnEditarPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPerfilActionPerformed(evt);
            }
        });

        btnNotificaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconoNotificaciones.jpg"))); // NOI18N
        btnNotificaciones.setBorder(null);
        btnNotificaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNotificaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotificacionesActionPerformed(evt);
            }
        });

        btnPublicarAlgo.setText("Publicar Algo");
        btnPublicarAlgo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublicarAlgoActionPerformed(evt);
            }
        });

        btnCancelarFiltro.setText("Cancelar");
        btnCancelarFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarFiltroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEtiquetas, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPublicarAlgo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBuscarEtiqueta)
                            .addComponent(btnCancelarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(43, 43, 43)
                .addComponent(btnEditarPerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNotificaciones)
                .addGap(150, 150, 150))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnEditarPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNotificaciones))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEtiquetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarEtiqueta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPublicarAlgo)
                            .addComponent(btnCancelarFiltro))))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 100));

        panelMuro.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        panelMuro.setLayout(new java.awt.BorderLayout());
        jPanel1.add(panelMuro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 880, 310));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 879, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNotificacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotificacionesActionPerformed
        // TODO add your handling code here:
        new FrmNotificaciones(conexion, usuario).setVisible(true);
        cerrar = false;
        this.dispose();
    }//GEN-LAST:event_btnNotificacionesActionPerformed

    private void btnEditarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPerfilActionPerformed
        // TODO add your handling code here:
        new FrmEditarUsuario(conexion, usuario).setVisible(true);
        cerrar = false;
        this.dispose();
    }//GEN-LAST:event_btnEditarPerfilActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        if(cerrar)
        {
            this.conexion.enviarEventoPublicaciones("desconectarse", "desconectarse", "desconectarse");
            conexion.desconectarSockets();
            conexion.detenerNotificador();
        }
    }//GEN-LAST:event_formWindowClosed

    private void btnPublicarAlgoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublicarAlgoActionPerformed
        this.eventManagerNotificacionPublicacionRegistrado.unsubscribe("notificarRegistroPublicacion", this);
        this.eventManagerNotificacionConsultarPublicaciones.unsubscribe("notificarConsultaPublicaciones", this);
        new FrmRegistrarPublicacion(conexion, usuario).setVisible(true);
        cerrar = false;
        this.dispose();
    }//GEN-LAST:event_btnPublicarAlgoActionPerformed

    private void btnBuscarEtiquetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEtiquetaActionPerformed
        // TODO add your handling code here:
        if(!txtEtiquetas.getText().equals(""))
        {
            this.conexion.enviarEventoPublicaciones("consultarPublicacionesPorEtiqueta", txtEtiquetas.getText(), this.usuario);
        }
    }//GEN-LAST:event_btnBuscarEtiquetaActionPerformed

    private void btnCancelarFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarFiltroActionPerformed
        this.conexion.enviarEventoPublicaciones("consultarPublicaciones", this.usuario, this.usuario);
    }//GEN-LAST:event_btnCancelarFiltroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMuro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMuro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMuro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMuro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMuro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarEtiqueta;
    private javax.swing.JButton btnCancelarFiltro;
    private javax.swing.JButton btnEditarPerfil;
    private javax.swing.JButton btnNotificaciones;
    private javax.swing.JButton btnPublicarAlgo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelMuro;
    private javax.swing.JTextField txtEtiquetas;
    // End of variables declaration//GEN-END:variables


}
