<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Formulario de Alta de Empresa</title>
    <link href="bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>Alta de Empresa</h2>

    <form action="/alta-empresa" method="post">
        <div class="mb-3">
            <label for="id" class="form-label">ID</label>
            <input type="number" class="form-control" id="id" name="id" required>
        </div>

        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>

        <div class="mb-3">
            <label for="sector" class="form-label">Sector</label>
            <select class="form-select" id="sector" name="sector" required>
                <option value="consultoria">Consultoría</option>
                <option value="construccion">Construcción</option>
                <option value="informatica">Informática</option>
                <option value="otros">Otros</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="capital" class="form-label">Capital</label>
            <input type="number" class="form-control" id="capital" name="capital" required>
        </div>

        <div class="mb-3">
            <label for="numEmpleados" class="form-label">Número de Empleados</label>
            <input type="number" class="form-control" id="numEmpleados" name="numEmpleados" required>
        </div>

        <button type="submit" class="btn btn-primary">Dar de Alta</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>