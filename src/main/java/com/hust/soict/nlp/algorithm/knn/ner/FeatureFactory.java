package com.hust.soict.nlp.algorithm.knn.ner;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Luan
 */
public class FeatureFactory {
    public static String path_Dict="E:\\NER\\";

    public static HashSet<String> countryList=readict("countries.txt");
    public static HashSet<String> vnFirstName=readict("vnFirstNames.txt");
    public static HashSet<String> vnLastName=readict("vnLastNames.txt");
    public static HashSet<String> locDict=readict("LOC-DICT.txt");
    public static HashSet<String> vnTimeMarker=readict("vnTimeMarkers.txt");
    public static HashSet<String> perIndicateNoun=readict("PER-INDICATE-NOUN-DICT.txt");
    public static HashSet<String> locIndicateNoun=readict("LOC-INDICATE-NOUN-DICT.txt");
    public static HashSet<String> orgIndicateNoun=readict("ORG- INDICATE-NOUN-DICT.txt");



    public static List<Datum> computeFeature(List<Datum> words, List<String> features)
    {


        // List<Datum> result=new ArrayList<Datum>();
        for(int j=0;j<words.size();j++)
        {
            Datum datum=words.get(j);
            String cWord=datum.word;
            String cTag=datum.posTag;
            String pTag="";
            String p2Tag="";
            String nTag="";
            String n2Tag="";
            String pWord="";
            String p2Word="";
            String n2Word="";
            String p3Word="";
            if(j>0)
            {
                pWord=words.get(j-1).word;
                pTag=words.get(j-1).posTag;
            }
            if(j>1)
            {
                p2Word=words.get(j-2).word;
                p2Tag=words.get(j-2).posTag;
            }
            if(j>2)p3Word=words.get(j-3).word;
            String nWord="";
            if(j<words.size()-1)
            {
                nWord=words.get(j+1).word;
                nTag=words.get(j+1).posTag;
            }
            if(j<words.size()-2)
            {
                n2Word=words.get(j+2).word;
                n2Tag=words.get(j+2).posTag;
            }
            Feature[] f=new Feature[features.size()];
            for(int i=0;i<f.length;i++)
            {
                String featureName=features.get(i);
                f[i]=new Feature(featureName);
                //add feature here
                switch (featureName) {
                    case "currentWord":
                        f[i].stringValue=cWord;
                        break;
                    case "prevWord":
                        f[i].stringValue=pWord;
                        break;
                    case "prev2Word":
                        f[i].stringValue=p2Word;
                        break;
                    case "next2Word":
                        f[i].stringValue=n2Word;
                        break;
                    case "nextWord":
                        f[i].stringValue=nWord;
                        break;
                    case "initUpcaseCurrentWord":
                        if(Character.isUpperCase(cWord.charAt(0))) f[i].intValue=1;
                        else f[i].intValue=0;
                        break;
                    case "initUpcasePrevWord":
                        if(pWord.length()>0)
                        {
                            if(Character.isUpperCase(pWord.charAt(0))) f[i].intValue=1;
                            else f[i].intValue=0;

                        }
                        else f[i].intValue=0;
                        break;
                    case "initUpcasePrev2Word":
                        if(p2Word.length()>0)
                        {
                            if(Character.isUpperCase(p2Word.charAt(0))) f[i].intValue=1;
                            else f[i].intValue=0;
                        }
                        else f[i].intValue=0;
                        break;
                    case "initUpcaseNextWord":
                        if(nWord.length()>0)
                        {
                            if(Character.isUpperCase(nWord.charAt(0))) f[i].intValue=1;
                            else f[i].intValue=0;
                        }
                        else f[i].intValue=0;
                        break;
                    case "initUpcaseNext2Word":
                        if(n2Word.length()>0)
                        {
                            if(Character.isUpperCase(n2Word.charAt(0))) f[i].intValue=1;
                            else f[i].intValue=0;
                        }
                        else f[i].intValue=0;
                        break;
                    case "lowercaseCurrentWord":
                        f[i].stringValue=cWord.toLowerCase();
                        break;
                    case "lowercasePrevWord":
                        f[i].stringValue=pWord.toLowerCase();
                        break;
                    case "lowercaseNextWord":
                        f[i].stringValue=nWord.toLowerCase();
                        break;
                    case "wordPairPC":
                        f[i].stringValue=pWord+cWord;
                        break;
                    case "wordPairCN":
                        f[i].stringValue=cWord+nWord;
                        break;
                    case "wordPairPN":
                        f[i].stringValue=pWord+nWord;
                        break;
                    case "allCapCurrentWord":
                        f[i].intValue=1;
                        for(int t=0;t<cWord.length();t++)
                        {
                            if(Character.isLowerCase(cWord.charAt(t)))
                                f[i].intValue=0;
                            break;
                        }
                        break;
                    case "allCapPrevWord":
                        if(pWord.length()>0)
                        {
                            f[i].intValue=1;
                            for(int t=0;t<pWord.length();t++)
                            {
                                if(Character.isLowerCase(pWord.charAt(t)))
                                    f[i].intValue=0;
                                break;
                            }
                        }
                        else f[i].intValue=0;
                        break;

                    case "allCapPrev2Word":
                        if(p2Word.length()>0)
                        {
                            f[i].intValue=1;
                            for(int t=0;t<p2Word.length();t++)
                            {
                                if(Character.isLowerCase(p2Word.charAt(t)))
                                    f[i].intValue=0;
                                break;
                            }
                        }
                        else f[i].intValue=0;
                        break;
                    case "allCapNextWord":
                        if(nWord.length()>0)
                        {
                            f[i].intValue=1;
                            for(int t=0;t<nWord.length();t++)
                            {
                                if(Character.isLowerCase(nWord.charAt(t)))
                                    f[i].intValue=0;
                                break;
                            }
                        }
                        else f[i].intValue=0;
                        break;
                    case "allCapNext2Word":
                        if(n2Word.length()>0)
                        {
                            f[i].intValue=1;
                            for(int t=0;t<n2Word.length();t++)
                            {
                                if(Character.isLowerCase(n2Word.charAt(t)))
                                    f[i].intValue=0;
                                break;
                            }
                        }
                        else f[i].intValue=0;
                        break;
                    case "letterAndDigitCWord":
                        boolean hasLetter=false;
                        boolean hasDigit=false;
                        for(int t=0;t<cWord.length();t++)
                        {
                            if(Character.isAlphabetic(cWord.charAt(t))) hasLetter=true;
                            if(Character.isDigit(cWord.charAt(t))) hasDigit=true;
                        }
                        if(hasLetter && hasDigit) f[i].intValue=1;
                        else f[i].intValue=0;
                        break;
                    case "letterAndDigitPWord":
                        hasLetter=false;
                        hasDigit=false;
                        for(int t=0;t<pWord.length();t++)
                        {
                            if(Character.isAlphabetic(pWord.charAt(t))) hasLetter=true;
                            if(Character.isDigit(pWord.charAt(t))) hasDigit=true;
                        }
                        if(hasLetter && hasDigit) f[i].intValue=1;
                        else f[i].intValue=0;
                        break;
                    case "letterAndDigitP2Word":
                        hasLetter=false;
                        hasDigit=false;
                        for(int t=0;t<p2Word.length();t++)
                        {
                            if(Character.isAlphabetic(p2Word.charAt(t))) hasLetter=true;
                            if(Character.isDigit(p2Word.charAt(t))) hasDigit=true;
                        }
                        if(hasLetter && hasDigit) f[i].intValue=1;
                        else f[i].intValue=0;
                        break;
                    case "letterAndDigitNWord":
                        hasLetter=false;
                        hasDigit=false;
                        for(int t=0;t<nWord.length();t++)
                        {
                            if(Character.isAlphabetic(nWord.charAt(t))) hasLetter=true;
                            if(Character.isDigit(nWord.charAt(t))) hasDigit=true;
                        }
                        if(hasLetter && hasDigit) f[i].intValue=1;
                        else f[i].intValue=0;
                        break;
                    case "letterAndDigitN2Word":
                        hasLetter=false;
                        hasDigit=false;
                        for(int t=0;t<n2Word.length();t++)
                        {
                            if(Character.isAlphabetic(n2Word.charAt(t))) hasLetter=true;
                            if(Character.isDigit(n2Word.charAt(t))) hasDigit=true;
                        }
                        if(hasLetter && hasDigit) f[i].intValue=1;
                        else f[i].intValue=0;
                        break;
                    case "prefixCWord":
                        if(cWord.length()>=3) f[i].stringValue=cWord.substring(0, 3);
                        else f[i].stringValue=cWord;

                        break;
                    case "prefixPWord":
                        if(pWord.length()>=3) f[i].stringValue=pWord.substring(0, 3);
                        else f[i].stringValue=pWord;

                        break;
                    case "prefixP2Word":
                        if(p2Word.length()>=3) f[i].stringValue=p2Word.substring(0, 3);
                        else f[i].stringValue=p2Word;

                        break;

                    case "postfixCWord":
                        if(cWord.length() >=3) f[i].stringValue=cWord.substring(cWord.length()-3);
                        else f[i].stringValue=cWord;
                        break;
                    case "postfixPWord":
                        if(pWord.length() >=3) f[i].stringValue=pWord.substring(pWord.length()-3);
                        else f[i].stringValue=pWord;
                        break;
                    case "postfixNWord":
                        if(nWord.length() >=3) f[i].stringValue=nWord.substring(nWord.length()-3);
                        else f[i].stringValue=nWord;
                        break;
                    case "isPunctuationPWord":
                        if(pWord.equals("."))
                            f[i].intValue=1;
                        else f[i].intValue=0;

                        break;
                    case "isPunctuationP2Word":
                        if(p2Word.equals("."))
                            f[i].intValue=1;
                        else f[i].intValue=0;

                        break;
                    case "isPunctuationNWord":
                        if(nWord.equals("."))
                            f[i].intValue=1;
                        else f[i].intValue=0;

                        break;
                    case "isPunctuationN2Word":
                        if(n2Word.equals("."))
                            f[i].intValue=1;
                        else f[i].intValue=0;

                        break;
                    case "isFirstSentenceWord":
                        if(pWord.equals(".") && Character.isUpperCase(cWord.charAt(0)))
                            f[i].intValue=1;
                        else f[i].intValue=0;
                        break;
                    case "isFirstSentencePWord":
                        if(pWord.length()>0)
                        {
                            if(p2Word.equals(".") && Character.isUpperCase(pWord.charAt(0)))
                                f[i].intValue=1;
                            else f[i].intValue=0;
                        }
                        else f[i].intValue=0;
                        break;
                    case "isFirstSentenceP2Word":
                        if(p2Word.length()>0)
                        {
                            if(p3Word.equals(".") && Character.isUpperCase(p2Word.charAt(0)))
                                f[i].intValue=1;
                            else f[i].intValue=0;
                        }
                        else f[i].intValue=0;
                        break;

                    case "isInCountryList":
                        String w=cWord.replace('_', ' ');
                        if(countryList.contains(w.trim())) f[i].intValue=1;
                        else f[i].intValue=0;
                        break;
                    case "isInCountryListPWord":
                        w=pWord.replace('_', ' ');
                        if(countryList.contains(w.trim())) f[i].intValue=1;
                        else f[i].intValue=0;
                        break;
                    case "isInCountryListP2Word":
                        w=p2Word.replace('_', ' ');
                        if(countryList.contains(w.trim())) f[i].intValue=1;
                        else f[i].intValue=0;
                        break;
                    case "isInCountryListNWord":
                        w=nWord.replace('_', ' ');
                        if(countryList.contains(w.trim())) f[i].intValue=1;
                        else f[i].intValue=0;
                        break;
                    case "isInCountryListN2Word":
                        w=n2Word.replace('_', ' ');
                        if(countryList.contains(w.trim())) f[i].intValue=1;
                        else f[i].intValue=0;
                        break;
                    case "tag":
                        f[i].stringValue=cTag;
                        break;
                    case "pTag":
                        f[i].stringValue=pTag;
                        break;
                    case "p2Tag":
                        f[i].stringValue=p2Tag;
                        break;
                    case "nTag":
                        f[i].stringValue=nTag;
                        break;
                    case "n2Tag":
                        f[i].stringValue=n2Tag;
                        break;
                    case "isInVnFirstName":
                        String[] part=cWord.split("_");
                        f[i].intValue=0;
                        if(part.length>0)
                        {
                            if(vnFirstName.contains(part[0])) f[i].intValue=1;
                            else f[i].intValue=0;
                        }

                        break;
                    case "isInVnFirstNamePWord":
                        part=pWord.split("_");
                        f[i].intValue=0;
                        if(part.length>0)
                        {
                            if(vnFirstName.contains(part[0])) f[i].intValue=1;
                            else f[i].intValue=0;
                        }

                        break;
                    case "isInVnFirstNameP2Word":
                        part=p2Word.split("_");
                        f[i].intValue=0;
                        if(part.length>0)
                        {
                            if(vnFirstName.contains(part[0])) f[i].intValue=1;
                            else f[i].intValue=0;
                        }

                        break;
                    case "isInVnFirstNameNWord":
                        part=nWord.split("_");
                        f[i].intValue=0;
                        if(part.length>0)
                        {
                            if(vnFirstName.contains(part[0])) f[i].intValue=1;
                            else f[i].intValue=0;
                        }

                        break;

                    case "isInVnFirstNameN2Word":
                        part=n2Word.split("_");
                        f[i].intValue=0;
                        if(part.length>0)
                        {
                            if(vnFirstName.contains(part[0])) f[i].intValue=1;
                            else f[i].intValue=0;
                        }

                        break;
                    case "isInVnLastName":
                        part=cWord.split("_");
                        f[i].intValue=0;
                        if(part.length>0)
                        {
                            if(vnLastName.contains(part[part.length-1])) f[i].intValue=1;
                            else f[i].intValue=0;
                        }

                        break;
                    case "isInVnLastNamePWord":
                        part=pWord.split("_");
                        f[i].intValue=0;
                        if(part.length>0)
                        {
                            if(vnLastName.contains(part[part.length-1])) f[i].intValue=1;
                            else f[i].intValue=0;
                        }

                        break;
                    case "isInVnLastNameP2Word":
                        part=p2Word.split("_");
                        f[i].intValue=0;
                        if(part.length>0)
                        {
                            if(vnLastName.contains(part[part.length-1])) f[i].intValue=1;
                            else f[i].intValue=0;
                        }

                        break;
                    case "isInVnLastNameNWord":
                        part=nWord.split("_");
                        f[i].intValue=0;
                        if(part.length>0)
                        {
                            if(vnLastName.contains(part[part.length-1])) f[i].intValue=1;
                            else f[i].intValue=0;
                        }

                        break;
                    case "isInVnLastNameN2Word":
                        part=n2Word.split("_");
                        f[i].intValue=0;
                        if(part.length>0)
                        {
                            if(vnLastName.contains(part[part.length-1])) f[i].intValue=1;
                            else f[i].intValue=0;
                        }

                        break;
                    case "inLocDict":
                        f[i].intValue=0;
                        if(locDict.contains(cWord.replace('_', ' '))) f[i].intValue=1;
                        break;
                    case "inLocDictPWord":
                        f[i].intValue=0;
                        if(locDict.contains(pWord.replace('_', ' '))) f[i].intValue=1;
                        break;
                    case "inLocDictP2Word":
                        f[i].intValue=0;
                        if(locDict.contains(p2Word.replace('_', ' '))) f[i].intValue=1;
                        break;
                    case "inLocDictNWord":
                        f[i].intValue=0;
                        if(locDict.contains(nWord.replace('_', ' '))) f[i].intValue=1;
                        break;
                    case "inLocDictN2Word":
                        f[i].intValue=0;
                        if(locDict.contains(n2Word.replace('_', ' '))) f[i].intValue=1;
                        break;
                    case "vnTimeMarkers":
                        f[i].intValue=0;
                        if(vnTimeMarker.contains(cWord.replace('_', ' '))) f[i].intValue=1;
                        break;
                    case "vnTimeMarkersPWord":
                        f[i].intValue=0;
                        if(vnTimeMarker.contains(pWord.replace('_', ' '))) f[i].intValue=1;
                        break;
                    case "vnTimeMarkersP2Word":
                        f[i].intValue=0;
                        if(vnTimeMarker.contains(p2Word.replace('_', ' '))) f[i].intValue=1;
                        break;
                    case "vnTimeMarkersNWord":
                        f[i].intValue=0;
                        if(vnTimeMarker.contains(nWord.replace('_', ' '))) f[i].intValue=1;
                        break;
                    case "vnTimeMarkersN2Word":
                        f[i].intValue=0;
                        if(vnTimeMarker.contains(n2Word.replace('_', ' '))) f[i].intValue=1;
                        break;
                    case "perIndicateNoun":
                        f[i].intValue=0;
                        if(perIndicateNoun.contains(cWord.toLowerCase().replace('_', ' ')))
                            f[i].intValue=1;
                        break;
                    case "perIndicateNounPWord":
                        f[i].intValue=0;
                        if(perIndicateNoun.contains(pWord.toLowerCase().replace('_', ' ')))
                            f[i].intValue=1;
                        break;
                    case "perIndicateNounP2Word":
                        f[i].intValue=0;
                        if(perIndicateNoun.contains(p2Word.toLowerCase().replace('_', ' ')))
                            f[i].intValue=1;
                        break;
                    case "perIndicateNounN2Word":
                        f[i].intValue=0;
                        if(perIndicateNoun.contains(n2Word.toLowerCase().replace('_', ' ')))
                            f[i].intValue=1;
                        break;
                    case "perIndicateNounNWord":
                        f[i].intValue=0;
                        if(perIndicateNoun.contains(nWord.toLowerCase().replace('_', ' ')))
                            f[i].intValue=1;
                        break;
                    case "locIndicateNoun":
                        f[i].intValue=0;
                        if(locIndicateNoun.contains(cWord.toLowerCase().replace('_', ' ')))
                            f[i].intValue=1;
                        break;
                    case "locIndicateNounPWord":
                        f[i].intValue=0;
                        if(locIndicateNoun.contains(pWord.toLowerCase().replace('_', ' ')))
                            f[i].intValue=1;
                        break;
                    case "locIndicateNounP2Word":
                        f[i].intValue=0;
                        if(locIndicateNoun.contains(p2Word.toLowerCase().replace('_', ' ')))
                            f[i].intValue=1;
                        break;
                    case "locIndicateNounNWord":
                        f[i].intValue=0;
                        if(locIndicateNoun.contains(nWord.toLowerCase().replace('_', ' ')))
                            f[i].intValue=1;
                        break;
                    case "locIndicateNounN2Word":
                        f[i].intValue=0;
                        if(locIndicateNoun.contains(n2Word.toLowerCase().replace('_', ' ')))
                            f[i].intValue=1;
                        break;
                    case "orgIndicateNoun":
                        f[i].intValue=0;
                        if(orgIndicateNoun.contains(cWord.toLowerCase().replace('_', ' ')))
                            f[i].intValue=1;
                        break;
                    case "orgIndicateNounPWord":
                        f[i].intValue=0;
                        if(orgIndicateNoun.contains(pWord.toLowerCase().replace('_', ' ')))
                            f[i].intValue=1;
                        break;
                    case "orgIndicateNounP2Word":
                        f[i].intValue=0;
                        if(orgIndicateNoun.contains(p2Word.toLowerCase().replace('_', ' ')))
                            f[i].intValue=1;
                        break;
                    case "orgIndicateNounN2Word":
                        f[i].intValue=0;
                        if(orgIndicateNoun.contains(n2Word.toLowerCase().replace('_', ' ')))
                            f[i].intValue=1;
                        break;
                    case "orgIndicateNounNWord":
                        f[i].intValue=0;
                        if(orgIndicateNoun.contains(nWord.toLowerCase().replace('_', ' ')))
                            f[i].intValue=1;
                        break;

                    case "isBracket":
                        f[i].intValue=0;
                        if(cWord.equals(")")||cWord.equals("("))
                            f[i].intValue=1;
                        break;
                    case "isBracketPWord":
                        f[i].intValue=0;
                        if(pWord.equals(")")||pWord.equals("("))
                            f[i].intValue=1;
                        break;
                    case "isBracketP2Word":
                        f[i].intValue=0;
                        if(p2Word.equals(")")|| p2Word.equals("("))
                            f[i].intValue=1;
                        break;
                    case "isBracketN2Word":
                        f[i].intValue=0;
                        if(n2Word.equals(")")|| n2Word.equals("("))
                            f[i].intValue=1;
                        break;
                    case "isBracketNWord":
                        f[i].intValue=0;
                        if(nWord.equals(")")|| nWord.equals("("))
                            f[i].intValue=1;
                        break;


                }


                //
                datum.features=f;
                words.set(j, datum);
            }

        }
        return words;
    }

    public  static HashSet<String> readict(String fileName)  {
        //  throw new UnsupportedOperationException("Not yet implemented");
        HashSet<String> result=new HashSet<String>();
        try{
            BufferedReader br=new BufferedReader(new FileReader
                    (new File(path_Dict+fileName)));
            String line="";
            while((line=br.readLine())!=null)
            {
                result.add(line.trim());
            }
        } catch(Exception e)
        {
            System.out.println(e);
        }
        return result;
    }



}
