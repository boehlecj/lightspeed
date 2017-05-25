package com.cboehle.lightspeed.demo.json;

/**
 * Option entity class
 * @author chboeh
 */
public class Option {
    private String option;
    private boolean rightAnswer;
    
    public Option(String option){
        this.option = option;
        this.rightAnswer = false;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
    
}
