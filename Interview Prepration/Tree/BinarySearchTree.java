package GeeksForGeeks.Tree;

class treeNodeBST{
	int key;
	treeNodeBST left, right;
	
	public treeNodeBST(int item){
		key = item;
		left = null;
		right = null;
	}
}

public class BinarySearchTree {
	treeNodeBST root = null;
	
	public BinarySearchTree(){
		root = null;
	}
	
	public BinarySearchTree(int item){
		root.key = item;
	}
	
//Inorder traversal in BST
	public void inorder(treeNodeBST node){
		if(node == null){
			return;
		}
		inorder(node.left);
		System.out.print(node.key + " ");
		inorder(node.right);
	}
	
//Reverse Inorder to find kth element from last
	public void reverseInorder(treeNodeBST node, int k, int count){
		if (node == null || count >= k) {
			return;
		}
		count++;
		reverseInorder(node.right, k, count);
		if (count == k) {
			System.out.print(node.key + " ");
			return;
		}
		reverseInorder(node.left, k, count);
	}

//Function to insert node in BST
	public void insert(int item){
		root = insertRecursion(root, item);
	}
	
	public treeNodeBST insertRecursion(treeNodeBST node, int item) {
		if (node == null) {
			node = new treeNodeBST(item);
			return node;
		}
		if (node.key > item) {
			node.left = insertRecursion(node.left, item);
		} else {
			node.right = insertRecursion(node.right, item);
		}
		return node;
	}

//Function to delete node in BST	
	public void delete(int item){
		root = deleteKey(root, item);
	}
	
	public treeNodeBST deleteKey(treeNodeBST node, int item){
		if(node == null){
			return node;
		}
		if(node.key > item){
			root.left = deleteKey(node.left, item);
		} 
		else if(node.key < item) {
			root.right = deleteKey(node.right, item);
		}
		else{
			// Single child
			if(node.left == null){
				return node.right;
			} 
			else if(node.right == null){
				return node.left;
			}
			// Two child, Finding Inorder successor. smallest in the right subtree
//			node.key = minValue(node.right);
			node = node.right;
			int minValue = node.key;
			while(node.left != null){
				minValue = node.left.key;
				node = node.left;
			}
			node.key = minValue;
			node.right = deleteKey(node.right, node.key);
		}
		return node;
	}
	
	/*public int minValue(Node node){
		int minValue = node.key;
		while(node.left != null){
			minValue = node.left.key;
			node = node.left;
		}
		return minValue;
	}*/
	
//Function to search node in a BST
	public void search(int item){
		searchTree(root, item);
	}
	public treeNodeBST searchTree(treeNodeBST node, int item){
		if(node == null || node.key == item){
			return node;
		}
		if(node.key > item){
			searchTree(node.left, item);
		}
		return searchTree(node.right, item);
	}

//LCA in BST
	public void LCA(int n1, int n2){
		LCAUtil(root, n1, n2);
	}
	
	public treeNodeBST LCAUtil(treeNodeBST node, int n1, int n2){
		if(node == null){
			return null;
		}
		else if(node.key > n1 && node.key > n2){
			return LCAUtil(node.left, n1, n2);
		}
		else if(node.key < n1 && node.key < n2){
			return LCAUtil(node.right, n1, n2);
		}
		System.out.println(node.key);
		return node;
	}

//Main Function
	public static void main(String[] args) {
	BinarySearchTree tree = new BinarySearchTree();
	tree.insert(1);
	tree.insert(2);
	tree.insert(3);
	tree.insert(4);
	tree.insert(5);
	tree.insert(6);
	tree.insert(7);
	tree.insert(8);
	
	System.out.println("Inorder: ");
	tree.inorder(tree.root);
	System.out.println("\nSearch in BST: ");
	System.out.println("Deletion: ");
	tree.delete(2);
	System.out.println("After Deletion: ");
	tree.inorder(tree.root);
	System.out.println("\nLowest Common Ancestor");
	tree.LCA(1, 4);
	System.out.println("Reverse Inorder: ");
	tree.reverseInorder(tree.root, 3, 0);
	}
}
