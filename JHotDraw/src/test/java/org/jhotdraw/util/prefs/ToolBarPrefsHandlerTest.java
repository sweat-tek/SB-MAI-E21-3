/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.util.prefs;

import java.awt.event.ComponentEvent;
import javax.swing.event.AncestorEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michalina
 */
public class ToolBarPrefsHandlerTest {
    
    public ToolBarPrefsHandlerTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of componentHidden method, of class ToolBarPrefsHandler.
     */
    @Test
    public void testComponentHidden() {
        System.out.println("componentHidden");
        ComponentEvent e = null;
        ToolBarPrefsHandler instance = null;
        instance.componentHidden(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of componentMoved method, of class ToolBarPrefsHandler.
     */
    @Test
    public void testComponentMoved() {
        System.out.println("componentMoved");
        ComponentEvent e = null;
        ToolBarPrefsHandler instance = null;
        instance.componentMoved(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of componentResized method, of class ToolBarPrefsHandler.
     */
    @Test
    public void testComponentResized() {
        System.out.println("componentResized");
        ComponentEvent e = null;
        ToolBarPrefsHandler instance = null;
        instance.componentResized(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ancestorAdded method, of class ToolBarPrefsHandler.
     */
    @Test
    public void testAncestorAdded() {
        System.out.println("ancestorAdded");
        AncestorEvent event = null;
        ToolBarPrefsHandler instance = null;
        instance.ancestorAdded(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of componentShown method, of class ToolBarPrefsHandler.
     */
    @Test
    public void testComponentShown() {
        System.out.println("componentShown");
        ComponentEvent e = null;
        ToolBarPrefsHandler instance = null;
        instance.componentShown(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ancestorMoved method, of class ToolBarPrefsHandler.
     */
    @Test
    public void testAncestorMoved() {
        System.out.println("ancestorMoved");
        AncestorEvent event = null;
        ToolBarPrefsHandler instance = null;
        instance.ancestorMoved(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ancestorRemoved method, of class ToolBarPrefsHandler.
     */
    @Test
    public void testAncestorRemoved() {
        System.out.println("ancestorRemoved");
        AncestorEvent event = null;
        ToolBarPrefsHandler instance = null;
        instance.ancestorRemoved(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
