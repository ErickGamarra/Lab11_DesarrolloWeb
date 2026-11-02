# Laboratorio 11 - Microservicios

Desarrollo de Aplicaciones Web - Tecsup

## microservicios

| Servicio | Puerto | Base de datos |
|---|---|---|
| cliente-service | 8081 | bd_cliente |
| categoria-service | 8082 | bd_categoria_dev (perfil dev) |
| producto-service | 8083 | bd_producto |
| pedido-service | 8084 | bd_pedido |

## clinica-salud-integral

Actividad del sistema de citas.

| Servicio | Puerto | Base de datos |
|---|---|---|
| paciente-service | 8085 | bd_paciente |
| medico-service | 8086 | bd_medico |
| cita-service | 8087 | bd_cita |

Las tablas se crean con `clinica-salud-integral/clinica_bd.sql`.
Para probar las citas deben estar encendidos los tres servicios.
