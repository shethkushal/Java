package GeeksForGeeks.Graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;



public class GraphTraversal {
	int V;
	LinkedList<Integer> adj[];
	
	public GraphTraversal(int v){
		V = v;
		adj = new LinkedList[V];
		for(int i = 0; i < V; i++){
			adj[i] = new LinkedList();
		}
	}
	
	public void addEdge(int u, int v){
		if(!adj[u].contains(v)){
			adj[u].add(v);
		}
	}
	
	public void printGraph(){
		for(int i = 0; i < adj.length; i++){
			System.out.println(i + "->" + adj[i]);
		}
	}
	
	public void BFS(int start){
		boolean[] visited = new boolean[V];
		Queue<Integer> queue = new LinkedList<Integer>();
		
		visited[start] = true;
		queue.add(start);
		
		while(!queue.isEmpty()){
			start = queue.poll();
			System.out.print(start + " ");
			Iterator<Integer> iter = adj[start].listIterator();
			while(iter.hasNext()){
				int n = iter.next();
				if(visited[n] != true){
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}
	
	public void DFS(int start){
		boolean[] visited = new boolean[V];
		Stack<Integer> stack = new Stack<Integer>();
		
		visited[start] = true;
		stack.push(start);
		
		while(!stack.isEmpty()){
			start = stack.pop();
			System.out.print(start + " ");
			Iterator<Integer> iter = adj[start].listIterator();
			while(iter.hasNext()){
				int n = iter.next();
				if(visited[n] != true){
					visited[n] = true;
					stack.push(n);
				}
			}
		}
	}
	
	public void DFSRecursion(int start){
		boolean visited[] = new boolean[V];
		DFSRecursionUtil(visited, start);
	}
	
	public void DFSRecursionUtil(boolean[] visited, int start) {
		visited[start] = true;
		System.out.print(start + " ");
		Iterator<Integer> iter = adj[start].listIterator();
		while (iter.hasNext()) {
			int n = iter.next();
			if (visited[n] != true) {
				DFSRecursionUtil(visited, n);
			}
		}
	}
		
	public boolean detectCycle() {
		boolean[] visited = new boolean[V];
		boolean[] stack = new boolean[V];

		for (int i = 0; i < V; i++) {
			if (detectCycleUtil(visited, stack, i)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean detectCycleUtil(boolean[] visited, boolean[] stack, int start) {
		if (visited[start] == false) {
			visited[start] = true;
			stack[start] = true;
			Iterator<Integer> iter = adj[start].listIterator();
			while (iter.hasNext()) {
				int n = iter.next();
				if (visited[n] == false && detectCycleUtil(visited, stack, n)) {
					return true;
				} else if (stack[n] == true) {
					return true;
				}
			}
		}
		stack[start] = false;
		return false;
	}
	
	public void topologicalSort() {
		Stack<Integer> stack = new Stack<Integer>();
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (visited[i] == false) {
				topologicalSortUtil(visited, stack, i);
			}
		}
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}

	public void topologicalSortUtil(boolean[] visited, Stack<Integer> stack, int start) {
		visited[start] = true;
		Iterator<Integer> iter = adj[start].listIterator();
		while (iter.hasNext()) {
			int n = iter.next();
			if (!visited[n]) {
				topologicalSortUtil(visited, stack, n);
			}
		}
		stack.push(new Integer(start));
	}
	
	public static void main(String[] args) {
		GraphTraversal graph = new GraphTraversal(6);
		graph.addEdge(0, 1);
		graph.addEdge(0, 5);
		graph.addEdge(1, 2);
		graph.addEdge(1, 4);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		graph.addEdge(5, 1);

		System.out.println("Printing adjacency list: ");
		graph.printGraph();
		System.out.println("BFS Traversal: ");
		graph.BFS(2);
		System.out.println("\nDFS Traversal: ");
		graph.DFS(2);
		System.out.println("\nDFS Recursion Traversal: ");
		graph.DFSRecursion(2);
		System.out.println("\nTopological Sort: ");
		graph.topologicalSort();
		System.out.println("\nDe"
				+ "tect Cycle: ");
		if (graph.detectCycle() == true) {
			System.out.println("Cycle Present");
		} else {
			System.out.println("No Cylce");
		}		
	}
}
