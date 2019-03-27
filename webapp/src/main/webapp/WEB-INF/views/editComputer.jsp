<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                    <div class="label label-default pull-right">
                        id: ${computer.getId()}
                    </div>
                    <h1>
                    	<spring:message code="edit.title"/>
                    </h1>

                    <form action="edit" method="POST">
                        <input type="hidden" name="cptId" value="${computer.getId()}" id="id"/> <!-- TODO: Change this value with the computer id -->
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">
                                	<spring:message code="edit.computerName"/>
                                </label>
                                <input type="text" class="form-control" id="computerName" name="nameCpt" placeholder="Computer name" value="${computer.getName()}">
                            </div>
                            <div class="form-group">
                                <label for="introduced">
                               		<spring:message code="edit.introduced"/>
                                </label>
                                <input type="text" class="form-control" id="introduced" name="introCpt" placeholder="dd-MM-yyyy" value="${computer.getIn()}">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">
                                	<spring:message code="edit.discontinued"/>
                                </label>
                                <input type="text" class="form-control" id="discontinued" name="discontCpt" placeholder="dd-MM-yyyy" value="${computer.getOut()}">
                            </div>
                            <div class="form-group">
                                <label for="companyId">
                                	<spring:message code="edit.companyName"/>
                                </label>
                                <select class="form-control" id="companyId" name="idCpy">
                                    <option value="">--</option>
                                    <c:forEach items="${companies}" var="company">
	                                    <c:choose>
	                                    	<c:when test="${company.getId() == computer.getCompId()}">
	                                    		<option selected="selected" value="${company.getId()}">${company.getName()}</option>
	                                    	</c:when>
	                                    	<c:otherwise>
	                                    		<option value="${company.getId()}">${company.getName()}</option>
	                                    	</c:otherwise>
	                                    </c:choose>
                                    </c:forEach>
                                </select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="edit.submit"/>" class="btn btn-primary">
                            or
                            <a href="dashboard.html" class="btn btn-default"><spring:message code="edit.cancel"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <footer class="navbar-fixed-bottom">
    	<div class="container text-center">
        	<div class="pull-left">
        		<spring:message code = "app.lang"/> : <a href="?lang=FR&cptId=${computer.getId()}">FR</a> | <a href="?lang=EN&cptId=${computer.getId()}">EN</a>
        	</div>
        </div>
        <script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
    	<script src="js/formComputer.js"></script>
    </footer>
</body>
</html>