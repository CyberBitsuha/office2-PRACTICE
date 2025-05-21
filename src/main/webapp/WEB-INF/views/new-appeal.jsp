<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Новое заявление"/>
<%@ include file="/WEB-INF/views/partials/header.jsp" %>

<div class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h3 class="mb-0"><i class="bi bi-file-earmark-plus"></i> Создать новое заявление</h3>
        </div>

        <div class="card-body">
            <form action="<c:url value='/appeals/save'/>" method="post">
                <div class="mb-4">
                    <label for="inputLine" class="form-label h5">Ввод строки</label>
                    <input type="text"
                           class="form-control form-control-lg"
                           id="inputLine"
                           name="inputLine"
                           placeholder="Введите текст заявления"
                           required>
                    <div class="form-text">Максимальная длина - 100 символов</div>
                </div>

                <div class="d-flex justify-content-between align-items-center">
                    <a href="<c:url value='/appeals'/>" class="btn btn-outline-secondary">
                        <i class="bi bi-arrow-left"></i> Назад к списку
                    </a>
                    <button type="submit" class="btn btn-primary btn-lg">
                        <i class="bi bi-save"></i> Сохранить
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/partials/footer.jsp" %>