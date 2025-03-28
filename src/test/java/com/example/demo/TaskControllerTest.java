package com.example.demo;

import com.example.demo.dtos.TaskDTO;
import com.example.demo.entities.Task;
import com.example.demo.services.JwtService;
import com.example.demo.services.TaskServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DemoApplication.class)
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskServiceImpl taskService;

    @InjectMocks
    private JwtService jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String jwtToken  = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuQHVzZXIuY29tIiwiaWF0IjoxNzQzMTE1NTM2fQ.7T0eDMSy-xaTvzc35ZnJVXRs_1flsl2dgs98fp_A-HE";

    Iterable<Task> allTasks;
    Task task;
    TaskDTO response;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(jwtUtil, "secretKey", "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b");
    }

    @Test
    public void givenToken_whenGetSecureRequest_thenOK() throws Exception {

        mvc.perform(get("/api/task")
            .header("Authorization", "Bearer " + jwtToken)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .with(SecurityMockMvcRequestPostProcessors.jwt())
        )
        .andExpect(status().isOk() );
    }


    @Test
    public void listAll_whenGetMethod()
            throws Exception {

        TaskDTO taskDTO = TaskDTO.builder()
                .id(1L)
                .description("")
                .createdAt(new Date())
                .state("OK")
                .build();
        List<TaskDTO> allTasks = List.of(taskDTO);

        given(taskService.findAll()).willReturn(allTasks);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/task")
                .with(SecurityMockMvcRequestPostProcessors.jwt());

        ResultActions result = mvc.perform(request);

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is( ((List)allTasks).size()  ) ));

    }

    @Test
    public void saveTest() throws Exception{
        TaskDTO taskDTO = TaskDTO.builder()
                .id(1L)
                .description("")
                .createdAt(new Date())
                .state("OK")
                .build();

        given(taskService.create(taskDTO)).willReturn(response);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/task")
                .content(objectMapper.writeValueAsString(taskDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwtToken)
                .with(SecurityMockMvcRequestPostProcessors.jwt());

        ResultActions response = mvc.perform(request);

        response.andDo(print()).
                andExpect(status().isCreated());
    }
}
