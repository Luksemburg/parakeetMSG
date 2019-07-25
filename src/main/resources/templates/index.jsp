<!DOCTYPE html>
<!-- <html xmlns:th="http://www.thymeleaf.org"> 
<mvc:resources mapping="/resources/**" location="/resources/" />

-->
<html>
	<head>
		<link rel="shortcut icon" href="https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/img/123.png" type="image/png">
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
					

						<form action="controller" method="POST">
							<h3 align="center"><font color="LimeGreen">LogIn / Registration</font></h3>
							<p>Use letters and numbers (3-20)</p>
							<font color="LimeGreen">Login: &#8194;&#8194;&nbsp;&nbsp;<input size="39" type="text" id="login" name="login" 
										pattern="[0-9a-zA-Z]{3,20}" required /></font><br><br>
										 
							<font color="LimeGreen">Password: <input size="39.5" type="password" name="pass" pattern="[0-9a-zA-Z]{3,20}" required /></font><br><br>
							
							<div style="text-align:right">
							<button style="vertical-align:middle" name="signup" value="signup"> 
								<font color="LimeGreen" size="5">1-click reg / logIN!</font>
								<img src="https://cloud-cube-eu.s3.amazonaws.com/t5j0m088t0ur/public/img/icon.png" style="vertical-align:middle" width="30" height="30"/>							
							</button><br><br>
							</div>
						</form>	

						<h4 align="center">
							Terms of Use: 
						</h4>
						<p> ParakeetMSG is training project. It allows just write messages, registration is pretty simple. Now this project 
						is on testing stage. Android client is on development stage for more comfortable correspondence between users.	In future 
						project may be upgraded
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