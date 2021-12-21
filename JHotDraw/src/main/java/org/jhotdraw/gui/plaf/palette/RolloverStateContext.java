/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.gui.plaf.palette;

import java.awt.Component;
import java.util.HashMap;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Michalina
 */
abstract class BorderState{
    boolean IS_ROLLOVER_ENABLED;
    abstract void handle(Component c); 
    abstract Border getBorder(); 
}
    
public class RolloverStateContext{ 

    private HashMap<AbstractButton,Border> borderTable;
    private HashMap<AbstractButton, Boolean> rolloverTable;
    private BorderState state;
    
    public RolloverStateContext(){
        borderTable = new HashMap<AbstractButton,Border>();
        rolloverTable = new HashMap<AbstractButton,Boolean>();
        state = new NormalBorderState(this);
    }
        
    
    //TEMPLATE METHOD SHOULD BE ALSO FOR --> installRolloverBorders() ETC WITH INSTAL...
    //template method
    public void setBorder(Component c, BorderState state, boolean isRollover){
        if (c instanceof AbstractButton) {
            AbstractButton b = (AbstractButton) c;
            Border border = (Border) getBorder(b);
            if (border == null || border instanceof javax.swing.plaf.UIResource) {
                addToBorderTable(b, b.getBorder());
            }
            // Only set the border if its the default border
            if (b.getBorder() instanceof javax.swing.plaf.UIResource) {
                b.setBorder(state.getBorder());
            }
            addToRolloverTable(b, b.isRolloverEnabled() ? Boolean.TRUE : Boolean.FALSE);
            setButtonBoolProperty(b, isRollover);
        }
    }
    
    
    protected void installBorders(JComponent c, BorderState state, Boolean isRolloverEnabled) {
        // Put non-rollover borders on buttons. These borders reduce the margin.
        Component[] components = c.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof JComponent) {
                ((JComponent) components[i]).updateUI();
                setBorder(c, state, isRolloverEnabled);
            }
        }
    }

    public HashMap<AbstractButton,Border> getBorderTable(){
        return borderTable;
    }
    
    public Border getBorder(AbstractButton btn){ 
        return borderTable.get(btn);
    }
    
     public void addToBorderTable(AbstractButton btn,Border brdr){
        borderTable.put(btn, brdr);
    }
     

    public  HashMap<AbstractButton, Boolean> getRolloverTable(){
        return rolloverTable;
    }
    
     public Boolean isButtonRollover(AbstractButton btn){ 
        return rolloverTable.get(btn);
    }
     
    
     public void setButtonBoolProperty(AbstractButton btn,Boolean bool){
         rolloverTable.replace(btn, bool);
     }
     
    public void addToRolloverTable(AbstractButton btn, Boolean bool){
        rolloverTable.put(btn, bool);
    }


    public void setState(BorderState state){
        this.state = state;
    }

    void handleRequest(Component c){
        state.handle(c);
    }

}

class RolloverBorderState extends BorderState {

    RolloverStateContext context;
    

    RolloverBorderState(RolloverStateContext context){
        this.context = context;
        this.IS_ROLLOVER_ENABLED = true;
    }
  /**
    * Installs rollover borders on all the child components of the JComponent.
    * <p>
    * This is a convenience method to call <code>setBorderToRollover</code>
    * for each child component.
    *
    * @param c container which holds the child components (usually a JToolBar)
    * @see #setBorderToRollover
    * @since 1.4
    */
       protected void installRolloverBorders(JComponent c) {
        context.installBorders(c, this, IS_ROLLOVER_ENABLED);
    }
    

       
  /**
    * Sets the border of the component to have a rollover border which
    * was created by <code>createRolloverBorder</code>.
    *
    * @param c component which will have a rollover border installed
    * @see #createRolloverBorder
    * @since 1.4
    */
    protected void setBorderToRollover(Component c) { //BorderState state) {
        context.setBorder(c,new RolloverBorderState(context), true);
    }

