<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Users Management</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${path}/resources/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path}/resources/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${path}/resources/easyui/demo.css">
	<script type="text/javascript" src="${path}/resources/easyui/jquery.min.js"></script>
<%--     <script type="text/javascript" src="${path}/jquery-1.4.4.min.js"></script> --%>
	<script type="text/javascript" src="${path}/resources/easyui/jquery.easyui.min.js"></script>
</head>
<body>
    <h2>DataGrid Pagination Demo</h2>
    <div class="demo-info" style="margin-bottom:10px">
        <div class="demo-tip icon-tip">&nbsp;</div>
        <div>Click the page bar to change page number or page size.</div>
    </div>
    <p>
        Pagination on 
        <select onchange="changeP(this.value)">
            <option>bottom</option>
            <option>top</option>
            <option>both</option>
        </select>
    </p>
    
    <table id="tt" class="easyui-datagrid" style="width:700px;height:250px"
            url="${path}/blog/getUser"
            title="Load Data" iconCls="icon-save"
            rownumbers="true" pagination="true"
         >
        <thead>
            <tr>
                <th field="USER_ID" width="80">User ID</th>
                <th field="USER_LOGIN_NAME" width="120">Login Name</th>
                <th field="USER_FULL_NAME" width="80" align="right">Full Name</th>
            </tr>
        </thead>
    </table>
    <script type="text/javascript">
        function changeP(pos){
            $('#tt').datagrid('loadData',[]);
            $('#tt').datagrid({pagePosition:pos});
        }
    </script>
</body>
</html>