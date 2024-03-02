<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Bienvenida</title>
    <link href="bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6">
            <h2>Datos de la Empresa</h2>

            <div class="card">
                <div class="card-body">
                    <p class="card-text"><strong>ID:</strong> ${empresa.id}</p>
                    <p class="card-text"><strong>Nombre de la Empresa:</strong> ${empresa.nombre}</p>
                    <p class="card-text"><strong>Sector:</strong> ${empresa.sector}</p>
                    <p class="card-text"><strong>Capital:</strong> ${empresa.capital} €</p>
                    <p class="card-text"><strong>Número de empleados:</strong> ${empresa.numEmpleados}</p>
                </div>
            </div>
            <div class="container p-4">
                <a href="/vender" class="btn btn-primary">Vender</a>
                <a href="/comprar" class="btn btn-success float-end">Comprar</a>
            </div>
        </div>
        <div class="col-md-6">

            <#if listadoStock?has_content && listadoStock?size gt 0>
                <h2>Artículos en Stock:</h2>

                <table class="table table-bordered">
                    <thead>
                    <tr style="background-color: #18406a; color: #fff;">
                        <th>ID Producto</th>
                        <th>Nombre</th>
                        <th>Cantidad</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list listadoStock as stock>
                        <#assign colorFondo = '#f9f9f9'>
                        <#if stock_index % 2 == 0>
                            <#assign colorFondo = '#ffffff'>
                        </#if>
                        <tr style="background-color: ${colorFondo};">
                            <td>${stock.idProducto}</td>
                            <td>
                                <#list productos as producto>
                                    <#if producto.id == stock.idProducto>
                                        ${producto.nombre}
                                    </#if>
                                </#list>
                            </td>
                            <td>${stock.cantidad}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            <#else>
                <h2>Por el momento no hay artículos en stock.</h2>
            </#if>
        </div>
    </div>
</div>

<script src="bootstrap.bundle.min.js"></script>
</body>
</html>