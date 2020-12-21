package GroupProject.WebInterface.model.sentiment;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * this class uses the Stanford CoreNLP dependencies to perform sentiment analysis
 * */
public class SentimentEngine {
    StanfordCoreNLP pipeline;


    public SentimentEngine(){
        pipeline = initPipeline();
    }

    /**
     * set up a pipeline to perfrom sentiment analysis
     * */
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



    /**
     * uses Stanford CoreNLP sentiment pipeline which was set up in initPipeline
     * classifies each sentence in the string as positive, neutral or negative
     * */

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

    /**
     * takes a sample using takeSample
     * concatenates all strings in sample using listtosentences
     * then judges this single large string using judgeString;
     * returns a list of the classifications for each sentence
     */
    public ArrayList<String> concatAndJudgeStrings(ArrayList<String> docs, int sampleSize){
        ArrayList<String> sampleDocs = takeSample(sampleSize,docs);
        String concatenated = ListToSentences(sampleDocs);
        return judgeString(concatenated);
    }


    /**
     * converts a list of strings to a single long string.
     * ensures they are formattes such that CoreNLP can correctly identify where sentences end and begin
     * allows data to be passed to the pipeline at once instead of separately
     * */
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




    /**
     * if the application requires speed, it cannot use the entire corpus.
     * this method pulls a random sample from the corpus to use
     * */
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
    //currently unused
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