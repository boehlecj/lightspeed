package com.cboehle.lightspeed.demo.results;

import com.cboehle.lightspeed.demo.util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Container class to persist quiz result history and to provide comparative
 * results of current score to previous score.
 * @author chboeh
 */
public class QuizHistory {

    private final String homeDir = System.getProperty("user.home");
    private final String fileName = "demoQuizHistory.csv";
    private final LinkedList<QuizResults> prevResults = new LinkedList();

    private File historyFile;

    public QuizHistory() {
        File dir = new File(homeDir + File.separator + ".demoQuiz");
        historyFile = new File(dir.getAbsolutePath() + File.separator + fileName);

        //If directory doesn't exist create it.
        if (!(dir.exists())) {
            if (!dir.mkdir()) {
                System.out.println("Could not create quiz history file!");
                System.out.println(historyFile.getAbsolutePath());
                System.exit(-10);
            }
        }

        //Parse the history file if it exists
        if (historyFile.exists()) {
            parseHistory();
        } else {
            System.out.println("Welcome! This is the first time you are playing taking the quiz.");
        }
    }

    /**
     * Write the current quiz results to the end of the history file.
     * @param result 
     */
    public void writeToFile(QuizResults result) {
        try {
            try (BufferedWriter output = new BufferedWriter(new FileWriter(historyFile, true))) {
                output.newLine();
                output.append(Util.SDF.format(result.getDate()));
                output.append(",");
                output.append(String.valueOf(result.getCorrect()));
                output.append(",");
                output.append(String.valueOf(result.getTotal()));
            }
        } catch (IOException ex) {
            Logger.getLogger(QuizHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Parses the csv history file and stores previous quiz results in a list.
     */
    private void parseHistory() {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(historyFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] history = line.split(cvsSplitBy);
                QuizResults results = new QuizResults(0);
                results.setDate(Util.SDF.parse(history[0]));
                results.setCorrect(Integer.parseInt(history[1]));
                results.setTotal(Integer.parseInt(history[2]));

                prevResults.add(results);

            }

        } catch (IOException | ParseException e) {
            System.out.println(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    /**
     * Prints out comparative results from current quiz to the last quiz taken.
     * @param result 
     */
    public void comparePreviousScore(QuizResults result) {
        QuizResults prev = prevResults.getLast();
        int comp = Float.compare(result.getPercentage(), prev.getPercentage());

        if (comp > 0) {
            System.out.println("Congratulations!");
            System.out.println("You improved from your previous attempt on " +prev.getDateAsString()
            + " by " + (result.getPercentage() - prev.getPercentage()) + "%");
        } else if (comp < 0) {
            System.out.println("Too bad!");
            int comp2 = Float.compare(result.getPercentage(), prev.getPercentage());
            System.out.println("Your previous attempt on " +prev.getDateAsString()
            + " was better by " + (prev.getPercentage() - result.getPercentage()) + "%");
        } else {
            System.out.println("You are consistant!");
            System.out.println("You matched your score from your previous attempt on " +prev.getDateAsString());
        }
    }
}
