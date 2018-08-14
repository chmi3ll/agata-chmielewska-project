package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Privides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye", "see you later!");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_goal", adminConfig.getCompanyGoal());
        context.setVariable("company_email", adminConfig.getCompanyEmail());
        context.setVariable("company_phone", adminConfig.getCompanyPhone());
        context.setVariable("preview", message.substring(10,15).concat("..."));
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String getCountTasksEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("nice_url", "http://localhost:8888/tasks_frontend/dupa.html");
        context.setVariable("button", "look here!");
        context.setVariable("goodbye", "see you next week");
        return templateEngine.process("mail/count-tasks-in-DB-scheduled", context);
    }
}
