<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
            <h1 id="homeTitle">
                ${allComputer} <spring:message code="dashboard.allComputers"/>
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="?limit=${page.getPageSize()}&page=${page.getPage()}&search=${page.getSearch()}" method="GET" class="form-inline">
						<c:choose>
							<c:when test="${page.getSearch() != null}">
	                        	<input type="search" id="searchbox" name="search" class="form-control" value="${page.getSearch()}"/>
	                        </c:when>
	                        <c:otherwise>
	                        	<input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
	                        </c:otherwise>
                        </c:choose>
                        <input type="submit" id="searchsubmit" value="<spring:message code="dashboard.search"/>" class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="add">
                    	<spring:message code="dashboard.add"/>
                    </a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">
                    	<spring:message code="dashboard.edit"/>
                    </a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="delete" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            <spring:message code="dashboard.computerName"/>
                            <div class="order">
                            	<c:choose>
                            		<c:when test="${page.getSearch() != null}">
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&search=${page.getSearch()}&order=name&sort=ASC">
                            				<i class="up"></i>
                            			</a>
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&search=${page.getSearch()}&order=name&sort=DESC">
                            				<i class="down"></i>
                            			</a>
                            		</c:when>
                            		<c:otherwise>
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&order=name&sort=ASC">
                            				<i class="up"></i>
                            			</a>
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&order=name&sort=DESC">
                            				<i class="down"></i>
                            			</a>
                            		</c:otherwise>
                            	</c:choose>
                            </div>
                        </th>
                        <th>
                            <spring:message code="dashboard.introduced"/>
                            <div class="order">
                            	<c:choose>
                            		<c:when test="${page.getSearch() != null}">
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&search=${page.getSearch()}&order=in&sort=ASC">
                            				<i class="up"></i>
                            			</a>
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&search=${page.getSearch()}&order=in&sort=DESC">
                            				<i class="down"></i>
                            			</a>
                            		</c:when>
                            		<c:otherwise>
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&order=in&sort=ASC">
                            				<i class="up"></i>
                            			</a>
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&order=in&sort=DESC">
                            				<i class="down"></i>
                            			</a>
                            		</c:otherwise>
                            	</c:choose>
                            </div>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            <spring:message code="dashboard.discontinued"/>
                            <div class="order">
                            	<c:choose>
                            		<c:when test="${page.getSearch() != null}">
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&search=${page.getSearch()}&order=out&sort=ASC">
                            				<i class="up"></i>
                            			</a>
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&search=${page.getSearch()}&order=out&sort=DESC">
                            				<i class="down"></i>
                            			</a>
                            		</c:when>
                            		<c:otherwise>
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&order=out&sort=ASC">
                            				<i class="up"></i>
                            			</a>
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&order=out&sort=DESC">
                            				<i class="down"></i>
                            			</a>
                            		</c:otherwise>
                            	</c:choose>
                            </div>
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            <spring:message code="dashboard.companyName"/>
                            <div class="order">
                            	<c:choose>
                            		<c:when test="${page.getSearch() != null}">
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&search=${page.getSearch()}&order=company.name&sort=ASC">
                            				<i class="up"></i>
                            			</a>
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&search=${page.getSearch()}&order=company.name&sort=DESC">
                            				<i class="down"></i>
                            			</a>
                            		</c:when>
                            		<c:otherwise>
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&order=company.name&sort=ASC">
                            				<i class="up"></i>
                            			</a>
                            			<a href="?limit=${page.getPageSize()}&page=${page.getPage()}&order=company.name&sort=DESC">
                            				<i class="down"></i>
                            			</a>
                            		</c:otherwise>
                            	</c:choose>
                            </div>
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
	              <c:forEach items="${computers}" var="computer">
	                  <tr>
	                      <td class="editMode">
	                          <input type="checkbox" name="cb" class="cb" value="${computer.getId()}">
	                      </td>
	                      <td>
	                          <a href="edit?cptId=${computer.getId()}" onclick="">${computer.getName()}</a>
	                      </td>
	                      <td>${computer.getIn()}</td>
	                      <td>${computer.getOut()}</td>
	                      <td>${computer.getCompName()}</td>
	
	                  </tr>
	              </c:forEach>                          
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
        	<div class="pull-left">
        		<spring:message code = "app.lang"/> : <a href="?lang=FR">FR</a> | <a href="?lang=EN">EN</a>
        	</div>
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
              <c:forEach begin="1" end="${page.getMaxPage()}" var="i">
              	<c:choose>
              		<c:when test="${page.getSearch() != null}">
              			<li><a href="?limit=${page.getPageSize()}&page=${i}&search=${page.getSearch()}" name="pageNbr"><c:out value="${i}"/></a></li>
              		</c:when>
              		<c:otherwise>
              			<li><a href="?limit=${page.getPageSize()}&page=${i}" name="pageNbr"><c:out value="${i}"/></a></li>
              		</c:otherwise>
              	</c:choose>
              </c:forEach>
              <li>
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
              </li>
          </ul>
        </div>

        <div class="btn-group btn-group-sm pull-right" role="group" >
        	
	            <a href="?limit=10&page=${page.getPage()}" name="pageSize"><button type="button" class="btn btn-default">10</button></a>
	            <a href="?limit=50&page=${page.getPage()}" name="pageSize"><button type="button" class="btn btn-default">50</button></a>
	            <a href="?limit=100&page=${page.getPage()}" name="pageSize"><button type="button" class="btn btn-default">100</button></a>
        </div>

    </footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>

</body>
</html>