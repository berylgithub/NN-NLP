/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetworkseblak;

import java.util.ArrayList;
import java.io.*;
/**
 *
 * @author Yorozuya
 */
public class Backpropagation {
    NeuralModel neuralModel;

    public Backpropagation(NeuralModel neuralModel) {
        this.neuralModel = neuralModel;
    }
    
    public Backpropagation() {
        
    }
    
    public void trainModel() throws FileNotFoundException, UnsupportedEncodingException{
        PrintWriter writer = new PrintWriter("D:\\Proyekan\\TabelHasilTraining.txt", "UTF-8");
        for(int i=0; i<neuralModel.epoch; i++){
            for(int j=0; j<neuralModel.arrDataTrain.size(); j++){
                neuralModel.setDatasetToNode(neuralModel.arrDataTrain.get(j));
                sumAllValue();
                neuralModel.calError();
                updateAllValueWeightByIndex();
                updateAllBias();
                neuralModel.setWeightOnNode();
                writer.println(neuralModel.outLayer.listOutNode.get(0).resultValue+"\t"+neuralModel.targetClass+"\t"+neuralModel.error);
                
                
//                neuralModel.saveModel(neuralModel);
            }
            neuralModel.calMSE();
            writer.println(neuralModel.mSE);
            neuralModel.setZeroError();
        }
        writer.close();
    }
    public void trainModelV2(){
        for(int i=0; i<neuralModel.epoch; i++){
            sumAllValue();
            neuralModel.calError();
            updateAllValueWeightByIndex();
            updateAllBias();
            neuralModel.setWeightOnNode();
        }
    }
    public void testModel() throws FileNotFoundException, UnsupportedEncodingException{
        PrintWriter writer = new PrintWriter("D:\\Proyekan\\TabelHasilTesting.txt", "UTF-8");
        for(int i=0; i<neuralModel.arrDataTest.size(); i++){
            neuralModel.setDatasetToNode(neuralModel.arrDataTest.get(i));
            sumAllValue();
            neuralModel.calError();
            neuralModel.calSumError();
            writer.println(neuralModel.outLayer.listOutNode.get(0).resultValue+"\t"+neuralModel.targetClass+"\t"+neuralModel.error);
        }
        neuralModel.calErrorPrecision();
        writer.println(neuralModel.errPrecision);
        writer.close();
        
    }
    public void sumAllValue(){//RUMUS SEMENTARA
        for(int i=0; i<neuralModel.listHidLayer.size(); i++){
            for (int j=0; j<neuralModel.listHidLayer.get(i).listHidNode.size(); j++){
                neuralModel.listHidLayer.get(i).listHidNode.get(j).summingFunction();
                neuralModel.listHidLayer.get(i).listHidNode.get(j).sigmoidFunction();
                //System.out.print(neuralModel.listHidLayer.get(i).listHidNode.get(j).bias+" ");
                for(int k=0; k<neuralModel.listWeight.size(); k++){
                    neuralModel.listWeight.get(k).setInValue();
                }
            }
        }
        neuralModel.outLayer.listOutNode.get(0).summingFunction();
        neuralModel.outLayer.listOutNode.get(0).sigmoidFunction();
        //System.out.print(neuralModel.outLayer.listOutNode.get(0).bias+" ");
        for(int k=0; k<neuralModel.listWeight.size(); k++){
            neuralModel.listWeight.get(k).setInValue();
        }
    }
    public void updateAllValueWeight(){//RUMUS SEMENTARA
        for(int i=neuralModel.listWeight.size()-1; i>=0; i--){
            Weight weight=new Weight();
            weight=updateValueWeight(neuralModel.listWeight.get(i));
            //System.out.print(weight.weightValue+" ");
            neuralModel.listWeight.set(i, weight);
        }
        //System.out.println();
    }
    public void updateAllValueWeightByIndex(){//RUMUS SEMENTARA
        for(int i=neuralModel.listWeight.size()-1; i>=0; i--){
            updateValueWeightByIndex(i);
            //System.out.print(weight.weightValue+" ");
        }
        //System.out.println();
    }
    public void updateAllBias(){//RUMUS SEMENTARA
        HiddenNode hiddenNode=null;
        OutputNode outputNode=null;
        for(int i=neuralModel.listHidLayer.get(0).listHidNode.size()-1; i>=0; i--){
            hiddenNode=(HiddenNode)updateBias(neuralModel.listHidLayer.get(0).listHidNode.get(i));
            neuralModel.listHidLayer.get(0).listHidNode.set(i, hiddenNode);
        }
        outputNode=(OutputNode)updateBias(neuralModel.outLayer.listOutNode.get(0));
        neuralModel.outLayer.listOutNode.set(0, outputNode);
        
    }
    public Weight updateValueWeight(Weight weight){ //RUMUS SEMENTARA
        double tempResult=0;
        Weight tempWeight=weight;
        Weight nextWeight=searchNextWeightBasedOnWeight(tempWeight);
        if(tempWeight.inNode.getClass().equals(InputNode.class)){
            tempResult=neuralModel.lr*neuralModel.error*tempWeight.inValue*(1-(Math.pow(((HiddenNode)tempWeight.outNode).resultValue, 2)))*(1-(Math.pow(neuralModel.outLayer.listOutNode.get(0).resultValue, 2)))*nextWeight.weightValue;
            tempWeight.weightValue=tempResult;
        }
        else {
            tempResult=neuralModel.lr*neuralModel.error*tempWeight.inValue*(1-(Math.pow(((HiddenNode)tempWeight.outNode).resultValue, 2)));
            tempWeight.weightValue=tempResult;
        }
        return tempWeight;
    }
    public void updateValueWeightByIndex(int index){
        double tempResult=0;
        Weight weight=neuralModel.listWeight.get(index);
        Weight nextWeight=searchNextWeightBasedOnWeight(weight);
        if(weight.inNode.getClass().equals(InputNode.class)){
            tempResult=neuralModel.lr*neuralModel.error*weight.inValue*(1-(Math.pow(((HiddenNode)weight.outNode).resultValue, 2)))*(1-(Math.pow(neuralModel.outLayer.listOutNode.get(0).resultValue, 2)))*nextWeight.weightValue;
            weight.weightValue=weight.weightValue+tempResult;
            neuralModel.listWeight.set(index, weight);
        }
        else {
            tempResult=neuralModel.lr*neuralModel.error*weight.inValue*(1-(Math.pow(((HiddenNode)weight.outNode).resultValue, 2)));
            weight.weightValue=weight.weightValue+tempResult;
            neuralModel.listWeight.set(index, weight);
        }

    }
    public Node updateBias(Node node){ //RUMUS SEMENTARA
        double tempResult=0;
        Node tempNode=null;
        if(node.getClass().equals(HiddenNode.class)){
            HiddenNode hiddenNode=(HiddenNode)node;
            Weight tempWeight=searchNextWeightBasedOnNode(hiddenNode);
            tempResult=neuralModel.lr*neuralModel.error*(1-(Math.pow(hiddenNode.resultValue,2)))*(1-(Math.pow(neuralModel.outLayer.listOutNode.get(0).resultValue, 2)))*tempWeight.weightValue;
            hiddenNode.bias=hiddenNode.bias+tempResult;
            tempNode=hiddenNode;
        }
        else{
            OutputNode outNode=(OutputNode)node;
            tempResult=neuralModel.lr*neuralModel.error*(1-(Math.pow(neuralModel.outLayer.listOutNode.get(0).resultValue, 2)));
            outNode.bias=outNode.bias+tempResult;
            tempNode=outNode;
        }
        return tempNode;
    }
    public Weight searchNextWeightBasedOnWeight(Weight weight){//RUMUS SEMENTARA
        HiddenNode tempHiddenNode=(HiddenNode) weight.outNode;
        Weight tempWeight=null;
        for(int i=0; i<neuralModel.listWeight.size(); i++){
            if(neuralModel.listWeight.get(i).inNode==tempHiddenNode){
                tempWeight=neuralModel.listWeight.get(i);
            }
        }
        return tempWeight;
    }
    public Weight searchNextWeightBasedOnNode(HiddenNode hiddenNode){//RUMUS SEMENTARA
        Weight tempWeight=null;
        for(int i=0; i<neuralModel.listWeight.size(); i++){
            if(neuralModel.listWeight.get(i).inNode==hiddenNode){
                tempWeight=neuralModel.listWeight.get(i);
            }
        }
        return tempWeight;
    }
    
}
