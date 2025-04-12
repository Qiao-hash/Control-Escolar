<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${empty alumno ? 'Nuevo' : 'Editar'} Alumno</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="card shadow-sm border-0 rounded-4">
                <div class="card-body p-4">
                    <h2 class="card-title text-center mb-4">${empty alumno ? 'Registrar' : 'Editar'} Alumno</h2>

                    <form action="${pageContext.request.contextPath}/alumnos" method="post">
                        <input type="hidden" name="action" value="${empty alumno ? 'insertar' : 'actualizar'}">

                        <c:if test="${not empty alumno}">
                            <input type="hidden" name="carnet" value="${alumno.carnet}">
                        </c:if>

                        <c:if test="${empty alumno}">
                            <div class="mb-3">
                                <label class="form-label">Carnet</label>
                                <input type="text" name="carnet" class="form-control" required>
                            </div>
                        </c:if>

                        <div class="mb-3">
                            <label class="form-label">Nombre</label>
                            <input type="text" name="nombre" value="${alumno.nombre}" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Apellidos</label>
                            <input type="text" name="apellidos" value="${alumno.apellidos}" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Fecha de Nacimiento</label>
                            <input type="date" name="fechaNacimiento" value="${alumno.fechaNacimiento}" class="form-control" required>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Dirección</label>
                            <input type="text" name="direccion" value="${alumno.direccion}" class="form-control" required>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">
                                Guardar
                            </button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
