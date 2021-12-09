/**
 * @(#)PaletteLookAndFeel.java  1.0  Apr 6, 2008
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

import java.awt.*;
import java.awt.event.ContainerListener;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.Handler;
import javax.swing.*;
import javax.swing.UIDefaults.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;

/**
 * A LookAndFeel for components in the palette windows of a drawing editor.
 *
 * @author Werner Randelshofer
 * @version 1.0 Apr 6, 2008 Created.
 */
public class PaletteLookAndFeel extends BasicLookAndFeel {
    
    private static  final  int FONT_12 = 12;
    private static  final int FONT_11 = 11;
    
    private static final String FONT_UI_RESOURCE = "javax.swing.plaf.FontUIResource";
    private final Object DIALOG_PLAIN_11 = new ProxyLazyFontValue(FONT_UI_RESOURCE, null, new Font("Dialog Sans", Font.PLAIN, FONT_11));
    private final Object DIALOG_PLAIN_12 = new ProxyLazyFontValue(FONT_UI_RESOURCE, null, new Font("Dialog Sans", Font.PLAIN, FONT_12));
    private final Object FIELD_PLAIN_12 = new ProxyLazyFontValue(FONT_UI_RESOURCE, null, new Font("Verdana", Font.PLAIN, FONT_12));
    private final ColorUIResource TOOLBAR_COLOR = new ColorUIResource(Color.LIGHT_GRAY);
    private final ColorUIResource TOOLBAR_COLOR_TEXT = new ColorUIResource(Color.black);
    private final ColorUIResource ERROR_INDICATOR_FGD = new ColorUIResource(0xfe4a41);
    private final ColorUIResource SELECTION_FGD = new ColorUIResource(Color.BLACK);   
    private final ColorUIResource SELECTION_BGD = new ColorUIResource(0xb5d5ff);
     
    private final Object BTN_BORDER = new BackdropBorder.UIResource(new PaletteButtonBorder());
    private final Object TXT_BORDER = new BackdropBorder.UIResource(new PaletteTextComponentBorder());
    private final InsetsUIResource ZERO_INSETS = new InsetsUIResource(0, 0, 0, 0);
    private final Object RIPPON_BORDER = new UIDefaults.ProxyLazyValue("javax.swing.border.MatteBorder", new Object[] {new Insets(1,0,0,0), new Color(0xa5a5a5)});
    private final Object SCROLLPANE_BORDER = new UIDefaults.ProxyLazyValue("javax.swing.border.MatteBorder", new Object[] {new Insets(1,1,1,1), new Color(0xa5a5a5)});
    private final Object HORIZONTAL_SLIDER_SIZE =  new DimensionUIResource(100, 20);
    private final Object VERTICAL_SLIDER_SIZE =  new DimensionUIResource(20, 100);
    private final Object PALETTE_TOOLBAR_BORDER = new UIDefaults.ProxyLazyValue("org.jhotdraw.gui.plaf.palette.PaletteToolBarBorder$UIResource");
    
  
   
    private static PaletteLookAndFeel instance;
    /**
     * Cached defaults.
     */
    private UIDefaults cachedDefaults;

    private PaletteLookAndFeel(){
       // initComponentDefaults(cachedDefaults); 
    }
    
    public static PaletteLookAndFeel getInstance() {
        if (instance == null) {
            instance = new PaletteLookAndFeel();
        }
        
        return instance;
    }

    @Override
    public String getName() {
        return "Palette Look and Feel";
    }

    @Override
    public String getID() {
        return "Palette";
    }

    @Override
    public String getDescription() {
        return "A look and feel for palette components";
    }

