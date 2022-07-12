import java.io.*; 
import java.util.*; 


class Node {
	Node parentid;
	Vector<Node> child = new Vector<Node>();  
	Vector<Integer> childid=new Vector<Integer>();
	int id;
	int level=0 ;
	
	public Node(int ids){
		id=ids;
		level=1;
		
	}
	public Node(int ids,Node p){
		parentid=p;
		id=ids;
		level=parentid.level+1;
	}
  
}




class AVLNode{
	AVLNode left, right;
    int h;
    Node key;

    public AVLNode()
    {
        left = null;
        right = null;
        key = null;
        h = 1;
    }
    
    public AVLNode(Node n)
    {
        left = null;
        right = null;
        key = n;
        h = 1;
    } 
}

class MakeAVLTree{
	 AVLNode avlroot;     
	 
     
	 int height(AVLNode N) 
	    { 
	        if (N == null) 
	            return 0; 
	        return N.h; 
	    } 
	  
	    
	    int max(int a, int b) 
	    { 
	        return (a > b) ? a : b; 
	    } 
	  
	    
	    AVLNode rightRotate(AVLNode y) 
	    { 
	        AVLNode x = y.left; 
	        AVLNode T2 = x.right; 
	  
	       
	        x.right = y; 
	        y.left = T2; 
	  
	        
	        y.h = max(height(y.left), height(y.right)) + 1; 
	        x.h = max(height(x.left), height(x.right)) + 1; 
	  
	        
	        return x; 
	    } 
	  
	     
	    AVLNode leftRotate(AVLNode x) 
	    { 
	        AVLNode y = x.right; 
	        AVLNode T2 = y.left; 
	  
	        
	        y.left = x; 
	        x.right = T2; 
	  
	        
	        x.h = max(height(x.left), height(x.right)) + 1; 
	        y.h = max(height(y.left), height(y.right)) + 1; 
	  
	       
	        return y; 
	    } 
	  
	    
	    int getBalance(AVLNode N) 
	    { 
	        if (N == null) 
	            return 0; 
	        
	        return height(N.left) - height(N.right); 
	    } 
	    
	  
	    AVLNode insert(AVLNode node, Node key) 
	    { 
	        
	        if (node == null) {
	        	
	            return (new AVLNode(key));} 
	  
	        if (key.id < node.key.id) 
	            node.left = insert(node.left, key); 
	        else if (key.id > node.key.id) 
	            node.right = insert(node.right, key); 
	        else 
	            return node; 
	  
	        
	        node.h = 1 + max(height(node.left), 
	                            height(node.right)); 
	  
	        
	        int balance = getBalance(node); 
	  
	       
	        if (balance > 1 && key.id < node.left.key.id) 
	            return rightRotate(node); 
	  
	        
	        if (balance < -1 && key.id > node.right.key.id) 
	            return leftRotate(node); 
	  
	        
	        if (balance > 1 && key.id > node.left.key.id) 
	        { 
	            node.left = leftRotate(node.left); 
	            return rightRotate(node); 
	        } 
	  
	        	        if (balance < -1 && key.id < node.right.key.id) 
	        { 
	            node.right = rightRotate(node.right); 
	            return leftRotate(node); 
	        } 
	  
	        
	        return node; 
	    } 
	  
	   
	    AVLNode minValueNode(AVLNode node) 
	    { 
	        AVLNode current = node; 
	  
	       
	        while (current.left != null) 
	        current = current.left; 
	  
	        return current; 
	    } 
	  
