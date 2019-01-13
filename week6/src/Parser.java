import java.io.*;
import java.util.*;
public class Parser {
    private File file;
    private Scanner sc;
    private SymbolTable st;
    private Code code;
    private int lineNumber;
    Parser(String path) throws Exception{
        file = new File(path);
        sc = new Scanner(file);
        st = new SymbolTable();
        lineNumber = 0;
        code = new Code();
    }

    public boolean hasNextLine(){
        return sc.hasNextLine();
    }
    public String getNextLine(){
        return sc.nextLine();
    }
    public String[] doParse(String line){
        line = line.trim();
        line = line.replaceAll(" ","");
        if(line.length() == 0 || ((line.charAt(0) == '/') && (line.charAt(1) == '/')))
            return new String[0];
        return line.split("//");
    }

    public CommandType getType(String[] command){
        if(command.length == 0)
            return CommandType.COMMENT;
        else if(command[0].charAt(0) == '@')
            return CommandType.A_COMMAND;
        else if(command[0].charAt(0) == '(')
            return CommandType.LABEL;
        else
            return CommandType.C_COMMAND;
    }
    //Call only when the command is label
    public void saveLabel(String label){
        if(!st.contains(label)){
            st.addEntry(label, st.getNextAddress());
            st.addressIncrement();
        }
    }
    //Call only when A Command(Begin with @), return binary code
    public String dealACommand(String command){
        String addr = command.substring(1);
        int decimalCode;
        //It's a number
        if(addr.charAt(0) >= '0' && addr.charAt(0) <= '9')
            decimalCode = Integer.parseInt(addr);

        //It's a symbol
        else {
            //We already have this symbol
            if (st.contains(addr))
                decimalCode = st.getAddress(addr);
            //We haven't had this symbol yet
            else {
                st.addEntry(addr,st.getNextAddress());
                decimalCode = st.getNextAddress();
                st.addressIncrement();
            }
        }

        return Integer.toBinaryString(0x10000 | decimalCode).substring(1);
    }
    // Call only when it's a label
    public void dealLabel(String command){
        String label = command.substring(1,command.length() - 1);
        st.addEntry(label,lineNumber);
    }

    //Called only when it's C Command
    public String dealCCommand(String command){
        String[] C_Command = command.split(";");
        if(C_Command.length == 0)
            return "1110000000000000";
        return "111" + code.comp(C_Command) + code.dest(C_Command) + code.jump(C_Command);
    }

    //Reset scanner, for round2 scan
    public void reset() throws FileNotFoundException {
        sc.close();
        sc = new Scanner(file);
        this.lineNumber = 0;
    }
    public void incLineNumber(){
        this.lineNumber++;
    }
}
