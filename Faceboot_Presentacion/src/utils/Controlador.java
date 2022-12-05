/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import Dominio.Comentario;
import Dominio.Etiqueta;
import Dominio.Publicacion;
import Dominio.Usuario;
import Dominio.UsuarioEtiquetado;
import Negocios.CtrlUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Fernando
 */
public class Controlador {
    
    private static volatile Controlador instance;
    private ObjectMapper objectMapper;
    
    public Controlador()
    {
        objectMapper = new ObjectMapper();
    }
    
    public static Controlador getInstance() 
    {
        Controlador result = instance;
        if (result != null) {
            return result;
        }
        synchronized(Controlador.class) 
        {
            if(instance == null) 
            {
                instance = new Controlador();
            }
        return instance;
        }
    }
    
    public String usuarioToJson(Usuario usuario)
    {
        String usuarioJson = null;
        try
        {
            usuarioJson = objectMapper.writeValueAsString(usuario);
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
        return usuarioJson;
    }
    
    public String publicacionToJson(Publicacion publicacion)
    {
        String publicacionJson = null;
        try
        {
            publicacionJson = objectMapper.writeValueAsString(publicacion);
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
        return publicacionJson;
    }
    
    public String comentarioToJson(Comentario comentario)
    {
        String comentarioJson = null;
        try
        {
            comentarioJson = objectMapper.writeValueAsString(comentario);
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
        return comentarioJson;
    }
    
    public File elegirImagen(JFrame jFrame)
    {
        JFileChooser selectorArchivos = new JFileChooser();
        FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("Imagenes","jpg","png", "jpeg");
        selectorArchivos.removeChoosableFileFilter(selectorArchivos.getFileFilter());
        selectorArchivos.setFileFilter(filtroImagen);
        int respuesta = selectorArchivos.showOpenDialog(jFrame);
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            File archivoImagenSeleccionada = selectorArchivos.getSelectedFile();
            if(archivoImagenSeleccionada.length() <= 5242880L) //5MB
            {
                return archivoImagenSeleccionada;
            }
            else
            {
                JOptionPane.showMessageDialog(jFrame, "La imagen pesa demasiado, eliga uno con un peso menor a 5MB", 
                    "Imagen muy pesada", 
                    JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }
        return null;
    }
    
    public String recortarNombreImagen(String nombreImagen)
    {
        if(nombreImagen.length() > 50)
            {
                nombreImagen = nombreImagen.substring(0, 46);
                nombreImagen = nombreImagen+"...";
            }
        return nombreImagen;
    }
    
    public byte[] imagenToByte(File archivoImagen)
    {
        byte[] imagenBytes = null;
        try 
        {
            imagenBytes = FileUtils.readFileToByteArray(archivoImagen);
            return imagenBytes;
        } catch (IOException ex) 
        {
            java.util.logging.Logger.getLogger(Controlador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Etiqueta> generarEtiquetas(String mensaje)
    {
        List<Etiqueta> etiquetas = new ArrayList<>();
        int i = 0;
        while(i < mensaje.length())
        {
            char c = mensaje.charAt(i);
            if(c == '#')
            {
                i++;
                char[] caractersEtiqueta = new char[mensaje.length()];
                int j = 0;
                while(c != ' ' && i < mensaje.length())
                {
                    c = mensaje.charAt(i);
                    caractersEtiqueta[j] = c;
                    i++;
                    j++;
                }
                String etiquetaString = String.valueOf(caractersEtiqueta);
                etiquetaString = etiquetaString.substring(0, etiquetaString.length() - 1);
                Etiqueta etiqueta = new Etiqueta(etiquetaString);
                etiquetas.add(etiqueta);
            }
            i++;
        }
        return etiquetas;
    }
    
    public List<UsuarioEtiquetado> generarUsuariosEtiquetados(String mensaje)
    {
        List<UsuarioEtiquetado> usuariosEtiquetados = new ArrayList<>();
        int i = 0;
        while(i < mensaje.length())
        {
            char c = mensaje.charAt(i);
            if(c == '@')
            {
                i++;
                char[] caractersUsuarioEtiquetado = new char[mensaje.length()];
                int j = 0;
                while(c != ' ' && i < mensaje.length())
                {
                    c = mensaje.charAt(i);
                    caractersUsuarioEtiquetado[j] = c;
                    i++;
                    j++;
                }
                String usuarioEtiquetadoString = String.valueOf(caractersUsuarioEtiquetado);
                usuarioEtiquetadoString = usuarioEtiquetadoString.substring(0, usuarioEtiquetadoString.length() - 1);
                UsuarioEtiquetado usuarioEtiquetado = new UsuarioEtiquetado(usuarioEtiquetadoString);
                usuariosEtiquetados.add(usuarioEtiquetado);
            }
            i++;
        }
        return usuariosEtiquetados;
    }

    
}
