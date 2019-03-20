<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>
                    	<spring:message code="add.title"/>
                    </h1>
                    <form action="add" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">
                                	<spring:message code="add.computerName"/>
                                </label>
                                <input type="text" class="form-control" id="computerName" name="nameCpt" placeholder="Computer name" required>
                            </div>
                            <div class="form-group">
                                <label for="introduced">
                                	<spring:message code="add.introduced"/>
                                </label>
                                <input type="text" class="form-control" id="introduced" name="inCpt" placeholder="dd-MM-yyyy">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">
                                	<spring:message code="add.discontinued"/>
                                </label>
                                <input type="text" class="form-control" id="discontinued" name ="outCpt" placeholder="dd-MM-yyyy">
                            </div>
                            <div class="form-group">
                                <label for="companyId">
                                	<spring:message code="add.companyName"/>
                                </label>
                                <select class="form-control" id="companyId" name="idCpy" >
                                <option value=""></option>
                                	<c:forEach items="${companies}" var="company">
                                    	<option value="${company.getId()}">${company.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="add.submit"/>" class="btn btn-primary">
                            or
                            <a href="" class="btn btn-default"><spring:message code="add.cancel"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <footer class="navbar-fixed-bottom">
    	<div class="container text-center">
        	<div class="pull-left">
        		<spring:message code = "app.lang"/> : <a href="?lang=FR">FR</a> | <a href="?lang=EN">EN</a>
        	</div>
        </div>
    	<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
    	<script src="js/formComputer.js"></script>
    </footer>
</body>
</html>