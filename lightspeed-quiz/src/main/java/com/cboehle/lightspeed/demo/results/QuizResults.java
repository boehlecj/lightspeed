package com.cboehle.lightspeed.demo.results;

import com.cboehle.lightspeed.demo.util.Util;
import java.util.Date;

/**
 * Container class for quiz results
 * @author chboeh
 */
public class QuizResults {

    private Date date = new Date();
    private int total = 0;
    private int correct = 0;

    public QuizResults(int total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public void incrementCorrect() {
        correct++;
    }

    /**
     * Calculates the quiz score.
     * @return percentage
     */
    public float getPercentage() {
        float percent = (correct * 100.0f) / total;
        return percent;
    }

    public String getDateAsString() {
        return Util.SDF.format(date);

    }

    /**
     * Override of the toString method to properly format the quiz results that
     * get returned to the user.
     * @return Quiz results as a String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Your Results:");
        sb.append(System.getProperty("line.separator"));
        sb.append(getDateAsString());
        sb.append(System.getProperty("line.separator"));
        sb.append("You answered ").append(correct).append(" out of ").append(total).append(" questions correctly.");
        sb.append(System.getProperty("line.separator"));
        sb.append("Score: ").append(getPercentage()).append("%");

        return sb.toString();
    }
}
