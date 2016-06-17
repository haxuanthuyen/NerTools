package edu.stanford.nlp.ie.crf;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kho
 */
public class Population_GA {
    public List NSTs;
    public int size;
    public static NST_GA currentNST;
    public Population_GA(List nsts)
    {
        this.NSTs=nsts;
    }
    public Population_GA(int s)
    {
        NSTs=new ArrayList();
//        for(int i=0;i<s;i++)
//        {
//            NST_GA nst=new NST_GA();
//            NSTs.add(nst);
//        }
        while(NSTs.size()<s)
        {
            NST_GA nst=new NST_GA();
            if( !isConflictNST(nst, NSTs))
                NSTs.add(nst);
        }
        this.size=s;
        currentNST=(NST_GA)NSTs.get(0);
    }

    void evolution() throws IOException {
        this.getFitness();
        this.breed();
        this.mutation();
        this.printTest();
        
    }

    private void breed() {
        
    }

    private void getFitness() throws IOException {
        for(int i=0; i<this.NSTs.size();i++)
        {
            currentNST=(NST_GA) NSTs.get(i);
            for(int j=0;j<currentNST.gens.size();j++)
            {
                System.out.println(((Gen_GA)currentNST.gens.get(j)).featureName+":"+
                        ((Gen_GA)currentNST.gens.get(j)).value);
            }
           // System.out.println(((Gen_GA)currentNST.gens.get(0)).featureName);
          if(currentNST!=null)  runMEMM();
            NSTs.set(i, currentNST);
            
        }
        for(int i=0;i<size;i++)
        {
            System.out.println(((NST_GA)NSTs.get(i)).score);
        }
        
    }

    private void mutation() {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    private void runMEMM() throws IOException {
        ProcessBuilder pb =
	    new ProcessBuilder("java", "-cp", "build/classes", "-Xmx1G", "MEMM", "trainWithFeatures.json", "testWithFeatures.json", "");
        pb.redirectErrorStream(true);
        Process proc = pb.start();

	BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
	String line = br.readLine();
	while (line != null) {
	    System.out.println(line);
	    line = br.readLine();
	}
    }

    private void printTest() {
       // throw new UnsupportedOperationException("Not yet implemented");
        for(Object NST: NSTs)
        {
            System.out.println("Score : "+((NST_GA)NST).score);
        }
    }
    public final boolean isConflictNST(NST_GA nst, List<NST_GA> ls)
    {
        boolean result=false;
        for(int i=0;i<ls.size();i++)
        {
            NST_GA n=ls.get(i);
            boolean isConfict=true;
            for(int j=0;j<n.gens.size();j++)
            {
                if(!nst.gens.get(j).value.equals(n.gens.get(j).value))
                {
                    isConfict=false;
                    break;
                }
            }
            if(isConfict) result=true;
        }
        return result;
    }
    
    
}
