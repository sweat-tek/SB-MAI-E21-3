/*
 * @(#)BoxHandleKit.java  2.0  2008-05-11
 *
 * Copyright (c) 1996-2008 by the original authors of JHotDraw
 * and all its contributors.
 * All rights reserved.
 *
 * The copyright of this software is owned by the authors and  
 * contributors of the JHotDraw project ("the copyright holders").  
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * the copyright holders. For details see accompanying license terms. 
 */
package org.jhotdraw.draw;

import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import org.jhotdraw.util.ResourceBundleUtil;

/**
 * A set of utility methods to create handles which resize a Figure by
 * using its <code>setBounds</code> method, if the Figure is transformable.
 * 
 * @author Werner Randelshofer
 * @version 2.0 2008-05-11 Added keyboard support. 
 * Handle attributes are now read from DrawingEditor.
 * <br>1.1 2008-02-28 Only resize a figure, if it is transformable. 
 * <br>1.0 2007-04-14 Created.
 */
        
public class ResizeHandleKit {

    private final static boolean DEBUG = false;

    /** Creates a new instance. */
    public ResizeHandleKit() {
    }

    /**
     * Creates handles for each corner of a
     * figure and adds them to the provided collection.
     */
    static public void addCornerResizeHandles(Figure f, Collection<Handle> handles) {
        handles.add(new SouthEastHandle(f));
        handles.add(new SouthWestHandle(f));
        handles.add(new NorthEastHandle(f));
        handles.add(new NorthWestHandle(f));
    }

    /**
     * Fills the given Vector with handles at each
     * the north, south, east, and west of the figure.
     */
    static public void addEdgeResizeHandles(Figure f, Collection<Handle> handles) {
        handles.add(new SouthHandle(f));
        handles.add(new NorthHandle(f));
        handles.add(new EastHandle(f));
        handles.add(new WestHandle(f));
    }

    /**
     * Fills the given Vector with handles at each
     * the north, south, east, and west of the figure.
     */
    static public void addResizeHandles(Figure f, Collection<Handle> handles) {
        handles.add(new BoundsOutlineHandle(f));
        addCornerResizeHandles(f, handles);
        addEdgeResizeHandles(f, handles);
    }
    
    private static class ResizeHandle extends LocatorHandle {

        private int dx,  dy;
        Object geometry;
        public boolean isNorth, isWest, isSouth, isEast;

        ResizeHandle(Figure owner, Locator loc) {
            super(owner, loc);
            isNorth = isWest = isSouth = isEast = false;
        }
        
        public void setDir(String dir){
            for(int i=0; i < dir.length();++i){
                if     (dir.charAt(i)=='N'){isNorth=true;}
                else if(dir.charAt(i)=='S'){isSouth=true;}
                if     (dir.charAt(i)=='W'){isWest=true;}
                else if(dir.charAt(i)=='E'){isEast=true;}
            }
        }

        @Override
        public String getToolTipText(Point p) {
            ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");
            return labels.getString("handle.resize.toolTipText");
        }

        /**
         * Draws this handle.
         * <p>
         * If the figure is transformable, the handle is drawn as a filled rectangle.
         * If the figure is not transformable, the handle is drawn as an unfilled
         * rectangle.
         */
        @Override
        public void draw(Graphics2D g) {
            if (getEditor().getTool().supportsHandleInteraction()) {
                if (getOwner().isTransformable()) {
                    drawRectangle(g,
                            (Color) getEditor().getHandleAttribute(HandleAttributeKeys.RESIZE_HANDLE_FILL_COLOR),
                            (Color) getEditor().getHandleAttribute(HandleAttributeKeys.RESIZE_HANDLE_STROKE_COLOR));
                } else {
                    drawRectangle(g,
                            (Color) getEditor().getHandleAttribute(HandleAttributeKeys.NULL_HANDLE_FILL_COLOR),
                            (Color) getEditor().getHandleAttribute(HandleAttributeKeys.NULL_HANDLE_STROKE_COLOR));
                }
            } else {
                drawRectangle(g,
                        (Color) getEditor().getHandleAttribute(HandleAttributeKeys.HANDLE_FILL_COLOR_DISABLED),
                        (Color) getEditor().getHandleAttribute(HandleAttributeKeys.HANDLE_STROKE_COLOR_DISABLED));
            }
        }

