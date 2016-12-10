package intermediate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lenny on 2016-12-10.
 */
public class SymTabEntry extends HashMap<SymTabEntryKey, Object>{

    SymTab symTab; //the symbol table containing this entry
    String name; //the name of this entry, basically the text of the token for now
    ArrayList<Integer> lineNumbers;

    public SymTabEntry(String name, SymTab symTab) {
        this.name = name;
        this.symTab = symTab;
        lineNumbers = new ArrayList<Integer>();
    }

    public void setAttribute(SymTabEntryKey key, Object value) {
        put(key, value);
    }

    public Object getAttribute(SymTabEntryKey key) {
        return get(key);
    }

    public void appendLineNumber(int lineNumber) {
        lineNumbers.add(lineNumber);
    }

    public ArrayList<Integer> getLineNumbers() {
        return lineNumbers;
    }

    public String getName(){
        return name;
    }

}
