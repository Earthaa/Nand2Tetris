import java.io.BufferedWriter;
import java.io.FileWriter;
public class HackAssembler {
    public static void main(String[] args) throws Exception {
        Parser parser = new Parser(args[0]);
        while (parser.hasNextLine()){
            String[] command = parser.doParse(parser.getNextLine());
            if(parser.getType(command) == CommandType.LABEL)
                parser.dealLabel(command[0]);
            else if(parser.getType(command) == CommandType.A_COMMAND ||
            parser.getType(command) == CommandType.C_COMMAND)
                parser.incLineNumber();
            else
                continue;
        }
        parser.reset();

        FileWriter fileWriter = new FileWriter(args[0].split("[.]")[0]+".hack");

        while (parser.hasNextLine()){
            String[] command = parser.doParse(parser.getNextLine());
            if(parser.getType(command) == CommandType.A_COMMAND){
                String res = parser.dealACommand(command[0])+"\n";
                fileWriter.write(res);
            }
            else if(parser.getType(command) == CommandType.C_COMMAND){
                String res = parser.dealCCommand(command[0])+"\n";
                fileWriter.write(res);
            }
        }
        fileWriter.close();
    }
}
