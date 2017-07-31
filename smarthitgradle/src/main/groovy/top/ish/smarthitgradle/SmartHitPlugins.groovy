package top.ish.smarthitgradle

import com.android.build.gradle.internal.pipeline.TransformTask
import com.android.build.gradle.internal.transforms.DexTransform;
import org.gradle.api.Plugin;
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionGraph
import org.gradle.api.execution.TaskExecutionGraphListener
import top.ish.smarthitgradle.inject.HookDexTransform

/**
 * Created by yanjie on 2017-07-31.
 * Describe:自定义插件入口类
 */

class SmartHitPlugins implements Plugin<Project> {


    @Override
    public void apply(Project project) {

        project.afterEvaluate {
            def android = project.extensions.android
            android.applicationVariants.all { variant ->

                project.getGradle().getTaskGraph().addTaskExecutionGraphListener(new TaskExecutionGraphListener() {
                    @Override
                    void graphPopulated(TaskExecutionGraph taskExecutionGraph) {

                        for (Task task : taskGraph.getAllTasks()) {

                            if (task instanceof TransformTask && task.name.toLowerCase().contains(variant.name.toLowerCase())) {
                                project.logger.warn("TaskName的名字======:" + task.name)

                                if (((TransformTask) task).getTransform() instanceof DexTransform && !(((TransformTask) task).getTransform() instanceof HookDexTransform)) {
                                    project.logger.warn("TaskName的名字:" + task.name)
                                    project.logger.warn("HOOK前的transform的名字:" + task.transform.getClass())

                                    DexTransform dexTransform = task.transform
                                    HookDexTransform hookDexTransform = new HookDexTransform(project, variant, dexTransform)
                                    variantName = variant.name;
                                    project.logger.warn("variant name: " + variant.name)

//                                    Field field = TransformTask.class.getDeclaredField("transform")
//                                    field.setAccessible(true)
//                                    field.set(task, hookDexTransform)
                                    project.logger.warn("HOOK后的transform的名字:" + task.transform.getClass())

                                    break;
                                }
                            }
                        }
                    }
                })
            }
        }
    }
}
