<%@include file="/WEB-INF/tags/taglibs.jsp" %>
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
        <div class="col-md-3 col-md-offset-5">
        <form class="form-signin" name='loginForm'
              action="<c:url value='/j_spring_security_check' />" method='POST'>
            <h2 class="form-signin-heading"><spring:message code="login.pleaselogin"/></h2>
            <label for="username" class="sr-only"><spring:message code="login.username"/></label>
            <input type="text" name="username" id="username" class="form-control" placeholder="Username" required="" autofocus="">
            <label for="password" class="sr-only"><spring:message code="login.password"/></label>
            <input type="password" name="password" id="password" class="form-control" placeholder="Password" required="">

            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> <spring:message code="login.rememberme"/>
                </label>
            </div>
            <input type="hidden"
                   name="${_csrf.parameterName}" value="${_csrf.token}" />
            <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="login.signin"/></button>
        </form>

            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <span class="sr-only">Error:</span>
                    ${error}
                </div>
            </c:if>

    </div>
</div>
</div>
</body>
</html>
