package com.halisaha.notification.repository;

import com.halisaha.notification.model.Notification;
import com.halisaha.notification.model.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
    List<Notification> findByType(NotificationType type);
    List<Notification> findBySentFalse();
    List<Notification> findByUserIdAndType(Long userId, NotificationType type);
} 