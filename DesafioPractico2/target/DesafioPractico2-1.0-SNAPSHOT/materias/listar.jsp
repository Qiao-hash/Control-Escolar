<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Materias</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container py-5">
    <div class="card shadow-sm border-0 rounded-4">
        <div class="card-body">
            <c:if test="${not empty mensajeExito}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <i class="bi bi-check-circle-fill"></i>
                        ${mensajeExito}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
                </div>
            </c:if>

            <c:if test="${not empty mensajeError}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <i class="bi bi-exclamation-triangle-fill"></i>
                        ${mensajeError}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
                </div>
            </c:if>

            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2 class="card-title mb-0">Listado de Materias</h2>
                <a href="materias/formulario.jsp" class="btn btn-primary">
                    <i class="bi bi-plus-circle"></i> Nueva Materia
                </a>
            </div>

            <c:if test="${empty materias}">
                <div class="alert alert-info" role="alert">
                    <i class="bi bi-info-circle"></i> No hay materias registradas.
                </div>
            </c:if>

            <c:if test="${not empty materias}">
                <div class="table-responsive">
                    <table class="table table-hover align-middle">
                        <thead class="table-primary">
                        <tr>
                            <th>Código</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Fecha de Creación</th>
                            <th>Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${materias}" var="materia">
                            <tr>
                                <td>${materia.codigo}</td>
                                <td>${materia.nombre}</td>
                                <td>${materia.descripcion}</td>
                                <td>${materia.fechaCreacion}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/materias?action=edit&codigo=${materia.codigo}"
                                       class="btn btn-sm btn-warning me-1">
                                        <i class="bi bi-pencil-square"></i> Editar
                                    </a>
                                    <form action="${pageContext.request.contextPath}/materias" method="post" class="d-inline">
                                        <input type="hidden" name="action" value="eliminar">
                                        <input type="hidden" name="codigo" value="${materia.codigo}">
                                        <button type="submit" class="btn btn-sm btn-danger"
                                                onclick="return confirm('¿Eliminar materia?')">
                                            <i class="bi bi-trash"></i> Eliminar
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
