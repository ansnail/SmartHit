package top.ish.smarthitlibrary.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM5;

public class AsmClassVisitor extends ClassVisitor {
    public String className;
    public AsmClassVisitor(String className, ClassVisitor classVisitor) {
        super(ASM5, classVisitor);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if ("onClick" == name) {
            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);// 先得到原始的方法
            return new AsmMethodVisit(className,mv);
        } else {
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
    }
}