package GroupProject.WebInterface.model.sentiment;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SentimentEngine {
    StanfordCoreNLP pipeline;


    public SentimentEngine(){
        pipeline = initPipeline();
    }

    private StanfordCoreNLP initPipeline(){
        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators",
                "tokenize,ssplit,pos,parse, sentiment");
        // set a property for an annotator, in this case the coref annotator is being
        //set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");
        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        return pipeline;
    }




    public ArrayList<String> judgeString(String text){
        CoreDocument document = new CoreDocument(text);
        System.out.println("annotating");

        pipeline.annotate(document);
        final ArrayList<String> subSentiments = new ArrayList<>();


        for (CoreSentence sentence:document.sentences()) {
            String sentiment = sentence.sentiment();
            subSentiments.add(sentiment);
            System.out.println(sentence);
            System.out.println(sentiment);
            System.out.println();
        }

        return subSentiments;
    }

    //concatenates all strings such that no sentences run over from one list item to next one
    //then judges this single large string
    //returns a list of the classifications for each sentence
    public ArrayList<String> concatAndJudgeStrings(ArrayList<String> docs, int sampleSize){
        ArrayList<String> sampleDocs = takeSample(sampleSize,docs);
        String concatenated = ListToSentences(sampleDocs);
        return judgeString(concatenated);
    }


    private String ListToSentences(ArrayList<String> docs){
        StringBuilder builder= new StringBuilder();

        //add all sentences
        for (String item: docs) {
            builder.append(item);
            builder.append(" . "); //FORCES THE END OF item TO BE AN END OF SENTENCE
        }

        //replace blank sentence substrings (".  .") with single period symbol(".")
        String result = builder.toString().replaceAll("\\.  \\.",".");

        System.out.println(result);

        return result;
    }



    private ArrayList<String> takeSample(int sampleSize, ArrayList<String> source){
        if( sampleSize>=source.size()){
            return (ArrayList<String>) (source.clone());
        }

        else {
            ArrayList<String> sample = new ArrayList<>();

            HashSet<Integer> used = new HashSet<>();
            int index = ThreadLocalRandom.current().nextInt(0,source.size());
            while (used.size()<sampleSize){
                //make a new random
                while (used.contains(index)){
                    index = ThreadLocalRandom.current().nextInt(0,source.size());
                }
                used.add(index);

                sample.add(source.get(index));

            }

            return sample;
        }

    }


    //return the most common classification
    public static String modalClassification(final ArrayList<String> classifications){
        Comparator<String> byFreqency = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Collections.frequency(classifications,o2)-Collections.frequency(classifications,o1);
            }
        };

        Collections.sort(classifications,byFreqency);

        return classifications.get(0);
    }





}