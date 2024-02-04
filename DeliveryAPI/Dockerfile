FROM openjdk:17-jdk
WORKDIR /DeliveryUI

COPY ./DeliveryUI/build/libs/DeliveryAPI-0.0.1-SNAPSHOT.jar ./api.jar
EXPOSE 7777

CMD ["java", "-jar", "api.jar", "--spring.profiles.active=dev"]