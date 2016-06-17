package edu.stanford.nlp.ie.crf;

import edu.stanford.nlp.process.WordShapeClassifier;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Kho
 */
public class NST_GA {

    public List<Gen_GA> gens;
    public double score = 0;
    public String trainFileList="trainFileList =";
    public String testFile="\n testFile = ";
    public String[] listNames = {
//        "useNearKeyWord",
        "wordShape",
//        "useLocDictHNHCM",
        "currentWord",
        "prevWord",
        "prev2Word",
        "next2Word",
        "nextWord",
        "initUpcaseCurrentWord",
        "initUpcasePrevWord",
        "initUpcasePrev2Word",
        "initUpcaseNextWord",
        "initUpcaseNext2Word",
        "lowercaseCurrentWord",
        "lowercasePrevWord",
        "lowercaseNextWord",
        "wordPairPC",
        "wordPairCN",
        "wordPairPN",
        "allCapCurrentWord",
        "allCapPrevWord",
        "allCapPrev2Word",
        "allCapNextWord",
        "allCapNext2Word",
        "letterAndDigitCWord",
        "letterAndDigitPWord",
        "letterAndDigitP2Word",
        "letterAndDigitNWord",
        "letterAndDigitN2Word",
        "prefixCWord",
        "prefixPWord",
        "prefixP2Word",
        "isPunctuationPWord",
        "isPunctuationP2Word",
        "isPunctuationNWord",
        "isPunctuationN2Word",
        "isBracket",
        "isBracketPWord",
        "isBracketP2Word",
        "isBracketN2Word",
        "isBracketNWord",
        "isFirstSentenceWord",
        "isFirstSentencePWord",
        "isFirstSentenceP2Word",
        "isInCountryList",
        "isInCountryListPWord",
        "isInCountryListP2Word",
        "isInCountryListNWord",
        "isInCountryListN2Word",
        "isInVnFirstName",
        "isInVnFirstNamePWord",
        "isInVnFirstNameP2Word",
        "isInVnFirstNameN2Word",
        "isInVnFirstNameNWord",
        "isInVnLastName",
        "isInVnLastNamePWord",
        "isInVnLastNameP2Word",
        "isInVnLastNameNWord",
        "isInVnLastNameN2Word",
        "isNumber",
        "inLocDict",
        "inLocDictPWord",
        "inLocDictP2Word",
        "inLocDictNWord",
        "inLocDictN2Word",
        "vnTimeMarkers",
        "vnTimeMarkersPWord",
        "vnTimeMarkersP2Word",
        "vnTimeMarkersN2Word",
        "vnTimeMarkersNWord",
        "perIndicateNoun",
        "perIndicateNounPWord",
        "perIndicateNounP2Word",
        "perIndicateNounNWord",
        "perIndicateNounN2Word",
        "locIndicateNounN2Word",
        "locIndicateNounNWord",
        "locIndicateNounPWord",
        "locIndicateNounP2Word",
        "locIndicateNoun",
        "orgIndicateNoun",
        "orgIndicateNounPWord",
        "orgIndicateNounP2Word",
        "orgIndicateNounNWord",
        "orgIndicateNounN2Word",
        "tag",
        "pTag",
        "p2Tag",
        "nTag",
        "n2Tag",
        "postfixCWord",
        "postfixPWord",
        "postfixNWord"
    };

    public NST_GA(List gens) {
        this.gens = gens;
    }
    public NST_GA(String gens)
    {
         this.gens = new ArrayList();
        String[] g=gens.split(":");
        for(int i=0;i<g.length;i++)
        {
            if(g[i].equals("true")) 
            {
                Gen_GA ga=new Gen_GA(listNames[i],true);
                this.gens.add(ga);
            }
            else 
            {
                Gen_GA ga=new Gen_GA(listNames[i],false);
                this.gens.add(ga);
            }
            
               
        }
    }

    public NST_GA() {
        gens = new ArrayList();
        for (int i = 0; i < listNames.length; i++) {
            String name = listNames[i];
            if (!name.equals("wordShape")) {
                Random r = new Random();
                boolean value = r.nextBoolean();
                Gen_GA g = new Gen_GA(name, value);
                System.out.println(name + ":" + value);
                gens.add(g);
            }
            if (name.equals("wordShape")) {
                Random r = new Random();
                int k = r.nextInt(10);
                Object value;
                if (k >= 5) {
                    value = WordShapeClassifier.WORDSHAPEJENNY1;
                } else {
                    value = WordShapeClassifier.NOWORDSHAPE;
                }
                Gen_GA g = new Gen_GA(name, value);
                System.out.println(name + ":" + value);
                gens.add(g);
            }

           
        }
        this.score = 0.0;

    }

    public void writeToConfigureFile(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);
        PrintWriter out = new PrintWriter(fw);
        String de = trainFileList +testFile
                
                + "\n serializeTo = ner.gz"
                + "\n type = crf"
                + "\n map = word=0,tag=1,answer=2"
                + "\n readerAndWriter=edu.stanford.nlp.sequences.ColumnDocumentReaderAndWriter"
                + "\n useQN = true"
                + "\n QNsize = 15";

        for (Object g : this.gens) {
            Gen_GA gen = (Gen_GA) g;
            de += "\n" + gen.featureName + " = " + gen.value;
        }
        out.print(de);
        out.close();

    }

    public NST_GA(boolean t) {
        gens = new ArrayList<>();
        for (int i = 0; i < listNames.length; i++) {
            String name = listNames[i];
            if (!name.equals("wordShape")) {

                Gen_GA g = new Gen_GA(name, true);

                gens.add(g);
            }
            if (name.equals("wordShape")) {
                Random r = new Random();
                int k = r.nextInt(10);
                Object value;
//               if(k>=5)
                value = WordShapeClassifier.WORDSHAPECHRIS2USELC;
//               else value=WordShapeClassifier.NOWORDSHAPE;
                Gen_GA g = new Gen_GA(name, value);
                System.out.println(name + ":" + value);
                gens.add(g);
            }


        }
       
        this.score = 0.0;

    }
}
