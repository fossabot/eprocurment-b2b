# Create builder stage for build application.
FROM maven:15-jdk-alpine as builder
WORKDIR /app
COPY . /app
# Build maven application
RUN mvn  -DskipTests=true clean package
RUN mv target/*.jar app.jar
# Reduce image size
FROM openjdk:15-jdk-alpine
EXPOSE 8080
WORKDIR /app
COPY --from=builder /app/app.jar /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
#docker build -t maxiplux/livemarket.business.b2bcart .
#docker tag  3501cab4af42 maxiplux/livemarket.business.b2bcart:1.0.5
#docker tag  39d440f82330 maxiplux/livemarket.business.b2bcart:kuerbernetes
#docker push maxiplux/livemarket.business.b2bcart:kuerbernetes

