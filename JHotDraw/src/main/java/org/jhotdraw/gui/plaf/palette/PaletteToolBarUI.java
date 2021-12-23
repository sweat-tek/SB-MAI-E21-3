/**
 * @(#)PaletteToolBarUI.java  1.1  2008-05-18
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

import com.sun.istack.internal.NotNull;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import java.beans.*;
import java.util.Objects;


import javax.swing.plaf.*;


/**
 * ToolBarUI for palette components.
 * <p>
 * This UI differs from BasicToolBarUI, in that the component holding the toolbar
 * is supposed to use BoxLayout instead of BorderLayout. This allows to have
 * multiple toolbars in the same component. The toolbars can be reorderd in the
 * component, but they are not allowed to float in their own floating window.
 * <p>
 * The JToolBar starts dragging only, if the drag starts over the insets of
 * its border.
 * 
 * @author Werner Randelshofer
 * @version 1.1 2008-05-18 Start dragging only over border insets. 
 * <br>1.0 Apr 6, 2008 Created.
 */
public class PaletteToolBarUI extends ComponentUI implements SwingConstants {

    
    public  JToolBar toolBar;
    
    private boolean floating;
    //private int floatingX;
    //private int floatingY;
    private RootPaneContainer floatingToolBar;
    public DragWindow dragWindow;
    private Container dockingSource;
    
  
    
  
    private final static boolean isFloatingAllowed = false; 
    

    protected int focusedCompIndex = -1;
    protected Color dockingColor = null;
    protected Color floatingColor = null;
    protected Color dockingBorderColor = null;
    protected Color floatingBorderColor = null;
    
    protected MouseInputListener dockingListener;
    protected PropertyChangeListener propertyListener;
    protected ContainerListener toolBarContListener;
    
    protected FocusListener toolBarFocusListener;
    private Handler handler;
    
    //SHOULD BE FIANL STATIC VARIABLE (CONSTANT)
    protected Integer constraintBeforeFloating = 0;

    // Rollover button implementation.
    private static String ROLLOVER = "JToolBar.isRollover";
    private boolean rolloverBorders = false;
      
    private static final String ORIENTATION = "orientation";
    private static final String LOOK_AND_FEEL = "lookAndFeel";
    /*private*/ static String IS_DIVIDER_DRAWN = "Palette.ToolBar.isDividerDrawn";
    // client properties
    /* The value of this client property must be an Icon or null. */
    public final static String TOOLBAR_ICON_PROPERTY = "Palette.ToolBar.icon";
    public final static String TOOLBAR_FLOATING_FGD ="ToolBar.floatingForeground";
     private static final String TOOLBAR_DOCKING_FGD = "ToolBar.dockingForeground";
    private static final String TOOLBAR_FLOATING_BGD = "ToolBar.floatingBackground";
    private static final String TOOLBAR_DOCKING_BGD = "ToolBar.dockingBackground";
    
    /* The value of this client property must be an Integer or null, if it is null, the value 2 is used. */
    public final static String TOOLBAR_TEXT_ICON_GAP_PROPERTY = "Palette.ToolBar.textIconGap";
    /* The value of this client property must be an Insets object or null, if it is null, the insets of the toolbar border are used */
    public final static String TOOLBAR_INSETS_OVERRIDE_PROPERTY = "Palette.ToolBar.insetsOverride";
    private static String FOCUSED_COMP_INDEX = "JToolBar.focusedCompIndex"; //Should be defined as a final
  
    private static PaletteToolBarBorder border = new PaletteToolBarBorder();
    //private static RolloverStateContext context;
   
    public static ComponentUI createUI(JComponent c) {
        return new PaletteToolBarUI();
    }
    

