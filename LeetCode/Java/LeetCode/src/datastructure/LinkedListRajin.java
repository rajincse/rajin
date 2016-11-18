package datastructure;

public class LinkedListRajin {

	public Node head;
	public LinkedListRajin()
	{
		this.head = null;
	}
	
	public void addElementToHead(String name)
	{
		Node node = new Node(name);
		node.next = head;
		this.head = node;
	}
	
	public void addElementToTail(String name)
	{
		Node tail = new Node(name);
		
		if(this.head == null)
		{
			this.head = tail;
		}
		else
		{
			Node node = this.head;
			while(node.next!= null)
			{
				node = node.next;
			}
			node.next = tail;
		}
	}
	
	public void delete(String name)
	{
		Node node = this.head;
		while(node!= null && node.next != null)
		{
			if(node.next.name.equals(name))
			{
				node.next = node.next.next;
			}
			node = node.next;
		}
	}
	
	public String toString()
	{
		String msg="";
		
		Node node = this.head;
		while(node != null)
		{
			msg+=node.name+"->";
			node = node.next;
		}
		return msg;
	}
	
	
	public static void main(String[] args)
	{
		LinkedListRajin list = new LinkedListRajin();
		
		
		list.addElementToTail("Asad");
		list.addElementToTail("Mary");
		list.addElementToTail("Tania");
		list.addElementToTail("Shume");
		list.addElementToTail("Rajin");
		list.addElementToTail("Munia");
		list.addElementToTail("Pulock");
		
		list.delete("Rajin");
		
		
		
		System.out.println(list.toString());
	}
}

class Node
{
	public String name;
	public Node next;
	public Node(String name)
	{
		this.name = name;
	}
}
