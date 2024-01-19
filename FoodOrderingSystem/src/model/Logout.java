package model;
import java.util.Scanner;
public class Logout 
{
    Scanner scanner = new Scanner(System.in);
    public void logout()
    {
        
        String yesOrNo;
        System.out.println();
        System.out.println("Are you Sure you want to logout");
        System.out.println("If Yes enter 'Y' or 'y'");
        yesOrNo = scanner.next();
        if(yesOrNo.equalsIgnoreCase("Y"))
        {
            System.out.println();
            System.out.println("Thank You for Ordering!!!");
            System.exit(1);
        } 
        else
        {
            System.out.println("Invalid Input. Try again !!!");
            logout();
        }
    }
}
