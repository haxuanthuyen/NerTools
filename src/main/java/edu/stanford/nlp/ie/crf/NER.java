package edu.stanford.nlp.ie.crf;

import java.io.*;
import java.util.*;


/** Do not modify this class
 *  The submit script does not use this class 
 *  It directly calls the methods of FeatureFactory and MEMM classes.
 */
public class NER {
    public static Population_GA pp;
    public static NST_GA currentNST;
    public static List<String> DEFINE_DICT=new ArrayList<>();
    public static List<String> LOC_INDICATE_ADVERB_DICT=new ArrayList<>();
    public static List<String> LOC_INDICATE_VERB_DICT=new ArrayList<>();
    public static List<String> LOC_INDICATE_NOUN_DICT=new ArrayList<>();
    public static List<String> ORG_INDICATE_NOUN_DICT=new ArrayList<>();
    public static List<String> PER_INDICATE_NOUN_DICT=new ArrayList<>();
    public static List<String> LOC_DICT=new ArrayList<>();
    public static List<String> CONJUNCTION_DICT=new  ArrayList<>();
    public static List<String> PER_LOC_AMBIGUTY_DICT=new ArrayList<>();
    public static String path_Dict="E:\\TTCN\\NguyenCongHoan_DATN_All\\NguyenCongHoan_DATN_All\\source_"
            + "code\\EEN_Phuong_interfaceF\\src\\een_phuong\\lexiconStorage\\";
    public static HashSet<NST_GA> listNST=new HashSet<>();

    public static void readDict(String filename, List<String> dict) throws FileNotFoundException, IOException
    {
        File f=new File(path_Dict+filename);
        FileReader fr=new FileReader(f);
        BufferedReader bfr=new BufferedReader(fr);
        String line=null;
        while( (line=bfr.readLine())!=null)
        {
            dict.add(line.trim().toLowerCase());
        }
        bfr.close();
        fr.close();

        for(String s:dict) System.out.println(s);

    }

