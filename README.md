# Reto Técnico – Control de Inventarios
## Objetivo
Desarrollar una pequeña aplicación que permita realizar el control de inventarios (stock) de una empresa, donde los bienes se organizan en 3 categorías.
La aplicación debe permitir dar de alta y de baja bienes, manejar operaciones por lote, mantener un histórico de movimientos y exponer endpoints REST para ser consumidos por un frontend.

El objetivo del reto es evaluar criterio técnico, diseño, buenas prácticas, pruebas y arquitectura.

# Alcance Funcional
## Reglas del dominio
Existen 3 categorías de bienes.
Una categoría puede contener varios tipos de bienes.
Un bien puede estar:
Activo (disponible)
Dado de baja (no disponible)
Se debe registrar el histórico de movimientos de los bienes:
Alta individual
Alta por lote
Baja individual
Baja por lote
No se debe eliminar información de forma física sin justificación (se recomienda manejo por estado).

# Requerimientos de API (Backend)
La aplicación Spring Boot debe exponer endpoints REST para:

Crear y actualizar categorías
Crear un nuevo bien y asignarlo a una categoría
Recuperar el listado de bienes con su detalle (activos y dados de baja)
Recuperar cuántos bienes disponibles existen en cada categoría
Recuperar las categorías existentes con sus respectivos bienes
Dada una categoría, recuperar:
Cantidad de bienes disponibles
Cantidad de bienes dados de baja
Ingresar un lote de nuevos bienes
Dar de baja un bien o un lote de bienes
Se pueden agregar endpoints adicionales si aportan valor (paginación, filtros, búsqueda, etc.).

# Restricciones de Modelado
Las clases y atributos quedan a criterio del candidato.
Máximo 5 atributos por clase, solo los más relevantes.
Se pueden crear las clases que se consideren necesarias, respetando la restricción.

# Persistencia
La información debe almacenarse en una base de datos (a elección del candidato).
Se puede utilizar:
H2
PostgreSQL
MySQL
Otra base relacional o NoSQL
(Opcional) incluir docker-compose.yml para levantar la base de datos.

# Pruebas
Implementar las pruebas que se consideren necesarias:

Pruebas unitarias
Pruebas de integración
Se valorará:
Cobertura sobre lógica de negocio
Pruebas de endpoints REST
Uso de herramientas como MockMvc, Testcontainers, etc.

# Calidad y Buenas Prácticas
Se evaluará especialmente:

Modelado del dominio
Diseño de endpoints REST
Manejo de estados (activo / baja)
Manejo de operaciones por lote
Separación de capas
Uso de patrones y principios (SOLID, Clean Code, etc.)
Manejo de errores y códigos HTTP
Claridad del README y facilidad de ejecución


# Funcionalidas cumplidas
## 📋 Estado de Tareas

| Tarea | Estado |
|------|--------|
| Crear y actualizar categorías | ✅ Cumplido |
| Crear un nuevo bien y asignarlo a una categoría | ✅ Cumplido |
| Recuperar el listado de bienes con su detalle (activos y dados de baja) | ✅ Cumplido |
| Recuperar cuántos bienes disponibles existen en cada categoría | ✅ Cumplido |
| Recuperar las categorías existentes con sus respectivos bienes | ✅ Cumplido |
| Dada una categoría, recuperar cantidad de bienes disponibles | ✅ Cumplido |
| Dada una categoría, recuperar cantidad de bienes dados de baja | ✅ Cumplido |
| Ingresar un lote de nuevos bienes | ✅ Cumplido |
| Dar de baja un bien o un lote de bienes | ⏳ Pendiente |
| Endpoints adicionales (paginación, filtros, búsqueda, etc.) | 🔧 Opcional |

# Datos tecnicos
La arquitectura elejida es hexagonal, debido asu flexibilidad para escalar.
Se creo un Objeto de valor para Categorias para tener un control mejor y evitar usar contadores automaticos
que al momento de migracion se vuelve un problema en relacionar la informacion.

## nomenglatura 
    Objeto de valor : C-0000 


## 🗄️ Modelo de Base de Datos

A continuación se muestra el script SQL utilizado para la creación de las tablas principales del sistema.

### Tabla: `categorias`

Almacena las categorías a las que pertenecen los bienes.

```sql
CREATE TABLE categorias (
  id varchar(6) NOT NULL,
  nombre varchar(100) NOT NULL,
  estado varchar(20) NOT NULL,
  fecharegistra date NOT NULL,
  horaregistra time NOT NULL,
  idcreador varchar(10) NOT NULL,
  CONSTRAINT categorias_pkey PRIMARY KEY (id)
);

CREATE TABLE bienes (
    id serial4 NOT NULL,
    idcategoria varchar(6) NOT NULL,
    nombre varchar(100) NOT NULL,
    descripcion varchar(500) NULL,
    estado varchar(20) NOT NULL,
    fecharegistra date NOT NULL,
    horaregistra time NOT NULL,
    idcreador varchar(10) NOT NULL,
    CONSTRAINT bienes_pkey PRIMARY KEY (id),
    CONSTRAINT bienes_idcategoria_fkey FOREIGN KEY (idcategoria) REFERENCES categorias(id)
);


INSERT INTO categorias
(id, nombre, estado, fecharegistra, horaregistra, idcreador)
VALUES
('C-0001', 'Ropa', 'ACTIVO', '2026-02-05', '10:45:00', 'ADMIN');
INSERT INTO categorias
(id, nombre, estado, fecharegistra, horaregistra, idcreador)
VALUES
('C-0002', 'Alimentos', 'ACTIVO', '2026-02-05', '11:00:00', 'ADMIN');
INSERT INTO categorias
(id, nombre, estado, fecharegistra, horaregistra, idcreador)
VALUES
('C-0003', 'Linea blanca', 'ACTIVO', '2026-02-05', '23:13:43', 'ADMIN');

````



## Nota, 
Configurar el application.properties

En tu proyecto Spring Boot, abre src/main/resources/application.properties y asegúrate de configurar los datos de conexión a tu base de datos:

spring.datasource.url=jdbc:postgresql://localhost:5432/inventario
spring.datasource.username=postgres
spring.datasource.password=admin

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true


💡 Explicación:

spring.datasource.url: URL de conexión a PostgreSQL, apuntando a la base de datos inventario.

spring.datasource.username: tu usuario de PostgreSQL.

spring.datasource.password: tu contraseña de PostgreSQL.

spring.jpa.hibernate.ddl-auto=update: Hibernate crea/actualiza automáticamente las tablas según tus entidades.

spring.jpa.show-sql y format_sql: opcionales, solo para ver las consultas SQL que Spring ejecuta.

# Para ejecutar la aplicacion 
Debe navegar dentro del proyecto inventario, src/main/com.empresa.inventario
Abrir el InventarioApplication
Buscar el boton run que sale a un costado de la clase y ejecutar

# Sawagger
http://localhost:8086/swagger-ui/index.html#/

# Esta aplicacion se programa con Intelij
Debido a que ya poseo una licencia comprada y da mas facilidades,
que los otros ides