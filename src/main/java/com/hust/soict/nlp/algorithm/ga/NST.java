package com.hust.soict.nlp.algorithm.ga;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.hust.soict.nlp.algorithm.knn.ner.Datum;
import com.hust.soict.nlp.algorithm.knn.ner.KNNNER;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Luan
 */
public class NST {

    boolean[] gens;
    double fitness = 0.0;

    public NST(List<String> f) {

        gens = new boolean[f.size()];
        for (int i = 0; i < gens.length; i++) {
            Random r = new Random();
            gens[i] = r.nextBoolean();
        }

    }

    public NST(List<String> f, boolean t) {

        gens = new boolean[f.size()];
        for (int i = 0; i < gens.length; i++) {

            gens[i] = t;
        }

    }

    NST(boolean[] gens) {
        this.gens = gens;
    }

    public double getFitness(List<Datum> trainWords, List<Datum> testWords, List<String> features) throws FileNotFoundException, IOException {


        if (this.fitness == 0.0) {
            KNNNER knn = new KNNNER();
//        NaiveBayes nb=new NaiveBayes();
            List<String> f = new ArrayList<>();
            int numTrue = 0;
            for (int i = 0; i < gens.length; i++) {
                if (gens[i] == true) {

                    f.add(features.get(i));
                    numTrue++;
                }
            }

            double error = 0.0;
            Random r = new Random();
            int w = 0;
            while (w < 3) {



                int k = r.nextInt(trainWords.size() - 4000);
                List<Datum> train = trainWords.subList(k, k + 4000);
                int p = r.nextInt(testWords.size() - 1000);
                List<Datum> test = testWords.subList(p, p + 1000);
                error += knn.run2(train, test, f);

//             Su dung incosistency
//            trainWords.addAll(testWords);
//            trainWords=FeatureFactory.computeFeature(trainWords, f);
//            error+=inconsistency.InconsistenceFactory.computeInconsistencyRate(trainWords);
                w++;
            }
            error = error / 3;
//        Ket thuc inconsistency
            System.out.println("fitness " + error);
            return error;
        } else {
            return fitness;
        }
    }

    double getFitness2(List<Datum> train, List<Datum> test, List<String> features) throws FileNotFoundException, IOException {


        KNNNER knn = new KNNNER();
//        NaiveBayes nb=new NaiveBayes();
        List<String> f = new ArrayList<>();
        int numTrue = 0;
        for (int i = 0; i < gens.length; i++) {
            if (gens[i] == true) {

                f.add(features.get(i));
                numTrue++;
            }
        }

        double error = 0.0;
        Random r = new Random();
//        int w=0;





        error = knn.run2(train, test, f);

        System.out.println("fitness " + error);
        if(fitness==0.0)fitness=error;
        else fitness=(fitness+error*Math.sqrt(2))/(Math.sqrt(2)+1);
        return fitness;



    }
}
