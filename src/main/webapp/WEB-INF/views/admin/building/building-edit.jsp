<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="builingEditUrl" value="/admin/building-edit"/>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Thêm tòa nhà</title>
</head>
<body>

<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul>
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                        <form class="form-horizontal" role="form" id="formEdit">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="name"> Tên tòa nhà </label>
                                <div class="col-sm-9">
                                    <input type="text" id="name" class="form-control" name="name" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="ward"> Phường </label>
                                <div class="col-sm-9">
                                    <input type="text" id="ward" class="form-control" name="ward" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="street"> Đường </label>
                                <div class="col-sm-9">
                                    <input type="text" id="street" class="form-control" name="street" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="numberOfBasement"> Số tầng hầm </label>
                                <div class="col-sm-9">
                                    <input type="number" id="numberOfBasement" class="form-control" name="numberOfBasement" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="floorArea"> Diện tích sàn </label>
                                <div class="col-sm-9">
                                    <input type="number" id="floorArea" class="form-control" name="floorArea" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="direction"> Hướng </label>
                                <div class="col-sm-9">
                                    <input type="number" id="direction" class="form-control" name="direction" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="level"> Hạng </label>
                                <div class="col-sm-9">
                                    <input type="number" id="level" class="form-control" name="level" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="rentArea"> Diện tích thuê </label>
                                <div class="col-sm-9">
                                    <input type="text" id="rentArea" class="form-control" name="rentArea" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="rentPrice"> Giá thuê </label>
                                <div class="col-sm-9">
                                    <input type="text" id="rentPrice" class="form-control" name="rentPrice" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="rentAreaDescription"> Mô tả diện tích </label>
                                <div class="col-sm-9">
                                    <input type="text" id="rentAreaDescription" class="form-control" name="rentAreaDescription" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="serviceFee"> Phí dịch vụ </label>
                                <div class="col-sm-9">
                                    <input type="text" id="serviceFee" class="form-control" name="serviceFee" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="carFee"> Phí ô tô </label>
                                <div class="col-sm-9">
                                    <input type="text" id="carFee" class="form-control" name="carFee" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="motorbikeFee"> Phí mô tô </label>
                                <div class="col-sm-9">
                                    <input type="text" id="motorbikeFee" class="form-control" name="motorbikeFee" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="overtimeFee"> Phí ngoài giờ </label>
                                <div class="col-sm-9">
                                    <input type="text" id="overtimeFee" class="form-control" name="overtimeFee" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="electricityFee"> Tiền điện </label>
                                <div class="col-sm-9">
                                    <input type="text" id="electricityFee" class="form-control" name="electricityFee" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="deposit"> Đặt cọc </label>
                                <div class="col-sm-9">
                                    <input type="text" id="deposit" class="form-control" name="deposit" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="payment"> Thanh toán </label>
                                <div class="col-sm-9">
                                    <input type="text" id="payment" class="form-control" name="payment" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="fullname"> Tên quản lý </label>
                                <div class="col-sm-9">
                                    <input type="text" id="fullName" class="form-control" name="fullname" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="phone"> Số điện thoại quản lý </label>
                                <div class="col-sm-9">
                                    <input type="text" id="phone" class="form-control" name="phone" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="brokeragefee"> Phí môi giới </label>
                                <div class="col-sm-9">
                                    <input type="text" id="brokeragefee" class="form-control" name="brokeragefee" value=""/>
                                </div>
                            </div>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-3 no-padding-right">Hình đại diện</label>--%>
                                <%--<input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>--%>
                                <%--<div class="col-sm-9">--%>
                                    <%--<c:if test="${not empty model.image}">--%>
                                        <%--<c:set var="imagePath" value="/repository${model.image}"/>--%>
                                        <%--<img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${empty model.image}">--%>
                                        <%--<img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">--%>
                                    <%--</c:if>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <label class="col-sm-3 no-padding-right">Hình đại diện</label>
                                <input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>
                                <div class="col-sm-9">
                                    <c:if test="${not empty buildingModel.image}">
                                        <c:set var="imagePath" value="/repository${buildingModel.image}"/>
                                        <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                    </c:if>
                                    <c:if test="${empty buildingModel.image}">
                                        <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="district"> Quận </label>
                                <div class="col-sm-9">
                                    <form:form modelAttribute="buildingModel" action="${builingEditUrl}" id="listForm" method="GET">
                                        <form:select path="district">
                                            <form:option value="" label="--Chọn quận--"/>
                                            <form:options items="${districtMaps}"/>
                                        </form:select>
                                    </form:form>
                                </div>
                            </div>
                                <div class="form-group">
                                    <label style="padding-left: 210px" class="col-sm-3 control-label no-padding-right" for="buildingTypes"> Loại tòa nhà </label>
                                    <div class="col-sm-9">
                                        <%--<label class="checkbox-inline"><input type="checkbox" name="buildingTypes" value="TANG_TRET" id="buildingTypes">Tầng trệt</label>--%>
                                        <%--<label class="checkbox-inline"><input type="checkbox" name="buildingTypes" value="NGUYEN_CAN" id="buildingTypes">Nguyên căn</label>--%>
                                        <%--<label class="checkbox-inline"><input type="checkbox" name="buildingTypes" value="NOI_THAT" id="buildingTypes">Nội thất</label>--%>
                                        <c:forEach var="item" items="${buildingTypeMaps}">
                                            <label class="checkbox-inline"><input type="checkbox" name="buildingTypes" value="${item.key}" id="buildingTypes">${item.value}</label>
                                        </c:forEach>
                                    </div>
                                </div>
                            <div style="padding-left: 250px" class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"></label>
                                <div class="col-sm-9">
                                    <button type="button" class="btn btn-primary" id="btnAddBuilding">Thêm tòa nhà</button>
                                    <button type="button" class="btn btn-primary">Hủy</button>
                                </div>
                            </div>
                </div><!-- /.row -->
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
<script>

    var imageBase64 = '';
    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. Dat theo format sau: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });


    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    $('#btnAddBuilding').click(function (e){
        e.preventDefault();
        var data= {};
        var buildingTypesString = '';
        var formData = $('#formEdit').serializeArray();
        $.each(formData, function (index,v) {
            data[""+v.name+""] = v.value;
            if ('' !== imageBase64) {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }
        });
        // Lấy tất cả các input có tên buildingTypes
        var buildingTypesInputs = document.getElementsByName('buildingTypes');

        // Lưu trữ các giá trị được chọn vào một mảng
        for (let i = 0; i < buildingTypesInputs.length; i++) {
            if (buildingTypesInputs[i].checked) {
                buildingTypesString += buildingTypesInputs[i].value + ', ';
            }
        }
        buildingTypesString = buildingTypesString.slice(0, -2);
        data["buildingTypes"] = buildingTypesString;
        // api add building
        $.ajax({
            type: 'POST',
            url: '${buildingAPI}',
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log('success');
                window.location.href = "<c:url value='/admin/building-list?message=success'/>";
            },
            error: function (response){
                console.log('failed');
                console.log(response);
                window.location.href = "<c:url value='/admin/building-list?message=error'/>";
            }
        });
    });
</script>
</body>
</html>
