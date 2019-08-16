package gui.simulador.lienzos;
import gui.simulador.Simulador;
import tools.Fuentes;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
public class Graficador extends JLabel {
    private boolean graficar;
    private Point punto;
    private Point axis;
    /**
     * Grafica un punto en una coordenada dada
     */
    public Graficador(){
        setFont(Fuentes.UBUNTU_LIGHT_12.getFont());
        graficar=false;
        punto=new Point();
        axis=new Point();
    }
    /**
     * Grafica un punto con las coordenadas dadas
     * @param x int coordenada en x
     * @param y int coordenada en y
     */
    public void graficar(int x, int y){
        punto.move(x, y);
        graficar=true;
        repaint();
    }
    /**
     * Coordenadas dadas del punto
     * @return coordenadas formateadas
     */
    private String coordenadas(){
        return "("+this.punto.x+","+this.punto.y+")";
    }
    /**
     * Limpia la grafica de las coordenadas que han sido graficadas
     */
    public void limpiar(){
        punto.move(0, 0);
        graficar=false;
        repaint();
    }
    /**
     * Divide el ancho de la dimensión del Canvas
     * @return mitad del ancho del Canvas
     */
    private int halfScreenWidth(){
        return Simulador.canvasSize.width/2;
    }
    /**
     * Divide el alto de la dimensión del Canvas
     * @return mitad del alto del Canvas
     */
    private int halfScreenHeight(){
        return Simulador.canvasSize.height/2;
    }
    /**
     * Inicio o fin de la linea X
     * @param cuadrante true: + | false: -
     */
    private int positionX(boolean cuadrante){
        return cuadrante ? halfScreenWidth()+280 : halfScreenWidth()-280;
    }
    /**
     * Inicio o fin de la linea Y
     * @param cuadrante true: + | false: -
     */
    private int positionY(boolean cuadrante){
        return cuadrante ? halfScreenHeight()-280 : halfScreenHeight()+280;
    }
    /**
     * Grafica las coordenadas dadas según el estado de graficación
     * @param g2 pincel
     * @param point punto de graficación de coordenadas
     */
    private void graficarCoordenada(Graphics2D g2, Point point){
        if (graficar){
            if (this.punto.x>0 && this.punto.y>0) g2.drawString(coordenadas(),point.x+7,point.y);//Cuandrante positivo
            else if (this.punto.x<0 && this.punto.y>0) g2.drawString(coordenadas(),point.x-22,point.y-7);//Cuadrante negativo - positivo
            else if (this.punto.x<0 && this.punto.y<0) g2.drawString(coordenadas(),point.x-22,point.y+17);//Cuadrante negativo
            else if (this.punto.x>0 && this.punto.y<0) g2.drawString(coordenadas(),point.x-17,point.y+17);//Cuadrante positivo - negativo
            else if (this.punto.x==0 && this.punto.y==0) g2.drawString(coordenadas(),point.x-29,point.y-9);//Punto medio
            else if (this.punto.x==0) g2.drawString(coordenadas(),point.x-45,point.y-2);
            else g2.drawString(coordenadas(),point.x-17,point.y+19);//y==0
            g2.setPaint(Color.GRAY);//lineas
            g2.setStroke(new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f,new float[]{10},0.0f));
            g2.draw(new Line2D.Double(halfScreenWidth(),point.y,point.x,point.y));
            g2.draw(new Line2D.Double(point.x,halfScreenHeight(),point.x,point.y));
            g2.setFont(Fuentes.DIALOG_35.getFont());
            g2.setPaint(Color.RED);//punto
            g2.drawString(".",point.x-5,point.y+4);
        }
    }
    /**
     * Grafica las líneas de eje X y Y
     * @param g2 pincel
     */
    private void eje(Graphics2D g2) {
        g2.draw(new Line2D.Double(positionX(false), halfScreenHeight(), positionX(true), halfScreenHeight()));//horizontal
        g2.draw(new Line2D.Double(halfScreenWidth(), positionY(true), halfScreenWidth(), positionY(false)));//vertical
    }
    /**
     * Guarda la posición en pantalla de la coordenada X
     * @param point Si x = 0 toma la mitad de la pantalla de lo contrario tomará la coodernada de j
     * @param j pixel X
     */
    private void puntoCoordenadaX(Point point, int j){
        if (this.punto.x==this.axis.x && graficar) point.x = this.punto.x==0 ? halfScreenWidth() : j;
    }
    /**
     * Guarda la posición en pantalla de la coordenada Y
     * @param point Si y = 0 toma la mitad de la pantalla de lo contrario tomará la coodernada de i
     * @param i pixel X
     */
    private void puntoCoordenadaY(Point point, int i){
        if (this.punto.y==this.axis.y && graficar) point.y = this.punto.y==0 ? halfScreenHeight() : i;
    }
    /**
     * Guarda la posición en pantalla de las coordenadas X y Y
     * @param point puntos de las posiciones a guardar
     * @param i posición en pantalla vertical
     * @param j posición en pantalla horizontal
     */
    private void puntoCoordenadas(Point point, int i, int j){
        puntoCoordenadaX(point, j);
        puntoCoordenadaY(point, i);
    }
    /**
     * Grafica los valores del axis X y el axis Y
     * @param g2 pincel
     * @param point punto a guardar las coordenas
     */
    private void valoresAxisXY(Graphics2D g2, Point point){
        axis.move(-10, 10);
        int i = positionY(true)+axis.y;
        int j = positionX(false)+axis.y;
        while (i <= positionY(false)){
            puntoCoordenadas(point, i, j);//Coordenas en pixeles del punto a graficar
            axisXY(g2, point, i, j);//axis corelativo de X y Y
            i+=27;
            j+=27;
        }
    }
    /**
     * Grafica la posición X y Y indicada en el axis X - Y del plano cartesiano
     * @param g2 pincel
     * @param point punto de coordenadas
     * @param i pixel en Y
     * @param j pixel en X
     */
    private void axisXY(Graphics2D g2, Point point, int i, int j){
        if (i!=290 && j!=halfScreenWidth()){
            axis(g2, i, j);
            if (axis.x==0 && axis.y==0) axisZero(point, i, j);
            number(g2, i, j);
            axis.move(axis.x+1, axis.y-1);
        }
    }
    /**
     * Dibuja el número correspondiente en el axis X y Y
     * @param g2 pincel
     * @param i pixel en Y
     * @param j pixel en X
     */
    private void number(Graphics2D g2, int i, int j){
        g2.drawString(String.valueOf(axis.y), posicion(axis.x<0, halfScreenWidth()+5, posicion(Math.abs(axis.x)==10, halfScreenWidth()-22, halfScreenWidth()-16)), i+4);//y
        g2.drawString(String.valueOf(axis.x), posicion(axis.x>0, j-4, j-8), posicion(axis.x>0, halfScreenHeight()-6, halfScreenHeight()+15));//x
    }
    /**
     * Posición doble a pocesionar
     * @param expr expresión de posecionamiento a evaluar
     * @param a valor 'a' a retornar
     * @param b valor 'b' a retornar
     * @return si la expresión es verdadera devolverá a 'a' de lo contrario devolverá a 'b'
     */
    private int posicion(boolean expr, int a, int b){
        return expr ? a : b;
    }
    /**
     * Pinta el axis X - Y en el plano cartesiano
     * @param g2 pincel
     * @param i pixel en Y
     * @param j pixel en X
     */
    private void axis(Graphics2D g2, int i, int j){
        g2.draw(new Line2D.Double(halfScreenWidth()-2,i,halfScreenWidth()+2,i));//y
        g2.draw(new Line2D.Double(j,halfScreenHeight()-2,j,halfScreenHeight()+2));//x
    }
    /**
     * Cuando X y Y equivalen a cero se pasan al siguiente axis a dibujar
     * @param point punto de coordenadas
     * @param i pixel en Y
     * @param j pixel en X
     */
    private void axisZero(Point point, int i, int j){
        axis.move(1, -1);
        puntoCoordenadas(point, i, j);
    }
    /**
     * Dibuja y grafica el punto en las coordenadas dadas
     * @param g {@link Graphics}
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2=(Graphics2D)g;
        Point point=new Point();
        eje(g2);
        valoresAxisXY(g2,point);
        graficarCoordenada(g2,point);
        g2.dispose();
    }
}