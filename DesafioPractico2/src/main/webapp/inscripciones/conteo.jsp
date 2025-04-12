<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Conteo de Estudiantes por Materia</title>
    <style>
        table { border-collapse: collapse; margin: 20px 0; }
        th, td { padding: 8px 12px; border: 1px solid #ddd; }
        th { background-color: #f5f5f5; }
        a { color: #007bff; text-decoration: none; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
<h1>Estudiantes Inscritos por Materia</h1>

<table>
    <tr>
        <th>Código Materia</th>
        <th>Total Estudiantes</th>
        <th>Acción</th>
    </tr>
    <c:forEach items="${conteo}" var="entry">
        <tr>
            <td>${entry.key}</td>
            <td>${entry.value}</td>
            <td>
                <a href="${pageContext.request.contextPath}/inscripciones?action=listaEstudiantes&codigoMateria=${entry.key}">
                    Ver estudiantes
                </a>
            </td>
        </tr>
    </c:forEach>
</table>

<div>
    <a href="${pageContext.request.contextPath}/inscripciones?action=asociaciones">&larr; Volver a inscripciones</a>
</div>
</body>
</html>