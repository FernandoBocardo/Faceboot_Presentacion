/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import utils.Conexion;
import utils.iEventListener;

/**
 *
 * @author Fernando
 */
public class EventManagerNotificacionEdicionPerfil implements iEventListener{
    
    private Map<String, List<iEventListener>> listeners = new HashMap<>();
    private static volatile EventManagerNotificacionEdicionPerfil instance;
    
    private EventManagerNotificacionEdicionPerfil(Conexion conexion, String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
        conexion.getNotificador().subscribe("notificarEdicionPerfil", this);
    }
    
    public static EventManagerNotificacionEdicionPerfil getInstance(Conexion conexion, String... operations) 
    {
        EventManagerNotificacionEdicionPerfil result = instance;
        if (result != null) {
            return result;
        }
        synchronized(EventManagerNotificacionEdicionPerfil.class) 
        {
            if(instance == null) 
            {
                instance = new EventManagerNotificacionEdicionPerfil(conexion , operations);
            }
        return instance;
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
    
    

    @Override
    public void update(String eventType, String contenido, String usuarioJson) {
        notify(eventType, contenido, usuarioJson);
    }
    
}