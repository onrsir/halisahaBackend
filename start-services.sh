#!/bin/bash

# Eureka Server'ı başlat
cd eureka-server
JAVA_OPTS="-Xmx256m" mvn spring-boot:run &
echo "Eureka Server başlatılıyor..."
sleep 20

# User Service'i başlat
cd ../user-service
JAVA_OPTS="-Xmx512m" mvn spring-boot:run &
echo "User Service başlatılıyor..."
sleep 10

# Field Service'i başlat
cd ../field-service
JAVA_OPTS="-Xmx512m" mvn spring-boot:run &
echo "Field Service başlatılıyor..."
sleep 10

# Reservation Service'i başlat
cd ../reservation-service
JAVA_OPTS="-Xmx512m" mvn spring-boot:run &
echo "Reservation Service başlatılıyor..."
sleep 10

# Payment Service'i başlat
cd ../payment-service
JAVA_OPTS="-Xmx512m" mvn spring-boot:run &
echo "Payment Service başlatılıyor..."
sleep 10

# Notification Service'i başlat
cd ../notification-service
JAVA_OPTS="-Xmx512m" mvn spring-boot:run &
echo "Notification Service başlatılıyor..."
sleep 10

# API Gateway'i başlat
cd ../api-gateway
JAVA_OPTS="-Xmx512m" mvn spring-boot:run &
echo "API Gateway başlatılıyor..."

echo "Tüm servisler başlatıldı!"
echo "Servislerin durumunu kontrol etmek için: http://localhost:8761" 