    public void installUI(JComponent c) {
        toolBar = (JToolBar) c;
        
        // Set defaults
        installDefaults();
        installComponents();
        installListeners();
        installKeyboardActions();

        dragWindow = null;
        
        floating = false; 
        
        floatingToolBar = null;
        dragWindow = null;
        dockingSource =null;
        setOrientation(toolBar.getOrientation());
        LookAndFeel.installProperty(c, "opaque", Boolean.TRUE);

        if (c.getClientProperty(FOCUSED_COMP_INDEX) != null) {
            focusedCompIndex =  (Integer) c.getClientProperty(FOCUSED_COMP_INDEX);
        }
    }

    public void uninstallUI(JComponent c) {

        // Clear defaults

        uninstallDefaults();
        uninstallComponents();
        uninstallListeners();
        uninstallKeyboardActions();

        // Clear instance vars
        if (isFloating()) {
            setFloating(false, null);
        }
        floatingToolBar = null;
        dragWindow = null;
        dockingSource =null;

        c.putClientProperty(FOCUSED_COMP_INDEX, (Integer)focusedCompIndex);
    }

    private void installDefaults() {
       
        PaletteLookAndFeel.installBorder(toolBar, "ToolBar.border");
        PaletteLookAndFeel.installFont(toolBar,  "ToolBar.font");
        PaletteLookAndFeel.installColors(toolBar,  "ToolBar.background",  "ToolBar.foreground");
        
        // Toolbar specific defaults
        installDockingColor();
        installFloatingColor();
        installDockingBorderColor();
        installFloatingBorderColor();  
        border.setRolloverContext(new RolloverBorderState(border.getContext()));  
        border.installRolloverContext(toolBar);

    }
    
    
    public PropertyChangeListener getPropertyChangeListener(){
        return propertyListener;
    }
    
    public int getConstraintBeforeFloating(){
        return constraintBeforeFloating;
    }
    
     /**
     * Sets the flag for enabling rollover borders on the toolbar and it will
     * also install the appropriate border depending on the state of the flag.
     *
     * @param rollover if true, rollover borders are installed.
     *	      Otherwise non-rollover borders are installed
     * @see #isRolloverBorders
     * @since 1.4
     */
   

    private void installFloatingBorderColor() {
        if (floatingBorderColor == null ||
                floatingBorderColor instanceof UIResource) {
            floatingBorderColor = UIManager.getColor(TOOLBAR_FLOATING_FGD);
            
            // ToolBar rollover button borders
        }
    }

    private void installDockingBorderColor() {
        if (dockingBorderColor == null ||
                dockingBorderColor instanceof UIResource) {
            dockingBorderColor = UIManager.getColor(TOOLBAR_DOCKING_FGD);
        }
    }
    
   

    private void installFloatingColor() {
        if (floatingColor == null || floatingColor instanceof UIResource) {
            floatingColor = UIManager.getColor(TOOLBAR_FLOATING_BGD);
        }
    }
   

    private void installDockingColor() {
        if (dockingColor == null || dockingColor instanceof UIResource) {
            dockingColor = UIManager.getColor(TOOLBAR_DOCKING_BGD);
        }
    }
   

    protected void uninstallDefaults() {
        LookAndFeel.uninstallBorder(toolBar);  // ustawiam Bordery na nulle
        dockingColor = null;
        floatingColor = null;
        dockingBorderColor = null;
        floatingBorderColor = null;
        border.setRolloverContext(new NormalBorderState(border.getContext()));
        border.installRolloverContext(toolBar);
    }

    //REFUSED BEQUEST
    protected void installComponents() {
        
    }
    
    //REFUSED BEQUEST
    protected void uninstallComponents() {
    }
    
    

    protected void installListeners() {
        dockingListener = createDockingListener();

        if (dockingListener != null) {
            toolBar.addMouseMotionListener(dockingListener);
            toolBar.addMouseListener(dockingListener);
        }

        propertyListener = createPropertyListener(); 

        if (propertyListener != null) {
            toolBar.addPropertyChangeListener(propertyListener);
        }

        toolBarContListener = createToolBarContListener();
        if (toolBarContListener != null) {
            toolBar.addContainerListener(toolBarContListener);
        }

        toolBarFocusListener = createToolBarFocusListener();

        if (toolBarFocusListener != null) {
            // Put focus listener on all components in toolbar
            Component[] components = toolBar.getComponents();

            for (int i = 0; i < components.length; ++i) {
                components[i].addFocusListener(toolBarFocusListener);
            }
        }
    }

