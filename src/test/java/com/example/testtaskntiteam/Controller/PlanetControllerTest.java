package com.example.testtaskntiteam.Controller;

import com.example.testtaskntiteam.Entity.Planet;
import com.example.testtaskntiteam.Repository.PlanetRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PlanetControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;


    @MockBean
    private PlanetRepository planetRepository;


    @InjectMocks
    private PlanetController planetController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(planetController)
                .build();
    }

    @Test
     public void save_Planet_OK(){
        var planet = new Planet();
        planet.setPlanetName("Mars");
        BDDMockito.when(planetRepository.save(planet)).thenReturn(planet);
        ResponseEntity<Planet> response = restTemplate.postForEntity("/saveLord", planet, Planet.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }


    @Test
    public void delete_Planet_NOT_FOUND() throws Exception {
        doNothing().when(planetRepository).deleteById(1);

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNotFound());

    }


}


