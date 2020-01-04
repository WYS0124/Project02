<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
  <META content="text/html; charset=utf-8" http-equiv=Content-Type>
  <LINK rel=stylesheet type=text/css href="../css/style.css">
  <META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>
  <script language="JavaScript" type="text/javascript">
    $(function (){  //加载
      //1.发送异步请求获取类型，进行加载
      $.post("getTypeData",null,function(data){
        //加载类型
        //[{"id":1000,"name":"一室一厅"},{"id":1001,"name":"一室两厅"},{"id":1002,"name":"两室一厅"},{"id":1003,"name":"两室两厅"},{"id":1004,"name":"三室一厅"},{"id":1005,"name":"三室两厅"},{"id":1006,"name":"四室一厅"},{"id":1007,"name":"四室两厅"}]
        for(var i=0;i<data.length;i++){
          //创建option
          var optionnode=$("<option value="+data[i].id+">"+data[i].name+"</option>");
          //将option添加到下拉列表
          $("#typeId").append(optionnode);
        }

        //回显类型
        $("#typeId").val(${searchCondition.tid});
      },"json");

      //2.发送异步请求加载区域信息
      $.post("getDistrictData",null,function(data){
        //加载类型
        for(var i=0;i<data.length;i++){
          //创建option
          var optionnode=$("<option value="+data[i].id+">"+data[i].name+"</option>");
          //将option添加到下拉列表
          $("#districtId").append(optionnode);
        }

        //设置区域的选中项
        $("#districtId").val(${searchCondition.did});
        //加载对应的区域下的街道
          loadStreet();

      },"json");

      //3.二级联动给区域下拉列拉添加选项改变事件
      $("#districtId").change(function(){
          loadStreet();
      });

      //通过区域加载街道
      function loadStreet(){
        //取当前区域选项的id
        var did=$("#districtId").val();
        //清空原有选项
        $("#streetId>option:gt(0)").remove();
        //发异步请求加载街道

        if (did != ""){
          $.post("getStreetById",{"did":did},function(data){
            //加载类型
            for(var i=0;i<data.length;i++){
              //创建option
              var optionnode=$("<option value="+data[i].id+">"+data[i].name+"</option>");
              //将option添加到下拉列表
              $("#streetId").append(optionnode);
            }
            //设置街道选中项
            $("#streetId").val(${searchCondition.sid});
          },"json");
        }
      }
    });





    //实现分页导航带条件的跳转
    //pageNum表示页码
    function goPage(pageNum){
      //设置页码
      $("#savePage").val(pageNum);
      //提交表单  跳转  传条件
      //alert("ttttt:"+  document.getElementById("fsss").tagName);
      $("#forms").submit();
      //document.getElementById("fsss").submit();
      //document.all.form1.submit();
    }
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
  <DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
  <DL class="search clearfix">
    <form id="forms" name="form1"  method="post" action="borswerHouse">
      <!--隐藏保存当前页码-->
      <input type="hidden" value="1" name="page" id="savePage">

      标题:<input type="text" name="title" value="${searchCondition.title}">
      区域:<select id="districtId" name="did"><option value="">请选择</option></select>
      街道:<select id="streetId" name="sid"><option value="">请选择</option></select>
      类型:<select id="typeId" name="tid"><option value="">请选择</option></select>
      价格:<input type="text" size="10" value="${searchCondition.startPrice}" name="startPrice">-<input value="${searchCondition.endPrice}" size="10"  type="text" name="endPrice">
      <input type="submit" value="搜索" name="submitz">
    </form>
  </DL></DIV>
<DIV class="main wrap">
  <TABLE class=house-list>
    <TBODY>
    <c:forEach items="${pageInfo.list}" var="h">
      <TR>
        <TD class=house-thumb><span><A href="details.htm" target="_blank"><img src="http://localhost:80/${h.path}" width="100" height="75" alt=""></a></span></TD>
        <TD>
          <DL>
            <DT><A href="details.htm" target="_blank">${h.title}</A></DT>
            <DD>${h.dname}-${h.sname},${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
        <TD class=house-type>${h.tname}</TD>
        <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD>
      </TR>
    </c:forEach>
    </TBODY>
  </TABLE>

  <DIV class=pager>
    <UL>
      <LI class=current><A href="javascript:goPage(1)">首页</A></LI>
      <LI><A href="javascript:goPage(${pageInfo.prePage})">上一页</A></LI>
      <LI><A href="javascript:goPage(${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage})">下一页</A></LI>
      <LI><A href="javascript:goPage(${pageInfo.pages})">末页</A></LI></UL><SPAN
          class=total>${pageInfo.pageNum==0?1:pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
  <DL>
    <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
    <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>