    @Override
    public boolean isNativeLookAndFeel() {
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel() {
        return true;
    }
    
    
    

    public UIDefaults getCachedDefaults() {
        if (cachedDefaults == null) {
            cachedDefaults = getDefaults();
          //  this.initComponentDefaults(cachedDefaults);
        }
        return cachedDefaults;
    }


    //ten kod jest powtórzeniem tego co juz zostało zaimpleementowane przez UIdefaults 
    public Object get(Object key) {
        return getCachedDefaults().get(key);
    }

    public Font getFont(String key) {
        return (Font) get(key);
    }

    public Border getBorder(String key) {
        return (Border) get(key);
    }

    public Color getColor(String key) {
        return (Color) get(key);
    }

    public Insets getInsets(String key) {
        return (Insets) get(key);
    }
    public boolean getBoolean(String key) {
        return ((Boolean) get(key));
    }

    /**
     * Convenience method for initializing a components foreground
     * background and font properties with values from the current
     * defaults table.  The properties are only set if the current
     * value is either null or a UIResource.
     * 
     * @param c the target component for installing default color/font properties
     * @param defaultBgName the key for the default background
     * @param defaultFgName the key for the default foreground
     * @param defaultFontName the key for the default font
     * 
     * @see #installColors
     * @see UIManager#getColor
     * @see UIManager#getFont
     */
    
    //SPECULATIVE GENERALITY
    public static void installColorsAndFont(JComponent c,
            String defaultBgName,
            String defaultFgName,
            String defaultFontName) {
      
        installFont(c, defaultFontName);
        installColors(c, defaultBgName, defaultFgName);
    }
    
    
    public static void installFont(JComponent c, String defaultFontName){
        if (c.getFont() == null ||c.getFont() instanceof UIResource) {
            c.setFont(getInstance().getFont(defaultFontName)); 
        }
    }
   
    public static void installBorder(JComponent c, String defaultBorderName) {
        if (c.getBorder() == null || c.getBorder() instanceof UIResource) {
            c.setBorder(getInstance().getBorder(defaultBorderName));
        }
    }

    public static void installColors(JComponent c, String defaultBgName, String defaultFgName) { 
        installBgColor(c, defaultBgName);
        installFgColor(c, defaultFgName);
    }

    private static void installFgColor(JComponent c, String defaultFgName) {
        if (c.getForeground() == null || c.getForeground() instanceof UIResource) {
            c.setForeground(getInstance().getColor(defaultFgName));
        }
    }

    private static void installBgColor(JComponent c, String defaultBgName) {
        if (c.getBackground() == null || c.getBackground() instanceof UIResource) {
            c.setBackground(getInstance().getColor(defaultBgName));
        }
    }

    
    private class ProxyLazyFontValue extends ProxyLazyValue{
        private Font font;
        ProxyLazyFontValue(String c, String m, Font ft) {
           super(c,m);
           this.font = ft;
        }
    }
    
    @Override
    protected  void initComponentDefaults(UIDefaults table) {
        super.initComponentDefaults(table);
        table.addResourceBundle("org.jhotdraw.gui.Labels");

        Object[] defaults = {
            // *** Fonts
            "SmallSystemFont", DIALOG_PLAIN_11,
             "Button.font", DIALOG_PLAIN_12,

            // *** Buttons
           
            "Button.background", TOOLBAR_COLOR,
            "Button.foreground", TOOLBAR_COLOR_TEXT,
            "Button.border", BTN_BORDER,
            "Button.margin", ZERO_INSETS,
            // *** FontChooser
            /*
            "Button.background", control,
            "Button.foreground", controlText,
            "Button.border", buttonBorder,
            "Button.margin", zeroInsets,
            */
            // *** FormattedTextField
            "FormattedTextField.font", FIELD_PLAIN_12,
            "FormattedTextField.background", TOOLBAR_COLOR,
            "FormattedTextField.foreground", TOOLBAR_COLOR_TEXT,
            "FormattedTextField.border", TXT_BORDER,
            "FormattedTextField.margin", ZERO_INSETS,
            "FormattedTextField.opaque", Boolean.TRUE,
            "FormattedTextField.errorIndicatorForeground", ERROR_INDICATOR_FGD,
            "FormattedTextField.selectionBackground", SELECTION_BGD,
            "FormattedTextField.selectionForeground",  SELECTION_FGD,
            // *** Labels
            "Label.font", DIALOG_PLAIN_12,
            // *** Ribbons
            "Ribbon.border", RIPPON_BORDER, //
            // *** ScrollPane
            "ScrollPane.border",SCROLLPANE_BORDER , //
            // *** Slider
            "Slider.background", TOOLBAR_COLOR,
            "Slider.foreground", TOOLBAR_COLOR_TEXT,
            "Slider.horizontalSize", HORIZONTAL_SLIDER_SIZE,
            "Slider.verticalSize",VERTICAL_SLIDER_SIZE,
            // *** TextArea
            "TextArea.selectionBackground", SELECTION_BGD,
            "TextArea.selectionForeground",  SELECTION_FGD,
            // *** TextField
            "TextField.font", FIELD_PLAIN_12,
            "TextField.background", TOOLBAR_COLOR,
            "TextField.foreground", TOOLBAR_COLOR_TEXT,
            "TextField.border", TXT_BORDER,
            "TextField.margin", ZERO_INSETS,
            "TextField.opaque", Boolean.TRUE,
            "TextField.selectionBackground", SELECTION_BGD,
            "TextField.selectionForeground",  SELECTION_FGD,
            // *** ToolBar
            "ToolBar.font", DIALOG_PLAIN_12,
            "ToolBar.background", TOOLBAR_COLOR,
            "ToolBar.foreground", TOOLBAR_COLOR_TEXT,
            "ToolBar.dockingBackground", TOOLBAR_COLOR,
            //	    "ToolBar.dockingForeground", red,
            "ToolBar.floatingBackground", TOOLBAR_COLOR,
            //	    "ToolBar.floatingForeground", darkGray,
            //	    "ToolBar.border", etchedBorder,
            "ToolBar.border",PALETTE_TOOLBAR_BORDER, //
            //	    "ToolBar.separatorSize", toolBarSeparatorSize,
        };

        table.putDefaults(defaults);
    }
    
    /**
     * Returns the ui that is of type <code>klass</code>, or null if
     * one can not be found.
     */
    static Object getUIOfType(ComponentUI ui, Class klass) {
        if (klass.isInstance(ui)) {
            return ui;
        }
        return null;
    }
}
