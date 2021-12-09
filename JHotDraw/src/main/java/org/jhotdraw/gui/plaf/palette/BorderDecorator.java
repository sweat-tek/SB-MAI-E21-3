/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.gui.plaf.palette;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import javax.swing.border.*;
import javax.swing.plaf.*;


/**
 *
 * @author Michalina
 */
public class BorderDecorator {
    
    private HashMap<AbstractButton, Border> borderTable = new HashMap<>();
    private final HashMap<AbstractButton, Boolean> rolloverTable = new HashMap<>();
    
    private boolean rolloverBorders = false;
    
    private static Border rolloverBorder;
    private static Border nonRolloverBorder;
    private static Border nonRolloverToggleBorder;

   protected void setBorderToNormal(Component c) {
   if (c instanceof AbstractButton) {
      Border border =  borderTable.remove(c); // zwraca Border tego przycisku który usuwam 
      ((AbstractButton) c).setBorder(border);

      Boolean value =  rolloverTable.remove(c);
      if (value != null) {
          ((AbstractButton)c).setRolloverEnabled(value);
      }
   }
   }


   protected void setBorderToNonRollover(Component c) {     
     if (c instanceof AbstractButton) {
         AbstractButton b = (AbstractButton) c;

         Border border = (Border) borderTable.get(b);
         if (border == null || border instanceof UIResource) {
             borderTable.put(b, b.getBorder());
         }

         // Only set the border if its the default border
         if (b.getBorder() instanceof UIResource) {
             if (b instanceof JToggleButton) {
                 ((JToggleButton) b).setBorder(createNonRolloverToggleBorder());
             } else {
                 b.setBorder(createNonRolloverBorder());
             }
         }
         rolloverTable.put(b, b.isRolloverEnabled() ? Boolean.TRUE : Boolean.FALSE);
         b.setRolloverEnabled(false);
     }
   }


   protected void setBorderToRollover(Component c) {
     
     if (c instanceof AbstractButton) {
         AbstractButton b = (AbstractButton) c;

         Border border = (Border) borderTable.get(b);
         if (border == null || border instanceof UIResource) {
             borderTable.put(b, b.getBorder());
         }

         // Only set the border if its the default border
         if (b.getBorder() instanceof UIResource) {
             b.setBorder(getRolloverBorder(b));
         }

         rolloverTable.put(b, b.isRolloverEnabled() ? Boolean.TRUE : Boolean.FALSE);
         b.setRolloverEnabled(true);
     }
 }

  private Border getRolloverBorder(AbstractButton b) {
     Object borderProvider = UIManager.get("ToolBar.rolloverBorderProvider");
     if (borderProvider == null) {
         return  createRolloverBorder();
     }
     return null;  // LOGGER
 }


  protected void installNormalBorders(JComponent c) {
        // Put back the normal borders on buttons
        Component[] components = c.getComponents();

        for (int i = 0; i < components.length; ++i) {
            setBorderToNormal(components[i]);
        }
   }


   protected void installNonRolloverBorders(JComponent c) {
        Component[] components = c.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof JComponent) {
                ((JComponent) components[i]).updateUI();
                setBorderToNonRollover(components[i]);           
            }
        }
   }


       protected void installRolloverBorders(JComponent c) {

        // Put rollover borders on buttons
        Component[] components = c.getComponents();

        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof JComponent) {
                ((JComponent) components[i]).updateUI();
                setBorderToRollover(components[i]);
            }
         }
        
      }

      public void setRolloverBorders(boolean rollover, JToolBar toolBar) {
        rolloverBorders = rollover;

        if (rolloverBorders) {
            installRolloverBorders(toolBar);
        } else {
            installNonRolloverBorders(toolBar);
        }
      }
      
      
      //FACTORY METHOD PATTERN
    protected Border createRolloverBorder() {
        Object border = UIManager.get("ToolBar.rolloverBorder");
        if (border != null) {
            return (Border) border;
        }
        return new EmptyBorder(0, 0, 0, 0);
    }

    /**
     * Creates the non rollover border for toolbar components. This
     * border will be installed as the border for components added
     * to the toolbar if rollover borders are not enabled.
     * <p>
     * Override this method to provide an alternate rollover border.
     *
     * @since 1.4
     */
    protected Border createNonRolloverBorder() {
        Object border = UIManager.get("ToolBar.nonrolloverBorder");
        if (border != null) {
            return (Border) border;
        }
       
        return new EmptyBorder(0, 0, 0, 0);
    
    }

    /**
     * Creates a non rollover border for Toggle buttons in the toolbar.
     */
    private Border createNonRolloverToggleBorder() {
        return new EmptyBorder(0, 0, 0, 0);
    }

    
}
