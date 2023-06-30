FROM maven:3.8.3-openjdk-17
RUN mkdir /app
ADD . /app
WORKDIR /app
EXPOSE 8080
RUN mvn install
CMD ["mvn", "spring-boot:run"]