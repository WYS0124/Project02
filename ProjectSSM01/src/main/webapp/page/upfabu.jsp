<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
<script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>
<script language="JavaScript" type="text/javascript">
    $(function () { //加载
        //1.发送异步请求获取户型数据，进行加载
        $.post("getTypeData",null,function (data) {
            //加载
          for (var i = 0; i < data.length ; i++) {
              //创建option
              var optionnode = "<option value="+data[i].id+">"+data[i].name+"</option>"
              //将option添加到下拉列表
              $("#typeId").append(optionnode);
          }
          $("#typeId").val(${house.streetId});
        },"json");

        //2.发送异步请求获取区域数据，进行加载
        $.post("getDistrictData",null,function (data) {
          //加载
          for (var i = 0; i < data.length ; i++) {
            //创建option
            var optionnode = "<option value="+data[i].id+">"+data[i].name+"</option>"
            //将option添加到下拉列表
            $("#districtId").append(optionnode);
          }

          //设置区域选中项
          $("#districtId").val(${house.districtId});
          //设置街道选中项
          loadStreet();
        },"json");

      //3.发送异步请求获取街道数据，进行加载
        $("#districtId").change(function () {
            loadStreet();
        });
    });

    //通过区域加载街道
    function loadStreet() {
      //获取当前选中的区域id
      var did = $("#districtId").val();
      //清空原有的街道信息
      $("#streetId>option:gt(0)").remove();
      //发送异步请求获取街道数据
      $.post("getStreetById",{"did":did},function (data) {
        //加载
        for (var i = 0; i < data.length ; i++) {
          //创建option
          var optionnode = "<option value="+data[i].id+">"+data[i].name+"</option>";
          //将option添加到下拉列表
          $("#streetId").append(optionnode);
        }
        //设置街道选中项
        $("#streetId").val(${house.streetId});
      },"json");
    }
</script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新房屋信息发布</DT>
  <DD class=past>填写房屋信息</DD></DL>
<DIV class=box>
<FORM id=add_action enctype="multipart/form-data" method=post name=add.action
action=updateHouse?id=${house.id}>
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <input type="hidden" name="oldPicPath" value="${house.path}" >
    <TD class=field>标　　题：</TD>
    <TD><INPUT id=add_action_title class=text type=text name=title value="${house.title}"> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD>
      <SELECT class=text name=typeId id="typeId">
          <OPTION selected value=>请选择</OPTION>
      </SELECT>
    </TD>
  </TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage class=text type=text value="${house.floorage}"
name=floorage></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=add_action_price class=text type=text name=price value="${house.price}"> </TD></TR>
  <TR>
    <TD class=field>发布日期：</TD>
    <TD><INPUT class=text type=date name=houseDate value="<fmt:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"></fmt:formatDate>"></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT class=text name=district_id id="districtId">
      <OPTION selected value=>请选择</OPTION>
    </SELECT> 街：<SELECT class=text name=streetId id="streetId">
      <OPTION selected value="0">请选择</OPTION>
    </SELECT>
    </TD>
  </TR>
  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT id=add_action_contact class=text type=text name=contact value="${house.contact}"> </TD></TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description>${house.description}</TEXTAREA></TD></TR>
  <tr>
    <td class="field">图　　片：</td>
    <td><img width="100" height="100" src="http://localhost:80/${house.path}"><input type="file" name="pfile"></td>
  </tr>
  </TBODY></TABLE>
<DIV class=buttons><INPUT value=更新 type="submit">
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
