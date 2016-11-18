package datastructure;

public class StackRajin extends LinkedListRajin{

	public Node getTop()
	{
		return this.head;
	}
	public Node pop()
	{
		Node node = this.head;
		if(this.head != null)
		{
			this.head = this.head.next;
		}
		
		return node;
	}
	
	public void push(String name)
	{
		this.addElementToHead(name);
	}
	public static void main(String[] args)
	{
		StackRajin stack = new StackRajin();
		stack.push("Asad");
		stack.push("Mary");
		stack.push("Tania");
		stack.push("Shume");
		stack.push("Munia");
		
		Node node = stack.pop();
		while(node != null)
		{
			System.out.println(node.name);
			node = stack.pop();
		}
		
		 
	}
}
