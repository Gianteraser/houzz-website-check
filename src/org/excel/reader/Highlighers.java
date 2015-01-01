/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.excel.reader;

import java.awt.Color;
import java.awt.Component;

import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.ComponentAdapter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.Highlighter;

/**
 *
 * @author sqa
 */
public class Highlighers
{

    private static Highlighter valid;
    private static Highlighter houzz;
    private Highlighers()
    {
    }
    
    public static Highlighers getInstance()
    {
        return HighlighersHolder.INSTANCE;
    }
    
    private static class HighlighersHolder
    {

        private static final Highlighers INSTANCE = new Highlighers();
    }
    // delete this in final version
//    public Highlighter nullHighlight(){
//        if(emptyFields != null){
//            return emptyFields;
//        }
//        HighlightPredicate test = new HighlightPredicate() {
//
//            private boolean negativeValue(Object value) {
//                boolean x = false;
//                if(value instanceof String) {
//                    x = value == null || value.toString().equals("");
//                } 
//                return x;
//            }
//
//            @Override
//            public boolean isHighlighted(Component cmpnt, org.jdesktop.swingx.decorator.ComponentAdapter ca)
//            {
//                return negativeValue(ca.getValue());
//            }
//        };
//        emptyFields = (Highlighter) new ColorHighlighter(test, Color.PINK, null);
//        return emptyFields;
//    }
    
    public Highlighter IsValidHighlight(){
        if(valid != null){
            return valid;
        }

            HighlightPredicate test = new HighlightPredicate() {

                @Override
                public boolean isHighlighted(Component renderer, org.jdesktop.swingx.decorator.ComponentAdapter adapter)
                {
                    return isValid(adapter);
                }
                
                public boolean isValid(ComponentAdapter adapter){                    
                    return adapter.getValueAt(adapter.row, 13).toString().equals("true");
                }    
            
            
        };
        valid = new ColorHighlighter(test, Color.GREEN, null);
        return valid;
    }
    
    public Highlighter OnHouzzHighlight(){
        if(houzz != null){
            return houzz;
        }
        
        HighlightPredicate test = new HighlightPredicate() {

            @Override
            public boolean isHighlighted(Component renderer, org.jdesktop.swingx.decorator.ComponentAdapter adapter) {
                return onHouzz(adapter);
            }
            
            public boolean onHouzz(ComponentAdapter adapter){
                return adapter.getValueAt(adapter.row, 14).toString().equals("On Houzz");
            }
        };
        houzz = new ColorHighlighter(test, Color.red, null);
        return houzz;
    }
}
