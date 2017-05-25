/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chboeh.lightspeed.demo.results;

import com.cboehle.lightspeed.demo.results.QuizResults;
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
public class QuizResultsTest {
    private QuizResults results;
    
    @Before
    public void setUp() {
        results = new QuizResults(5);
    }

     @Test
     public void testResults() {
         for (int i = 0; i < 3; i++) {
             results.incrementCorrect();
         }
         
         assertTrue(results.getCorrect() == 3);
         assertTrue(results.getPercentage() == 60.0);
     }
}
