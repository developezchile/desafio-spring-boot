package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task_state")
public class TaskState {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "taskState")
    private Task state;

    @Enumerated(EnumType.STRING)
    private TaskStateDef taskState;
}
