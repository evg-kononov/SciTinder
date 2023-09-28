FROM maven:3.8.3-openjdk-17

WORKDIR /spring-boot-api
COPY . .
RUN mvn clean install -Dmaven.test.skip=true

CMD mvn spring-boot:run