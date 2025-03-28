package com.example.demo.services.mappers;

import com.example.demo.dtos.TaskDTO;
import com.example.demo.entities.Task;
import com.example.demo.entities.TaskState;
import com.example.demo.entities.TaskStateDef;
import com.example.demo.entities.UserAdmin;

import java.util.Date;

public class TaskDTOToTaskMapper {

    public Task mapper (TaskDTO taskDTO, UserAdmin userAdmin) {
        return Task.builder()
                .id(taskDTO.getId())
                .description(taskDTO.getDescription())
                .createdAt(taskDTO.getCreatedAt()==null?new Date():taskDTO.getCreatedAt())
                .taskState(TaskState.builder().taskState(TaskStateDef.valueOf(taskDTO.getState())).build())
                .userAdmin(userAdmin)
                .build();

    }
}
