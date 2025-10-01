package com.hauxy.turistguide2.controller;

import com.hauxy.turistguide2.model.TouristAttraction;
import com.hauxy.turistguide2.repository.Tag;
import com.hauxy.turistguide2.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristController.class)
class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService touristService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }




    // Aftalt med Ian at vi ser på DispatcherServlet fejl på fredag da det er en større fejl
    @Test
    void shouldGetAttractions() throws Exception {
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"));
    }

   /* @Test
    void shouldAddAttraction() throws Exception {
        List<Tag> tagList = new ArrayList<>();
        tagList.add(Tag.DYRT);
        tagList.add(Tag.KONCERT);
        tagList.add(Tag.BØRNEVENLIG);

        mockMvc.perform(post("/attractions/add")
                .param("name", "Parken")
                .param("description", "Football Stadium")
                .param("tags",  "DYRT", "KONCERT", "BØRNEVENLIG"))
                .andExpect(status().is3xxRedirection());
//                .andExpect(view().name("redirect:/attractions/attractionList"));

        ArgumentCaptor<TouristAttraction> captor = ArgumentCaptor.forClass(TouristAttraction.class);
        verify(touristService).addTouristAttraction(captor.capture());

        TouristAttraction captured = captor.getValue();
        assertEquals("Parken", captured.getName());
        assertEquals("Football Stadium", captured.getDescription());
        assertEquals(tagList, captured.getTags());
    }
    */
}
