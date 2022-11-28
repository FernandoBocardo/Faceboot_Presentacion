/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package faceboot_presentacion;

/**
 *
 * @author Hiram
 */
public class Faceboot_Presentacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //new FrmRegistarUsuario().setVisible(true);

        Conexion conexion1 = new Conexion();
        Conexion conexion2 = new Conexion();

        new FrmRegistrarPublicacion(conexion1).setVisible(true);
        new FrmRegistrarPublicacion(conexion2).setVisible(true);

    }
    
}
