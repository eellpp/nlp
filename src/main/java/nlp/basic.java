package nlp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.LoggerFactory;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.NERCombinerAnnotator;
import edu.stanford.nlp.pipeline.POSTaggerAnnotator;
import edu.stanford.nlp.pipeline.RegexNERAnnotator;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.TokenizerAnnotator;
import edu.stanford.nlp.pipeline.WordsToSentencesAnnotator;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.util.CoreMap;

public class basic
{
    	
    public static void main( String[] args ) throws ClassNotFoundException, IOException
    {
//    	String text = "I saw a cat. I am John from India. I am 12 years old. I have 50$.";
    	String text = "1231231212 2/05/2015 12/01/2013 15645623A India 34.34 876";
        Annotator tokenizer = new TokenizerAnnotator("en");
        Annotator ssplit = new WordsToSentencesAnnotator();
        Annotation annotation = new Annotation(text);
        tokenizer.annotate(annotation);
        ssplit.annotate(annotation);
        POSTaggerAnnotator posTaggerAnnotator = new POSTaggerAnnotator();
        posTaggerAnnotator.annotate(annotation);
        NERCombinerAnnotator nerCombinerAnnotator = new NERCombinerAnnotator();
        nerCombinerAnnotator.annotate(annotation);
        
        RegexNERAnnotator regexNERAnnotator = new RegexNERAnnotator("locations.txt");
        regexNERAnnotator.annotate(annotation);
        
        // Get sentences
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        
        for(CoreMap sentence: sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
              // this is the text of the token
              String word = token.get(TextAnnotation.class);
              // this is the POS tag of the token
              String pos = token.get(PartOfSpeechAnnotation.class);
              // this is the NER label of the token
              String ne = token.get(NamedEntityTagAnnotation.class);
              
              System.out.println("word: " + word + " pos: " + pos + " ne:" + ne);
            }
//        new basicAnalysis().runBasicAnalysis();
    }

	
	}
}
    
    