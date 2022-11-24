/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package faceboot_presentacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Fernando
 */
public class Notificador extends Thread{

    private static volatile Notificador instance;
    private Map<String, List<iEventListener>> listeners = new HashMap<>();
    private Socket socketCliente;
    private BufferedReader entrada;
    public BufferedWriter salida;
    
    
    @Override
    public void run() {
        boolean i = true;
        while(i)
        {
            try
            {
                if(entrada.ready())
                {
                    String eventType = entrada.readLine();
                    String contenido = entrada.readLine();
                    if(!eventType.equalsIgnoreCase("NotificacionPublicacionRegistrada"))
                    {
                        i = false;
                    }
                    notify(eventType, contenido);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    

    public Notificador(Socket socketCliente, String... operations) {
        this.socketCliente = socketCliente;
        try
        {
            entrada = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));
            salida = new BufferedWriter(new OutputStreamWriter(this.socketCliente.getOutputStream()));
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }
    
    public Notificador(String... operations)
    {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, iEventListener listener) {
        List<iEventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, iEventListener listener) {
        List<iEventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType, String contenido) {
        List<iEventListener> users = listeners.get(eventType);
        for (iEventListener listener : users) {
            listener.update(eventType, contenido);
        }
    }
    
    public void setSocketCliente(Socket socketCliente)
    {
        this.socketCliente = socketCliente;
        try
        {
            entrada = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));
            salida = new BufferedWriter(new OutputStreamWriter(this.socketCliente.getOutputStream()));
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public static Notificador getInstance() 
    {
        Notificador result = instance;
        if (result != null) {
            return result;
        }
        synchronized(Notificador.class) 
        {
            if(instance == null) 
            {
                instance = new Notificador("NotificacionUsuarioRegistrado", "NotificacionPublicacionRegistrada");
            }
        return instance;
        }
    }
    
    public static Notificador getInstance(Socket socketCliente) 
    {
        Notificador result = instance;
        if (result != null) {
            result.setSocketCliente(socketCliente);
            return result;
        }
        synchronized(Notificador.class) 
        {
            if(instance == null) 
            {
                instance = new Notificador(socketCliente, "NotificacionUsuarioRegistrado", "NotificacionPublicacionRegistrada");
            }
        return instance;
        }
    }
    
}
