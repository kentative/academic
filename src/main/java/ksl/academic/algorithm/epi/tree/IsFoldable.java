package ksl.academic.algorithm.epi.tree;

public class IsFoldable {

	/* Returns true if the given tree can be folded */
    static boolean isFoldable(Node node)  
    { 
        if (node == null) 
            return true; 
   
        return IsFoldableUtil(node.left, node.right); 
    } 
   
    /* A utility function that checks if trees with roots as n1 and n2 
     are mirror of each other */
    static boolean IsFoldableUtil(Node n1, Node n2)  
    { 
          
        /* If both left and right subtrees are NULL, 
         then return true */
        if (n1 == null && n2 == null) 
            return true; 
   
        /* If one of the trees is NULL and other is not, 
         then return false */
        if (n1 == null || n2 == null) 
            return false; 
   
        /* Otherwise check if left and right subtrees are mirrors of 
         their counterparts */
        return IsFoldableUtil(n1.left, n2.right) 
                && IsFoldableUtil(n1.right, n2.left); 
    } 
   
    /* Driver program to test above functions */
    public static void main(String args[]) { 
    	Node root = new Node(26);
		add(root, 17, 41);
		add(root.left, 14, 21);
		add(root.right, 30, 47);
		
   
        if (isFoldable(root)) 
            System.out.println("tree is foldable"); 
        else
            System.out.println("Tree is not foldable"); 
   
    } 
    
    private static void add(Node node, int left, int right) {
		node.left = new Node(left);
		node.right = new Node(right);
	}
}
