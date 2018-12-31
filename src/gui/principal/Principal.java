package gui.principal;
import eventos.Eventos;
import fuentes.Fuentes;
import logica.*;
import logica.Archivos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Principal extends JPanel {
    /**
     * Menu principal de la interfaz grafica del proyecto
     */
    public Principal(){
        setLayout(new GridBagLayout());
        init();
    }
    private void init()  {
        JLabel tda=label("tda","Tipos de Datos Abstratos", Archivos.image("/image/TDA.png", 88, -1), new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Eventos.show(Paneles.TDA.toString());
            }
        });
        JLabel modularidad=label("Modularidad","Complejidad Algoritmica", Archivos.image("/image/Modularidad y Mapas.png", 88, -1), new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        JLabel recursividad=label("Recursividad","Metodos Recursivos", Archivos.image("/image/Recursividad.png", 88, -1), new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        JLabel arreglos=label("Arreglos","Vectores y Matrices", Archivos.image("/image/Arreglos y Arboles.png", 88, -1), new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        JLabel nodos=label("Nodos","Enlazamiento de Datos", Archivos.image("/image/Nodos.png", 88, -1), new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        JLabel ordenamiento=label("Ordenamiento", "Metodos de Ordenamiento Interno", Archivos.image("/image/Ordenamiento.png", 88, -1), new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        JLabel busqueda=label("Busqueda","Metodos de Busqueda", Archivos.image("/image/Busqueda.png", 88, -1), new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        JLabel pila=label("Pilas","Pilas Dinamicas y Estaticas", Archivos.image("/image/Pilas.png", 88, -1), new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        JLabel cola=label("Colas","Colas Dinamicas y Estaticas", Archivos.image("/image/Colas.png", 88, -1), new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        JLabel lista=label("Listas","Listas Enlazadas", Archivos.image("/image/Listas Enlazadas.png", 88, -1), new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        JLabel arboles=label("Árboles","Arboles Binarios y AVL", Archivos.image("/image/Arreglos y Arboles.png", 88, -1), new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        JLabel grafos=label("Grafos","Algoritmo del Camino más Corto", Archivos.image("/image/Grafos.png", 88, -1), new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        Constrains.addCompY(tda,this,0,0,1,4,0.1,30,57,30,18,GridBagConstraints.CENTER,GridBagConstraints.NONE);
        Constrains.addCompY(modularidad, this,1,0,1,1,0.1,30,18,0,18,GridBagConstraints.CENTER,GridBagConstraints.NONE);
        Constrains.addCompY(recursividad,this,1,1,1,1,0.1,13,18,13,18,GridBagConstraints.CENTER,GridBagConstraints.NONE);
        Constrains.addCompY(arreglos,this,1,2,1,1,0.1,13,18,13,18,GridBagConstraints.CENTER,GridBagConstraints.NONE);
        Constrains.addCompY(nodos,this,1,3,1,1,0.1,13,18,30,18,GridBagConstraints.CENTER,GridBagConstraints.NONE);
        Constrains.addCompY(ordenamiento,this,2,0,1,2,0.1,30,18,13,18, GridBagConstraints.SOUTH, GridBagConstraints.NONE);
        Constrains.addCompY(busqueda,this,2,2,1,2,0.1,13,18,30,18,GridBagConstraints.NORTH,GridBagConstraints.NONE);
        Constrains.addCompY(pila,this,3,0,1,2,0.1,30,18,13,18,GridBagConstraints.CENTER,GridBagConstraints.NONE);
        Constrains.addCompY(cola,this,3,1,1,2,0.1,13,18,13,13,GridBagConstraints.CENTER,GridBagConstraints.NONE);
        Constrains.addCompY(lista,this,3,2,1,2,0.1,13,18,30,18,GridBagConstraints.CENTER,GridBagConstraints.NONE);
        Constrains.addCompY(arboles,this,4,0,1,2,0.1,30,18,13,75,GridBagConstraints.SOUTH,GridBagConstraints.NONE);
        Constrains.addCompY(grafos,this,4,2,1,2,0.1,13,18,30,75,GridBagConstraints.NORTH,GridBagConstraints.NONE);
    }
    /**
     * Etiqueta con parametros predefinidos enfocados hacia el proyecto
     * @param text {@link String}
     * @param tip {@link String}
     * @param icon {@link ImageIcon}
     * @param adapter {@link MouseAdapter}
     * @return label {@link JLabel}
     */
    private JLabel label(String text,String tip, ImageIcon icon, MouseAdapter adapter){
        JLabel label=new JLabel(text,icon,SwingConstants.CENTER);
        label.setFont(Fuentes.PURISA18.getFont());
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setToolTipText(tip);
        label.addMouseListener(adapter);
        return label;
    }
    /**
     * Tapiz del panel
     * @param g {@link Graphics}
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/Lienzo.jpg")),0,0,getWidth(),getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }
}