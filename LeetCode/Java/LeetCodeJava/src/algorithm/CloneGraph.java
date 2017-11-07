package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rajin on 11/3/2017.
 */
class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
};
public class CloneGraph {

    public static void main(String[] args){
        CloneGraph obj = new CloneGraph();
        UndirectedGraphNode node = new UndirectedGraphNode(0);
        node.neighbors.add(node);

        UndirectedGraphNode clone = obj.cloneGraph(node);

    }


    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null){
            return null;
        }
        HashMap<Integer, UndirectedGraphNode> nodeMap = new HashMap<Integer, UndirectedGraphNode>();
        UndirectedGraphNode cloneNode = cloneNode(node,nodeMap);

        return cloneNode;
    }

    public UndirectedGraphNode cloneNode(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> nodeMap){
        if(nodeMap.containsKey(node.label)){
            return nodeMap.get(node.label);
        }
        else{
            UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
            nodeMap.put(newNode.label, newNode);
            for(UndirectedGraphNode neighbor: node.neighbors){
                UndirectedGraphNode newNeighbor = cloneNode(neighbor, nodeMap);
                newNode.neighbors.add(newNeighbor);
            }
            nodeMap.put(newNode.label, newNode);

            return newNode;
        }
    }
}
