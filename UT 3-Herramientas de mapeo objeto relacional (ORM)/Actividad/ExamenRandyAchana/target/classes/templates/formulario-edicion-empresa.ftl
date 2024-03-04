<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Formulario de Edición de Empresa</title>
    <link href="bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>Editar Empresa</h2>

    <form action="/editar-empresa" method="post">
        <div class="mb-3">
            <label for="id" class="form-label">ID</label>
            <input type="text" class="form-control" id="id" name="id" value="${empresa.id}" pattern="\d+" readonly>
        </div>

        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="${empresa.nombre}" required>
        </div>

        <div class="mb-3">
            <label for="sector" class="form-label">Sector: ${empresa.sector}</label>
            <select class="form-select" id="sector" name="sector" required>
                <#if (empresa.sector == "consultoria")>
                    <#assign selected = "selected">
                <#else>
                    <#assign selected = "">
                </#if>
                <option value="consultoria" ${selected}>Consultoría</option>
                <#if (empresa.sector == "construccion")>
                    <#assign selected = "selected">
                <#else>
                    <#assign selected = "">
                </#if>
                <option value="construccion" ${selected}>Construcción</option>
                <#if (empresa.sector == "informatica")>
                    <#assign selected = "selected">
                <#else>
                    <#assign selected = "">
                </#if>
                <option value="informatica" ${selected}>Informática</option>
                <#if (empresa.sector == "otros")>
                    <#assign selected = "selected">
                <#else>
                    <#assign selected = "">
                </#if>
                <option value="otros" ${selected}>Otros</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="capital" class="form-label">Capital</label>
            <input type="text" class="form-control" id="capital" name="capital" value="${empresa.capital}" pattern="\d+" required>
        </div>

        <div class="mb-3">
            <label for="numEmpleados" class="form-label">Número de Empleados</label>
            <input type="text" class="form-control" id="numEmpleados" name="numEmpleados" value="${empresa.numEmpleados}" pattern="\d+" required>
        </div>

        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>