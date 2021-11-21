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
    //private JLabel label;
    public final static String DISCLOSURE_STATE_PROPERTY = "disclosureState";
    public final static String DISCLOSURE_STATE_COUNT_PROPERTY = "disclosureStateCount";

    /** Creates new form. */
    public JDisclosureToolBar() {
        setUI(PaletteToolBarUI.createUI(this));
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        AbstractButton btn;
        //JLabel lbl;

        gbc = new GridBagConstraints();
        setUpGridBagConstraints(gbc, GridBagConstraints.SOUTHWEST,GridBagConstraints.NONE);
        
        if (disclosureButton == null) {
            btn = new JButton();
            setUpDisclosureButton(btn);
        } else {
            btn = disclosureButton;
        }
        /*
         if (label == null) {
            lbl = new JLabel();
            lbl.setVisible(false);
        } else {
            lbl = label;
        }
         */
        add(btn, gbc);
        //add(lbl, gbc);

        putClientProperty(PaletteToolBarUI.TOOLBAR_INSETS_OVERRIDE_PROPERTY, new Insets(0, 0, 0, 0));
        putClientProperty(PaletteToolBarUI.TOOLBAR_ICON_PROPERTY, new EmptyIcon(10, 8));
    }

    private void setUpGridBagConstraints(GridBagConstraints gbc, int anchorPos, int idFill) {
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 1, 0, 1);
        gbc.anchor = anchorPos;
        gbc.fill = idFill;
        gbc.weighty = 1d;
        gbc.weightx = 1d;
    }

    private void setUpDisclosureButton(AbstractButton btn) {
        btn.setUI((PaletteButtonUI) PaletteButtonUI.createUI(btn));
        btn.setBorderPainted(false);
        btn.setIcon(new DisclosureIcon());
        btn.setOpaque(false);
        disclosureButton = (JButton) btn;
        disclosureButton.putClientProperty(DisclosureIcon.CURRENT_STATE_PROPERTY, 1);
        disclosureButton.putClientProperty(DisclosureIcon.STATE_COUNT_PROPERTY, 2);
        disclosureButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                int newState = ((Integer) disclosureButton.getClientProperty(DisclosureIcon.CURRENT_STATE_PROPERTY) + 1) %
                        (Integer) disclosureButton.getClientProperty(DisclosureIcon.STATE_COUNT_PROPERTY);
                setDisclosureState(newState);
            }
        });
    }
    /*
    private void setUpLabel(JLabel lbl) {
      lbl.setOpaque(false);
      label = (JLabel) lbl;
      label.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
              label.setVisible(true);
              label.setText("LOL");

          }
      });
    }
*/
    public void setDisclosureStateCount(int newValue) {
        int oldValue = getDisclosureStateCount();
        disclosureButton.putClientProperty(DisclosureIcon.STATE_COUNT_PROPERTY, newValue);
        firePropertyChange(DISCLOSURE_STATE_COUNT_PROPERTY, oldValue, newValue);
    }

    public void setDisclosureState(int newValue) {
        int oldValue = getDisclosureState();
        disclosureButton.putClientProperty(DisclosureIcon.CURRENT_STATE_PROPERTY, newValue);

        removeAll();
        JComponent c = getDisclosedComponent(newValue);
        GridBagLayout layout = (GridBagLayout) getLayout();
        GridBagConstraints gbc; //= new GridBagConstraints();
        if (c != null) {
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.weightx = 1d;
            gbc.weighty = 1d;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.WEST;
            add(c, gbc);
            
            gbc = new GridBagConstraints();
            
            gbc.gridx = 0;
            gbc.weightx = 0d;
            gbc.insets = new Insets(0, 1, 0, 1);
            gbc.weighty = 1d;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.SOUTHWEST;
            add(disclosureButton, gbc);
        } else {
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.weightx = 1d;
            gbc.weighty = 1d;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.SOUTHWEST;
            gbc.insets = new Insets(0, 1, 0, 1);
            add(disclosureButton, gbc);
        }

        invalidate();
        Container parent = getParent();
        while (parent.getParent() != null && !parent.getParent().isValid()) {
            parent = parent.getParent();
        }
        parent.validate();
        repaint();

        firePropertyChange(DISCLOSURE_STATE_PROPERTY, oldValue, newValue);
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
