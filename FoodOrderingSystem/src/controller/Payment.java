package controller;

import model.Logout;
import java.util.Scanner;
import java.io.Console;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Payment 
{
    Scanner scanner = new Scanner(System.in);
    Logout logoutfood = new Logout();
    Console console = System.console();
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    int pay_number;
    String creditCard_number;
    String creditPin;

    public void payment(int total)
    {
        System.out.println();
        System.out.println("**********************Payment**********************");
        System.out.println();
        System.out.println("Press 1 for UPI method to pay");
        System.out.println("Press 2 for Cash on Delivery");
        pay_number = scanner.nextInt();
        System.out.println();
        if(pay_number==1)
        {
            System.out.println("Enter your 16 digit Credit Card Number");
            char[] charater = console.readPassword();
            String creditCard_number = String.valueOf(charater);
            System.out.println(asterisk(creditCard_number));
            System.out.println();
            System.out.println("Enter your PIN number");
            char[] character_pin = console.readPassword();
            String creditPin = String.valueOf(character_pin);
            System.out.println(asterisk(creditPin));
            System.out.println();
            if(isValidCreditDetails(creditCard_number,creditPin))
            {
                System.out.println("Rupee of "+total+" Paid Successfully at ");
                System.out.print(dateTimeFormat.format(now)); 
                System.out.println();
                logoutfood.logout();
            }
            else{
                System.out.println("Invalid Credit Card Number or Credit Card Password");
                payment(total);
            }
        }
        else if(pay_number==2)
        {
            System.out.println("You have chosen Cash on Delivery");
            System.out.println("Keep the correct Change");
            System.out.println("Your order will delivery within 1 hr");
            logoutfood.logout();
        }
        else
        {
            System.out.println("Inavlid Number");
            payment(total);
        }
    }

    public boolean isValidCreditDetails(String cred_number,String cred_pin)
    {
        if(cred_number.charAt(0)!=0 && cred_number.length() == 16 && cred_number.matches("[0-9]+") && cred_pin.length() == 4 && cred_pin.matches("[0-9]+"))
        {
            return true;
        }
        else
        {
            return false;
        } 
    }

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
