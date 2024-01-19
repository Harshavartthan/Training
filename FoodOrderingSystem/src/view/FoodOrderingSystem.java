/*
    Title: ONLINE FOOD ORDERING SYSTEM
    Author: HARSHAVARTTHAN
    Created at: 06/07/2023
    Updated at: 31/07/2023
    Reviewed by: SILPA MADHUSOODANAN
    Reviewed at: 12/07/2023
*/
//PACKAGE
package view;
// MAIN CLASS
public class FoodOrderingSystem {
    public static void main(String[]args)
    {
        Display displayFood = new Display();
        Login loginObject = new Login();

        displayFood.startDisplay();
        System.out.println();
        loginObject.login();
    }
}
