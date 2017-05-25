package com.cboehle.lightspeed.demo.util;

import com.cboehle.lightspeed.demo.json.Option;
import com.cboehle.lightspeed.demo.json.Question;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Utility class to assist in randomization of question and answer options.
 * @author chboeh
 */
public class Shuffle {
    
    /**
     * Randomly sorts the question order
     * @param questions 
     */
    public static void shuffleQuestions(List<Question> questions) {
        for (Question question : questions) {
            shuffleOptions(question.getOptions());
        }
        Collections.shuffle(questions, new Random(System.nanoTime()));
    }

    /**
     * Randomly sorts the answer options
     * @param options 
     */
    private static void shuffleOptions(List<Option> options){
         Collections.shuffle(options, new Random(System.nanoTime()));
    }
    
    
}
