package com.hust.soict.nlp.algorithm.knn.ner;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Luan
 */
public class KNNNER {

    /**
     * @param args the command line arguments
     */
    public List<Datum> trainWords;
    public List<Datum> testWords;
    public static int k =15;

    public static void main(String[] args) {
        // TODO code application logic here
    }

    public double run(String trainFile, String testFile, List<String> features) throws FileNotFoundException, IOException {
        trainWords = DataReaderAndWriter.readData(trainFile);
        System.out.println("TrainWords size: " + trainWords.size());
        testWords = DataReaderAndWriter.readData(testFile);
        System.out.println("TestWord Size: " + testWords.size());
        //Compute feature train and test
        //  HashMap<String, Feature[]> computedTrain=FeatureFactory.computeFeature(trainWords, features);
//      
        List<Datum> computedTrain = FeatureFactory.computeFeature(trainWords, features);
        for (Datum datum : computedTrain) {
            System.out.println(datum.word + "::" + datum.features[13].stringValue);
        }
        System.out.println(computedTrain.size());
        List<Datum> computedTest = FeatureFactory.computeFeature(testWords, features);
        //Classify testFile 

        computedTest = classify(computedTest, computedTrain);
        return computeFscore(computedTest);




    }
    public double run2(List<Datum> trainWords, List<Datum> testWords, List<String> features) throws FileNotFoundException, IOException {
//        trainWords = DataReaderAndWriter.readData(trainFile);
        System.out.println("TrainWords size: " + trainWords.size());
//        testWords = DataReaderAndWriter.readData(testFile);
        System.out.println("TestWord Size: " + testWords.size());
        //Compute feature train and test
        //  HashMap<String, Feature[]> computedTrain=FeatureFactory.computeFeature(trainWords, features);
//      
        List<Datum> computedTrain = FeatureFactory.computeFeature(trainWords, features);
//        for (Datum datum : computedTrain) {
//            System.out.println(datum.word + "::" + datum.features[13].stringValue);
//        }
        // System.out.println(computedTrain.size());
        List<Datum> computedTest = FeatureFactory.computeFeature(testWords, features);
        //Classify testFile 

        int count=0;

        computedTest = classify2(computedTest, computedTrain);
        return -0.02*(double)(features.size())/89+0.98* computeFscore(computedTest);




    }

    public void run(List<String> trainFile, List<String> testFile, List<String> features) throws FileNotFoundException, IOException {
        trainWords = DataReaderAndWriter.readData(trainFile);
        System.out.println("TrainWords size: " + trainWords.size());
        testWords = DataReaderAndWriter.readData(testFile);
//        testWords=trainWords.subList(trainWords.size()-1000, trainWords.size());
//        trainWords=trainWords.subList(0, trainWords.size()-1000);
//        trainWords.subList(trainWords.size()-1500, trainWords.size()).clear();
        System.out.println("TestWord Size: " + testWords.size());
//        System.out.println("TrainWords size: " + trainWords.size());
        //Compute feature train and test
        //  HashMap<String, Feature[]> computedTrain=FeatureFactory.computeFeature(trainWords, features);
//      sout    
//        System.out.println("Computing train and test:"+Calendar.getInstance().getTime());
        List<Datum> computedTrain = FeatureFactory.computeFeature(trainWords, features);
//        for (Datum datum : computedTrain) {
//            System.out.println(datum.word + "::" + datum.features[13].stringValue);
//        }
//        System.out.println(computedTrain.size());
        List<Datum> computedTest = FeatureFactory.computeFeature(testWords, features);
        //Classify testFile 
//        System.out.println("Classifying test:"+Calendar.getInstance().getTime());
        computedTest = classify2(computedTest, computedTrain);
//         System.out.println("Compute F score:"+Calendar.getInstance().getTime());
        computeFscore(computedTest);






    }

