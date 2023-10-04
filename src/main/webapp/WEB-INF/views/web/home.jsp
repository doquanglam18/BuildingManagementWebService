<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingListUrl" value="/admin/building-list"/>
<c:url var="loadStaffAPI" value="/api/building"/>
<c:url var="builingEditUrl" value="/admin/building-edit"/>
<c:url var="builingInsertUrl" value="/admin/building-insert"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
</head>
<body>
<!-- Page Content -->
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
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm kiếm</h4>
                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <%--<form:form modelAttribute="modelSearch" action="${buildingListUrl}" id="listForm" method="GET">--%>
                                    <%--<div class="form-horizontal">--%>
                                        <%--<div class="form-group">--%>
                                            <%--<div class="col-sm-6">--%>
                                                <%--<label for="name">Tên tòa nhà</label>--%>
                                                <%--<input type="text" id="name" name="name" value="${modelSearch.name}" class="form-control" />--%>
                                            <%--</div>--%>
                                            <%--<div class="col-sm-6">--%>
                                                <%--<label for="floorArea">Diện tích sàn</label>--%>
                                                <%--<input type="text" id="floorArea" name="floorArea" value="${modelSearch.floorArea}" class="form-control" />--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="form-group">--%>
                                            <%--<div class="col-sm-4">--%>
                                                <%--<label for="district">Quận hiện có</label><br>--%>
                                                    <%--&lt;%&ndash;<select class="from-control" id="district">&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;<option value="">---Chọn quận---</option>&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;<option name="district" value="Q1">Quận 1</option>&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;<option name="district" value="Q2">Quận 2</option>&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;<option name="district" value="Q4">Quận 4</option>&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;</select>&ndash;%&gt;--%>
                                                <%--<form:select path="district">--%>
                                                    <%--<form:option value="" label="--Chọn quận--"/>--%>
                                                    <%--<form:options items="${districtMaps}"/>--%>
                                                <%--</form:select>--%>
                                            <%--</div>--%>
                                            <%--<div class="col-sm-4">--%>
                                                <%--<label for="ward">Phường</label>--%>
                                                <%--<input type="text" id="ward" name="ward" value="${modelSearch.ward}" class="form-control" />--%>
                                            <%--</div>--%>
                                            <%--<div class="col-sm-4">--%>
                                                <%--<label for="street">Đường</label>--%>
                                                <%--<input type="text" id="street" name="street" value="${modelSearch.street}" class="form-control" />--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="form-group">--%>
                                            <%--<div class="col-sm-4">--%>
                                                <%--<label for="numberOfBasement">Số tầng hầm</label>--%>
                                                <%--<input type="text" id="numberOfBasement" name="numberOfBasement" value="${modelSearch.numberOfBasement}" class="form-control" />--%>
                                            <%--</div>--%>
                                            <%--<div class="col-sm-4">--%>
                                                <%--<label for="direction">Hướng</label>--%>
                                                <%--<input type="text" id="direction" class="form-control" />--%>
                                            <%--</div>--%>
                                            <%--<div class="col-sm-4">--%>
                                                <%--<label for="level">Hạng</label>--%>
                                                <%--<input type="text" id="level"  class="form-control" />--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="form-group">--%>
                                            <%--<div class="col-sm-3">--%>
                                                <%--<label for="areaFrom">Diện tích từ</label>--%>
                                                <%--<input type="text" id="areaFrom" name="areaFrom" value="${modelSearch.areaFrom}" class="form-control" />--%>
                                            <%--</div>--%>
                                            <%--<div class="col-sm-3">--%>
                                                <%--<label for="areaTo">Diện tích đến</label>--%>
                                                <%--<input type="text" id="areaTo" name="areaTo" value="${modelSearch.areaTo}" class="form-control" />--%>
                                            <%--</div>--%>
                                            <%--<div class="col-sm-3">--%>
                                                <%--<label for="rentPriceFrom">Giá thuê từ</label>--%>
                                                <%--<input type="text" id="rentPriceFrom" name="rentPriceFrom" value="${modelSearch.rentPriceFrom}" class="form-control" />--%>
                                            <%--</div>--%>
                                            <%--<div class="col-sm-3">--%>
                                                <%--<label for="rentPriceTo">Giá thuê đến</label>--%>
                                                <%--<input type="text" id="rentPriceTo" name="rentPriceTo" value="${modelSearch.rentPriceTo}" class="form-control" />--%>
                                            <%--</div>--%>
                                        <%--</div><!-- /.col -->--%>
                                        <%--<div class="form-group">--%>
                                            <%--<div class="col-sm-4">--%>
                                                <%--<label for="nameOfManager">Tên quản lý</label>--%>
                                                <%--<input type="text" id="nameOfManager" name="nameOfManager" value="${modelSearch.nameOfManager}"  class="form-control"/>--%>
                                            <%--</div>--%>
                                            <%--<div class="col-sm-4">--%>
                                                <%--<div>--%>
                                                    <%--<label for="phoneOfManager">Điện thoại quản lý</label>--%>
                                                    <%--<input type="text" id="phoneOfManager" name="phoneOfManager" value="${modelSearch.phoneOfManager}" class="form-control" />--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                            <%--<div class="col-sm-3">--%>
                                                <%--<label >Nhân viên hiện có</label> <br>--%>
                                                <%--<form:select path="staffId">--%>
                                                    <%--<form:option value="" label="--Chọn nhân viên--"/>--%>
                                                    <%--<form:options items="${staffMaps}"/>--%>
                                                <%--</form:select>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="form-group">--%>
                                                <%--&lt;%&ndash;<label class="col-sm-3 control-label no-padding-right" for="buildingTypes"> Loại tòa nhà </label>&ndash;%&gt;--%>
                                            <%--<div class="col-sm-9">--%>
                                                    <%--&lt;%&ndash;<label class="checkbox-inline"><input type="checkbox" name="buildingTypes" value="TANG_TRET" id="buildingTypes">Tầng trệt</label>&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;<label class="checkbox-inline"><input type="checkbox" name="buildingTypes" value="NGUYEN_CAN" id="buildingTypes">Nguyên căn</label>&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;<label class="checkbox-inline"><input type="checkbox" name="buildingTypes" value="NOI_THAT" id="buildingTypes">Nội thất</label>&ndash;%&gt;--%>
                                                <%--<c:forEach var="item" items="${buildingTypeMaps}">--%>
                                                    <%--<label class="checkbox-inline"><input type="checkbox" name="buildingTypes" value="${item.key}" id="buildingTypes">${item.value}</label>--%>
                                                <%--</c:forEach>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="form-group">--%>
                                            <%--<div class="col-sm-9">--%>
                                                <%--<button type="button" class="btn btn-primary" id="btnSearch">Tìm kiếm</button>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</form:form>--%>
                            </div>
                        </div>
                    </div>
                    <div class="pull-right">
                        <button class="btn btn-white btn-info btn-bold" data-toggle="tolltip" title="Thêm tòa nhà" id="btnAddBuilding">
                            <i class="fa fa-plus-circle">
                                <a href="<c:url value='/admin/building-list'/>">
                                </a>
                            </i>
                        </button>
                        <button class="btn btn-white btn-warning btn-bold" data-toggle="tolltip" title="Xóa tòa nhà" id="btnDeleteBuilding">
                            <i class="fa fa-trash" aria-hidden="true"></i>
                        </button>
                    </div>
                </div><!-- /.row -->
            </div>
        </div>
    </div>
</div>
<!-- /.container -->

</body>

</html>