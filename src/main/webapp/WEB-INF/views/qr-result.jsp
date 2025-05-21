<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="QR-код решения"/>
<%@ include file="/WEB-INF/views/partials/header.jsp" %>

<div class="container mt-4">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h3 class="mb-0"><i class="bi bi-qr-code"></i> Решение по заявлению #${appeal.id}</h3>
        </div>

        <div class="card-body">
            <div class="row">
                <div class="col-md-8">
                    <div class="mb-4">
                        <h4>Детали решения</h4>
                        <div class="alert alert-success">
                            <strong><i class="bi bi-info-circle"></i> Резолюция:</strong>
                            <span class="ms-2">${appeal.resolution}</span>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="text-center border p-3 rounded bg-light">
                        <h5 class="mb-3">QR-код решения</h5>
                        <img src="data:image/png;base64,${qrData}"
                             alt="QR Code"
                             class="img-fluid mb-2"
                             style="max-width: 200px;">
                        <small class="text-muted">Отсканируйте для проверки</small>
                    </div>
                </div>
            </div>

            <div class="mt-4 text-center">
                <a href="<c:url value='/appeals'/>" class="btn btn-outline-primary btn-lg">
                    <i class="bi bi-arrow-left-circle"></i> Назад к списку
                </a>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/partials/footer.jsp" %>