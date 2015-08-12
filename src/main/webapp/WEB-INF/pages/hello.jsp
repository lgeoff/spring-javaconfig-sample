<%@include file="/WEB-INF/tags/taglibs.jsp" %>

<html>
<head>
    <spring:url value="/webjars/bootstrap/3.3.5/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/webjars/bootstrap/3.3.5/js/bootstrap.min.js" var="bootstrapJs"/>


    <link href="${bootstrapCss}" rel="stylesheet"/>
    <script src="${bootstrapJs}"></script>

</head>
<body>
<h1>${message}</h1>

<h2>Spring security</h2>
You are <strong><sec:authentication property="principal.username"/></strong><br>
<a href="<c:url value='/j_spring_security_logout'/>">Logout</a>

<h2>Web application / Spring MVC</h2>
<a href="<c:url value='/books'/>">Books</a><br>


<h2>REST interfaces / JAX-RS</h2>
<a href="<c:url value='/rest/books/'/>">GET Books [REST]</a><br>
<a href="<c:url value='/rest/books/1'/>">GET Specific book [REST]</a><br>
To insert book, POST on the /rest/books url
    <pre>
    {
        "title": "Book 2",
        "description": "This is book 2"
    }
    </pre>

<h2>Spring batch</h2>

<a href="/books/generateCsv">Generate CSV Report of books</a>


</body>
</html>