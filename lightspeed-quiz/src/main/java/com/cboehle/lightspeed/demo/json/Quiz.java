package com.cboehle.lightspeed.demo.json;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Quiz entity class represented in the json schema
 * @author chboeh
 */

public class Quiz {
    private List<Map<String,Object>> questions = new ArrayList();
    
    @JsonAnySetter
    public void setDynamicProperty(String name, Map<String,Object> map) {
        questions.add(map);
    }

    public List<Map<String,Object>> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Map<String,Object>> questions) {
        this.questions = questions;
    }
 
}
