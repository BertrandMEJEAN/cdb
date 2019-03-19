<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <h1>Edit Computer</h1>

                    <form action="edit" method="POST">
                        <input type="hidden" name="cptId" value="${computer.getId()}" id="id"/> <!-- TODO: Change this value with the computer id -->
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" name="nameCpt" placeholder="Computer name" value="${computer.getName()}">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="text" class="form-control" id="introduced" name="introCpt" placeholder="dd-MM-yyyy" value="${computer.getIn()}">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="text" class="form-control" id="discontinued" name="discontCpt" placeholder="dd-MM-yyyy" value="${computer.getOut()}">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
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
                            <input type="submit" value="Edit" class="btn btn-primary">
                            or
                            <a href="dashboard.html" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <footer>
        <script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
    	<script src="js/formComputer.js"></script>
    </footer>
</body>
</html>