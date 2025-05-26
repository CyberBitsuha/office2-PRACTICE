<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Новое заявление"/>
<%@ include file="/WEB-INF/views/partials/header.jsp" %>

<div class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h3 class="mb-0">
                <i class="bi bi-qr-code"></i> Загрузить QR-код заявления
            </h3>
        </div>
        <div class="card-body">
            <form action="<c:url value='/appeals/save'/>"
                  method="post"
                  enctype="multipart/form-data"
                  class="needs-validation"
                  novalidate>

                <div class="mb-4">
                    <label for="qrFile" class="form-label h5">
                        <i class="bi bi-file-earmark-image"></i> Выберите файл QR-кода
                    </label>
                    <input type="file"
                           class="form-control form-control-lg"
                           id="qrFile"
                           name="qrFile"
                           accept="image/*"
                           required>
                    <div class="invalid-feedback">
                        Пожалуйста, загрузите изображение с QR-кодом.
                    </div>
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

<script>
    (function() {
        'use strict';
        const forms = document.querySelectorAll('.needs-validation');
        Array.from(forms).forEach(form => {
            form.addEventListener('submit', ev => {
                if (!form.checkValidity()) {
                    ev.preventDefault();
                    ev.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    })();
</script>

<%@ include file="/WEB-INF/views/partials/footer.jsp" %>
