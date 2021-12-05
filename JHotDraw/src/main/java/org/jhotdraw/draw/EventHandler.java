/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.draw;

import java.io.Serializable;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

/**
 *
 * @author Michalina
 */
class EventHandler extends FigureAdapter implements UndoableEditListener, Serializable {
    
    private static EventHandler evtHandler;
    private final AbstractCompositeFigure outer;

    EventHandler(final AbstractCompositeFigure outer) {
        this.outer = outer;
    }

    EventHandler(final AbstractCompositeFigure outer) {
        if (evtHandler == null) {
            evtHandler = new EventHandler(outer);
        }
        this.outer = outer;
    }
    (ERROR)

    public static EventHandler getEventHandler() {
        createEventHandler();
        return evtHandler;
    }

    @Override
    public void figureRequestRemove(FigureEvent e) {
        outer.remove(e.getFigure());
    }

    @Override
    public void figureChanged(FigureEvent e) {
        outer.invalidate();
        fireFigureChanged(e.getInvalidatedArea());
    }

    @Override
    public void areaInvalidated(FigureEvent e) {
        fireAreaInvalidated(e);
    }

    public void undoableEditHappened(UndoableEditEvent e) {
        fireUndoableEditHappened(e.getEdit());
    }

    @Override
    public void attributeChanged(FigureEvent e) {
        outer.invalidate();
    }

    @Override
    public void figureAdded(FigureEvent e) {
        outer.invalidate();
    }

    @Override
    public void figureRemoved(FigureEvent e) {
        outer.invalidate();
    }
    
}
