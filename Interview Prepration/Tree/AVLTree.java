package GeeksForGeeks.Tree;

class treeNode{
	int key, height;
	treeNode left, right;
	
	public treeNode(int item){
		key = item;
		height = 1;
		left = null;
		right = null;
	}
}

public class AVLTree {
	
	treeNode root = null;
	
	public int height(treeNode node){
		if(node == null){
			return 0;
		}
		return node.height;
	}
	
	public int max(int x, int y){
		return x > y? x : y;
	}
	
	public int getBalance(treeNode node){
		if(node == null){
			return 0;
		}
		return height(node.left) - height(node.right);
	}
	
	public treeNode rotateRight(treeNode node){
		treeNode x = node.left;
		treeNode y = x.right;
		
		node.left = y;
		x.right = node;
		
		x.height = max(height(x.left), height(x.right)) + 1;
		node.height = max(height(node.left), height(node.right)) + 1;
		
		return x;
	}
	
	public treeNode rotateLeft(treeNode node){
		treeNode x = node.right;
		treeNode y = x.left;
		
		node.right = y;
		x.left = node;
		
		x.height = max(height(x.left), height(x.right)) + 1;
		node.height = max(height(node.left), height(node.right)) + 1;
		
		return x;
	}

	
	public treeNode insert(treeNode node, int key) {
		//Inserting as regular BST
		if (node == null) {
			node = new treeNode(key);
			return node;
		}
		if (node.key > key) {
			node.left = insert(node.left, key);
		} else if (node.key < key) {
			node.right = insert(node.right, key);
		} else {
			return node;
		}
		
		//Updating the height of ancestor
		node.height = 1 + max(height(node.left), height(node.right));
		
		//Balancing the node
		int bal = getBalance(node);
		
		//Identification of the case
		//Left-Left Case
		if(bal > 1 && key < node.left.key){
			return rotateRight(node);
		}
		
		//Right-Right Case
		if(bal < 1 && key > node.right.key){
			return rotateLeft(node);
		}
		
		//Left-Right Case
		if(bal > 1 && key > node.left.key){
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}

		//Right-Left Case
		if(bal < 1 && key > node.right.key){
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}
		
		return node;
	}
	
	public treeNode delete(treeNode node, int key){
		//Regular BST Delete
		if(node == null){
			return node;
		}
		if(node.key > key){
			root.left = delete(node.left, key);
		}
		else if(node.key < key){
			root.right =  delete(node.right, key);
		}
		else{
			//Single Child
			if(node.left == null){
				return node.right;
			}
			else if(node.right == null){
				return node.left;
			}
			// Two Child
			node = node.right;
			int minValue = node.key;
			while (node.left != null) {
				minValue = node.left.key;
				node = node.left;
			}
			node.key = minValue;
			node.right = delete(node.right, node.key);
		}
		
		node.height = 1 + max(height(node.right), height(node.left));
		int balance = getBalance(node);
		
		if(balance > 1 && key < node.left.key){
			return rotateRight(node);
		}
		
		if(balance < 1 && key > node.right.key){
			return rotateLeft(node);
		}
		
		if(balance > 1 && key > node.left.key){
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}

		if(balance < 1 && key > node.right.key){
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}
		
		return node;
	}
	
	public void preorder(treeNode node){
		if(node == null){
			return;
		}	
		System.out.print(node.key + " ");
		preorder(node.left);
		preorder(node.right);
	}
	
	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		tree.root = tree.insert(tree.root, 8);
		tree.root = tree.insert(tree.root, 5);
		tree.root = tree.insert(tree.root, 4);
		tree.root = tree.insert(tree.root, 7);
		tree.root = tree.insert(tree.root, 11);
		tree.root = tree.insert(tree.root, 2);
		
		System.out.println("Preorder for AVL: ");
		tree.preorder(tree.root);
		
		tree.delete(tree.root, 5);
		
		System.out.println("\nPreorder for AVL after deletion: ");
		tree.preorder(tree.root);
		
	}
}
