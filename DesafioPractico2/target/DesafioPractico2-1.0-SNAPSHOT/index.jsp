<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema de Control Escolar</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 100px;
            text-align: center;
        }
        .option-card {
            cursor: pointer;
            transition: transform 0.2s;
            border: none;
            border-radius: 15px;
            background-color: #f8f9fa;
        }
        .option-card:hover {
            transform: scale(1.05);
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="mb-5">Bienvenido al Sistema de Control Escolar</h1>
    <div class="row justify-content-center">
        <!-- Opción Alumnos -->
        <div class="col-md-4 mb-4">
            <div class="card option-card h-100"
                 onclick="window.location.href='${pageContext.request.contextPath}/alumnos'">
                <div class="card-body">
                    <h5 class="card-title">📚 Alumnos</h5>
                    <p class="card-text">Gestión de registros de estudiantes.</p>
                </div>
            </div>
        </div>

        <!-- Opción Materias -->
        <div class="col-md-4 mb-4">
            <div class="card option-card h-100"
                 onclick="window.location.href='${pageContext.request.contextPath}/materias'">
                <div class="card-body">
                    <h5 class="card-title">📖 Materias</h5>
                    <p class="card-text">Administración de materias académicas.</p>
                </div>
            </div>
        </div>

        <!-- Opción Inscripciones -->
        <div class="col-md-4 mb-4">
            <div class="card option-card h-100"
                 onclick="window.location.href='${pageContext.request.contextPath}/inscripciones'">
                <div class="card-body">
                    <h5 class="card-title">✏️ Inscripciones</h5>
                    <p class="card-text">Registro de alumnos en materias.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS y Popper.js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>