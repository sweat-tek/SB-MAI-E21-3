/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.gui.plaf.palette;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import sun.java2d.SunGraphics2D;

/**
 *
 * @author Michalina
 */
public class PaletteToolBarUITestUtil {
    
    Robot robot;
    
   PaletteToolBarUI.FloatingManager floatManager;
   PaletteToolBarUI.FloatableStrategy floatableStrg;
   PaletteToolBarUI.NonFloatableStrategy nonFloatableStrg;
   
    Class<?> floatManagerClass;
    Class<?> floatableStrgClass;
    Class<?> nonFloatableStrgClass;
    
    Constructor<?> constructorManager;
    Constructor<?> constructorNonFloatableStrg;
    Constructor<?> constructorFloatableStrg;
  
    public Class getToolBarDialogClass() throws Exception{
        return Class.forName("org.jhotdraw.gui.plaf.palette.PaletteToolBarUI$ToolBarDialog");
    }

     public Class getDragWindowClass() throws Exception{
        return  Class.forName("org.jhotdraw.gui.plaf.palette.PaletteToolBarUI$DragWindow");
    }
     
    public Class getToolsToolBarClass() throws Exception{
        return Class.forName("org.jhotdraw.samples.svg.gui.ToolsToolBar");
    }
    
    public Class getDefautltDrawingEditorClass() throws Exception{
        return Class.forName("org.jhotdraw.draw.DefaultDrawingEditor");
    }
    
    public Constructor getDefaultDrawingEditorConstructor() throws Exception{
        Constructor<?> getDefaultDrawingEditorConstructor = getDefautltDrawingEditorClass().getConstructor();
        getDefaultDrawingEditorConstructor.setAccessible(true);
        return getDefaultDrawingEditorConstructor;
    }
    
    public Constructor getToolsToolBarConstructor() throws Exception{
        Constructor<?> getToolsToolBarConstructor = getToolsToolBarClass().getConstructor();
        getToolsToolBarConstructor.setAccessible(true);
        return getToolsToolBarConstructor;
    }
    
    public Method getCompDispToolsToolBarMethod() throws Exception{
        Method getComponentDisplayedToolsToolbar = getToolsToolBarClass().getDeclaredMethod("getComponentDisplayed", JPanel.class);
        getComponentDisplayedToolsToolbar.setAccessible(true);
        return getComponentDisplayedToolsToolbar;
    }
     
     
     public Method paintDragWindowMethod() throws Exception{
        Method paintDragWindowMethod = getDragWindowClass().getDeclaredMethod("paintDragWindow", Graphics.class);
        paintDragWindowMethod.setAccessible(true);
        return paintDragWindowMethod;
     }
     

     
    public Field getDockingSourceField() throws Exception{
        Field dockingSourceField =PaletteToolBarUI.class.getDeclaredField("dockingSource");
        dockingSourceField.setAccessible(true);
        return dockingSourceField;
    } 
     
       public Field getConstraintBeforeFloating() throws Exception{
         Field constraintBeforeFloating =PaletteToolBarUI.class.getDeclaredField("constraintBeforeFloating");
         constraintBeforeFloating.setAccessible(true);
         return constraintBeforeFloating;
     } 


    public Method createDragWindowMethod() throws Exception{
        Method createDragWindowMethod = PaletteToolBarUI.class.getDeclaredMethod("createDragWindow", JToolBar.class);
        createDragWindowMethod.setAccessible(true);
        return createDragWindowMethod;
    }

    public static Method createFloatingWindowMethod() throws Exception{
        Method createFloatingWindowMethod = PaletteToolBarUI.class.getDeclaredMethod("createFloatingWindow", JToolBar.class);
        createFloatingWindowMethod.setAccessible(true);
        return createFloatingWindowMethod;

    }
    
