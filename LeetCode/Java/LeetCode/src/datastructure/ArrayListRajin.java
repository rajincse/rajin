package datastructure;

import java.util.ArrayList;

public class ArrayListRajin {

	public static void main(String []args)
	{
		String family[] = new String[]{"Tania","Shume", "Munia"};
		String jamais[] = new String[]{"Pulock","Rajin"};
		
		ArrayList<String> ferdousFamily = new ArrayList<String>();
		ferdousFamily.add("Asad");
		ferdousFamily.add("Mary");
		
		for(int i=0;i<family.length;i++)
		{
			ferdousFamily.add(family[i]);
		}
		
		for(String jamai:jamais)
		{
			ferdousFamily.add(jamai);
		}
		
		for(String member:ferdousFamily)
		{
			System.out.println(member);
		}
			
	}
}
