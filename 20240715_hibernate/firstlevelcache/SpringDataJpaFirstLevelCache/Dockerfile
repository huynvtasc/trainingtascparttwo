# Sử dụng hình ảnh chính thức của OpenJDK làm base image
FROM openjdk:17-jdk-alpine

# Thiết lập thư mục làm việc
WORKDIR /app


# Sao chép file jar vào container
COPY target/SpringDataJpaWithCache-0.0.1-SNAPSHOT.jar /app/app.jar


# Chạy ứng dụng Java
ENTRYPOINT ["java", "-jar", "/app/app.jar"]