	    AVLNode deleteNode(AVLNode root, int key) 
	    { 
	       
	        if (root == null) 
	            return root; 
	  
	        
	        if (key < root.key.id) 
	            root.left = deleteNode(root.left, key); 
	  
	     
	        else if (key > root.key.id) 
	            root.right = deleteNode(root.right, key); 
	  
	        
	        else
	        { 
	  
	            
	            if ((root.left == null) || (root.right == null)) 
	            { 
	                AVLNode temp = null; 
	                if (temp == root.left) 
	                    temp = root.right; 
	                else
	                    temp = root.left; 
	  
	             
	                if (temp == null) 
	                { 
	                    temp = root; 
	                    root = null; 
	                } 
	                else 
	                    root = temp; 
	            } 
	            else
	            { 
	  
	                
	                AVLNode temp = minValueNode(root.right); 
	  
	                
	                root.key = temp.key; 
	  
	                
	                root.right = deleteNode(root.right, temp.key.id); 
	            } 
	        } 
	  
	        
	        if (root == null) 
	            return root; 
	  
	        
	        root.h = max(height(root.left), height(root.right)) + 1; 
	  
	        
	        int balance = getBalance(root); 
	  
	        
	        if (balance > 1 && getBalance(root.left) >= 0) 
	            return rightRotate(root); 
	  
	        
	        if (balance > 1 && getBalance(root.left) < 0) 
	        { 
	            root.left = leftRotate(root.left); 
	            return rightRotate(root); 
	        } 
	  
	        
	        if (balance < -1 && getBalance(root.right) <= 0) 
	            return leftRotate(root); 
	  
	        
	        if (balance < -1 && getBalance(root.right) > 0) 
	        { 
	            root.right = rightRotate(root.right); 
	            return leftRotate(root); 
	        } 
	  
	        return root; 
	    } 
	  
	   
	    void preOrder(AVLNode node) 
	    { 
	        if (node != null) 
	        { 
	            System.out.print(node.key.id + " "); 
	            preOrder(node.left); 
	            preOrder(node.right); 
	        } 
	    } 
	    
	    public AVLNode search(int key,AVLNode root)
	    {
	        
	        if (root==null || root.key.id==key)
	            return root;
	     
	        
	        if (root.key.id < key)
	           return search(key,root.right);
	     
	        
	        return search(key,root.left);
	    }
   
}  
     
     
     











