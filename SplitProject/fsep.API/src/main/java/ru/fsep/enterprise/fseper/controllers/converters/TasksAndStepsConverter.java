package ru.fsep.enterprise.fseper.controllers.converters;


import ru.fsep.enterprise.fseper.controllers.dto.StepDto;
import ru.fsep.enterprise.fseper.controllers.dto.TaskDto;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Task;

import java.util.List;

/**
 * Created by ���� on 15.07.2015.
 */
public interface TasksAndStepsConverter {
    TaskDto fromTask(Task entity);
    List<TaskDto> fromTasks(List<Task> entities);
    StepDto fromStep(Step entity);
    List<StepDto> fromSteps(List<Step> entities);
    Task toTask(TaskDto taskDto);
    List<Task> toTasks(List<TaskDto> tasksDto);
    Step toStep(StepDto stepDto);
    List<Step> toSteps(List<StepDto> stepsDto);

}
