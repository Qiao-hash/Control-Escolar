<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${empty materia ? 'Nueva' : 'Editar'} Materia</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="card shadow-sm border-0 rounded-4">
                <div class="card-body p-4">
                    <h2 class="card-title text-center mb-4">${empty materia ? 'Registrar' : 'Editar'} Materia</h2>

                    <form action="${pageContext.request.contextPath}/materias" method="post">
                        <input type="hidden" name="action" value="${empty materia ? 'insertar' : 'actualizar'}">

                        <c:if test="${not empty materia}">
                            <input type="hidden" name="codigo" value="${materia.codigo}">
                        </c:if>

                        <c:if test="${empty materia}">
                            <div class="mb-3">
                                <label class="form-label">Código</label>
                                <input type="text" name="codigo" class="form-control" required>
                            </div>
                        </c:if>

                        <div class="mb-3">
                            <label class="form-label">Nombre</label>
                            <input type="text" name="nombre" value="${materia.nombre}" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Descripción</label>
                            <textarea name="descripcion" class="form-control" rows="3">${materia.descripcion}</textarea>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Fecha de Creación</label>
                            <input type="date" name="fecha_creacion" value="${materia.fechaCreacion}" class="form-control" required>
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
