package com.example.demo.controllers;

import com.example.demo.dtos.TaskDTO;
import com.example.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public List<TaskDTO> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public TaskDTO findOne(@PathVariable String id) {
        return taskService.findById(Long.parseLong(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@RequestBody TaskDTO dto) {
        return taskService.create(dto);
    }

    @PatchMapping
    public TaskDTO save(@RequestBody TaskDTO dto) {
        return taskService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        taskService.delete(Long.parseLong(id));
    }
}
