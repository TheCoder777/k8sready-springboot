# using multistage docker build
# ref: https://docs.docker.com/develop/develop-images/multistage-build/
# from: https://stackoverflow.com/questions/61108021/gradle-and-docker-how-to-run-a-gradle-build-within-docker-container

# temporary container to build in a clean environment using gradle
FROM gradle:latest AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME/

RUN gradle clean build || return 0

# final container
FROM openjdk:17 AS SPRING_DEPLOYMENT_IMAGE
# Don't forget to change the version number in the build.gradle and in the YAML config too
ENV ARTIFACT_NAME=k8sready-springboot-0.0.1.jar
ENV APP_HOME=/usr/app

WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .

# Don't forget to change the server port in the application.properties and in the YAML config too
EXPOSE 8080
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}