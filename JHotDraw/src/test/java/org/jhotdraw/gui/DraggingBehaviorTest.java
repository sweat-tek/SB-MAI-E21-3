/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.gui;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.jhotdraw.draw.action.WhenGrouping;
import org.junit.Test;

/**
 *
 * @author Michalina
 */

public class DraggingBehaviorTest extends ScenarioTest<GivenPaletteToolBarToDrag, WhenDragging, ThenToolBarDragged> {

    @Test
    public void selectingToolBarAndDraggingIt() {
        given().aToolBarToDrag("ToolsToolBar");
                
        when().clickTodragToolBar();

        then().selectedToolBarisDragging();
    }

    @Test
    public void tryingToDragMoreToolBarsAtOnce() {
          given().aToolBarToDrag("ToolsToolBar").and().aToolBarToDrag("SelectionToolBar")
                  .and().aToolBarToDrag("FontToolBar");
                
          when().clickTodragToolBar();
          when().clickTodragToolBar();
          when().clickTodragToolBar();

         then().onlyTheLastSelectedToolBarIsDragging();
    }
    
    @Test
    public void tryingToSelectMoreToolBarsAtOnce() {
        given().aToolBarToDrag("ToolsToolBar").and().aToolBarToDrag("SelectionToolBar");
                
        when().clickTodragToolBar();

        then().onlyTheLastSelectedToolBarIsDragging();
    }
}


   