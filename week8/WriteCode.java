import java.util.*;
import java.io.*;
public class WriteCode {
    private String fileName;
    private FileWriter fileWriter;
    private String className;
    private int lineNum; //Always point to the last line
    WriteCode(String fileName,String className) throws Exception{
        this.fileName = fileName.split("[.]")[0];
        fileWriter = new FileWriter(this.fileName+".asm");
        lineNum = -1;
        this.className = className.split("[.]")[0];
    }
    private void writeCompare(String command) throws Exception{
        //True push -1, false push 0
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
    public void setClassName(String className){
        this.className = className.split("[.]")[0];
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
                fileWriter.write("@"+className+"."+num + '\n');
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
                fileWriter.write("@"+className+"."+num + '\n');
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
    public void writeLabel(String command) throws Exception{
        fileWriter.write("// " + command + "\n");
        String label = command.split(" ")[1];
        fileWriter.write("("+label+")"+"\n");
    }
    public void writeGoto(String command) throws Exception{
        fileWriter.write("// " + command + "\n");
        String label = command.split(" ")[1];
        fileWriter.write("@" + label + "\n");
        fileWriter.write("0;JMP");
        lineNum += 2;
    }
    public void writeIf(String command) throws Exception{
        fileWriter.write("// " + command + "\n");
        String label = command.split(" ")[1];
        fileWriter.write("@SP" + "\n");
        fileWriter.write("M=M-1" + "\n");
        fileWriter.write("A=M" + "\n");
        fileWriter.write("D=M" + "\n");
        fileWriter.write("@" + label + "\n");
        fileWriter.write("D;JNE" + "\n");
        lineNum+=6;
    }
    public void writeFunction(String command) throws Exception{
        fileWriter.write("// " + command + "\n");
        String[] function = command.split(" ");
        String functionName = function[1];
        String argsNum = function[2];
        fileWriter.write("("+functionName+")"+"\n");
        for(int i = 0; i < Integer.parseInt(argsNum); i++){
            writePushPop("push constant 0");
        }
    }
    public void writeReturn(String command) throws Exception{
        //Frame = LCL
        fileWriter.write("// " + command + "\n");
        fileWriter.write("@LCL" + "\n");
        fileWriter.write("D=M" + "\n");
        fileWriter.write("@FRAME" + "\n");
        fileWriter.write("M=D" + "\n");
        lineNum += 4;
        //RET = *(FRAME-5)
        writeFrame(5,"RET");
        //*ARG = pop()
        writePushPop("pop argument 0");
        // SP = ARG+1
        fileWriter.write("@ARG" + "\n");
        fileWriter.write("D=M" + "\n");
        fileWriter.write("@SP" + "\n");
        fileWriter.write("M=D+1" + "\n");
        lineNum+=4;
        //THAT = *(FRAME-1)
        writeFrame(1,"THAT");
        //THIS = *(FRAME-2)
        writeFrame(2,"THIS");
        //ARG = *(FRAME-3)
        writeFrame(3,"ARG");
        //LCL = *(FRAME-4)
        writeFrame(4,"LCL");
        fileWriter.write("@RET"+"\n");
        fileWriter.write("A=M"+"\n");
        fileWriter.write("0;JMP"+"\n");
        lineNum+=3;
    }
    private void writeFrame(int n, String pointer) throws Exception{
        /*   pointer = *(FRAME - n)
        * */
        //Do with set FRAME - n
        fileWriter.write("@FRAME" + "\n");
        //fileWriter.write("A=M"+"\n");
        fileWriter.write("D=M"+"\n");
        fileWriter.write("@R13"+"\n");
        fileWriter.write("M=D"+"\n");
        fileWriter.write("@"+Integer.toString(n)+"\n");
        fileWriter.write("D=A"+"\n");
        fileWriter.write("@R13"+"\n");//lineNum + 8
        fileWriter.write("M=M-1"+"\n");
        fileWriter.write("@"+Integer.toString(lineNum + 7) + "\n");
        fileWriter.write("D=D-1;JGT"+"\n");
        //Do with set pointer
        fileWriter.write("@R13"+"\n");
        fileWriter.write("A=M"+"\n");
        fileWriter.write("D=M"+"\n");
        fileWriter.write("@"+pointer+"\n");
        fileWriter.write("M=D"+"\n");
        lineNum += 15;
    }
    public void writeCall(String command) throws Exception{
        fileWriter.write("//" + command + "\n");
        String[] call = command.split(" ");
        String n = call[2];
        String function = call[1];
        //Save sp
        fileWriter.write("@SP" + "\n");
        fileWriter.write("D=M" + "\n");
        fileWriter.write("@R14" + "\n");
        fileWriter.write("M=D" + "\n");
        lineNum += 4;
        //push return-address
        fileWriter.write("@" + Integer.toString(lineNum + 54) + "\n");
        fileWriter.write("D=A" + "\n");
        fileWriter.write("@SP" + "\n");
        fileWriter.write("A=M" + "\n");
        fileWriter.write("M=D" + "\n");
        fileWriter.write("@SP" + "\n");
        fileWriter.write("M=M+1" + "\n");
        lineNum+=7;
        //push pointer
        pushPointer("LCL");
        pushPointer("ARG");
        pushPointer("THIS");
        pushPointer("THAT");
        lineNum += 28;
        //ARG =SP - n - 5 which is saved in R14
        fileWriter.write("@" + n + "\n");
        fileWriter.write("D=A" + "\n");
        fileWriter.write("@" + Integer.toString(lineNum + 9) + "\n");
        fileWriter.write("D=D-1;JLT" + "\n");
        fileWriter.write("@R14" + "\n");
        fileWriter.write("M=M-1" + "\n");
        fileWriter.write("@" + Integer.toString(lineNum + 3) + "\n");
        fileWriter.write("0;JMP" + "\n");
        fileWriter.write("@R14" + "\n");
        fileWriter.write("D=M" + "\n");
        fileWriter.write("@ARG" + "\n");
        fileWriter.write("M=D" + "\n");
        //LCL = SP
        fileWriter.write("@SP" + "\n");
        fileWriter.write("D=M" + "\n");
        fileWriter.write("@LCL" + "\n");
        fileWriter.write("M=D" + "\n");
        //goto f
        fileWriter.write("@" + function + "\n");
        fileWriter.write("0;JMP" + "\n");
        lineNum += 18;

    }
    private void pushPointer(String pointer) throws Exception{
        fileWriter.write("@" + pointer + "\n");
        fileWriter.write("D=M" + "\n");
        fileWriter.write("@SP" + "\n");
        fileWriter.write("A=M" + "\n");
        fileWriter.write("M=D" + "\n");
        fileWriter.write("@SP" + "\n");
        fileWriter.write("M=M+1" + "\n");
    }
    public void writeInitialize() throws Exception{
        fileWriter.write("@256"+"\n");
        fileWriter.write("D=A"+"\n");
        fileWriter.write("@SP"+"\n");
        fileWriter.write("M=D"+"\n");
        lineNum+=4;
        writeCall("call Sys.init 0");
    }
    public void close() throws Exception{
        fileWriter.close();
    }
}


