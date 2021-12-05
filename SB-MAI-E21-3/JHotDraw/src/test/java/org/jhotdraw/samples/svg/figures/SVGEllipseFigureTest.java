/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.samples.svg.figures;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import org.jhotdraw.draw.ConnectionFigure;
import org.jhotdraw.draw.Connector;
import org.jhotdraw.draw.Handle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author micha
 */
public class SVGEllipseFigureTest {
    
   

    public SVGEllipseFigureTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    

    /**
     * Test of drawFill method, of class SVGEllipseFigure.
     */
    @Test
    public void testDrawFill() {
        System.out.println("drawFill");
        Graphics2D g = null;
        SVGEllipseFigure instance = new SVGEllipseFigure();
        instance.drawFill(g);

    }

    /**
     * Test of drawStroke method, of class SVGEllipseFigure.
     */
    @Test
    public void testDrawStroke() {
        System.out.println("drawStroke");
        Graphics2D g = null;
        SVGEllipseFigure instance = new SVGEllipseFigure();
        instance.drawStroke(g);

    }

    /**
     * Test of getX method, of class SVGEllipseFigure.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        SVGEllipseFigure instance = new SVGEllipseFigure();
        double expResult = 0.0;
        double result = instance.getX();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getY method, of class SVGEllipseFigure.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        SVGEllipseFigure instance = new SVGEllipseFigure();
        double expResult = 0.0;
        double result = instance.getY();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getWidth method, of class SVGEllipseFigure.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        SVGEllipseFigure instance = new SVGEllipseFigure();
        double expResult = 0.0;
        double result = instance.getWidth();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getHeight method, of class SVGEllipseFigure.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        SVGEllipseFigure instance = new SVGEllipseFigure();
        double expResult = 0.0;
        double result = instance.getHeight();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getBounds method, of class SVGEllipseFigure.
     */
    @Test
    public void testGetBounds() {
        System.out.println("getBounds");
        SVGEllipseFigure instance = new SVGEllipseFigure(1,1,1,1);
        Rectangle2D.Double expResult = new Rectangle2D.Double(2,2,2,2);
        Rectangle2D.Double result = instance.getBounds();
        assertEquals(expResult, result);

    }



    /**
     * Test of contains method, of class SVGEllipseFigure.
     */
    @Test
    
    public void testContains() {
        System.out.println("contains");
        Point2D.Double p = new Point2D.Double(-1, -1);
        
        
        double xc=1.2310;
        double yc=211.0;
        double rx=131.0241;
        double ry=4211.0;
        double x=xc-rx;
        double y=yc-ry;
        SVGEllipseFigure instance = new SVGEllipseFigure(xc,yc,rx,ry);
        
        boolean expResult = true;
        boolean result = instance.contains(p);
       
        
       while( Math.pow((x-xc),2) /Math.pow(rx,2)+Math.pow(y-yc,2)/Math.pow(ry,2) <= 1.0){
           
            p = new Point2D.Double(x, y);
            result = instance.contains(p);
           
            assertEquals(expResult, result);
            
            if(expResult==result)
                System.out.println("Point ("+ x + " ," + y + ") is coonteind in Elipse" );
            }   
            x+=0.1;
            y+=0.1;
        }
    


    /**
     * Test of canConnect method, of class SVGEllipseFigure.
     */
    @Test
    public void testCanConnect() {
        System.out.println("canConnect");
        SVGEllipseFigure instance = new SVGEllipseFigure();
        boolean expResult = false;
        boolean result = instance.canConnect();
        assertEquals(expResult, result);

    }

    /**
     * Test of findConnector method, of class SVGEllipseFigure.
     */
    @Test
    public void testFindConnector() {
        System.out.println("findConnector");
        Point2D.Double p = null;
        ConnectionFigure prototype = null;
        SVGEllipseFigure instance = new SVGEllipseFigure();
        Connector expResult = null;
        Connector result = instance.findConnector(p, prototype);
        assertEquals(expResult, result);

    }

    /**
     * Test of findCompatibleConnector method, of class SVGEllipseFigure.
     */
    @Test
    public void testFindCompatibleConnector() {
        System.out.println("findCompatibleConnector");
        Connector c = null;
        boolean isStartConnector = false;
        SVGEllipseFigure instance = new SVGEllipseFigure();
        Connector expResult = null;
        Connector result = instance.findCompatibleConnector(c, isStartConnector);
        assertEquals(expResult, result);

    }

    /**
     * Test of clone method, of class SVGEllipseFigure.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        SVGEllipseFigure instance = new SVGEllipseFigure();
        SVGEllipseFigure expResult = null;
        SVGEllipseFigure result = instance.clone();
        
        if(result.equals(instance))
        {System.out.println("COLNED");}
        else
        {System.out.println("ERROR");}


    }

    /**
     * Test of isEmpty method, of class SVGEllipseFigure.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        SVGEllipseFigure instance = new SVGEllipseFigure(1,12,1124,112);
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
     //   fail("The test case is a prototype.");
    }

    /**
     * Test of invalidate method, of class SVGEllipseFigure.
     */
    @Test
    public void testInvalidate() {
        System.out.println("invalidate");
        SVGEllipseFigure instance = new SVGEllipseFigure();
        instance.invalidate();
        // TODO review the generated test code and remove the default call to fail.
     //   fail("The test case is a prototype.");
    }
    
}
