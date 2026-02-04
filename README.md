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

