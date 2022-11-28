package faceboot_presentacion;

import Dominio.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Hiram
 */
public class Conexion {
    
    private static volatile Conexion instance;
    String host = "localhost";
    int puerto = 1234;
    private BufferedReader entrada;
    public BufferedWriter salida;
    private ObjectMapper objectMapper = new ObjectMapper();
    public Socket socketCliente;
    public Socket socketNotificacion;
    private Notificador notificador;
    
    public Conexion()
    {
        try
        {
            socketCliente = new Socket(host, puerto);
            socketNotificacion = new Socket(host, puerto);
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            salida = new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream()));
            notificador = new Notificador(socketNotificacion, "notificarRegistroUsuario", "notificarRegistroPublicacion");
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    public void desconectarSockets()
    {
        try
        {
            this.socketCliente.close();
            this.socketNotificacion.close();
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void eventoUsuario(String eventType, Usuario usuario)
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
            salida.write(eventType);
            salida.newLine();
            salida.write(jsonUsuario);
            salida.newLine();
            salida.flush();
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void eventoPublicacion(String eventType, String publicacion)
    {
        try
        {
            salida.write(eventType);
            salida.newLine();
            salida.write(publicacion);
            salida.newLine();
            salida.flush();
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void iniciarNotificador()
    {
        if(!notificador.getEstado())
        {
            notificador.start();
        }
    }
    
    public Notificador getNotificador()
    {
        return this.notificador;
    }
    
    public void detenerNotificador()
    {
        notificador.desactivar();
    }
    
    
}
