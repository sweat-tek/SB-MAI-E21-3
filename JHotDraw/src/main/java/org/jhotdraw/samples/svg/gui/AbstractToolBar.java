/**
 * @(#)AbstractToolBar.java  2.0  2008-05-24
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
package org.jhotdraw.samples.svg.gui;

import dk.sdu.mmmi.featuretracer.lib.FeatureEntryPoint;
import java.awt.*;

import java.beans.*;
import java.util.prefs.*;
import javax.swing.*;
import org.jhotdraw.app.JHotDrawFeatures;
import org.jhotdraw.gui.*;
import org.jhotdraw.draw.*;

/**
 * AbstractToolBar.
 *
 * @author Werner Randelshofer
 * @version 2.0 2008-05-24 Reworked to create panels lazily.
 * <br>1.0 2008-04-13 Created.
 */
public abstract class AbstractToolBar extends JDisclosureToolBar {
    
    
    //REFACTORIZATION
    //there should be a singletons
    protected DrawingEditor editor;
    private JComponent[] panels;
    protected Preferences prefs;
    protected PropertyChangeListener eventHandler;
    private static final int DEFAULT_DISCLOSURE_STATE = 0;
    
    
    

    /** Creates new form. */
    @FeatureEntryPoint(JHotDrawFeatures.TOOL_PALETTE)
    protected AbstractToolBar() {
        initComponents();
        try {
            prefs = Preferences.userNodeForPackage(getClass());
        } catch (SecurityException e) {
            // prefs is null, because we are not permitted to read preferences
        }
    }

    /** This should be an abstract method, but the NetBeans GUI builder
     * doesn't support abstract beans.
     * @return The ID used to retrieve labels and store user preferences.
     */
    protected abstract String getID(); // instead of returning " ", what can triggers some mistakes

    /** This should be an abstract method, but the NetBeans GUI builder
     * doesn't support abstract beans.
     */
    
    //protected void init(){}; // we dont use it anywhere, it should be removed// we dont use it anywhere, it should be removed
    
    
//REFACTORING comparing objects  changed from (==) to .equals
    
    //only if something happened update (put new disclosure state to  preferences)
    protected PropertyChangeListener getDisclosureEventHandler() {
        if (eventHandler == null) {
            eventHandler = (PropertyChangeEvent evt) -> {
                if (evt.getPropertyName().equals(DISCLOSURE_STATE_PROPERTY)) {
                    try {
                        prefs.putInt(getID() + ".disclosureState", (Integer) evt.getNewValue());
                    } catch (IllegalStateException e) {
                        // This happens, due to a bug in Apple's implementation
                        // of the Preferences class.
                        // SMELL OF CODE use logger instead of System.err.println
                        //System.err.println("Warning AbstractToolBar caught IllegalStateException of Preferences class");
                        //e.printStackTrace();
                    }
                }
            };
        }
        return eventHandler;
    }

    public void setEditor(DrawingEditor editor) {
        
        if (this.editor != null) {
            this.removePropertyChangeListener(getDisclosureEventHandler());
        }
          
        if (editor != null) {
            //init(); it is an empty method it should be removed
            setDisclosureState(Math.max(0, Math.min(getDisclosureStateCount(), prefs.getInt(getID() + ".disclosureState", DEFAULT_DISCLOSURE_STATE))));
            this.addPropertyChangeListener(getDisclosureEventHandler());
            this.editor = editor;
        } 
    }

    public DrawingEditor getEditor() {
        return editor;
    }

    @Override
    // divided into createDisclosedComponent and getDisclosedComponent
    protected final JComponent getDisclosedComponent(int state) {
        if (panels == null){
            panels = new JPanel[getDisclosureStateCount()];
            for (int i = 0; i < panels.length; i++) {
               panels[i] = new ProxyPanel();
           }
        }
       return panels[state];
    }

    
    //purpose is to replace this method by the above one.s
    abstract  JComponent createDisclosedComponent(int state); //changed to abstract method, instead of empty one (but it can be a  private void method which creates a component, and it is called in getDosclosedMethod)
   
    //CHECK ALL OF THE USAGES and force the subclasses to override createDisclosedComponent2 to finally remove createDisclosedCOmponent to make getDisclosedCOmponent the function responsible for the thing inside this overrided method
    protected int getDefaultDisclosureState() {
        return DEFAULT_DISCLOSURE_STATE;
    }
    
    
    private class ProxyPanel extends JPanel {

        private Runnable runner;
        
       
        public ProxyPanel() {
            setOpaque(false);
            setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));        
        }
       

        @Override
        @FeatureEntryPoint(JHotDrawFeatures.TOOL_PALETTE)
        public void paint(Graphics g) { // paint an icon of a tool from Tools
           super.paint(g);
            
            final int state = getDisclosureState();
            if (runner == null) {
                runner = new Runnable() {

                    public void run() {
                        try {
                        panels[state] = createDisclosedComponent(state);

                        } catch (RuntimeException e) {
                            panels[state]=null;
                        }
                        
                        JComponent parent = (JComponent) getParent();
                   
                        if (parent != null) {
                            GridBagLayout layout = (GridBagLayout) parent.getLayout();
                            GridBagConstraints gbc = layout.getConstraints(ProxyPanel.this);
                        
                                 
                            parent.remove(ProxyPanel.this);
                            
                            if (getDisclosureState() == state) {
                            if (panels[state] != null) {
                                parent.add(panels[state], gbc);
                                
                            } else {
                                
                                JPanel empty = new JPanel(new BorderLayout());
                                empty.setOpaque(false);
                                parent.add(empty, gbc);
                                
                            }
                            }
                            parent.revalidate();
                            
                            //to sprawia że na widoku na początku mam normalną wielkość component baru
                            ((JComponent) parent.getRootPane().getContentPane()).revalidate();

                        }
                    }
                };
                SwingUtilities.invokeLater(runner);
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
