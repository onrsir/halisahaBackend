package com.halisaha.notification.service;

import com.halisaha.notification.client.UserClient;
import com.halisaha.notification.model.Notification;
import com.halisaha.notification.model.NotificationChannel;
import com.halisaha.notification.model.NotificationType;
import com.halisaha.notification.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserClient userClient;
    private final JavaMailSender mailSender;

    @Transactional
    public Notification createNotification(Notification notification) {
        // Kullanıcı kontrolü
        userClient.getUserById(notification.getUserId());
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public List<Notification> getNotificationsByType(NotificationType type) {
        return notificationRepository.findByType(type);
    }

    public List<Notification> getPendingNotifications() {
        return notificationRepository.findBySentFalse();
    }

    @Async
    @Transactional
    public void sendNotification(Notification notification) {
        try {
            switch (notification.getChannel()) {
                case EMAIL:
                    sendEmailNotification(notification);
                    break;
                case SMS:
                    sendSmsNotification(notification);
                    break;
                case PUSH:
                    sendPushNotification(notification);
                    break;
            }
            
            notification.setSent(true);
            notification.setSentAt(LocalDateTime.now());
            notificationRepository.save(notification);
        } catch (Exception e) {
            notification.setErrorMessage(e.getMessage());
            notificationRepository.save(notification);
            throw new RuntimeException("Failed to send notification: " + e.getMessage());
        }
    }

    private void sendEmailNotification(Notification notification) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        
        // TODO: Kullanıcının email adresini UserClient'dan al
        helper.setTo("user@example.com");
        helper.setSubject(notification.getSubject());
        helper.setText(notification.getContent(), true);
        
        mailSender.send(message);
    }

    private void sendSmsNotification(Notification notification) {
        // TODO: SMS gönderme servisi entegrasyonu
        throw new UnsupportedOperationException("SMS notification not implemented yet");
    }

    private void sendPushNotification(Notification notification) {
        // TODO: Push notification servisi entegrasyonu
        throw new UnsupportedOperationException("Push notification not implemented yet");
    }

    @Transactional
    public void resendFailedNotifications() {
        List<Notification> failedNotifications = notificationRepository.findBySentFalse();
        failedNotifications.forEach(this::sendNotification);
    }
} 