package ru.leo.springdemo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.leo.springdemo.entity.Greeting;
import ru.leo.springdemo.service.GreetingServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {

    @MockBean
    GreetingServiceImpl service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void webMvcPostTest() throws Exception {
        given(service.createGreeting(ArgumentMatchers.any())).willReturn(new Greeting("LeoMax"));
        mockMvc.perform(post("/greeting")
                .contentType(MediaType.APPLICATION_JSON).content("{\"id\": \"1\", \"content\":\"LeoMax\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", is("Здравствуй, ученик, LeoMax!")));
    }

    @Test
    public void webMvcPutTest() throws Exception {
        given(service.updateGreeting(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).willReturn(new Greeting("LeoMax"));
        mockMvc.perform(put("/greeting/1")
                .contentType(MediaType.APPLICATION_JSON).content("{\"id\": \"1\", \"content\":\"LeoMax\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", is("Здравствуй, ученик, LeoMax!")));
    }

    @Test
    public void webMvcGetTest() throws Exception {
        Greeting greeting = new Greeting("Leo");
        greeting.setId(1L);
        given(service.findGreetingByID(ArgumentMatchers.anyLong())).willReturn(greeting);
        mockMvc.perform(get("/greeting/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void webMvcGetAllTest() throws Exception {
        Greeting greeting1 = new Greeting("LeoMax 1");
        Greeting greeting2 = new Greeting("LeoMax 2");
        List<Greeting> grList = new ArrayList<>();
        grList.add(greeting1);
        grList.add(greeting2);
        given(service.findAllGreetings()).willReturn(grList);
        mockMvc.perform(get("/greetings"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
        ;
    }
}
