<%--
  Created by IntelliJ IDEA.
  User: Angelika
  Date: 01.08.2021
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/header.jsp"/>
<head>
    <title>Add product | Online-shop</title>
</head>
<body class="d-flex flex-column h-100">
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization.language" var="loc"/>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-6 my-sm-3">
            <c:if test="${sessionScope.role.name != 'admin'}">
                <div class="alert alert-danger fade show " role="alert">
                    <fmt:message bundle="${loc}" key="language.noRights"/>
                </div>
            </c:if>
            <c:if test="${sessionScope.role.name == 'admin'}">

                <c:if test="${param.message == 'error'}">
                    <div class="alert alert-danger fade show " role="alert">
                        <fmt:message bundle="${loc}" key="language.productAddingError"/>
                    </div>
                </c:if>
                <c:if test="${param.message == 'ok'}">
                    <div class="alert alert-success fade show " role="alert">
                        <fmt:message bundle="${loc}" key="language.productAddedSuccessfully"/>
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/online-shop?command=confirmAddingProduct" method="post">
                    <h4><fmt:message bundle="${loc}" key="language.enterProductDetails"/></h4>

                    <div class="mb-3">
                        <label for="title" class="form-label"><fmt:message bundle="${loc}" key="language.nameOfProduct"/></label>
                        <input type="text" class="form-control" name="product-name" id="title" placeholder="<fmt:message bundle="${loc}" key="language.nameOfProduct"/>" required>
                    </div>

                    <div class="row mb-3">
                        <div class="col-sm mb-3">
                            <label for="photo" class="form-label"><fmt:message bundle="${loc}" key="language.productPhoto"/></label>
                            <input type="text" id="photo" name="photo" class="form-control" placeholder="<fmt:message bundle="${loc}" key="language.productPhoto"/>" required>
                            <select id="image-select" class="form-select mt-3">
                                <option value="" disabled selected>Выберите изображение</option>
                            </select>
                            <div id="image-preview" class="mt-3"></div>
                        </div>

                        <div class="col-sm">
                            <label for="price" class="form-label"><fmt:message bundle="${loc}" key="language.price"/></label>
                            <input type="text" id="price" name="price" class="form-control" placeholder="100.15" pattern="^\d+\.\d{0,2}$" required>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-sm mb-3">
                            <label for="category" class="form-label"><fmt:message bundle="${loc}" key="language.category"/></label>
                            <input type="text" name="category" id="category" class="form-control" placeholder="<fmt:message bundle="${loc}" key="language.category"/>" required>
                        </div>

                        <div class="col-sm">
                            <fieldset class="form-group">
                                <div class="form-check">
                                    <input type="checkbox" name="availability" class="form-check-input" id="exampleCheck1">
                                    <label class="form-check-label" for="exampleCheck1"><fmt:message bundle="${loc}" key="language.availability"/></label>
                                </div>
                            </fieldset>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="description" class="form-label"><fmt:message bundle="${loc}" key="language.description"/></label>
                        <input type="text" class="form-control" name="description" id="description" placeholder="<fmt:message bundle="${loc}" key="language.description"/>" required>
                    </div>

                    <button class="btn btn-primary" type="submit"><fmt:message bundle="${loc}" key="language.add"/></button>
                </form>

                <script>
                    const contextPath = '${pageContext.request.contextPath}';
                    const images = [
                        "0-02-05-3fcaa3deed039e6dcd1b590ed9cdc3a2eb616cf1da32beaf0d60aa0b53bd7a2d_8d1836ac93da5040-825x412.jpeg",
                        "1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "banner_2-825x412.jpeg",
                        "cennikhomder-10.jpeg", "cennikhomder-11.jpeg", "cennikhomder-12.jpeg", "cennikhomder-13.jpeg",
                        "cennikhomder-14.jpeg", "cennikhomder-15.jpeg", "cennikhomder-16.jpeg", "cennikhomder-17.jpeg",
                        "cennikhomder-18.jpeg", "cennikhomder-19.jpeg", "cennikhomder-20.jpeg", "cennikhomder-21.jpeg",
                        "cennikhomder-22.jpeg", "cennikhomder-23.jpeg", "cennikhomder-24.jpeg", "cennikhomder-25.jpeg",
                        "cennikhomder-26.jpeg", "cennikhomder-27.jpeg", "cennikhomder-6.jpeg", "cennikhomder-7.jpeg",
                        "cennikhomder-8.jpeg", "cennikhomder-9.jpeg", "lakogalogo(logotip2021)_10.jpeg", "online-shop.jpg",
                        "ramkidetali-200x200.jpeg", "rasprodazha-200x200.jpeg", "sputnik1b-200x200.jpeg", "tita7-8-200x200.jpeg",
                        "titulnaja2-200x200.jpeg"
                    ];

                    const imageSelect = document.getElementById('image-select');
                    const photoInput = document.getElementById('photo');
                    const imagePreview = document.getElementById('image-preview');

                    images.forEach(image => {
                        const option = document.createElement('option');
                        option.value = image;
                        option.textContent = image;
                        imageSelect.appendChild(option);
                    });

                    imageSelect.addEventListener('change', () => {
                        const selectedImage = imageSelect.value;
                        photoInput.value = selectedImage;

                        // Очистить текущее изображение предпросмотра
                        imagePreview.innerHTML = '';

                        // Показать выбранное изображение в предпросмотре
                        const imgElement = document.createElement('img');
                        imgElement.src = `http://127.0.0.1:8080/static/images/` + selectedImage;
                        imgElement.alt = selectedImage;
                        imgElement.style.width = '200px';
                        imgElement.style.height = '200px';
                        imagePreview.appendChild(imgElement);
                    });
                </script>

                <hr class="dropdown-divider">
                <a href="/online-shop?command=main"><fmt:message bundle="${loc}" key="language.home"/></a>
            </c:if>
        </div>
    </div>
</div>
</body>
<jsp:include page="fragments/footer.jsp"/>
</html>