    protected void uninstallListeners() {
        if (dockingListener != null) {
            toolBar.removeMouseMotionListener(dockingListener);
            toolBar.removeMouseListener(dockingListener);

            dockingListener = null;
        }

        if (propertyListener != null) {
            toolBar.removePropertyChangeListener(propertyListener);
            propertyListener = null;  

        }

        if (toolBarContListener != null) {
            toolBar.removeContainerListener(toolBarContListener);
            toolBarContListener = null;
        }

        if (toolBarFocusListener != null) {
            // Remove focus listener from all components in toolbar
            Component[] components = toolBar.getComponents();

            for (int i = 0; i < components.length; ++i) {
                components[i].removeFocusListener(toolBarFocusListener);
            }

            toolBarFocusListener = null;
        }
        handler = null;
    }

    protected void installKeyboardActions() {
        InputMap km = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        SwingUtilities.replaceUIInputMap(toolBar, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                km);

        PaletteLazyActionMap.installLazyActionMap(toolBar, PaletteToolBarUI.class,
                "ToolBar.actionMap");
    }
    
     InputMap getInputMap(int condition) {
        if (condition == JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            return (InputMap) PaletteLookAndFeel.getInstance().get(
                    "ToolBar.ancestorInputMap");
        }
        return null;
    }
    
     protected void uninstallKeyboardActions() {
        SwingUtilities.replaceUIActionMap(toolBar, null);
        SwingUtilities.replaceUIInputMap(toolBar, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                null);
    }
     
    static class ToolBarDialog extends JDialog {
        private Frame owner;
        private String title;
        private boolean modal;
        
        
        public Frame getFrame(){
            return owner;
        }
        
        public String getTitle(){
            return title;
        }
        
        public boolean isModal(){
            return modal;
        }
        
      
        

       public ToolBarDialog(Frame owner, String title, boolean modal) {
          super(owner, title, modal);  
       }

       public ToolBarDialog(Dialog owner, String title, boolean modal) {
          super(owner, title, modal);
         
       }
       
       @Override
       public boolean equals(Object toolBarD){
           ToolBarDialog toolBarDialog = (ToolBarDialog) toolBarD;
           return(isModal() == toolBarDialog.isModal() && isResizable() == toolBarDialog.isResizable() && getRootPane().getName().equals(toolBarDialog.getRootPane().getName()));
       }

       // Override createRootPane() to automatically resize
       // the frame when contents change
       public JRootPane createRootPane() {
           JRootPane rootPane = new JRootPane() {

               private boolean packing = false;

               public void validate() {
                   super.validate();
                   if (!packing) {
                       packing = true;
                       pack();
                       packing = false;
                   }
               }
           };
           rootPane.setOpaque(true);
           return rootPane;
       }
   }
    
   public RootPaneContainer getFloatingToolBar(){
        return floatingToolBar;
   }
 
    /**
     * Creates a window which contains the toolbar after it has been
     * dragged out from its container
     * @return a <code>RootPaneContainer</code> object, containing the toolbar.
     */
    public RootPaneContainer createFloatingWindow(JToolBar toolbar) {
    

        JDialog dialog;
        Window window = SwingUtilities.getWindowAncestor(toolbar);
        if (window instanceof Frame) {
            dialog = new ToolBarDialog((Frame) window, toolbar.getName(), false);
        } else if (window instanceof Dialog) {
            dialog = new ToolBarDialog((Dialog) window, toolbar.getName(), false);
        } else {
            dialog = new ToolBarDialog((Frame) null, toolbar.getName(), false);
        }

        dialog.getRootPane().setName("ToolBar.FloatingWindow");
        dialog.setTitle(toolbar.getName());
        dialog.setResizable(false);
        WindowListener wl = createFrameListener();
        dialog.addWindowListener(wl);
        return dialog;
    }
    

