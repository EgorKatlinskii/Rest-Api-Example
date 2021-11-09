package com.example.testtaskntiteam.Controller;

import com.example.testtaskntiteam.Entity.Lord;
import com.example.testtaskntiteam.Entity.Planet;
import com.example.testtaskntiteam.Repository.LordRepository;
import com.example.testtaskntiteam.Service.LordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //only if you will use @MockMvc
@ActiveProfiles("test")
class LordControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort //only for port
    private int port;

    @MockBean
    private LordRepository lordRepository;


    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class config {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder().basicAuthentication("sa", "");
        }
    }

    @Before
    public void setup() {

    }

    @Test
    void save_Lord_OK() throws Exception {
        var lord = new Lord();
        lord.setLordName("Oger");
        lord.setLordAge(40);
        // given
        BDDMockito.when(lordRepository.save(lord)).thenReturn(lord);
        ResponseEntity<Lord> response = restTemplate.postForEntity("/saveLord", lord, Lord.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void get_Youngest_Lords_OK() throws Exception {
        var lordsList = new ArrayList<Lord>();

        var lordOne = new Lord();
        lordOne.setLordName("Yan");
        lordOne.setLordAge(10);

        var lordTwo = new Lord();
        lordTwo.setLordName("Oger");
        lordTwo.setLordAge(50);

        lordsList.add(lordOne);
        lordsList.add(lordTwo);
        when(lordRepository.getTheYoungestLords(1))
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

    /*@Test
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
    }*/
}