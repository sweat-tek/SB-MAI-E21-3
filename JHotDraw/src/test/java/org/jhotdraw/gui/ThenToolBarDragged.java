/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.gui;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JToolBar;
import static org.assertj.core.api.Assertions.assertThat;
import org.jhotdraw.draw.CompositeFigure;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.gui.plaf.palette.PaletteToolBarUI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Michalina
 */
public class ThenToolBarDragged {
    
   @ExpectedScenarioState
    MouseEvent mouseEvent;
    
   @ExpectedScenarioState
   PaletteToolBarUI.DragWindow dragWindowOfSelectedToolBar;
  
   @ExpectedScenarioState
   DrawingEditor editor;
       
   @ExpectedScenarioState
    PaletteToolBarUI selectedToolBar;
   
    @ExpectedScenarioState
     ArrayList<PaletteToolBarUI> notSelectedToolBars;
    
    
    @ProvidedScenarioState
    org.jhotdraw.samples.svg.SVGDrawingPanel drawingPanel;


    ThenToolBarDragged selectedToolBarisDragging(){
        assertToolBarIsSelected(selectedToolBar, mouseEvent);
        assertToolBarIsFloating(selectedToolBar);
        assertToolBarisDragged(selectedToolBar);
        return this;
    }
    
    ThenToolBarDragged onlyTheLastSelectedToolBarIsDragging() {
        assertToolBarIsSelected(selectedToolBar, mouseEvent);
        assertToolBarIsSelected(selectedToolBar, mouseEvent);
        assertOnlyOneIsDragging(notSelectedToolBars);
        return this;
               
       
    }
    
    private void assertToolBarIsFloating(PaletteToolBarUI selectedToolBar) {
        assertTrue(selectedToolBar.isFloating());
    }
    
    private void assertToolBarIsSelected(PaletteToolBarUI selectedToolBar, MouseEvent e){
        assertTrue(e.getComponent().equals(selectedToolBar));
    }
    
    private void assertToolBarisDragged(PaletteToolBarUI selectedToolBar){
        assertThat(selectedToolBar.dragWindow).isNotNull();
        assertTrue(selectedToolBar.dragWindow.isActive());
    }
    
    
     private void assertOnlyOneIsDragging(ArrayList<PaletteToolBarUI> notSelectedToolBars){
         notSelectedToolBars.stream().map(rest -> {
             assertThat(rest.dragWindow).isNull();
            return rest;
        }).forEachOrdered(rest -> {
            assertTrue(rest.dragWindow.isActive());
        });
    }
    


}
