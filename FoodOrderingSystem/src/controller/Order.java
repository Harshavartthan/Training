
package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Order
{

    final static String FILEPATH = "C:\\Users\\91936\\Desktop\\ASPIRE\\FoodOrderingSystem\\FoodandPrice.txt";

    Scanner scanner = new Scanner(System.in);
    Payment payment = new Payment();

    static int total;
    String foodName;
    int quantity;
    String addItemYesorNo;

    Map<String, Integer> callMap = MapforOrderFood();
    
    public void order()
    {
        while(true)
        {
            System.out.println("Enter the Name of the Dish");
            foodName = scanner.next();
            if(callMap.containsKey(foodName))
            {
                System.out.println("You Have Ordered "+foodName);
                System.out.println();
                System.out.println("Enter how much you need");
                quantity = scanner.nextInt();
                total = total + quantity*(callMap.get(foodName));
            }
            else
            {
                System.out.println("No such Food is found");
                order();
            }

            System.out.println();
            System.out.println("Do you want to add any Items");
            System.out.println("If Yes enter 'Y' or 'y'");
            System.out.println("If No enter 'N' or 'n'");
            System.out.println();

            addItemYesorNo = scanner.next();
            System.out.println();
            
            if(addItemYesorNo.equalsIgnoreCase("Y"))
            {
                continue;
            }
            else if(addItemYesorNo.equalsIgnoreCase("N"))
            {
                System.out.println("Your Bill is "+total);
                break;
            }
            else
            {
                System.out.println("Invalid Input");
                order();
            }
        }
        payment.payment(total);
    }
    private Map<String, Integer> MapforOrderFood() 
    {
        Map<String, Integer> orderfood = new HashMap<String, Integer>();
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
                        orderfood.put(dish, price);
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
        return orderfood;
    }
}
