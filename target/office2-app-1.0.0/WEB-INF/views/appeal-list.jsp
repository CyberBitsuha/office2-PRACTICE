<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="pageTitle" value="Список заявлений"/>
<%@ include file="/WEB-INF/views/partials/header.jsp" %>

<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Заявления</h2>
        <a class="btn btn-success" href="<c:url value='/appeals/new'/>">
            <i class="bi bi-plus-circle"></i> Добавить новое
        </a>
    </div>

    <%-- Не рассмотренные --%>
    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4 mb-5">
        <c:forEach var="a" items="${appeals}">
            <c:if test="${empty a.resolution}">
                <div class="col">
                    <div class="card h-100 border-danger card-hover shadow">
                        <div class="card-body">
                            <h5 class="card-title">${fn:escapeXml(a.theme)}</h5>
                            <p class="card-text">
                                <small class="text-muted">ID: ${a.id}</small><br>
                                Заявитель: ${fn:escapeXml(a.applicantname)}<br>
                                Статус: <span class="text-danger">к рассмотрению</span>
                            </p>
                        </div>
                        <div class="card-footer bg-transparent">
                            <a href="<c:url value='/appeals/edit/${a.id}'/>"
                               class="btn btn-sm btn-outline-primary">
                                <i class="bi bi-pencil"></i> Редактировать
                            </a>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>

    <%-- Рассмотренные --%>
    <h4 class="mb-3">Рассмотренные заявления</h4>
    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
        <c:forEach var="a" items="${appeals}">
            <c:if test="${not empty a.resolution}">
                <div class="col">
                    <div class="card h-100 border-success card-hover shadow">
                        <div class="card-body">
                            <h5 class="card-title">${fn:escapeXml(a.theme)}</h5>
                            <p class="card-text">
                                <small class="text-muted">ID: ${a.id}</small><br>
                                Заявитель: ${fn:escapeXml(a.applicantname)}<br>
                                Резолюция: <span class="text-success">${fn:escapeXml(a.resolution)}</span>
                            </p>
                        </div>
                        <div class="card-footer bg-transparent">
                            <a href="<c:url value='/appeals/edit/${a.id}'/>"
                               class="btn btn-sm btn-outline-primary">
                                <i class="bi bi-pencil"></i> Редактировать
                            </a>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>

<style>
    .card-hover {
        transition: transform 0.2s ease-in-out;
    }
    .card-hover:hover {
        transform: scale(1.03);
        z-index: 1;
    }
</style>

<%@ include file="/WEB-INF/views/partials/footer.jsp" %>