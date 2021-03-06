<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<my:admin tab="integralOrder" subtab="integralOrder">
	<jsp:body>
		<form role="form" class="form-horizontal" method="post">
		  <div class="form-group">
               <label for="seq" class="col-sm-2 control-label">订单编号:</label>
               <div class="col-sm-10">
                    <p class="form-control">${t.orderNo }</p>
               </div>
           </div>
           <div class="form-group">
               <label for="seq" class="col-sm-2 control-label">订单日期:</label>
               <div class="col-sm-10">
                    <p class="form-control"><fmt:formatDate value="${t.orderDate}" type="both"/></p>
               </div>
           </div>
           <div class="form-group">
               <label for="seq" class="col-sm-2 control-label">用户:</label>
               <div class="col-sm-10">
                    <p class="form-control">${t.user.account }</p>
               </div>
           </div>
           <div class="form-group">
               <label for="classify" class="col-sm-2 control-label">订单状态:</label>
               <div class="col-sm-10">
                    <p class="form-control">
                    <c:if test="${t.status eq '00' }">未使用</c:if>
                  	<c:if test="${t.status eq '01' }">已使用</c:if>
                  	<c:if test="${t.status eq '02' }">已过期</c:if>
                  	<c:if test="${t.status eq '10' }">待发货</c:if>
                  	<c:if test="${t.status eq '11' }">已发货</c:if>
                  	<c:if test="${t.status eq '12' }">已收货</c:if>
                  	<c:if test="${t.status eq '20' }">已取消</c:if>
                    </p>
               </div>
           </div>
           <div class="form-group">
               <label for="classify" class="col-sm-2 control-label">订单详情:</label>
               <div class="col-sm-10">
               	 <table class="table">
               	   <tr>
		               <td>商品分类</td>
		               <td>${t.orderMerchandiseDto.merchandise.classifyDto.name }</td>
	               </tr>
	               <tr>
		               <td>商品品牌</td>
		               <td>${t.orderMerchandiseDto.merchandise.brandDto.name }</td>
	               </tr>
	               <tr>
		               <td>商品名称</td>
		               <td>
		               <a href="<c:url value='/admin/merchandise/${t.orderMerchandiseDto.merchandise.id}'/>">${t.orderMerchandiseDto.merchandise.name }</a>
		               </td>
	               </tr>
	               <tr>
		               <td>消耗积分</td>
		               <td>${t.orderMerchandiseDto.integralCount }</td>
	               </tr>
	               <c:if test="${t.orderMerchandiseDto.merchandise.type eq '0' }">
	               <tr>
		               <td>有效期</td>
		               <td>${t.orderMerchandiseDto.merchandise.end}</td>
	               </tr>
	               <tr>
		               <td>兑换码</td>
		               <td>${t.orderMerchandiseDto.couponCode }</td>
	               </tr>
	               <tr>
		               <td>使用时间</td>
		               <td><fmt:formatDate value="${t.orderMerchandiseDto.usageDate }" type="both"/></td>
	               </tr>
	               </c:if>
			     </table>   
               </div>
           </div>
       </form>
	</jsp:body>
</my:admin>