<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/api/customer"/>
<html>
<head>
    <title>Chỉnh sửa người dùng</title>
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
                                    <form:input path="fullName" id="fullName" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                        Số điện thoại
                                </label>
                                <div class="col-sm-9">
                                    <form:input path="phone" id="phone" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                        <%--<spring:message code="label.fullname"/>--%>
                                    Email
                                </label>
                                <div class="col-sm-9">
                                    <form:input path="email" id="email" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                        <%--<spring:message code="label.fullname"/>--%>
                                    Nhu cầu
                                </label>
                                <div class="col-sm-9">
                                    <form:input path="demand" id="demand" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                        <%--<spring:message code="label.fullname"/>--%>
                                    Ghi chú
                                </label>
                                <div class="col-sm-9">
                                    <form:input path="note" id="note" cssClass="form-control"/>
                                </div>
                            </div>
                            <!--Btn-->
                            <div class="col-sm-12">
                                    <label class="col-sm-3 control-label no-padding-right message-info"></label>
                                    <input type="button" class="btn btn-white btn-warning btn-bold"
                                           value="Cập nhật người dùng" id="btnUpdateUser"/>
                            </div>
                            <!--Btn-->
                            <form:hidden path="id" id="userId"/>
                            </form:form>
                    </div>
                </div>
            </div>
            <br><br>
            <div class="col-xs-12" id="assignmentStaffModal" role="dialog" >
                <%--    <div class="modal-dialog">--%>
                <div class="col-xs-12">
                    <div class="modal-content col-xs-12">
                        <c:forEach var="item" items="${load_transaction}">
                            <div class="modal-header" style="display: flex">
                                <h4 class="modal-title" style="margin-right: 10px">${item.transactionTypeName}</h4>
                                <button flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
                                   title="Thêm giao dịch" onclick="newTransaction('${item.transactionTypeName}')">
                                    <span>
                                        <i class="fa fa-plus-circle bigger-110 purple"></i>
                                    </span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <table class="table table-bordered" id="staffList">
                                    <thead>
                                    <tr>
                                        <th>Ngày tạo</th>
                                        <th>Ghi chú</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="infor" items="${item.transactionResponseDTOS}">
                                        <tr>
                                            <td class="col-xs-3">${infor.createdDate}</td>
                                            <td class="col-xs-9">${infor.note}</td>
                                        </tr>
                                    </c:forEach>
                                        <tr>
                                            <td class="col-xs-3"></td>
                                            <td class="col-xs-9"><input name="${item.transactionTypeName}"></td>
                                        </tr>
                                    </tbody>
                                </table>
                                <input type="hidden" id="customerId" name="customerId" value="">
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

    <script>

        <%-- let inputElement = document.getElementById('transactionContent');--%>
        <%--inputElement.addEventListener('keydown', function(event) {--%>
        <%--    if (event.key === "Enter") {--%>
        <%--        var inputValue = inputElement.value;--%>
        <%--        console.log("Value of the input:", inputValue);--%>
        <%--        let dataArray = {};--%>
        <%--        dataArray["note"] = $('#transactionContent').val()--%>
        <%--        dataArray["customerId"] = ${model.id};--%>
        <%--        updateTransaction(dataArray);--%>
        <%--    }--%>
        <%--});--%>

        function newTransaction(transactionTypeName){
            let dataArray = {};
            dataArray["code"] = transactionTypeName;
            // let note = $('#' + transactionTypeName + '').val();
            let value = $('input[name="' + transactionTypeName + '"]').val();
            dataArray["note"] = value;
            dataArray["customerId"] = ${model.id};
            updateTransaction(dataArray);
        }

        function updateTransaction(data) {
            $.ajax({
                url: '${formUrl}/transaction',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (res) {
                    window.location.href = "<c:url value='/admin/profile-${model.id}?message=update_success'/>";
                },
                error: function (res) {
                    console.log(res);
                    window.location.href = "<c:url value='/admin/profile-${model.id}?message=error_system'/>";
                }
            });
        }

        $("#btnUpdateUser").click(function (event) {
            event.preventDefault();
            let dataArray = {};
            let formData = $('#formEdit').serializeArray();
            $.each(formData, function (index,v) {
                dataArray[""+v.name+""] = v.value;
            });
            dataArray["id"] = ${model.id};
            if ($('#userId').val() != "") {
                updateInfo(dataArray);
            }
        });

        function updateInfo(data) {
            $.ajax({
                url: '${formUrl}/profile',
                type: 'PUT',
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
    </script>
</body>
</html>
