//
//
//
// // To change this license header, choose License Headers in Project Properties.
// // To change this template file, choose Tools | Templates
//// and open the template in the editor.
//
//package org.jhotdraw.draw;
//
//import java.awt.Component;
//import java.util.Collection;
//import javax.swing.JToolBar;
//
///**
// *
// * @author Michalina
//**/
//
//public abstract class FocusedCompStrategy {
//    
//    private Collection <String> keys;
//    private String key;
//    public abstract Component getComponent(int focusedCompIndex, int compQuantity, JToolBar toolBar);
//    
//    public Collection <String> getKeys(){
//        return keys;
//    }
//    
//    protected boolean containKey(String key){
//       return keys.contains(key);
//    }
//    
//    
//    FocusedCompStrategy(String key){
//       if (keys.contains(key)){
//           this.key = key;
//       }
//    }
//  
//}
//
//class ComponentFromTheFront extends FocusedCompStrategy{
//    
//    private  Collection <String> keys ;
//    private String key;
//     
//     
//    @Override
//    public Component getComponent(int focusedCompIndex, int compQuantity, JToolBar toolBar){
//       
//        int j = focusedCompIndex + 1;    
//
//        while (j != focusedCompIndex) {
//            if (j >= compQuantity) {    //niemozliwe zeby comp był null
//                j = 0;
//            }
//            Component comp = toolBar.getComponentAtIndex(j++);
//
//            if (comp != null && comp.isEnabled()) {
//                //comp.requestFocus();
//                return comp;
//            }
//        }
//        return comp;
//    }
//    
//}
//
//class ComponentFromTheFront extends FocusedCompStrategy{
//    
//}
//
//
//
