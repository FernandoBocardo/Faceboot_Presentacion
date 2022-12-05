/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIs;

import Dominio.Usuario;
import Negocios.UsuarioBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import eventManagers.EventManagerNotificacionInicioSesion;
import utils.Conexion;
import utils.iEventListener;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class FrmIniciarSesion extends javax.swing.JFrame implements iEventListener{

    private Conexion conexion;
    private EventManagerNotificacionInicioSesion eventManagerNotificacionSesionIniciada;
    private boolean cerrar = true;
    
    /**
     * Creates new form registarUsuario
     */
    public FrmIniciarSesion(Conexion conexion) {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/images/Icono.png")).getImage());
        this.conexion = conexion;
        this.eventManagerNotificacionSesionIniciada = EventManagerNotificacionInicioSesion.getInstance(this.conexion, "notificarSesionIniciada", "notificarSesionNoIniciada");
        this.eventManagerNotificacionSesionIniciada.subscribe("notificarSesionIniciada", this);
        this.eventManagerNotificacionSesionIniciada.subscribe("notificarSesionNoIniciada", this);
        this.conexion.iniciarNotificador();
    }

    private FrmIniciarSesion() {
    }
    
    
    @Override
    public void update(String eventType, String contenido, String usuarioJson) {
        if(eventType.equals("notificarSesionIniciada"))
        {
            JOptionPane.showMessageDialog(this, "Se ha iniciado sesion correctamente", "Sesion iniciada", JOptionPane.INFORMATION_MESSAGE);
            this.eventManagerNotificacionSesionIniciada.unsubscribe("notificarSesionIniciada", this);
            this.eventManagerNotificacionSesionIniciada.unsubscribe("notificarSesionNoIniciada", this);
            new FrmMuro(conexion, usuarioJson).setVisible(true);
            cerrar = false;
            this.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No se ha podido iniciar sesión debido a que el usuario es incorrecto", "Usuario incorrecto", JOptionPane.WARNING_MESSAGE);
            btnIniciarSesion.setEnabled(true);
            btnRegistrarse.setEnabled(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtContrasena = new javax.swing.JPasswordField();
        btnRegistrarse = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnIniciarSesion = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        jButton3.setText("jButton3");

        jButton5.setText("jButton5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Iniciar Sesión");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogoCompleto.png"))); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 320, 160));

        jLabel8.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel8.setText("¿No tienes cuenta?");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 410, -1, -1));

        jLabel9.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N
        jLabel9.setText("Iniciar sesión");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        txtUsuario.setText("Ingrese el nombre de usuario");
        txtUsuario.setBorder(null);
        txtUsuario.setForeground(new java.awt.Color(153, 153, 153));
        txtUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtUsuarioMousePressed(evt);
            }
        });
        jPanel2.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 310, 30));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 310, 10));

        jLabel4.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel4.setText("CONTRASEÑA");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 310, 10));

        txtContrasena.setText("*******************");
        txtContrasena.setBorder(null);
        txtContrasena.setForeground(new java.awt.Color(153, 153, 153));
        txtContrasena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtContrasenaMousePressed(evt);
            }
        });
        jPanel2.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 310, 20));

        btnRegistrarse.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        btnRegistrarse.setText("REGISTRARSE");
        btnRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, 140, 40));

        jButton2.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logoFacebook.png"))); // NOI18N
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, 90, 40));

        jLabel10.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel10.setText("Iniciar sesión con;");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 190, -1));

        jButton6.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo-Gmail.png"))); // NOI18N
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 90, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inicioPortada.jpg"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 330, 500));

        jLabel6.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N
        jLabel6.setText("Iniciar sesión");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        jLabel11.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel11.setText("CORREO");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        btnIniciarSesion.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        btnIniciarSesion.setText("INICIAR SESIÓN");
        btnIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });
        jPanel2.add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 140, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioMousePressed
        // TODO add your handling code here:
        if (txtUsuario.getText().equals("Ingrese el nombre de usuario")) {
            txtUsuario.setText("");
            txtUsuario.setForeground(Color.black);
        }
        if (String.valueOf(txtContrasena.getPassword()).isEmpty()) {
            txtContrasena.setText("*******************");
            txtContrasena.setForeground(Color.gray);
        }
    }//GEN-LAST:event_txtUsuarioMousePressed

    private void txtContrasenaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtContrasenaMousePressed
        // TODO add your handling code here:
        if (String.valueOf(txtContrasena.getPassword()).equals("*******************")) {
            txtContrasena.setText("");
            txtContrasena.setForeground(Color.black);
        }
        if (txtUsuario.getText().isEmpty()) {
            txtUsuario.setText("Ingrese el nombre de usuario");
            txtUsuario.setForeground(Color.gray);
        }
    }//GEN-LAST:event_txtContrasenaMousePressed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        btnIniciarSesion.setEnabled(false);
        btnRegistrarse.setEnabled(false);
        UsuarioBuilder usuarioBuilder = new UsuarioBuilder();
        usuarioBuilder.buildCorreo(txtUsuario.getText());
        usuarioBuilder.buildContrasena(new String(txtContrasena.getPassword()));
        conexion.enviarEventoUsuario("iniciarSesion", usuarioBuilder.getResultado());
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed
        this.eventManagerNotificacionSesionIniciada.unsubscribe("notificarSesionIniciada", this);
        this.eventManagerNotificacionSesionIniciada.unsubscribe("notificarSesionNoIniciada", this);
        new FrmRegistarUsuario(conexion).setVisible(true);
        cerrar = false;
        this.dispose();
    }//GEN-LAST:event_btnRegistrarseActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if(cerrar)
        {
            this.conexion.enviarEventoPublicaciones("desconectarse", "desconectarse", "desconectarse");
            conexion.desconectarSockets();
            conexion.detenerNotificador();
        }
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(FrmIniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmIniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmIniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmIniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmIniciarSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnRegistrarse;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
