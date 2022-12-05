/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package utils;

import java.net.Socket;

/**
 *
 * @author Fernando
 */
public interface iEventListener {
    
    void update(String eventType, String contenido, String usuarioJson);
    
}
