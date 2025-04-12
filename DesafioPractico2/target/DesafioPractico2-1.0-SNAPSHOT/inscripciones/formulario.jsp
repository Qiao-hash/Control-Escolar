<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Inscripción</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <script>
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

    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="card shadow-sm border-0 rounded-4">
                <div class="card-body p-4">
                    <h2 class="card-title text-center mb-4">Nueva Inscripción</h2>

                    <form action="${pageContext.request.contextPath}/inscripciones" method="post">
                        <input type="hidden" name="action" value="inscribir">

                        <div class="mb-3">
                            <label class="form-label">Carnet del Alumno</label>
                            <input type="text" name="carnet" class="form-control" required>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Código de la Materia</label>
                            <input type="text" name="codigoMateria" class="form-control" required>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">
                                <i class="bi bi-save"></i> Registrar
                            </button>
                        </div>
                    </form>

                    <div class="mt-4 text-center">
                        <a href="${pageContext.request.contextPath}/inscripciones?action=asociaciones" class="btn btn-outline-secondary">
                            <i class="bi bi-list-ul"></i> Ver Inscripciones
                        </a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