        public void trackStart(Point anchor, int modifiersEx) {
            geometry = getOwner().getTransformRestoreData();
            Point location = getLocation();
            dx = -anchor.x + location.x;
            dy = -anchor.y + location.y;
        }

        public void trackStep(Point anchor, Point lead, int modifiersEx) {
            if (getOwner().isTransformable()) {
                Point2D.Double p = view.viewToDrawing(new Point(lead.x + dx, lead.y + dy));
                view.getConstrainer().constrainPoint(p);

                if (AttributeKeys.TRANSFORM.get(getOwner()) != null) {
                    try {
                        AttributeKeys.TRANSFORM.get(getOwner()).inverseTransform(p, p);
                    } catch (NoninvertibleTransformException ex) {
                        if (DEBUG) {
                            ex.printStackTrace();
                        }
                    }
                }

                trackStepNormalized(p);
            }
        }

        public void trackEnd(Point anchor, Point lead, int modifiersEx) {
            if (getOwner().isTransformable()) {
                fireUndoableEditHappened(
                        new GeometryEdit(getOwner(), geometry, getOwner().getTransformRestoreData()));
            }
        }

        protected void trackStepNormalized(Point2D.Double p) {
        }

        protected void setBounds(Point2D.Double anchor, Point2D.Double lead) {
            Figure f = getOwner();
            f.willChange();
            f.setBounds(anchor, lead);
            f.changed();
        }
        
        @Override
        public void keyPressed(KeyEvent evt) { //Function gets key events and which handle is being used as an input
            Rectangle2D.Double r = getOwner().getBounds();
            int N,W,S,E,vertical,horizontal,pressedKey;
            N=W=S=E=vertical=horizontal=0;
            pressedKey=evt.getKeyCode();
            
                if     (pressedKey==KeyEvent.VK_UP)       {vertical = -1;} //if up key is pressed elevate the handle by 1 pixel
                else if(pressedKey==KeyEvent.VK_DOWN)     {vertical =  1;} //if down key is pressed lower the handle by 1 pixel
                else if(pressedKey==KeyEvent.VK_LEFT)     {horizontal=-1;} //if left key is pressed push handle to left by 1 pixel
                else if(pressedKey==KeyEvent.VK_RIGHT)    {horizontal= 1;} //if right key is pressed push handle to right by 1 pixel

                if      (isNorth){N=vertical;}     // If it is a North handle
                else if (isSouth){S=vertical;}     // If it is a South handle
                if      (isWest) {W=horizontal;}   // If it is a West handle
                else if (isEast) {E=horizontal;}   // If it is East handle

                setBounds(      //modifies the size
                    new Point2D.Double(r.x+W, r.y+N),
                    new Point2D.Double(r.x+E + r.width, r.y + r.height+S));
                evt.consume();
        }
    }

    private static class NorthEastHandle extends ResizeHandle {

        NorthEastHandle(Figure owner) {
            super(owner, RelativeLocator.northEast(true));
            setDir("NE");
        }

        protected void trackStepNormalized(Point2D.Double p) {
            Rectangle2D.Double r = getOwner().getBounds();
            setBounds(
                    new Point2D.Double(r.x, Math.min(r.y + r.height - 1, p.y)),
                    new Point2D.Double(Math.max(r.x, p.x), r.y + r.height));
        }
           
        @Override
        public Cursor getCursor() {
            return Cursor.getPredefinedCursor(
                    getOwner().isTransformable() ? Cursor.NE_RESIZE_CURSOR : Cursor.DEFAULT_CURSOR);
        }
    }

    private static class EastHandle extends ResizeHandle {

        EastHandle(Figure owner) {
            super(owner, RelativeLocator.east(true));
            setDir("E");
        }

        protected void trackStepNormalized(Point2D.Double p) {
            Rectangle2D.Double r = getOwner().getBounds();
            setBounds(
                    new Point2D.Double(r.x, r.y),
                    new Point2D.Double(Math.max(r.x + 1, p.x), r.y + r.height));
        }

        @Override
        public Cursor getCursor() {
            return Cursor.getPredefinedCursor(
                    getOwner().isTransformable() ? Cursor.E_RESIZE_CURSOR : Cursor.DEFAULT_CURSOR);
        }
    }

    private static class NorthHandle extends ResizeHandle {

