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
    public Socket sc;
    
    public Conexion()
    {
        try
        {
            sc = new Socket(host, puerto);
            entrada = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            salida = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    public static Conexion getInstance() 
    {
        Conexion result = instance;
        if (result != null) {
            return result;
        }
        synchronized(Conexion.class) 
        {
            if(instance == null) 
            {
                instance = new Conexion();
            }
        return instance;
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
        notificador();
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
    
    public void notificador()
    {
        Notificador.getInstance(sc).start();
    }
    
    
}