    public DragWindow createDragWindow(JToolBar toolbar) {
        Window frame = null;
        if (toolbar != null) {
            Container p;
            for (p = toolbar.getParent(); p != null && !(p instanceof Window); p = p.getParent());
            if (p != null && p instanceof Window) {
                frame = (Window) p;
            }
        }
        if (floatingToolBar == null) {
            floatingToolBar = createFloatingWindow(toolbar);
        }
        if (floatingToolBar instanceof Window) {
            frame = (Window) floatingToolBar;
        }
        DragWindow w = new DragWindow(frame);
        addClientProperties(w);
        return w;
    }

    private void addClientProperties(DragWindow w) {
        if (w instanceof RootPaneContainer) {
            JRootPane rp = (w).getRootPane();
            rp.putClientProperty("Window.alpha", new Float(0.6f));
        }
    }
    

    public boolean isFloating() {
        return floating;
    }
     
    public boolean setFloating(boolean b, Point p) {            
        FloatingManager floatingManager = new FloatingManager(isFloatingAllowed, b);
        floating = floatingManager.applyStrategy(b, p); 
        return floating;
    }

    
    
    public void updateAfterFloating() {
        dockingSource.invalidate();
        
        Container dockingSourceParent = dockingSource.getParent();
        if (dockingSourceParent != null) {
            dockingSourceParent.validate();
        }
        dockingSource.repaint();
    }
    
   
    public int mapConstraintToOrientation(Object constraint) {
        int orientation = toolBar.getOrientation();

        if (constraint != null) {
            if (constraint.equals(BorderLayout.EAST) || constraint.equals(BorderLayout.WEST)) {
                orientation = JToolBar.HORIZONTAL; //WAS VERTICAL
            } else if(constraint.equals(BorderLayout.NORTH) || constraint.equals(BorderLayout.SOUTH)) { 
                orientation = JToolBar.VERTICAL; //was horizontal
                toolBar.setSize(toolBar.getHeight(), toolBar.getWidth());  
                toolBar.repaint();
            }
        }

        return orientation;
    }

    public void setOrientation(int orientation) {
        toolBar.setOrientation(orientation);

        if (dragWindow != null) {
            dragWindow.setOrientation(orientation);
        }
    }

    
    /**
     * Gets the color displayed when over a docking area
     */
    public Color getDockingColor() {
        return dockingColor;
    }

    /**
     * Sets the color displayed when over a docking area
     */
    public void setDockingColor(Color c) {
        this.dockingColor = c;
    }

    /**
     * Gets the color displayed when over a floating area
     */
    public Color getFloatingColor() {
        return floatingColor;
    }

    /**
     * Sets the color displayed when over a floating area
     */
    public void setFloatingColor(Color c) {
        this.floatingColor = c;
    }

    public boolean canDock(Component c, Point p) {
        return (p != null && !Objects.equals(getDockingConstraint(c, p), constraintBeforeFloating));
    }
    
   
    public Integer calculateConstraint() {
        if (dockingSource != null){
            LayoutManager lm = dockingSource.getLayout();
            if (lm instanceof BoxLayout) {
                for (int i = 0; i < dockingSource.getComponentCount(); i++) {
                    if (dockingSource.getComponent(i) == toolBar) {
                        return i;
                    }
                }
            }
        }
        return constraintBeforeFloating;
    }

    
    public Container getDockingSource(){
        return dockingSource;
    }
    
