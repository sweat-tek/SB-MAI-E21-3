/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.gui.plaf.palette;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ContainerListener;
import java.awt.event.FocusListener;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.RootPaneContainer;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Michalina
 */
public class PaletteToolBarUITest {
    
    ComponentUI SUT;
     JComponent c;
    
    public PaletteToolBarUITest() {
        
    }
    
    @Before
    public void setUp() {
        c = null;
        SUT = PaletteToolBarUI.createUI(c);
    }
    
    @After
    public void tearDown() {
    }

  
    /**
     * Test of installUI method, of class PaletteToolBarUI.
     */
    @Test
    public void testInstallUI() {
        System.out.println("installUI");
       
        SUT.installUI(c);
       assertTrue();
       assertEquals();
    }
    
     @Test
    public void testInstallDefaults() {
    }
    
    
    @Test
    public void testSetOrientation() {
    }
    
     @Test
    public void testUninstallDefaults() {
    }
    
  
    
    
    

    /**
     * Test of uninstallUI method, of class PaletteToolBarUI.
     */
    @Test
    public void testUninstallUI() {
        System.out.println("uninstallUI");
        JComponent c = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.uninstallUI(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
  

    /**
     * Test of installKeyboardActions method, of class PaletteToolBarUI.
     */
    @Test
    public void testInstallKeyboardActions() {
        System.out.println("installKeyboardActions");
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.installKeyboardActions();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInputMap method, of class PaletteToolBarUI.
     */
    @Test
    public void testGetInputMap() {
        System.out.println("getInputMap");
        int condition = 0;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        InputMap expResult = null;
        InputMap result = instance.getInputMap(condition);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadActionMap method, of class PaletteToolBarUI.
     */
    @Test
    public void testLoadActionMap() {
        System.out.println("loadActionMap");
        PaletteLazyActionMap map = null;
        PaletteToolBarUI.loadActionMap(map);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of uninstallKeyboardActions method, of class PaletteToolBarUI.
     */
    @Test
    public void testUninstallKeyboardActions() {
        System.out.println("uninstallKeyboardActions");
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.uninstallKeyboardActions();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of navigateFocusedComp method, of class PaletteToolBarUI.
     */
    @Test
    public void testNavigateFocusedComp() {
        System.out.println("navigateFocusedComp");
        int direction = 0;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.navigateFocusedComp(direction);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRolloverBorder method, of class PaletteToolBarUI.
     */
    @Test
    public void testCreateRolloverBorder() {
        System.out.println("createRolloverBorder");
        PaletteToolBarUI instance = new PaletteToolBarUI();
        Border expResult = null;
        Border result = instance.createRolloverBorder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createNonRolloverBorder method, of class PaletteToolBarUI.
     */
    @Test
    public void testCreateNonRolloverBorder() {
        System.out.println("createNonRolloverBorder");
        PaletteToolBarUI instance = new PaletteToolBarUI();
        Border expResult = null;
        Border result = instance.createNonRolloverBorder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFloatingFrame method, of class PaletteToolBarUI.
     */
    @Test
    public void testCreateFloatingFrame() {
        System.out.println("createFloatingFrame");
        JToolBar toolbar = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        JFrame expResult = null;
        JFrame result = instance.createFloatingFrame(toolbar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFloatingWindow method, of class PaletteToolBarUI.
     */
    @Test
    public void testCreateFloatingWindow() {
        System.out.println("createFloatingWindow");
        JToolBar toolbar = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        RootPaneContainer expResult = null;
        RootPaneContainer result = instance.createFloatingWindow(toolbar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDragWindow method, of class PaletteToolBarUI.
     */
    @Test
    public void testCreateDragWindow() {
        System.out.println("createDragWindow");
        JToolBar toolbar = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        PaletteToolBarUI.DragWindow expResult = null;
        PaletteToolBarUI.DragWindow result = instance.createDragWindow(toolbar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRolloverBorders method, of class PaletteToolBarUI.
     */
    @Test
    public void testIsRolloverBorders() {
        System.out.println("isRolloverBorders");
        PaletteToolBarUI instance = new PaletteToolBarUI();
        boolean expResult = false;
        boolean result = instance.isRolloverBorders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRolloverBorders method, of class PaletteToolBarUI.
     */
    @Test
    public void testSetRolloverBorders() {
        System.out.println("setRolloverBorders");
        boolean rollover = false;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.setRolloverBorders(rollover);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of installRolloverBorders method, of class PaletteToolBarUI.
     */
    @Test
    public void testInstallRolloverBorders() {
        System.out.println("installRolloverBorders");
        JComponent c = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.installRolloverBorders(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of installNonRolloverBorders method, of class PaletteToolBarUI.
     */
    @Test
    public void testInstallNonRolloverBorders() {
        System.out.println("installNonRolloverBorders");
        JComponent c = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.installNonRolloverBorders(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of installNormalBorders method, of class PaletteToolBarUI.
     */
    @Test
    public void testInstallNormalBorders() {
        System.out.println("installNormalBorders");
        JComponent c = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.installNormalBorders(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBorderToRollover method, of class PaletteToolBarUI.
     */
    @Test
    public void testSetBorderToRollover() {
        System.out.println("setBorderToRollover");
        Component c = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.setBorderToRollover(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBorderToNonRollover method, of class PaletteToolBarUI.
     */
    @Test
    public void testSetBorderToNonRollover() {
        System.out.println("setBorderToNonRollover");
        Component c = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.setBorderToNonRollover(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBorderToNormal method, of class PaletteToolBarUI.
     */
    @Test
    public void testSetBorderToNormal() {
        System.out.println("setBorderToNormal");
        Component c = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.setBorderToNormal(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFloatingLocation method, of class PaletteToolBarUI.
     */
    @Test
    public void testSetFloatingLocation() {
        System.out.println("setFloatingLocation");
        int x = 0;
        int y = 0;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.setFloatingLocation(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFloating method, of class PaletteToolBarUI.
     */
    @Test
    public void testIsFloating() {
        System.out.println("isFloating");
        PaletteToolBarUI instance = new PaletteToolBarUI();
        boolean expResult = false;
        boolean result = instance.isFloating();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFloating method, of class PaletteToolBarUI.
     */
    @Test
    public void testSetFloating() {
        System.out.println("setFloating");
        boolean b = false;
        Point p = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.setFloating(b, p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of getDockingColor method, of class PaletteToolBarUI.
     */
    @Test
    public void testGetDockingColor() {
        System.out.println("getDockingColor");
        PaletteToolBarUI instance = new PaletteToolBarUI();
        Color expResult = null;
        Color result = instance.getDockingColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDockingColor method, of class PaletteToolBarUI.
     */
    @Test
    public void testSetDockingColor() {
        System.out.println("setDockingColor");
        Color c = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.setDockingColor(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFloatingColor method, of class PaletteToolBarUI.
     */
    @Test
    public void testGetFloatingColor() {
        System.out.println("getFloatingColor");
        PaletteToolBarUI instance = new PaletteToolBarUI();
        Color expResult = null;
        Color result = instance.getFloatingColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFloatingColor method, of class PaletteToolBarUI.
     */
    @Test
    public void testSetFloatingColor() {
        System.out.println("setFloatingColor");
        Color c = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.setFloatingColor(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canDock method, of class PaletteToolBarUI.
     */
    @Test
    public void testCanDock() {
        System.out.println("canDock");
        Component c = null;
        Point p = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        boolean expResult = false;
        boolean result = instance.canDock(c, p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dragTo method, of class PaletteToolBarUI.
     */
    @Test
    public void testDragTo() {
        System.out.println("dragTo");
        Point position = null;
        Point origin = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.dragTo(position, origin);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of floatAt method, of class PaletteToolBarUI.
     */
    @Test
    public void testFloatAt() {
        System.out.println("floatAt");
        Point position = null;
        Point origin = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.floatAt(position, origin);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of paintDragWindow method, of class PaletteToolBarUI.
     */
    @Test
    public void testPaintDragWindow() {
        System.out.println("paintDragWindow");
        Graphics g = null;
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.paintDragWindow(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
