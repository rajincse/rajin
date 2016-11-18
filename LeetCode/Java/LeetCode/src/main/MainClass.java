package main;

import javax.swing.JOptionPane;

public class MainClass {
	public static void main(String[] args)
	{
		String s1=JOptionPane.showInputDialog("Enter the purchase amount");
    	String s2=JOptionPane.showInputDialog("Enter the amount given");
    	double purchaseAmount= Double.parseDouble(s1);
    	double amountGiven= Double.parseDouble(s2);
    	double change = purchaseAmount - amountGiven;
    	JOptionPane.showMessageDialog(null, change);

        String result = "Change consists of: ";
        
        int changeInPennies = (int)(change * 100);
        int twenties= changeInPennies / 2000;
        result= result + twenties + " twenty dollar bills";
        changeInPennies = changeInPennies %2000;
        
        int tens = changeInPennies / 1000;
        result= result + tens + " ten dollar bills";
        
        JOptionPane.showMessageDialog(null, result);



	}

}
