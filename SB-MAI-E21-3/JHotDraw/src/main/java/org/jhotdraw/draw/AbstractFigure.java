/*
 * @(#)AbstractFigure.java   6.0  2000-02-13
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

import org.jhotdraw.beans.AbstractBean;

import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;
import javax.swing.undo.*;

import org.jhotdraw.geom.*;
import static org.jhotdraw.samples.svg.SVGAttributeKeys.OPACITY;


public abstract class AbstractFigure
        extends AbstractBean
        implements Figure {

    protected EventListenerList listenerList = new EventListenerList();
    private Drawing drawing;
    private boolean isSelectable = true;
    private boolean isRemovable = true;
    private boolean isVisible = true;
    private boolean isTransformable = true;
    /**
     * This variable is used to prevent endless change loops.
     * We increase its value on each invocation of willChange() and
     * decrease it on each invocation of changed().
     */
    protected int changingDepth = 0;

    public AbstractFigure() {
    }

    
    public void addFigureListener(FigureListener l) {
        listenerList.add(FigureListener.class, l);
    }

    public void removeFigureListener(FigureListener l) {
        listenerList.remove(FigureListener.class, l);
    }

    public void addNotify(Drawing d) {
        this.drawing = d;
        fireFigureAdded();
    }

    public void removeNotify(Drawing d) {
        fireFigureRemoved();
        this.drawing = null;
    }

    protected Drawing getDrawing() {
        return drawing;
    }

    protected Object getLock() {
        return (getDrawing() == null) ? this : getDrawing().getLock();
    }


    public void fireAreaInvalidated() {
        fireAreaInvalidated(getDrawingArea());
    }


    protected void fireAreaInvalidated(Rectangle2D.Double invalidatedArea) {
        if (listenerList.getListenerCount() > 0) {
            FigureEvent event = null;
            // Notify all listeners that have registered interest for
            // Guaranteed to return a non-null array
            Object[] listeners = listenerList.getListenerList();
            // Process the listeners last to first, notifying
            // those that are interested in this event
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == FigureListener.class) {
                    // Lazily create the event:
                    if (event == null) {
                        event = new FigureEvent(this, invalidatedArea);
                    }
                    ((FigureListener) listeners[i + 1]).areaInvalidated(event);
                }
            }
        }
    }


    protected void fireAreaInvalidated(FigureEvent event) {
        // Notify all listeners that have registered interest for
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == FigureListener.class) {
                ((FigureListener) listeners[i + 1]).areaInvalidated(event);
            }
        }
    }


    protected void fireFigureRequestRemove() {
        if (listenerList.getListenerCount() > 0) {
            FigureEvent event = null;
            // Notify all listeners that have registered interest for
            // Guaranteed to return a non-null array
            Object[] listeners = listenerList.getListenerList();
            // Process the listeners last to first, notifying
            // those that are interested in this event
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == FigureListener.class) {
                    // Lazily create the event:
                    if (event == null) {
                        event = new FigureEvent(this, getBounds());
                    }
                    ((FigureListener) listeners[i + 1]).figureRequestRemove(event);
                }
            }
        }
    }


    protected void fireFigureAdded() {
        if (listenerList.getListenerCount() > 0) {
            FigureEvent event = null;
            // Notify all listeners that have registered interest for
            // Guaranteed to return a non-null array
            Object[] listeners = listenerList.getListenerList();
            // Process the listeners last to first, notifying
            // those that are interested in this event
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == FigureListener.class) {
                    // Lazily create the event:
                    if (event == null) {
                        event = new FigureEvent(this, getBounds());
                    }
                    ((FigureListener) listeners[i + 1]).figureAdded(event);
                }
            }
        }
    }


    protected void fireFigureRemoved() {
        if (listenerList.getListenerCount() > 0) {
            FigureEvent event = null;
            // Notify all listeners that have registered interest for
            // Guaranteed to return a non-null array
            Object[] listeners = listenerList.getListenerList();
            // Process the listeners last to first, notifying
            // those that are interested in this event
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == FigureListener.class) {
                    // Lazily create the event:
                    if (event == null) {
                        event = new FigureEvent(this, getBounds());
                    }
                    ((FigureListener) listeners[i + 1]).figureRemoved(event);
                }
            }
        }
    }

    public void fireFigureChanged() {
        fireFigureChanged(getBounds());
    }


    protected void fireFigureChanged(Rectangle2D.Double changedArea) {
        if (listenerList.getListenerCount() > 0) {
            FigureEvent event = null;
            // Notify all listeners that have registered interest for
            // Guaranteed to return a non-null array
            Object[] listeners = listenerList.getListenerList();
            // Process the listeners last to first, notifying
            // those that are interested in this event
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == FigureListener.class) {
                    // Lazily create the event:
                    if (event == null) {
                        event = new FigureEvent(this, changedArea);
                    }
                    ((FigureListener) listeners[i + 1]).figureChanged(event);
                }
            }
        }
    }

    protected void fireFigureChanged(FigureEvent event) {
        if (listenerList.getListenerCount() > 0) {
            // Notify all listeners that have registered interest for
            // Guaranteed to return a non-null array
            Object[] listeners = listenerList.getListenerList();
            // Process the listeners last to first, notifying
            // those that are interested in this event
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == FigureListener.class) {
                    // Lazily create the event:
                    ((FigureListener) listeners[i + 1]).figureChanged(event);
                }
            }
        }
    }


    protected void fireAttributeChanged(AttributeKey attribute, Object oldValue, Object newValue) {
        if (listenerList.getListenerCount() > 0 &&
                (oldValue == null || newValue == null || !oldValue.equals(newValue))) {
            FigureEvent event = null;
            // Notify all listeners that have registered interest for
            // Guaranteed to return a non-null array
            Object[] listeners = listenerList.getListenerList();
            // Process the listeners last to first, notifying
            // those that are interested in this event
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == FigureListener.class) {
                    // Lazily create the event:
                    if (event == null) {
                        event = new FigureEvent(this, attribute, oldValue, newValue);
                    }
                    ((FigureListener) listeners[i + 1]).attributeChanged(event);
                }
            }
        }
    }


    protected void fireFigureHandlesChanged() {
        Rectangle2D.Double changedArea = getDrawingArea();
        if (listenerList.getListenerCount() > 0) {
            FigureEvent event = null;
            // Notify all listeners that have registered interest for
            // Guaranteed to return a non-null array
            Object[] listeners = listenerList.getListenerList();
            // Process the listeners last to first, notifying
            // those that are interested in this event
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == FigureListener.class) {
                    // Lazily create the event:
                    if (event == null) {
                        event = new FigureEvent(this, changedArea);
                    }
                    ((FigureListener) listeners[i + 1]).figureHandlesChanged(event);
                }
            }
        }
    }


    protected void fireUndoableEditHappened(UndoableEdit edit) {
        if (getDrawing() != null) {
            getDrawing().fireUndoableEditHappened(edit);
        }
    }


    @Override
    public AbstractFigure clone() {
        AbstractFigure that = (AbstractFigure) super.clone();
        that.listenerList = new EventListenerList();
        that.drawing = null; // Clones need to be explictly added to a drawing
        return that;
    }

    public final AbstractFigure basicClone(HashMap<Figure, Figure> oldToNew) {
        return null;
    }

    public void remap(Map<Figure, Figure> oldToNew, boolean disconnectIfNotInMap) {
    }

    public Collection<Handle> createHandles(int detailLevel) {
        LinkedList<Handle> handles = new LinkedList<Handle>();
        switch (detailLevel) {
            case -1:
                handles.add(new BoundsOutlineHandle(this,false,true));
                break;
            case 0:
                ResizeHandleKit.addResizeHandles(this, handles);
                break;
        }
        return handles;
    }

    public Cursor getCursor(Point2D.Double p) {
        if (contains(p)) {
            return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        } else {
            return Cursor.getDefaultCursor();
        }
    }

    public final void setBounds(Rectangle2D.Double bounds) {
        setBounds(new Point2D.Double(bounds.x, bounds.y), new Point2D.Double(bounds.x + bounds.width, bounds.y + bounds.height));
    }

    public void setBounds(Point2D.Double anchor, Point2D.Double lead) {
        Point2D.Double oldAnchor = getStartPoint();
        Point2D.Double oldLead = getEndPoint();
        if (!oldAnchor.equals(anchor) || !oldLead.equals(lead)) {
            willChange();
            setBounds(anchor, lead);
            changed();
            fireUndoableEditHappened(new SetBoundsEdit(this, oldAnchor, oldLead, anchor, lead));
        }
    }


    public boolean canConnect() {
        return true;
    }


    protected void invalidate() {
    }

    protected boolean isChanging() {
        return changingDepth != 0;
    }

    protected int getChangingDepth() {
        return changingDepth;
    }


    public void willChange() {
        if (changingDepth == 0) {
            fireAreaInvalidated();
            invalidate();
        }
        changingDepth++;
    }

    protected void validate() {
    }

 
    public void changed() {
        if (changingDepth == 1) {
            validate();
            fireFigureChanged(getDrawingArea());
        } else if (changingDepth < 0) {
            throw new InternalError("changed was called without a prior call to willChange.");
        }
        changingDepth--;
    }

    /**
     * Returns the Figures connector for the specified location.
     * By default a ChopBoxConnector is returned.
     *
     *
     * @see ChopRectangleConnector
     */
    public Connector findConnector(Point2D.Double p, ConnectionFigure prototype) {
        return new ChopRectangleConnector(this);
    }

    public boolean includes(Figure figure) {
        return figure == this;
    }

    public Figure findFigureInside(Point2D.Double p) {
        return (contains(p)) ? this : null;
    }

    public Connector findCompatibleConnector(Connector c, boolean isStart) {
        return new ChopRectangleConnector(this);
    }

    
    public Collection<Action> getActions(Point2D.Double p) {
        return Collections.emptyList();
    }


    public Tool getTool(Point2D.Double p) {
        return null;
    }

    /**
     * Handles a mouse click.
     */
    public boolean handleMouseClick(Point2D.Double p, MouseEvent evt, DrawingView view) {
        return false;
    }

    public boolean handleDrop(Point2D.Double p, Collection<Figure> droppedFigures, DrawingView view) {
        return false;
    }

    public Point2D.Double getEndPoint() {
        Rectangle2D.Double r = getBounds();
        return new Point2D.Double(r.x + r.width, r.y + r.height);
    }

    public Point2D.Double getStartPoint() {
        Rectangle2D.Double r = getBounds();
        return new Point2D.Double(r.x, r.y);
    }

    public Dimension2DDouble getPreferredSize() {
        Rectangle2D.Double r = getBounds();
        return new Dimension2DDouble(r.width, r.height);
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setSelectable(boolean newValue) {
        boolean oldValue = isSelectable;
        isSelectable = newValue;
        firePropertyChange("selectable", oldValue, newValue);
    }

    public boolean isRemovable() {
        return isRemovable;
    }

    public void setRemovable(boolean newValue) {
        boolean oldValue = isRemovable;
        isRemovable = newValue;
        firePropertyChange("removable", oldValue, newValue);
    }

    public boolean isTransformable() {
        return isTransformable;
    }

    public void setTransformable(boolean newValue) {
        boolean oldValue = isTransformable;
        isTransformable = newValue;
        firePropertyChange("transformable", oldValue, newValue);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean newValue) {
        if (newValue != isVisible) {
            willChange();
            isVisible = newValue;
            changed();
        }
    }

    public Collection<Figure> getDecomposition() {
        LinkedList<Figure> list = new LinkedList<Figure>();
        list.add(this);
        return list;
    }

    protected FontRenderContext getFontRenderContext() {
        FontRenderContext frc = null;
        if (frc == null) {
            frc = new FontRenderContext(new AffineTransform(), Options.isTextAntialiased(), Options.isFractionalMetrics());
        }
        return frc;
    }

    public void requestRemove() {
        fireFigureRequestRemove();
    }

    public int getLayer() {
        return 0;
    }

    public String getToolTipText(Point2D.Double p) {
        return null;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1));
        buf.append('@');
        buf.append(hashCode());
        return buf.toString();
    }

    public Collection<Connector> getConnectors(ConnectionFigure prototype) {
        LinkedList<Connector> connectors = new LinkedList<Connector>();
        connectors.add(new ChopRectangleConnector(this));
        return connectors;
    }
    
        public void draw(Graphics2D g)  {
        double opacity = OPACITY.get(this);
        opacity = Math.min(Math.max(0d, opacity), 1d);
        if (opacity != 0d) {
            if (opacity != 1d) {
                Rectangle2D.Double drawingArea = getDrawingArea();
                
                Rectangle2D clipBounds = g.getClipBounds();
                if (clipBounds != null) {
                    Rectangle2D.intersect(drawingArea, clipBounds, drawingArea);
                }
                
                if (! drawingArea.isEmpty()) {
                    
                    BufferedImage bufforedImage = new BufferedImage(
                            Math.max(1, (int) ((2 + drawingArea.width) * g.getTransform().getScaleX())),
                            Math.max(1, (int) ((2 + drawingArea.height) * g.getTransform().getScaleY())),
                            BufferedImage.TYPE_INT_ARGB);
                    Graphics2D graphic = bufforedImage.createGraphics();
                    graphic.scale(g.getTransform().getScaleX(), g.getTransform().getScaleY());
                    graphic.translate((int) -drawingArea.x, (int) -drawingArea.y);
                    graphic.setRenderingHints(g.getRenderingHints());
                    drawFigure(graphic);
                    graphic.dispose();
                    Composite savedComposite = g.getComposite();
                    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) opacity));
                    g.drawImage(bufforedImage, (int) drawingArea.x, (int) drawingArea.y,
                            2 + (int) drawingArea.width, 2 + (int) drawingArea.height, null);
                    g.setComposite(savedComposite);
                }
            } else {
                drawFigure(g);
            }
        }
    }
    
    

    public void drawFigure(Graphics2D g){};
    
}
