# sentenciados_ms

El API provee los servicios que requiere los sentenciados en libertad

# Especificaciones Técnicas

## Tecnologías Implementadas y Versiones



- Spring Boot 2.6
- Java 11
- Maven
- Gradle

## Instalación

```bash
# clonar repositorio
git clone https://gitlab.dads.infotec.mx/oadprs/sicosel/sentenciados_ms.git

# Ingresar a la carpeta del proyecto
cd sentenciados_ms

# Instalar dependencias del proyecto con Maven
mvn install

```

## Variables de Entorno

```yml
spring:
  jpa:
    generate-ddl=true:
    hibernate:
      ddl-auto=create-drop:h2:
    console:
      enabled: true
      path: /h2
  datasource:
    platform: postgres
    url: jdbc:postgresql://localhost:5432/sicosel?currentSchema=rls
    username:  postgres
    password:  postgres
    driverClassName: org.postgresql.Driver
  liquibase:
    change-log: 'classpath:db/databaseChangeLog.yaml'
```

## Ejecución del Proyecto

```bash
# Ingresar a la carpeta del proyecto
cd sentenciados_ms

# Ejecutar aplicacion
mvn spring-boot:run -DskipTests

# Generar .jar
mvn package
```

## Ejecución Pruebas

```bash
mvn test
```

# Modelo de Datos

> Esquema de base de base de datos  "rls"
