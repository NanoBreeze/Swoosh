package intermediate;

import java.util.ArrayList;

/**
 * Created by Lenny on 2016-12-10.
 */
public class SymTabStack extends ArrayList<SymTab> {

    int currentNestingLevel;

    public SymTabStack() {
        this.currentNestingLevel = 0;
        add(new SymTab(currentNestingLevel));
    }

    public SymTab getLocalSymTab () {
        return get(currentNestingLevel);
    }

    public SymTabEntry lookupLocalEntry(String name) {
        SymTab local = get(currentNestingLevel);
        return local.lookup(name);
    }

    public SymTabEntry enterLocal(String name) {
        SymTab local = get(currentNestingLevel);
        return local.enter(name);


    }

    public int getCurrentNestingLevel() {
        return currentNestingLevel;
    }

    public SymTab push() {
        currentNestingLevel++;
        SymTab symTab = new SymTab(currentNestingLevel);
        add(symTab);
        return symTab;
    }

    public SymTab pop() {

        SymTab symTab = get(currentNestingLevel);
        remove(currentNestingLevel);
        currentNestingLevel--;

        return symTab;
    }






}
