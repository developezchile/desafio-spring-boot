package com.example.demo.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;
    private Date createdAt;
    private String description;
    private String state;
    private Long userId;

}
