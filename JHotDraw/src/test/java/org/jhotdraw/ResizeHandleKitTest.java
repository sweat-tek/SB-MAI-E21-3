/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.draw;

import java.awt.AWTException;
import java.awt.Button;
import java.awt.Component;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import org.jhotdraw.samples.svg.figures.SVGEllipseFigure;
import org.jhotdraw.samples.svg.figures.SVGRectFigure;
import org.jhotdraw.samples.svg.figures.SVGTextFigure;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Acer
 */
public class ResizeHandleKitTest extends Component{
    
    public ResizeHandleKitTest() {
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

    public Figure addShape(int randShape){
        int p1,p2,p3,p4;
        p1 = (int)(Math.random() * 101) + 1;
        p2 = (int)(Math.random() * 102) + 1;
        p3 = (int)(Math.random() * 25) + 1;
        p4 = (int)(Math.random() * 26) + 1;
        
        if(randShape==0){return new SVGRectFigure(p1,p2,p3,p4);}
        else if(randShape==1){return new SVGEllipseFigure(p1,p2,p3,p4);}
        else if(randShape==2){return new SVGTextFigure();}
        return null;
    }
    /**
     * Test of addResizeHandles method, of class ResizeHandleKit.
     */
    @Test
    public void testAddResizeHandles() {
        System.out.println("addResizeHandles");
        Collection<Handle> handles;
        Iterator<Handle> itrt;
        boolean isFail = false;
        String s;
        
        Figure f = addShape(0); //just call this function for 0, 1 and 2
        if(f==null){fail("ERROR: Creating the figure.");}
        
        handles = (Collection<Handle>) new LinkedList<Handle>();
        
        ResizeHandleKit.addResizeHandles(f, handles);
        if(handles.size()!=9){fail("ERROR: Adding resize handles");}
        itrt = handles.iterator();
        if(itrt.next().getClass().getName()!="org.jhotdraw.draw.BoundsOutlineHandle"){isFail=true;}
        if(itrt.next().getClass().getName()!="org.jhotdraw.draw.ResizeHandleKit$SouthEastHandle"){isFail=true;}
        if(itrt.next().getClass().getName()!="org.jhotdraw.draw.ResizeHandleKit$SouthWestHandle"){isFail=true;}
        if(itrt.next().getClass().getName()!="org.jhotdraw.draw.ResizeHandleKit$NorthEastHandle"){isFail=true;}
        if(itrt.next().getClass().getName()!="org.jhotdraw.draw.ResizeHandleKit$NorthWestHandle"){isFail=true;}
        if(itrt.next().getClass().getName()!="org.jhotdraw.draw.ResizeHandleKit$SouthHandle"){isFail=true;}
        if(itrt.next().getClass().getName()!="org.jhotdraw.draw.ResizeHandleKit$NorthHandle"){isFail=true;}
        if(itrt.next().getClass().getName()!="org.jhotdraw.draw.ResizeHandleKit$EastHandle"){isFail=true;}
        if(itrt.next().getClass().getName()!="org.jhotdraw.draw.ResizeHandleKit$WestHandle"){isFail=true;}
        if(isFail){fail("ERROR: Wrong resize handles added");}
    }
    
    @Test
    public void testNorthWestHandle() throws AWTException{
        System.out.println("KeyPressedResizing-NorthWestHandle");
        Handle tempHandle;
        KeyEvent evt;
        Button robotClick = new Button("click");
        Collection<Handle> handles = (Collection<Handle>) new LinkedList<Handle>();
        SVGRectFigure f = new SVGRectFigure(100,100,25,25);
        int methodX, methodY, manualX, manualY;
        int methodWidth, methodHeight, manualWidth, manualHeight;
                
        ResizeHandleKit.addResizeHandles(f, handles);
        Iterator<Handle> iterator = handles.iterator();
        tempHandle = iterator.next();
        for(int i = 0 ; i<4 ; ++i){
            tempHandle = iterator.next();
        }
        
        evt = new KeyEvent(robotClick, KeyEvent.KEY_PRESSED,1 ,1 ,KeyEvent.VK_UP ,'u');
        manualY = tempHandle.getBounds().y - 1;
        manualHeight = tempHandle.getBounds().height + 1;
        tempHandle.keyPressed(evt);
        methodY = tempHandle.getBounds().y;
        methodHeight = tempHandle.getBounds().height;
        if(manualY != methodY || manualHeight != methodHeight){fail("UP key resize failed");}
        //--------------------------------------------------------------------------------------
        evt = new KeyEvent(robotClick, KeyEvent.KEY_PRESSED,1 ,1 ,KeyEvent.VK_DOWN ,'d');
        manualY = tempHandle.getBounds().y + 1;
        manualHeight = tempHandle.getBounds().height - 1;
        tempHandle.keyPressed(evt);
        methodY = tempHandle.getBounds().y;
        methodHeight = tempHandle.getBounds().height;
        if(manualY != methodY || manualHeight != methodHeight){fail("DOWN key resize failed");}
        //--------------------------------------------------------------------------------------
        evt = new KeyEvent(robotClick, KeyEvent.KEY_PRESSED,1 ,1 ,KeyEvent.VK_LEFT ,'l');
        manualX = tempHandle.getBounds().x - 1;
        manualWidth = tempHandle.getBounds().width + 1;
        tempHandle.keyPressed(evt);
        methodX = tempHandle.getBounds().x;
        methodWidth = tempHandle.getBounds().width;
        if(manualX != methodX || manualWidth != methodWidth){fail("LEFT key resize failed");}
        //--------------------------------------------------------------------------------------
        evt = new KeyEvent(robotClick, KeyEvent.KEY_PRESSED,1 ,1 ,KeyEvent.VK_RIGHT ,'r');
        manualX = tempHandle.getBounds().x + 1;
        manualWidth = tempHandle.getBounds().width - 1;
        tempHandle.keyPressed(evt);
        methodX = tempHandle.getBounds().x;
        methodWidth = tempHandle.getBounds().width;
        if(manualX != methodX || manualWidth != methodWidth){fail("RIGHT key resize failed");}
    }
}