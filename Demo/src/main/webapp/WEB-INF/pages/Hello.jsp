<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>[WIN3] Research Project Demo</title>
		<script src="http://code.jquery.com/jquery-2.1.0.js"></script>
		<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
		<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	</head>

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
		
		jSearchURL += $.trim(jSearchValue.val());

		if (!$.trim(jSearchValue.val())) return;

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
		type = "<b>Person<b>";
	} else if (json.type==="Movie"){
		type = "<b>Film<b>";
	}

	//Title row
	var jTable = $('<table/>').append('<tr><td width="120px">' +type+ '</td><td></td><td></td></tr>');;
	
	//content row
	$.each(json, function(k,v){
		if(k !=="type" && v!=null) {		
			var row = $("<tr/>").append("<td width='120px'></td><td>"+k+"</td><td>: "+v+"</td>");
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
		strCmt="No result found for '"+ searchWord +"'.";
	}else {
		strCmt=data.length + " result(s) found for '"+ searchWord +"'.";	
		
	}
	
	$("#rs_comment").append('<b>'+strCmt+'</b>');
}

function ClearResults(){
	$("#rs_comment").empty();
	$("#rs_content").empty();
}
</script>

<body style="padding-top: 70px;">
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
	        <div class="navbar-header">
	          <a class="navbar-brand" href="#">[WIN3] Research Project Demo</a>
	        </div>
		</div>
	</nav>
<div class="container">
	<div class="row">
		<div class="col-md-8">
			<form class="form-inline" role="form">
				<div class="col-md-10">
					<div class="input-group">
					  <span class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>
					  <input id="seach_key" type="text" class="form-control" placeholder="Enter a person name or movie title to search" style="width: 100%;" />
					</div>
				</div>
				<button id="search_submit" type="button" class="btn btn-default">Search</button>
			</form>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div id="rs_comment" style="padding-top: 20px;"></div>
			<div id="rs_panel" class="demo_result_panel">
				<div id="rs_content" class="demo_result_content">
				</div>
			</div>	
		</div>

	</div>
</div>
</body>
</html>
