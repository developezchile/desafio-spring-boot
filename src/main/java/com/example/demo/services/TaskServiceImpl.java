package com.example.demo.services;

import com.example.demo.controllers.exceptions.TaskNotFoundException;
import com.example.demo.dtos.TaskDTO;
import com.example.demo.entities.Task;
import com.example.demo.entities.UserAdmin;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.services.mappers.TaskDTOToTaskMapper;
import com.example.demo.services.mappers.TaskToTaskDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    TaskToTaskDTOMapper taskToTaskDTOMapper = new TaskToTaskDTOMapper();
    TaskDTOToTaskMapper taskDTOToTaskMapper = new TaskDTOToTaskMapper();
    @Override
    public List<TaskDTO> findAll() {
        return taskToTaskDTOMapper.mapperList(taskRepository.findAll());
    }

    @Override
    public TaskDTO findById(Long id) {
        Optional<Task> opt = taskRepository.findById(id);
        if (opt.isEmpty())
            throw new TaskNotFoundException("Task not exists");
        else
            return taskToTaskDTOMapper.mapper( taskRepository.findById(id).get() );
    }

    @Override
    public TaskDTO create(TaskDTO taskDTO) {
        UserAdmin userAdmin = (UserAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = taskDTOToTaskMapper.mapper(taskDTO, userAdmin);
        return taskToTaskDTOMapper.mapper(taskRepository.save(task));
    }

    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        UserAdmin userAdmin = (UserAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = taskDTOToTaskMapper.mapper(taskDTO, userAdmin);
        return taskToTaskDTOMapper.mapper(taskRepository.save(task));
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
