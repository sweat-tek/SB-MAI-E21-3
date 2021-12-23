/**
 * @(#)JDisclosureToolBar.java  1.0  April 12, 2008
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
package org.jhotdraw.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import org.jhotdraw.gui.plaf.palette.*;

/**
 * A ToolBar with disclosure functionality.
 *
 * @author Werner Randelshofer
 * @version 1.0 JDisclosureToolBar Created.
 */
public class JDisclosureToolBar extends JToolBar {

    private JButton disclosureButton;
    protected static final int DEFAULT_DISCLOSURE_STATE = 0;
   //REFACTORIZATION momdifiers were in incorrect order
    public static final String DISCLOSURE_STATE_PROPERTY = "disclosureState";
    public static final String DISCLOSURE_STATE_COUNT_PROPERTY = "disclosureStateCount";

    /** Creates new form. */
    public JDisclosureToolBar() {
        setUI(PaletteToolBarUI.createUI(this));
        initComponents();
    }
    

    protected int getDefaultDisclosureState() {
        return DEFAULT_DISCLOSURE_STATE;
    }
    
//REFACTORIZATION -> uneeded local variable (button), large method-> divided into several one
    private void initComponents() {
        setLayout(new GridBagLayout());
        
        if (disclosureButton == null) {
            disclosureButton = new JButton();
            setUpDisclosureButton();
            
        }
        
        GridBagConstraints gbc;
        gbc = new GridBagConstraints(0, 0, 1, 1, 1d, 1d, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(0, 1, 0, 1), 0, 0); 
        add(disclosureButton, gbc);
      
        putClientProperty(PaletteToolBarUI.TOOLBAR_INSETS_OVERRIDE_PROPERTY, new Insets(0, 0, 0, 0));
        putClientProperty(PaletteToolBarUI.TOOLBAR_ICON_PROPERTY, new DisclosureIcon());
       
        
    }

   

    private void setUpDisclosureButton() {
        PaletteButtonUI btnUI =  (PaletteButtonUI) PaletteButtonUI.createUI(this);
        disclosureButton.setUI(btnUI);
        disclosureButton.setBorderPainted(false);
        disclosureButton.setIcon(new DisclosureIcon());
        disclosureButton.setOpaque(false);
    
        disclosureButton.putClientProperty(DisclosureIcon.CURRENT_STATE_PROPERTY, 1);
        disclosureButton.putClientProperty(DisclosureIcon.STATE_COUNT_PROPERTY, 2);
        
        disclosureButton.addActionListener((ActionEvent e) -> {
            int newState = ((Integer) disclosureButton.getClientProperty(DisclosureIcon.CURRENT_STATE_PROPERTY) + 1) %
                    (Integer) disclosureButton.getClientProperty(DisclosureIcon.STATE_COUNT_PROPERTY);
            setDisclosureState(newState);
        });
    }
  
    //używana przez wszystkie bary 
    public void setDisclosureStateCount(int newValue) {
        int oldValue = getDisclosureStateCount();
        disclosureButton.putClientProperty(DisclosureIcon.STATE_COUNT_PROPERTY, newValue);
        firePropertyChange(DISCLOSURE_STATE_COUNT_PROPERTY, oldValue, newValue);
    }
    
    
    
    
    
        //ActionsToolBar, AbstractToolBar
    public void setDisclosureState(int newValue) {
        
        int oldValue = getDisclosureState();
        disclosureButton.putClientProperty(DisclosureIcon.CURRENT_STATE_PROPERTY, newValue);
        removeAll();
        JComponent component = getDisclosedComponent(newValue);
        GridBagConstraints gbc;
        
        if (component != null) {
            gbc = new GridBagConstraints(1, 0, 1, 1, 1d, 1d, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
            add(component, gbc);

            gbc = new GridBagConstraints(0, 0, 1, 1, 1d, 1d, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(0, 1, 0, 1), 0, 0);  
            add(disclosureButton, gbc);
                 
        } else {
            gbc = new GridBagConstraints(1, 0, 1, 1, 1d, 1d, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(0, 1, 0, 1), 0, 0);  
            add(disclosureButton, gbc);
        }

        updateToolbarView(oldValue, newValue);
    }
    
    
    public GridBagConstraints createGridBagConstraints(int gridX, int gridY){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        return gbc;
    }
    
    //ToolsToolBar
    public GridBagConstraints createGridBagConstraints(int gridX, int gridY, Insets insets){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.insets = insets;
        return gbc;
    }
    

    //Uzywana wewnętrznie 
    public void updateToolbarView(int oldValue, int newValue) {
        invalidate();
        if (getParent() != null){
            Container parent = getParent();
            while (parent.getParent() != null && !parent.getParent().isValid()) { 
                parent = parent.getParent();
            }
            parent.validate();
            repaint();

            firePropertyChange(DISCLOSURE_STATE_PROPERTY, oldValue, newValue);
        }
    }
    
    
    public int getDisclosureStateCount() {
        Integer value = (Integer) disclosureButton.getClientProperty(DisclosureIcon.STATE_COUNT_PROPERTY);
        return (value == null) ? 2 : value;
    }

    public int getDisclosureState() {
        Integer value = (Integer) disclosureButton.getClientProperty(DisclosureIcon.CURRENT_STATE_PROPERTY);
        return (value == null) ? 1 : value;
    }

    protected JComponent getDisclosedComponent(int state) {
        return new JLabel(Integer.toString(state));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     * /
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