    public static void main(String[] args) throws IOException {

//        //read dict
//        readDict("DEFINE-DICT.txt", DEFINE_DICT);
//        readDict("LOC-INDICATE-ADVERB-DICT.txt", LOC_INDICATE_ADVERB_DICT);
//        readDict("LOC-INDICATE-NOUN-DICT.txt", LOC_INDICATE_NOUN_DICT);
//        readDict("LOC-INDICATE-VERB-DICT.txt", LOC_INDICATE_VERB_DICT);
//        readDict("ORG- INDICATE-NOUN-DICT.txt", ORG_INDICATE_NOUN_DICT);
//        readDict("PER-INDICATE-NOUN-DICT.txt", PER_INDICATE_NOUN_DICT);
//        readDict("LOC-DICT.txt", LOC_DICT);
//        readDict("CONJUNCTION-DICT.txt",CONJUNCTION_DICT);
//        readDict("PER-LOC-AMBIGUITY.txt", PER_LOC_AMBIGUTY_DICT);
//        
//        





        List<String> trainFile=new ArrayList<String>();
        List<String> testFile=new ArrayList<String>();
//        trainFile.add("23000-24000-300-11-tagged-7label-LPO");
//        trainFile.add("23000-24000-300-12-tagged-7label-LPO");
//        trainFile.add("23000-24000-300-10-tagged-7label-LPO");
//        trainFile.add("23000-24000-300-8-tagged-7label-LPO");
//        trainFile.add("23000-24000-300-9-tagged-7label-LPO");
//        trainFile.add("23000-24000-300-6-tagged-7label-LPO");

        trainFile.add("23000-24000-300-5-tagged-7label-LPO");
        trainFile.add("23000-24000-300-4-tagged-7label-LPO");
        trainFile.add("23000-24000-300-3-tagged-7label-LPO");
        testFile.add("23000-24000-300-13-tagged-7label-LPO");
        testFile.add("23000-24000-300-7-tagged-7label-LPO");

        List<String> trainWords=new ArrayList<>();
        List<String> testWords=new ArrayList<>();

        for(int i=0;i<trainFile.size();i++)
        {
            String f=trainFile.get(i);
            BufferedReader br=new BufferedReader(new FileReader(f));
            String line="";
            while((line=br.readLine())!=null)
            {
                trainWords.add(line);
            }
            br.close();

        }
        for(int i=0;i<testFile.size();i++)
        {
            String f=testFile.get(i);
            BufferedReader br=new BufferedReader(new FileReader(f));
            String line="";
            while((line=br.readLine())!=null)
            {
                testWords.add(line);
            }
            br.close();

        }
        int count=0;
        int t=0;
//        for(int i=0;i<trainWords.size()/3000+1;i++)
//        {
//            try (FileWriter w = new FileWriter(new File(String.valueOf(i))); 
//                    BufferedWriter bw = new BufferedWriter(w)) {
//                for(int j=i*3000;j<i*3000+3000 ;j++)
//                {
//                    if(j<trainWords.size())
//                    { 
//                        bw.write(trainWords.get(j));
//                        bw.newLine();
//                    }
//                }
//            }
//        }
        FileWriter w = new FileWriter(new File(String.valueOf(t)));
        BufferedWriter bw = new BufferedWriter(w);

        while(count<trainWords.size())
        {


            if(count<3000*(Math.pow(Math.sqrt(2),t+1)-1)/(Math.sqrt(2)-1)  )
            {
                bw.write(trainWords.get(count));
                bw.newLine();
            }
            else {
                bw.close();
                w.close();

                t++;
                w = new FileWriter(new File(String.valueOf(t)));
                bw = new BufferedWriter(w);
                bw.write(trainWords.get(count));
                bw.newLine();


            }
            count++;

        }
        count=0;t=0;
        bw.close();
        w.close();
        w = new FileWriter(new File(String.valueOf(t)+"_test"));
        bw = new BufferedWriter(w);

        while(count<testWords.size())
        {



            if(count<2000*(Math.pow(Math.sqrt(2),t+1)-1)/(Math.sqrt(2)-1)   )
            {
                bw.write(testWords.get(count));
                bw.newLine();

            }
            else {
                bw.close();
                w.close();

                t++;
                w = new FileWriter(new File(String.valueOf(t)+"_test"));
                bw = new BufferedWriter(w);
                bw.write(testWords.get(count));
                bw.newLine();


            }
            count++;

        }
        bw.close();
        w.close();

//        for(int i=0;i<testWords.size()/1000+1;i++)
//        {
//            FileWriter w=new FileWriter(new File(String.valueOf(i)+"_test"));
//            
//            BufferedWriter bw=new BufferedWriter(w);
//            for(int j=0;j<i*1000+1000;j++)
//            {
//                if(j<testWords.size())
//                {
//                    
//               
//                bw.write(testWords.get(j));
//                bw.newLine();
//                }
//            }
//            bw.close();
//            w.close();
//        }

//	

        pp=new Population_GA(15);
        NST_GA nst=new NST_GA(true);
        pp.NSTs.add(nst);
        int iter=0;
        while(pp.NSTs.size()>1){

            updateConfigTrainTest(iter);
            CalFitness();
            printPopulation();

            System.out.println("After sorting");
            printPopulation();
            for(int i=0;i<pp.NSTs.size()/2;i++){


                breed(iter);
            }
            sortPopulation();

            pp.NSTs=pp.NSTs.subList(0, pp.NSTs.size()/2);


            iter++;

            System.out.println("After breeding");
            printPopulation();
        }
        /*
        System.out.println("Test the best NST");
        updateConfigTrainTest();
        System.out.println(calFitnessNST((NST_GA)pp.NSTs.get(0)));
        for(int i=0;i<((NST_GA)pp.NSTs.get(0)).gens.size();i++)
        {
            if(((NST_GA)pp.NSTs.get(0)).gens.get(i).value==true)
                System.out.println(((NST_GA)pp.NSTs.get(0)).gens.get(i).featureName);
        }
        * */



        //Chose parents

//       // Population_GA pp=new Population_GA(0);
//        NST_GA nst=new NST_GA(true);
//                         NST_GA nst=new NST_GA(true);
//        updateConfigNST(nst);
//        System.out.println(calFitnessNST(nst));
////        
//        
//        
    }
    public static double calFitnessNST(NST_GA nst) throws IOException
    {

        currentNST=nst;


        nst.writeToConfigureFile("ner.prop");
        // run MEMM
        ProcessBuilder pb =
                new ProcessBuilder("java", "-cp",  "build/classes", "-Xmx1G","edu/stanford/nlp/ie/crf/CRFClassifier", "-prop", "ner.prop");
        pb.redirectErrorStream(true);
        Process proc = pb.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
        //System.out.println("Current NST score:"+currentNST.score);
        //readDataFromFile("E:\\Fscore.txt");
        String fscore=(getContents(new File("E:\\Fscore.txt")));
        currentNST.score=Double.valueOf(fscore);
        //  System.out.println(((NST_GA)pp.NSTs.get(i)).score);
        return Double.valueOf(fscore);

    }

