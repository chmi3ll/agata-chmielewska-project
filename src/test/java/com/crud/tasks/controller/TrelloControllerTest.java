package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
public class TrelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrelloFacade trelloFacade;

//    @RequestMapping(method = RequestMethod.GET, value = "/getTrelloBoards")
//    public List<TrelloBoardDto> getTrelloBoards() {
//        return trelloFacade.fetchTrelloBoards();
//    }

    @Test
    public void shouldFetchEmptyTrelloBoards() throws Exception {
        //Given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);
        //When & Then
        mockMvc.perform(get("/v1/trello/getTrelloBoards")
                .contextType(MediaType.APPLICATION_JSON))
                .andExpect(status.is(200)) //or isOk()
                .andExpect(jsonPath("$", hasSize(0)));
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/createTrelloCard")
//    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
//        return trelloFacade.createCard(trelloCardDto);

    @Test
    public void createTrelloCard() {
    }
}