    private List<Datum> classify(List<Datum> computedTest, List<Datum> computedTrain) {
        // List<Datum> predictedTest=new ArrayList<Datum>();
        for (int p = 0; p < computedTest.size(); p++) {
            Datum word = computedTest.get(p);
            Feature[] fTest = word.features;
//            HashMap<Double, List<Datum>> hm=new HashMap<Double, List<Datum>>();
            for (int i = 0; i < computedTrain.size(); i++) {
                Datum datum = computedTrain.get(i);
                Feature[] fTrain = computedTrain.get(i).features;

                datum.score = SimilarityFactory.computeSimilarity(fTest, fTrain);
//                if(hm.containsKey(datum.score))
//                    hm.get(datum.score).add(datum);
//                else {
//                    List d=new ArrayList();
//                    d.add(datum);
//                    hm.put(datum.score, d);
//                }
//               for(int j=0;j<trainWord.size();j++)
//                 
//                       System.out.println(trainWord.get(j).score);


            }
            Collections.sort(computedTrain, VALUE_ORDER);


            //select k best candidate
            List<Datum> kbestCandidate = new ArrayList<Datum>();
            //  computedTrain.subList(computedTrain.size()-k-1, computedTrain.size()-1);
            for (int t = computedTrain.size() - k; t < computedTrain.size(); t++) {
                kbestCandidate.add(computedTrain.get(t));
            }
            //int scoreLevel=computedTrain.get(0).features.length;
//            while(kbestCandidate.size()<k && scoreLevel>=0)
//            {
//                if(hm.containsKey(scoreLevel))
//                    kbestCandidate.addAll(hm.get(scoreLevel));
//                scoreLevel--;
//                
//                
//            }
            //   System.out.println("Best Candidate size:"+kbestCandidate.size());
            //  int numORG = 0, numPER = 0, numLOC = 0, numO = 0;
            int numB=0, numI=0, numO=0;
            for (Datum d : kbestCandidate) {
                switch (d.label) {
                    case "B":
                        numB += 1;
                        break;
                    case "I":
                        numI += 1;
                        break;
                    case "O":
                        numO += 1;
                        break;

                }
            }




            int max;
            max = numB > numI ? numB : numI;
            max = max > numO ? max : numO;

//           System.out.println("numPER:"+numPER);
//            System.out.println("numLoc:"+numLOC);
//           
//             System.out.println("numORG:"+numORG);
//           
//              System.out.println("numO:"+numO);
//              System.out.println("nummax:"+max);


            if (max == numB) {
                word.predictedLabel = "B";
            } else if (max == numI) {
                word.predictedLabel = "I";
            }  else {
                word.predictedLabel = "O";
            }
            //System.out.println(word.word + "::" + word.predictedLabel);

        }

//       for(Datum d:computedTest)
//       {
//           System.out.println(d.word+"::"+d.label);
//       }
        return computedTest;

    }
    private List<Datum> classify2(List<Datum> computedTest, List<Datum> computedTrain) {
        // List<Datum> predictedTest=new ArrayList<Datum>();
//        System.out.println("Classifying "+Calendar.getInstance().getTime());
        for (int p = 0; p < computedTest.size(); p++) {
            Datum word = computedTest.get(p);
            //   System.out.println(p);
            Feature[] fTest = word.features;
//            HashMap<Double, List<Datum>> hm=new HashMap<Double, List<Datum>>();

//            System.out.println("Computing score :"+Calendar.getInstance().getTime());
//            System.out.println("Computing score "+System.currentTimeMillis());
            for (int i = 0; i < computedTrain.size(); i++) {
                Datum datum = computedTrain.get(i);
                Feature[] fTrain = computedTrain.get(i).features;

                datum.score = SimilarityFactory.computeSimilarity(fTest, fTrain);
//                if(hm.containsKey(datum.score))
//                    hm.get(datum.score).add(datum);
//                else {
//                    List d=new ArrayList();
//                    d.add(datum);
//                    hm.put(datum.score, d);
//                }
//               for(int j=0;j<trainWord.size();j++)
//                 
//                       System.out.println(trainWord.get(j).score);


            }
//            System.out.println("Sorting:"+System.currentTimeMillis());

//             System.out.println("Sorting score :"+Calendar.getInstance().getTime());
            Collections.sort(computedTrain, VALUE_ORDER);
//            System.out.println("Choosing best candidate :"+Calendar.getInstance().getTime());
//            System.out.println("Choose best candidate "+System.currentTimeMillis());
            //select k best candidate
            List<Datum> kbestCandidate = new ArrayList<Datum>();
            //  computedTrain.subList(computedTrain.size()-k-1, computedTrain.size()-1);
            for (int t = computedTrain.size() - k; t < computedTrain.size(); t++) {
                kbestCandidate.add(computedTrain.get(t));
            }
            //int scoreLevel=computedTrain.get(0).features.length;
//            while(kbestCandidate.size()<k && scoreLevel>=0)
//            {
//                if(hm.containsKey(scoreLevel))
//                    kbestCandidate.addAll(hm.get(scoreLevel));
//                scoreLevel--;
//                
//                
//            }
            //   System.out.println("Best Candidate size:"+kbestCandidate.size());
            int numORG = 0, numPER = 0, numLOC = 0, numO = 0;
            int numB_ORG = 0, numB_PER = 0, numB_LOC = 0;
            int numI_ORG = 0, numI_PER = 0, numI_LOC = 0;

//            int numB=0, numI=0, numO=0;

//            System.out.println("Labeling test  :"+System.currentTimeMillis());

            for (Datum d : kbestCandidate) {
                switch (d.label) {
                    case "B-ORG":
                        numB_ORG += 1;
                        break;
                    case "I-ORG":
                        numI_ORG += 1;
                        break;
                    case "B_LOC":
                        numB_LOC += 1;
                        break;
                    case "I_LOC":
                        numI_LOC += 1;
                        break;
                    case "B_PER":
                        numB_PER += 1;
                        break;
                    case "I_PER":
                        numI_PER += 1;
                        break;
                    case "O":
                        numO += 1;
                        break;

                }
            }




            int max;
            max = numB_PER > numI_PER ? numB_PER : numI_PER;
            max = max > numB_LOC ? max : numB_LOC;
            max = max > numI_LOC ? max : numI_LOC;
            max = max > numI_ORG ? max : numI_ORG;
            max = max > numB_ORG ? max : numB_ORG;
            max = max > numO ? max : numO;

//           System.out.println("numPER:"+numPER);
//            System.out.println("numLoc:"+numLOC);
//           
//             System.out.println("numORG:"+numORG);
//           
//              System.out.println("numO:"+numO);
//              System.out.println("nummax:"+max);


            if (max == numB_PER) {
                word.predictedLabel = "B-PER";
            }
            else if (max == numI_PER) {
                word.predictedLabel = "I-PER";
            }





























            else if (max == numB_LOC) {
                word.predictedLabel = "B-LOC";
            }
            else if (max == numI_LOC) {
                word.predictedLabel = "I-LOC";
            }
            else if (max == numB_ORG) {
                word.predictedLabel = "B-ORG";
            }
            else if (max == numI_ORG) {
                word.predictedLabel = "I-ORG";
            }

            else {
                word.predictedLabel = "O";
            }
//            System.out.println("Finish Labeling");
            //System.out.println(word.word + "::" + word.predictedLabel);

        }

//       for(Datum d:computedTest)
//       {
//           System.out.println(d.word+"::"+d.label);
//       }
        return computedTest;

    }

