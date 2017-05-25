package com.cboehle.lightspeed.demo.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Root entity class of the json schema
 * @author chboeh
 */
public class Root {
    @JsonProperty("quiz")
    private Quiz quiz;

    @JsonProperty("quiz")
    public Quiz getQuiz() {
        return quiz;
    }

    @JsonProperty("quiz")
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    
    
}
