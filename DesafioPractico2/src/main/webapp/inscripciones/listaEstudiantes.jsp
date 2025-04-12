<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Estudiantes en la Materia</title>
</head>
<body>
<h1>Estudiantes en ${param.codigoMateria}</h1>
<ul>
    <c:forEach items="${estudiantes}" var="estudiante">
        <li>${estudiante}</li>
    </c:forEach>
</ul>
<a href="${pageContext.request.contextPath}/inscripciones">Volver</a>
</body>
</html>