/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.gui;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import java.awt.Component;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import javax.swing.JToolBar;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.gui.plaf.palette.PaletteToolBarUI;
import org.jhotdraw.gui.plaf.palette.PaletteToolBarUI.DragWindow;
import org.jhotdraw.gui.plaf.palette.PaletteToolBarUITestUtil;
import org.jhotdraw.samples.svg.*;

/**
 *
 * @author Michalina
 */
public class WhenDragging extends Stage<WhenDragging> {

   @ExpectedScenarioState
   DragWindow dragWindowOfSelectedToolBar;
       
   @ExpectedScenarioState
    PaletteToolBarUI selectedToolBar;
   
   @ExpectedScenarioState
   ArrayList<PaletteToolBarUI> notSelectedToolBars;
   
   @ProvidedScenarioState
   ArrayList <MouseListener> mouseListeners;
    
   @ProvidedScenarioState
   MouseEvent mouseEvent;
    
   @ProvidedScenarioState
   org.jhotdraw.samples.svg.SVGDrawingPanel drawingPanel;
    
   @ProvidedScenarioState
    PaletteToolBarUI toolbarUI;
    
    @ProvidedScenarioState
    PaletteToolBarUI.DockingListener dockingListener;
    
    Robot r;
    PaletteToolBarUITestUtil testUtil;
    Class<?>  dockingListenerClass;
    
    WhenDragging() throws Exception{  
        this.dockingListenerClass = Class.forName("org.jhotdraw.gui.plaf.palette.PaletteToolBarUI$DockingListener");
        Constructor<?> constr = dockingListenerClass.getDeclaredConstructor(JToolBar.class);
        constr.setAccessible(true);
        dockingListener = (PaletteToolBarUI.DockingListener) constr.newInstance(selectedToolBar);  
    }
    
    
    @BeforeStage
    private void before() throws Exception {
        r = new Robot();
        testUtil.MoveMouseOnComponent(r, selectedToolBar.toolBar);
    }
    
    WhenDragging clickTodragToolBar() {
        dockingListener.mousePressed(mouseEvent);
        mouseEvent.getComponent().equals(selectedToolBar);
        return this;
        
    }
    


   
}
