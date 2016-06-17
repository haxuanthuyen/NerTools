package com.hust.soict.nlp.algorithm.knn.ner;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luan
 */
public class Datum {

    public String word;
    public String label;
    public String posTag;
    public double score;
    public Feature[] features;
    public String predictedLabel;


    public Datum(String word, String posTag, String label) {
        this.word = word;
        this.label = label;
        this.posTag = posTag;
    }

    public String getLabel() {
        return label;
    }

    public String getPosTag() {
        return posTag;
    }

    public void setPosTag(String posTag) {
        this.posTag = posTag;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Datum(String word, String label) {
        this.word = word;
        this.label = label;
    }


}
