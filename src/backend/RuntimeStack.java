package backend;

import java.util.ArrayList;

/**
 * Created by Lenny on 2016-12-18.
 */
public class RuntimeStack extends ArrayList<ActivationRecord> {

    public ArrayList<ActivationRecord> getRecords() {
        return this;
    }

    public int currentNestingLevel() {
        int topIndex = size() - 1;
        return topIndex;
    }

    public void push(ActivationRecord ar) {
        add(ar);
    }

    public void pop() {
        remove(size() - 1);
    }
}
