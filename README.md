# Proyecto Microservicios - Sistema Financiero

Este repositorio contiene la implementación de un ecosistema de microservicios para la gestión de un sistema financiero, incluyendo los servicios de **Cliente** y **Transacción**.

## 🚀 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Docker & Docker Compose**
- **Karate DSL** (Pruebas de Integración)
- **JUnit 5 & Mockito** (Pruebas Unitarias)
- **PostgreSQL** (Base de Datos)

## 📋 Prerrequisitos

Antes de comenzar, asegúrate de tener instalado:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Maven](https://maven.apache.org/) (opcional, se recomienda usar el wrapper `./mvnw`)

## 🛠️ Ejecución del Proyecto

### 1. Levantar la infraestructura y los microservicios

Para iniciar la base de datos y todos los microservicios configurados en el orquestador, utiliza el siguiente comando desde la raíz del proyecto:

```bash
docker compose up -d --build
```

Este comando:

1. Construirá las imágenes de cada microservicio.
2. Levantará el contenedor de PostgreSQL.
3. Ejecutará los microservicios en segundo plano.

### 2. Ejecución de Pruebas de Integración (Karate)

Para validar el flujo completo de los microservicios (comunicación inter-servicio, persistencia y lógica de negocio) mediante pruebas de integración con Karate, ejecuta:

```bash
mvn test -Dtest=KarateTestRunner
```

### 3. Ejecución de Pruebas en el Microservicio de Transacción

Si deseas ejecutar las pruebas específicamente para el microservicio de **Transacción**, navega a su directorio y utiliza el Maven Wrapper:

```bash
cd microservicio-transaccion
./mvnw test
```
