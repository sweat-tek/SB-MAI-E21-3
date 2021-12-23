/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.gui;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import java.awt.Component;
import java.awt.Robot;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.logging.Handler;
import javax.swing.JToolBar;
import org.jhotdraw.draw.*;
import org.jhotdraw.gui.plaf.palette.PaletteToolBarUI;
import org.jhotdraw.gui.plaf.palette.PaletteToolBarUI.DockingListener;
import org.jhotdraw.gui.plaf.palette.PaletteToolBarUITestUtil;
import org.jhotdraw.samples.svg.figures.SVGGroupFigure;


/**
 *
 * @author Michalina
 */


public class GivenPaletteToolBarToDrag  extends Stage<GivenPaletteToolBarToDrag> {

    @ProvidedScenarioState
    org.jhotdraw.samples.svg.SVGDrawingPanel drawingPanel;
   
    JToolBar selectedToolBar;
    PaletteToolBarUI toolbarUI;
    
   
    
 
    @BeforeStage
    private void before() throws Exception {
        toolbarUI = new PaletteToolBarUI();
        drawingPanel = new org.jhotdraw.samples.svg.SVGDrawingPanel();  
        
        
    }

    GivenPaletteToolBarToDrag aToolBarToDrag(String nemID) {
        JDisclosureToolBar toolbar = (JDisclosureToolBar) drawingPanel.getToolBar(nemID);
        toolbarUI.installUI(toolbar);
        return this;    
    }
    
    GivenPaletteToolBarToDrag aToolBarToDragDimensions(Component choosenComp) {
        toolbarUI.getPreferredSize(selectedToolBar);
        return this;
    }
    
    
 

}
