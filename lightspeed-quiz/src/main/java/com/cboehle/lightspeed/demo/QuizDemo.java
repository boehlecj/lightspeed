package com.cboehle.lightspeed.demo;

import com.cboehle.lightspeed.demo.json.Option;
import com.cboehle.lightspeed.demo.json.Question;
import com.cboehle.lightspeed.demo.json.Quiz;
import com.cboehle.lightspeed.demo.json.Root;
import com.cboehle.lightspeed.demo.results.QuizHistory;
import com.cboehle.lightspeed.demo.results.QuizResults;
import com.cboehle.lightspeed.demo.util.Shuffle;
import com.cboehle.lightspeed.demo.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.io.IOUtils;

/**
 * Main class specified in the Manifest.
 * This main class kicks off the quiz.
 * @author chboeh
 */
public class QuizDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        printBannerFile();
        QuizHistory history = new QuizHistory();

        try {
            List<Question> questions = prepareQuestions();
            QuizResults results = new QuizResults(questions.size());
            Scanner scanner = new Scanner(System.in);

            for (int i = 0; i < questions.size(); i++) {
                Question question = questions.get(i);
                Map<String, Option> optionMap = question.getOptionsAsMap();
                System.out.println("Question " + (i + 1) + " out of " + questions.size());
                System.out.println(question.toString());
                boolean repeat = true;
                String answer = "";
                while (repeat) {
                    System.out.print("Pick Multiple Choice(A,B,C...): ");
                    answer = scanner.nextLine().toUpperCase();
                    if (!Util.isValidAnswer(answer) || !optionMap.containsKey(answer)) {
                        System.out.println(answer + " is an invalid choice!");
                    } else {
                        repeat = false;
                    }
                }

                if (optionMap.get(answer).isRightAnswer()) {
                    System.out.println("Correct!");
                    results.incrementCorrect();
                } else {
                    System.out.println("Sorry! That is incorrect.");
                }

                System.out.println();

            }

            System.out.println(results.toString());
            history.comparePreviousScore(results);
            history.writeToFile(results);
        } catch (IOException ex) {
            System.out.println("Error occured while preparing the questions!");
            System.out.print(ex);
            System.exit(-1);
        }
    }

    /**
     * This method parses the questions from the questions.json file, deserializes
     * the file to POJOs, sets the correct response and randomly sorts both the
     * question order and the answer option order.
     * @return A randomly sorted list of questions with randomly sorted answers
     * @throws IOException 
     */
    private static List<Question> prepareQuestions() throws IOException {
        //question.json file data is a project resource
        InputStream is = QuizDemo.class.getResourceAsStream("/questions.json");
        byte[] jsonData = IOUtils.toByteArray(is);

        ObjectMapper objectMapper = new ObjectMapper();

        //convert json string to object
        Root root = objectMapper.readValue(jsonData, Root.class);
        Quiz quiz = root.getQuiz();
        List<Question> questions = new ArrayList(quiz.getQuestions().size());
        for (Map<String, Object> item : quiz.getQuestions()) {
            //Parse questions and set the correct answer option
            Question question = objectMapper.convertValue(item, Question.class);
            question.setCorrectAnswer();
            questions.add(question);
        }

        //Randomly sort the question and answer options
        Shuffle.shuffleQuestions(questions);

        return questions;
    }

    /**
     * A fun little method that displays the ascii art to the demo quiz.
     */
    private static void printBannerFile() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(QuizDemo.class.getResourceAsStream("/banner.txt")));

            String line = in.readLine();
            while (line != null) {
                System.out.println(line);
                line = in.readLine();
            }
            in.close();
        } catch (IOException ex) {
            System.out.println("Error reading banner file!");
            System.out.println(ex);
            System.exit(-1);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                System.out.println("Error closing banner file!");
                System.exit(-1);
            }
        }
    }
}
