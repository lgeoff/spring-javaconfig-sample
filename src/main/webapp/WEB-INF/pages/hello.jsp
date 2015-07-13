<%@include file="/WEB-INF/tags/taglibs.jsp" %>

<html>
<head>
    <spring:url value="/webjars/bootstrap/3.3.5/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/webjars/bootstrap/3.3.5/js/bootstrap.min.js" var="bootstrapJs"/>


    <link href="${bootstrapCss}" rel="stylesheet" />
    <script src="${bootstrapJs}"></script>

</head>
<body>
	<h1>${message}</h1>
You are <sec:authentication property="principal.username" /><br>
    <a href="<c:url value='/books'/>">Books</a><br>

    <a href="<c:url value='/rest/books/'/>">GET Books [REST]</a><br>
    <a href="<c:url value='/rest/books/1'/>">GET Specific book [REST]</a><br>
    To insert book, POST on the /rest/books url
    <pre>
    {
        "title": "Book 2",
        "description": "This is book 2"
    }
    </pre>
<a href="<c:url value='/j_spring_security_logout'/>">Logout</a>
</body>
</html>