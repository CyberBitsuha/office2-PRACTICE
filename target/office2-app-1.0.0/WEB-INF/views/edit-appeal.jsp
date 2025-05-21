<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Редактировать заявление #${appeal.id}"/>

<%@ include file="/WEB-INF/views/partials/header.jsp" %>

<h2 class="mb-4">Редактировать #${appeal.id}</h2>

<form action="<c:url value='/appeals/update'/>" method="post" class="row g-3 needs-validation" novalidate>
    <input type="hidden" name="id" value="${appeal.id}"/>

    <div class="col-md-6">
        <label class="form-label">Заявитель</label>
        <input type="text" class="form-control" value="${appeal.applicantname}" readonly>
    </div>

    <div class="col-md-6">
        <label class="form-label">Тема</label>
        <input type="text" class="form-control" value="${appeal.theme}" readonly>
    </div>

    <div class="col-12">
        <label class="form-label">Содержание</label>
        <textarea class="form-control" rows="3" readonly>${appeal.content}</textarea>
    </div>

    <div class="col-md-6">
        <label for="resolution" class="form-label">Резолюция</label>
        <select class="form-select" id="resolution" name="resolution" required>
            <option value="">— выберите —</option>
            <option value="рассмотрено" ${appeal.resolution == 'рассмотрено' ? 'selected' : ''}>рассмотрено</option>
            <option value="отклонено"   ${appeal.resolution == 'отклонено'   ? 'selected' : ''}>отклонено</option>
        </select>
        <div class="invalid-feedback">Выберите резолюцию.</div>
    </div>

    <div class="col-md-6">
        <label for="note" class="form-label">Заметка</label>
        <textarea class="form-control" id="note" name="note" rows="2"
                  placeholder="Подпись руководителя">${appeal.note}</textarea>
    </div>

    <div class="col-12">
        <button type="submit" class="btn btn-primary">Сохранить</button>
        <a class="btn btn-secondary" href="<c:url value='/appeals'/>">Отмена</a>
    </div>
</form>

<script>
    // Bootstrap валидация
    (function() {
        'use strict';
        const forms = document.querySelectorAll('.needs-validation');
        Array.from(forms).forEach(form => {
            form.addEventListener('submit', event => {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    })();
</script>

<%@ include file="/WEB-INF/views/partials/footer.jsp" %>