    public static void  CalFitness() throws IOException
    {
        for(int i=0;i<pp.NSTs.size();i++)
        {

            currentNST=(NST_GA) pp.NSTs.get(i);
            currentNST.writeToConfigureFile("ner.prop");

            // run MEMM
            ProcessBuilder pb =
                    new ProcessBuilder("java", "-cp",  "build/classes", "-Xmx1G","edu/stanford/nlp/ie/crf/CRFClassifier", "-prop", "ner.prop");
            pb.redirectErrorStream(true);
            Process proc = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
            //System.out.println("Current NST score:"+currentNST.score);
            //readDataFromFile("E:\\Fscore.txt");
            String fscore=(getContents(new File("E:\\Fscore.txt")));
            double f=Double.valueOf(fscore);
            double count=0;
            for(Gen_GA g: currentNST.gens)
            {
                if((boolean)g.value==true)
                {
                    count++;
                }
            }
            f=f*0.98-0.02*count/89;
            if(currentNST.score==0) currentNST.score=f;
            else
            {
                currentNST.score=(currentNST.score+f*Math.sqrt(2))/(Math.sqrt(2)+1);
            }

            //  System.out.println(((NST_GA)pp.NSTs.get(i)).score);
        }
        //ket thuc tinh fitness
        //print fitness 

    }

    public static void printPopulation()
    {
        for(int i=0;i<pp.NSTs.size();i++)
        {
            System.out.println(((NST_GA)pp.NSTs.get(i)).score);
        }
        for(int i=0;i<((NST_GA)pp.NSTs.get(0)).gens.size();i++)
        {
            Gen_GA g=(Gen_GA) ((NST_GA)pp.NSTs.get(0)).gens.get(i);
            System.out.println(g.featureName+":"+g.value);
        }
    }

    public static void sortPopulation()
    {

        for(int i=0;i<pp.NSTs.size()-1;i++)
        {
            for(int j=i+1;j<pp.NSTs.size();j++)
            {
                if(((NST_GA)pp.NSTs.get(j)).score>((NST_GA)pp.NSTs.get(i)).score)
                {
                    Collections.swap(pp.NSTs, i, j);
                }
            }
        }
    }

    public static NST_GA mutation(NST_GA nst)
    {
        Random r=new Random();
        int k=r.nextInt(nst.gens.size());
        Gen_GA gen=(Gen_GA) nst.gens.get(k);

        Object value=gen.value;
        if(value.equals( true)|| value.equals( false))

        {
            int t=r.nextInt(10);

            if(t>=7) gen.value=!(boolean)value;
        }

        //if(pp.isConflictNST(nst, null))
        if(!listNST.contains(nst))
        {
            listNST.add(nst);
            return nst;
        }
        else return mutation(nst);

    }
    public static void  breed(int iter) throws IOException
    {

        Random r=new Random();

        int k1=r.nextInt(pp.NSTs.size()/3+1);
        int k2=r.nextInt(pp.NSTs.size()/3+1)+r.nextInt(pp.NSTs.size()*2/3);
        NST_GA pr1=(NST_GA) pp.NSTs.get(k1);
        NST_GA pr2=(NST_GA) pp.NSTs.get(k2);

        NST_GA spring1=crossOver(pr1,pr2);

        spring1=mutation(spring1);
        spring1=updateConfig(iter, spring1);
        spring1.score=calFitnessNST(spring1);

        NST_GA spring2=crossOver2(pr1, pr2);

        spring2=mutation(spring2);
        spring2=updateConfig(iter, spring2);
        spring2.score=calFitnessNST(spring2);
        if(spring1.score>((NST_GA)pp.NSTs.get(pp.NSTs.size()-1)).score)
        {
            pp.NSTs.set(pp.NSTs.size()-1, spring1);
        }
        sortPopulation();
        if(spring2.score>((NST_GA)pp.NSTs.get(pp.NSTs.size()-1)).score)
        {
            pp.NSTs.set(pp.NSTs.size()-1, spring2);
        }
        sortPopulation();
    }

