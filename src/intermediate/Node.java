package intermediate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lenny on 2016-12-10.
 */
public class Node extends HashMap<NodeKey, Object> {

    NodeType nodeType;
    Node parent;
    ArrayList<Node> children;

    public Node(NodeType type) {
        this.nodeType = type;
        this.parent = null;
        children = new ArrayList<Node>();
    }

    public Node(NodeType type, Node parent) {
        this.nodeType = type;
        this.parent = parent;
        children = new ArrayList<Node>();
    }

    public NodeType getType() {
        return nodeType;
    }

    public Node addChild(Node child) {
        children.add(child);
        return child;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public Node getParent() {
        return parent;
    }

    public void setAttribute(NodeKey key, Object value) {
        put(key, value);
    }

    public Object getAttribute(NodeKey key) {
        return get(key);
    }


}
