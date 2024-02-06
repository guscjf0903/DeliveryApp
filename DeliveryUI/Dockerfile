FROM openjdk:17-jdk
WORKDIR ./DeliveryUI

COPY build/libs/DeliveryUI-0.0.1-SNAPSHOT.jar ./ui.jar
EXPOSE 8888

CMD ["java", "-jar", "ui.jar", "--spring.profiles.active=docker"]