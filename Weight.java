/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetworkseblak;

/**
 *
 * @author Yorozuya
 */
public class Weight{
	String index;
	double weightValue, inValue;
	Node inNode, outNode;

        public Weight() {
        }
        
	public Weight(Node inNode, Node outNode){
            this.index=inNode.index+outNode.index;
            this.weightValue=0;
            this.inNode=inNode;
            this.outNode=outNode;
            if(this.inNode.getClass().equals(InputNode.class)){
               this.inValue=this.inNode.value;
            }
            else{
               this.inValue=((HiddenNode)this.inNode).resultValue;
            }
	}
        public void setInValue(){
            if(this.inNode.getClass().equals(InputNode.class)){
               this.inValue=this.inNode.value;
            }
            else{
               this.inValue=((HiddenNode)this.inNode).resultValue;
            }
        }
//        public void updateWeight(double lr){
//            if(inNode.getClass().equals(InputNode.class)){
//                weightValue=lr*inValue*outNode.
//            }
//            else if(outNode.getClass().equals(OutputNode.class)){
//                
//            }
//            else{
//                
//            }
//        }
}
