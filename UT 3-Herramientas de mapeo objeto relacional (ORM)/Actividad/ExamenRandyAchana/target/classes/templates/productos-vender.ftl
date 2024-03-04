<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Vender Productos</title>
    <link href="bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5 p-4">
    <h2>Selecciona los productos que deseas vender</h2>

    <form action="/vender-productos" method="post">
        <table class="table table-bordered">
            <thead>
            <tr style="background-color: #18406a; color: #fff;">
                <th>ID</th>
                <th>Nombre</th>
                <th>Valor</th>
                <th>Cantidad</th>
                <th>Seleccionar</th>
            </tr>
            </thead>
            <tbody>
            <#list productos as producto>
                <tr>
                    <td>${producto.id}</td>
                    <td>${producto.nombre}</td>
                    <td>${producto.valor} €</td>
                    <td>
                        <input type="number" name="cantidad-${producto.id}" value="1" min="1">
                    </td>
                    <td>
                        <input type="checkbox" name="productosSeleccionados" value="${producto.id}">
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>

        <button type="submit" class="btn btn-success">Vender productos seleccionados</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>