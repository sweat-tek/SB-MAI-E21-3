/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import java.awt.Graphics2D;

/**
 *
 * @author micha
 */
public class WhenUserPressCircleIcon extends Stage<WhenUserPressCircleIcon>{
    
    @ExpectedScenarioState
    private SVGEllipseFigure instance;
     
    WhenUserPressCircleIcon WhenUserPressCircleIcon(){
        Graphics2D g = null;
        instance.drawFill(g);
        return this;
        
     }
    
}
