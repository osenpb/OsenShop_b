# OsenShop 🛒

OsenShop es una aplicación e‑commerce backend desarrollada con Java 21 y Spring Boot, enfocada en buenas prácticas de arquitectura, seguridad y diseño orientado a portafolio profesional.

El proyecto implementa autenticación basada en JWT, manejo de DTOs, excepciones personalizadas, filtros de Spring Security y paginación, combinando Clean Architecture para el módulo de autenticación y una arquitectura feature‑based para el resto del dominio.

## 🚀 Tecnologías principales

- Java 21

- Spring Boot

- Spring Security + JWT + Nimbus JOSE

- Spring Data JPA

- Hibernate

- REST API

- Maven

Base de datos relacional (configurable)

## 🔐 Seguridad y Autenticación

El módulo de autenticación sigue principios de Clean Architecture, separando claramente:

Dominio: reglas de negocio y modelos centrales

Aplicación: casos de uso (login, registro, validación)

Infraestructura: implementación de JWT, repositorios y seguridad

Entrypoints: controladores REST

## Características

- Autenticación y autorización mediante JWT

- Filtros personalizados de Spring Security

- Separación de responsabilidades entre capas

- Manejo centralizado de errores de seguridad

## 🧩 Arquitectura del proyecto
#### Enfoque híbrido

- Auth → Clean Architecture

- Resto del dominio → Arquitectura feature‑based

- Esto permite:

- Máxima claridad en un módulo crítico como autenticación

- Escalabilidad y organización por contexto funcional en el resto del sistema

Ejemplo de estructura seguida:

```
src/main/java/com/osenshop
│
├── auth
│   ├── domain
│   ├── application
│   ├── infrastructure
│   └── controller
│
├── product
│   ├── ProductController
│   ├── ProductService
│   ├── ProductRepository
│   ├── ProductEntity
│   └── dto
│
├── order
│   ├── OrderController
│   ├── OrderService
│   └── ...
│
├── shared
│   ├── exception
│   ├── pagination
│   └── response
│
└── security
├── JwtFilter
├── JwtProvider
└── SecurityConfig
📦 DTOs y Mapeo
```

El proyecto utiliza DTOs para:

- Evitar exponer entidades directamente

- Controlar el contrato de la API

- Facilitar validaciones y evolución del modelo

Incluye:

- DTOs de request y response

- Conversión explícita entre entidades y DTOs

### ⚠️ Manejo de Excepciones

- Excepciones personalizadas por dominio

- @ControllerAdvice para manejo global

- Respuestas de error claras y consistentes

Ejemplo:
```json
{
"timestamp": "2026-01-15T10:30:00",
"status": 404,
"error": "RESOURCE_NOT_FOUND",
"message": "Producto no encontrado"
}
```
### 📄 Paginación

- La API implementa paginación usando Pageable de Spring:

- Soporte para page, size y sort

- Respuestas optimizadas para listados grandes

- Compatible con frontend moderno (Angular, React, etc.)

📡 Endpoints principales (ejemplo)
```
POST   /api/auth/login
POST   /api/auth/register


GET    /api/products?page=0&size=10
GET    /api/products/{id}
POST   /api/products


GET    /api/orders
POST   /api/orders
```

## 🧪 Enfoque del proyecto

OsenShop está diseñado como:

Proyecto demostrativo de backend profesional

Base sólida para un e‑commerce real

Ejemplo de buenas prácticas en Spring Boot moderno

Especial énfasis en:

- Arquitectura limpia

- Seguridad

- Escalabilidad

- Legibilidad del código

## 📌 Próximas mejoras

Tests unitarios y de integración

Documentación con OpenAPI / Swagger

Roles y permisos avanzados

Integración con frontend