    private Integer getDockingConstraint(Component c, Point p) {
        if (p != null && c.contains(p)) {
            for (int i = 0; i < dockingSource.getComponentCount(); i++) {
                Component child = dockingSource.getComponent(i);
                Point childP = new Point(p.x - child.getX(), p.y - child.getY());
                if (child.contains(childP)) {
                    return Math.min(dockingSource.getComponentCount() - 1, ((childP.x <= child.getWidth()) ? i : i + 1));
                }
            }
            if (dockingSource.getComponentCount() == 0 || p.x < dockingSource.getComponent(0).getX()) {
                return 0;
            }
            return dockingSource.getComponentCount() - 1;
        }
        return constraintBeforeFloating;
    }

    
    public Point getDragPoint(Point position, Point origin){
        
        Point offset = dragWindow.getOffset();
        if (offset == null) { 
            offset = new Point(toolBar.getWidth() / 2, toolBar.getHeight() / 2);
            dragWindow.setOffset(new Point(toolBar.getWidth() / 2, toolBar.getHeight() / 2));
        }

        return new Point(origin.x + position.x - offset.x, origin.y + position.y - offset.y);
    }
    
    public void dragTo(Point position, Point origin) {
        if (toolBar.isFloatable()) {
            try {
                if (dragWindow == null) {
                    dragWindow = createDragWindow(toolBar); 
                }
        
                Point dragPoint = getDragPoint(position, origin);
                 
                
                if (dockingSource == null) {
                    dockingSource = toolBar.getParent();
                }
                
                constraintBeforeFloating = calculateConstraint(); 
                 
                
                Point comparisonPoint = getComparisonPoint(new Point(origin.x + position.x , origin.y + position.y));      
                if (canDock(dockingSource, comparisonPoint)) {
                    dragWindow.setBackground(getDockingColor());                
                    Object constraint = getDockingConstraint(dockingSource,
                            comparisonPoint);             
                    
                    
                  
                    
                    int orientation = mapConstraintToOrientation(constraint);   
                    dragWindow.setOrientation(orientation); 
                    dragWindow.setBorderColor(dockingBorderColor);
                    
                 
                } else {
                    
                    dragWindow.setBackground(getFloatingColor());
                    dragWindow.setBorderColor(floatingBorderColor);
     
                }

                dragWindow.setLocation(dragPoint.x, dragPoint.y);
                if (!dragWindow.isVisible()) {
                    dragWindow.setSize(toolBar.getWidth(), toolBar.getHeight());
                    dragWindow.setVisible(true);
                   
                }
            } catch (IllegalComponentStateException e) {
            }
        }
    }

   

    
    public void floatAt(Point position, Point origin) {

        if (dragWindow.getOffset() == null) {
            dragWindow.setOffset(position);
        }

        Point global = new Point(origin.x + position.x,origin.y + position.y);
        
        if (dockingSource == null) {
            dockingSource = toolBar.getParent();
        }
         
        Point comparisonPoint = getComparisonPoint(global);
        
        boolean isDockingPossible = canDock(dockingSource, comparisonPoint);


        setFloating(isFloatingAllowed && !isDockingPossible,(isDockingPossible) ? comparisonPoint : null );
        dragWindow.setOffset(null);
 
    }
    
    private Point getComparisonPoint(Point global) {
        dockingSource.setVisible(true);
        Point dockingPosition = dockingSource.getLocationOnScreen();
        return new Point(global.x - dockingPosition.x, global.y - dockingPosition.y);
    }

    private Handler getHandler() {
        if (handler == null) {
            handler = new Handler();
        }
        return handler;
    }

    protected ContainerListener createToolBarContListener() {
        return getHandler();
    }

    protected FocusListener createToolBarFocusListener() {
        return getHandler();
    }

    protected PropertyChangeListener createPropertyListener() {
        return getHandler();
    }

    protected MouseInputListener createDockingListener() {
        getHandler().tb = toolBar;
        return getHandler();
    }

    
    protected WindowListener createFrameListener() {
        return new FrameListener();
    }
    
