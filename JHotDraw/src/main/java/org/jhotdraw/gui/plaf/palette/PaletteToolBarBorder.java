/*
 * @(#)PaletteToolBarBorder.java  1.2  2008-05-18
 *
 * Copyright (c) 2008 by the original authors of JHotDraw
 * and all its contributors.
 * All rights reserved.
 *
 * The copyright of this software is owned by the authors and  
 * contributors of the JHotDraw project ("the copyright holders").  
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * the copyright holders. For details see accompanying license terms. 
 */
package org.jhotdraw.gui.plaf.palette;

import org.apache.batik.ext.awt.LinearGradientPaint;
import org.apache.batik.ext.awt.MultipleGradientPaint;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * PaletteToolBarBorder.
 *
 * @author  Werner Randelshofer
 * @version 1.2 2008-05-18 Added method getDragInsets. 
 * <br>1.1 2005-12-18 Tweaked insets.
 * <br>1.0.4 2005-12-09 Inner class UIResource added.
 * <br>1.0.3 2005-09-10 Dont' implement UIResource.
 * <br>1.0.2 2005-05-28 Fixed class cast exceptions in methods paintBorder
 * and getBorderInsets.
 * <br>1.0.1 2005-04-21 Fixed insets.
 * <br>1.0  2005-03-30  Created.
 */
public class PaletteToolBarBorder
        extends AbstractBorder
        implements SwingConstants {

    private static final  float[] enabledStops = new float[]{0f, 0.5f, 1f};
    private static final Color[] enabledStopColors = new Color[]{new Color(0xf8f8f8), new Color(0xc8c8c8), new Color(0xf8f8f8)};
   
    private static final int DEFAULT_ICON_GAP = 2;
    private static final String CLIP_STRING = "…";
    private final int barW = 18;
    private int barH = 0;
    private final int barX = 0;
    private final int barY = 0;
    
    
    private void setToolBarHeight(int height){
        barH = height;
    }


    private void setBorderStroke(int width, Graphics2D g){
          g.drawRect(barX, barY, barW - width, barH - width);
          g.drawRect(barX, barY, barW, barH);
    }

    @Override
    public void paintBorder(Component component, Graphics gr, int x, int y, int w, int h) {
        Graphics2D g = (Graphics2D) gr;
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        JToolBar c = (JToolBar) component;  
        setToolBarHeight(h);
        setBorderColor(g);
        setBorderStroke(1, g);    
        paintToolBarBgd(enabledStops, enabledStopColors, g);
        g.fillRect(barX, barY, barW-1, barH-2);
       
        Icon icon = (Icon) c.getClientProperty(PaletteToolBarUI.TOOLBAR_ICON_PROPERTY);
        int textIconGap = (c.getClientProperty(PaletteToolBarUI.TOOLBAR_ICON_PROPERTY) instanceof Integer) ? 
        (Integer) c.getClientProperty(PaletteToolBarUI.TOOLBAR_ICON_PROPERTY) : DEFAULT_ICON_GAP;
        
         setToolBarTitle(c, g, icon, textIconGap);
    }
  

    private void setToolBarTitle(JToolBar c, Graphics2D g, Icon icon, int textIconGap) {
        String theTitle = c.getName();
        if (theTitle != null) {
            FontMetrics fm = g.getFontMetrics();
            int titleW;
            titleW = barH - TITLE_INTERSPACE;
            
            if (icon != null) {
                titleW -= icon.getIconHeight() + textIconGap;
            }
            setTitleOrientation(g, fm, titleW, clippedText(theTitle, fm, titleW));
        }
    }

    private void setTitleOrientation(Graphics2D g, FontMetrics fm, int titleW, String theTitle) {
        AffineTransform savedTransform = g.getTransform();
        AffineTransform t = g.getTransform();
        t.rotate(Math.PI / -2d, barX + 2 + fm.getAscent(), titleW + 4);
        g.setTransform(t);
        
        g.setColor(Color.black);
        g.drawString(theTitle, barX + 2 + fm.getAscent(), titleW + 4);
        g.setTransform(savedTransform);
    }
    private static final int TITLE_INTERSPACE = 8;

    private void setBorderColor(Graphics2D g) {
        int borderColor = 0xffa5a5a5;
        
        g.setColor(new Color(borderColor, true));
    }

    private void paintToolBarBgd(float[] stops, Color[] stopColors, Graphics2D g) {
        LinearGradientPaint lgp = new LinearGradientPaint(
                new Point2D.Float(1, 1), new Point2D.Float(19, 1),
                stops, stopColors,
                MultipleGradientPaint.REPEAT,
                MultipleGradientPaint.LINEAR_RGB);
        g.setPaint(lgp);
    }
    
    @Override
    public Insets getBorderInsets(Component c) {
        return getBorderInsets(c, new Insets(0, 0, 0, 0));
    }
    
    /**
     * These insets are used by PaletteToolBarUI, to determine if
     * the toolbar should be dragged.
     * 
     * @param c JToolBar.
     * @return Return drag insets.
     */
    
    //TU MOGĘ NAPRAWIĆ TO CO SIĘ PSUJE PRZY WYSWIETLANIU DRAG INSETS
    
    public Insets getDragInsets(Component c) {
        return new Insets(0,18,0,0);
    }
   

    @Override
    public Insets getBorderInsets(Component component, Insets newInsets) {
    
        JComponent c = (JComponent) component;
        if (c.getClientProperty(PaletteToolBarUI.TOOLBAR_INSETS_OVERRIDE_PROPERTY) instanceof Insets) {
            Insets override = (Insets) c.getClientProperty(PaletteToolBarUI.TOOLBAR_INSETS_OVERRIDE_PROPERTY);
          
        return (Insets) override.clone();
        }
        
        return  getDragInsets(c);
    }

    /**
     * Convenience method to clip the passed in text to the specified
     * size.
     */
    
    private String clippedText(String text, FontMetrics fm, int availTextWidth) {
        boolean thereIsNoTitle = (text == null) || (text.equals(""));
        
        if (thereIsNoTitle) {
            return "";
        }
        
        int textWidth = SwingUtilities.computeStringWidth(fm, text);
 
        if (textWidth > availTextWidth) {
            text = shortenTitle(fm, text, availTextWidth);
        }
        
        return text;
    }

    private String shortenTitle(FontMetrics fm, String text, int availTextWidth) {
        
        int totalWidth = SwingUtilities.computeStringWidth(fm, CLIP_STRING);
        boolean someSpaceLeft = totalWidth > availTextWidth; 
        int nChars = 0;
        
        for(nChars = 0; nChars < text.length()  && someSpaceLeft; nChars++) {
            totalWidth += fm.charWidth(text.charAt(nChars));
        }
        
        return text.substring(0, nChars) + CLIP_STRING;
    }

    //TO MUSI TU BYĆ ŻEBY DZIAŁAŁ PROGRAM NORMALNIE 
    public static class UIResource extends PaletteToolBarBorder implements javax.swing.plaf.UIResource {
    }

}
