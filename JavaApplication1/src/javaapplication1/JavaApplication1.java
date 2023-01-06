/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;
//Importamos las librerias necesarias
import java.text.DecimalFormat;
import java.util.LinkedList;
import org.nfunk.jep.JEP;
import javax.swing.table.DefaultTableModel;

public class JavaApplication1 {
    //Creacion de variables necesarias y listas para llevar a cabo todo
    double h;
    double limite;
    boolean verificador;
    public String []celda = new String[3];
    private String funcion ="";
    public DefaultTableModel modelito;
    public LinkedList<Double> lista_x = new LinkedList<Double>();
    public LinkedList<Double> lista_y = new LinkedList<Double>();
    private double result= 0.0;
    private double constx= 0.0;    
    private double consty= 0.0;
    private double retorno= 0.0;
    //Se dice que se quiere los valores a 4 decimales
    DecimalFormat df =new DecimalFormat("#.0000");
    JEP jep;
    
    public JavaApplication1(double constx, double consty, double h, String funcion, double limite, DefaultTableModel modelito){
        this.constx = constx;
        this.consty = consty;
        this.h = h;
        this.limite = limite;
        this.funcion = funcion;
        this.verificador = true;  //verifica si es primera vez que se esta usando
        this.modelito = modelito;

    }
    //Se ingresan los datos a String 
    public void printDatos(double x, double y, double resultDerivada){
        if(verificador){
            celda[0]= String.valueOf(df.format(this.constx));
            celda[1]= String.valueOf(df.format(this.consty));
            celda[2]= String.valueOf(df.format(derivadaCalculator(this.constx,this.consty)));
            //Mandamos los valores a la tabla
            modelito.addRow(celda);

        }else{
            //Guardamos valores en el string
            celda[0]= String.valueOf(df.format(x));
            celda[1]= String.valueOf(df.format(y));
            celda[2]= String.valueOf(df.format(resultDerivada));
            //mandamos a tabla
            modelito.addRow(celda);
 
       }
    }
    
    
    public double getResult(){
        // Obtenemos el resultado del valor maximo
        return Double.parseDouble(df.format(this.retorno));
    }
    


    public double derivadaCalculator(double valorx, double valory){
        //solucionamos la funcion con la libreria JEP que implementamos
        jep = new JEP();
        this.jep.addStandardFunctions();
        this.jep.addStandardConstants();
        this.jep.addVariable("x", valorx);
        this.jep.addVariable("y", valory);
        this.jep.parseExpression(this.funcion);
        this.result = this.jep.getValue();
        return this.result;      
   
    }
    public void resolverEDO(){
        //Resolvemos la edo con nuestro metodo matematico
        double xAnterior = 0;
        double xActual = this.constx;
        double yAnterior = 0;
        double yActual = this.consty;
        double AnteriorDerivada = 0;

        while(xActual<=this.limite){
            if(verificador){  //caso base

                xAnterior = xActual;
                yAnterior = yActual;
                //Se guardan los valores de x y y en listas para poder generar la grafica
                lista_x.add(xAnterior);
                lista_y.add(yAnterior);
                AnteriorDerivada = derivadaCalculator(xActual, yActual);
                //Mandamos datos a donde los ingresamos a la tabla
                printDatos(xActual, yActual, AnteriorDerivada);
                verificador = false;

            }else{ //casos posteriores

                xActual = xAnterior + this.h;
                yActual = yAnterior + (AnteriorDerivada*this.h);
                //Se guardan los valores de x y y en listas para poder generar la grafica
                lista_x.add(xActual);
                lista_y.add(yActual);
                //Mandamos datos a donde los ingresamos a la tabla
                printDatos(xActual, yActual, derivadaCalculator(xActual, yActual));

                xAnterior = xActual;
                yAnterior = yActual;
                AnteriorDerivada = derivadaCalculator(xActual, yActual);

            }
        }

        this.retorno=yActual;
}
    


    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
