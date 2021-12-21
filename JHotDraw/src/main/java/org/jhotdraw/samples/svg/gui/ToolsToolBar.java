/*
 * @(#)DrawToolsPane.java  2.0  2008-04-06
 *
 * Copyright (c) 2007-2008 by the original authors of JHotDraw
 * and all its contributors.
 * All rights reserved.
 *
 * The copyright of this software is owned by the authors and
 * contributors of the JHotDraw project ("the copyright holders").
 * You may not use, copy or modify this software, except in
 * accordance with the license agreement you entered into with
 * the copyright holders. For details see accompanying license terms.
 */
package org.jhotdraw.samples.svg.gui;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.MissingResourceException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jhotdraw.app.action.DuplicateAction;
import org.jhotdraw.draw.*;
import static org.jhotdraw.draw.AttributeKeys.CLOSED;
import org.jhotdraw.draw.action.*;
import org.jhotdraw.gui.plaf.palette.PaletteButtonUI;
import org.jhotdraw.samples.svg.PathTool;
import org.jhotdraw.samples.svg.SVGCreateFromFileTool;
import org.jhotdraw.samples.svg.action.CombineAction;
import org.jhotdraw.samples.svg.action.SplitAction;
import org.jhotdraw.samples.svg.figures.*;
import org.jhotdraw.util.ResourceBundleUtil;

/**
 * DrawToolsPane.
 *
 * @author Werner Randelshofer
 * @version 2.0 2008-04-06 Reworked.
 * <br>1.0 May 1, 2007 Created.
 */
public class ToolsToolBar extends AbstractToolBar {
    
    private static final int DISPLAY_COMPONENT = 1;
    private ResourceBundleUtil labels;
    private final static Action SEPARATOR = null;
    private static final int DEFAULT_DISCLOSURE_STATE = 1;
    private static final String NAME_ID = "tools";
    /**
     * Creates new instance.
     */
    public ToolsToolBar() {
        labels = ResourceBundleUtil.getBundle("org.jhotdraw.samples.svg.Labels");
        setName(labels.getString("tools.toolbar"));   
    }

    @Override
    protected JComponent createDisclosedComponent(int state) {
      JPanel panel = null;
      if(state == DISPLAY_COMPONENT)
        panel = getComponentDisplayed(panel);  
      return panel;
    }

    private JPanel getComponentDisplayed(JPanel p) throws MissingResourceException { 
        p = new JPanel();
        setToolBarContainer(p);
      
        
        installSelectionToolButton(p);
        installRectangleButton(createNonDefaultAttributes(),  p);
        installEllipseButton(createNonDefaultAttributes(), p);
        
        installPolygonButton(createNonDefaultAttributes(), p);
        installLineButton(createOpenShapeAttributes(),  p);
        installScribbleButton(createOpenShapeAttributes(), p);
      
        installCreateTextButton(setTextAttributes(), p); 
        installTextAreaButton(setTextAttributes(),  p);
        installAddImageButton(setAddImageAttributes(), p);
        
        return p;
    }

