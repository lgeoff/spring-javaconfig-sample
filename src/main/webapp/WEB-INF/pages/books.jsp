<%@include file="/WEB-INF/tags/taglibs.jsp" %>

<%--@elvariable id="book" type="com.ctp.springtemplate.models.Book"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/webjars/bootstrap/3.3.5/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/webjars/bootstrap/3.3.5/js/bootstrap.min.js" var="bootstrapJs"/>

    <link href="${bootstrapCss}" rel="stylesheet"/>
    <script src="${bootstrapJs}"></script>
</head>
<body>
<div class="container">
    <div class="row">
        Hello


        <table class="table">
            <caption>Books list</caption>
            <thead>
            <tr>
                <th>#</th>
                <th>Title</th>
                <th>Description</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book">

                <tr>
                    <th scope="row">
                        <a href="<c:url value="/books/remove/${book.id}"/> ">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"> ${book.id}</span>
                        </a>
                    </th>
                    <td>${book.title}</td>
                    <td>${book.description}</td>
                </tr>
            </c:forEach>
            <form action="<c:url value="/books/add"/> " method="post">
                <tr>
                    <th scope="row"> <button class="btn" type="submit">Add New</button></th>
                    <td>  <input type="text" name="title" id="title" class="form-control" placeholder="Title" required="" autofocus=""></td>
                    <td><input type="text" name="description" id="description" class="form-control" placeholder="Description" required="" autofocus=""></td>
                </tr>
                <input type="hidden"
                       name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
            </tbody>
        </table>


    </div>
</div>
</body>
</html>
