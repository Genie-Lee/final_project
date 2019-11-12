package com.Model;

import org.python.util.PythonInterpreter;
 
public class test {
 
    private static PythonInterpreter interpreter;
 
    public static void main(String[] args) {
 
        interpreter = new PythonInterpreter();
        
        
        interpreter.execfile("C:/Users/GIGABYTE/PycharmProjects/testing/test.py");
        
        
       
 
    }
 
}
