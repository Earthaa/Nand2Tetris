public class VMTranslator {
    public static void main(String args[]) throws Exception{
        System.out.println("Input File is: " + args[0]);
        Parser parser = new Parser(args[0]);
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
