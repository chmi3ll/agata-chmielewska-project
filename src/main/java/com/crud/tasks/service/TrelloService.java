package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.controller.CreatedTrelloCard;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {

    private static final String SUBJECT = "Tasks: New Trello card";
    @Autowired
    AdminConfig adminConfig;
    @Autowired
    private TrelloClient trelloClient;
    @Autowired
    private SimpleMailService mailService;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCard createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        ofNullable(newCard).ifPresent(card -> mailService.send(new Mail(adminConfig.getAdminMail(),
                SUBJECT, "New card: " + card.getName()
                + "has been created on Trello account", "chmielewska.agata33@gmail.com")));

        return newCard;
    }
}
