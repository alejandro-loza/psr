# nuestra imagen base para compilar
FROM maven:3.6.3-jdk-11 as maven

# establecer carpeta de trabajo
WORKDIR /workspace

# establecer zona horaria
ENV TZ America/Mexico_City

# copiar otros archivos del proyecto
COPY pom.xml pom.xml
COPY .git .git
COPY .gitignore .gitignore

# construir todas las dependencias
RUN mvn dependency:go-offline -B

# copiar los archivos del proyecto
COPY src src

# construir el artefacto
RUN mvn clean package -DskipTests

# nuestra imagen base final
FROM openjdk:11-jre-slim

# establecer carpeta de trabajo
WORKDIR /app

# establecer zona horaria
ENV TZ America/Mexico_City

# copiar el artefacto construido en la imagen maven
COPY --from=maven /workspace/target /app

# configurar el comando de inicio para ejecutar el artefacto
ENTRYPOINT ["java","-jar","sentenciados_ms.jar"]