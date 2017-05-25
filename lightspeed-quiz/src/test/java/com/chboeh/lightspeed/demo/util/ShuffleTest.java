/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chboeh.lightspeed.demo.util;

import com.cboehle.lightspeed.demo.QuizDemo;
import com.cboehle.lightspeed.demo.json.Question;
import com.cboehle.lightspeed.demo.json.Quiz;
import com.cboehle.lightspeed.demo.json.Root;
import com.cboehle.lightspeed.demo.util.Shuffle;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chboeh
 */
public class ShuffleTest {

    private List<Question> questions;

    public ShuffleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            //question.json file data is a project resource
            InputStream is = QuizDemo.class.getResourceAsStream("/questions.json");
            byte[] jsonData = IOUtils.toByteArray(is);

            ObjectMapper objectMapper = new ObjectMapper();

            //convert json string to object
            Root root = objectMapper.readValue(jsonData, Root.class);
            Quiz quiz = root.getQuiz();
            questions = new ArrayList(quiz.getQuestions().size());
            for (Map<String, Object> item : quiz.getQuestions()) {
                //Parse questions and set the correct answer option
                Question question = objectMapper.convertValue(item, Question.class);
                question.setCorrectAnswer();
                questions.add(question);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Test
    public void testShuffle() {
        List<Question> randQuestions = new ArrayList(questions);
        Shuffle.shuffleQuestions(randQuestions);
        assertFalse(randQuestions.equals(questions));
    }
}
