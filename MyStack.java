

class EmptyStackException extends Exception
{
    public  EmptyStackException (String s)
    {
        super(s);
    }
} 
public class MyStack {

 Object arr[]=new Object[1];
 public int top;
 public MyStack(){
      top=-1;
 }
 public void push(Object k)
 {   
     if( k!=null)
     {
        
     
      if(top==arr.length-1)
     {
        Object a[]= new Object[2*arr.length];
        
        for(int i=0;i<arr.length;i++)
        {
            a[i]=arr[i];
        }
        arr=a;
        top++;
        a[top]=k;
     }
     else
     {
         top++;
         arr[top]=k;
     }
    }
}
    public Object pop() throws EmptyStackException
    {
      if(isEmpty())
      {
        throw  new EmptyStackException("stack is empty");
      }
      else{
          return arr[top--];
          
      }
      
    }
    public Object top() throws EmptyStackException
    {
        if(isEmpty())
      {
        throw  new EmptyStackException("stack is empty");
       
      }
      else{
          return arr[top];
          
      }
      
    }

    public boolean isEmpty()
    {
        return(top==-1);
    }
    public String toString()
    {
    if(isEmpty()  )
     {
         return "[]";
     }
    
    

     else
     {   
        String str = "[";
		for(int i=arr.length-1; i>=0; i--) {
			Object obj = arr[i];
			if(i==0) {
				str+= obj;
			} else {
				str+=obj + ","+" ";
			}
		}
		str+="]";
		return str;
    
        
     }
    }
    
    
    

    
}


    

