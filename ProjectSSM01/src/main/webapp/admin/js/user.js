//分页查询功能
$(function () {
    //1使用datagrid组件绑定数据
    $("#dg").datagrid({
        url:'getUsersData',
        pagination:true,
        pageSize:3,
        pageList:[3,5,8,10,20],
        toolbar:'#ToolBar',
        columns:[[
            {checkbox:true,width:100,align:'center'},
            {field:'id',title:'用户编号',width:100,align:'right'},
            {field:'name',title:'用户名',width:100,align:'right'},
            {field:'telephone',title:'电话号码',width:100,align:'right'},
            {field:'isadmin',title:'是否管理员',width:100,align:'right'},
            {field:'age',title:'年龄',width:100,align:'right'},
            {field:'opt',title:'编辑|操作', width:80,
                formatter: function(value,row,index){
                    return "<a href='javascript:gotoup("+row.id+")'>修改</a> <a href='javascript:delType("+row.id+")'>删除</a>";
                }
            }
        ]]
    })
});
//
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




