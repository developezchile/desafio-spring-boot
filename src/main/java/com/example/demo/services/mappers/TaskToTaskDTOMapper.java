package com.example.demo.services.mappers;

import com.example.demo.dtos.TaskDTO;
import com.example.demo.entities.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskToTaskDTOMapper {

    public TaskDTO mapper(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .createdAt(task.getCreatedAt())
                .description(task.getDescription())
                .state(task.getTaskState().getTaskState().toString())
                .userId(task.getUserAdmin().getId())
                .build();
    }

    public List<TaskDTO> mapperList(Iterable<Task> tasks) {
        List<TaskDTO> taskDTOS = new ArrayList<>();
        tasks.forEach(e -> {
            TaskDTO dto = TaskDTO.builder()
                    .id(e.getId())
                    .state(e.getTaskState().getTaskState().toString())
                    .description(e.getDescription())
                    .createdAt(e.getCreatedAt())
                    .userId(e.getUserAdmin().getId())
                    .build();
            taskDTOS.add(dto);
        });
        return taskDTOS;
    }

}
