/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package utils;

import GUIs.FrmIniciarSesion;

/**
 *
 * @author Hiram
 */
public class Faceboot_Presentacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        new FrmIniciarSesion(conexion).setVisible(true);
    }
    
}
