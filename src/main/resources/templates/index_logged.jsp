<!DOCTYPE html>
<!-- <html xmlns:th="http://www.thymeleaf.org"> 
<mvc:resources mapping="/resources/**" location="/resources/" />

-->
<html>
	<head>
		<link rel="shortcut icon" href="https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/img/123.ico" type="image/x-icon">
		<title>
			Welcome to Parakeet MSG!
		</title>
		
	<!--	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> -->
		
		<style type="text/css">
			.blockRight{
				width: 300px;
				background: #1E90FF;
				padding: 5px; 				
				position: relative;
			}
		</style>
	</head>
	
	<body>
		
	
		<table cellspasing="0" cellpadding="5">
			<tr>
				<td width="900" valign="top">				
					<p><img src="https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/img/title.png" width="500" height="100" alt="Parakeet MSG" /></p>						
					<p><img src="https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/img/123.png" width="800" height="800"	alt="[logo]" /></p>
				</td>
				
				
				<td width="350" valign="top" bgcolor="AliceBlue">
					
						<h3 align="center"><font color="LimeGreen" size="4">You have logged in as </font><br>
						<font size="5" color="SteelBlue">${login}</font><br>
						<a style="color:red" href="logout"><font size="4">LogOUT</font></a></h3>
						
						<br>
						
						
						<form action="controller" method="POST">
							<div style="text-align:center">
								<button style="vertical-align:middle" name="tochat" value="tochat"> 
									<font color="LimeGreen" size="5">To Chats!</font>
									<img src="https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/img/icon.png" style="vertical-align:middle" width="30" height="30"/>							
								</button><br><br>
							</div>
						</form>						

						<h4 align="center">
							Terms of Use: 
						</h4>
						<p> ParakeetMSG is training project. It allows just write messages, registration is pretty simple. Now this project 
						is on testing stage. Android client is on development stage for more comfortable correspondence between users.						
						</p>
						<p> Creator and administrator of this project is not responsible for:
							<ul>
								<li>Actions of it`s users</li>
								<li>Information leak</li>
								<li>Unstable work</li>
								<li>Incorrect displaying of information</li>
								<li>Incorrect  planetary motion</li>
								<li>Anything else in this project and in the world</li>
							</ul>
						</p>
				</td>
				
			</tr>
		</table>
	</body>
</html>