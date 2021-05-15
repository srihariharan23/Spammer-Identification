/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spammer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

/**
 *
 * @author admin
 */
public class FeatureExtraction 
{
    Details dt=new Details();
    SimpleDateFormat sf=new SimpleDateFormat("mm d, yyyy");
    
    FeatureExtraction()
    {
        
    }
    
    
    
    public double GTW(String rid,ArrayList pro)
    {
        double gtw=0;
        try
        {
            
            
            //for(int i=0;i<dt.cand.size();i++)
            //{
              //  String rid=dt.cand.get(i).toString();
                //ArrayList pro=getProduct(rid);  // get product
               
                double max=0;
                for(int j=0;j<pro.size();j++)
                {
                    double gt1=0;
                    String p1=pro.get(j).toString();
                    ArrayList<Date> lt1=new ArrayList();
                    for(int k=0;k<dt.reviews.size();k++)
                    {
                        String sg=dt.reviews.get(k).toString();
                        String g1[]=sg.split("#");
                        if(sg.contains(p1))
                        {
                            if(!lt1.contains(sf.parse(g1[3])))
                                lt1.add(sf.parse(g1[3]));
                        }
                    }
                    //System.out.println(p1+" : "+lt1.size());
                    if(lt1.size()>1)
                    {
                        Collections.sort(lt1);
                        //System.out.println(lt1.get(0)+" : "+lt1.get(lt1.size()-2)+" : "+lt1.get(lt1.size()-1));
            
                        long tm1=lt1.get(0).getTime();
                        long tm2=lt1.get(lt1.size()-1).getTime();
                        long tm3=lt1.get(lt1.size()-2).getTime();
            
                        long diff1=tm2-tm1;
                        long days1 = diff1 / (24 * 60 * 60 * 1000);
                        
                        long diff2=tm2-tm3;
                        long days2 = diff2 / (24 * 60 * 60 * 1000);
                        
                        //System.out.println("\t"+p1+" :  "+days1+" : "+days2);
                        if(days2>5)
                            gt1=0;
                        else
                        {
                            gt1=1-((double)days2/5);
                        }
                        max=Math.max(max, gt1);
                        //System.out.println("\t"+gt1);
                    }
                    
                }
                
                //System.out.println("GTW : "+rid+" = "+max);
            //}
                gtw=max;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return gtw;
    }
    
    
    public double GD(String rid, ArrayList pro)
    {
        double gd=0;
        try
        {
            
            
           // for(int i=0;i<dt.cand.size();i++)
            //{
               // String rid=dt.cand.get(i).toString();
                //ArrayList pro=getProduct(rid);  // get product
               
                double max=0;
                for(int j=0;j<pro.size();j++)
                {
                    double gt1=0;
                    String p1=pro.get(j).toString();
                    double avg1=0;
                    double c1=0;
                    for(int k=0;k<dt.reviews.size();k++)
                    {
                        String sg=dt.reviews.get(k).toString();
                        String g1[]=sg.split("#");
                        if(sg.contains(p1))
                        {
                            avg1=avg1+(Double.parseDouble(g1[2]));
                            c1++;
                        }
                    }
                    avg1=avg1/c1;                    
                    avg1=avg1/5;
                    max=Math.max(avg1, max);
                }
                
               // System.out.println("GD = "+rid+" = "+max);
                gd=max;
            //}
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return gd;
    }
    
     public double GETF(String rid, ArrayList pro)
    {
        double getf=0;
        try
        {
            SimpleDateFormat sf=new SimpleDateFormat("mm d, yyyy");
            
           // for(int i=0;i<dt.cand.size();i++)
           // {
               // String rid=dt.cand.get(i).toString();
                //ArrayList pro=getProduct(rid);  // get product
               
                double max=0;
                for(int j=0;j<pro.size();j++)
                {
                    double gt1=0;
                    String p1=pro.get(j).toString();
                    ArrayList<Date> lt1=new ArrayList();
                    for(int k=0;k<dt.reviews.size();k++)
                    {
                        String sg=dt.reviews.get(k).toString();
                        String g1[]=sg.split("#");
                        if(sg.contains(p1))
                        {
                            if(!lt1.contains(sf.parse(g1[3])))
                                lt1.add(sf.parse(g1[3]));
                        }
                    }
                    //System.out.println(p1+" : "+lt1.size());
                    if(lt1.size()>1)
                    {
                        Collections.sort(lt1);
                        //System.out.println(lt1.get(0)+" : "+lt1.get(lt1.size()-1));
            
                       LocalDate m1=LocalDate.of(lt1.get(0).getYear(),lt1.get(0).getMonth()+1,lt1.get(0).getDate());
                       LocalDate m2=LocalDate.of(lt1.get(lt1.size()-1).getYear(),lt1.get(lt1.size()-1).getMonth()+1,lt1.get(lt1.size()-1).getDate());
                        double mon=m1.until(m2, ChronoUnit.MONTHS);
                       // System.out.println("\t"+p1+" :  "+mon);
                        if(mon>24)
                            gt1=0;
                        else
                        {
                            gt1=1-((double)mon/24.0);
                        }
                        max=Math.max(max, gt1);
                        //System.out.println("\t"+gt1);
                    }
                    
                }
                
               // System.out.println("GETF : "+rid+" = "+max);
                getf=max;
            //}
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getf;
    }
    
    public double GSR(String rid,ArrayList pro)
    {
        double gsr=0;
        try
        {
            double g=0;
            if(rid.contains("#"))
            {
                g=rid.split("#").length;
            }
            else
                g=1;
           // ArrayList pro=getProduct(rid);  // get product
               
            double avg1=0;
            for(int j=0;j<pro.size();j++)
            {
                double gt1=0;
                String p1=pro.get(j).toString();
                
                double c1=0;
                ArrayList at=new ArrayList();
                for(int k=0;k<dt.reviews.size();k++)
                {
                    String sg=dt.reviews.get(k).toString();
                    String g1[]=sg.split("#");
                    if(sg.contains(p1))
                    {
                        if(!at.contains(g1[1]))
                            at.add(g1[1]);
                   }
                }
                avg1=avg1+(g/(double)at.size());
            }
            gsr=avg1/(double)pro.size();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return gsr;
    }
    
    public double GS(String rid,double maxG)
    {
        double gs=0;
        try
        {
            double g=0;
            if(rid.contains("#"))
            {
                String t[]=rid.split("#");
                g=t.length;
            }
            else
                g=1;
            gs=g/maxG;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return gs;
    }
    
    public double GSUP(ArrayList pro,double maxPg)
    {
        double gsup=0;
        try
        {
            gsup=pro.size()/maxPg;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return gsup;
    }
    
    
    public double Tightness(String rid, ArrayList pro,double rv)
    {
        double tg=0;
        try
        {
            double g=0;
            if(rid.contains("#"))
            {
                String t[]=rid.split("#");
                g=t.length;
            }
            else
                g=1;
            tg=rv/(g*(double)pro.size());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return tg;
    }
    
    public double RV(ArrayList pro,String rid)
    {
        double avg=0;
        for(int i=0;i<pro.size();i++)
        {
            double rr[]=getRate(pro.get(i).toString());
            double mean = 0.0;
            for (int ii = 0; ii < rr.length; ii++) 
            {
                mean += rr[ii];
            }
            mean /= rr.length;
            double variance=0;
            for (int ii = 0; ii < rr.length; ii++) 
            {
                variance += (rr[ii] - mean) * (rr[ii] - mean);
            }
            variance /= rr.length;
            avg=avg+variance;
        }
        avg=avg/(double)pro.size();
        
        double g=0;
        if(rid.contains("#"))
        {
            String t[]=rid.split("#");
            g=t.length;
        }
        else
            g=1;
        
        double lg=1/(1+Math.exp(-g+pro.size()-3));
        
       // double rv=2*(1-(1/1+Math.exp(-avg)))*lg;
        return avg;
        //return rv;
    }
    
    public double PRR(ArrayList pro,String rid)
    {
        double prr=0;
        double avg=0;
        
         double g=0;
        if(rid.contains("#"))
        {
            String t[]=rid.split("#");
            g=t.length;
        }
        else
            g=1;
        
        for(int i=0;i<pro.size();i++)
        {
            String p1=pro.get(i).toString();
            ArrayList at=new ArrayList();
            for(int j=0;j<dt.reviews.size();j++)
            {
                String sg=dt.reviews.get(j).toString();
                if(sg.contains(p1))
                {
                    String s1[]=sg.split("#");
                    if(!at.contains(s1[1]))
                        at.add(s1[1]);
                }
            }
            avg=avg+(g/(double)at.size());
        }
        prr=avg/(double)pro.size();
        return prr;
    }
    
   
    public double[] getRate(String p1)
    {
        ArrayList<Double> rate=new ArrayList();
        
        for(int j=0;j<dt.reviews.size();j++)
        {
            String sg=dt.reviews.get(j).toString();
            if(sg.contains(p1))
            {
                String s1[]=sg.split("#");
                rate.add(Double.parseDouble(s1[2]));
                }
        }
        
        double de[]=new double[rate.size()];
        for(int i=0;i<rate.size();i++)
            de[i]=rate.get(i);
        return de;
    }
}
