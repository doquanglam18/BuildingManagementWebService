<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/api/customer"/>
<html>
<head>
    <title>Thêm người dùng</title>
</head>
<body>
<div class="main-content">
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
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa người dùng</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>
                    <div id="profile">
                        <form:form id="formEdit" class="form-horizontal" modelAttribute="model">
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                    Tên đầy đủ
                                </label>
                                <div class="col-sm-9">
                                    <input name="fullName" id="fullName" class="form-control" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                    Số điện thoại
                                </label>
                                <div class="col-sm-9">
                                    <input name="phone" id="phone" class="form-control"/>
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                        <%--<spring:message code="label.fullname"/>--%>
                                    Email
                                </label>
                                <div class="col-sm-9">
                                    <input name="email" id="email" class="form-control"/>
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                        <%--<spring:message code="label.fullname"/>--%>
                                    Nhu cầu
                                </label>
                                <div class="col-sm-9">
                                    <input name="demand" id="demand" class="form-control"/>
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                        <%--<spring:message code="label.fullname"/>--%>
                                    Ghi chú
                                </label>
                                <div class="col-sm-9">
                                    <input name="note" id="note" class="form-control"/>
                                </div>
                            </div>
                            <!--Btn-->
                            <div class="col-sm-12">
                                <label class="col-sm-3 control-label no-padding-right message-info"></label>
                                <input type="button" class="btn btn-white btn-warning btn-bold"
                                       value="Thêm người dùng" id="btnAddUser"/>
                            </div>
                            <!--Btn-->
                            <form:hidden path="id" id="userId"/>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $("#btnAddUser").click(function (event) {
        event.preventDefault();
        let dataArray = {};
        let formData = $('#formEdit').serializeArray();
        $.each(formData, function (index,v) {
            dataArray[""+v.name+""] = v.value;
        });
        dataArray["id"] = ${model.id}
        updateInfo(dataArray);
    });

    function updateInfo(data) {
        $.ajax({
            url: '${formUrl}/profile',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/profile-"+res.id+"?message=update_success'/>";
            },
            error: function (res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/profile-"+id+"?message=error_system'/>";
            }
        });
    }

    $('#btnResetPassword').click(function (event) {
        event.preventDefault();
        $('#loading_image').show();
        resetPassword($('#userId').val());
    });

    function addUser(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                $('#loading_image').hide();
                window.location.href = "<c:url value='/admin/user-edit-"+res.id+"?message=insert_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/user-edit-"+res.id+"?message=error_system'/>";
            }
        });
    }

    function updateUser(data, id) {
        $.ajax({
            url: '${formUrl}/' + id,
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/user-edit-"+res.id+"?message=update_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/user-edit-"+id+"?message=error_system'/>";
            }
        });
    }

    function resetPassword(id) {
        $.ajax({
            url: '${formUrl}/password/'+id+'/reset',
            type: 'PUT',
            dataType: 'json',
            success: function (res) {
                $('#loading_image').hide();
                window.location.href = "<c:url value='/admin/user-edit-"+res.id+"?message=reset_password_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/user-edit-"+id+"?message=error_system'/>";
            }
        });
    }
</script>
</body>
</html>