   //createRolloverBorder
    @Override
    public Border getBorder() {
       Object border = UIManager.get("ToolBar.rolloverBorder");
        if (border != null) {
            return (Border) border;
        }
        return new EmptyBorder(0, 0, 0, 0);
    }

      @Override
      public void handle(Component c ){
          installRolloverBorders((JComponent) c);
      }
      
      

}


class NonRolloverBorderState extends BorderState{

     RolloverStateContext context;

    public NonRolloverBorderState(RolloverStateContext context){
        this.context = context;
        this.IS_ROLLOVER_ENABLED = false;
    }

    /**
      * Installs non-rollover borders on all the child components of the JComponent.
      * A non-rollover border is the border that is installed on the child component
      * while it is in the toolbar.
      * <p>
      * This is a convenience method to call <code>setBorderToNonRollover</code>
      * for each child component.
      *
      * @param c container which holds the child components (usally a JToolBar)
      * @see #setBorderToNonRollover
      * @since 1.4
      */
    protected void installNonRolloverBorders(JComponent c) {
        context.installBorders(c, this, IS_ROLLOVER_ENABLED);
    }

    protected void setBorderToNonRollover(Component c) { //BorderState //Boolean isRolloverEnabled 
        context.setBorder(c, new NonRolloverBorderState(context), false);
    }


    //createNonRolloverBorder
    @Override
    public Border getBorder() {
        Object border = UIManager.get("ToolBar.nonrolloverBorder");
        if (border != null) {
            return (Border) border;
        }
        return new EmptyBorder(0, 0, 0, 0);
    }

    @Override 
    public void handle(Component c){
       installNonRolloverBorders((JComponent)c);
    }


}

class ToggleBorderState extends BorderState{

    RolloverStateContext context;

    public ToggleBorderState(RolloverStateContext context){
        this.context = context;
        this.IS_ROLLOVER_ENABLED = false;
    }

    protected void installToggleBorders(JComponent c) {
       context.installBorders(c, this, IS_ROLLOVER_ENABLED);
    }

    protected void setBorderToToggle(Component c) {   
       context.setBorder(c, new ToggleBorderState(context), false);
    }
    


    @Override
    public void handle(Component c) {
      installToggleBorders((JComponent)c);
    }

    @Override
    public Border getBorder(){
        return new EmptyBorder(0, 0, 0, 0);
    }

}


class NormalBorderState extends BorderState{          
    RolloverStateContext context;

    public NormalBorderState(RolloverStateContext context){
        this.context = context;
    }

     /**
 * Sets the border of the component to have a normal border.
 * A normal border is the original border that was installed on the child
 * component before it was added to the toolbar.
 *
 * @param c component which will have a normal border re-installed
 * @see #createNonRolloverBorder
 * @since 1.4
 */
    protected void setBorderToNormal(Component c) {
        if (true) {
            return;
        }
        if (c instanceof AbstractButton) {
            Border border = context.getBorderTable().remove(c);
            ((AbstractButton) c).setBorder(border);
            Boolean value =  context.getRolloverTable().remove(c);
            if (value != null) {
                ((AbstractButton) c).setRolloverEnabled(value);
            }
        }
        
        
    }

    /**
     * Installs normal borders on all the child components of the JComponent.
     * A normal border is the original border that was installed on the child
     * component before it was added to the toolbar.
     * <p>
     * This is a convenience method to call <code>setBorderNormal</code>
     * for each child component.
     *
     * @param c container which holds the child components (usually a JToolBar)
     * @see #setBorderToNonRollover
     * @since 1.4
     */
    protected void installNormalBorders(JComponent c) {
        // Put back the normal borders on buttons
        Component[] components = c.getComponents();
        for (int i = 0; i < components.length; ++i) {
            setBorderToNormal(components[i]);
        }
    }
    
  

    @Override
    public void handle(Component c) {
       installNormalBorders((JComponent) c);
    }

    @Override
    public Border getBorder(){
        return new EmptyBorder(0,0,0,0);
    }

}
