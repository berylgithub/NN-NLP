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
public class OutputLayer {
    String index;
    ArrayList<OutputNode> listOutNode=new ArrayList<>();
    public OutputLayer(int jumlah_OutputNode){
	for(int i=0; i<jumlah_OutputNode; i++){
            OutputNode oNode=new OutputNode("o"+i);
            listOutNode.add(oNode);
	}
    }
    public int countNode(){
	return listOutNode.size();
    }
}
