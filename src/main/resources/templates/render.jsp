﻿<%@page import="com.parakeet.msg.web.RenderMSG"%>
<%@page import="com.parakeet.msg.web.AdapterDB"%>
<%@page import="java.util.List"%>

<%
	//RenderMSG rend = ((RenderMSG)request.getAttribute("r"));
	//int temp = -2;
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
        <title>Parakeet: Chats</title>	
		<script src="/portal_content/js/jquery-3.4.1.min.js"></script>
		<audio id="myAudio" src="/portal_content/sound/02031.mp3"></audio>
		
	</head>
	
	<body>
	
		<table cellspasing="0" cellpadding="5">
			<tr>		
				<td>
					<p><img src="/portal_content/img/123.png" width="50" height="50"	alt="[logo]" />
					<img src="/portal_content/img/title.png" width="250" height="50" alt="Parakeet MSG" /></p>
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
				
				<div style="overflow:auto; height: 750px;" width="320" >
				
					<p style="text-align:center; "><input accept-charset="utf-8" type="search" size="50" name="search" id="search" placeholder="Find User" value="${patt}" />
					<div style="text-align:center; " name="drop" id="drop" >
						<%
							if(session.getAttribute("searchList") != null){
								List<String> list = (List<String>)session.getAttribute("searchList");
								for(int i = 0; i < list.size(); i++){									
									out.println("<form accept-charset=\"utf-8\" method=\"POST\" action=\"setclient?client=" + list.get(i) + "\">");
									out.println("<a  href=\"getava/" + list.get(i) + "\" target=\"_blank\" >");
										out.println("<img src=\"getava/" + list.get(i) + "\" style=\"vertical-align:top\" width=\"41\" height=\"41\" alt=\"[ava]\" /></a>");
										out.println("<button style=\"width:290px; \"><font color=\"Yellow\" size=\"6\">");										
										out.println(list.get(i));
										out.println("</font></button>");
									out.println("</form>");	
								}
							}
							
							//session.setAttribute("patt", null);
						%>	
						
					</div>
						<!-- <input type="submit" value="Go!" name="find" /> --></p>
						
						<div style="overflow:auto; text-align:center" width="320" height="600px">
							<%								
								for(int i = 0; i < ((RenderMSG)request.getAttribute("r")).getLogins().size(); i++){
									out.println("<form  accept-charset=\"utf-8\" method=\"POST\" action=\"setclient?client=" + ((RenderMSG)request.getAttribute("r")).getLogins().get(i) + "\">");
										out.println("<div style=\"text-align:center\">");
										out.println("<a href=\"getava/" + ((RenderMSG)request.getAttribute("r")).getLogins().get(i) + "\" target=\"_blank\" >");
											out.println("<img src=\"getava/" + ((RenderMSG)request.getAttribute("r")).getLogins().get(i) + "\" width=\"41\" height=\"41\" style=\"vertical-align:top; \" alt=\"[ava]\" /></a>");	
											
											out.println("<button style=\"width:290px\"><font color=\"LimeGreen\" size=\"6\">");													
											out.println(((RenderMSG)request.getAttribute("r")).getLogins().get(i)); 

												int temp = ((AdapterDB)session.getAttribute("helper")).getUnread((String)session.getAttribute("login"), ((RenderMSG)request.getAttribute("r")).getLogins().get(i));
												if(temp > 0){	
													out.println("<font color=\"Red\"> +"	+ temp);
												}
												
											out.println("</font></font></button>");
										out.println("</div>");
									out.println("</form>");																	
								}
							%>	
							</div>
							</form>
				</div>
				
				</td>	
				
				<td width="900" valign="top" bgcolor="Snow">			
				
					<div id="chat" style="overflow:auto;height: 600px;">	
						<%
						
							int to = ((RenderMSG)request.getAttribute("r")).getChats().size();							
							int line = ((AdapterDB)session.getAttribute("helper")).getUnread((String)session.getAttribute("login"), (String)session.getAttribute("client"));
						
							for(int i = 0; i < to; i++){								
								
								if(to - i == line){
									out.println("<p align=\"center\"><font color=\"Red\">--------------------------------------------------------------- Unanswered Messages ---------------------------------------------------------------</font></p>");
								}
								
								if(((RenderMSG)request.getAttribute("r")).getLogins().indexOf(((RenderMSG)request.getAttribute("r")).getChats().get(i).getHost()) < 0){
									out.println("<p align=\"right\"><font color=\"SteelBlue\">");
								}
								
								/*out.println("<br> host: " + ((RenderMSG)request.getAttribute("r")).getChats().get(i).getHost() + " | client: " + 
															((RenderMSG)request.getAttribute("r")).getChats().get(i).getClient() + " | data: " +
															((RenderMSG)request.getAttribute("r")).getChats().get(i).getDateTime() + 
											" || msg: " + ((RenderMSG)request.getAttribute("r")).getChats().get(i).getMessage() + "<br>");*/
											
								out.println("<br>" + ((RenderMSG)request.getAttribute("r")).getChats().get(i).getMessage() + "<br>" + 
													"<font color=\"Silver\" size=\"1\">" + ((RenderMSG)request.getAttribute("r")).formatTime(((RenderMSG)request.getAttribute("r")).getChats().get(i).getDateTime() ) + "</font><br>");
											
								if(((RenderMSG)request.getAttribute("r")).getLogins().indexOf(((RenderMSG)request.getAttribute("r")).getChats().get(i).getHost()) < 0){
									out.println("</font></p>");
								}			
							}
						%>
						<br><br>
					</div>					

					<script type="text/javascript" >
											 							
							function fun() {
															
								$.ajax({
									async: false,
									type: 'POST',
									url: "writerhead",									
									
									data:{"msg":document.getElementById("msg33").value},									
																		
									success: function(responseJson) {
										
										    if (responseJson.redirect) {
												window.location = responseJson.redirect;
												
												return;
											}
									},
									
									error: function(){
										alert("error writer head!");
									}

								});
							}							
							
							$(document).ready(function(){
								
								document.getElementById("search").focus();
								document.getElementById("search").selectionStart = document.getElementById("search").value.length;
																
								$.ajax({
									type: 'POST',									
									url: "daemoncall",
									
									success: function(responseJson) {										
										    if (responseJson.redirect) {
												window.location = responseJson.redirect;
												var audio = document.getElementById("myAudio").play();
												return;
											}
									},
									
									error: function(){
										//alert("error!");
									}
									
								});
							});
							
							$(function(){								
								
								$('#search').keyup(function(){
								  var Value = $('#search').val();
								  
								  $.ajax({																
									url: "searchuser",	
									data:{"pattern":document.getElementById("search").value},								
									
									cache: false, 
									success:function(res){
										window.location.replace("controller");											
									},
									
									error: function(){
										//alert("error searchuser!");
									}									
									
								  });							  
								  return false;
								  
								});								
							});	
						
					</script>
					
					<form name="myform" id="myform" method="POST" accept-charset="utf-8">						
						
						<table>							
							<td  valign="top">
								<a href="${pageContext.request.contextPath}/getava/${client}" target="_blank">
									<img align="left" src="${pageContext.request.contextPath}/getava/${client}" style="padding-right:10px " width="100" height="100"	alt="[client ava]" /><br>
								</a>	
									<% 	
										if(session.getAttribute("client") != null){
											out.println("<font size=\"5\" color=\"SteelBlue\">" + (String)session.getAttribute("client") + "</font>"); 
										}else{
											out.println("<font size=\"5\" color=\"SteelBlue\">Private Notes</font>");
										}
									%>
							</td>
							
							<td>
								<div id="msg1" style="text-align:right" >	
									<textarea name="msg" id="msg33" cols="90" rows="5"></textarea>				
									<!--<p><input type="submit"  value="Send Message" /></p>--><br>
									<button style="vertical-align:middle" name="sendmsg" value="sendmsg" onclick="fun();">
										<img src="/portal_content/img/icon.png" style="vertical-align:middle" width="30" height="30"/>	
										<font color="LimeGreen" size="5">Send Message!</font>
									</button>
								</div>	 
							</td>	
						</table>	
					</form>					
					
				</td>	
			</tr>				
		</table>
		
	<script type="text/javascript">
		document.body.scrollTop = document.body.scrollHeight;
		
		var block = document.getElementById("chat");
		block.scrollTop = block.scrollHeight;
	</script>
	
	<p align="center">by <a href="setclient?client=FooCo">FooCo</a></p>				
	
	</body>
</html>