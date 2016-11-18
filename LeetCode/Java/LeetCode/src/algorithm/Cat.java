package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Cat {
	public static void main(String [] args)
	{
		String input ="the cat in the hat";
		parseAndShow(input);
		
	}
	static class Entry implements Comparable<Entry>
	{
		public String key;
		public int occurrence;
		//constructor
		public Entry(String k, int o)
		{
			key =k;
			occurrence=o;
		}
		@Override
		public int compareTo(Entry o) {
			// TODO Auto-generated method stub
			return o.occurrence - this.occurrence;
		}
	}
	public static void parseAndShow(String input)
	{
		String[] split = input.split(" ");
		HashMap<String, Integer> occurrenceMap = new HashMap<>();
		
		for(int i=0;i<split.length;i++)
		{
			String key = split[i];
			if(!occurrenceMap.containsKey(key))
			{
				occurrenceMap.put(key, 1);
			}
			else
			{
				int value = occurrenceMap.get(key);
				value++;
				occurrenceMap.put(key, value);
			}			
		}
		ArrayList<Entry> sortList = new ArrayList<>();
		for(String key: occurrenceMap.keySet())
		{
			Entry en= new Entry(key, occurrenceMap.get(key).intValue());
			sortList.add(en);
		}
		Collections.sort(sortList);
		
		for(Entry e: sortList)
		{
			System.out.println(e.key+" "+e.occurrence);
		}
	}
}
