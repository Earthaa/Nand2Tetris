import java.util.*;
public class Code {
    Map<String,String> codeTable;
    Map<String,String> symbolTable;
    public Code(){
        codeTable = new HashMap<>();
        symbolTable = new HashMap<>();
        codeTable.put("0","0101010");
        codeTable.put("1","0111111");
        codeTable.put("-1","0111010");
        codeTable.put("D","0001100");
        codeTable.put("A","0110000");codeTable.put("M","1110000");
        codeTable.put("!D","0001101");
        codeTable.put("!A","0110001"); codeTable.put("!M","1110001");
        codeTable.put("-D","0001111");
        codeTable.put("-A","0110011");codeTable.put("-M","1110011");
        codeTable.put("D+1","0011111");
        codeTable.put("A+1","0110111");codeTable.put("M+1","1110111");
        codeTable.put("D-1","0001110");
        codeTable.put("A-1","0110010");codeTable.put("M-1","1110010");
        codeTable.put("D+A","0000010");codeTable.put("D+M","1000010");
        codeTable.put("D-A","0010011");codeTable.put("D-M","1010011");
        codeTable.put("A-D","0000111");codeTable.put("M-D","1000111");
        codeTable.put("D&A","0000000");codeTable.put("D&M","1000000");
        codeTable.put("D|A","0010101");codeTable.put("D|M","1010101");
        symbolTable.put("M","001"); codeTable.put("JGT","001");
        symbolTable.put("D","010"); codeTable.put("JEQ","010");
        symbolTable.put("MD","011");codeTable.put("JGE","011");
        symbolTable.put("A","100");codeTable.put("JLT","100");
        symbolTable.put("AM","101");codeTable.put("JNE","101");
        symbolTable.put("AD","110");codeTable.put("JLE","110");
        symbolTable.put("AMD","111");codeTable.put("JMP","111");
    }

    public String jump(String[] C_Command){
        if(C_Command.length < 2)
            return "000";
        else
            return codeTable.get(C_Command[1]);
    }
    public String comp(String[] C_Command){
        String[] firstPart = C_Command[0].split("=");
        if(firstPart.length < 2)
            return codeTable.get(firstPart[0]);
        else
            return codeTable.get(firstPart[1]);
    }
    public String dest(String[] C_Command){
        String[] firstPart = C_Command[0].split("=");
        if(firstPart.length < 2)
            return "000";
        else
            return symbolTable.get(firstPart[0]);
    }
}
