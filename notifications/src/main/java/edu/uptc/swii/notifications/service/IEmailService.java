package edu.uptc.swii.notifications.service;

import jakarta.mail.MessagingException;

import java.io.File;

public interface IEmailService {

    void sendEmail(String toUser, String subject, String message) throws MessagingException;

    void sendEmailWithFile(String[] toUser, String subject, String message, File file);
}