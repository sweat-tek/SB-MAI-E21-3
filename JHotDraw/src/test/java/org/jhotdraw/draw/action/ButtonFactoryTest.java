/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.draw.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import org.jhotdraw.app.action.*;
import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.Tool;
import org.jhotdraw.gui.JPopupButton;
import org.jhotdraw.util.ResourceBundleUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michalina
 */
public class ButtonFactoryTest {
    
    public ButtonFactoryTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createDrawingActions method, of class ButtonFactory.
     */
    @Test
    public void testCreateDrawingActions() {
        System.out.println("createDrawingActions");
        DrawingEditor editor = new DrawingEditorProxy().getTarget();
      
        Collection<Action> result = ButtonFactory.createDrawingActions(editor);
        
        assertEquals(CutAction.class, ((LinkedList<Action>)result).get(0).getClass());
        assertEquals(CopyAction.class, ((LinkedList<Action>)result).get(1).getClass());
        assertEquals(PasteAction.class, ((LinkedList<Action>)result).get(2).getClass());
        assertEquals(SelectSameAction.class, ((LinkedList<Action>)result).get(3).getClass());
        
       
    }

    /**
     * Test of createSelectionActions method, of class ButtonFactory.
     */
    @Test
    public void testCreateSelectionActions() {
        System.out.println("createSelectionActions");
        DrawingEditor editor = null;
        Collection<Action> expResult = null;
        Collection<Action> result = ButtonFactory.createSelectionActions(editor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSelectionToolTo method, of class ButtonFactory.
     */
    @Test
    public void testAddSelectionToolTo_JToolBar_DrawingEditor() {
        System.out.println("addSelectionToolTo");
        JToolBar tb = null;
        DrawingEditor editor = null;
        JToggleButton expResult = null;
        JToggleButton result = ButtonFactory.addSelectionToolTo(tb, editor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSelectionToolTo method, of class ButtonFactory.
     */
    @Test
    public void testAddSelectionToolTo_4args() {
        System.out.println("addSelectionToolTo");
        JToolBar tb = null;
        DrawingEditor editor = null;
        Collection<Action> drawingActions = null;
        Collection<Action> selectionActions = null;
        JToggleButton expResult = null;
        JToggleButton result = ButtonFactory.addSelectionToolTo(tb, editor, drawingActions, selectionActions);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSelectionToolTo method, of class ButtonFactory.
     */
    @Test
    public void testAddSelectionToolTo_3args() {
        System.out.println("addSelectionToolTo");
        JToolBar tb = null;
        DrawingEditor editor = null;
        Tool selectionTool = null;
        JToggleButton expResult = null;
        JToggleButton result = ButtonFactory.addSelectionToolTo(tb, editor, selectionTool);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToolTo method, of class ButtonFactory.
     */
    @Test
    public void testAddToolTo() {
        System.out.println("addToolTo");
        JToolBar tb = null;
        DrawingEditor editor = null;
        Tool tool = null;
        String labelKey = "";
        ResourceBundleUtil labels = null;
        JToggleButton expResult = null;
        JToggleButton result = ButtonFactory.addToolTo(tb, editor, tool, labelKey, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addZoomButtonsTo method, of class ButtonFactory.
     */
    @Test
    public void testAddZoomButtonsTo() {
        System.out.println("addZoomButtonsTo");
        JToolBar bar = null;
        DrawingEditor editor = null;
        ButtonFactory.addZoomButtonsTo(bar, editor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createZoomButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateZoomButton_DrawingEditor() {
        System.out.println("createZoomButton");
        DrawingEditor editor = null;
        AbstractButton expResult = null;
        AbstractButton result = ButtonFactory.createZoomButton(editor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createZoomButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateZoomButton_DrawingView() {
        System.out.println("createZoomButton");
        DrawingView view = null;
        AbstractButton expResult = null;
        AbstractButton result = ButtonFactory.createZoomButton(view);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createZoomButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateZoomButton_DrawingView_doubleArr() {
        System.out.println("createZoomButton");
        DrawingView view = null;
        double[] factors = null;
        AbstractButton expResult = null;
        AbstractButton result = ButtonFactory.createZoomButton(view, factors);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAttributesButtonsTo method, of class ButtonFactory.
     */
    @Test
    public void testAddAttributesButtonsTo() {
        System.out.println("addAttributesButtonsTo");
        JToolBar bar = null;
        DrawingEditor editor = null;
        ButtonFactory.addAttributesButtonsTo(bar, editor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addColorButtonsTo method, of class ButtonFactory.
     */
    @Test
    public void testAddColorButtonsTo_JToolBar_DrawingEditor() {
        System.out.println("addColorButtonsTo");
        JToolBar bar = null;
        DrawingEditor editor = null;
        ButtonFactory.addColorButtonsTo(bar, editor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addColorButtonsTo method, of class ButtonFactory.
     */
    @Test
    public void testAddColorButtonsTo_4args() {
        System.out.println("addColorButtonsTo");
        JToolBar bar = null;
        DrawingEditor editor = null;
        List<ColorIcon> colors = null;
        int columnCount = 0;
        ButtonFactory.addColorButtonsTo(bar, editor, colors, columnCount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEditorColorButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateEditorColorButton_6args() {
        System.out.println("createEditorColorButton");
        DrawingEditor editor = null;
        AttributeKey<Color> attributeKey = null;
        List<ColorIcon> swatches = null;
        int columnCount = 0;
        String labelKey = "";
        ResourceBundleUtil labels = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createEditorColorButton(editor, attributeKey, swatches, columnCount, labelKey, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEditorColorButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateEditorColorButton_7args() {
        System.out.println("createEditorColorButton");
        DrawingEditor editor = null;
        AttributeKey<Color> attributeKey = null;
        List<ColorIcon> swatches = null;
        int columnCount = 0;
        String labelKey = "";
        ResourceBundleUtil labels = null;
        Map<AttributeKey, Object> defaultAttributes = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createEditorColorButton(editor, attributeKey, swatches, columnCount, labelKey, labels, defaultAttributes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEditorColorButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateEditorColorButton_8args() {
        System.out.println("createEditorColorButton");
        DrawingEditor editor = null;
        AttributeKey<Color> attributeKey = null;
        List<ColorIcon> swatches = null;
        int columnCount = 0;
        String labelKey = "";
        ResourceBundleUtil labels = null;
        Map<AttributeKey, Object> defaultAttributes = null;
        Shape colorShape = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createEditorColorButton(editor, attributeKey, swatches, columnCount, labelKey, labels, defaultAttributes, colorShape);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSelectionColorButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateSelectionColorButton_6args() {
        System.out.println("createSelectionColorButton");
        DrawingEditor editor = null;
        AttributeKey<Color> attributeKey = null;
        List<ColorIcon> swatches = null;
        int columnCount = 0;
        String labelKey = "";
        ResourceBundleUtil labels = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createSelectionColorButton(editor, attributeKey, swatches, columnCount, labelKey, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSelectionColorButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateSelectionColorButton_7args() {
        System.out.println("createSelectionColorButton");
        DrawingEditor editor = null;
        AttributeKey<Color> attributeKey = null;
        List<ColorIcon> swatches = null;
        int columnCount = 0;
        String labelKey = "";
        ResourceBundleUtil labels = null;
        Map<AttributeKey, Object> defaultAttributes = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createSelectionColorButton(editor, attributeKey, swatches, columnCount, labelKey, labels, defaultAttributes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSelectionColorButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateSelectionColorButton_8args() {
        System.out.println("createSelectionColorButton");
        DrawingEditor editor = null;
        AttributeKey<Color> attributeKey = null;
        List<ColorIcon> swatches = null;
        int columnCount = 0;
        String labelKey = "";
        ResourceBundleUtil labels = null;
        Map<AttributeKey, Object> defaultAttributes = null;
        Shape colorShape = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createSelectionColorButton(editor, attributeKey, swatches, columnCount, labelKey, labels, defaultAttributes, colorShape);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDrawingColorButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateDrawingColorButton_6args() {
        System.out.println("createDrawingColorButton");
        DrawingEditor editor = null;
        AttributeKey<Color> attributeKey = null;
        List<ColorIcon> swatches = null;
        int columnCount = 0;
        String labelKey = "";
        ResourceBundleUtil labels = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createDrawingColorButton(editor, attributeKey, swatches, columnCount, labelKey, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDrawingColorButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateDrawingColorButton_7args() {
        System.out.println("createDrawingColorButton");
        DrawingEditor editor = null;
        AttributeKey<Color> attributeKey = null;
        List<ColorIcon> swatches = null;
        int columnCount = 0;
        String labelKey = "";
        ResourceBundleUtil labels = null;
        Map<AttributeKey, Object> defaultAttributes = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createDrawingColorButton(editor, attributeKey, swatches, columnCount, labelKey, labels, defaultAttributes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDrawingColorButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateDrawingColorButton_8args() {
        System.out.println("createDrawingColorButton");
        DrawingEditor editor = null;
        AttributeKey<Color> attributeKey = null;
        List<ColorIcon> swatches = null;
        int columnCount = 0;
        String labelKey = "";
        ResourceBundleUtil labels = null;
        Map<AttributeKey, Object> defaultAttributes = null;
        Shape colorShape = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createDrawingColorButton(editor, attributeKey, swatches, columnCount, labelKey, labels, defaultAttributes, colorShape);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStrokeButtonsTo method, of class ButtonFactory.
     */
    @Test
    public void testAddStrokeButtonsTo() {
        System.out.println("addStrokeButtonsTo");
        JToolBar bar = null;
        DrawingEditor editor = null;
        ButtonFactory.addStrokeButtonsTo(bar, editor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStrokeWidthButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateStrokeWidthButton_DrawingEditor() {
        System.out.println("createStrokeWidthButton");
        DrawingEditor editor = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createStrokeWidthButton(editor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStrokeWidthButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateStrokeWidthButton_DrawingEditor_ResourceBundleUtil() {
        System.out.println("createStrokeWidthButton");
        DrawingEditor editor = null;
        ResourceBundleUtil labels = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createStrokeWidthButton(editor, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStrokeWidthButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateStrokeWidthButton_DrawingEditor_doubleArr() {
        System.out.println("createStrokeWidthButton");
        DrawingEditor editor = null;
        double[] widths = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createStrokeWidthButton(editor, widths);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStrokeWidthButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateStrokeWidthButton_3args() {
        System.out.println("createStrokeWidthButton");
        DrawingEditor editor = null;
        double[] widths = null;
        ResourceBundleUtil labels = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createStrokeWidthButton(editor, widths, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStrokeDecorationButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateStrokeDecorationButton() {
        System.out.println("createStrokeDecorationButton");
        DrawingEditor editor = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createStrokeDecorationButton(editor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStrokeDashesButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateStrokeDashesButton_DrawingEditor() {
        System.out.println("createStrokeDashesButton");
        DrawingEditor editor = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createStrokeDashesButton(editor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStrokeDashesButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateStrokeDashesButton_DrawingEditor_ResourceBundleUtil() {
        System.out.println("createStrokeDashesButton");
        DrawingEditor editor = null;
        ResourceBundleUtil labels = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createStrokeDashesButton(editor, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStrokeDashesButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateStrokeDashesButton_DrawingEditor_doubleArrArr() {
        System.out.println("createStrokeDashesButton");
        DrawingEditor editor = null;
        double[][] dashes = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createStrokeDashesButton(editor, dashes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStrokeDashesButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateStrokeDashesButton_3args() {
        System.out.println("createStrokeDashesButton");
        DrawingEditor editor = null;
        double[][] dashes = null;
        ResourceBundleUtil labels = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createStrokeDashesButton(editor, dashes, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStrokeTypeButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateStrokeTypeButton() {
        System.out.println("createStrokeTypeButton");
        DrawingEditor editor = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createStrokeTypeButton(editor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStrokePlacementButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateStrokePlacementButton() {
        System.out.println("createStrokePlacementButton");
        DrawingEditor editor = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createStrokePlacementButton(editor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFontButtonsTo method, of class ButtonFactory.
     */
    @Test
    public void testAddFontButtonsTo() {
        System.out.println("addFontButtonsTo");
        JToolBar bar = null;
        DrawingEditor editor = null;
        ButtonFactory.addFontButtonsTo(bar, editor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFontButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateFontButton_DrawingEditor() {
        System.out.println("createFontButton");
        DrawingEditor editor = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createFontButton(editor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFontButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateFontButton_DrawingEditor_ResourceBundleUtil() {
        System.out.println("createFontButton");
        DrawingEditor editor = null;
        ResourceBundleUtil labels = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createFontButton(editor, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFontButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateFontButton_3args() {
        System.out.println("createFontButton");
        DrawingEditor editor = null;
        AttributeKey<Font> key = null;
        ResourceBundleUtil labels = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createFontButton(editor, key, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFontStyleBoldButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateFontStyleBoldButton_DrawingEditor() {
        System.out.println("createFontStyleBoldButton");
        DrawingEditor editor = null;
        JButton expResult = null;
        JButton result = ButtonFactory.createFontStyleBoldButton(editor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFontStyleBoldButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateFontStyleBoldButton_DrawingEditor_ResourceBundleUtil() {
        System.out.println("createFontStyleBoldButton");
        DrawingEditor editor = null;
        ResourceBundleUtil labels = null;
        JButton expResult = null;
        JButton result = ButtonFactory.createFontStyleBoldButton(editor, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFontStyleItalicButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateFontStyleItalicButton_DrawingEditor() {
        System.out.println("createFontStyleItalicButton");
        DrawingEditor editor = null;
        JButton expResult = null;
        JButton result = ButtonFactory.createFontStyleItalicButton(editor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFontStyleItalicButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateFontStyleItalicButton_DrawingEditor_ResourceBundleUtil() {
        System.out.println("createFontStyleItalicButton");
        DrawingEditor editor = null;
        ResourceBundleUtil labels = null;
        JButton expResult = null;
        JButton result = ButtonFactory.createFontStyleItalicButton(editor, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFontStyleUnderlineButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateFontStyleUnderlineButton_DrawingEditor() {
        System.out.println("createFontStyleUnderlineButton");
        DrawingEditor editor = null;
        JButton expResult = null;
        JButton result = ButtonFactory.createFontStyleUnderlineButton(editor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFontStyleUnderlineButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateFontStyleUnderlineButton_DrawingEditor_ResourceBundleUtil() {
        System.out.println("createFontStyleUnderlineButton");
        DrawingEditor editor = null;
        ResourceBundleUtil labels = null;
        JButton expResult = null;
        JButton result = ButtonFactory.createFontStyleUnderlineButton(editor, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAlignmentButtonsTo method, of class ButtonFactory.
     */
    @Test
    public void testAddAlignmentButtonsTo() {
        System.out.println("addAlignmentButtonsTo");
        JToolBar bar = null;
        DrawingEditor editor = null;
        ButtonFactory.addAlignmentButtonsTo(bar, editor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createToggleGridButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateToggleGridButton() {
        System.out.println("createToggleGridButton");
        DrawingView view = null;
        AbstractButton expResult = null;
        AbstractButton result = ButtonFactory.createToggleGridButton(view);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStrokeCapButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateStrokeCapButton_DrawingEditor() {
        System.out.println("createStrokeCapButton");
        DrawingEditor editor = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createStrokeCapButton(editor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStrokeCapButton method, of class ButtonFactory.
     */
    @Test
    public void testCreateStrokeCapButton_DrawingEditor_ResourceBundleUtil() {
        System.out.println("createStrokeCapButton");
        DrawingEditor editor = null;
        ResourceBundleUtil labels = null;
        JPopupButton expResult = null;
        JPopupButton result = ButtonFactory.createStrokeCapButton(editor, labels);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

  

  



 
    
}
