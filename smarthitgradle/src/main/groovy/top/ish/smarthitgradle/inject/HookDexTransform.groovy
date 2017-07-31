package top.ish.smarthitgradle.inject

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.transforms.DexTransform
import org.gradle.api.Project
import top.ish.smarthitgradle.utils.FileUtils;

public class HookDexTransform extends Transform {
    Project project
    DexTransform dexTransform
    def variant

    HookDexTransform(Project project, def variant, DexTransform dexTransform) {
        this.dexTransform = dexTransform
        this.project = project
        this.variant = variant
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, IOException, InterruptedException {
        //所有的class目录
        HashSet<File> directoryInputFiles = new HashSet<>();
        for (TransformInput input : transformInvocation.getInputs()) {
            Collection<DirectoryInput> directoryInputs = input.getDirectoryInputs()
            if (directoryInputs != null) {
                for (DirectoryInput directoryInput : directoryInputs) {
                    directoryInputFiles.add(directoryInput.getFile())
                }
            }
        }

        for (File file : directoryInputFiles.iterator()) {
            FileUtils.traverseFolder(file.getAbsolutePath())
        }
        dexTransform.transform(transformInvocation);
    }

    @Override
    String getName() {
        return dexTransform.name
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return dexTransform.getInputTypes()
    }

    @Override
    Set<QualifiedContent.Scope> getScopes() {
        return dexTransform.getScopes()
    }

    @Override
    boolean isIncremental() {
        return dexTransform.isIncremental()
    }

}