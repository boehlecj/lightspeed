package com.cboehle.lightspeed.demo.json;

import com.cboehle.lightspeed.demo.util.Util;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Question entity class
 * @author chboeh
 */
@JsonPropertyOrder({
    "question",
    "options",
    "answer"
})
public class Question {

    @JsonProperty("question")
    private String question;
    @JsonProperty("options")
    private List<Option> options;
    @JsonProperty("answer")
    private String answer;

    private Map<String, Option> mapOptions;

    @JsonProperty("question")
    public String getQuestion() {
        return question;
    }

    @JsonProperty("question")
    public void setQuestion(String question) {
        this.question = question;
    }

    @JsonProperty("answer")
    public String getAnswer() {
        return answer;
    }

    @JsonProperty("answer")
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Correct answer is applied before random sorting of answer options.
     */
    public void setCorrectAnswer() {
        for (int i = 0; i < options.size(); i++) {
            if (Integer.parseInt(answer) == (i + 1)) {
                options.get(i).setRightAnswer(true);
            }
        }
    }

    @JsonProperty("options")
    public List<Option> getOptions() {
        return options;
    }

    @JsonProperty("options")
    public void setOptions(List<Option> options) {
        this.options = options;
    }

    /**
     * Convenience method to get options and a map with the respective answer
     * letters as a key.
     * @return a map of answer options
     */
    public Map<String, Option> getOptionsAsMap() {
        if (mapOptions == null) {
            mapOptions = new LinkedHashMap();
            for (int i = 0; i < options.size(); i++) {
                String optionLetter = Util.getCharForNumber(i + 1);
                mapOptions.put(optionLetter, options.get(i));
            }
        }

        return mapOptions;
    }

    /**
     * Formats a string of answer options.
     * @return string for answer option layout in the console
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Question:");
        sb.append(System.getProperty("line.separator"));
        sb.append(question);
        sb.append(System.getProperty("line.separator"));

        Iterator<Entry<String, Option>> iter = getOptionsAsMap().entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, Option> next = iter.next();
            sb.append(next.getKey()).append("  ");
            sb.append(next.getValue().getOption());
            sb.append(System.getProperty("line.separator"));

        }

        return sb.toString();
    }

}
