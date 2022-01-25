FROM openjdk:8
ADD /target/docker-spring.jar docker-spring.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "docker-spring.jar"]
