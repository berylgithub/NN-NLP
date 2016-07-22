/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetworkseblak;

import java.io.IOException;

/**
 *
 * @author Yorozuya
 */
public class NeuralNetworkSeblak {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        //inisialisasi
//        NeuralModel model2=new NeuralModel();
//        model2.createInputLayer(3);
//        model2.createHiddenLayer(2);
//        model2.createOutputLayer(1);
//        
//        model2.inLayer.listInNode.get(0).value=20;
//        model2.inLayer.listInNode.get(1).value=100;
//        model2.inLayer.listInNode.get(2).value=0.1;
//        
//        model2.targetClass=0.8;
//        model2.lr=0.01;
//        model2.epoch=10000;
//        
//        model2.generateWeight();
//        model2.setWeightOnNode();
//        
//        System.out.println(model2.inLayer.listInNode.size()+" "+model2.listHidLayer.get(0).listHidNode.size()+" "+model2.outLayer.listOutNode.size());
//        System.out.println(model2.listWeight.size());
//        System.out.println(model2.error);
//        for(int i=0; i<model2.listWeight.size(); i++){
//            System.out.print(model2.listWeight.get(i).weightValue+" ");
//        }
//        System.out.println();
//        System.out.println();
//        Backpropagation bp=new Backpropagation(model2);
//        bp.trainModelV2();
//        
//        System.out.println(bp.neuralModel.outLayer.listOutNode.get(0).resultValue);
//        System.out.println(bp.neuralModel.error);
//        for(int i=0; i<bp.neuralModel.listWeight.size(); i++){
//            System.out.print(bp.neuralModel.listWeight.get(i).weightValue+" ");
//        }
//        System.out.println();
//        for(int i=0; i<bp.neuralModel.listHidLayer.get(0).listHidNode.size(); i++){
//            for(int j=0; j<bp.neuralModel.listHidLayer.get(0).listHidNode.get(i).listInWeight.size(); j++){
//                System.out.print(bp.neuralModel.listHidLayer.get(0).listHidNode.get(i).listInWeight.get(j).weightValue+", ");
//            }
//        }
//        System.out.println(bp.neuralModel.outLayer.listOutNode.get(0).listInWeight.size());
//        System.out.println();
//        for(int i=0; i<bp.neuralModel.listWeight.size(); i++){
//            System.out.print(bp.neuralModel.listWeight.get(i).inValue+" ");
//        }
        
        //test2
        NeuralModel nM1=new NeuralModel();
        nM1.loadDataSetTrainingFromFile("D:\\Proyekan\\TabelTraining.txt");
        nM1.loadDataSetTestFromFile("D:\\Proyekan\\TabelTesting.txt");
        nM1.createInputLayer(9);
        nM1.createHiddenLayer(5);
        nM1.createOutputLayer(1);
        nM1.generateWeight();
        nM1.setLR(0.01);
        nM1.setEpoch(10000);
        Backpropagation bp=new Backpropagation(nM1);
        bp.trainModel();
        bp.testModel();
        
        
    }
    
}
