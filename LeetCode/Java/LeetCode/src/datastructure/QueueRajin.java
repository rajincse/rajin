package datastructure;

public class QueueRajin {
	public Node head;
	public Node tail;
	public QueueRajin()
	{
		head = null;
		tail = null;
	}
	
	public void enqueue(String name)
	{
		this.tail = new Node(name);
		
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
	public Node dequeue()
	{
		Node node = this.head;
		if(this.head!= null)
		{
			this.head = this.head.next;
		}
		
		return node;
	}
	
	public static void main(String[] args)
	{
		QueueRajin queue = new QueueRajin();
		queue.enqueue("Asad");
		queue.enqueue("Mary");
		queue.enqueue("Tania");
		
		Node node = queue.dequeue();
		while(node != null)
		{
			System.out.println(node.name);
			node = queue.dequeue();
		}
	}
}
