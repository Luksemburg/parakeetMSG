<%@page import="com.parakeet.msg.web.RenderMSG"%>
<%@page import="com.parakeet.msg.web.AdapterDB"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<link rel="shortcut icon" href="https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/img/123.ico" type="image/x-icon">
        <title>Parakeet: Chats</title>	
		<script src="https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/js/jquery-3.4.1.min.js"></script>
		<audio id="myAudio" src="https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/sound/02031.mp3"></audio>
		<audio id="box" src="https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/sound/box.mp3"></audio>
		<audio id="page" src="https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/sound/page.mp3"></audio>

	</head>
	
	<body>
	
		<table cellspasing="0" cellpadding="5">
			<tr>		
				<td>
					<p><img src=" https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/img/123.png" width="50" height="50"	alt="[logo]" />
					<img src=" https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/img/title.png" width="250" height="50" alt="Parakeet MSG" /></p>
				</td>
				
				<td bgcolor="AliceBlue">												
					<table cellspasing="0" cellpadding="5"  align="right">						
						<td>
							<h3 align="right"><font color="LimeGreen" size="3">You have logged in as </font>
							<font size="5" color="SteelBlue">${login}</font><br>
							<a style="color:red" href="logout"><font size="3">LogOUT</font></a>		
						</td>					
					
						<td>
							<a href="${pageContext.request.contextPath}/getava/${login}" target="_blank">
								<img align="right" src="${pageContext.request.contextPath}/getava/${login}" style="padding-left:10px" width="90" height="90"	alt="[your ava]" /> 
							</a>	
						</td>
						
						<td>
							<form action="update_ava" align="right" enctype="multipart/form-data" method="post">
								   <h3 style="color:SteelBlue">Upload New AVA</h3>
								   <p style="color:SteelBlue"><input   type="file" required name="ava" accept="image/png">
								   <input type="submit" value="Apply!"></p>
							 </form> 
						 </td>
						
					</table>
					<!-- <a style="color:green" href=""><font size="3">Change AVA</font></a></h3> -->
				</td>			
				
			</tr>
			
			<tr>
				<td  width="370" valign="top" bgcolor="AliceBlue">
				
				<div style="overflow:auto; height: 400px;" width="320" >
				
					<p style="text-align:center; "><input accept-charset="utf-8" type="search" size="50" name="search" id="search" placeholder="Find User... Press 'Enter' to search" value="${patt}" />
					<div id="results" style="text-align:center; " name="drop" id="drop" >
											
					</div>
						<!-- <input type="submit" value="Go!" name="find" /> --></p>
						
						<div id="companions" style="overflow:auto; text-align:center" width="320" height="400px">
							
						</div>
							</form>
				</div>
				
				</td>	
				
				<td valign="top" bgcolor="Snow">			
				
					<div id="chat" style="overflow:auto;height: 400px;">

						<br><br>
					</div>					

					<script type="text/javascript" >
					
							//	var temp = "";
							
							function read() {
								$.ajax({
									//async: false,
									type: 'POST',
									url: "read",
									data:{"empty":"empty"},
									success: function() {
										alert("zbs read!");
										document.getElementById("page").play();
										$('#chat').load("right",  function () { $('#chat').scrollTop($('#chat')[0].scrollHeight); });
										$('#companions').load("left");	
										$('#results').load("find");
										
										return false;
									},
									
									error: function(){
										alert("error read!");
									}
								});
							}
											 							
							function fun() {
								
								//sessionStorage.temp = "";
								
								$.ajax({
									async: false,
									type: 'POST',
									url: "writerhead",									
									
									data:{"msg":document.getElementById("msg33").value},									
																		
									success: function(responseJson) {
									/*	$('chat')html(responseJson);
										$('#chat').load(responseJson,  function () { GenerateData(); });
										document.getElementById("box").play();
										return;*/
										    if (responseJson.redirect) {
												//window.location = responseJson.redirect;
												
												$('#chat').load("right",  function () { $('#chat').scrollTop($('#chat')[0].scrollHeight); });
												$('#companions').load("left");
												$('#results').load("find");
												
												document.getElementById("msg33").value = "";
												//$('chat')html(res);
												document.getElementById("box").play();
												return false;
											}
									},
									
									error: function(){
										alert("error writer head!");
									}

								});
							}							
							
							$(document).ready(function(){
								//document.getElementById("myAudio").play();
								var m = document.getElementById("msg33");
								//if(sessionStorage.getItem("temp") != null){
								//	m.value = sessionStorage.getItem("temp");									
								//}
								m.focus();
								//m.selectionStart = m.value.length;
								
								$('#chat').load("right" ,  function () { $('#chat').scrollTop($('#chat')[0].scrollHeight); });
								$('#companions').load("left",  function () { GenerateData(); });	
								//document.getElementById("search").selectionStart = document.getElementById("search").value.length;
																
								$.ajax({
									type: 'POST',									
									url: "daemoncall",
									cache: false, 
									success: function(responseJson) {										
										    if (responseJson.redirect) {
												//window.location = responseJson.redirect;
												
												$('#myAudio').get(0).play();	
												$('#chat').load("right" ,  function () { $('#chat').scrollTop($('#chat')[0].scrollHeight); });
												$('#companions').load("left",  function () { GenerateData(); });
												
												//sessionStorage.temp = document.getElementById("msg33").value;
												//window.location.replace("controller");												
												//var audio = document.getElementById("myAudio").play();
												//let audio = new Audio('https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/sound/02031.mp3');
												//audio.play();
												
												return false;
											}
									},
								/*	success:function(res){
											//$('#chat').load("controller",  function () { GenerateData(); });
											$('#chat').html();
											$('#companions').html(res);
											$('#myAudio').get(0).play();
											//let audio = new Audio('https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/sound/02031.mp3');
											//audio.play();
											//window.location.replace("controller");	
											//var audio = document.getElementById("myAudio").play();
											return false;
										},*/
									error: function(){
											//alert("error searchuser!");
									}
								});
							});
							
							$(function(){
								$('#search').keyup(function(event){
								  var Value = $('#search').val();
								  
								  if (event.keyCode === 13) {
									  $.ajax({																
										url: "searchuser",	
										data:{"pattern":document.getElementById("search").value},								
										
										cache: false, 
										success:function(res){
											//window.location.replace("controller");
											$('#results').load("find",  function () { GenerateData(); });
										},
										
										error: function(){
											//alert("error searchuser!");
										}																			
									  });
								  }
								  
								  return false;
								  
								});								
							});	
							
							
						/*	$(function(){
								$('#msg33').keyup(function(event){
								  var Value = $('#msg33').val();
								  
								  if (event.keyCode === 13) {
									  fun();
								  }						  
								  								  
								  return;								  
								});								
							});*/
							
							$(function(){
								var input = document.getElementById("msg33");
								// Execute a function when the user releases a key on the keyboard
								input.addEventListener("keyup", function(event) {
								  // Number 13 is the "Enter" key on the keyboard
								  if (event.keyCode === 13) {
									// Cancel the default action, if needed
									event.preventDefault();
									// Trigger the button element with a click
									document.getElementById("sendmsg").click();
								  }
								}); 							
							});	
							
							window.onload = function() {
							  window.scrollTo(0,document.querySelector("chat").scrollHeight);
							}
						
					</script>
					
					<form name="myform" id="myform" method="POST" accept-charset="utf-8">						
						
						<table>							
							<td  valign="top">
								<a href="${pageContext.request.contextPath}/getava/${client}" target="_blank">
									<img id="avka" align="left" src="${pageContext.request.contextPath}/getava/${client}" style="padding-right:10px " width="100" height="100"	alt="[client ava]" /><br>
								</a>	
									<% 	
										if(session.getAttribute("client") != null){
											out.println("<font size=\"5\" color=\"SteelBlue\">" + (String)session.getAttribute("client") + "</font>"); 
										}
									%>
							</td>
							
							<td>
								<div id="msg1" style="text-align:right" >	
									<textarea name="msg" id="msg33" cols="90" rows="3"></textarea>				
									<!--<p><input type="submit"  value="Send Message" /></p>--><br>
									
									<button style="vertical-align:middle" id="read" name="read" value="read" onclick="read();">											
										<font color="Yellow" size="5">Read</font>
										<img src="https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/img/icon_yellow.png" style="vertical-align:middle" width="30" height="30"/>
									</button>
									
									<button style="vertical-align:middle" id="sendmsg" name="sendmsg" value="sendmsg" onclick="fun();">
										<img src="https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/img/icon.png" style="vertical-align:middle" width="30" height="30"/>	
										<font color="LimeGreen" size="5">Send Message!</font>
									</button>
								</div>	

								<%
									if(session.getAttribute("client") == null){
										out.println("<script> document.getElementById(\"sendmsg\").disabled = true; </script>");
										out.println("<script> document.getElementById(\"myform\").disabled = true; </script>");
										out.println("<script> document.getElementById(\"avka\").style.visibility = 'hidden'; </script>");
										out.println("<script> document.getElementById(\"msg33\").disabled = true; </script>");
										out.println("<script> document.getElementById(\"read\").disabled = true; </script>");
									}
								%>
								
							</td>	
						</table>	
					</form>					
					
				</td>	
			</tr>				
		</table>
		
	<script type="text/javascript">
		/*document.body.scrollTop = document.body.scrollHeight;		
		var block = document.getElementById("chat");
		block.scrollTop = block.scrollHeight;*/
		//window.scrollTo(0,document.querySelector("chat").scrollHeight);
	</script>
	
	<p align="center">by <a href="setclient?client=FooCo">FooCo</a></p>				
	
	</body>
</html>