    public Object getRolloverProperty() {
        Object rolloverProperty = toolBar.getClientProperty(ROLLOVER);
        return (rolloverProperty != null) ? rolloverProperty : UIManager.get(ROLLOVER);
    }

//    /**
//     * Paints the contents of the window used for dragging.
//     *
//     * @param g Graphics to paint to.
//     * @throws NullPointerException if <code>g</code> is null
//     * @since 1.5
//     */
//    

    
    private class Handler implements ContainerListener,
            FocusListener, MouseInputListener, PropertyChangeListener {
        
         public void componentRemoved(ContainerEvent evt) {}
         public void componentAdded(ContainerEvent evt) {}
        
         private ChangeStateContext ctxt = new ChangeStateContext();
    
  
        public void focusGained(FocusEvent evt) {
            Component c = evt.getComponent();
            focusedCompIndex = toolBar.getComponentIndex(c);
        }

        public void focusLost(FocusEvent evt) {
        }
      
        JToolBar tb;
        boolean isDragging = false;
        Point origin = null;
        boolean isArmed = false;

        public void mousePressed(MouseEvent evt) {
            if (!tb.isEnabled()) {
                return;
            }
            isDragging = false;
            if (evt.getSource() instanceof JToolBar) {
                JComponent c = (JComponent) evt.getSource();
                Insets insets;
                if (c.getBorder() instanceof PaletteToolBarBorder) {
                insets = ((PaletteToolBarBorder) c.getBorder()).getDragInsets(c);
                } else {
                insets = c.getInsets();
                }
                isArmed = ! (evt.getX() > insets.left && evt.getX() < c.getWidth() - insets.right &&
                        evt.getY() > insets.top && evt.getY() < c.getHeight() - insets.bottom);
            }
        }

        public void mouseReleased(MouseEvent evt) {
            if (!tb.isEnabled()) {
                return;
            }
            if (isDragging == true) {
                Point position = evt.getPoint();
                if (origin == null) {
                    origin = evt.getComponent().getLocationOnScreen();
                }
                floatAt(position, origin);
               
            }
            origin = null;
            isDragging = false;
        }
        

        public void mouseDragged(MouseEvent evt) {
            if (!tb.isEnabled()) {
                return;
            }
            if (! isArmed) {
                return;
            }
            isDragging = true;
            Point position = evt.getPoint();
            if (origin == null) {
                origin = evt.getComponent().getLocationOnScreen();
            }
            dragTo(position, origin);
            
        }

        
        public void mouseClicked(MouseEvent evt) {
        }

        public void mouseEntered(MouseEvent evt) {
        }

        public void mouseExited(MouseEvent evt) {
        }

        public void mouseMoved(MouseEvent evt) {
        }
        
        
        public void propertyChange(PropertyChangeEvent evt) {
           String propertyName = evt.getPropertyName();
           ctxt.request(propertyName, evt);   
        }
      
    }
    
    
    interface PropertyChangeState {
      void changeState(PropertyChangeEvent evt);
    }

    class NoChangeState implements PropertyChangeState{

        @Override
        public void changeState(PropertyChangeEvent evt){
            System.err.println(evt.getPropertyName());
        }
    }

    class LookAndFeelPropertyChange implements PropertyChangeState{

        @Override 
        public void changeState(PropertyChangeEvent evt){
            toolBar.updateUI();
        }
    }

    class RolloverPropertyChange implements PropertyChangeState{

        @Override
        public void changeState(PropertyChangeEvent evt){
            border.setRolloverContext(new NormalBorderState(border.getContext()));
            border.installRolloverContext(toolBar);
        }
        
        public void changeState(BorderState state){
            border.setRolloverContext(state);
            border.installRolloverContext(toolBar);
        }
    }

    class OrientationPropertyChange implements PropertyChangeState{

        @Override
        public void changeState(PropertyChangeEvent evt){
        JToolBar.Separator separator;
        Component [] components = toolBar.getComponents();
            for (int i = 0; i < components.length; ++i) {
          
                if (components[i] instanceof JToolBar.Separator ) {   
                    separator = (JToolBar.Separator) components[i];
                    setSeparatorOrientation((JToolBar.Separator) components[i], toolBar.getOrientation()); 
                    Dimension size = ((JToolBar.Separator)separator).getSeparatorSize();
                    separator.setSeparatorSize(new Dimension(size.height, size.width));  
                }
            }
        }

