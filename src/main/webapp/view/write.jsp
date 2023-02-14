<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방 명 록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/summernote-lite.css">
<style type="text/css">
	a { text-decoration: none;}
	table{width: 800px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{text-align: center;}
	#wrap{width:800px; margin: auto; }
	.bg{background-color: #99ccff;}
</style>

<script type="text/javascript">
	function save_go(f) {
		f.action = "${pageContext.request.contextPath}/MyController?cmd=write_ok";
		f.submit();
	}
</script>
</head>
<body>
	<div id="wrap">
		<h2>방명록 : 작성화면</h2>
		<hr />
		<p>
			[<a href="${pageContext.request.contextPath}/MyController?cmd=list">목록으로
				이동</a>]
		</p>
		<form method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td class="bg">작성자</td>
					<td><input type="text" name="name" size="20" /></td>
				</tr>
				<tr>
					<td class="bg">제 목</td>
					<td><input type="text" name="subject" size="20" /></td>
				</tr>
				<tr>
					<td class="bg">email</td>
					<td><input type="text" name="email" size="20" /></td>
				</tr>
				<tr>
					<td class="bg">첨부파일</td>
					<td><input type="file" name="f_name" size="20" /></td>
				</tr>
				<tr>
					<td class="bg">비밀번호</td>
					<td><input type="password" name="pwd" size="20" /></td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="12" cols="60" id="content"
							name="content"></textarea></td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="button" value="저장" onclick="save_go(this.form)"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<input type="reset" value="취소" ></td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/js/summernote-lite.js"></script>
	<script src="${pageContext.request.contextPath}/js/lang/summernote-ko-kr.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#content").summernote({
				lang : "ko-KR",
				height : 300,
				focus : true,
				callbacks : {
					onImageUpload : function(files, editor) {
						for (var i = 0; i < files.length; i++) {
							sendImage(files[i], editor)
						}
					}
				}
			});
		});
		
		function sendImage(file, editor) {
			var frm = new FormData(); 
			frm.append("upload", file);

			// 비동기 통신
			$.ajax({
				url : "${pageContext.request.contextPath}/view/saveImage.jsp", 
				data : frm, // 전달하고자 하는 파라미터 값
				type : "post", // 전송 방식
				contentType : false,
				processData : false,
				dataType : "json",
			}).done(function(data) {
				$("#content").summernote("editor.insertImage", data.img_url);
			});
		}
	</script>
</body>
</html>