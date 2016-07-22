/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetworkseblak;

import java.util.ArrayList;

/**
 *
 * @author Yorozuya Samurai
 */
public class DatasetSeblak {
    ArrayList<Double> arrRowData=new ArrayList<>();

    public DatasetSeblak() {
        
    }

    public DatasetSeblak(ArrayList<Double> arrData) {
        this.arrRowData=arrData;
    }
    

    public void seDataset(ArrayList<Double> arrData){
        this.arrRowData=arrData;
    }
    
    public void setDatasetFromDouble(double m1, double d1, double m2, double d2, double m3, double d3, double p, double c, double n, double l){
        ArrayList<Double> temp=new ArrayList();
        temp.add(m1);
        temp.add(d1);
        temp.add(m2);
        temp.add(d2);
        temp.add(m3);
        temp.add(d3);
        temp.add(p);
        temp.add(c);
        temp.add(n);
        temp.add(l);
        this.arrRowData=temp;
    }
    
    
}
