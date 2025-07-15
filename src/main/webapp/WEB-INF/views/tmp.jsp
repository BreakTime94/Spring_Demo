<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp" %>
</head>
<body>
	<form method="post">
		<div class="item">
			<input type="text" name="uuid" placeholder="uuid">
			<input type="text" name="origin" placeholder="origin">
			<input type="text" name="image" placeholder="image">
			<input type="text" name="path" placeholder="path">
			<input type="text" name="odr" placeholder="odr">
		</div>
		<div class="item">
			<input type="text" name="uuid" placeholder="uuid">
			<input type="text" name="origin" placeholder="origin">
			<input type="text" name="image" placeholder="image">
			<input type="text" name="path" placeholder="path">
			<input type="text" name="odr" placeholder="odr">
		</div>
		<input type="hidden" name="encodedStr" value="">
		<button>제출</button>
	</form>
	<script>
		$(function(){
			$("form").submit(function(){
				event.preventDefault();
				
				const data = [];

				$(".item").each(function(){
					const obj= {};
					$(this).find('input').each(function(){
						obj[$(this).attr("name")] = this.value;
					}); 
					data.push(obj);
				});
				$("[name='encodedStr']").val(JSON.stringify(data));
				this.submit();
				// console.log(data);
				
				// const obj = {
				// 	uuid: $("input[name='uuid']").val(),
				// 	origin: $("input[name='origin']").val(),
				// 	image: $("input[name='image']").val(),
				// 	path: $("input[name='path']").val(),
				// 	odr: $("input[name='odr']").val()
				// }
				console.log(data);
				
			})
		})
	</script>
</body>
</html>