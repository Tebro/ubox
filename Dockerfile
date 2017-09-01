FROM java:8-jre-alpine


ENV SPRING_CLOUD_CONSUL_HOST consul
ENV DATABASE_HOST ubox_db
ENV DATABASE_USER postgres
ENV DATABASE_PASSWORD postgres
ENV DATABASE_NAME postgres
ENV DATABASE_PORT 5432

EXPOSE 8080

ADD build/libs/ubox-0.0.1-SNAPSHOT.jar /app.jar

CMD java -jar /app.jar