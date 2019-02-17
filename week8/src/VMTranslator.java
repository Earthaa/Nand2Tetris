public class VMTranslator {
    public static void main(String args[]) throws Exception{
        Parser parser = new Parser(args[0]);
        //String[] tmp = args[0].split("/");
        WriteCode writer = new WriteCode(args[0]);
        while (parser.hasMoreCommands()){
            parser.advance();
            if(parser.getCurrentType() == CommandType.C_ARITHMETIC)
                writer.writeArithmetic(parser.getCurrrentCommand());
            else if(parser.getCurrentType() == CommandType.C_POP || parser.getCurrentType() == CommandType.C_PUSH)
                writer.writePushPop(parser.getCurrrentCommand());
            else if(parser.getCurrentType() == CommandType.C_LABEL)
                writer.writeLabel(parser.getCurrrentCommand());
            else if(parser.getCurrentType() == CommandType.C_GOTO)
                writer.writeGoto(parser.getCurrrentCommand());
            else if(parser.getCurrentType() == CommandType.C_IF)
                writer.writeIf(parser.getCurrrentCommand());
            else if(parser.getCurrentType() == CommandType.C_FUNCTION)
                writer.writeFunction(parser.getCurrrentCommand());
            else if(parser.getCurrentType() == CommandType.C_RETURN)
                writer.writeReturn(parser.getCurrrentCommand());
            else if(parser.getCurrentType() == CommandType.C_CALL)
                writer.writeCall(parser.getCurrrentCommand());

        }
        writer.close();
    }
}
