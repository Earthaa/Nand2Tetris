import java.util.*;
import java.io.*;
public class Parser {
    private File file;
    private Scanner sc;
    private String currrentCommand;
    private CommandType currentType;
    Parser(String path) throws Exception{
        file = new File(path);
        sc = new Scanner(file);
    }

    public boolean hasMoreCommands(){
        return sc.hasNextLine();
    }
    public String advance() throws Exception{
        if(!hasMoreCommands())
            throw new Exception("There is no more commands");
        currrentCommand = sc.nextLine().trim();
        currentType = commandType();
        return currrentCommand;
    }
    //Parsing word
    private CommandType commandType() throws Exception{
        if(currrentCommand == null)
            throw new Exception("There is no current command");
        if(currrentCommand.length() == 0 ||(currrentCommand.charAt(0) == '/' && currrentCommand.charAt(1) == '/'))
            return CommandType.C_COMMENT;

        String firstPart = currrentCommand.split("//")[0];
        currrentCommand = firstPart.trim();//Ignore comment text behind

        String type = firstPart.split(" ")[0];
        if(firstPart.equals("add") || firstPart.equals("sub") || firstPart.equals("neg") ||
                firstPart.equals("eq") || firstPart.equals("lt") || firstPart.equals("and") ||
                firstPart.equals("or") || firstPart.equals("not") || firstPart.equals("gt"))
            return CommandType.C_ARITHMETIC;
            //If not C_ARITHETIC then do parsing to the first part
        else if(type.equals("push") )
            return CommandType.C_PUSH;
        else if(type.equals("pop") )
            return CommandType.C_POP;
        else if(type.equals("label"))
            return CommandType.C_LABEL;
        else if(type.equals("return"))
            return CommandType.C_RETURN;
        else if(type.equals("goto"))
            return CommandType.C_GOTO;
        else if(type.equals("if-goto"))
            return CommandType.C_IF;
        else if(type.equals("call"))
            return CommandType.C_CALL;
        else if(type.equals("function"))
            return CommandType.C_FUNCTION;

        return null;
    }
    public String getCurrrentCommand(){
        return currrentCommand;
    }
    public CommandType getCurrentType() {
        return currentType;
    }

    /*Return the first argument of the current commands,
            If it is a C_ARITHETIC command, just return itself, such method
            should not be called if current command is a C_RETURN or C_COMMENT command
         */
//    public String getArg1() throws Exception{
//        if(currentType == null || currentType == CommandType.C_RETURN ||
//                currentType == CommandType.C_COMMENT)
//            throw new Exception("Can't get the first argument, please check the current type.");
//        return currrentCommand.split("//")[0].split(" ")[0];
//    }
//    /*Return the second argument of the current commands,
//                should only be called if current command is a C_PUSH , C_POP , C_FUNCTION,
//                 C_CALL command
//             */
//    public String getArg2() throws Exception{
//        if(currentType == null || currentType == CommandType.C_RETURN ||
//                currentType == CommandType.C_COMMENT || currentType == CommandType.C_ARITHMETIC)
//            throw new Exception("Can't get the second argument, please check the current type.");
//        String[] parsed = currrentCommand.split("//")[0].split(" ");
//        return parsed[1] + " " + parsed[2];
//    }
}
