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
        }
        writer.close();
    }
}
