package com.hauxy.turistguide2.controller;

import com.hauxy.turistguide2.model.TouristAttraction;
import com.hauxy.turistguide2.repository.Tag;
import com.hauxy.turistguide2.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

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
    private TouristService touristService1;

    @BeforeEach
    void setUp() {
    }

    @Mock
    private TouristService service;

    @Mock
    private Model model;

    @InjectMocks
    private TouristController controller;


    @AfterEach
    void tearDown() {
    }

    @Test
    void saveAttraction_returnsFormWhenNameEmpty() {
        TouristAttraction t = new TouristAttraction();
        when(service.saveAttraction(t)).thenReturn(false);

        String result = controller.saveAttraction(t, model);

        assertEquals("createAttraction", result);
        verify(model).addAttribute(eq("error"), anyString());
    }
    @Test
    void getAttractionTags_returnsTagsViewAndAddsModelAttributes() {
        TouristAttraction attraction = new TouristAttraction();
        when(service.getAttraction("Rome")).thenReturn(attraction);

        String view = controller.getAttractionTags("Rome", model);

        verify(service).getAttraction("Rome");
        verify(model).addAttribute("attraction", attraction);
        assertEquals("tags", view);
    }
    @Test
    void getAttractions_addsListToModelAndReturnsView() {
        List<TouristAttraction> attractions = List.of(new TouristAttraction());
        when(service.getTouristAttractions()).thenReturn(attractions);

        String viewName = controller.getAttractions(model);

        verify(service).getTouristAttractions();
        verify(model).addAttribute("listofattractions", attractions);
        assertEquals("attractionList", viewName);
    }


    // Aftalt med Ian at vi ser på DispatcherServlet fejl på fredag da det er en større fejl
    // Fixed
    @Test
    void shouldGetAttractions() throws Exception {
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"));
    }

}
