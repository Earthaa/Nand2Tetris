import java.util.*;
import java.io.*;
public class WriteCode {
    private String fileName;
    private FileWriter fileWriter;
    private int lineNum; //Always point to the last line
    WriteCode(String name) throws Exception{
        String[] tmp = name.split("/");
        fileName = tmp[tmp.length - 1].split("[.]")[0];
        fileWriter = new FileWriter(fileName+".asm");
        lineNum = -1;
    }
    private void writeCompare(String command) throws Exception{

        fileWriter.write("D=M-D" + '\n');
        fileWriter.write("@" + Integer.toString(lineNum+9)+'\n');
        if(command.equals("eq"))
            fileWriter.write("D;JEQ"+'\n');
        else if(command.equals("lt"))
            fileWriter.write("D;JLT"+'\n');
        else if(command.equals("gt"))
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
                writeCompare("eq");
            }
            else if(command.equals("gt")){
               writeCompare("gt");
            }
            else if(command.equals("lt")){
                writeCompare("lt");
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
        if(comm.equals("pop")){
            fileWriter.write("@SP" + '\n');
            fileWriter.write("M=M-1" + '\n');
            lineNum += 2;
        }
        if(field.equals("constant")){
            fileWriter.write("@" + Integer.parseInt(num) + '\n');
            fileWriter.write("D=A" + '\n');
            fileWriter.write("@SP" + '\n');
            fileWriter.write("A=M" + '\n');
            fileWriter.write("M=D" + '\n');
            lineNum += 5;
        }
        else if(field.equals("argument") || field.equals("local")
                || field.equals("this") || field.equals("that") || field.equals("temp")){
            if(field.equals("argument")){
                fileWriter.write("@ARG" + '\n');
                fileWriter.write("A=M" + '\n');
                lineNum+=2;
            }
            else if (field.equals("local")){
                fileWriter.write("@LCL" + '\n');
                fileWriter.write("A=M" + '\n');
                lineNum+=2;
            }
            else if(field.equals("this")){
                fileWriter.write("@THIS" + '\n');
                fileWriter.write("A=M" + '\n');
                lineNum+=2;
            }
            else if(field.equals("that")){
                fileWriter.write("@THAT" + '\n');
                fileWriter.write("A=M" + '\n');
                lineNum+=2;
            }
            else if(field.equals("temp")){
                fileWriter.write("@5"+'\n');
                lineNum+=1;
            }
            fileWriter.write("D=A" + '\n');
            fileWriter.write("@R13" + '\n');
            fileWriter.write("M=D" + '\n');
            fileWriter.write("@" + num + '\n');
            fileWriter.write("D=A" + '\n');
            fileWriter.write("@" + Integer.toString(lineNum+12) + '\n');
            fileWriter.write("D=D-1;JLT" + '\n');
            fileWriter.write("@R13" + '\n');
            fileWriter.write("M=M+1" + '\n');
            fileWriter.write("@" + Integer.toString(lineNum+6) + '\n');
            fileWriter.write("0;JMP" + '\n');
            lineNum+=11;
            if(comm.equals("push")){
                fileWriter.write("@R13" + '\n');
                fileWriter.write("A=M" + '\n');
                fileWriter.write("D=M" + '\n');
                fileWriter.write("@SP" + '\n');
                fileWriter.write("A=M" + '\n');
                fileWriter.write("M=D" + '\n');
            }
            else{
                fileWriter.write("@SP" + '\n');
                fileWriter.write("A=M" + '\n');
                fileWriter.write("D=M" + '\n');
                fileWriter.write("@R13" + '\n');
                fileWriter.write("A=M" + '\n');
                fileWriter.write("M=D" + '\n');
            }
            lineNum += 6;
        }
        else if(field.equals("static")){
            if(comm.equals("push")){
                fileWriter.write("@"+fileName+"."+num + '\n');
                fileWriter.write("D=M"+'\n');
                fileWriter.write("@SP"+'\n');
                fileWriter.write("A=M"+'\n');
                fileWriter.write("M=D"+'\n');
                lineNum+=5;
            }
            else{
                fileWriter.write("@SP"+'\n');
                fileWriter.write("A=M"+'\n');
                fileWriter.write("D=M"+'\n');
                fileWriter.write("@"+fileName+"."+num + '\n');
                fileWriter.write("M=D"+'\n');
                lineNum+=5;
            }
        }
        else if(field.equals("pointer")){
            if(comm.equals("push")){
                if(num.equals("0")){
                    fileWriter.write("@THIS"+'\n');
                }
                else if(num.equals("1")){
                    fileWriter.write("@THAT"+'\n');
                }
                fileWriter.write("D=M"+'\n');
                fileWriter.write("@SP"+'\n');
                fileWriter.write("A=M"+'\n');
                fileWriter.write("M=D"+'\n');
                lineNum += 5;
            }
            else{
                fileWriter.write("@SP"+'\n');
                fileWriter.write("A=M"+'\n');
                fileWriter.write("D=M"+'\n');
                if(num.equals("0")){
                    fileWriter.write("@THIS"+'\n');
                }
                else if(num.equals("1")){
                    fileWriter.write("@THAT"+'\n');
                }
                fileWriter.write("M=D"+'\n');
                lineNum += 5;
            }
        }
        if(comm.equals("push")){
            fileWriter.write("@SP" + '\n');
            fileWriter.write("M=M+1" + '\n');
            lineNum += 2;
        }

    }
    public void close() throws Exception{
        fileWriter.close();
    }
}
