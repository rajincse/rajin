package datastructure;

import java.util.HashMap;

public class HashTableRajin {

	public static void main(String args[])
	{
		Student ata = new Student();
		ata.pantherId =1234567;
		ata.name="Ata";
		ata.major="CS";
		
		Student mamun = new Student();
		mamun.pantherId = 8092180;
		mamun.name="Mamun";
		mamun.major="CE";
		
		
		Student[] studentArray = new Student[]{ata, mamun};
		HashMap<Integer, Student> map = new HashMap<Integer, Student>();
		map.put(ata.pantherId, ata);
		map.put(mamun.pantherId, mamun);
		
		for(Integer id:map.keySet())
		{
			Student s = map.get(id);
			System.out.println("["+id+"]=>"+s.name);
		}
	}
	
}

class Student
{
	public int pantherId;
	public String name;
	public String major;
}