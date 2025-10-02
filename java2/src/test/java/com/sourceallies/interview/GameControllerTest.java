package com.sourceallies.interview;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sourceallies.interview.controller.GameController;
import com.sourceallies.interview.model.Game;
import com.sourceallies.interview.service.GameService;

@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // @Autowired
    // private ObjectMapper objectMapper;

    @MockBean
    private GameService gameService;

    @Test
    public void testCreateGame() throws Exception {
        Game game = new Game();
        game.setId(1L);

        when(gameService.createGame()).thenReturn(game);

        mockMvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }
}