    static public String getContents(File aFile) {
        //...checks on aFile are elided
        StringBuilder contents = new StringBuilder();

        try {
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            BufferedReader input =  new BufferedReader(new FileReader(aFile));
            try {
                String line = null; //not declared within while loop
        /*
        * readLine is a bit quirky :
        * it returns the content of a line MINUS the newline.
        * it returns null only for the END of the stream.
        * it returns an empty String if two newlines appear in a row.
        */
                while (( line = input.readLine()) != null){
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            }
            finally {
                input.close();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return contents.toString();
    }

    private static void readDataFromFile(String fileName) throws FileNotFoundException, IOException {
        //  throw new UnsupportedOperationException("Not yet implemented");
        File f=new File(fileName);
        FileInputStream fis=new FileInputStream(f);
        int a= fis.read();
        fis.close();
        System.out.println(a);

    }

    private static NST_GA crossOver(NST_GA pr1, NST_GA pr2) {
        NST_GA result=new NST_GA();
        for(int i=0;i<pr1.gens.size();i++)
        {
            if(i<pr1.gens.size()/2)
                ((Gen_GA)result.gens.get(i)).value=((Gen_GA)pr1.gens.get(i)).value;
            else
                ((Gen_GA)result.gens.get(i)).value=((Gen_GA)pr2.gens.get(i)).value;

        }
        return result;
    }
    private static NST_GA crossOver2(NST_GA pr1, NST_GA pr2) {
        NST_GA result=new NST_GA();
        for(int i=0;i<pr1.gens.size();i++)
        {
            if(i<pr1.gens.size()/2)
                ((Gen_GA)result.gens.get(i)).value=((Gen_GA)pr2.gens.get(i)).value;
            else
                ((Gen_GA)result.gens.get(i)).value=((Gen_GA)pr1.gens.get(i)).value;

        }
        return result;
    }



    public static NST_GA updateConfig(int iter, NST_GA n)
    {

        n.trainFileList="\n trainFileList =";


        n.trainFileList+=iter+"\n";


        n.testFile="testFile ="+iter+"_test";

        return n;
    }

    private static void updateConfigTrainTest(int iter) {
        for(int i=0;i<pp.NSTs.size();i++)
        {
            NST_GA n=(NST_GA) pp.NSTs.get(i);
            n.trainFileList="\n trainFileList =";


            n.trainFileList+=iter+"\n";


            n.testFile="testFile ="+iter+"_test";
            pp.NSTs.set(i, n);
        }

    }
    private static void updateConfigTrainTest() {
        for(int i=0;i<pp.NSTs.size();i++)
        {
            NST_GA n=(NST_GA) pp.NSTs.get(i);
            n.trainFileList="\n trainFileList =";


            n.trainFileList+=" 23000-24000-300-9-tagged-7label-LPO,"
                    + "23000-24000-300-10-tagged-7label-LPO,"
                    + "23000-24000-300-11-tagged-7label-LPO,"
                    + "23000-24000-300-12-tagged-7label-LPO \n";


            n.testFile="testFile = 23000-24000-300-13-tagged-7label-LPO \n";
            pp.NSTs.set(i, n);
        }

    }

    public static void updateConfigNST(NST_GA n)
    {
        n.trainFileList="\n trainFileList =";

        for(int i=1;i<=80;i++)
        {
            n.trainFileList+=i+".txt,";
        }
//            n.trainFileList+=" "
//                        + "23000-24000-300-10-tagged-7label-LPO,"
//                        + "23000-24000-300-11-tagged-7label-LPO,"
//                        + "23000-24000-300-12-tagged-7label-LPO \n"
//                        ;
        n.trainFileList=n.trainFileList.substring(0, n.trainFileList.length()-1);
        n.trainFileList+="\n";

//                n.trainFileList+=" raovat_train_tagged \n"
//                     //   + "23000-24000-300-10-tagged-7label-LPO,"
//                     //   + "23000-24000-300-11-tagged-7label-LPO,"
//                      //  + "23000-24000-300-12-tagged-7label-LPO \n"
//                        ;


        n.testFile="testFile = 91.txt \n";


    }

}