#!/bin/bash

# Eureka Server'ı başlat
cd eureka-server
mvn spring-boot:run &
echo "Eureka Server başlatılıyor..."
sleep 20

# User Service'i başlat
cd ../user-service
mvn spring-boot:run &
echo "User Service başlatılıyor..."
sleep 10

# Field Service'i başlat
cd ../field-service
mvn spring-boot:run &
echo "Field Service başlatılıyor..."
sleep 10

# Reservation Service'i başlat
cd ../reservation-service
mvn spring-boot:run &
echo "Reservation Service başlatılıyor..."
sleep 10

# Payment Service'i başlat
cd ../payment-service
mvn spring-boot:run &
echo "Payment Service başlatılıyor..."
sleep 10

# Notification Service'i başlat
cd ../notification-service
mvn spring-boot:run &
echo "Notification Service başlatılıyor..."
sleep 10

# API Gateway'i başlat
cd ../api-gateway
mvn spring-boot:run &
echo "API Gateway başlatılıyor..."

echo "Tüm servisler başlatıldı!"
echo "Servislerin durumunu kontrol etmek için: http://localhost:8761" 