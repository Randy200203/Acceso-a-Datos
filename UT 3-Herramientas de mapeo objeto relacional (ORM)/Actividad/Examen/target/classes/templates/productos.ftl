<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Listado de Productos</title>
        <link href="bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="container mt-5">
            <h2>Listado de Productos</h2>
            <div class="container p-4">
                <a href="/formulario-alta-producto" class="btn btn-primary">Crear producto</a>
                <a href="/" class="btn btn-success float-end">Volver</a>
            </div>
            <#if productos?has_content && productos?size gt 0>
                <table class="table table-bordered">
                    <thead>
                        <tr style="background-color: #18406a; color: #fff;">
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Valor</th>
                            <th>Editar</th>
                            <th>Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list productos as producto>
                            <#assign colorFondo = '#f9f9f9'>
                            <#if producto_index % 2 == 0>
                                <#assign colorFondo = '#ffffff'>
                            </#if>
                            <tr style="background-color: ${colorFondo};">
                                <td>${producto.id}</td>
                                <td>${producto.nombre}</td>
                                <td>${producto.valor} €</td>
                                <td><a href="/formulario-edicion-producto/${producto.id}">editar</a></th>
                                <td><a href="/eliminar-producto/${producto.id}">eliminar</a></th>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            <#else>
                <p>Parece que no existen productos.</p>
            </#if>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>