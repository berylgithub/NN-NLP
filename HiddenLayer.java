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
public class HiddenLayer {
    String index;
    ArrayList<HiddenNode> listHidNode=new ArrayList<>(); 
    public HiddenLayer(int jumlah_HiddenNode){
	for(int i=0; i<jumlah_HiddenNode; i++){
            HiddenNode hNode=new HiddenNode("h"+i);
            listHidNode.add(hNode);
        }
    }
    public int countNode(){
	return listHidNode.size();
    }
}
