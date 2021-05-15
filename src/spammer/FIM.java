/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.HashMap;

/**
 *
 * @author admin
 */
public class FIM 
{
    List<int[]> itemsets ;
    
    String transaFile;
    
    int numItems;
    
    int numTransactions;
    
    double minSup;
   
    String out2="";
    
    Details dt=new Details();
    
    FIM(String tr,double su)
    {
        transaFile=tr;
        minSup=su;
    }
    
    public void readTransaction()
    {
        try
        {
            numItems = 0;
            numTransactions=0;
            BufferedReader data_in = new BufferedReader(new FileReader(transaFile));
            while (data_in.ready()) 
            {              
                String line=data_in.readLine();
                if (line.matches("\\s*")) continue; 
                numTransactions++;
                StringTokenizer t = new StringTokenizer(line," ");
                while (t.hasMoreTokens()) 
                {
                    int x = Integer.parseInt(t.nextToken());
                    
                    if (x+1>numItems) 
			numItems=x+1;
                }              
            }  
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void runFIM()
    {
        try
        {
            
            // generate the candidates of size 1
            createItemsetsOfSize1(); 
			
            int itemsetNumber=1; 
            int nbFrequentSets=0;
       
            while (itemsets.size()>0)
            {

                calculateFrequentItemsets();

                if(itemsets.size()!=0)
                {
                    nbFrequentSets+=itemsets.size();
                    createNewItemsetsFromPreviousOnes();
                }

                itemsetNumber++;
            }

             
            File fe2=new File("out1.txt");
            FileOutputStream fos2=new FileOutputStream(fe2);
            fos2.write(out2.getBytes());
            fos2.close();
		
           
           
            System.out.println("Found "+nbFrequentSets+ " frequents sets");
            System.out.println("Finshed...");
		
		
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    void foundFrequentItemSet(int[] itemset, int support) 
    {
      // 	System.out.println(Arrays.toString(itemset) + "  ("+ ((support / (double) numTransactions))+" "+support+")");
			
	//out2=out2+Arrays.toString(itemset)+"\n";
	out2=out2+Arrays.toString(itemset) + "#"+ ((support / (double) numTransactions))+"\n";		
    }
    
    public void createItemsetsOfSize1() 
    {
        itemsets = new ArrayList<int[]>();
        for(int i=0; i<numItems; i++)
        {
                int[] cand = {i};
                itemsets.add(cand);
        }
	System.out.println("-------- "+itemsets.size()+" : "+itemsets.get(0).length);
        
        /*int a1[]=itemsets.get(6);
        for(int i=0;i<a1.length;i++)
            System.out.print(a1[i]+" ");
        
        System.out.println();*/
        
    }
    
    void createNewItemsetsFromPreviousOnes()
    {
        
        int currentSizeOfItemsets = itemsets.get(0).length;
               
        HashMap<String, int[]> tempCandidates = new HashMap<String, int[]>(); //temporary candidates
       
        
        for(int i=0; i<itemsets.size(); i++)
        {
            for(int j=i+1; j<itemsets.size(); j++)
            {
                int[] X = itemsets.get(i);
                int[] Y = itemsets.get(j);

                assert (X.length==Y.length);
               
                
                int [] newCand = new int[currentSizeOfItemsets+1];
                for(int s=0; s<newCand.length-1; s++) {
                        newCand[s] = X[s];
                }
                   
                int ndifferent = 0;
                
                for(int s1=0; s1<Y.length; s1++)
                {
                        boolean found = false;
                        
                    for(int s2=0; s2<X.length; s2++) 
                    {
                        if (X[s2]==Y[s1]) 
                        {
                                found = true;
                                break;
                        }
                    }
                    if (!found)
                    { 
                        ndifferent++;
                      
                        newCand[newCand.length -1] = Y[s1];
                    }
               
                }
               
                
                assert(ndifferent>0);
               
               
                if (ndifferent==1) 
                {
                        Arrays.sort(newCand);
                        tempCandidates.put(Arrays.toString(newCand),newCand);
                }
            }
        }
       
        
        itemsets = new ArrayList<int[]>(tempCandidates.values());

    }


   
    public void line2booleanArray(String line, boolean[] trans) 
    {
        Arrays.fill(trans, false);
        StringTokenizer stFile = new StringTokenizer(line, " "); 
        while (stFile.hasMoreTokens())
        {
               
            int parsedVal = Integer.parseInt(stFile.nextToken());
            trans[parsedVal]=true; 
        }
    }

   
   
    private void calculateFrequentItemsets() throws Exception
    {      
        
        List<int[]> frequentCandidates = new ArrayList<int[]>();

        boolean match; 
        int count[] = new int[itemsets.size()]; 

        BufferedReader data_in = new BufferedReader(new InputStreamReader(new FileInputStream(transaFile)));

        boolean[] trans = new boolean[numItems];
               
        for (int i = 0; i < numTransactions; i++) 
        {
            String line = data_in.readLine();
            line2booleanArray(line, trans);

            for (int c = 0; c < itemsets.size(); c++) 
            {
                match = true; 
                int[] cand = itemsets.get(c);
                for (int xx : cand) 
                {
                    if (trans[xx] == false) 
                    {
                        match = false;
                        break;
                    }
                }
                if (match) 
                { 
                    count[c]++;
                }
            }

        }
               
        data_in.close();
        
        for (int i = 0; i < itemsets.size(); i++) 
        {
                        
            if ((count[i] / (double) (numTransactions)) > minSup) 
            {
                 int it[]=itemsets.get(i);
                 String sg="";
                for(int j=0;j<it.length;j++)
                {
                    System.out.print(it[j]+" ");
                    sg=sg+dt.rev_Id.get(it[j]-1)+"#";
                    //sg=sg+it[j]+"#";
                }
           
                sg=sg.substring(0, sg.lastIndexOf("#"));
                dt.cand.add(sg);
                System.out.println(count[i] / (double) (numTransactions));
                
                foundFrequentItemSet(itemsets.get(i),count[i]);
                frequentCandidates.add(itemsets.get(i));
            }            
        }
        
        itemsets = frequentCandidates;
    }
    
}
