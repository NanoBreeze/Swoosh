package backend;

import intermediate.SymTabEntry;

/**
 * Created by Lenny on 2016-12-18.
 */
public class ActivationRecord {
    SymTabEntry routineId; //symbol table entry of the routine's name

    public ActivationRecord(SymTabEntry routineId) {
        this.routineId = routineId;
    }

    public SymTabEntry getRoutineId() {
        return routineId;
    }

}
