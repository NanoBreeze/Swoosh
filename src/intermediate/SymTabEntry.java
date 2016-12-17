package intermediate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lenny on 2016-12-10.
 */
public class SymTabEntry extends HashMap<SymTabEntryKey, Object>{

    SymTab symTab; //the symbol table containing this entry
    SymTabEntryType type;
    String name; //the name of this entry, basically the lowercase text of the token
    ArrayList<Integer> lineNumbers;



    public SymTabEntry(String name, SymTab symTab) {
        this.name = name;
        this.symTab = symTab;
        lineNumbers = new ArrayList<Integer>();
    }

    public void setType(SymTabEntryType type) {
        this.type = type;
    }

    public SymTabEntryType getType() {
        return type;
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
