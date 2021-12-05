/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.util.prefs;

import java.awt.Window;
import java.util.prefs.Preferences;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michalina
 */
public class PreferencesUtilTest {
    
    public PreferencesUtilTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of installFramePrefsHandler method, of class PreferencesUtil.
     */
    @Test
    public void testInstallFramePrefsHandler() {
        System.out.println("installFramePrefsHandler");
        Preferences prefs = null;
        String name = "";
        Window window = null;
        PreferencesUtil.installFramePrefsHandler(prefs, name, window);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of installPalettePrefsHandler method, of class PreferencesUtil.
     */
    @Test
    public void testInstallPalettePrefsHandler() {
        System.out.println("installPalettePrefsHandler");
        Preferences prefs = null;
        String name = "";
        Window window = null;
        int x = 0;
        PreferencesUtil.installPalettePrefsHandler(prefs, name, window, x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of installInternalFramePrefsHandler method, of class PreferencesUtil.
     */
    @Test
    public void testInstallInternalFramePrefsHandler() {
        System.out.println("installInternalFramePrefsHandler");
        Preferences prefs = null;
        String name = "";
        JInternalFrame window = null;
        JDesktopPane desktop = null;
        PreferencesUtil.installInternalFramePrefsHandler(prefs, name, window, desktop);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of installToolBarPrefsHandler method, of class PreferencesUtil.
     */
    @Test
    public void testInstallToolBarPrefsHandler() {
        System.out.println("installToolBarPrefsHandler");
        Preferences prefs = null;
        String name = "";
        JToolBar toolbar = null;
        PreferencesUtil.installToolBarPrefsHandler(prefs, name, toolbar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of installTabbedPanePrefsHandler method, of class PreferencesUtil.
     */
    @Test
    public void testInstallTabbedPanePrefsHandler() {
        System.out.println("installTabbedPanePrefsHandler");
        Preferences prefs = null;
        String name = "";
        JTabbedPane tabbedPane = null;
        PreferencesUtil.installTabbedPanePrefsHandler(prefs, name, tabbedPane);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
