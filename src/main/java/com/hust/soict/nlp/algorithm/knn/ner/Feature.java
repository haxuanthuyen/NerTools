package com.hust.soict.nlp.algorithm.knn.ner;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luan
 */
public class Feature {

    public String featureName;
    public String stringValue;
    public int intValue;

    public Feature(String featureName) {
        this.featureName = featureName;
    }


    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }


}
