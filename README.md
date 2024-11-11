# Proyecto Estructura de Datos G2

### Descripción

Los **Contactos** tienen sus atributos de los cuales son los siguientes:
- Nombre 
- Número de teléfono 
- Foto 
- Dirección 

También existen multiples atributos para los contactos: 
- direccion de domicilio o trabajo, descriptiva y/o ubicación geográfica
- Varios emails ya sea de uso personal, trabajo o entre otros.
- Lista de números telefónicos
- Identificadores de distintas redes sociales
- Colección de fotos
- Varias fechas de interes

Los contactos se pueden relacionar con otros contactos de tal manera que:
- El contacto de un empresa tiene asociado el contacto de su director o de algún empleado
- El contacto de un médico puede tener el contacto de su asistente
- El contacto del mejor amigo puede tener el contacto de sus familiares o sus demás amigos

### Requerimientos de la aplicación
Los usuarios podrán visualizar los contactos, crearlos, editarlos y eliminarlos
Los atributos de los contactos no siempre son los mismos, una empresa no tiene apellido por lo cual, los contactos deben de ser consistentes.

> [!IMPORTANT]
> - La navegación de los contactos debe poder realizarse de atras hacia delante y viceversa (manera circular). 
> - Crear, editar y remover Contactos. Se debe de tener al menos dos plantillas al momendot de crear un nuevo contactos tipicos: Empresa o Persona.
> - Crear, edita y remover contactos existentes. Se debe poder añadir más direcciones de un contacto, remover o editar los atributos del contacto existente
> - Ordenamiento de los contactos por al menos 3 criterios de los cuales pueden ser:
>   * Apellido y primer nombre
>   * Cantidad de atributos contenidos
>   * Fecha de cumpleaños mas cercana
>   * País de residencia
>   * Tipo de contacto (Persona o Empresa)
>   * Existencia de un atributo en particular
>   * Entre otros que se considere pertinente
> - Filtrar la lista de contactos por, al menos, tres de los atributos listados ateriormente
> - Visualizar los contactos asociados de un contacto existente y visualizar los detalles del mismo.
