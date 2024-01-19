package view;

import controller.Order;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.LinkedHashMap;

public class Display 
{
    final static String FILEPATH = "C:\\Users\\91936\\Desktop\\ASPIRE\\FoodOrderingSystem\\FoodandPrice.txt";

    Order orderfood = new Order();

    public void display()
    {

        System.out.printf("| %-13s | %-5s |%n"," ITEMS " , "PRICE");
        
        LinkedHashMap<String, Integer> callMap = MapfromTextfile();

        for(Map.Entry<String, Integer> entry : callMap.entrySet())
        {
            System.out.printf("| %-13s | %-5s |%n", entry.getKey() , entry.getValue());
        }
        System.out.println();
        orderfood.order();
    }

    public void startDisplay()
    {
        System.out.println("*******//Hotel AS\\*******");
        System.out.println("--------------------------");
        System.out.println("       TODAY'S MENU       ");
        System.out.println("--------------------------");

        System.out.printf("| %-13s | %4s |%n", "    ITEMS   " , "PRICE");
        
        Map<String, Integer> callMap = MapfromTextfile();
        
        for(Map.Entry<String, Integer> entry : callMap.entrySet()) 
        {
            System.out.printf("| %-13s | %-5s |%n", entry.getKey() , entry.getValue());
        }

         System.out.println();
        System.out.println("These are the items available for today ");
       
    }
    // BufferReader
    private LinkedHashMap<String, Integer> MapfromTextfile() 
    {
        LinkedHashMap<String, Integer> mapFromTextFile = new LinkedHashMap<String, Integer>();
        BufferedReader bufferreader = null;
    
        try
        {
            bufferreader = new BufferedReader(new FileReader(FILEPATH));
            String line = null;
            while((line = bufferreader.readLine())!=null)
            {
                String[] parts = line.split(":");
                String dish = parts[0].trim();
                Integer price = Integer.parseInt(parts[1].trim());
                if(!dish.equals("")&&!price.equals(""))
                {
                    mapFromTextFile.put(dish, price);
                }
            }
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
        finally
        {
            if(bufferreader != null)
            {
                try
                {
                    bufferreader.close();
                }
                catch(Exception exception)
                {
                    System.out.println(exception);
                };
            }
        }
        return mapFromTextFile;
    }
}

