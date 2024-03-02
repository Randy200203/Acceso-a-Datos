<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Listado de Empresas</title>
        <link href="bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="container mt-5">
            <h2>Listado de Empresas</h2>
            <div class="container p-4">
                <a href="/formulario-alta-empresa" class="btn btn-primary">Crear empresa</a>
                <a href="/" class="btn btn-success float-end">Volver</a>
            </div>
            <#if empresas?has_content && empresas?size gt 0>
                <table class="table table-bordered">
                    <thead>
                        <tr style="background-color: #18406a; color: #fff;">
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Sector</th>
                            <th>Capital</th>
                            <th>Número de Empleados</th>
                            <th>Editar</th>
                            <th>Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list empresas as empresa>
                            <#assign colorFondo = '#f9f9f9'>
                            <#if empresa_index % 2 == 0>
                                <#assign colorFondo = '#ffffff'>
                            </#if>
                            <tr style="background-color: ${colorFondo};">
                                <td>${empresa.id}</td>
                                <td>${empresa.nombre}</td>
                                <td>${empresa.sector} €</td>
                                <td>${empresa.capital} €</td>
                                <td>${empresa.numEmpleados} €</td>
                                <td><a href="/formulario-edicion-empresa/${empresa.id}">editar</a></th>
                                <td><a href="/eliminar-empresa/${empresa.id}">eliminar</a></th>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            <#else>
                <p>Parece que no existen empresas.</p>
            </#if>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>