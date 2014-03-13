<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="Scripts/JQuery/jquery-2.1.0.js"></script>
<script type="text/javascript">
	$(document).ready( function(){
		$("input").click( function(){
			alert("Jquery welcome");	
		});	
	});
</script>
<style>
	html,body{
		height: 100%;
		width:100%
	}
	
	div.demo_seach_panel{
		background: gray ;
		height: 20%;
		width: 100%;
	}
	
	div.demo_result_panel{
		background: silver;
		height: 80%;		
	}
	
	div.center{
		margin-left: auto;
		margin-right: auto;
		width: 80%;	
		margin-top: auto;
		margin-bottom: auto;
		display: inline-block;
	}
</style>
	
<body>

<div class="demo_seach_panel">
	<div class="center">
		<input type="text" style="width:60%">	
		<input type="button" value="Search" style="width:30%" >
	</div>
</div>
<div class="demo_result_panel">
</div>	
</body>
</html>
