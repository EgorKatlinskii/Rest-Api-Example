package com.example.testtaskntiteam.Controller;

import com.example.testtaskntiteam.Entity.Lord;
import com.example.testtaskntiteam.Entity.Planet;
import com.example.testtaskntiteam.Service.LordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
/*@WebMvcTest(LordController.class)*/
class LordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    public LordService lordService;

    @Test
    void saveLord() throws Exception {
        var lord = new Lord();
        lord.setLordName("Oger");
        lord.setLordAge(40);

        this.mockMvc
                .perform(post("/saveLord")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lord))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lordName").value("Oger"))
                .andExpect(jsonPath("$.lordAge").value(40))
                .andExpect(jsonPath("$.lordId").isNotEmpty())
                .andReturn();
        verify(lordService).saveLord(lord);
    }

    @Test
    void getYoungestLords() throws Exception {
        var lordsList = new ArrayList<Lord>();

        var lordOne = new Lord();
        lordOne.setLordName("Yan");
        lordOne.setLordAge(10);

        var lordTwo = new Lord();
        lordTwo.setLordName("Oger");
        lordTwo.setLordAge(50);

        lordsList.add(lordOne);
        lordsList.add(lordTwo);
        when(lordService.getTheYoungestLords(1))
                .thenReturn((List<Lord>) lordsList);
        var count = 1;
        this.mockMvc
                .perform(get("/getYoungestLords/"+count)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].lordAge", is(10)));
    }

    @Test
    void getIdlers() throws Exception {

        var planet = new Planet();
        planet.setPlanetName("Mars");


        var lordOne = new Lord();
        lordOne.setLordName("Yan");
        lordOne.setLordAge(10);
        lordOne.setListPlanets((List<Planet>) planet);

        var lordTwo = new Lord();
        lordTwo.setLordName("Oger");
        lordTwo.setLordAge(50);

        this.mockMvc
                .perform(get("/getIdlers")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].lordAge", is(10)));
    }

    @Test
    void getLords() {
    }
}