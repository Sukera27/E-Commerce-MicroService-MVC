# E-Commerce Microservices Platform 🚀

¡Bienvenido al repositorio del proyecto E-Commerce Microservices! Esta es una plataforma robusta, escalable y moderna construida bajo una arquitectura de microservicios.

## 🏗 Arquitectura del Sistema

El proyecto está compuesto por varios microservicios independientes que se comunican a través de eventos y llamadas de red.

### Microservicios Principales:
* **API Gateway (`apigetway`)**: El punto de entrada único para todos los clientes, enrutando las peticiones a los servicios correspondientes.
* **Products Service (`products-service`)**: Gestiona el catálogo de productos.
* **Orders Service (`orders-service`)**: Administra el ciclo de vida de las compras y pedidos de los usuarios.
* **Inventory Service (`inventory-service`)**: Controla el stock y disponibilidad de los productos.
* **Notification Server (`notification-server`)**: Servicio encargado de enviar notificaciones (ej. confirmaciones de pedido) de forma asíncrona.

## 🛠 Stack Tecnológico

El proyecto utiliza un conjunto moderno de herramientas tanto para desarrollo como para su despliegue en producción.

### Backend y Datos
* **Bases de Datos**: PostgreSQL (Instancias separadas por microservicio para mantener el aislamiento de datos).
* **Mensajería / Eventos**: Apache Kafka & Zookeeper para la comunicación asíncrona entre servicios.
* **Seguridad e Identidad**: Keycloak para la autenticación y autorización (OAuth2 / OIDC).

### Observabilidad y Calidad
* **Métricas y Monitoreo**: Prometheus y Grafana.
* **Trazabilidad Distribuida**: Zipkin.
* **Calidad de Código**: SonarQube integrado.

### Infraestructura y CI/CD (Cloud Native)
* **Contenedores**: Docker y Docker Compose para desarrollo local.
* **Orquestación**: Kubernetes (preparado para Azure Kubernetes Service - AKS).
* **Infraestructura como Código (IaC)**: Terraform (`/infrastructure`).
* **GitOps / Entrega Continua**: Argo CD (manifiestos en `/manifests`).

## 🚀 Entorno de Desarrollo Local

Para levantar el entorno de desarrollo con todas sus dependencias localmente, asegúrate de tener Docker y Docker Compose instalados.

1. Clona el repositorio:
   ```bash
   git clone <url-del-repositorio>
   cd microservice
   ```

2. Levanta la infraestructura base (Bases de datos, Kafka, Keycloak, Observabilidad):
   ```bash
   docker-compose up -d
   ```
   > **Nota**: Este comando levantará todas las dependencias necesarias como PostgreSQL (inventory, orders, products, keycloak), Keycloak, SonarQube, Kafka, Zipkin, Prometheus y Grafana.

## ☁️ Despliegue en Cloud (Azure AKS)

El proyecto está preparado para desplegarse en Azure utilizando las mejores prácticas.

1. **Terraform**: La carpeta `/infrastructure` contiene los scripts de Terraform para aprovisionar el clúster de AKS y otros recursos en la nube.
2. **Kubernetes & Argo CD**: La carpeta `/manifests` contiene todos los recursos necesarios de Kubernetes para desplegar la aplicación siguiendo un enfoque GitOps utilizando Argo CD.

*Si tienes problemas con las zonas en Azure, te recomendamos probar creando una nueva cuenta de Azure o seleccionando una región diferente (como `northeurope` o `westeurope`) en tus variables de Terraform.*

## 📈 Servicios Exuestos (Local)

* **Keycloak**: `http://localhost:8181`
* **SonarQube**: `http://localhost:9000`
* **Grafana**: `http://localhost:3000`
* **Prometheus**: `http://localhost:9090`
* **Zipkin**: `http://localhost:9411`
* **Kafka**: puerto `9092`