    public Method calculateConstraintMethod() throws Exception{
        Method calculateConstraintMethod = PaletteToolBarUI.class.getDeclaredMethod("calculateConstraint");
        calculateConstraintMethod.setAccessible(true);
        return calculateConstraintMethod;
        
    }
    
     public Method getDockingConstraintMethod() throws Exception{
        Method getDockingConstraintMethod = PaletteToolBarUI.class.getDeclaredMethod("getDockingConstraint", Container.class, Point.class);
        getDockingConstraintMethod.setAccessible(true);
        return getDockingConstraintMethod;
        
    }
     
     public Method getComparisonPointMethod() throws Exception{
        Method getComparisonPointMethod = PaletteToolBarUI.class.getDeclaredMethod("getComparisonPoint", Point.class);
        getComparisonPointMethod.setAccessible(true);
        return getComparisonPointMethod;
     }
     
     
    public Class geFloatManagerClass() throws Exception{
        floatManagerClass = Class.forName("org.jhotdraw.gui.plaf.palette.PaletteToolBarUI$FloatingManager");
        return floatManagerClass;
    }
    
    
     public Class geFloatableStrategyClass() throws Exception{
        floatableStrgClass = Class.forName("org.jhotdraw.gui.plaf.palette.PaletteToolBarUI$FloatableStrategy");
        return floatableStrgClass;
    } 
        
    public Class geNonFloatableStrategyClass() throws Exception{
        nonFloatableStrgClass = Class.forName("org.jhotdraw.gui.plaf.palette.PaletteToolBarUI$NonFloatableStrategy");
        return nonFloatableStrgClass;
     }
     
    public Constructor getManagerConstructor() throws Exception{ 
        constructorManager = floatManagerClass.getDeclaredConstructor(boolean.class, boolean.class);
        constructorManager.setAccessible(true);
        return constructorManager;
    }
    
     public Constructor getFloatableStrategyConstructor() throws Exception{ 
        constructorFloatableStrg = floatableStrgClass.getDeclaredConstructor();
        constructorFloatableStrg.setAccessible(true);
        return constructorFloatableStrg;
    }
    
    public Constructor getNonFloatableStrategyConstructor() throws Exception{ 
        constructorNonFloatableStrg = nonFloatableStrgClass.getDeclaredConstructor();
        constructorFloatableStrg.setAccessible(true);
        return constructorNonFloatableStrg;
    }
    
    public Constructor<?> getFloatingManager(PaletteToolBarUI toolbarUI, PaletteToolBarUI.FloatingStrategy strategy) throws Exception{
       return (Constructor<?>) constructorManager.newInstance(toolbarUI, strategy);
    }
    
    
    public static void  clickMouse(String button, Robot r, int delay){
        int mouse;
        switch (button) {
            case "left": mouse = InputEvent.BUTTON1_MASK;break;
            case "right": mouse = InputEvent.BUTTON3_MASK;break;
            default: mouse = InputEvent.BUTTON1_MASK; break;
        }    
            r.mousePress(mouse);
            r.delay(delay);
            r.mouseRelease(mouse);

    }
    
    
    public static void ClickOnComponent(Robot r, int x, int y){
        moveMouse(r, x, y);
        clickMouse("left",r, 1000);
    }
    
    
    public static void dragComponent(Robot r, int xFrom, int yFrom, int xTo, int yTo){
        int mouse = InputEvent.BUTTON1_MASK;
        moveMouse(r, xFrom, yFrom);
        r.mousePress(mouse);
        moveMouse(r,xTo, yTo);
        r.mouseRelease(mouse);
        
    }
    
    public static void  clicksMouse(Robot r, int number){
        int mouse = InputEvent.BUTTON1_MASK;
        
        for(int i =0;i< number;i++){
            r.mousePress(mouse);
            r.delay(1000);
            r.mouseRelease(mouse);
            r.delay(1000);
        }
    }
   
    public static void moveMouse(Robot r, int X, int Y){
        r.mouseMove(X, Y);
    }


}
