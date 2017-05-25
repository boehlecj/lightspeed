/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chboeh.lightspeed.demo.util;

import com.cboehle.lightspeed.demo.util.Util;
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
public class UtilTest {
    
    public UtilTest() {
    }
    
    @Test
    public void testValidAnswer() {
        String one = "a";
        String two = "b";
    
        assertTrue(Util.isValidAnswer(one));
        assertTrue(Util.isValidAnswer(two));
    }
    
    @Test
    public void testInValidAnswer() {
        String one = "aa";
        String two = "2";
    
        assertFalse(Util.isValidAnswer(one));
        assertFalse(Util.isValidAnswer(two));
    }

}
