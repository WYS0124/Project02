//分页查询功能
$(function () {
    //1使用datagrid组件绑定数据
    $("#dg").datagrid({
        url:'getTypeData',
        pagination:true,
        pageSize:3,
        pageList:[3,5,8,10,20],
        toolbar:'#ToolBar',
        columns:[[
            {checkbox:true,width:100,align:'center'},
            {field:'id',title:'编号',width:100,align:'right'},
            {field:'name',title:'区域名称',width:100,align:'right'},
            {field:'opt',title:'编辑|操作', width:80,
                formatter: function(value,row,index){
                    return "<a href='javascript:gotoup("+row.id+")'>修改</a> <a href='javascript:delType("+row.id+")'>删除</a>";
                }
            }
        ]]
    })
});

//打开添加添加
function goAdd() {
    //打开对话框
    $("#AddDialog").dialog("open").dialog('setTitle',"添加区域");
}

//关闭窗口
function CloseDialog() {
    $("#AddDialog").dialog("close");
}
//关闭窗口
function CloseDialog() {
    $("#upDialog").dialog("close");
}

//添加保存的数据，保存按钮
function SaveDialog() {
    //     //实现异步技术实现添加
    //     //方法一：借助ajax技术实现添加，使用jquery发送异步请求
    //     //表单序列化参数数据
    //     var param = $('#addDialogForm').serialize();
    //     $.post("addType",param,function (data) {
    //         if (data.result > 0){
    //             //成功关闭窗口
    //             $("#AddDialog").dialog("close");
    //         } else {
    //             $.messager.alert('友情提示','添加失败，请联系管理员:15871409004!','info')
    //         }
    //     },"json");



    //方法二：借助asyui技术实现添加，使用jquery发送异步请求

    $('#addTypeForm').form('submit',{
        url:"addType",
        success:function (data) {
            //将json字符串转换为json对象
            var obj = $.parseJSON(data);
            if (obj.result > 0){
                //刷新数据
                $('#dg').datagrid('reload');
                //成功关闭窗口
                $("#AddDialog").dialog("close");
            } else {
                $.messager.alert('友情提示','添加失败，请联系管理员:15871409004!','info')
            }
        }
    });
}



//修改回显功能
//打开修改的窗口
function goupdate() {
    //1.获取dategrid的选中行
    var selObj = $("#dg").datagrid("getSelections");
    //2.验证是否选中一行
    if (selObj.length == 1) {
        //打开对话框
        $("#upDialog").dialog("open").dialog('setTitle',"编辑区域");
        //还原表单数据(不可取，少字段可以用，不建议使用)
        // $("#upTypeForm").form('load',selObj[0])
        //使用post方式发送异步请求
        var id = selObj[0].id;
        $.post("getType",{"id":id},function (data) {
            $("#upDialogForm").form('load',data)
        },"json")
    }else {
        $.messager.alert('友情提示','请选择且只能选择一行要修改的区域！！','info')
    }
}
//编辑列中的修改回显功能
function gotoup(id) {
    //打开对话框
    $("#upDialog").dialog("open").dialog('setTitle',"编辑区域");
    $.post("getType",{"id":id},function (data) {
        $("#upDialogForm").form('load',data)
    },"json")
}

//修改数据
//添加保存的数据，保存按钮
function updateSaveDialog() {
    $('#upDialogForm').form('submit',{
        url:"updateType",
        success:function (data) {
            //将json字符串转换为json对象
            var obj = $.parseJSON(data);
            if (obj.result > 0){
                $('#dg').datagrid('reload');  //刷新
                //成功关闭窗口
                $("#upDialog").dialog("close");
            } else {
                $.messager.alert('友情提示','修改失败，请联系管理员:15871409004!','info')
            }
        }
    });
}


//删除区域   id是用于接收删除的编号
function delType(id){
    //使用jquery的post发送异步请求
    $.messager.confirm('友情提示','确定删除此信息吗？',function (r) {
        if (r){
            $.post("deleteType",{"id":id},function(data){
                if(data.result>0){
                    $('#dg').datagrid('reload');  //刷新datagrid
                }else{
                    //alert("sss");
                    $.messager.alert('友情提示','删除失败，请联系管理员:13260601227!','info');
                }
            },"json");
        }
    });

}


//批量删除
function DeleteMoreType() {
    //1.获取dategrid的选中行
    var selObjs = $("#dg").datagrid("getSelections");
    //2.验证是否选中一行
    if (selObjs.length > 0) {
        $.messager.confirm('友情提示','确定删除此信息吗？',function (r) {
            if (r){
                //发送异步请求实现批量删除
                //获取选中的id值，拼接成值1，值2，值3的形式
                //拼接成字符串形式
                var str = "";
                for (var i = 0; i < selObjs.length ; i++) {
                    str = str + selObjs[i].id+",";
                }
                str = str.substring(0,str.length-1);
                //发异步请求
                $.post("deleteMoreType",{"ids":str},function (data) {
                    if (data.result > 0){
                        //刷新数据
                        $('#dg').datagrid('reload');
                    }else {
                        $.messager.alert('友情提示','批量删除失败，请联系管理员:13260601227!','info');
                    }
                },"json")

                /*var str = "";
                for (var i=0; i<selObjs.length;i++) {
                    str=str+"a="+selObjs[i].id+"&";
                }
                str = str.substring(0,str.length-1);
                alert(str)
                */
            }
        })
    }else {
        $.messager.alert('友情提示','请选择至少一行数据删除操作！','info');

    }

}
