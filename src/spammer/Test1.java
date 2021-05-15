/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spammer;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Month;
/**
 *
 * @author admin
 */
public class Test1 
{
    public static void main(String ar[])
    {
        try
        {
            //FIM fm=new FIM("tr1.txt",0.01);
          //  fm.readTransaction();
          // fm.runFIM();
            
             
				 String start = "01/01/2012";
String end = "31/07/2012";
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate from = LocalDate.parse(start, formatter);
LocalDate to = LocalDate.parse(end, formatter);
System.out.println(from.until(to, ChronoUnit.MONTHS));

    LocalDate m1=LocalDate.of(2012, 01, 01);
    LocalDate m2=LocalDate.of(2012, 07, 31);
    System.out.println(m1.until(m2, ChronoUnit.MONTHS));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
