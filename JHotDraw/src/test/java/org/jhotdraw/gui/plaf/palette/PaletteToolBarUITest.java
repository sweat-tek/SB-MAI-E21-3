/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.gui.plaf.palette;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.WindowListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import org.jhotdraw.gui.plaf.palette.PaletteToolBarUI.DragWindow;
import org.jhotdraw.gui.plaf.palette.PaletteToolBarUI.ToolBarDialog;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnitRunner;


/**
 *
 * @author Michalina
 */
@RunWith(MockitoJUnitRunner.class)
public class PaletteToolBarUITest {
    
    @Mock
    DragWindow dragWindow;
    
    @Mock
    ToolBarDialog tbd;         
    
    PaletteToolBarUI instance;
    JToolBar toolbar;
    Window window;
    JDialog dialog;
    Window frame;
    
   public static final int HORIZONTAL = JToolBar.HORIZONTAL;
   public static final int VERTICAL   = JToolBar.VERTICAL;
     
      
    @Before
    public void setUp() {
        
        
     instance = new PaletteToolBarUI();  
     dialog = new JDialog();
     frame = new Frame();
     toolbar = new JToolBar();
     toolbar.setSize(100,100);
     toolbar.setName("SUT");
     instance.toolBar = toolbar;
     //frame.add(toolbar);
     //window = SwingUtilities.getWindowAncestor(toolbar); 
    
   
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void testCreateUI() {
        JComponent c = (JComponent)new JToolBar();
        PaletteToolBarUI expected = new PaletteToolBarUI();
        assertThat(PaletteToolBarUI.createUI(c)).usingRecursiveComparison().isEqualTo(expected);
    }
    
    @Test
    public void testInstallUI() throws Exception{
       
            instance.installUI(toolbar);
  
            Method getOrient = PaletteToolBarUI.class.getDeclaredMethod("mapConstraintToOrientation", Object.class);
            getOrient.setAccessible(true);
            
            assertThat(instance.isFloating()).isEqualTo(false);
            assertThat(instance.getFloatingToolBar()).isNull();
            assertThat(getOrient.invoke(instance, frame)).isEqualTo(toolbar.getOrientation());
            assertThat(toolbar.isOpaque()).isEqualTo(true);
        
    }


    /**
     * Test of createFloatingWindow method, of class PaletteToolBarUI.
     */
    @Test
    public void testCreateFloatingWindow()  {
        System.out.println("createFloatingWindow");
        frame = null;
        frame.setName("dialog0");
        tbd = new PaletteToolBarUI.ToolBarDialog((Frame) frame, toolbar.getName(), false);
        PaletteToolBarUI.ToolBarDialog result =  (ToolBarDialog) instance.createFloatingWindow(toolbar);
        tbd.getRootPane().setName("ToolBar.FloatingWindow");
        tbd.setTitle(toolbar.getName());
        tbd.setResizable(false);
        tbd.addWindowListener(null);
         
        assertEquals(tbd, result);
    }
    
 
    /**
     * Test of createDragWindow method, of class PaletteToolBarUI.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateDragWindow() throws Exception {
        System.out.println("createDragWindow");
        instance.installUI(toolbar);
        Class<?> dragWindowClass = Class.forName("org.jhotdraw.gui.plaf.palette.PaletteToolBarUI$DragWindow");
        
        Constructor<?> constructor = dragWindowClass.getDeclaredConstructor(PaletteToolBarUI.class, Window.class);
        constructor.setAccessible(true);
        
        Class <?> componentClass = Class.forName("javax.swing.JComponent");
        Method putPropertyMethod = componentClass.getDeclaredMethod("putClientProperty", Object.class, Object.class);
        putPropertyMethod.setAccessible(true);
        
        frame = (Window) instance.createFloatingWindow(toolbar);
        
        DragWindow expected = (DragWindow) constructor.newInstance(instance, (Window) frame);
       

        JRootPane rp = (expected).getRootPane();
        putPropertyMethod.invoke(rp, "Window.alpha", new Float(0.6f));
        
       assertEquals(expected, instance.createDragWindow(toolbar));
     

 
    }

    @Nested
    class FloatingTests{
        
       
       
        PaletteToolBarUI.FloatingManager floatManager;
        PaletteToolBarUI.FloatableStrategy floatableStrg;
        PaletteToolBarUI.NonFloatableStrategy nonFloatableStrg;
        Constructor<?> constructorManager;


        @Before
        public void setUp()throws Exception {
            Class<?> floatManagerClass = Class.forName("org.jhotdraw.gui.plaf.palette.PaletteToolBarUI$FloatingManager");
            Class<?> floatableStrgClass = Class.forName("org.jhotdraw.gui.plaf.palette.PaletteToolBarUI$FloatableStrategy");
            Class<?> nonFloatableStrgClass = Class.forName("org.jhotdraw.gui.plaf.palette.PaletteToolBarUI$NonFloatableStrategy");
        
            constructorManager = floatManagerClass.getDeclaredConstructor(PaletteToolBarUI.class, Boolean.class, Boolean.class);
            constructorManager.setAccessible(true);
            
            
            Constructor<?> constructorFloatableStrg = floatableStrgClass.getDeclaredConstructor(PaletteToolBarUI.class);
            constructorFloatableStrg.setAccessible(true);
            
            
            Constructor<?> constructorNonFloatableStrg = nonFloatableStrgClass.getDeclaredConstructor(PaletteToolBarUI.class);
            constructorFloatableStrg.setAccessible(true);

            //floatManager = ;
            floatableStrg = (PaletteToolBarUI.FloatableStrategy) constructorFloatableStrg.newInstance(instance);
            nonFloatableStrg = (PaletteToolBarUI.NonFloatableStrategy) constructorNonFloatableStrg.newInstance(instance);
        }
    

        /**
         * Test of setFloating method, of class PaletteToolBarUI.
         */
        @Test
        public void testSetFloating()throws Exception {
            System.out.println("setFloating");

            boolean floating = true;
            Point p = null;
 
            //PaletteToolBarUI expected = (PaletteToolBarUI) instance;
            floatManager = (PaletteToolBarUI.FloatingManager) constructorManager.newInstance(instance, floatableStrg.isFloatable, floating);
           
            assertEquals(instance.setFloating(true, p), floatManager.applyStrategy(floating, p));
            assertThat(instance.getPropertyChangeListener()).isNull();
            assertThat(instance.mapConstraintToOrientation(toolbar)).isEqualTo(instance.getConstraingBeforeFloating());

            
            @Nested
            class FloatingManagerTest{
                PaletteToolBarUI.FloatingManager floatManager;
                Constructor<?> constructorManager;
                Class<?> floatManagerClass;
                
                @Before
                void setUp() throws Exception{  
                    floatManagerClass = Class.forName("org.jhotdraw.gui.plaf.palette.PaletteToolBarUI$FloatingManager");
                    constructorManager = floatManagerClass.getDeclaredConstructor(PaletteToolBarUI.class, Boolean.class, Boolean.class);
                    constructorManager.setAccessible(true);
                    
                }
                
                void createManagerTest() throws Exception{
                    assertThat(floatManagerClass).isEqualTo(PaletteToolBarUI.FloatingManager.class);
                    floatManager = (PaletteToolBarUI.FloatingManager) constructorManager.newInstance(instance, true, true);
                    assertThat((floatManager.getStrategy().isFloatable())).isEqualTo(true);
                   
                    
                    floatManager = (PaletteToolBarUI.FloatingManager) constructorManager.newInstance(instance, false, false);
                    assertThat((floatManager.getStrategy().isFloatable())).isEqualTo(false);
                    
                    
                    floatManager = (PaletteToolBarUI.FloatingManager) constructorManager.newInstance(instance, true, false);
                    assertThat((floatManager.getStrategy().isFloatable())).isEqualTo(false);
                }
            }
            
            @Nested
            class FloatableStrategyTest{
                 PaletteToolBarUI.FloatableStrategy floatableStrg;
                 
                 
                @Before
                public void setUp()throws Exception {
                   
                    Class<?> floatableStrgClass = Class.forName("org.jhotdraw.gui.plaf.palette.PaletteToolBarUI$FloatableStrategy");
                    
                    Constructor<?> constructorFloatableStrg = floatableStrgClass.getDeclaredConstructor(PaletteToolBarUI.class);
                    constructorFloatableStrg.setAccessible(true);
                
                    floatableStrg = (PaletteToolBarUI.FloatableStrategy) constructorFloatableStrg.newInstance(instance);  
                }
      

                public void setFloatingTest(){
                    Point p = null;
                    Boolean isFloating = true;
                   assertEquals(instance.setFloating(isFloating, p), true);

                }
            }  
            
            @Nested
            class NonFloatableStrategyTest{
                  PaletteToolBarUI.NonFloatableStrategy nonFloatableStrg;
                  
                @Before
                public void setUp()throws Exception {
                   
                    Class<?> nonFloatableStrgClass = Class.forName("org.jhotdraw.gui.plaf.palette.PaletteToolBarUI$FloatableStrategy"); 
                    Constructor<?> constructorNonFloatableStrg = nonFloatableStrgClass.getDeclaredConstructor(PaletteToolBarUI.class);
                    constructorNonFloatableStrg.setAccessible(true);
                    nonFloatableStrg = (PaletteToolBarUI.NonFloatableStrategy) constructorNonFloatableStrg.newInstance(instance);  
                }

                
                
                public void setFloatingTest(){
                    
                   Point p = new Point(6,0);
                   Boolean isFloating = false;
                   assertEquals(instance.setFloating(isFloating, p), false);


                }      
            }
        }   
    }   