    static final Comparator<Datum> VALUE_ORDER =
            new Comparator<Datum>() {

                @Override
                public int compare(Datum e1, Datum e2) {


                    return (e1.score < e2.score ? -1
                            : (e1.score == e2.score ? 0 : 1));
                }
            };

    public double computeFscore(List<Datum> classifiedTestFile) {
        // throw new UnsupportedOperationException("Not yet implemented");

//        int numRealOrg=0,
//                numRealLoc=0, numRealPer=0;
//        int predictedOrg=0, predictedLoc=0, predictedPer=0, correctOrg=0, correctLoc=0,
//                correctPer=0;
//        
//       int  numRealB=0, numRealI=0, numRealO=0,
//               predictedB=0,predictedI=0, predictedO=0,
//               correctB=0,correctI=0,correctO=0;
//        
//        for(int i=0;i<classifiedTestFile.size();i++)
//        {
//            Datum d=classifiedTestFile.get(i);
//            switch (d.label) {
//                case "PER":
//                    numRealPer++;
//                    break;
//                case "LOC":
//                    numRealLoc++;
//                    break;
//                case "ORG":
//                    numRealOrg++;
//                    break;
//                case "O":
//                    numRealO++;
//                    break;
//                
//            }
//            switch (d.predictedLabel) {
//                case "PER":
//                    predictedPer++;
//                    break;
//                case "LOC":
//                    predictedLoc++;
//                    break;
//                case "ORG":
//                    predictedOrg++;
//                    break;
//                case "O":
//                    predictedO++;
//                    break;
//               
//            }
//            
//            if(d.label.equals(d.predictedLabel))
//            {
//                 switch (d.predictedLabel) {
//                case "PER":
//                    correctPer++;
//                    break;
//                case "LOC":
//                    correctLoc++;
//                    break;
//                case "ORG":
//                    correctOrg++;
//                    break;
//                case "O":
//                    correctO++;
//                    break;
//               
//            }
//                 
//          
//            
//            
//        }
//            
//        
//        
//        
//    }
//         double f1Per=2.0*correctPer/(predictedPer+numRealPer);
//         double f1Loc=2.0*correctLoc/(predictedLoc+numRealLoc);
//         double f1Org=2.0*correctOrg/(predictedOrg+numRealOrg);
//         
//          
//        
//           double f1=2.0*(correctPer+correctLoc+correctOrg)/
//                   (predictedPer+predictedLoc+predictedOrg+numRealLoc+numRealPer+numRealOrg);
//                System.out.println("F1 Per: "+f1Per);
//                System.out.println("F1 Loc: "+f1Loc);
//             
//                System.out.println("F1 Org: "+f1Org); 
//                System.out.println("F1 : "+f1); 
//            

        double result=0.0;
        for(int i=0;i<classifiedTestFile.size();i++)
        {
            Datum d=classifiedTestFile.get(i);
//            System.out.println(d.word+"~"+d.predictedLabel+"~"+d.label);
            if(!d.label.equals(d.predictedLabel))
                result++;
        }
        System.out.println("Miss classification: "+result);
        System.out.println("Ti le dung : "+(1-result/classifiedTestFile.size()));
        return -result/classifiedTestFile.size();
    }}
