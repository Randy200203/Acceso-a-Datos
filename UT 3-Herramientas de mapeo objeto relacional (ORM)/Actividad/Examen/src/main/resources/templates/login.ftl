<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login</title>
    <link href="bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 mt-5">
            <h2>Login</h2>

            <#if error?exists>
                <h2 class="text-center" style="color: red;">${error}</h2>
            </#if>

            <form action="/login" method="post">
                <div class="mb-3">
                    <label for="nombreEmpresa" class="form-label">Nombre de la Empresa</label>
                    <input type="text" class="form-control" id="nombreEmpresa" name="nombreEmpresa" required>
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

                <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
            </form>
        </div>

    </div>
    <div class="row justify-content-center">
        <div class="col-md-6 mt-5">
            <h2>Empresas</h2>
            <div class="container p-4">
                <a href="/visualizar-empresas" class="btn btn-secondary">Ver empresas</a>
            </div>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-6 mt-5">
            <h2>Productos</h2>
            <div class="container p-4">
                <a href="/visualizar-productos" class="btn btn-success">Ver productos</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>