public class OrgHierarchy implements OrgHierarchyInterface{


Node root;
MakeAVLTree avltree=new MakeAVLTree();
int size=0;


public boolean isEmpty(){
	if(size==0) {
		return true;
	}
	return false;
	//your implementation
	 //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
} 

public int size(){
	return size;
	//your implementation
	 //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

public int level(int id) throws IllegalIDException, EmptyTreeException{
	
	
	
	if(root==null) {
		throw new EmptyTreeException(null);
	}
	AVLNode av=avltree.search(id,avltree.avlroot);
	if(av==null) {
		throw new IllegalIDException(null);
	}
	//System.out.println(av.key.id+" "+av.key.parentid.id+" "+av.key.level);
	return av.key.level;
	//your implementation
	 //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
} 

public void hireOwner(int id) throws NotEmptyException{
	//your implementation
	if(size>=1) {
		throw new NotEmptyException(null);
	}
	root=new Node(id);
	avltree.avlroot=avltree.insert(avltree.avlroot,root);
	size=1;
	root.level=1;
	
	// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

public void hireEmployee(int id, int bossid) throws IllegalIDException, EmptyTreeException{
	
	//avltree.preOrder(avltree.avlroot);
	//System.out.println("lawda  "+avltree.avlroot.key.id);
	if(root==null) {
		throw new EmptyTreeException(null);
	}
	AVLNode av=avltree.search(bossid,avltree.avlroot);
	if(av==null) {
		throw new IllegalIDException(null);
	}
	Node node=new Node(id,av.key);
	av.key.child.add(node);
	av.key.childid.add(id);
	
	avltree.avlroot=avltree.insert(avltree.avlroot,node);
	size+=1;
	node.level=av.key.level+1;
	
	
	//your implementation
	 //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
} 

public void fireEmployee(int id) throws IllegalIDException,EmptyTreeException{
	//avltree.preOrder(avltree.avlroot);
	if(root==null) {
		throw new EmptyTreeException(null);
	}
	AVLNode av=avltree.search(id,avltree.avlroot);
	if(av==null) {
		throw new IllegalIDException(null);
	}
	Vector<Node> v=av.key.parentid.child;
	
	//System.out.println(av.key.parentid.parentid.child+" jhkguygkuyfjgfv  ");
	
	for(int i=0;i<v.size();i++) {
		
		if(v.elementAt(i).id==id) {
			
			av.key.parentid.child.remove(i);
			av.key.parentid.childid.remove(i);
			break;
		}
	}
	avltree.avlroot=avltree.deleteNode(avltree.avlroot,id);
	size-=1;
	//System.out.println(av.key.parentid.parentid.id+" jhkguygkuyfjgfv  ");
	//your implementation
 	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}
public void fireEmployee(int id, int manageid) throws IllegalIDException,EmptyTreeException{
	//avltree.preOrder(avltree.avlroot);
	if(root==null) {
		throw new EmptyTreeException(null);
	}
	AVLNode av=avltree.search(id,avltree.avlroot);
	if(av==null) {
		throw new IllegalIDException(null);
	}
	AVLNode mid=avltree.search(manageid,avltree.avlroot);
	if(mid==null) {
		throw new IllegalIDException(null);
	}
	mid.key.child.addAll(av.key.child);
	mid.key.childid.addAll(av.key.childid);
	Vector<Node> v=av.key.parentid.child;
	
	for(int i=0;i<av.key.child.size();i++) {
		av.key.child.elementAt(i).parentid=mid.key;
	}
	
	for(int i=0;i<v.size();i++) {
		if(v.elementAt(i).id==id) {
			
			av.key.parentid.child.remove(i);
			av.key.parentid.childid.remove(i);
			
			
			break;
		}
	}
	avltree.avlroot=avltree.deleteNode(avltree.avlroot,id);
	size-=1;
	//your implementation
	 //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
} 

public int boss(int id) throws IllegalIDException,EmptyTreeException{
	if(root==null) {
		throw new EmptyTreeException(null);
	}
	if(root.id==id) {
		return -1;
	}
	AVLNode av=avltree.search(id,avltree.avlroot);
	if(av==null) {
		throw new IllegalIDException(null);
	}
	
	return av.key.parentid.id;
	//your implementation
	 //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}


private Node LCB(Node n1,Node n2) {
	if(n1.parentid.id==n2.parentid.id) {
		return n1.parentid;
	}else {
	
	LCB(n1.parentid,n2.parentid);}
	return null;
}

public int lowestCommonBoss(int id1, int id2) throws IllegalIDException,EmptyTreeException{
	if(root==null) {
		throw new EmptyTreeException(null);
	}
	AVLNode n1=avltree.search(id1,avltree.avlroot);
	if(n1==null) {
		throw new IllegalIDException(null);
	}
	AVLNode n2=avltree.search(id2,avltree.avlroot);
	if(n2==null) {
		throw new IllegalIDException(null);
	}
	Node node1=n1.key;
	Node node2=n2.key;
	int l1=node1.level;
	int l2=node2.level;
	if(l1>l2) {
		while(l1-l2!=0) {
			node1=node1.parentid;
			l1--;
		}
	}else {
		while(l2-l1!=0) {
			node2=node2.parentid;
			l2--;
		}
	}
	return LCB(node1,node2).id;
	
	//your implementation
	// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}



public String toString(int id) throws IllegalIDException, EmptyTreeException{
	//avltree.preOrder(avltree.avlroot);
	if(root==null) {
		throw new EmptyTreeException(null);
	}
	AVLNode n1=avltree.search(id,avltree.avlroot);
	if(n1==null) {
		throw new IllegalIDException(null);
	}
	Vector<Node> vec=n1.key.child;
	Vector<Integer> vec11=n1.key.childid;
	Collections.sort(vec11);
	String s = Integer.toString(n1.key.id);
	

	while(vec.size()!=0) {
	//System.out.println(" "+vec11);
	for(int i=0;i<vec11.size();i++) {
		if(i==0) {
		//System.out.println(i+" "+vec11.size());
		s=s+","+Integer.toString(vec11.elementAt(i))+" ";}
		else if(i==vec.size()-1) {
			s=s+Integer.toString(vec11.elementAt(i));
		}else {
			s=s+Integer.toString(vec11.elementAt(i))+" ";
		}
	}
	
	Vector<Integer> nv1=new Vector<Integer>();
	//System.out.println(n1.key.childid+" jhkgjub");
	Vector<Node> nv=new Vector<Node>();
	for(int i=0;i<vec.size();i++) {
		nv.addAll(vec.elementAt(i).child);
		nv1.addAll(vec.elementAt(i).childid);
	}

	vec=nv;
	vec11=nv1;
	Collections.sort(vec11);
	}
	return s;
	
	
	//your implementation
	 //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}


}