package view;

import model.Logout;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Login
{
    final static String FILEPATH = "C:\\Users\\91936\\Desktop\\ASPIRE\\FoodOrderingSystem\\FoodandPrice.txt";
    final static String FILEPATHFORUSERCREDENTIALS = "C:\\Users\\91936\\Desktop\\ASPIRE\\FoodOrderingSystem\\UserCredentials.txt";
    final static String FILEPATHFORADMINCREDENTIALS = "C:\\Users\\91936\\Desktop\\ASPIRE\\FoodOrderingSystem\\AdminCredentials.txt";

    Display displayfood = new Display();
    Logout logoutfood = new Logout();
    Scanner scanner = new Scanner(System.in);
    Console console = System.console();

    Map<String, Integer> callMap = MapforFoodAdd();

    public boolean verifyAddItems(String newAdd)
    {
        if(callMap.containsKey(newAdd))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean verifyRemoveItems(String removeFood)
    {  
        if(callMap.containsKey(removeFood))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    Map<String, String> AdminMap = AdminDetails();
        private Map<String, String> AdminDetails() 
        {
            Map<String, String> adminNamePwd = new HashMap<String, String>();
            BufferedReader bufferReader = null;
        
            try
            {
                bufferReader = new BufferedReader(new FileReader(FILEPATHFORADMINCREDENTIALS));
                String line = null;
                while((line = bufferReader.readLine())!=null)
                {
                    String[] parts = line.split(":");
        
                    String adminName = parts[0].trim();
                    String adminPassword = parts[1].trim();
        
                    if(!adminName.equals("")&&!adminPassword.equals(""))
                    {
                        adminNamePwd.put(adminName, adminPassword);
                    }
                }
            }
            catch(Exception exception)
            {
                System.out.println(exception);
            }
            finally
            {
                if(bufferReader != null)
                {
                    try
                    {
                        bufferReader.close();
                    }
                    catch(Exception exception)
                    {
                        System.out.println(exception);
                    };
                }
            }
            return adminNamePwd;
        }

    Map<String, String> UserMap = UserDetails();
        private Map<String, String> UserDetails() 
        {
            Map<String, String> userNamePwd = new HashMap<String, String>();
            BufferedReader bufferReader = null;
        
            try
            {
        
                bufferReader = new BufferedReader(new FileReader(FILEPATHFORUSERCREDENTIALS));
                String line = null;
                while((line = bufferReader.readLine())!=null)
                {
                    String[] parts = line.split(":");
                    String UserName = parts[0].trim();
                    String UserPassword = parts[1].trim();
        
                    if(!UserName.equals("")&&!UserPassword.equals(""))
                    {
                        userNamePwd.put(UserName, UserPassword);
                    }
                }
            }
            catch(Exception exception)
            {
                System.out.println(exception);
            }
            finally
            {
                if(bufferReader != null)
                {
                    try
                    {
                        bufferReader.close();
                    }
                    catch(Exception exception)
                    {
                        System.out.println(exception);
                    };
                }
            }
            return userNamePwd;
        }

    private void addNewUserMethod()
    {
        File file = new File(FILEPATHFORUSERCREDENTIALS);
        BufferedWriter bufferWriter = null;;
        try
        {    
            bufferWriter = new BufferedWriter( new FileWriter(file) );

            for(Map.Entry<String, String> entry : UserMap.entrySet())
            {
                bufferWriter.write( entry.getKey() + ":" + entry.getValue() );
                bufferWriter.newLine();
            }
            
            bufferWriter.flush();
 
        }
        catch(IOException exceptionIO)
        {
            exceptionIO.printStackTrace();
        }
        finally
        {    
            try
            {
                bufferWriter.close();
            }
            catch(Exception exception)
            {

            }
        }
    }

    private Map<String, Integer> MapforFoodAdd() 
    {
        Map<String, Integer> addFood = new HashMap<String, Integer>();
        BufferedReader bufferReader = null;
        try
        {   
            bufferReader = new BufferedReader(new FileReader(FILEPATH));
            String line = null;
            while((line = bufferReader.readLine())!=null)
            {

                String[] parts = line.split(":");
                String dish = parts[0].trim();
                Integer price = Integer.parseInt(parts[1].trim());
                if(!dish.equals("")&&!price.equals(""))
                {
                    addFood.put(dish, price);
                }
            }
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
        finally
        {
            if(bufferReader != null)
            {
                try
                {
                    bufferReader.close();
                }
                catch(Exception exception)
                {
                    System.out.println(exception);
                };
            }
        }
        return addFood;
    }

    private void foodAndPriceAdd()
    {
        File file = new File(FILEPATH);
        BufferedWriter bufferWriter = null;
        try
        {    
            bufferWriter = new BufferedWriter(new FileWriter(file));
            for(Map.Entry<String, Integer> entry : callMap.entrySet())
            {
                bufferWriter.write(entry.getKey() + ":" + entry.getValue());
                bufferWriter.newLine();
            }
            bufferWriter.flush();
        }
        catch(IOException exceptionIO)
        {
            exceptionIO.printStackTrace();
        }
        finally
        {    
            try
            {
                bufferWriter.close();
            }
            catch(Exception exception)
            {
                System.out.println(exception);
            }
        }
    }

    // Method to remove Food
    private void ItemtoRemove(String dishName)
    {
        Map<String, String> dishRemover = new HashMap<>();
        try 
        {
            FileReader fileReader = new FileReader(FILEPATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    dishRemover.put(parts[0], parts[1]);
                }
            }
            bufferedReader.close();
            
            callMap.remove(dishName);
            
            FileWriter fileWriter = new FileWriter(FILEPATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Map.Entry<String, Integer> entry : callMap.entrySet()) 
            {
                bufferedWriter.write(entry.getKey() + ":" + entry.getValue());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println();
            System.out.println(dishName + " is Removed Successfully");
        } 
        catch (IOException exceptionIO)
        {
            exceptionIO.printStackTrace();
        }
    }

    public void login()
    {
        System.out.println();
        System.out.println("**********************"+"Login Access"+"**********************");
        System.out.println();
        System.out.println("Select your role");
        System.out.println();
        System.out.println("Press 1 - If you are an Admin");
        System.out.println("Press 2 - If you are a User");
        System.out.println("Press 3 - If you are new to this page");
        System.out.println();

        int press_number = scanner.nextInt();
        if(press_number==1)
        {
            goToAdmin();
        }
        else if(press_number==2)
        {
            goToUser();
        }
        else if(press_number==3)
        {
            goToAddUser();
        }
        else 
        {
            System.out.println("Invalid Input");
            System.out.println("-----------------------------------------------------");
            login();
        }
    }

    public void goToAdmin()
    {
        int flag = 1;
        int admin_press_number;
        System.out.println();
        System.out.println("You have entered as Admin");
        System.out.println();
        System.out.println("Enter your name");
        String adminName = scanner.next();
        System.out.println();
        System.out.println("Enter your Password");
        char[] character = console.readPassword();
        String adminPassword = String.valueOf(character);
        if(verifyAdmin(adminName, adminPassword))
        {
            System.out.println(asterisk(adminPassword));
            System.out.println();
            System.out.println("Yes login Successful " + adminName);
            System.out.println();
            System.out.println("Press 1 for Add Items");
            System.out.println("Press 2 to Remove Items");
            System.out.println();
            admin_press_number = scanner.nextInt();
            if(admin_press_number == 1)
            {
                addingFoodItems();
                while(flag == 1)
                {
                    makeYourChoice();
                }
            }
            else if(admin_press_number == 2)
            {
                removingFoodItems();
                while(flag == 1 )
                {
                    makeYourChoice();
                }
            }
            else
            {
                System.out.println("Invalid Input");
                System.out.println("-----------------------------------------------------");
                goToAdmin();
            }
        }
        else
        {
            System.out.println();
            System.out.println("Retry! Invalid Admin Name or Admin Password");
            System.out.println("-----------------------------------------------------");
            goToAdmin();
        }
    }
    
    public void goToUser()
    {
        System.out.println();
        System.out.println("You have entered as User");
        System.out.println();
        System.out.println("Enter your Name");
        String userName = scanner.next();
        System.out.println();
        System.out.println("Enter your Password");
        char[] character = console.readPassword();
        String userPassword = String.valueOf(character);
        if(verifyUser(userName, userPassword))
        {
            System.out.println(asterisk(userPassword));
            System.out.println();
             System.out.println("Yes, Login Successful as --> " + userName);
             System.out.println();
             displayfood.display();
        }
        else
        {
            System.out.println("Retry! Invalid Password");
            System.out.println("-----------------------------------------------------");
            goToUser();
        }
    }

    public void goToAddUser()
    {
        System.out.println();
        System.out.println("Enter your New User Name");
        System.out.println();
        String addUserName = scanner.next();
        if(addUserName.length()>=4)
        {
            if(UserMap.containsKey((addUserName)))
            {
                System.out.println();
                System.out.println("Already given \n Enter New user name");
                goToAddUser();
            }
            else
            {
                System.out.println();
                System.out.println("Added New User");
                System.out.println();
                System.out.println("Enter New Password");
                String addUserPassword = scanner.next();
                UserMap.put(addUserName, addUserPassword);
                System.out.println();
                System.out.println("You entered as New User");
                addNewUserMethod();
                goToUser();
            }
        }
        else
        {
            System.out.println("Invalid Name!!!");
            System.out.println("-----------------------------------------------------");
            goToAddUser();
        }
    }

    public void addingFoodItems()
    {
        System.out.println("Enter New Item");
        String newItems = scanner.next();
        if(verifyAddItems(newItems))
        {
            System.out.println("Item already exists");
            System.out.println();
            addingFoodItems();
        }
        else
        {
            System.out.println("Enter the price of the added Items");
            int newItems_price = scanner.nextInt();
            callMap.put(newItems,newItems_price);
            foodAndPriceAdd();
            System.out.println();
            System.out.println("Here are the Items added");
            System.out.println(callMap);
            System.out.println();
        }
    }

    public void removingFoodItems()
    {
        System.out.println("Here are the List of Items");
        System.out.println(callMap);
        System.out.println();
        System.out.println("Enter the Item to be Remove");
        String removeFood;
        removeFood = scanner.next();
        if(verifyRemoveItems(removeFood))
        {
            System.out.println("Item does not exist");
            System.out.println();
            removingFoodItems();
        }
        else
        {
            ItemtoRemove(removeFood);
            System.out.println();
            System.out.println("The Updated Food List");
            System.out.println(callMap);
            System.out.println();
        }
    }
    
    public void makeYourChoice()
    {
        System.out.println("If you want to remove more Items");
        System.out.println("Press R");
        System.out.println("If you want to Add more Items");
        System.out.println("Press A");
        System.out.println("If you want to Logout");
        System.out.println("Press L");
        String check_use;
        check_use = scanner.next();
        if(check_use.equalsIgnoreCase("R"))
        {
            removingFoodItems();
        }
        else if(check_use.equalsIgnoreCase("A"))
        {
            addingFoodItems();
        }
        else if(check_use.equalsIgnoreCase("L"))
        {
            System.out.println("Thank you ");
            System.exit(1);
        }
        else
        {
            makeYourChoice();
        }
    }

    // these methods is used to verify the user credentials and admin credentials
    private boolean verifyAdmin (String adminName,String adminPassword)
    {
        if ((AdminMap.containsKey(adminName)) && (AdminMap.get(adminName).equals(adminPassword)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private boolean verifyUser (String userName , String userPassword)
    {
        if ((UserMap.containsKey(userName)) && (UserMap.get(userName).equals(userPassword)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // THIS METHOD IS USED FOR MASKING PASSWORD
    public static String asterisk(String password)
    {
        if(password == null)
        {
            return null;
        }

        char[] passwordlength = new char[password.length()];
        for( int itr = 0; itr < passwordlength.length ; itr ++)
        {
            passwordlength[itr] = '*';
        }
        return new String(passwordlength);
    }
}