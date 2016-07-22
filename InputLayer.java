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
public class InputLayer {
	String index;
	ArrayList<InputNode> listInNode=new ArrayList();
	public InputLayer(int jumlah_InputNode){
            for(int i=0; i<jumlah_InputNode; i++){
		InputNode iNode=new InputNode("i"+i);
		listInNode.add(iNode);
            }
	}
	public int countNode(){
            return listInNode.size();
	}
        public void setInputValue(ArrayList<Double> listInput){
            for(int i=0; i<listInNode.size(); i++){
                listInNode.get(i).value=(double)listInput.get(i);
            }
        }
        public void setInputData(ArrayList<Double> listInputData){
            
        }
}
