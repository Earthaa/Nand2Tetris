import java.util.*;
import java.io.*;
public class WriteCode {
    private String fileName;
    private FileWriter fileWriter;
    private int lineNum; //Always point to the last line
    WriteCode(String name) throws Exception{
        fileName = name.split("[.]")[0];
        fileWriter = new FileWriter(fileName+".asm");
        lineNum = -1;
    }

    public void writeArithmetic(String command) throws Exception{
        fileWriter.write("//"+" " + command + '\n');
        fileWriter.write("@SP" + '\n');
        fileWriter.write("M=M-1" + '\n');
        fileWriter.write("A=M"+'\n');
        lineNum +=3;
        //one op
        if(command.equals("neg")) {
            fileWriter.write("M=-M" + '\n');
            lineNum++;
        }
        else if(command.equals("not")){
            fileWriter.write("M=!M" + '\n');
            lineNum++;
        }
        //Two ops
        else {
            fileWriter.write("D=M" + '\n');
            fileWriter.write("@SP" + '\n');
            fileWriter.write("M=M-1" + '\n');
            fileWriter.write("A=M" + '\n');
            lineNum+=4;
            if (command.equals("add")){
                fileWriter.write("M=D+M" + '\n');
                lineNum++;
            }
            else if (command.equals("sub")){
                fileWriter.write("M=M-D" + '\n');
                lineNum++;
            }
            else if (command.equals("eq")){
                fileWriter.write("D=M-D" + '\n');
                fileWriter.write("@" + Integer.toString(lineNum+9)+'\n');
                fileWriter.write("D;JEQ"+'\n');
                fileWriter.write("@SP"+'\n');
                fileWriter.write("A=M"+'\n');
                fileWriter.write("M=0"+'\n');
                fileWriter.write("@" + Integer.toString(lineNum+12)+'\n');
                fileWriter.write("0;JMP"+'\n');
                fileWriter.write("@SP"+'\n');
                fileWriter.write("A=M"+'\n');
                fileWriter.write("M=-1"+'\n');
                lineNum+=11;
            }
            else if(command.equals("gt")){
                fileWriter.write("D=M-D" + '\n');
                fileWriter.write("@" + Integer.toString(lineNum+9)+'\n');
                fileWriter.write("D;JGT"+'\n');
                fileWriter.write("@SP"+'\n');
                fileWriter.write("A=M"+'\n');
                fileWriter.write("M=0"+'\n');
                fileWriter.write("@" + Integer.toString(lineNum+12)+'\n');
                fileWriter.write("0;JMP"+'\n');
                fileWriter.write("@SP"+'\n');
                fileWriter.write("A=M"+'\n');
                fileWriter.write("M=-1"+'\n');
                lineNum+=11;
            }
            else if(command.equals("lt")){
                fileWriter.write("D=M-D" + '\n');
                fileWriter.write("@" + Integer.toString(lineNum+9)+'\n');
                fileWriter.write("D;JLT"+'\n');
                fileWriter.write("@SP"+'\n');
                fileWriter.write("A=M"+'\n');
                fileWriter.write("M=0"+'\n');
                fileWriter.write("@" + Integer.toString(lineNum+12)+'\n');
                fileWriter.write("0;JMP"+'\n');
                fileWriter.write("@SP"+'\n');
                fileWriter.write("A=M"+'\n');
                fileWriter.write("M=-1"+'\n');
                lineNum+=11;
            }
            else if(command.equals("and")){
                fileWriter.write("M=D&M" + '\n');
                lineNum++;
            }
            else if(command.equals("or")){
                fileWriter.write("M=D|M" + '\n');
                lineNum++;
            }
        }
        fileWriter.write("@SP" + '\n');
        fileWriter.write("M=M+1" + '\n');
        lineNum+=2;
    }
    public void writePushPop(String command) throws Exception{
        fileWriter.write("//"+" " + command + '\n');
        String[] splited = command.split(" ");
        String comm = splited[0];
        String field = splited[1];
        String num = splited[2];
        if(comm.equals("push"))
            writePush(field,num);
        else
            writePop(field,num);
    }
    private void writePush(String field, String num) throws Exception{
        if(field.equals("constant")){
            fileWriter.write("@" + Integer.parseInt(num) + '\n');
            fileWriter.write("D=A" + '\n');
            fileWriter.write("@SP" + '\n');
            fileWriter.write("A=M" + '\n');
            fileWriter.write("M=D" + '\n');
            lineNum += 5;
        }
        else if(field.equals("argument") || field.equals("local")
                || field.equals("this") || field.equals("that")){
               if(field.equals("argument")){
                   fileWriter.write("@ARG" + '\n');}
               else if (field.equals("local")){
                   fileWriter.write("LCL" + '\n');}
               else if(field.equals("this")){
                   fileWriter.write("THIS" + '\n');}
               else {
                   fileWriter.write("THAT" + '\n');
               }

                fileWriter.write("D=A" + '\n');
                fileWriter.write("@R5" + '\n');
                fileWriter.write("M=D" + '\n');
                fileWriter.write("@" + '\n');

        }
        fileWriter.write("@SP" + '\n');
        fileWriter.write("M=M+1" + '\n');
        lineNum += 2;

    }
    private void writePop(String field, String num){

    }
    public void close() throws Exception{
        fileWriter.close();
    }
}
