<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingListUrl" value="/admin/building-list"/>
<c:url var="loadStaffAPI" value="/api/building"/>
<c:url var="builingEditUrl" value="/admin/building-edit"/>
<c:url var="builingInsertUrl" value="/admin/building-insert"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
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
                                <form:form modelAttribute="modelSearch" action="${buildingListUrl}" id="listForm" method="GET">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-sm-6">
                                                <label for="name">Tên tòa nhà</label>
                                                <input type="text" id="name" name="name" value="${modelSearch.name}" class="form-control" />
                                            </div>
                                            <div class="col-sm-6">
                                                <label for="floorArea">Diện tích sàn</label>
                                                <input type="text" id="floorArea" name="floorArea" value="${modelSearch.floorArea}" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-4">
                                                <label for="district">Quận hiện có</label><br>
                                                    <%--<select class="from-control" id="district">--%>
                                                    <%--<option value="">---Chọn quận---</option>--%>
                                                    <%--<option name="district" value="Q1">Quận 1</option>--%>
                                                    <%--<option name="district" value="Q2">Quận 2</option>--%>
                                                    <%--<option name="district" value="Q4">Quận 4</option>--%>
                                                    <%--</select>--%>
                                                <form:select path="district">
                                                    <form:option value="" label="--Chọn quận--"/>
                                                    <form:options items="${districtMaps}"/>
                                                </form:select>
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="ward">Phường</label>
                                                <input type="text" id="ward" name="ward" value="${modelSearch.ward}" class="form-control" />
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="street">Đường</label>
                                                <input type="text" id="street" name="street" value="${modelSearch.street}" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-4">
                                                <label for="numberOfBasement">Số tầng hầm</label>
                                                <input type="text" id="numberOfBasement" name="numberOfBasement" value="${modelSearch.numberOfBasement}" class="form-control" />
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="direction">Hướng</label>
                                                <input type="text" id="direction" class="form-control" />
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="level">Hạng</label>
                                                <input type="text" id="level"  class="form-control" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-3">
                                                <label for="areaFrom">Diện tích từ</label>
                                                <input type="text" id="areaFrom" name="areaFrom" value="${modelSearch.areaFrom}" class="form-control" />
                                            </div>
                                            <div class="col-sm-3">
                                                <label for="areaTo">Diện tích đến</label>
                                                <input type="text" id="areaTo" name="areaTo" value="${modelSearch.areaTo}" class="form-control" />
                                            </div>
                                            <div class="col-sm-3">
                                                <label for="rentPriceFrom">Giá thuê từ</label>
                                                <input type="text" id="rentPriceFrom" name="rentPriceFrom" value="${modelSearch.rentPriceFrom}" class="form-control" />
                                            </div>
                                            <div class="col-sm-3">
                                                <label for="rentPriceTo">Giá thuê đến</label>
                                                <input type="text" id="rentPriceTo" name="rentPriceTo" value="${modelSearch.rentPriceTo}" class="form-control" />
                                            </div>
                                        </div><!-- /.col -->
                                        <div class="form-group">
                                            <div class="col-sm-4">
                                                <label for="nameOfManager">Tên quản lý</label>
                                                <input type="text" id="nameOfManager" name="nameOfManager" value="${modelSearch.nameOfManager}"  class="form-control"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="phoneOfManager">Điện thoại quản lý</label>
                                                    <input type="text" id="phoneOfManager" name="phoneOfManager" value="${modelSearch.phoneOfManager}" class="form-control" />
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <label >Nhân viên hiện có</label> <br>
                                                <form:select path="staffId">
                                                    <form:option value="" label="--Chọn nhân viên--"/>
                                                    <form:options items="${staffMaps}"/>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <%--<label class="col-sm-3 control-label no-padding-right" for="buildingTypes"> Loại tòa nhà </label>--%>
                                            <div class="col-sm-9">
                                                <%--<label class="checkbox-inline"><input type="checkbox" name="buildingTypes" value="TANG_TRET" id="buildingTypes">Tầng trệt</label>--%>
                                                <%--<label class="checkbox-inline"><input type="checkbox" name="buildingTypes" value="NGUYEN_CAN" id="buildingTypes">Nguyên căn</label>--%>
                                                <%--<label class="checkbox-inline"><input type="checkbox" name="buildingTypes" value="NOI_THAT" id="buildingTypes">Nội thất</label>--%>
                                                <c:forEach var="item" items="${buildingTypeMaps}">
                                                    <label class="checkbox-inline"><input type="checkbox" name="buildingTypes" value="${item.key}" id="buildingTypes">${item.value}</label>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-9">
                                                <button type="button" class="btn btn-primary" id="btnSearch">Tìm kiếm</button>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                    <div class="pull-right">
                        <security:authorize access="hasRole('MANAGER')">
                            <button class="btn btn-white btn-info btn-bold" data-toggle="tolltip" title="Thêm tòa nhà" id="btnAddBuilding">
                                <i class="fa fa-plus-circle">
                                    <a href="<c:url value='/admin/building-list'/>">
                                    </a>
                                </i>
                            </button>
                            <button class="btn btn-white btn-warning btn-bold" data-toggle="tolltip" title="Xóa tòa nhà" id="btnDeleteBuilding">
                                <i class="fa fa-trash" aria-hidden="true"></i>
                            </button>
                        </security:authorize>
                    </div>
                </div><!-- /.row -->
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <display:table name="modelSearch.listResult" cellspacing="0" cellpadding="0"
                                   requestURI="${buildingListUrl}" partialList="true" sort="external"
                                   size="${modelSearch.totalItems}" defaultsort="2" defaultorder="ascending"
                                   id="tableList" pagesize="${modelSearch.maxPageItems}"
                                   export="false"
                                   class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                   style="margin: 3em 0 1.5em;">
                        <display:column >
                            <fieldset>
                                <input type="checkbox" name="checkList" value="${tableList.id}"
                                       id="checkbox_${tableList.id}" class="check-box-element"/>
                            </fieldset>
                        </display:column>
                        <display:column property = "createdDate" headerClass="text-left"/>
                        <display:column property = "name" headerClass="text-left"/>
                        <display:column property = "address" headerClass="text-left"/>
                        <display:column property = "nameOfUser" headerClass="text-left"/>
                        <display:column property = "phoneOfUser" headerClass="text-left"/>
                        <display:column property = "floorArea" headerClass="text-left"/>
                        <display:column property = "rentPrice" headerClass="text-left"/>
                        <display:column property = "serviceFee" headerClass="text-left"/>
                        <display:column title="Thao tác">
                            <button class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip" data-placement="bottom" title="Giao tòa nhà" onclick="assignmentBuilding(${tableList.id})">
                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip" data-placement="bottom" title="Sửa tòa nhà" onclick="insertBuilding(${tableList.id})">
                                <i class="fa fa-wrench" aria-hidden="true">
                                    <a href="<c:url value='/admin/building-insert'/>">
                                    </a>
                                </i>
                            </button>
                        </display:column>
                    </display:table>
                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<div class="modal fade" id="assignmentBuildingModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal-content -->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="staffList">
                    <thead>
                    <tr>
                        <th>Chọn nhân viên</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--<tr>--%>
                        <%--<td><input type="checkbox" value="2" id="checkbox_2"></td>--%>
                        <%--<td>Nguyễn Văn B</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td><input type="checkbox" value="3" id="checkbox_3"></td>--%>
                        <%--<td>Nguyễn Văn C</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td><input type="checkbox" value="4" id="checkbox_4"></td>--%>
                        <%--<td>Nguyễn Văn D</td>--%>
                    <%--</tr>--%>
                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="model-footer">
                <button type="button" class="btn btn-default" id="btnAssignBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<script>
    function insertBuilding(buildingId) {
        window.location.href = "/admin/building-insert?buildingId=" + buildingId;
    }
    function assignmentBuilding(buildingId){
        openModalAssingmentBuilding();
        //thêm tham số buildingId vào loadStaff()
        loadStaff(buildingId)
        $('#buildingId').val(buildingId);
        console.log($('#buildingId').val());
    }

    function loadStaff(buildingId) {
        $.ajax({
            type: "GET",
            <%--url: "${loadStaffAPI}/1/staffs",--%>
            url: "${loadStaffAPI}/staffs?buildingid="+buildingId+"",
            // data: JSON.stringify(data),
            dataType: "json",
            // contentType: "application/json",1
            success: function (response) {
                console.log('success');
                var row = '';
                $.each(response.data, function (index, item) {
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value=' + item.staffId + ' id="checkbox_' + item.staffId + '" class="check-box-element" ' + item.check + '/></td>';
                    row += '<td class="text-center">' + item.fullName + '</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
            },
            error: function (response){
                console.log('failed');
                console.log(response);
            }
        });
    }

    function openModalAssingmentBuilding(){
        $('#assignmentBuildingModal').modal();
    }
    $('#btnAssignBuilding').click(function (e) {
        e.preventDefault();
        var data = {};
        data["buildingId"] = $('#buildingId').val();
        // var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map( function () {
        //     return $(this).val();}).get();
        // var staffs = $('#staffList').find('fieldset input[type=checkbox]:checked').map(function () {
        //     return $(this).val();
        // }).get();
        var staffs = [];
        $('input[type=checkbox]:checked').each(function() {
            staffs.push($(this).val());
        });
        data["staffId"] = staffs;
        assignmentStaff(data);
    });
    function assignmentStaff(data){
        $.ajax({
            type: "POST",
            url: "http://localhost:8080${loadStaffAPI}/user-assignment",
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
    }
    $("#btnAddBuilding").click(function(){
        // chuyển hướng đến trang "admin/building-edit"
        window.location.href = "/admin/building-edit";
    });
    
    $("#btnDeleteBuilding").click(function (e) {
        e.preventDefault();
        var data = {};
        var buildingIds = $('input[name=checkList]:checked').map(function() {
            return $(this).val();
        }).get();
        data["buildingIds"] = buildingIds;
        deleteBuilding(data);
    });
    //check
    function deleteBuilding(data){
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/api/building",
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
    }
    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    });
</script>

</body>
</html>