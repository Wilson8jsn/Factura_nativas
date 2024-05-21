FROM openjdk:17-jdk
ADD target/Factura_nativas-0.0.1-SNAPSHOT.jar /usr/share/app.jar
ENTRYPOINT ["java", "-jar", "/usr/share/app.jar"]
# cambiar appn por security