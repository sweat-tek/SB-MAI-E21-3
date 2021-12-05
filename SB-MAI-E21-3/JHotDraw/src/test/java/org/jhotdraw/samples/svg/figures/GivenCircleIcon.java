/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

/**
 *
 * @author micha
 */
public class GivenCircleIcon extends Stage<GivenCircleIcon> {
    
    @ProvidedScenarioState
    private SVGEllipseFigure instance= null;
    
    GivenCircleIcon CircleIcon(){
        instance = new SVGEllipseFigure();
        return this;
    }
}
