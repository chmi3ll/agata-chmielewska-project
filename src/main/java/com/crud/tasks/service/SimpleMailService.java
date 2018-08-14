package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleMailService {

    private static final Logger LOGGER = LoggerFactory.getLogger
            (SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    @Autowired
    TaskRepository taskRepository;

    public void send(final Mail mail) {
        LOGGER.info("Starting email preparation...");
        try {
//            SimpleMailMessage mailMessage = createMailMessage(mail);
//            javaMailSender.send(mailMessage);
//            javaMailSender.send(createMimeMessage(mail));
            javaMailSender.send(createMimeMessageFromDb(mail));
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ",
                    e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
        };
    }

    private MimeMessagePreparator createMimeMessageFromDb(final Mail mail) {
        long size = taskRepository.count();

        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.getCountTasksEmail(mail.getMessage()), true);
        };
    }

        private SimpleMailMessage createMailMessage(final Mail mail) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(mail.getMailTo());
            mailMessage.setSubject(mail.getSubject());
            mailMessage.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()));
            return mailMessage;
        }

//    private SimpleMailMessage createMailMessage(final Mail mail) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(mail.getMailTo());
//        mailMessage.setSubject(mail.getSubject());
//        mailMessage.setText(mail.getMessage());
//        if (mail.getToCc() != null) {
//            mailMessage.setCc(mail.getToCc());
//        } else return mailMessage;
//        return mailMessage;
//    }
}
