package gui.contenido.scroll;

import tools.Colour;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static gui.contenido.scroll.ModernScrollPane.THUMB_SIZE;

public class ModernScrollBarUI extends BasicScrollBarUI {
    private final ModernScrollPane sp;

    /**
     * Interfaz personalizada al JScrollBar
     * @param sp {@link JScrollPane} que va se le va a personalizar los JScrollBar´s
     */
    protected ModernScrollBarUI(ModernScrollPane sp) {
        this.sp = sp;
    }

    /**
     * Se oculta el botón de decremento en el JScrollBar
     * @param orientation orientación del scroll
     * @return botón de decremento oculto
     */
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new InvisibleScrollBarButton();
    }

    /**
     * Se oculta el botón de incremento en el JScrollBar
     * @param orientation orientación del scroll
     * @return botón de incremento oculto
     */
    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new InvisibleScrollBarButton();
    }

    /**
     * Se le da una nueva interfaz al JScrollBar
     */
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        int orientation = scrollbar.getOrientation();
        int x = thumbBounds.x;
        int y = thumbBounds.y;
        int width = Math.max(orientation == JScrollBar.VERTICAL ? THUMB_SIZE : thumbBounds.width, THUMB_SIZE);
        int height = Math.max(orientation == JScrollBar.VERTICAL ? thumbBounds.height : THUMB_SIZE, THUMB_SIZE);
        Color color;
        JScrollBar sb = (JScrollBar) c;
        if (!sb.isEnabled()) return;
        else if (isDragging) color = Colour.SCROLL_DRAGGING.getColor();
        else if (isThumbRollover()) color = Colour.SCROLL_ROLLOVER.getColor();
        else color = Colour.SCROLL_PRESSED.getColor();
        Graphics2D graphics2D = (Graphics2D) g.create();
        graphics2D.setColor(color);
        graphics2D.fillRoundRect(x, y, width, height, 10, 10);
        graphics2D.setColor(Colour.BORDE_SCROLL.getColor());
        graphics2D.drawRoundRect(x, y, width, height, 10, 10);
        graphics2D.dispose();
    }

    @Override
    protected void setThumbBounds(int x, int y, int width, int height) {
        super.setThumbBounds(x, y, width, height);
        sp.repaint();
    }

    /**
     * Botón de incremento y decremento del ScrollBar
     */
    private static class InvisibleScrollBarButton extends JButton {
        /**
         * Ocultar el botón de incremento y decremento en el ScrollBar
         */
        InvisibleScrollBarButton() {
            setOpaque(false);
            setFocusable(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setBorder(BorderFactory.createEmptyBorder());
        }
    }
}