        NorthHandle(Figure owner) {
            super(owner, RelativeLocator.north(true));
            setDir("N");
        }

        protected void trackStepNormalized(Point2D.Double p) {
            Rectangle2D.Double r = getOwner().getBounds();
            setBounds(
                    new Point2D.Double(r.x, Math.min(r.y + r.height - 1, p.y)),
                    new Point2D.Double(r.x + r.width, r.y + r.height));
        }
        
        @Override
        public Cursor getCursor() {
            return Cursor.getPredefinedCursor(
                    getOwner().isTransformable() ? Cursor.N_RESIZE_CURSOR : Cursor.DEFAULT_CURSOR);
        }
    }

    private static class NorthWestHandle extends ResizeHandle {

        NorthWestHandle(Figure owner) {
            super(owner, RelativeLocator.northWest(true));
            setDir("NW");
        }

        protected void trackStepNormalized(Point2D.Double p) {
            Rectangle2D.Double r = getOwner().getBounds();
            setBounds(
                    new Point2D.Double(Math.min(r.x + r.width - 1, p.x), Math.min(r.y + r.height - 1, p.y)),
                    new Point2D.Double(r.x + r.width, r.y + r.height));
        }

        @Override
        public Cursor getCursor() {
            return Cursor.getPredefinedCursor(
                    getOwner().isTransformable() ? Cursor.NW_RESIZE_CURSOR : Cursor.DEFAULT_CURSOR);
        }
    }

    private static class SouthEastHandle extends ResizeHandle {

        SouthEastHandle(Figure owner) {
            super(owner, RelativeLocator.southEast(true));
            setDir("SE");
        }

        protected void trackStepNormalized(Point2D.Double p) {
            Rectangle2D.Double r = getOwner().getBounds();
            setBounds(
                    new Point2D.Double(r.x, r.y),
                    new Point2D.Double(Math.max(r.x + 1, p.x), Math.max(r.y + 1, p.y)));
        }

        @Override
        public Cursor getCursor() {
            return Cursor.getPredefinedCursor(
                    getOwner().isTransformable() ? Cursor.SE_RESIZE_CURSOR : Cursor.DEFAULT_CURSOR);
        }
    }

    private static class SouthHandle extends ResizeHandle {

        SouthHandle(Figure owner) {
            super(owner, RelativeLocator.south(true));
            setDir("S");
        }

        protected void trackStepNormalized(Point2D.Double p) {
            Rectangle2D.Double r = getOwner().getBounds();
            setBounds(
                    new Point2D.Double(r.x, r.y),
                    new Point2D.Double(r.x + r.width, Math.max(r.y + 1, p.y)));
        }

        @Override
        public Cursor getCursor() {
            return Cursor.getPredefinedCursor(
                    getOwner().isTransformable() ? Cursor.S_RESIZE_CURSOR : Cursor.DEFAULT_CURSOR);
        }
    }

    private static class SouthWestHandle extends ResizeHandle {

        SouthWestHandle(Figure owner) {
            super(owner, RelativeLocator.southWest(true));
            setDir("SW");
        }

        protected void trackStepNormalized(Point2D.Double p) {
            Rectangle2D.Double r = getOwner().getBounds();
            setBounds(
                    new Point2D.Double(Math.min(r.x + r.width - 1, p.x), r.y),
                    new Point2D.Double(r.x + r.width, Math.max(r.y + 1, p.y)));
        }

        @Override
        public Cursor getCursor() {
            return Cursor.getPredefinedCursor(
                    getOwner().isTransformable() ? Cursor.SW_RESIZE_CURSOR : Cursor.DEFAULT_CURSOR);
        }
    }

    private static class WestHandle extends ResizeHandle {

        WestHandle(Figure owner) {
            super(owner, RelativeLocator.west(true));
            setDir("W");
        }

        protected void trackStepNormalized(Point2D.Double p) {
            Rectangle2D.Double r = getOwner().getBounds();
            setBounds(
                    new Point2D.Double(Math.min(r.x + r.width - 1, p.x), r.y),
                    new Point2D.Double(r.x + r.width, r.y + r.height));
        }

        @Override
        public Cursor getCursor() {
            return Cursor.getPredefinedCursor(
                    getOwner().isTransformable() ? Cursor.W_RESIZE_CURSOR : Cursor.DEFAULT_CURSOR);
        }
    }
}
