/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.gui.plaf.palette;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michalina
 */
public class PaletteToolBarBorderTest {
    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of paintBorder method, of class PaletteToolBarBorder.
     */
    @Test
    public void testPaintBorder() {
        System.out.println("paintBorder");
        Component component = null;
        Graphics gr = null;
        int x = 0;
        int y = 0;
        int w = 0;
        int h = 0;
        PaletteToolBarBorder instance = new PaletteToolBarBorder();
        instance.paintBorder(component, gr, x, y, w, h);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBorderInsets method, of class PaletteToolBarBorder.
     */
    @Test
    public void testGetBorderInsets_Component() {
        System.out.println("getBorderInsets");
        Component c = null;
        PaletteToolBarBorder instance = new PaletteToolBarBorder();
        Insets expResult = null;
        Insets result = instance.getBorderInsets(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDragInsets method, of class PaletteToolBarBorder.
     */
    @Test
    public void testGetDragInsets() {
        System.out.println("getDragInsets");
        PaletteToolBarBorder instance = new PaletteToolBarBorder();
        Insets expResult = null;
        Insets result = instance.getDragInsets();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBorderInsets method, of class PaletteToolBarBorder.
     */
    @Test
    public void testGetBorderInsets_Component_Insets() {
        System.out.println("getBorderInsets");
        Component component = null;
        Insets newInsets = null;
        PaletteToolBarBorder instance = new PaletteToolBarBorder();
        Insets expResult = null;
        Insets result = instance.getBorderInsets(component, newInsets);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
