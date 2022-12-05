/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import Dominio.Comentario;
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
public class ComentariosScrollPane {
    
    private int elementCount;
    private JPanel elementsPanel ;
    private Conexion conexion;

    public ComentariosScrollPane(Conexion conexion) {
        this.elementCount = 0;
        this.conexion = conexion;
        this.elementsPanel = new JPanel(new GridLayout(0,1,2,2));
    }

    public JPanel getComentarios(List<Comentario> comentarios, JPanel jPanel, String usuario) {
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
        for (Comentario comentario: comentarios) 
        {
            elementsPanel.add(getPanelForScroll(comentario, usuario));
        }
        return jPanel;
    }

    private JPanel getPanelForScroll(Comentario comentario, String usuario) {
        PanelComentario p = new PanelComentario(comentario, usuario, conexion);
        elementCount++;
        return p;
    }
    
}
