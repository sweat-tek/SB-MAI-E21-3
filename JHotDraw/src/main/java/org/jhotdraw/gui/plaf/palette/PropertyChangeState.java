/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.gui.plaf.palette;

import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

/**
 *
 * @author Michalina
 */




/*
interface PropertyChangeState {
    
  abstract void changeState(JToolBar toolBar, PropertyChangeEvent evt);
}


class LookAndFeelPropertyChange implements PropertyChangeState{
   
    @Override 
    public void changeState(JToolBar toolBar, PropertyChangeEvent evt){
        toolBar.updateUI();
    }
}

class RolloverPropertyChange implements PropertyChangeState{
    
    @Override
    public void changeState(JToolBar toolBar, PropertyChangeEvent evt){
        toolBar.rolloverPropertyChange(evt);
    }

}

class OrientationPropertyChange implements PropertyChangeState{
    
    @Overrides
    public void changeState(JToolBar toolBar, PropertyChangeEvent evt){
    JToolBar.Separator separator;
    Component [] components = toolBar.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof JToolBar.Separator) {   
                setSeparatorOrientation(components[i], toolBar.getOrientation()); 
                Dimension size = separator.getSeparatorSize();
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
    
    private static final String ROLLOVER = "JToolBar.isRollover";
    private static final String ORIENTATION = "orientation";
    private static final String LOOK_AND_FEEL = "lookAndFeel";
    private PropertyChangeState propertyChangeState;
    private static ChangeStateContext context  =  new ChangeStateContext(); //singleton
    
    public static ChangeStateContext getChangeContext(){
       return context;
    }
   
    
   
    void request(String name, JToolBar toolBar){
        propertyChangeState = getPropertyChangeState(name);
        propertyChangeState.changeState(toolBar);
    }
    
    
   private PropertyChangeState  getPropertyChangeState(String name){
       if(name.equals(ROLLOVER)) return new RolloverPropertyChange();  
       if(name.equals(ORIENTATION)) return new OrientationPropertyChange();      
       if(name.equals(LOOK_AND_FEEL)) return new LookAndFeelPropertyChange(); 
       return null;
    }
   
}
    
*/