    /**
     * Test of setOrientation method, of class PaletteToolBarUI.
     */
    @Test
    public void testSetOrientation() {
        System.out.println("setOrientation");   
        assertThat(instance.getOrientation()).isEqualTo(toolbar.getOrientation());   
        instance.setOrientation(VERTICAL);
        assertThat(instance.getOrientation()).isEqualTo(toolbar.getOrientation());
        
    }


    /**
     * Test of canDock method, of class PaletteToolBarUI.
     */
    @Test
    public void testCanDock() throws Exception {
        System.out.println("canDock");
        
        Method getDockingContraint = PaletteToolBarUI.class.getDeclaredMethod("getDockingConstraint", Component.class, Point.class);
        getDockingContraint.setAccessible(true);
        frame.add(toolbar);   
        Point p = new Point(5,0);
        int dockingConstraints = (int) getDockingContraint.invoke(instance,(Component) frame, p);

        boolean result = instance.canDock(frame, p);
        assertEquals(dockingConstraints != 0, result);
         
    }
    
    
    
/////////////////////////////////////////////////////////////////////////////////////////////

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
        // TODO review the generated test code and fail("The test case is a prototype.");
    }

    /**
     * Test of floatAt method, of class PaletteToolBarUI.
     */
    @Test
    public void testFloatAt() {
        System.out.println("floatAt");
        
        Point position = new Point(5,0);
        Point origin = new Point(1,0);
        
        boolean isDockingPossible = true;
        
 
        instance.floatAt(position, origin);
        // TODO review the generated test code and remove the default call to fail.
        
    }

/////////////////////////////////////////////////////////////////////////////////////////////

    
    
    /**
     * Test of paintDragWindow method, of class PaletteToolBarUI.
     */
    @Test
    public void testPaintDragWindow() {
        System.out.println("paintDragWindow");
        Rectangle rec = new Rectangle(0,0,10,10);
        Graphics2D g = rec.
        PaletteToolBarUI instance = new PaletteToolBarUI();
        instance.paintDragWindow(g);
        // TODO review the generated test code and remove the default call to fail.
       
    }


    
}
