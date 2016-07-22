/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetworkseblak;

import java.util.ArrayList;

/**
 *
 * @author Yorozuya
 */
public class Normalization {
    ArrayList<Double> listResult=new ArrayList();
    public Normalization(double min, double max, ArrayList<Double> listValue){
        for(int i=0; i<listValue.size(); i++){
            double tempResult=normalize(min, max, listValue.get(i));
            listResult.add(tempResult);
        }
    }
    public double normalize(double min, double max, double value){
         return (value-min)/(max-min);
    }
    public ArrayList<Double> getResult(){
	return listResult;
    }
}
