//分页查询功能
$(function () {
    //1使用datagrid组件绑定数据
    $("#dg").datagrid({
        url:'getHouse',
        pagination:true,
        pageSize:5,
        pageList:[5,8,10,15,20],
        toolbar:'#ToolBar',
        columns:[[
            {checkbox:true,width:100,align:'center'},
            {field:'id',title:'编号',width:100,align:'right'},
            {field:'title',title:'标题',width:100,align:'right'},
            {field:'dname',title:'区域',width:100,align:'right'},
            {field:'sname',title:'街道',width:100,align:'right'},
            {field:'tname',title:'类型',width:100,align:'right'},
            {field:'price',title:'价格',width:100,align:'right'},
            {field:'floorage',title:'面积',width:100,align:'right'},
            {field:'ispass',title:'状态',width:100,align:'right',
                formatter: function(value,row,index){
                    return value == 1?"已审核":"未审核";
            }
            },
            {field:'opt',title:'编辑|操作', width:80,
                formatter: function(value,row,index){
                    return "<a href='javascript:goPass("+row.id+")'>确认审核</a> <a href='javascript:delType("+row.id+")'>详情</a>";
                }
            }
        ]]
    })
});

//去通过审核
function goPass(id) {
    //发送异步请求        1：表示通过审核
    $.post("updatePassState",{"id":id,"state":1},function (data) {
        if (data.result > 0) {
            //刷新datagrid
            $('#dg').datagrid('reload');
        }else {
            $.messager.alert('友情提示','审核失败，请联系管理员:15871409004!','info')
        }
    },"json")
}


// //实现datagrid绑定条件查询
function searchUser() {
    var inputName = $("#inputName").val();
    var inputTel = $("#inputTel").val();
    //设置条件查询


    $("#dg").datagrid("load", {
            name: inputName,
            tel: inputTel
        }
    )

}




