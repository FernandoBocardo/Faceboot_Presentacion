package utils;

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
    
    String host = "localhost";
    int puerto = 1234;
    private BufferedReader entrada;
    public BufferedWriter salida;
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
            notificador = new Notificador(socketNotificacion, "notificarConsultaMenciones", "editarUsuario", "notificarEdicionPerfil", "consultarPublicacionesPorEtiqueta", "notificarConsultaComentarios", "notificarRegistroComentario", "notificarConsultaPublicaciones", "notificarRegistroUsuario", "notificarRegistroPublicacion", "notificarSesionIniciada", "notificarSesionNoIniciada");
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
            this.entrada.close();
            this.salida.close();
            this.socketCliente.close();
            this.socketNotificacion.close();
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
    }
     
    public void enviarEventoUsuario(String eventType, Usuario usuario)
    {
        String jsonUsuario = Controlador.getInstance().usuarioToJson(usuario);
        try
        {
            salida.write(eventType);
            salida.newLine();
            salida.write(jsonUsuario);
            salida.newLine();
            if(!eventType.equals("registrarUsuario"))
            {
                salida.write(jsonUsuario);
                salida.newLine();
            }
            salida.flush();
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void enviarEventoPublicaciones(String eventType, String contenido, String usuario)
    {
        try
        {
            salida.write(eventType);
            salida.newLine();
            salida.write(contenido);
            salida.newLine();
            if(!eventType.equals("registrarUsuario"))
            {
                salida.write(usuario);
                salida.newLine();
            }
            salida.flush();
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void enviarEventoComentarios(String eventType, String contenido, String usuario)
    {
        try
        {
            salida.write(eventType);
            salida.newLine();
            salida.write(contenido);
            salida.newLine();
            if(!eventType.equals("registrarUsuario"))
            {
                salida.write(usuario);
                salida.newLine();
            }
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
