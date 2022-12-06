/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import Dominio.Publicacion;
import Dominio.UsuarioEtiquetado;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import utils.Conexion;

/**
 *
 * @author Fernando
 */
public class MencionesScrollPane {
    
    private int elementCount;
    private JPanel elementsPanel ;
    private Conexion conexion;

    public MencionesScrollPane(Conexion conexion) {
        this.elementCount = 0;
        this.conexion = conexion;
        this.elementsPanel = new JPanel(new GridLayout(0,1,2,2));
    }

    public JPanel getMenciones(UsuarioEtiquetado usuarioEtiquetado, JPanel jPanel, String usuario) {
        // this will become the content pane of the frame
        this.elementCount = 0;
        jPanel = new JPanel(new BorderLayout(4,4));
        jPanel.setBorder(new EmptyBorder(4,4,4,4));
        JPanel scrollPanel = new JPanel(new BorderLayout());
        scrollPanel.add(elementsPanel, BorderLayout.PAGE_START);
        jPanel.add(new JScrollPane(scrollPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
        );
        if(usuarioEtiquetado != null)
        {
            List<Publicacion> publicacionesConMencion = usuarioEtiquetado.getPublicaciones();
            for (Publicacion publicacion: publicacionesConMencion) 
            {
                elementsPanel.add(getPanelForScroll(publicacion, usuario));
            }
        }
        
        return jPanel;
    }

    private JPanel getPanelForScroll(Publicacion publicacion, String usuario) {
        PanelMencion p = new PanelMencion(publicacion, usuario, conexion);
        elementCount++;
        return p;
    }
    
}
