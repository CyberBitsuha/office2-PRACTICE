<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Авторизация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .office-header {
            background-color: #0d6efd;
            color: white;
            padding: 1rem;
            font-size: 1.5rem;
            font-weight: bold;
        }
        .login-container {
            max-width: 400px;
            margin: 2rem auto;
            padding: 2rem;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
<div class="office-header">
    <div class="container">
        Office 2
    </div>
</div>

<div class="container">
    <div class="login-container rounded">
        <h1 class="text-center mb-4">Авторизация</h1>
        <form action="<c:url value='/login'/>" method="post">
            <div class="mb-3">
                <label for="login" class="form-label">Логин</label>
                <input type="text"
                       class="form-control"
                       id="login"
                       name="login"
                       placeholder="Введите логин"
                       required>
            </div>

            <div class="mb-4">
                <label for="password" class="form-label">Пароль</label>
                <input type="password"
                       class="form-control"
                       id="password"
                       name="password"
                       placeholder="Введите пароль"
                       required>
            </div>

            <div class="d-grid">
                <button type="submit" class="btn btn-primary btn-lg">Войти</button>
            </div>
        </form>

        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">
                    ${error}
            </div>
        </c:if>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>