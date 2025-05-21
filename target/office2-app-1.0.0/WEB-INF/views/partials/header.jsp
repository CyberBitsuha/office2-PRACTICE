<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:out value="${pageTitle}"/></title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <!-- Ваши стили -->
    <link rel="stylesheet" href="<c:url value='/static/styles.css'/>">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
    <div class="container">
        <a class="navbar-brand" href="<c:url value='/appeals'/>">
            <i class="bi bi-building"></i> Office2
        </a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <c:if test="${not empty sessionScope.loggedUser}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/logout'/>">
                            <i class="bi bi-box-arrow-right"></i> Выйти
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<div class="container">