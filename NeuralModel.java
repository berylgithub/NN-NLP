/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetworkseblak;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Yorozuya
 */
public class NeuralModel {
    ArrayList<HiddenLayer> listHidLayer=new ArrayList();
    InputLayer inLayer;
    OutputLayer outLayer;
    ArrayList<Weight> listWeight=new ArrayList<>();
    double lr;
    int epoch;
    double error, sumError, mSE;
    ArrayList<DatasetSeblak> arrDataTrain=new ArrayList<>();
    ArrayList<DatasetSeblak> arrDataTest=new ArrayList<>();
    ArrayList<Double> arrMSE=new ArrayList<>();
    double targetClass;
    double errPrecision;
    
    
    public NeuralModel() {
    }

    public void setLR(double n){
        this.lr=n;
    }
    
    public void createInputLayer(int jumlah_InputNode){
        inLayer=new InputLayer(jumlah_InputNode);
    }
    public void createHiddenLayer(int jumlah_HiddenNode){
        HiddenLayer hidLayer=new HiddenLayer(jumlah_HiddenNode);
        listHidLayer.add(hidLayer);
    }
    public void createOutputLayer(int jumlah_OutputNode){
        outLayer=new OutputLayer(jumlah_OutputNode);
    }
    public void setSummingFunction(String nama_function){}//anggap sigmoid aja
    public void setLearningRate(double lr){
        this.lr=lr;
    }
    public void setEpoch(int epoch){
        this.epoch=epoch;
    }
    public void generateInputWeight(){
        for(int i=0; i<inLayer.listInNode.size(); i++){
            for(int j=0; j<listHidLayer.get(0).listHidNode.size(); j++){
                Weight weight=new Weight(inLayer.listInNode.get(i), listHidLayer.get(0).listHidNode.get(j));
                listWeight.add(weight);
            }
        }
    }//set jumlah weight yang muncul diantara input layer dan hidden layer PERTAMA, otomatis mengikuti jumlah node
    public void generateOutputWeight(){
        for(int i=0; i<listHidLayer.get(listHidLayer.size()-1).listHidNode.size(); i++){
            for(int j=0; j<outLayer.listOutNode.size(); j++){
                Weight weight=new Weight(listHidLayer.get(listHidLayer.size()-1).listHidNode.get(i), outLayer.listOutNode.get(j));
                listWeight.add(weight);
            }
        }
    }//set jumlah weight yang ada diantara hidden layer TERAKHIR dan output layer, otomatis mengikuti jumlah node
    public void generateHiddenWeight(){
        if(listHidLayer.size()>1){
            for(int i=0; i<listHidLayer.size()-1; i++){
                for(int j=0; j<listHidLayer.get(i).listHidNode.size(); j++){
                    for(int k=0; k<listHidLayer.get(i+1).listHidNode.size(); k++){
                        Weight weight=new Weight(listHidLayer.get(i).listHidNode.get(j), listHidLayer.get(i+1).listHidNode.get(k));
                        listWeight.add(weight);
                    }
                }
            }
        }
    }//set jumlah weight yang ada diantara hidden layer dan output layer
    public void generateWeight(){
        generateInputWeight();
        generateHiddenWeight();
        generateOutputWeight();
    }
    
    public void setWeightOnNode(){
        for(int i=0; i<this.listHidLayer.get(0).listHidNode.size(); i++){
            this.listHidLayer.get(0).listHidNode.get(i).listInWeight=searchWeightBasedOnNode(listHidLayer.get(0).listHidNode.get(i));
        }
        this.outLayer.listOutNode.get(0).listInWeight=searchWeightBasedOnNode(this.outLayer.listOutNode.get(0));
    }
    public void calError(){
        this.error=this.targetClass-this.outLayer.listOutNode.get(0).resultValue;
        this.sumError += error * error;
    }
    public void calMSE(){
        this.mSE=this.sumError/this.arrDataTrain.size();
        this.arrMSE.add(this.mSE);
    }
    public void calSumError(){
        this.errPrecision=this.errPrecision+this.error;
    }
    public void calErrorPrecision(){
        this.errPrecision=(100-(this.errPrecision/arrDataTest.size()))/100;
    }
    public void setZeroError(){
        this.sumError=0;
//        this.mSE=0;
    }
    public ArrayList<Weight> searchWeightBasedOnNode(Node node){
        ArrayList<Weight> arrWeight=new ArrayList();
        Weight tempWeight=null;
        for(int i=0; i<this.listWeight.size(); i++){
            if(this.listWeight.get(i).outNode.equals(node)){
                tempWeight=this.listWeight.get(i);
                arrWeight.add(tempWeight);   
            }
        }
        return arrWeight;
    }
    public void loadDataSetTraining(ArrayList<DatasetSeblak> arrDataset){
        this.arrDataTrain=arrDataset;
    }
    public void setDatasetToNode(DatasetSeblak data){
        for(int i=0; i<inLayer.listInNode.size()-1; i++){
            inLayer.listInNode.get(i).value=data.arrRowData.get(i);
        }
        this.targetClass=data.arrRowData.get(9);
    }
    public void saveModel(NeuralModel nNM){
        
    }
    public void loadModel(NeuralModel nNM){
        
    }
    public void loadDataSetTrainingFromFile(String url) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(url));
        String line;
        String[] splitLine;
        DatasetSeblak tempData;
        ArrayList<DatasetSeblak> arrTempData=new ArrayList();
        while ((line = br.readLine()) != null) {
            splitLine=line.split(" ");
            tempData = new DatasetSeblak();
            tempData.setDatasetFromDouble(Double.parseDouble(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]), Double.parseDouble(splitLine[4]), Double.parseDouble(splitLine[5]), Double.parseDouble(splitLine[6]), Double.parseDouble(splitLine[7]), Double.parseDouble(splitLine[8]), Double.parseDouble(splitLine[9]));
            arrTempData.add(tempData);
         }
        arrDataTrain=arrTempData;
    }
    public void loadDataSetTestFromFile(String url) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(url));
        String line;
        String[] splitLine;
        DatasetSeblak tempData;
        ArrayList<DatasetSeblak> arrTempData=new ArrayList();
        while ((line = br.readLine()) != null) {
            splitLine=line.split(" ");
            tempData = new DatasetSeblak();
            tempData.setDatasetFromDouble(Double.parseDouble(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]), Double.parseDouble(splitLine[4]), Double.parseDouble(splitLine[5]), Double.parseDouble(splitLine[6]), Double.parseDouble(splitLine[7]), Double.parseDouble(splitLine[8]), Double.parseDouble(splitLine[9]));
            arrTempData.add(tempData);
         }
        arrDataTest=arrTempData;
    }
}
