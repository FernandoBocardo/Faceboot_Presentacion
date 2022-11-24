/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package faceboot_presentacion;

import Dominio.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Hiram
 */
public class Fachada {
    
    String host = "localhost";
    int puerto = 1234;
    protected DataOutputStream salida;
    protected DataOutputStream salida2;
    protected DataInputStream entrada;
    private ObjectMapper objectMapper = new ObjectMapper();
    
    public void usuario(String eventType, Usuario usuario)
    {
        String jsonUsuario = null;
        try
        {
            jsonUsuario = objectMapper.writeValueAsString(usuario);
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
        try
        {
            Socket sc = new Socket(host, puerto);
            Socket sc2 = new Socket(host, puerto);
            salida = new DataOutputStream(sc.getOutputStream());
            salida2 = new DataOutputStream(sc2.getOutputStream());
            entrada = new DataInputStream(sc.getInputStream());
            salida.writeUTF(eventType);
            salida2.writeUTF(jsonUsuario);
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
}
