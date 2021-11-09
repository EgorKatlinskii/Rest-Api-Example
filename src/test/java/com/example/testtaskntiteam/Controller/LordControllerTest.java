package com.example.testtaskntiteam.Controller;

import com.example.testtaskntiteam.Entity.Lord;
import com.example.testtaskntiteam.Entity.Planet;
import com.example.testtaskntiteam.Repository.LordRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LordControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @MockBean
    private LordRepository lordRepository;


    @Autowired
    private MockMvc mockMvc;


    @Before
    public void setup() {

    }

    @Test
    void save_Lord_OK() {
        var lord = new Lord();
        lord.setLordName("Oger");
        lord.setLordAge(40);
        BDDMockito.when(lordRepository.save(lord)).thenReturn(lord);
        ResponseEntity<Lord> response = restTemplate.postForEntity("/saveLord", lord, Lord.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void get_Youngest_Lords_OK() throws Exception {

        var listLords= Arrays.asList(new Lord("Yan",10),
                new Lord("Oger",50));

        when(lordRepository.getTheYoungestLords(1))
                .thenReturn((List<Lord>) listLords);
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
    void get_Idlers_OK() throws Exception {

        var listPlanet = Collections.singletonList(new Planet("Mars"));
        var listIdlers = Collections.singletonList(new Lord("Oger", 50));
        when(lordRepository.getIdlers())
                .thenReturn(listIdlers);

        this.mockMvc
                .perform(get("/getIdlers")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].listPlanets").isEmpty() );
    }
    @Test
    void find_AllLords_OK() throws Exception {
        var listLords= Arrays.asList(new Lord("Yan",10),
                new Lord("Oger",50));
        when(lordRepository.findAll()).thenReturn(listLords);
        this.mockMvc
                .perform(get("/getLords")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].lordId").isEmpty())
                .andExpect(jsonPath("$[0].lordName", is("Yan")))
                .andExpect(jsonPath("$.[0].listPlanets").isEmpty())
                .andExpect(jsonPath("$[1].lordId").isEmpty())
                .andExpect(jsonPath("$[1].lordName", is("Oger")))
                .andExpect(jsonPath("$.[1].listPlanets").isEmpty());
        verify(lordRepository, times(1)).findAll();
    }
}