/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.gui.plaf.palette;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
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
import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.gui.plaf.palette.PaletteToolBarUI.DragWindow;
import org.jhotdraw.gui.plaf.palette.PaletteToolBarUI.ToolBarDialog;
import org.jhotdraw.samples.svg.gui.ToolsToolBar;
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
    
    PaletteToolBarUITestUtil testUtil;
    DragWindow dragWindow;
    ToolBarDialog tbd;          
    PaletteToolBarUI instance;
    JToolBar toolbar;
    Window window;
    JDialog dialog;
    Window frame;
    Robot robot;
    
   public static final int HORIZONTAL = JToolBar.HORIZONTAL;
   public static final int VERTICAL   = JToolBar.VERTICAL;

     
    @Before
    public void setUp() throws Exception {
        
    testUtil = new PaletteToolBarUITestUtil();
    instance = new PaletteToolBarUI();  
    dialog = new JDialog();
    frame = new Frame();
    toolbar = new JToolBar();
    toolbar.setSize(100,100);
    toolbar.setName("SUT");
    instance.toolBar = toolbar;
    robot = new Robot();
    

     
    
   
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
     * @throws java.lang.Exception
     */
    @Test
    public void dragTo()throws Exception {
       frame.add(toolbar); 
       toolbar.setSize(18,18); 
       toolbar.setVisible(true);
       toolbar.setFloatable(true); 
       Point currentPoint = new Point(100,100);
       toolbar.setLocation(currentPoint);
       instance.createUI((JComponent) toolbar);
       instance.toolBar.setVisible(true);
       
      
       Point newPosition = new Point(150,100);
       
       PaletteToolBarUI expected = new PaletteToolBarUI();
       expected.toolBar = toolbar;
       expected.createUI((JComponent) toolbar);
       expected.toolBar.setVisible(true);  
       instance.dragTo(newPosition, currentPoint);
       
       DragWindow drgWindow = expected.createDragWindow(expected.toolBar);
       
       Point offset = new Point(expected.toolBar.getWidth() / 2,  expected.toolBar.getHeight() / 2);
       drgWindow.setOffset(offset);
       

        testUtil.getDockingSourceField().set(expected, expected.toolBar.getParent());
        testUtil.getConstraintBeforeFloating().set(expected, testUtil.calculateConstraintMethod().invoke(expected));
        
        
        assertEquals(expected.getConstraintBeforeFloating(),instance.getConstraintBeforeFloating());

    }
    

    /**
     * Test of floatAt method, of class PaletteToolBarUI.
     */
    @Test
    public void testFloatAt() throws Exception {
       frame.add(toolbar); 
       toolbar.setSize(18,18); 
       toolbar.setVisible(true);
       toolbar.setFloatable(true); 
       Point currentPoint = new Point(100,100);
       toolbar.setLocation(currentPoint);
       instance.createUI((JComponent) toolbar);
       instance.toolBar.setVisible(true);
       
      
       Point newPosition = new Point(150,100);
       
       PaletteToolBarUI expected = new PaletteToolBarUI();
       expected.toolBar = toolbar;
       expected.createUI((JComponent) toolbar);
       expected.toolBar.setVisible(true);  
       instance.dragTo(newPosition, currentPoint);
       instance.floatAt(newPosition, currentPoint);
       
       DragWindow drgWindow = expected.createDragWindow(expected.toolBar);
       
       Point offset = new Point(expected.toolBar.getWidth() / 2,  expected.toolBar.getHeight() / 2);
       drgWindow.setOffset(offset);
       

        testUtil.getDockingSourceField().set(expected, expected.toolBar.getParent());
        testUtil.getConstraintBeforeFloating().set(expected, testUtil.calculateConstraintMethod().invoke(expected));
        
        
        assertEquals(expected.getConstraintBeforeFloating(),instance.getConstraintBeforeFloating());
        
    }

/////////////////////////////////////////////////////////////////////////////////////////////

    
    
    /**
     * Test of paintDragWindow method, of class PaletteToolBarUI.
     * @throws java.lang.Exception
     */
    @Test
    public void testPaintDragWindow() throws Exception{
       toolbar = (JToolBar) testUtil.getToolsToolBarConstructor().newInstance();
       org.jhotdraw.draw.DefaultDrawingEditor editor = (org.jhotdraw.draw.DefaultDrawingEditor) testUtil.getDefaultDrawingEditorConstructor().newInstance();
       
       ((org.jhotdraw.samples.svg.gui.AbstractToolBar)toolbar).setEditor((DrawingEditor) editor );
       JPanel panel = null;
       panel = (JPanel) testUtil.getCompDispToolsToolBarMethod().invoke((ToolsToolBar) toolbar, panel);
       panel.add(toolbar);
       toolbar.setVisible(true);
       toolbar.setFloatable(true); 
       Point currentPoint = new Point(100,100);
       toolbar.setLocation(currentPoint);
       instance.installUI((JComponent) toolbar);

       Point newPosition = new Point(150,100);
   
       instance.dragTo(newPosition, currentPoint);
       

       PaletteToolBarUI expected = new PaletteToolBarUI();
       expected.installUI((JComponent) toolbar);
       
       DragWindow drgWindow = expected.createDragWindow(expected.toolBar);
       expected.dragWindow = drgWindow;
       
       
        assertEquals(expected.dragWindow.getBorderColor(), instance.dragWindow.getBorderColor());
        assertEquals(expected.dragWindow.getGraphics(), instance.dragWindow.getGraphics());
        assertEquals(expected.dragWindow.getSize(), instance.dragWindow.getSize());
       
    }


    @Nested
    class FloatingTests{
        

        @Before
        public void setUp()throws Exception {
            
       
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
            testUtil.floatManager = (PaletteToolBarUI.FloatingManager) testUtil.constructorManager.newInstance(instance, testUtil.floatableStrg.isFloatable, floating);
           
            assertEquals(instance.setFloating(true, p), testUtil.floatManager.applyStrategy(floating, p));
            assertThat(instance.getPropertyChangeListener()).isNull();
            assertThat(instance.mapConstraintToOrientation(toolbar)).isEqualTo(instance.getConstraintBeforeFloating());

            
            @Nested
            class FloatingManagerTest{
       
                void createManagerTest() throws Exception{
                    assertThat(testUtil.floatManagerClass).isEqualTo(PaletteToolBarUI.FloatingManager.class);
                    testUtil.floatManager = (PaletteToolBarUI.FloatingManager) testUtil.constructorManager.newInstance(instance, true, true);
                    assertThat((testUtil.floatManager.getStrategy().isFloatable())).isEqualTo(true);
                   
                    
                    testUtil.floatManager = (PaletteToolBarUI.FloatingManager) testUtil.constructorManager.newInstance(instance, false, false);
                    assertThat((testUtil.floatManager.getStrategy().isFloatable())).isEqualTo(false);
                    
                    
                    testUtil.floatManager = (PaletteToolBarUI.FloatingManager) testUtil.constructorManager.newInstance(instance, true, false);
                    assertThat((testUtil.floatManager.getStrategy().isFloatable())).isEqualTo(false);
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

 

    
}
