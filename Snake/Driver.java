
public class Driver
{
	public static void main(String [] args)
	{
		Integer head = null;
		QueueAsSLL<Integer> myQueue = new QueueAsSLL<Integer>();
		
		System.out.println("\nInitial list: ");
		System.out.println(myQueue +"\n");
		
		System.out.println("\nAdd One item: ");// Add item to Empty list
		myQueue.enqueue(new Integer(3));
		System.out.println(myQueue +"\n");
	 
		System.out.println("\nAdd a few more:");
		myQueue.enqueue(new Integer(2));
		myQueue.enqueue(new Integer(9));
		myQueue.enqueue(new Integer(5));
		System.out.println(myQueue +"\n");
		
		System.out.println("\ndequeue one");
		head = (Integer) myQueue.dequeue();
		
		if (head != null)
			System.out.println("Head is: " + head.intValue() +"\n");
		else
			System.out.println("Empty Queue");
		
		System.out.println(myQueue +"\n");
	 
		System.out.println("\ndequeue one");
		head = (Integer) myQueue.dequeue();
		
		if (head != null)
			System.out.println("Head is: " + head.intValue() +"\n");
		else
			System.out.println("Empty Queue");
		
		System.out.println(myQueue +"\n");
	 
		System.out.println("\ndequeue one");
		head = (Integer) myQueue.dequeue();
		
		if (head != null)
			System.out.println("Head is: " + head.intValue() +"\n");
		 else
			System.out.println("Empty Queue");
		
		System.out.println(myQueue +"\n");
		 
		System.out.println("\ndequeue one");
		head = (Integer) myQueue.dequeue();
		
		if (head != null)
			System.out.println("Head is: " + head.intValue() +"\n");
		else
			System.out.println("Empty Queue");
		
		System.out.println(myQueue +"\n");
		 
		System.out.println("\ndequeue one");
		head = (Integer) myQueue.dequeue();
		
		if (head != null)
			System.out.println("Head is: " + head.intValue() +"\n");
		else
			System.out.println("Empty Queue");
		
		System.out.println(myQueue +"\n");
	}
}