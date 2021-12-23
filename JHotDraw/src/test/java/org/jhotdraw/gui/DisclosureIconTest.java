/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.gui;

import java.awt.Component;
import java.awt.Graphics;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michalina
 */
public class DisclosureIconTest {
    
    public DisclosureIconTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of paintIcon method, of class DisclosureIcon.
     */
    @Test
    public void testPaintIcon() {
        System.out.println("paintIcon");
        Component component = null;
        Graphics g = null;
        int x = 0;
        int y = 0;
        DisclosureIcon instance = new DisclosureIcon();
        instance.paintIcon(component, g, x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIconWidth method, of class DisclosureIcon.
     */
    @Test
    public void testGetIconWidth() {
        System.out.println("getIconWidth");
        DisclosureIcon instance = new DisclosureIcon();
        int expResult = 0;
        int result = instance.getIconWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIconHeight method, of class DisclosureIcon.
     */
    @Test
    public void testGetIconHeight() {
        System.out.println("getIconHeight");
        DisclosureIcon instance = new DisclosureIcon();
        int expResult = 0;
        int result = instance.getIconHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
