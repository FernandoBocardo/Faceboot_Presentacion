/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Fernando
 */
public class Notificador extends Thread{

    private Map<String, List<iEventListener>> listeners = new HashMap<>();
    private Socket socketNotificacion;
    private BufferedReader entrada;
    public BufferedWriter salida;
    private boolean estado;
    private String usuarioJson;
    
    
    @Override
    public void run() {
        this.estado = true;
        while(estado)
        {
            try
            {
                if(entrada.ready())
                {
                    String eventType = entrada.readLine();
                    String contenido = entrada.readLine();
                    if(!eventType.equals("notificarRegistroUsuario"))
                    {
                        usuarioJson = entrada.readLine();
                    }
                    notify(eventType, contenido, usuarioJson);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public boolean getEstado()
    {
        return this.estado;
    }

    public void desactivar() {
        this.estado = false;
    }

    public Notificador(Socket socketNotificacion, String... operations) {
        this.socketNotificacion = socketNotificacion;
        try
        {
            entrada = new BufferedReader(new InputStreamReader(this.socketNotificacion.getInputStream()));
            salida = new BufferedWriter(new OutputStreamWriter(this.socketNotificacion.getOutputStream()));
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

    public void notify(String eventType, String contenido, String usuarioJson) {
        List<iEventListener> users = new CopyOnWriteArrayList(listeners.get(eventType));
        for (iEventListener listener : users) {
            listener.update(eventType, contenido, usuarioJson);
        }
    }
    
    public void setSocketNotificacion(Socket socketNotificacion)
    {
        this.socketNotificacion = socketNotificacion;
        try
        {
            entrada = new BufferedReader(new InputStreamReader(this.socketNotificacion.getInputStream()));
            salida = new BufferedWriter(new OutputStreamWriter(this.socketNotificacion.getOutputStream()));
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    
    
}
