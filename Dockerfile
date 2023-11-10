FROM maven:3.6.3-jdk-11
RUN mkdir "kafka-demo"
WORKDIR "kafka-demo"
COPY . .
RUN mvn clean install -Dmaven.test.skip=true
CMD ["java", "-jar", "target/kafka-demo-0.0.1-SNAPSHOT.jar"]