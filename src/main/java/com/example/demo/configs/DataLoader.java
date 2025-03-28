package com.example.demo.configs;

import com.example.demo.entities.Task;
import com.example.demo.entities.TaskState;
import com.example.demo.entities.TaskStateDef;
import com.example.demo.entities.UserAdmin;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.repositories.UserAdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    private final TaskRepository taskRepository;
    private final UserAdminRepository userAdminRepository;

    public DataLoader(TaskRepository taskRepository, UserAdminRepository userAdminRepository) {
        this.taskRepository = taskRepository;
        this.userAdminRepository = userAdminRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Load initial data into the database
        UserAdmin userAdmin = UserAdmin.builder()
                .email("juan@user.com")
                .fullName("Juan Doe")
                .password("$2a$10$mGym3wauAIwiQSX450O2punGUVWnilp6l14hfCq8cJ9qX94CEJH7S")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        userAdminRepository.save(userAdmin);

        Task task = Task.builder()
                .description("task_one")
                .createdAt(new Date())
                .taskState(TaskState.builder().taskState(TaskStateDef.OPEN).build())
                .userAdmin(userAdmin)
                .build();
        taskRepository.save(task);

    }
}
