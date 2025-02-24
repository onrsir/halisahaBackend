package com.halisaha.payment.model;

public enum PaymentStatus {
    PENDING,        // Ödeme bekliyor
    PROCESSING,     // İşleniyor
    COMPLETED,      // Tamamlandı
    FAILED,         // Başarısız
    REFUNDED       // İade edildi
} 