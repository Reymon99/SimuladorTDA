package gui.paneles.modularidad;
import gui.contenido.Contenido;
import gui.contenido.Texto;
import tools.Constrains;
import tools.Text;
import java.awt.*;
public class Modularidad extends Contenido {
    /**
     * Contenido del panel Modularidad
     * @see Contenido
     * @author Sergio Majé
     */
    public Modularidad(){
        getTitle().setText("Modularidad");
        getNext().setToolTipText("");
        Constrains.addComp(new Texto(Text.MODULARIDAD.toString()),getContenido(),0,0,1,1,1,1,10, 25, 15, 12, GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL);
        Constrains.addComp(new Texto(Text.MODULARIDAD1.toString()),getContenido(),0,1,1,1,1,1,10, 25, 15, 12, GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL);
    }
}