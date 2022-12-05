/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import Dominio.Publicacion;
import Dominio.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import utils.Conexion;

/**
 *
 * @author Fernando
 */
public class MuroScrollPane {
    
    private int elementCount;
    private JPanel elementsPanel ;
    private Conexion conexion;

    public MuroScrollPane(Conexion conexion) {
        this.elementCount = 0;
        this.conexion = conexion;
        this.elementsPanel = new JPanel(new GridLayout(0,1,2,2));
    }

    public JPanel getMuro(List<Publicacion> publicaciones, JPanel jPanel, String usuario) {
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
        for (Publicacion publicacion: publicaciones) 
        {
            elementsPanel.add(getPanelForScroll(publicacion, usuario));
        }
        return jPanel;
    }

    private JPanel getPanelForScroll(Publicacion publicacion, String usuario) {
        PanelPublicacion p = new PanelPublicacion(publicacion, usuario, conexion);
        elementCount++;
        return p;
    }
    
    
    
    
    
}
