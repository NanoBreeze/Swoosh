package intermediate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Created by Lenny on 2016-12-10.
 */
public class SymTab extends TreeMap<String, SymTabEntry> {
    int nestingLevel;

    public SymTab(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }

    public SymTabEntry enter(String name) {
        SymTabEntry symTabEntry = new SymTabEntry(name, this);
        put(name, symTabEntry);

        return symTabEntry;
    }


    public SymTabEntry lookup(String name) {
        return get(name);
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public ArrayList<SymTabEntry> getAllEntries() {
        Collection<SymTabEntry> values = values();
        Iterator<SymTabEntry> iter = values.iterator();
        ArrayList<SymTabEntry> symTabEntries = new ArrayList<SymTabEntry>();

        while (iter.hasNext()) {
            symTabEntries.add(iter.next());
        }

        return symTabEntries;
    }
}
