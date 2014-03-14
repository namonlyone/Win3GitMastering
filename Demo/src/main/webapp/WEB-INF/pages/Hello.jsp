<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="http://code.jquery.com/jquery-2.1.0.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	
	//Button click when enter
    $('#seach_key').keypress(function(e){
        if(e.keyCode==13)
        $('#search_submit').click();
      });
	

	$("#search_submit").click(function(){
		var jSearchValue=$("#seach_key");		
		var jSearchURL="/Demo/search/" ;
		
		jSearchURL += jSearchValue.val();


		$.ajax(
				{
					url: jSearchURL,
					
					type: "get",
	
					dataType: "json",
					
					error: function(xhr, status, text){
						
						alert("http send error: " + xhr.responseText + text);
					},
					
					beforeSend: function(){

					},
					
					complete: function(){
					},
					
					success: function( data ){
						ClearResults();
						PrintResultComment(data,jSearchValue.val());
						$(data).each(function(i,val){														
							AppendResult(val);
						});
			}
				});
			
			// Prevent default click.
			return( false );					
		}
		);
	});

	
function AppendResult(json){

	var jContent = $("#rs_content");
	var type;


	if(json.type==="Person"){
		type = "<h4>Actor/Actress:<h4>";
	} else if (json.type==="Movie"){
		type = "<h4>Film<h4>";
	}

	//Title row
	var jTable = $('<table/>').append('<tr><td width="120px">' +type+ '</td><td></td><td></td></tr>');;
	jTable.css("margin-left","10%");
	
	//content row
	$.each(json, function(k,v){
		if(k !=="type" && v!=null) {		
			var row = $("<tr/>").append("<td width='120px'></td><td>"+k+"</td><td>:"+v+"</td>");
			jTable.append(row);
		}
	});
	
	jContent.append($("<br>"));
	$("<hr>").appendTo(jContent);
	jContent.append(jTable); 

}
	
function PrintResultComment(data, searchWord){
	var strCmt="";
	if(data==null || data==""){
		strCmt="No result found for '"+ searchWord +"'. Please try again...";
	}else {
		strCmt=data.length + " result(s) found for '"+ searchWord +"'. All are listed below :";	
		
	}
	
	$("#rs_comment").append("<h4>"+strCmt+"</h4>");
}

function ClearResults(){
	$("#rs_comment").empty();
	$("#rs_content").empty();
}
</script>
<style>
	html,body{
		height: 100%;
		width:80%
		margin-left: auto;
		margin-right: auto;
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
	div.demo_result_content
	{
		background:inherit;
		overflow: auto;
	}
	div.horizonal_center{
		margin-left: auto;
		margin-right: auto;
		width: 80%;	
		height:100%;
	}
	div.vertical_center{
		height:100%; 
	}
</style>

<body>

<div class="demo_seach_panel">
	<div class="horizonal_center">
		<div class="vertical_center">
		<table height="100%" width="100%">
			<tr><td style="vertical-align: middle;">
			<input id="seach_key" type="text" style="width:70%">	
			<input id="search_submit" type="submit" value="Search" style="width:100px" >
			</td></tr>
		</table>
		</div>
	</div>
</div>
<div id="rs_panel" class="demo_result_panel">
	<div id="rs_comment" style="width:100%; height:60px; background:inherit; overflow: hidden; padding-left: 10%">
	</div>
	<div id="rs_content" class="demo_result_content">
	</div>
</div>	
</body>
</html>
