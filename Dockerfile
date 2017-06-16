FROM java:8
EXPOSE 8080
ADD /target/SpringMysql.jar SpringMysql.jar
ENTRYPOINT ["java","-jar","SpringMysql.jar"]