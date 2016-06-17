package com.hust.soict.nlp.algorithm.ga;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;

/**
 *
 * @author Luan
 */
public class GAOperation {

    public static NST[] crossOver(NST a, NST b) {
        boolean[] g1 = new boolean[a.gens.length];
        boolean[] g2 = new boolean[a.gens.length];
        for (int i = 0; i < g1.length; i++) {
            if (i < g1.length / 2) {
                g1[i] = a.gens[i];
                g2[i] = b.gens[i];
            } else {
                g1[i] = b.gens[i];
                g2[i] = a.gens[i];
            }
        }

        NST p1 = new NST(g1);
        NST p2 = new NST(g2);
        return new NST[]{p1, p2};
    }

    public static NST mutation(NST a, double t) {
        Random r = new Random();
        int k = r.nextInt(a.gens.length);
        double s = r.nextDouble();
        if (s < t) {
            a.gens[k] = !a.gens[k];
        }
        return a;

    }


}