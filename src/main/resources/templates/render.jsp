<%@page import="com.parakeet.msg.web.RenderMSG"%>
<%@page import="java.util.List"%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
        <title>Parakeet: Chats</title>	
		<script src="/portal_content/js/jquery-3.4.1.min.js"></script>
	</head>
	
	<body>
	
		<table cellspasing="0" cellpadding="5">
			<tr>
				<td width="350" valign="top" bgcolor="AliceBlue">
				
					<p><input type="search" size="39" name="search" placeholder="Find Login" />
						<input type="submit" value="Go!" name="find" /></p>
						
						<div style="overflow:auto;" width="340">
							<%
								for(int i = 0; i < RenderMSG.getLogins().size(); i++){
									out.println("<form accept-charset=\"utf-8\" method=\"POST\" action=\"setclient?client=" + RenderMSG.getLogins().get(i) + "\">");
										out.println("<div style=\"text-align:center\">");
											out.println("<button style=\"width:340px\"><font color=\"LimeGreen\" size=\"4\">");											
											out.println("~" + RenderMSG.getLogins().get(i));
											out.println("</font></button>");
										out.println("</div>");
									out.println("</form>");																	
								}
							%>	
							</div>
							</form>
						</div>
				</td>	
				
				<td width="900" valign="top" bgcolor="Snow">
					<div id="chat" style="overflow:auto;height: 700px;">	
						<%
							for(int i = 0; i < RenderMSG.getChats().size(); i++){
								
								if(RenderMSG.getLogins().indexOf(RenderMSG.getChats().get(i).getHost()) < 0){
									out.println("<p align=\"right\"><font color=\"SteelBlue\">");
								}
								
								out.println("<br> host: " + RenderMSG.getChats().get(i).getHost() + " | client: " + 
															RenderMSG.getChats().get(i).getClient() + " | data: " +
															RenderMSG.getChats().get(i).getDateTime() + 
											" || msg: " + RenderMSG.getChats().get(i).getMessage() + "<br>");
											
								if(RenderMSG.getLogins().indexOf(RenderMSG.getChats().get(i).getHost()) < 0){
									out.println("</font></p>");
								}			
							}
						%>
						<br><br>
					</div>					

					<script type="text/javascript" >

							var field;
												 							
							function fun() {
								//alert("fun run!");
																
								$.ajax({
									type: 'POST',
									url: "/ParakeetMSG-0.0.1-SNAPSHOT/writerhead",									
									
									data:{"msg":field},									
																		
									dataType: "html",

								});
							}
							
							function read() {
								//alert(document.getElementById("msg33").value);
								field = document.getElementById("msg33").value;
							};
						
					</script>
					
					<form name="myform" id="myform" method="POST" accept-charset="utf-8">
						
						<div id="msg1" style="text-align:right" >	
							<textarea name="msg" id="msg33" cols="100" rows="5"></textarea>				
							<!--<p><input type="submit"  value="Send Message" /></p>--><br>
							<button style="vertical-align:middle" name="sendmsg" value="sendmsg" onclick="read();fun();">
								<img src="/portal_content/img/icon.png" style="vertical-align:middle" width="30" height="30"/>	
								<font color="LimeGreen" size="5">Send Message!</font>
							</button>
						</div>	
					</form>					
					
				</td>	
			</tr>	
		</table>
		
	<script type="text/javascript">
		document.body.scrollTop = document.body.scrollHeight;
		
		var block = document.getElementById("chat");
		block.scrollTop = block.scrollHeight;
	</script>		
	
	</body>
</html>