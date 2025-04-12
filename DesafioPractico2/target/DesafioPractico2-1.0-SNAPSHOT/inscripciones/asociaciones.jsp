<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inscripciones Registradas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <script>
        function confirmarEliminacion(carnet, codigoMateria) {
            if (confirm("¿Eliminar esta inscripción?")) {
                window.location.href = `${pageContext.request.contextPath}/inscripciones?action=eliminar&carnet=${carnet}&codigoMateria=${codigoMateria}`;
            }
        }
        window.onload = function() {
            const urlParams = new URLSearchParams(window.location.search);
            const error = urlParams.get('error');
            if (error) {
                const alertPlaceholder = document.getElementById('alertPlaceholder');
                alertPlaceholder.innerHTML = `
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="bi bi-exclamation-triangle-fill"></i> ${error}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
                    </div>
                `;
            }
        };
    </script>
</head>
<body class="bg-light">

<div class="container py-5">
    <div id="alertPlaceholder"></div>

    <div class="card shadow-sm border-0 rounded-4">
        <div class="card-body">

            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2 class="card-title mb-0">Inscripciones Registradas</h2>
                <a href="${pageContext.request.contextPath}/inscripciones" class="btn btn-primary">
                    <i class="bi bi-plus-circle"></i> Nueva Inscripción
                </a>
            </div>

            <div class="table-responsive">
                <table class="table table-hover align-middle">
                    <thead class="table-primary">
                    <tr>
                        <th>Carnet</th>
                        <th>Alumno</th>
                        <th>Código Materia</th>
                        <th>Materia</th>
                        <th>Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${asociaciones}" var="asociacion">
                        <tr>
                            <td>${asociacion.carnet}</td>
                            <td>${asociacion.nombreAlumno}</td>
                            <td>${asociacion.codigoMateria}</td>
                            <td>${asociacion.nombreMateria}</td>
                            <td>
                                <button type="button"
                                        class="btn btn-sm btn-danger"
                                        onclick="confirmarEliminacion('${asociacion.carnet}', '${asociacion.codigoMateria}')">
                                    <i class="bi bi-trash"></i> Eliminar
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
