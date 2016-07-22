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
public class HiddenNode extends Node{
    double bias;
    double resultValue;
    ArrayList<Weight> listInWeight=new ArrayList();
    public HiddenNode(String index) {
        super(index);
        this.bias=0;
        this.resultValue=0;
        summingFunction();
        sigmoidFunction();
    }
    public void summingFunction(){
        for(int i=0; i<listInWeight.size(); i++){
            resultValue=resultValue+(listInWeight.get(i).weightValue)*(listInWeight.get(i).inValue);
        }
    }
    public void sigmoidFunction(){
        resultValue=1/(1+Math.exp(0-resultValue));
    }
}
