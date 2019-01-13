import java.util.*;
public class SymbolTable {
    private Map<String, Integer> symbolTable = new HashMap<>();
    private int address;
    public SymbolTable(){
        symbolTable.put("R0",0); symbolTable.put("SCREEN",16384);
        symbolTable.put("R1",1); symbolTable.put("KBD",24576);
        symbolTable.put("R2",2); symbolTable.put("SP",0);
        symbolTable.put("R3",3); symbolTable.put("LCL",1);
        symbolTable.put("R4",4); symbolTable.put("ARG",2);
        symbolTable.put("R5",5); symbolTable.put("THIS",3);
        symbolTable.put("R6",6); symbolTable.put("THAT",4);
        symbolTable.put("R7",7);
        symbolTable.put("R8",8);
        symbolTable.put("R9",9);
        symbolTable.put("R10",10);
        symbolTable.put("R11",11);
        symbolTable.put("R12",12);
        symbolTable.put("R13",13);
        symbolTable.put("R14",14);
        symbolTable.put("R15",15);
        this.address = 16;
    }
    public boolean contains(String symbol){
        return this.symbolTable.containsKey(symbol);
    }
    public void addEntry(String symbol, int address){
        if(!contains(symbol))
            this.symbolTable.put(symbol,address);
    }
    public int getAddress(String symbol){
        if(!contains(symbol))
            return -1;
        return this.symbolTable.get(symbol);
    }
    public int getNextAddress(){
        return this.address;
    }
    public void addressIncrement(){
        this.address++;
    }
}
