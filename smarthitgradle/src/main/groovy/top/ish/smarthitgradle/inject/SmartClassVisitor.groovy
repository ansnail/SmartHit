package top.ish.smarthitgradle.inject

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import static org.objectweb.asm.Opcodes.*


import java.lang.reflect.Type;

class SmartClassVisitor extends ClassVisitor {
    public String className;
    public SmartClassVisitor(String className, ClassVisitor classVisitor) {
        super(ASM5, classVisitor);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if ("onClick" == name && "Landroid/view/View;" == desc) {
            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);// 先得到原始的方法
            return new SmartMethodVisit(className,mv);
        } else {
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
    }
}