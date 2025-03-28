package com.example.demo.services;

import com.example.demo.dtos.TaskDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> findAll();
    TaskDTO findById(Long id);
    TaskDTO create(TaskDTO user);
    TaskDTO save(TaskDTO user);
    void delete(Long id);
}
