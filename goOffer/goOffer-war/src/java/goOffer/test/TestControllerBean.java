/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.test;

import java.io.Serializable;

/**
 *
 * @author max
 */
public class TestControllerBean implements Serializable {
    private String testname;

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }
    public TestControllerBean() {}
    
}
