package top.ish.smarthitlibrary.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

public class AsmMethodVisit extends MethodVisitor {
    public String className;
    public AsmMethodVisit(String className, MethodVisitor mv) {
        super(Opcodes.ASM4, mv);
        this.className = className;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc) {
        super.visitMethodInsn(opcode, owner, name, desc);
    }

    @Override
    public void visitCode() {
        // 此方法在访问方法的头部时被访问到，仅被访问一次
        // 此处可插入新的指令
        //System.out.println("Hello World!");
        System.out.println("=======================执行啦start============================");
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn(className);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        System.out.println("=======================执行啦end============================");
        super.visitCode();
    }

    @Override
    public void visitInsn(int opcode) {
        // 此方法可以获取方法中每一条指令的操作类型，被访问多次
        // 如应在方法结尾处添加新指令，则应判断：

        if (opcode == Opcodes.RETURN) {
        }
        super.visitInsn(opcode);
    }
}