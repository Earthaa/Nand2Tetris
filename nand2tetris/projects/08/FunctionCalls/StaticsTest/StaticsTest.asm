@256
D=A
@SP
M=D
//call Sys.init 0
@SP
D=M
@R14
M=D
@61
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@51
D=D-1;JLT
@R14
M=M-1
@45
0;JMP
@R14
D=M
@ARG
M=D
@SP
D=M
@LCL
M=D
@Sys.init
0;JMP
// function Sys.init 0
(Sys.init)
// push constant 6
@6
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 8
@8
D=A
@SP
A=M
M=D
@SP
M=M+1
//call Class1.set 2
@SP
D=M
@R14
M=D
@132
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@2
D=A
@122
D=D-1;JLT
@R14
M=M-1
@116
0;JMP
@R14
D=M
@ARG
M=D
@SP
D=M
@LCL
M=D
@Class1.set
0;JMP
// pop temp 0
@SP
M=M-1
@5
D=A
@R13
M=D
@0
D=A
@146
D=D-1;JLT
@R13
M=M+1
@140
0;JMP
@SP
A=M
D=M
@R13
A=M
M=D
// push constant 23
@23
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 15
@15
D=A
@SP
A=M
M=D
@SP
M=M+1
//call Class2.set 2
@SP
D=M
@R14
M=D
@223
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@2
D=A
@213
D=D-1;JLT
@R14
M=M-1
@207
0;JMP
@R14
D=M
@ARG
M=D
@SP
D=M
@LCL
M=D
@Class2.set
0;JMP
// pop temp 0
@SP
M=M-1
@5
D=A
@R13
M=D
@0
D=A
@237
D=D-1;JLT
@R13
M=M+1
@231
0;JMP
@SP
A=M
D=M
@R13
A=M
M=D
//call Class1.get 0
@SP
D=M
@R14
M=D
@300
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@290
D=D-1;JLT
@R14
M=M-1
@284
0;JMP
@R14
D=M
@ARG
M=D
@SP
D=M
@LCL
M=D
@Class1.get
0;JMP
//call Class2.get 0
@SP
D=M
@R14
M=D
@357
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@347
D=D-1;JLT
@R14
M=M-1
@341
0;JMP
@R14
D=M
@ARG
M=D
@SP
D=M
@LCL
M=D
@Class2.get
0;JMP
// label WHILE
(WHILE)
// goto WHILE
@WHILE
0;JMP// function Class1.set 0
(Class1.set)
// push argument 0
@ARG
A=M
D=A
@R13
M=D
@0
D=A
@372
D=D-1;JLT
@R13
M=M+1
@366
0;JMP
@R13
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
// pop static 0
@SP
M=M-1
@SP
A=M
D=M
@Class1.0
M=D
// push argument 1
@ARG
A=M
D=A
@R13
M=D
@1
D=A
@400
D=D-1;JLT
@R13
M=M+1
@394
0;JMP
@R13
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
// pop static 1
@SP
M=M-1
@SP
A=M
D=M
@Class1.1
M=D
// push constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// return
@LCL
D=M
@FRAME
M=D
@FRAME
D=M
@R13
M=D
@5
D=A
@R13
M=M-1
@432
D=D-1;JGT
@R13
A=M
D=M
@RET
M=D
// pop argument 0
@SP
M=M-1
@ARG
A=M
D=A
@R13
M=D
@0
D=A
@456
D=D-1;JLT
@R13
M=M+1
@450
0;JMP
@SP
A=M
D=M
@R13
A=M
M=D
@ARG
D=M
@SP
M=D+1
@FRAME
D=M
@R13
M=D
@1
D=A
@R13
M=M-1
@472
D=D-1;JGT
@R13
A=M
D=M
@THAT
M=D
@FRAME
D=M
@R13
M=D
@2
D=A
@R13
M=M-1
@487
D=D-1;JGT
@R13
A=M
D=M
@THIS
M=D
@FRAME
D=M
@R13
M=D
@3
D=A
@R13
M=M-1
@502
D=D-1;JGT
@R13
A=M
D=M
@ARG
M=D
@FRAME
D=M
@R13
M=D
@4
D=A
@R13
M=M-1
@517
D=D-1;JGT
@R13
A=M
D=M
@LCL
M=D
@RET
A=M
0;JMP
// function Class1.get 0
(Class1.get)
// push static 0
@Class1.0
D=M
@SP
A=M
M=D
@SP
M=M+1
// push static 1
@Class1.1
D=M
@SP
A=M
M=D
@SP
M=M+1
// sub
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=M-D
@SP
M=M+1
// return
@LCL
D=M
@FRAME
M=D
@FRAME
D=M
@R13
M=D
@5
D=A
@R13
M=M-1
@563
D=D-1;JGT
@R13
A=M
D=M
@RET
M=D
// pop argument 0
@SP
M=M-1
@ARG
A=M
D=A
@R13
M=D
@0
D=A
@587
D=D-1;JLT
@R13
M=M+1
@581
0;JMP
@SP
A=M
D=M
@R13
A=M
M=D
@ARG
D=M
@SP
M=D+1
@FRAME
D=M
@R13
M=D
@1
D=A
@R13
M=M-1
@603
D=D-1;JGT
@R13
A=M
D=M
@THAT
M=D
@FRAME
D=M
@R13
M=D
@2
D=A
@R13
M=M-1
@618
D=D-1;JGT
@R13
A=M
D=M
@THIS
M=D
@FRAME
D=M
@R13
M=D
@3
D=A
@R13
M=M-1
@633
D=D-1;JGT
@R13
A=M
D=M
@ARG
M=D
@FRAME
D=M
@R13
M=D
@4
D=A
@R13
M=M-1
@648
D=D-1;JGT
@R13
A=M
D=M
@LCL
M=D
@RET
A=M
0;JMP
// function Class2.set 0
(Class2.set)
// push argument 0
@ARG
A=M
D=A
@R13
M=D
@0
D=A
@673
D=D-1;JLT
@R13
M=M+1
@667
0;JMP
@R13
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
// pop static 0
@SP
M=M-1
@SP
A=M
D=M
@Class2.0
M=D
// push argument 1
@ARG
A=M
D=A
@R13
M=D
@1
D=A
@701
D=D-1;JLT
@R13
M=M+1
@695
0;JMP
@R13
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
// pop static 1
@SP
M=M-1
@SP
A=M
D=M
@Class2.1
M=D
// push constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// return
@LCL
D=M
@FRAME
M=D
@FRAME
D=M
@R13
M=D
@5
D=A
@R13
M=M-1
@733
D=D-1;JGT
@R13
A=M
D=M
@RET
M=D
// pop argument 0
@SP
M=M-1
@ARG
A=M
D=A
@R13
M=D
@0
D=A
@757
D=D-1;JLT
@R13
M=M+1
@751
0;JMP
@SP
A=M
D=M
@R13
A=M
M=D
@ARG
D=M
@SP
M=D+1
@FRAME
D=M
@R13
M=D
@1
D=A
@R13
M=M-1
@773
D=D-1;JGT
@R13
A=M
D=M
@THAT
M=D
@FRAME
D=M
@R13
M=D
@2
D=A
@R13
M=M-1
@788
D=D-1;JGT
@R13
A=M
D=M
@THIS
M=D
@FRAME
D=M
@R13
M=D
@3
D=A
@R13
M=M-1
@803
D=D-1;JGT
@R13
A=M
D=M
@ARG
M=D
@FRAME
D=M
@R13
M=D
@4
D=A
@R13
M=M-1
@818
D=D-1;JGT
@R13
A=M
D=M
@LCL
M=D
@RET
A=M
0;JMP
// function Class2.get 0
(Class2.get)
// push static 0
@Class2.0
D=M
@SP
A=M
M=D
@SP
M=M+1
// push static 1
@Class2.1
D=M
@SP
A=M
M=D
@SP
M=M+1
// sub
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=M-D
@SP
M=M+1
// return
@LCL
D=M
@FRAME
M=D
@FRAME
D=M
@R13
M=D
@5
D=A
@R13
M=M-1
@864
D=D-1;JGT
@R13
A=M
D=M
@RET
M=D
// pop argument 0
@SP
M=M-1
@ARG
A=M
D=A
@R13
M=D
@0
D=A
@888
D=D-1;JLT
@R13
M=M+1
@882
0;JMP
@SP
A=M
D=M
@R13
A=M
M=D
@ARG
D=M
@SP
M=D+1
@FRAME
D=M
@R13
M=D
@1
D=A
@R13
M=M-1
@904
D=D-1;JGT
@R13
A=M
D=M
@THAT
M=D
@FRAME
D=M
@R13
M=D
@2
D=A
@R13
M=M-1
@919
D=D-1;JGT
@R13
A=M
D=M
@THIS
M=D
@FRAME
D=M
@R13
M=D
@3
D=A
@R13
M=M-1
@934
D=D-1;JGT
@R13
A=M
D=M
@ARG
M=D
@FRAME
D=M
@R13
M=D
@4
D=A
@R13
M=M-1
@949
D=D-1;JGT
@R13
A=M
D=M
@LCL
M=D
@RET
A=M
0;JMP
