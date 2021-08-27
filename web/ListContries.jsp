<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Population Information</title>
        <link rel="stylesheet" href="css/myStyle.css" type="text/css" />
        <link rel="stylesheet" href="css/detail.css" type="text/css" />
    </head>
    <body>
        <div class="container">
            <h2>Population Informationn</h2>
            <hr style="width: 50%">
            <form id="searchForm" action="GetAllCountries" method="GET">
                <div class="search-group">
                    <label for="name">Name</label>
                    <input type="text" id="search" name="search" placeholder="enter user name" value="${searchCode}"  required>
                    <button type="submit" class="btn-success"> search </button>
                </div>
            </form>
            <c:if test="${listUser.size() eq 0}">
                <p style="color: red; font-style: italic">No Result</p>
            </c:if>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Population</th>
                    <th>Ranking</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ces}" var="i">
                    <tr>
                        <th>${i.name}</th>
                        <td>${i.population}</td>
                        <td>${i.ranking}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="pagination">    
            <button onclick="pagingHandle(${pageIndex}, '${searchCode}')" class="btn-success rounded-50">Trang trước</button>
            <span id="pagination-index">${pageIndex +1}</span>
            <button onclick="pagingHandle(${pageIndex}, '${searchCode}', ${maxPage})" class="btn-success rounded-50"  >Trang sau</button>
        </div>
    </div>

    <script>
                const contextPath = "<%=request.getContextPath()%>";
                const pageIndex = document.getElementById("pagination-index").textContent;
                const searchCode = document.getElementById("search").value.trim();
                function submitFormHandler() {
                let form = document.getElementById("orderCode");
                        form.value = form.value.trim();
                        if (form.value === "") {
                window.alert("Hãy nhập code để search!");
                } else {
                document.getElementById("searchForm").submit();
                }
                }
        function pagingHandle(index, code, max) {
        console.log(index);
                if (typeof max === 'undefined') {
        index = index - 1;
        } else {
        ++index;
        }
        let url = contextPath + "/GetAllCountries?index=" + index + "&search=" + code
                window.location.href = url.toString();
        }
    </script>
</body>
</html>