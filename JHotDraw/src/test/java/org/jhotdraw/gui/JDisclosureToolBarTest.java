/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComponent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michalina
 */
public class JDisclosureToolBarTest {
    
    public JDisclosureToolBarTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setDisclosureStateCount method, of class JDisclosureToolBar.
     */
    @Test
    public void testSetDisclosureStateCount() {
        System.out.println("setDisclosureStateCount");
        int newValue = 0;
        JDisclosureToolBar instance = new JDisclosureToolBar();
        instance.setDisclosureStateCount(newValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDisclosureState method, of class JDisclosureToolBar.
     */
    @Test
    public void testSetDisclosureState() {
        System.out.println("setDisclosureState");
        int newValue = 0;
        JDisclosureToolBar instance = new JDisclosureToolBar();
        instance.setDisclosureState(newValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createGridBagConstraints method, of class JDisclosureToolBar.
     */
    @Test
    public void testCreateGridBagConstraints_int_int() {
        System.out.println("createGridBagConstraints");
        int gridX = 0;
        int gridY = 0;
        JDisclosureToolBar instance = new JDisclosureToolBar();
        GridBagConstraints expResult = null;
        GridBagConstraints result = instance.createGridBagConstraints(gridX, gridY);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createGridBagConstraints method, of class JDisclosureToolBar.
     */
    @Test
    public void testCreateGridBagConstraints_3args() {
        System.out.println("createGridBagConstraints");
        int gridX = 0;
        int gridY = 0;
        Insets insets = null;
        JDisclosureToolBar instance = new JDisclosureToolBar();
        GridBagConstraints expResult = null;
        GridBagConstraints result = instance.createGridBagConstraints(gridX, gridY, insets);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisclosureStateCount method, of class JDisclosureToolBar.
     */
    @Test
    public void testGetDisclosureStateCount() {
        System.out.println("getDisclosureStateCount");
        JDisclosureToolBar instance = new JDisclosureToolBar();
        int expResult = 0;
        int result = instance.getDisclosureStateCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisclosureState method, of class JDisclosureToolBar.
     */
    @Test
    public void testGetDisclosureState() {
        System.out.println("getDisclosureState");
        JDisclosureToolBar instance = new JDisclosureToolBar();
        int expResult = 0;
        int result = instance.getDisclosureState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisclosedComponent method, of class JDisclosureToolBar.
     */
    @Test
    public void testGetDisclosedComponent() {
        System.out.println("getDisclosedComponent");
        int state = 0;
        JDisclosureToolBar instance = new JDisclosureToolBar();
        JComponent expResult = null;
        JComponent result = instance.getDisclosedComponent(state);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