        private void setSeparatorOrientation(JToolBar.Separator separator, int orientation) {
            if ((orientation == JToolBar.HORIZONTAL)) {
                separator.setOrientation(JSeparator.HORIZONTAL);
            } else {
                separator.setOrientation(JSeparator.VERTICAL);
            }
        }
    }
    
    class ChangeStateContext{

        private PropertyChangeState propertyChangeState;
        private PropertyChangeEvent evt;


        void request(String name, PropertyChangeEvent evt){
            propertyChangeState = getPropertyChangeState(name);
            propertyChangeState.changeState(evt);
        }
        
      

       private PropertyChangeState  getPropertyChangeState(String name){
           if(name.equals(ROLLOVER)) return new RolloverPropertyChange();  
           if(name.equals(ORIENTATION)) return new OrientationPropertyChange();      
           if(name.equals(LOOK_AND_FEEL)) return new LookAndFeelPropertyChange();  
           return new NoChangeState();
        }

    }
     
    protected class FrameListener extends WindowAdapter {

        public void windowClosing(WindowEvent w) {     
            if (dragWindow != null) {
                dragWindow.setVisible(false); 
            } 
            setFloating(false, null);    
        }
        
        
        
    } 
    /**
     * This class should be treated as a &quot;protected&quot; inner class.
     * Instantiate it only within subclasses of PaletteToolBarUI.
     */
   public class DockingListener implements MouseInputListener {
        // NOTE: This class exists only for backward compatability. All
        // its functionality has been moved into Handler. If you need to add
        // new functionality add it to the Handler, but make sure this      
        // class calls into the Handler.

        protected JToolBar toolBar;
        protected boolean isDragging = false;
        protected Point origin = null;

        public DockingListener(JToolBar t) {
            this.toolBar = t;
            getHandler().tb = t;
        }

        public void mouseClicked(MouseEvent e) {
            getHandler().mouseClicked(e);
        }

        public void mousePressed(MouseEvent e) {
            getHandler().tb = toolBar;
            getHandler().mousePressed(e);
            isDragging = getHandler().isDragging;
        }

        public void mouseReleased(MouseEvent e) {
            getHandler().tb = toolBar;
            getHandler().isDragging = isDragging;
            getHandler().origin = origin;
            getHandler().mouseReleased(e);
            isDragging = getHandler().isDragging;
            origin = getHandler().origin;
        }

        public void mouseEntered(MouseEvent e) {
            getHandler().mouseEntered(e);
        }

        public void mouseExited(MouseEvent e) {
            getHandler().mouseExited(e);
        }

        public void mouseDragged(MouseEvent e) {
            getHandler().tb = toolBar;
            getHandler().origin = origin;
            getHandler().mouseDragged(e);
            isDragging = getHandler().isDragging;
            origin = getHandler().origin;
        }

        public void mouseMoved(MouseEvent e) {
            getHandler().mouseMoved(e);
        }
    }
   
    public int getOrientation(){
         return toolBar.getOrientation();
    }
        
    public class DragWindow extends JWindow {
        private Color borderColor = Color.gray;
        private int orientation = toolBar.getOrientation();
        private Point offset; // offset of the mouse cursor inside the DragWindow
       
        
        private void paintDragWindow(@NotNull Graphics g) {
            toolBar.paint(g);
            g.setColor(dragWindow.getBorderColor());
            g.drawRect(0, 0, dragWindow.getWidth(), dragWindow.getHeight());
  
        }

        DragWindow(Window w) {
            super(w);
          
            getContentPane().add(new JPanel() {
                
                @Override
                public void paintComponent(Graphics g) {
                    paintDragWindow(g);
                }
            });
        }
        
