package ksl.academic.algorithm.set1;


/**
 * Shortest paths between nodes in a graph
 *
 * initial node
 * distance of node Y
 * 
 * Setup
 * 1. Marked all nodes unvisited. Unvisited set
 * 2. Assign distance value to every node, 0 and infinite
 * 
 * Iterate
 * 3. Current node, calculate distance for all neighboring nodes, use minimum value
 * 4. When all neighboring nodes are visited, mark current node as visited and remove it from unvisited set
 * 5. Finished if destination node is marked as visited, or the smallest value in unvisited set is infinite.
 * 6. select unvisited node that's marked with smallest distance as current node and iterate
 * 
 */
public class Dijkstra {

}
