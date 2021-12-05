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
import java.awt.geom.Rectangle2D;

/**
 *
 * @author micha
 */
public class ThenCircleIsDrawn extends Stage<ThenCircleIsDrawn>  {
              
    @ExpectedScenarioState
    private SVGEllipseFigure instance;
    
    @ProvidedScenarioState
    private Rectangle2D.Double result;

    
    ThenCircleIsDrawn CircleAppersOnAScreen(){
        
        result = instance.getBounds();
        return this;

    }

 
    
}
