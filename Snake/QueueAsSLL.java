public class QueueAsSLL<T extends Comparable<? super T>>
{   
	private SLL<T> theQueue;
	
    public QueueAsSLL()
    {  
		theQueue = new SLL<T>();       
    }
	
    public boolean enqueue(T newElement)
    {  
		   return theQueue.append(newElement);
	}
	
	public T dequeue()
    {  
		T temp = null;
		temp = (T)theQueue.deleteHead();
		return temp;
    }
	public T deleteTail()
	{
		T temp=null;
		temp=(T)theQueue.deleteTail();
		return temp;
	}
	public String toString()
	{
		return theQueue.toString();
	}

	 public boolean isMember(T item)
   {
	   return theQueue.isMember(item);
   }
   
}