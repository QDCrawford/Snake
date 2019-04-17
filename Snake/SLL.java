public class SLL<T extends Comparable<? super T>>
{   
	private Element<T> head;
	private Element<T> tail;
	
    public SLL()
    {  head = null;  
	   tail = null;        
    }
	
	public T getHead()
	{
		if (head == null)
			return null;
		else 
			return head.data;
	}
	
	public T deleteHead()
	{
		T deleted;
		
		if (head == null)
			return null;	   
		else
		{
			deleted = head.data;
			head = head.next;
			return deleted;
		}
	}
   
   public T deleteTail()
   {
		T deleted;
		
		if (head == null)
			return null;
		else if (head.next==null)
		{
			return null;
		}		   
		else
		{
			deleted = head.data;
			head = head.next;
			return deleted;
		}
	}
	
	public T getTail()
	{
		if (head == null)
			return null;
		else 
			return tail.data;
	}		

    public boolean prepend(T newElement) 
    {  
		Element<T> temp = new Element<T>(newElement);
		
        if(temp == null)
           return false;
        else
        {  
	       if (head==null)
		   {
			head = temp;
			tail = temp;
		   }
           else
           {
			 temp.next = head;
			 head = temp;
		   }
		}
        return true;
    }
	
	 public boolean append(T newElement)
    {  
		Element<T> temp = new Element<T>(newElement);
		
        if(temp == null)
           return false;
        else
        {  
	       if (head==null)
		   {
			head = temp;
			tail = temp;
		   }
           else
           {
			 tail.next = temp;
			 tail = temp;
		   }
		}
     return true;
    }
    
	public boolean delete(T item)
	{
		Element<T> ptr = head;
		Element<T> prevPtr = null;
	   
		while (ptr!= null&& ptr.data.compareTo(item)!= 0)
		{
			prevPtr=ptr;
			ptr=ptr.next;
		}
		if (ptr == null)
			return false;
		if (ptr==head)
			head= ptr.next;
		else
			prevPtr.next=ptr.next;
		if (ptr==tail)
			tail=prevPtr;
		return true;
	}
	
	public boolean isMember(T item)
	{
		Element<T> ptr = head;
	   
		if (head == null)
			return false;
		if (item == null)
			return false;
		while (ptr!= null)
		{
			if (ptr.data.compareTo(item)== 0)
				return true;
			   
			ptr=ptr.next;
		}
		return false;   
	}

	public String toString()
	{
		String s="";
		Element<T> ptr = head;
		
		while (ptr != null)
		{   
			s=s + ptr.data + " ";
			ptr = ptr.next;
		}
		return s;
	}
 
	public class Element<T extends Comparable<? super T>>
	{   
		private T data;
		private Element<T> next;
		
		public Element(T param)
		{
			data = param;
		}	
	}
}

