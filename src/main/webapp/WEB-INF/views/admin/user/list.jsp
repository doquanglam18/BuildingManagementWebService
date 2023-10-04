<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@include file="/common/taglib.jsp" %>
<c:url var="loadStaffAPI" value="/api/building"/>
<c:url var="formUrl" value="/admin/customer-list"/>
<c:url var="formAjax" value="/api/customer"/>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<html>

<head>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <title>
        <%--<spring:message code="label.user.list"/>--%>
            Danh sách người dùng
    </title>
</head>

<body>
<div class="main-content">
<%--    <form:form modelAttribute="model" action="${formUrl}" id="listForm" method="GET">--%>
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="<c:url value="/admin/home"/>">
                            <%--<spring:message code="label.home"/>--%>
                            Trang chủ
                        </a>
                    </li>
                    <li class="active">
                        <%--<spring:message code="label.user.list"/>--%>
                            Danh sách người dùng
                    </li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <c:if test="${messageResponse!=null}">
                            <div class="alert alert-block alert-${alert}">
                                <button type="button" class="close" data-dismiss="alert">
                                    <i class="ace-icon fa fa-times"></i>
                                </button>
                                    ${messageResponse}
                            </div>
                        </c:if>
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box table-filter">
                                    <div class="widget-header">
                                        <h4 class="widget-title">
                                            <%--<spring:message code="label.search"/>--%>
                                            Tìm kiếm
                                        </h4>
                                        <div class="widget-toolbar">
                                            <a href="#" data-action="collapse">
                                                <i class="ace-icon fa fa-chevron-up"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <form:form modelAttribute="model" action="${buildingListUrl}" id="listForm" method="GET">
                                                <div class="form-horizontal">
                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div>
                                                                <label for="fullName">Tên khách hàng</label>
                                                                <input type="text" id="fullName" name="fullName" value="${model.fullName}" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div>
                                                                <label for="phone">Di động</label>
                                                                <input type="text" id="phone" name="phone" value="${model.phone}" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div>
                                                                <label for="email">Email</label> <br>
                                                                <input type="text" id="email" name="email" value="${model.email}" class="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group ">
                                                        <security:authorize access="hasRole('MANAGER')">
                                                            <div class="col-sm-6">
                                                                <label for="nameOfStaff">Nhân viên phụ trách</label>
                                                                    <%--<form:select path="staffId">--%>
                                                                <form:select path="nameOfStaff" cssStyle="margin-left: 40px">
                                                                    <form:option value="" label="--Chọn nhân viên--"/>
                                                                    <form:options items="${staffMaps}"/>
                                                                </form:select>
                                                            </div>
                                                        </security:authorize>
                                                        <div class="col-sm-6" >
<%--                                                            <label class="col-sm-2 control-label" ></label>--%>
                                                            <button id="btnSearch" type="button" class="btn btn-sm btn-success" style="margin-left: 524px">
                                                                    <%--spring:message code="label.search"/>--%>
                                                                Tìm kiếm
                                                                <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                            </button>
                                                        </div>
                                                    </div>
<%--                                                    <div class="form-group">--%>
<%--                                                        <label class="col-sm-2 control-label"></label>--%>
<%--                                                        --%>
<%--                                                    </div>--%>
                                                </div>
                                            </form:form>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-btn-controls">
                                    <div class="pull-right tableTools-container">
                                        <div class="dt-buttons btn-overlap btn-group">
                                            <security:authorize access="hasRole('MANAGER')">
                                                <a flag="info"
                                                   class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                                   data-toggle="tooltip"
                                                   <%--title='<spring:message code="label.user.add"/>'--%>
                                                   title="Thêm người dùng"
                                                   href='<c:url value="/admin/user-edit"/>'>
                                                                <span>
                                                                    <i class="fa fa-plus-circle bigger-110 purple"></i>
                                                                </span>
                                                </a>
                                                <button class="btn btn-white btn-warning btn-bold" data-toggle="tolltip" title="Xóa người dùng" id="btnDeleteCustomer">
                                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                                </button>
                                            </security:authorize>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <display:table name="model.listResult" cellspacing="0" cellpadding="0"
                                                   requestURI="${formUrl}" partialList="true" sort="external"
                                                   size="${model.totalItems}" defaultsort="2" defaultorder="ascending"
                                                   id="tableList" pagesize="${model.maxPageItems}"
                                                   export="false"
                                                   class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                                   style="margin: 3em 0 1.5em;">
                                        <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                                        headerClass="center select-cell">
                                            <fieldset>
                                                <input type="checkbox" name="checkList" value="${tableList.id}"
                                                       id="checkbox_${tableList.id}" class="check-box-element"/>
                                            </fieldset>
                                        </display:column>
                                        <display:column headerClass="text-left" property="fullName" title="Tên"/>
                                        <display:column headerClass="text-left" property="nameOfStaff" title="Nhân viên quản lý"/>
                                        <display:column headerClass="text-left" property="phone" title="Di động"/>
                                        <display:column headerClass="text-left" property="email" title="Email"/>
                                        <display:column headerClass="text-left" property="demand" title="Nhu cầu"/>
                                        <display:column headerClass="text-left" property="createdBy" title="Người nhập"/>
                                        <display:column headerClass="text-left" property="createdDate" title="Ngày nhập"/>
                                        <display:column headerClass="text-left" property="status" title="Tình trạng"/>
                                        <display:column headerClass="col-actions" title="Thao tác">
