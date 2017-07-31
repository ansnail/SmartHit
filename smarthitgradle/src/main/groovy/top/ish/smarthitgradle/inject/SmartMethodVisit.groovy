package top.ish.smarthitgradle.inject

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.*

class SmartMethodVisit extends MethodVisitor {
    public String className;
    public SmartMethodVisit(String className, MethodVisitor mv) {
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

        mv.visitVarInsn(ALOAD, 1);
        mv.visitLdcInsn(className);
        mv.visitMethodInsn(INVOKESTATIC, "top/ish/smarthitlibrary/utils/HitUtils", "onClickMethod", "(Landroid/view/View;Ljava/lang/String;)V", false);


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

    @Override
    void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
        super.visitLocalVariable(name, desc, signature, start, end, index)
        System.out.println("=======================visitLocalVariable============================");
    }

}