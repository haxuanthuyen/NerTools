package com.hust.soict.nlp.algorithm.ga;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.hust.soict.nlp.algorithm.knn.ner.Datum;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Luan
 */
class Population {

    public List<NST> nsts;
    public static double muatation=0.4;

    public Population(List<NST> nsts) {
        this.nsts = nsts;
    }
    public Population(List<String> f, int size){
        nsts=new ArrayList<>();
        for(int i=0;i<size-1;i++){
            NST n=new NST(f);
            nsts.add(n);
        }
        NST n=new NST(f,true);
        nsts.add(n);

    }

    public  void sortPopulation()
    {

        for(int i=0;i<nsts.size()-1;i++)
        {
            for(int j=i+1;j<nsts.size();j++)
            {
                if(((NST)nsts.get(j)).fitness>((NST)nsts.get(i)).fitness)
                {
                    Collections.swap(nsts, i, j);
                }
            }
        }
    }


    public void evolution2(List<Datum> train, List<Datum> test, List<String> feature) throws FileNotFoundException, IOException
    {
        double k=1;
        int count1=0;
        int count2=0;
        while(nsts.size()>1)
        {

            for(NST n: nsts)
            {
                n.getFitness2(train.subList(count1,count1+ (int)(k*3000)), test.subList(count2,count2+ (int)(k*2000)), feature);
                Runtime.getRuntime().freeMemory();
            }
            for(int i=0;i<nsts.size()/2;i++)
            {
                Random r=new Random();
                int k2=r.nextInt(nsts.size());
                int l=r.nextInt(nsts.size());
                NST[] p= GAOperation.crossOver(nsts.get(l), nsts.get(k2));

                p[1]= GAOperation.mutation(p[1], muatation);
                p[0]= GAOperation.mutation(p[0], muatation);
                p[0].fitness=p[0].getFitness2(train.subList(count1,count1+ (int)(k*3000)), test.subList(count2,count2+ (int)(k*2000)), feature);
                Runtime.getRuntime().freeMemory();
                System.gc();
                p[1].fitness=p[1].getFitness2(train.subList(count1,count1+ (int)(k*3000)), test.subList(count2,count2+ (int)(k*2000)), feature);
                insertIntoPopulation(p[1]);
                insertIntoPopulation(p[0]);
                //  printPopulation();

            }

            sortPopulation();

            nsts=nsts.subList(0, nsts.size()/2);
            count1=count1+(int) (k*3000);
            count2=count2+(int) (k*2000);
            k=k*Math.sqrt(2.0);

        }
        printPopulation();
    }



    public double evolution(List<Datum> train, List<Datum> test, List<String> feature) throws FileNotFoundException, IOException{
        for(int i=0;i<nsts.size();i++){
            nsts.get(i).fitness=nsts.get(i).getFitness(train, test, feature);
            Runtime.getRuntime().freeMemory();
            System.gc();

        }
        Random r=new Random();
        int k=r.nextInt(nsts.size());
        int l=r.nextInt(nsts.size());
        NST[] p= GAOperation.crossOver(nsts.get(l), nsts.get(k));

        p[1]= GAOperation.mutation(p[1], muatation);
        p[0]= GAOperation.mutation(p[0], muatation);
        p[0].fitness=p[0].getFitness(train, test, feature);
        Runtime.getRuntime().freeMemory();
        System.gc();
        p[1].fitness=p[1].getFitness(train, test, feature);
        insertIntoPopulation(p[1]);
        insertIntoPopulation(p[0]);
        printPopulation();
        double total_fitness = 0;
        for(int i=0;i<nsts.size();i++){
            total_fitness+=nsts.get(i).fitness;
        }
        return total_fitness/nsts.size();

    }
    public  void insertIntoPopulation( NST n) {
        for(NST ns:nsts)
        {

            boolean result=true;
            for(int i=0;i<ns.gens.length;i++)
            {
                if(ns.gens[i]!=n.gens[i]) result=false;
            }
            if(result==true) return;
        }
        NST t = this.nsts.get(0);
        int index = 0;
        for (int i = 0; i < this.nsts.size(); i++) {
            NST ns = this.nsts.get(i);
            if (t.fitness > ns.fitness) {
                index = i;
                t = ns;
            }
        }

        if (t.fitness < n.fitness ) {
            this.nsts.set(index, n);
        }

    }
    public void printPopulation(){
        for(int j=0;j<nsts.size();j++){
            NST n=nsts.get(j);
            for(int i=0;i<n.gens.length;i++){
                System.out.print(n.gens[i]+":");
            }
            System.out.println(n.fitness);
        }
    }





}
