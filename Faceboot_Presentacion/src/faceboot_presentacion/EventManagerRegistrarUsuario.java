/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package faceboot_presentacion;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hiram
 */
public class EventManagerRegistrarUsuario implements iEventListener{
    
    private static volatile EventManagerRegistrarUsuario instance;
    private Map<String, List<iEventListener>> listeners = new HashMap<>();

    public EventManagerRegistrarUsuario(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
        Notificador.getInstance().subscribe("NotificacionUsuarioRegistrado", this);
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
    
    public static EventManagerRegistrarUsuario getInstance() 
    {
        EventManagerRegistrarUsuario result = instance;
        if (result != null) {
            return result;
        }
        synchronized(EventManagerRegistrarUsuario.class) 
        {
            if(instance == null) 
            {
                instance = new EventManagerRegistrarUsuario("NotificacionUsuarioRegistrado", "sesionIniciada");
            }
        return instance;
        }
    }

    @Override
    public void update(String eventType, String contenido) {
        notify(eventType, contenido);
    }
    
}
