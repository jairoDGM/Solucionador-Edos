/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import javax.swing.table.DefaultTableModel;
import org.nfunk.jep.JEP;
import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 *
 * @author student
 */
public class MetodoMejorado {  
  //atributos
  //variables creadas y listas
  double xo2=0.0;
  double yo2=0.0;
  double h2=0.0;
  String derivada2;
  double limite2;
  boolean verificador2;
  private double result2= 0.0;
  public String []celda2 = new String[3];
  public DefaultTableModel modelito2;
  DecimalFormat df2 =new DecimalFormat("#.0000");
  private double retorno2= 0.0;
  public LinkedList<Double> lista_x2 = new LinkedList<Double>();
  public LinkedList<Double> lista_y2 = new LinkedList<Double>();
  JEP jep;

public MetodoMejorado(double xo2,double yo2, double h2, String derivada2, double limite2, DefaultTableModel modelito2){
      this.xo2 = xo2;
      this.yo2 = yo2;
      this.h2 = h2;
      this.derivada2 = derivada2;
      this.limite2 = limite2;
      this.verificador2 = true;
      this.modelito2 = modelito2;
  }


  //metodos
  public void printDatosMejorado(double x, double y, double resultDerivada){
        if(verificador2){
            //Guardamos datos en string para mandarlos a la tabla
            celda2[0]= String.valueOf(df2.format(this.xo2));
            celda2[1]= String.valueOf(df2.format(this.yo2));
            celda2[2]= String.valueOf(df2.format(funcionCalculator(this.xo2,this.yo2)));
            //Lo mandamos a la tabla
            modelito2.addRow(celda2);

        }else{
             //Guardamos datos en string para mandarlos a la tabla
            celda2[0]= String.valueOf(df2.format(x));
            celda2[1]= String.valueOf(df2.format(y));
            celda2[2]= String.valueOf(df2.format(resultDerivada));
            //Lo mandamos a la tabla
            modelito2.addRow(celda2);
 
       }
}

public double funcionCalculator(double valorx, double valory){
    //se calcula la funcion
        jep = new JEP();
        this.jep.addStandardFunctions();
        this.jep.addStandardConstants();
        this.jep.addVariable("x", valorx);
        this.jep.addVariable("y", valory);
        this.jep.parseExpression(this.derivada2);
        this.result2 = this.jep.getValue();
        return this.result2;  
}
  

public double eulerSimple(double x0, double y0){
    //se calcula euler normal
  double ySiguiente = y0 + this.h2*funcionCalculator(x0, y0);
  return ySiguiente;  //resultado de la solucion de la edo simple
}

public double getResult2(){
    return Double.parseDouble(df2.format(this.retorno2));
}

public void resolverEdoMejorado(){
    //se resuelve el metodo de euler mejorado
  double xAnterior = 0;
  double xActual = this.xo2;
  double yAnterior = 0;
  double yActual = this.yo2;
  double AnteriorDerivada = 0;

  while(xActual<=this.limite2){
      if(verificador2){  //caso base

          xAnterior = xActual;
          yAnterior = yActual;
          lista_x2.add(xAnterior);
          lista_y2.add(yAnterior);
          AnteriorDerivada = funcionCalculator(xActual, yActual);
          printDatosMejorado(xActual, yActual, funcionCalculator(xActual, yActual));
          verificador2 = false;

      }else{ //casos posteriores

          xActual = xAnterior + this.h2;
          yActual = yAnterior + (this.h2*((AnteriorDerivada + funcionCalculator(xActual, eulerSimple(xAnterior, yAnterior)))/2));
          lista_x2.add(xActual);
          lista_y2.add(yActual);
          printDatosMejorado(xActual, yActual, funcionCalculator(xActual, yActual));

          xAnterior = xActual;
          yAnterior = yActual;
          AnteriorDerivada = funcionCalculator(xActual, yActual);

      }
  }

     this.retorno2=yActual;
}
}
