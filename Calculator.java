class InvalidPostfixException extends Exception
    {
        public InvalidPostfixException (String s)
        {
            super(s);
        }
    } 


    class InvalidExprException extends Exception
    {
        public InvalidExprException (String s)
        {
            super(s);
        }
    } 

public class Calculator {
  
    

 public int evaluatePostFix( String s)  throws InvalidPostfixException 
    {
    MyStack Cstack = new MyStack();
        for(int i=0;i<s.length();i++){
        
        if(s.charAt(i)==' ')
        continue;
        
        else if(s.charAt(i)=='+' || s.charAt(i)=='-' || s.charAt(i)== '*') {
             
            int a;
            int b;
        try {
            a=(int)Cstack.pop();
            b=(int)Cstack.pop();}
          
        catch(EmptyStackException e)
        {
            throw new InvalidPostfixException("");
        }
        
            switch (s.charAt(i)) {

            case '+':
            Cstack.push(a+ b);
            break;
            case '-':
            Cstack.push(b-a);
            
            break;
            case '*':
            Cstack.push(a*b);
            break;
            
            
            }
        }
    
        else  
        { int j=i;
             String k="";
            while(j<s.length()&& s.charAt(j)!=' '){
                k= k+s.charAt(j);
                j++;
            
            }
            i=i+k.length()-1;
            int m=Integer.parseInt(k);
            Cstack.push(m);
        }
    }   

    
try {
    return (int)Cstack.top();
}
 catch (EmptyStackException e) {
    throw new InvalidPostfixException("");
}
   }   

   


   public String convertExpression ( String s) throws InvalidExprException
     {try{
         MyStack Newstc  = new MyStack();
         String str= "";
    for(int i=0;i<s.length();i++)
    {
         if(s.charAt(i)==' '){
         continue; }

         else if(s.charAt(i)>47 && s.charAt(i)<58)
         { int j=i;
            String k="";
            
           while(j<s.length()&& s.charAt(j)!=' ' &&  s.charAt(j)>47 && s.charAt(j)<58  )

           {
               k= k+s.charAt(j);
               j++;
           
           }
           i=i+k.length()-1;
           str=str+k + " ";
           
           
         }
        
          else if(s.charAt(i)==')')
         {    
            if( s.charAt(i-2)=='-' && s.charAt(i-1)>47 && s.charAt(i-1)<58 && s.charAt(i-3)=='(' )
            {
             throw new InvalidExprException("");
            }
             while((char)Newstc.top()!='(')
             {   char m =(char)Newstc.pop();
                 str=str+m +" ";
                 
             }
             Newstc.pop();
             
        }  
        else if(s.charAt(i)=='('|| s.charAt(i)=='+' || s.charAt(i)== '-' || s.charAt(i)=='*')
        {  
            if(s.charAt(i)=='(') 
           {
            Newstc.push(s.charAt(i));
            
           }

           else {
                  while(!Newstc.isEmpty() && ((s.charAt(i)==(char)Newstc.top)  || (s.charAt(i)=='+'&& (char)Newstc.top=='*') || (s.charAt(i)=='+'&& (char)Newstc.top == '+' )  || (s.charAt(i)=='-'&& (char)Newstc.top=='+') ||(s.charAt(i)=='-'&& (char)Newstc.top=='*'))) 
                  
                   {
                       str =str + Newstc.pop() + " ";

                   }
                   ;
                   if(str!=""){
               Newstc.push(s.charAt(i));
             }
               else{
                   throw new InvalidExprException ("");
               }

           }
           

        }  
        
         
         else {
             throw new  InvalidExprException("");
         
        }
    }while(!Newstc.isEmpty())
     { if((char)Newstc.top()=='('){
        throw new InvalidExprException ("");
        }
         else {
           
    
         str =str + Newstc.pop() +" " ;
        
         }
      }
    
     return str.substring(0,str.length()-1);
    }

    catch (EmptyStackException e)
    {
       throw new InvalidExprException("");
    }

}}

