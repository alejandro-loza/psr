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

## Compilación y empaquetado

### Maven
```bash
# Ingresar a la carpeta del proyecto
cd sentenciados_ms

# Compilar proyecto configuración local
mvn clean package -DskipTests -P loc

# Compilar proyecto configuración desarrollo
mvn clean package -DskipTests -P dev

# Compilar proyecto configuración produccion
mvn clean package -DskipTests -P prod
```
> Para Gradle: [Compilación y empaquetado](#gradle)

## Ejecución del Proyecto
```bash
# Ingresar a la carpeta del proyecto
cd sentenciados_ms

# Ejecutar proyecto configuración local
mvn spring-boot:run -DskipTests

# Ejecutar proyecto configuración desarrollo
mvn spring-boot:run -DskipTests -P dev

# Ejecutar proyecto configuración produccion
mvn spring-boot:run -DskipTests -P prod

```
> Para Gradle: [Ejecución del Proyecto](#gradle)

## Ejecución con Docker

```bash
# Para generar una imagen de docker de aamates, puede utilizar el siguiente comando:
docker-compose build

# Para inicializar los servicios del stack de docker utilice el siguiente comando:
docker-compose up -d

# Para generar la imagen e inicializar los servicios del stack de docker
docker-compose up -d --build

```

## Variables de entorno

Las variables de ambiente disponibles en el archivo de configuración (.env) son las siguientes:

| **Nombre**                        | **Descripción**                                        |
| --------------------------------- | ------------------------------------------------------ |
| IMAGE_TAG                         | Nombre del tag de la imagen de Docker generada.        |
| INFO_APPLICATION_ENVIRONMENT      | Nombre del ambiente.                                   |
| SPRING_DATASOURCE_URL             | Cadena de conexión a la base de datos.                 |
| SPRING_DATASOURCE_USERNAME        | Usuario para conexión a PostgreSQL.                    |
| SPRING_DATASOURCE_PASSWORD        | Contraseña para conexión a PostgreSQL.                 |
| SPRING_DATASOURCE_DRIVERCLASSNAME | Nombre de clase para conectarse a PostgreSQL.          |
| SPRING_LIQUIBASE_ENABLED          | Condicional para habilitar liquibase.                  |
| MANAGEMENT_INFO_GIT_MODE          | Mode para mostrar la información del Git del proyecto. |

Ejemplo:

```bash
IMAGE_TAG=infotec
INFO_APPLICATION_ENVIRONMENT=INFOTEC

SPRING_DATASOURCE_URL=jdbc:postgresql://postgresdb:5432/sicosel?currentSchema=rls
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
SPRING_DATASOURCE_DRIVERCLASSNAME=org.postgresql.Driver
SPRING_LIQUIBASE_ENABLED=false

MANAGEMENT_INFO_GIT_MODE=FULL
```

> Será necesario configurar el dominio **postgresdb** en el **/etc/hosts** de la máquina donde se ejeucta el contenedor, para configurar la IP de la base de datos.

## Ejecución Pruebas

```bash
# Ejecución de las pruebas unitarias
mvn test
```

# Modelo de Datos

> Esquema de base de base de datos  "rls"


# Gradle
## Compilación y empaquetado
```bash
# Ingresar a la carpeta del proyecto
cd sentenciados_ms

# Compilar proyecto configuración local
gradle clean bootJar -P loc

# Compilar proyecto configuración desarrollo
gradle clean bootJar -P dev

# Compilar proyecto configuración produccion
gradle clean bootJar -P prod
```
## Ejecución del Proyecto
```bash
# Ingresar a la carpeta del proyecto
cd sentenciados_ms

# Ejecutar proyecto configuración local
gradle bootRun

# Ejecutar proyecto configuración desarrollo
gradle bootRun -P dev

# Ejecutar proyecto configuración produccion
gradle bootRun -P prod
```