package edu.stanford.nlp.ie.crf;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kho
 */
public class Gen_GA {
    
    
    public String featureName;
    public Object value;
    
    
    public Gen_GA(String name, boolean value)
    {
        this.featureName=name;
        this.value=value;
    }

    Gen_GA(String name, Object value) {
        this.featureName=name;
        this.value=value;
    }
    
}