    private void setToolBarContainer(JPanel p) {
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(5, 5, 5, 8));
        p.setLayout(new GridBagLayout());
    }

    private void installSelectionToolButton(JPanel p) {
        AbstractButton btn;
        
        // ----------------------------------------------------
        btn = ButtonFactory.addSelectionToolTo(this, editor,
                ButtonFactory.createDrawingActions(editor),
                createSelectionActions(editor));
        
        btn.setUI((PaletteButtonUI) PaletteButtonUI.createUI(btn));
        
        p.add(btn, createGridBagConstraints(0, 0));
        labels.configureToolBarButton(btn, "selectionTool");
    }

    private void installRectangleButton(HashMap<AttributeKey, Object> attributes, JPanel p) {
        AbstractButton btn;
        CreationTool creationTool;
        // ----------------------------------------------------
        btn = ButtonFactory.addToolTo(this, editor, creationTool = new CreationTool(new SVGRectFigure(), attributes), "createRectangle", labels);
        creationTool.setToolDoneAfterCreation(false);
        
        btn.setUI((PaletteButtonUI) PaletteButtonUI.createUI(btn));
        
        p.add(btn,createGridBagConstraints(0, 1 ,new Insets(3, 0, 0, 0)));
    }

    private void installEllipseButton(HashMap<AttributeKey, Object> attributes, JPanel p) {
        
        AbstractButton btn;
        CreationTool creationTool;
        GridBagConstraints gbc;
        
        btn = ButtonFactory.addToolTo(this, editor, creationTool = new CreationTool(new SVGEllipseFigure(), attributes), "createEllipse", labels);
        creationTool.setToolDoneAfterCreation(false);
        btn.setUI((PaletteButtonUI) PaletteButtonUI.createUI(btn));
       
        p.add(btn, createGridBagConstraints(1, 1, new Insets(3, 3, 0, 0)));
    }

    private HashMap<AttributeKey, Object> createNonDefaultAttributes() {
        HashMap<AttributeKey, Object> attributes;
        attributes = new HashMap<AttributeKey, Object>();
        // ----------------------------------------------------
        return attributes;
    }

    private void installPolygonButton(HashMap<AttributeKey, Object> attributes, JPanel p) {
        
        AbstractButton btn;
        PathTool pathTool;
        GridBagConstraints gbc;
        
        btn = ButtonFactory.addToolTo(this, editor, pathTool = new PathTool(new SVGPathFigure(), new SVGBezierFigure(true), attributes), "createPolygon", labels);
        pathTool.setToolDoneAfterCreation(false);
        btn.setUI((PaletteButtonUI) PaletteButtonUI.createUI(btn));
        
        
        p.add(btn, createGridBagConstraints(2, 1, new Insets(3, 3, 0, 0)));
    }

    private void installScribbleButton(HashMap<AttributeKey, Object> attributes, JPanel p) {
        
        AbstractButton btn;
        PathTool pathTool;
        GridBagConstraints gbc;
        
        btn = ButtonFactory.addToolTo(this, editor, pathTool = new PathTool(new SVGPathFigure(), new SVGBezierFigure(false), attributes), "createScribble", labels);
        pathTool.setToolDoneAfterCreation(false);
        btn.setUI((PaletteButtonUI) PaletteButtonUI.createUI(btn));
        
        p.add(btn, createGridBagConstraints(2, 0, new Insets(0, 3, 0, 0)));
    }

    private void installLineButton(HashMap<AttributeKey, Object> attributes, JPanel p) {
        
        AbstractButton btn;
        CreationTool creationTool;
        GridBagConstraints gbc;
        
        btn = ButtonFactory.addToolTo(this, editor, creationTool = new CreationTool(new SVGPathFigure(), attributes), "createLine", labels);
        creationTool.setToolDoneAfterCreation(false);
        btn.setUI((PaletteButtonUI) PaletteButtonUI.createUI(btn));
      
        p.add(btn, createGridBagConstraints(1, 0, new Insets(0, 3, 0, 0)));
    }

    private HashMap<AttributeKey, Object> createOpenShapeAttributes() {
        HashMap<AttributeKey, Object> attributes = new HashMap<>();
        attributes.put(AttributeKeys.FILL_COLOR, null);
        attributes.put(CLOSED, false);
        return attributes;
    }

    private void installCreateTextButton(HashMap<AttributeKey, Object> attributes, JPanel p) {
        
        AbstractButton btn;
        TextCreationTool textTool;
        
        
        btn = ButtonFactory.addToolTo(this, editor, textTool = new TextCreationTool(new SVGTextFigure(), attributes), "createText", labels);
        textTool.setToolDoneAfterCreation(true);
        btn.setName("createText");
        btn.setUI((PaletteButtonUI) PaletteButtonUI.createUI(btn));
        
        p.add(btn, createGridBagConstraints(0, 2, new Insets(3, 0, 0, 0)));
    }

    private void installTextAreaButton(HashMap<AttributeKey, Object> attributes, JPanel p) {
        
        TextAreaCreationTool textAreaTool;
        AbstractButton btn;
        GridBagConstraints gbc;
        
        textAreaTool = new TextAreaCreationTool(new SVGTextAreaFigure(), attributes);
        textAreaTool.setRubberbandColor(Color.BLACK);
        textAreaTool.setToolDoneAfterCreation(true);
        btn = ButtonFactory.addToolTo(this, editor, textAreaTool, "createTextArea", labels);
        btn.setUI((PaletteButtonUI) PaletteButtonUI.createUI(btn));
        
        p.add(btn, createGridBagConstraints(1, 2, new Insets(3, 3, 0, 0)));
    }

    private HashMap<AttributeKey, Object> setTextAttributes() {
        HashMap<AttributeKey, Object> attributes = new HashMap<>();
        attributes.put(AttributeKeys.FILL_COLOR, Color.black);
        attributes.put(AttributeKeys.STROKE_COLOR, null);
        return attributes;
    }

    private void installAddImageButton(HashMap<AttributeKey, Object> attributes, JPanel p) {
        
        AbstractButton btn;
        SVGCreateFromFileTool imageTool;
        GridBagConstraints gbc;
        
        btn = ButtonFactory.addToolTo(this, editor, imageTool = new SVGCreateFromFileTool(new SVGImageFigure(), new SVGGroupFigure(), attributes), "createImage", labels);
        imageTool.setToolDoneAfterCreation(true);
        imageTool.setUseFileDialog(true);
        btn.setUI((PaletteButtonUI) PaletteButtonUI.createUI(btn));
        
        p.add(btn, createGridBagConstraints(2, 2, new Insets(3, 3, 0, 0)));
    }

    private HashMap<AttributeKey, Object> setAddImageAttributes() {
        HashMap<AttributeKey, Object> attributes = new HashMap<AttributeKey, Object>();
        attributes.put(AttributeKeys.FILL_COLOR, null);
        attributes.put(AttributeKeys.STROKE_COLOR, null);
        return attributes;
    }
    
    
    
    public static Collection<Action> createSelectionActions(DrawingEditor editor) {
        LinkedList<Action> a = new LinkedList<>(); // Actions should be singletons
        a.add(new DuplicateAction());

        a.add(SEPARATOR); // separator

        a.add(GroupAction.create(editor));
        a.add(UngroupAction.create(editor));
        a.add(CombineAction.create(editor));
        a.add(SplitAction.create(editor));

        a.add(SEPARATOR); // separator

        a.add(BringToFrontAction.create(editor));
        a.add(SendToBackAction.create(editor));

        return a;
    }

    @Override
    protected String getID() {
        return NAME_ID;
    }

     @Override
    protected int getDefaultDisclosureState() {
        return DEFAULT_DISCLOSURE_STATE;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
