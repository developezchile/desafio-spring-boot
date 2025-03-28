package com.example.demo;


import com.example.demo.controllers.exceptions.TaskNotFoundException;
import com.example.demo.dtos.TaskDTO;
import com.example.demo.entities.Task;
import com.example.demo.entities.TaskState;
import com.example.demo.entities.TaskStateDef;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.services.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void whenGetAll_thenOK() {
        Iterable<Task> tasks = Arrays.asList(Task.builder().taskState(TaskState.builder().taskState(TaskStateDef.OPEN).build()).build());
        given(taskRepository.findAll()).willReturn(tasks);
        List<TaskDTO> expected = taskService.findAll();
        assertEquals(expected.get(0).getDescription(), tasks.iterator().next().getDescription());
    }

    @Test
    public void givenObject_whenCreate_thenReturnSaved()  {
        Task task = createTask();
        TaskDTO expected = TaskDTO.builder().description("").state("OPEN").build();
        when(taskRepository.save(ArgumentMatchers.any(Task.class))).thenReturn(task);
        TaskDTO created = taskService.create(expected);
        assertEquals(created.getDescription(), task.getDescription());
    }

    @Test
    public void whenGivenId_shouldReturn_ifFound() {
        Task task = createTask();
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
        TaskDTO expected = taskService.findById(task.getId());
        assertEquals(expected.getDescription(), task.getDescription());
    }

    @Test
    public void should_throw_exception_when_data_no_exist()  {
        Task task = new Task();
        task.setId(null);
        given(taskRepository.findById(any())).willReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskService.findById(task.getId()));
    }

    private Task createTask() {
        return Task.builder()
                .id(1L)
                .description("")
                .createdAt(new Date()).taskState(TaskState.builder().taskState(TaskStateDef.OPEN).build())
                .build();
    }
}