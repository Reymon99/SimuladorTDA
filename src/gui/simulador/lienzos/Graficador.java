package gui.simulador.lienzos;
import gui.simulador.Simulador;
import tools.Fuentes;
import java.awt.*;
import java.awt.geom.Line2D;
public class Graficador extends Canvas {
    private boolean graficar;
    private int x,y;
    /**
     * Grafica un punto en una coordenada dada
     * @author Sergio Majé
     */
    public Graficador(){
        setPreferredSize(Simulador.canvasSize);
        setMaximumSize(Simulador.canvasSize);
        setMinimumSize(Simulador.canvasSize);
        setFont(Fuentes.UBUNTULIGHT12.getFont());
        graficar=false;
        x=y=0;
    }
    /**
     * Grafica un punto con las coordenadas dadas
     * @param x int coordenada en x
     * @param y int coordenada en y
     * @author Sergio Majé
     */
    public void graficar(int x,int y){
        this.x=x;
        this.y=y;
        graficar=true;
        repaint();
    }
    /**
     * Coordenadas dadas del punto
     * @return {@link String}
     * @author Sergio Majé
     */
    public String coordenadas(){
        return "("+this.x+","+this.y+")";
    }
    /**
     * Limpia la grafica de las coordenadas que han sido graficadas
     * @author Sergio Majé
     */
    public void limpiar(){
        x=y=0;
        graficar=false;
        repaint();
    }
    /**
     * Estado de graficacion
     * @return boolean
     * @author Sergio Majé
     */
    public boolean isGraficar() {
        return graficar;
    }
    /**
     * Modifica el estado de graficacion
     * @param graficar boolean
     * @author Sergio Majé
     */
    public void setGraficar(boolean graficar) {
        this.graficar = graficar;
    }
    /**
     * Divide el ancho de la dimensión del Canvas
     * @return mitad del ancho del Canvas
     * @author Sergio Majé
     */
    private int halfScreenWidth(){
        return Simulador.canvasSize.width/2;
    }
    /**
     * Divide el alto de la dimensión del Canvas
     * @return mitad del alto del Canvas
     * @author Sergio Majé
     */
    private int halfScreenHeight(){
        return Simulador.canvasSize.height/2;
    }
    /**
     * Inicio o fin de la linea X
     * @param cuadrante true: + | false: -
     * @author Sergio Majé
     */
    private int positionX(boolean cuadrante){
        return cuadrante ? halfScreenWidth()+280 : halfScreenWidth()-280;
    }
    /**
     * Inicio o fin de la linea Y
     * @param cuadrante true: + | false: -
     * @author Sergio Majé
     */
    private int positionY(boolean cuadrante){
        return cuadrante ? halfScreenHeight()-280 : halfScreenHeight()+280;
    }
    /**
     * Dibuja y grafica el punto en las coordenadas dadas
     * @param g {@link Graphics}
     * @author Sergio Majé
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2=(Graphics2D)g;
        g2.draw(new Line2D.Double(positionX(false),halfScreenHeight(),positionX(true),halfScreenHeight()));//horizontal
        g2.draw(new Line2D.Double(halfScreenWidth(),positionY(true),halfScreenWidth(),positionY(false)));//vertical
        int x=-10,y=10;
        Point point=new Point();
        for (int i = positionY(true)+10,j = positionX(false)+10; i <= positionY(false); i+=27, j+=27) {
            if (this.x==x && graficar) point.x=(this.x==0)?halfScreenWidth():j;//Si x = 0 toma la mitad de la pantalla de lo contrario tomara la coodernada de j
            if (this.y==y && graficar) point.y=(this.y==0)?halfScreenHeight():i;//Si y = 0 toma la mitad de la pantalla de lo contrario tomara la coodernada de i
            if (i!=290 && j!=halfScreenWidth()){
                g2.draw(new Line2D.Double(halfScreenWidth()-2,i,halfScreenWidth()+2,i));//y
                g2.draw(new Line2D.Double(j,halfScreenHeight()-2,j,halfScreenHeight()+2));//x
                if (x==0 && y==0) {
                    x = 1;
                    y = -1;
                    if (this.x==1) point.x=j;
                    if (this.y==-1) point.y=i;
                }
                g2.drawString(String.valueOf(y),x<0 ? halfScreenWidth()+5 : Math.abs(x)==10 ? halfScreenWidth()-22 : halfScreenWidth()-16,i+4);//y
                g2.drawString(String.valueOf(x),x>0 ? j-4 : j-8,x>0 ? halfScreenHeight()-6 : halfScreenHeight()+15);//x
                x++;
                y--;
            }
        }
        if (graficar){
            if (this.x>0 && this.y>0) g2.drawString(coordenadas(),point.x+7,point.y);//Cuandrante positivo
            else if (this.x<0 && this.y>0) g2.drawString(coordenadas(),point.x-22,point.y-7);//Cuadrante negativo - positivo
            else if (this.x<0 && this.y<0) g2.drawString(coordenadas(),point.x-22,point.y+17);//Cuadrante negativo
            else if (this.x>0 && this.y<0) g2.drawString(coordenadas(),point.x-17,point.y+17);//Cuadrante positivo - negativo
            else if (this.x==0 && this.y==0) g2.drawString(coordenadas(),point.x-29,point.y-9);//Punto medio
            else if (this.x==0) g2.drawString(coordenadas(),point.x-45,point.y-2);
            else g2.drawString(coordenadas(),point.x-17,point.y+19);//y==0
            g2.setPaint(Color.GRAY);//lineas
            g2.setStroke(new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f,new float[]{10},0.0f));
            g2.draw(new Line2D.Double(halfScreenWidth(),point.y,point.x,point.y));
            g2.draw(new Line2D.Double(point.x,halfScreenHeight(),point.x,point.y));
            g2.setFont(Fuentes.DIALOG35.getFont());
            g2.setPaint(Color.RED);//punto
            g2.drawString(".",point.x-5,point.y+4);
        }
    }
}