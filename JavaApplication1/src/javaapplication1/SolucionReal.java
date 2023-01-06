/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import org.nfunk.jep.JEP;
import java.text.DecimalFormat;


/**
 *
 * @author student
 */
public class SolucionReal {
    String funcionR;
    double xinicial=0.0;
    double result=0.0;
    
    JEP jep;
    
    
public SolucionReal(String funcionR, double xinicial ){
    this.funcionR=funcionR;
    this.xinicial=xinicial;  
}


public double funcioncalculator(){
    //Solucionamos la funcion ingresada para la solucion real
    jep = new JEP();
    this.jep.addStandardFunctions();
    this.jep.addStandardConstants();
    this.jep.addVariable("x", this.xinicial);
    this.jep.parseExpression(this.funcionR);
    this.result = this.jep.getValue();
    return this.result;      
   
    }
    
}
