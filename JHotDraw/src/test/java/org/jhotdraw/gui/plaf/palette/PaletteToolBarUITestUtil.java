/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.gui.plaf.palette;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.lang.reflect.Method;
import javax.swing.JToolBar;

/**
 *
 * @author Michalina
 */
public class PaletteToolBarUITestUtil {
    
    Robot robot;
  
    public Class getToolBarDialogClass() throws Exception{
        return Class.forName("org.jhotdraw.gui.plaf.palette.PaletteToolBarUI$ToolBarDialog");
    }

     public Class getDragWindowClass() throws Exception{
        return  Class.forName("org.jhotdraw.gui.plaf.palette.PaletteToolBarUI$DragWindow");
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
