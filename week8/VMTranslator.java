import java.io.FileNotFoundException;
import java.io.*;
public class VMTranslator {
    public static void writeCode(Parser parser,WriteCode writer) throws Exception{
        while (parser.hasMoreCommands()){
            parser.advance();
            //System.out.println(parser.getCurrrentCommand());
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
    }
    public static void main(String args[]) throws Exception{
        //String inName = args[0];
        String[] splitFileName = args[0].split("[.]");
        //Directory
        if(splitFileName.length == 1) {
            String[] tmpname = args[0].split("/");
            WriteCode writer = new WriteCode(args[0] + "/" + tmpname[tmpname.length - 1],"None");
            //Write Sys.vm
            writer.writeInitialize();
            try{
                Parser parser = new Parser(args[0]+"/Sys.vm");
                writer.setClassName("Sys");
                VMTranslator.writeCode(parser,writer);
            }
            catch (FileNotFoundException e){
                throw new FileNotFoundException("Can't find Sys.vm file, please check the files in your directory" );
            }
            File folder = new File(args[0]);
            File[] Files = folder.listFiles();
            for(File eachFile:Files){
                //System.out.println(eachFile.toString());
                if(eachFile.toString().indexOf(".vm") != -1 && eachFile.toString().indexOf("Sys.vm") == -1){
                    String[] tmp = eachFile.toString().split("/");
                    Parser parser = new Parser(eachFile.toString());
                    writer.setClassName(tmp[tmp.length - 1]);
                    VMTranslator.writeCode(parser,writer);
                }
            }
            writer.close();
        }
        else if(splitFileName.length > 1 && splitFileName[1].equals("vm")) {
            Parser parser = new Parser(args[0]);
            String[] tmp = args[0].split("/");
            WriteCode writer = new WriteCode(tmp[tmp.length - 1],tmp[tmp.length - 1]);
            VMTranslator.writeCode(parser,writer);
            writer.close();
        }
        else{
            throw new Exception("File type error");
        }
    }
}
