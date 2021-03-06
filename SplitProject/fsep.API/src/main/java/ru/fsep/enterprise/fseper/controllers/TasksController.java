package ru.fsep.enterprise.fseper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fsep.enterprise.fseper.controllers.converters.TasksAndStepsConverter;
import ru.fsep.enterprise.fseper.controllers.dto.ResponseDto;
import ru.fsep.enterprise.fseper.controllers.dto.StepDto;
import ru.fsep.enterprise.fseper.controllers.dto.TaskDto;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ���� on 13.07.2015.
 */
@RestController
@RequestMapping(value = "/tasks/")
public class TasksController {
    @Autowired
    private UsersServiceFacade usersServiceFacade;

    @Autowired
    private TasksAndStepsConverter tasksAndStepsConverter;

    @RequestMapping(value = "{task-id}.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> getTask(@PathVariable("task-id") int taskId) {
        Task task = usersServiceFacade.getTask(taskId);
        TaskDto taskDto = tasksAndStepsConverter.fromTask(task);
        return ResponseBuilder.buildResponseGet(taskDto);
    }

    @RequestMapping(value = "{task-id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseDto> updateTask(@PathVariable("task-id") int taskId, @RequestBody TaskDto taskDto) {
        Task task = tasksAndStepsConverter.toTask(taskDto);
        usersServiceFacade.updateTask(task);
        return ResponseBuilder.buildResponsePut(taskDto);
    }

    @RequestMapping(value = "{task-id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDto> removeTask(@PathVariable("task-id") int taskId) {
        usersServiceFacade.removeTask(taskId);
        return ResponseBuilder.buildResponseDelete();
    }

    @RequestMapping(value = "{task-id}/steps.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> getSteps(@PathVariable("task-id") int id) {
        List<Step> steps = usersServiceFacade.getTask(id).getSteps();
        List<StepDto> stepsDto = tasksAndStepsConverter.fromSteps(steps);
        return ResponseBuilder.buildResponseGet(stepsDto);
    }

    @RequestMapping(value = "{task-id}/steps/{step-id}.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> getStep(@PathVariable("task-id") int taskId,
                                               @PathVariable("step-id") int stepId) {
        List<Step> steps = usersServiceFacade.getTask(taskId).getSteps();
        Step step = null;
        for (Step s : steps) {
            if (stepId == s.getId() && taskId == s.getTaskId()) step = s;
        }
        StepDto stepDto = tasksAndStepsConverter.fromStep(step);
        return ResponseBuilder.buildResponseGet(stepDto);
    }

    @RequestMapping(value = "{task-id}/steps.json/filter={finished}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> getStepsByFilter(@PathVariable("task-id") int taskId,
                                                        @PathVariable("finished") boolean finished) {
        List<Step> steps = usersServiceFacade.getTask(taskId).getSteps();
        List<Step> result = new LinkedList<Step>();
        for (Step s : steps) {
            if (finished == s.isFinished() && taskId == s.getTaskId()) result.add(s);
        }
        List<StepDto> stepsDto = tasksAndStepsConverter.fromSteps(result);
        return ResponseBuilder.buildResponseGet(stepsDto);
    }

    @RequestMapping(value = "{task-id}/steps/assignments", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> addStep(@PathVariable("task-id") int taskId, @RequestBody StepDto stepDto) {
        Task task = usersServiceFacade.getTask(taskId);
        List<Step> steps = task.getSteps();
        Step step = tasksAndStepsConverter.toStep(stepDto);
        steps.add(step);
        return ResponseBuilder.buildResponsePut(stepDto);
    }

    @RequestMapping(value = "{task-id}/steps/{step-id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseDto> updateStep(@PathVariable("task-id") int taskId,
                                                  @PathVariable("step-id") int stepId, @RequestBody StepDto stepDto) {
        List<Step> steps = usersServiceFacade.getTask(taskId).getSteps();
        Step step;
        for (int i = 0; i < steps.size(); i++) {
            if (stepId == steps.get(i).getId() && taskId == steps.get(i).getTaskId()) {
                steps.remove(i);
                step = tasksAndStepsConverter.toStep(stepDto);
                steps.add(i, step);
                break;
            }
        }
        return ResponseBuilder.buildResponseGet(stepDto);
    }

    @RequestMapping(value = "{task-id}/steps/{step-id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDto> removeStep(@PathVariable("task-id") int taskId, @PathVariable("step-id") int stepId) {
        List<Step> steps = usersServiceFacade.getTask(taskId).getSteps();
        for (int i = 0; i < steps.size(); i++) {
            if (stepId == steps.get(i).getId() && taskId == steps.get(i).getTaskId()) steps.remove(i);
        }
        return ResponseBuilder.buildResponseDelete();
    }
}
