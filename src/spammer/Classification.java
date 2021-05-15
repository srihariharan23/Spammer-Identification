/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spammer;
import java.util.Random;

import java.text.DecimalFormat;
import weka.core.Instances;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.Logistic;
/**
 *
 * @author admin
 */
public class Classification 
{
    DecimalFormat df=new DecimalFormat("#.###");
    
     public String NBCls(Instances data)
    {
        String res="";
        try
        {
            data.setClassIndex(data.numAttributes() - 1);
            NaiveBayes nb=new NaiveBayes();
            nb.buildClassifier(data);
            
            Random rand1 = new Random(1);  // using seed = 1
            int folds = 10;
            
            Evaluation eval2 = new Evaluation(data);                       
            eval2.crossValidateModel(nb, data, folds, rand1);
 
            double pre=eval2.weightedPrecision();
            double rec=eval2.weightedRecall();
            double fmes=eval2.weightedFMeasure();
            
            double cr2=eval2.correct();
            double ncr2=eval2.incorrect();
            
            double acc2=(double)cr2/(double)(ncr2+cr2);
            acc2=acc2*100;
            
            res=df.format(acc2)+"#"+df.format(pre)+"#"+df.format(rec)+"#"+df.format(fmes);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
                    
        return res;
    }
     
    public String MLPCls(Instances data)
    {
        String res="";
        try
        {
            data.setClassIndex(data.numAttributes() - 1);
            MultilayerPerceptron mlp=new MultilayerPerceptron();
            mlp.buildClassifier(data);
            
           
            Evaluation eval1 = new Evaluation(data);
                       
            Random rand1 = new Random(1);  // using seed = 1
            int folds = 10;
            eval1.crossValidateModel(mlp, data, folds, rand1);
            
            double pre=eval1.weightedPrecision();
            double rec=eval1.weightedRecall();
            double fmes=eval1.weightedFMeasure();
                
            double cr1=eval1.correct();
            double ncr1=eval1.incorrect();
            
            double acc1=(double)cr1/(double)(ncr1+cr1);
            acc1=acc1*100;
            res=df.format(acc1)+"#"+df.format(pre)+"#"+df.format(rec)+"#"+df.format(fmes);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return res;
    } 
    
    public String SMOCls(Instances data)
    {
        String res="";
        try
        {
            data.setClassIndex(data.numAttributes() - 1);
            SMO sm=new SMO();
            sm.buildClassifier(data);
            
                        
            Evaluation eval1 = new Evaluation(data);
                       
            Random rand1 = new Random(1);  // using seed = 1
            int folds = 10;
            eval1.crossValidateModel(sm, data, folds, rand1);
            
            double pre=eval1.weightedPrecision();
            double rec=eval1.weightedRecall();
            double fmes=eval1.weightedFMeasure();
                
            double cr1=eval1.correct();
            double ncr1=eval1.incorrect();
            
            double acc1=(double)cr1/(double)(ncr1+cr1);
            acc1=acc1*100;
            res=df.format(acc1)+"#"+df.format(pre)+"#"+df.format(rec)+"#"+df.format(fmes);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return res;
    }
    
     public String LogCls(Instances data)
    {
        String res="";
        try
        {
            data.setClassIndex(data.numAttributes() - 1);
            Logistic nb=new Logistic();
            nb.buildClassifier(data);
            
            Random rand1 = new Random(1);  // using seed = 1
            int folds = 10;
            
            Evaluation eval2 = new Evaluation(data);                       
            eval2.crossValidateModel(nb, data, folds, rand1);
 
            double pre=eval2.weightedPrecision();
            double rec=eval2.weightedRecall();
            double fmes=eval2.weightedFMeasure();
            
            double cr2=eval2.correct();
            double ncr2=eval2.incorrect();
            
            double acc2=(double)cr2/(double)(ncr2+cr2);
            acc2=acc2*100;
            
            res=df.format(acc2)+"#"+df.format(pre)+"#"+df.format(rec)+"#"+df.format(fmes);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
                    
        return res;
    }
}