        public int getOrientation(){
            return orientation;
        }
        
        
        public void setOrientation(int o) {
            if (isShowing() && o != orientation) {
              
                orientation = o;
                
                Dimension size = getSize();
                setSize(new Dimension(size.height, size.width));
                
                
                if (offset != null) {
                    if (toolBar.getComponentOrientation().isLeftToRight()) {  //W TYM MOMENCIE DZIAŁA TYLKO ORIENTACJA IS LEFT TO RIGHT
                        setOffset(new Point(offset.y, offset.x));
                    }
                       
                    else if (o == JToolBar.HORIZONTAL) {
                        setOffset(new Point(size.height - offset.y, offset.x)); //offset.x
                    } else {
                        setOffset(new Point(offset.y, size.width - offset.x));
                    }
                    
                }
            
            }
            repaint();
        }
        

        public Point getOffset() {
            return offset;
        }

        public void setOffset(Point p) {
            this.offset = p;
        }

        public void setBorderColor(Color c) {
            if (this.borderColor == c) {
                return;
            }
            this.borderColor = c;
            repaint();
        }

        public Color getBorderColor() {
            return this.borderColor;
        }

        public Insets getInsets() {
            return new Insets(1, 1, 1, 1);
        }
        
        @Override
        public boolean equals(Object other){
            if(other != null){
                return hashCode() == other.hashCode();
                        
            }
              return false;      
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 47 * hash + Objects.hashCode(this.borderColor);
            hash = 47 * hash + Objects.hashCode(this.offset);
            return hash;
        }
    }
    
     
    public class FloatingManager{ 
        
        FloatingStrategy strategy;
        
        public FloatingManager(boolean isFloatingAllowed, boolean setFloating){
            setStrategy(isFloatingAllowed, setFloating);
        }

        private void setStrategy(boolean isFloatingAllowed1, boolean setFloating) {
            if (isFloatingAllowed1 && setFloating) {
                strategy = new FloatableStrategy();
            } else {
                strategy = new NonFloatableStrategy(); 
            }
        }
        
        public boolean applyStrategy(boolean setFloating, Point p){
             return strategy.setFloating(setFloating, p);
        }
        
        public FloatingStrategy getStrategy(){
            return strategy;
        }
        
          
    }
    
    abstract class FloatingStrategy{
        boolean isFloatable;
        abstract boolean setFloating(boolean setfloating, Point p);
        
        
        
        boolean isFloatable(){
            return isFloatable;
        }
    }
    
    
    public class FloatableStrategy extends FloatingStrategy{
      
        public FloatableStrategy(){
            isFloatable = true;
        }
        
        public boolean setFloating(boolean setfloating, Point p){
            if (dockingSource == null) {
                dockingSource = toolBar.getParent();
                dockingSource.remove(toolBar);
            }
            constraintBeforeFloating = calculateConstraint();
            if (propertyListener != null) {
                UIManager.addPropertyChangeListener(propertyListener);
            }
            
           
            updateAfterFloating(); //odpowiedzialne za pokazanie na screenie
            return isFloatable;
        }
    }
    
    
    public class NonFloatableStrategy extends FloatingStrategy{
        
          public NonFloatableStrategy(){
            isFloatable = false;
        }
          
         public boolean setFloating(boolean setfloating, Point p){
            
            if (floatingToolBar instanceof Window) {
                ((Window) floatingToolBar).setVisible(false);
            }
            
            floatingToolBar.getContentPane().remove(toolBar);
            Integer constraint = getDockingConstraint(dockingSource, p);
           
            int orientation = mapConstraintToOrientation(constraint);
            setOrientation(orientation);

            if (dockingSource == null) {
                dockingSource = toolBar.getParent();
            }
            
            if (propertyListener != null) {
                UIManager.removePropertyChangeListener(propertyListener);
            }
            
            dockingSource.add(toolBar, constraint.intValue()); 
            updateAfterFloating();
            return isFloatable;
        }
        
    }
    
    
}

