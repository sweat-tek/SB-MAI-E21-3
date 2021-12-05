/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;


/**
 *
 * @author micha
 */
public class DrawingCircleTest extends ScenarioTest<GivenCircleIcon, WhenUserPressCircleIcon, ThenCircleIsDrawn> {
    
    @Test
    public void DrawingCircle(){
        
        given().CircleIcon();
        
        when().WhenUserPressCircleIcon();
        
        then().CircleAppersOnAScreen();
        
    }
    
}