<%--                                            <c:if test="${tableList.roleCode != 'ADMIN'}">--%>
                                                <security:authorize access="hasRole('MANAGER')">
                                                    <button class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip" data-placement="bottom" title="Giao nhân viên" onclick="assignmentStaff(${tableList.id})">
                                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </button>
                                                </security:authorize>
<%--                                                <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"--%>
<%--                                                   title="Cập nhật người dùng"--%>
<%--                                                   href='<c:url value="/admin/user-edit-${tableList.id}"/>'>--%>
<%--                                                    <i class="fa fa-wrench" aria-hidden="true"></i>--%>
<%--                                                </a>--%>
                                                <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                   title="Thông tin người dùng"
                                                   href='<c:url value="/admin/profile-${tableList.id}"/>'>
                                                    <i class="fa fa-eye" aria-hidden="true"></i>
                                                </a>
<%--                                            </c:if>--%>
<%--                                            <c:if test="${tableList.roleCode == 'ADMIN'}">--%>
<%--                                               <p>Không đươc thao tác</p>--%>
<%--                                            </c:if>--%>
                                        </display:column>
                                    </display:table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
<%--    </form:form>--%>
</div>

<div class="modal fade" id="assignmentStaffModal" role="dialog">
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
                <input type="hidden" id="customerId" name="customerId" value="">
            </div>
            <div class="model-footer">
                <button type="button" class="btn btn-default" id="btnAssignStaff">Giao nhân viên</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<script >
    $("#btnDeleteCustomer").click(function (e) {
        e.preventDefault();
        var data = {};
        var customerIds = $('input[name=checkList]:checked').map(function() {
            return $(this).val();
        }).get();
        data["buildingIds"] = customerIds;
        deleteCustomer(data);
    });
    //check
    function deleteCustomer(data){
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/api/customer",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log('success');
                window.location.href = "<c:url value='/admin/customer-list?message=success'/>";
            },
            error: function (response){
                console.log('failed');
                console.log(response);
                window.location.href = "<c:url value='/admin/customer-list?message=error'/>";
            }
        });
    }
    <%--$(document).ready(function () {--%>
    <%--    var someJsVar = "<c:out value='${addOrEditNews}'/>";--%>
        $('#btnSearch').click(function () {
            $('#listForm').submit();
        });
    // });

    function assignmentStaff(customerId){
        openModalAssingmentStaff();
        //thêm tham số buildingId vào loadStaff()
        loadStaffs(customerId)
        $('#customerId').val(customerId);
        console.log($('#customerId').val());
    }

    function loadStaffs(customerId) {
        console.log(customerId);
        $.ajax({
            type: "GET",
            url: "/api/customer/staffs?customerid="+customerId+"",
            dataType: "json",
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
    function openModalAssingmentStaff(){
        $('#assignmentStaffModal').modal();
    }

    $('#btnAssignStaff').click(function (e) {
        e.preventDefault();
        var data = {};
        data["customerId"] = $('#customerId').val();
        var staffs = [];
        $('input[type=checkbox]:checked').each(function() {
            staffs.push($(this).val());
        });
        data["staffId"] = staffs;
        assignmentCustomer(data);
    });
    function assignmentCustomer(data){
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/customer/user-assignment",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log('success');
                window.location.href = "<c:url value='/admin/customer-list?message=success'/>";
            },
            error: function (response){
                console.log('failed');
                console.log(response);
                window.location.href = "<c:url value='/admin/customer-list?message=error'/>";
            }
        });
    }
    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            event.preventDefault();
            var dataArray = $('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteUser(dataArray);
        });
    }

    function deleteUser(data) {
        $.ajax({
            url: '${formAjax}/',
            type: 'DELETE',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/customer-list?message=delete_success'/>";
            },
            error: function (res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/customer-list?message=error_system'/>";
            }
        });
    }


    // $('#btnSearch').click(function (e) {
    //     e.preventDefault();
    //     $('#listForm').submit();
    // });
</script>
</body>

</html>