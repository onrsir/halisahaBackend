package com.halisaha.notification.model;

public enum NotificationType {
    RESERVATION_CREATED,      // Rezervasyon oluşturuldu
    RESERVATION_CONFIRMED,    // Rezervasyon onaylandı
    RESERVATION_CANCELLED,    // Rezervasyon iptal edildi
    PAYMENT_COMPLETED,        // Ödeme tamamlandı
    PAYMENT_FAILED,          // Ödeme başarısız
    PAYMENT_REFUNDED,        // Ödeme iade edildi
    REMINDER                 // Hatırlatma
} 