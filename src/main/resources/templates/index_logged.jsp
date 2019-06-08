<!DOCTYPE html>
<!-- <html xmlns:th="http://www.thymeleaf.org"> 
<mvc:resources mapping="/resources/**" location="/resources/" />

-->
<html>
	<head>
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
					<p><img src="/portal_content/img/title.png" width="500" height="100" alt="Parakeet MSG" /></p>						
					<p><img src="/portal_content/img/123.png" width="800" height="800"	alt="[logo]" /></p>
				</td>
				
				
				<td width="350" valign="top" bgcolor="AliceBlue">
					
					<!--
						<form action="controller" method="POST">
							<h3 align="center"><font color="LimeGreen">LogIn / Registration</font></h3><br><br>
							<font color="LimeGreen">Login: &#8194;&#8194;&nbsp;&nbsp;<input size="39" type="text" name="login" 
										 required /></font><br><br>
										 
							<font color="LimeGreen">Password: <input size="39.5" type="password" name="pass" required /></font><br><br>
							
							<div style="text-align:right">
							<button style="vertical-align:middle" name="signup" value="signup"> 
								<font color="LimeGreen" size="5">Send!</font>
								<img src="/portal_content/img/icon.png" style="vertical-align:middle" width="30" height="30"/>							
							</button><br><br>
							</div>
						</form>	-->
						
						
						<h2 align="center"><font color="LimeGreen">You have logged in as</font></h2>
						<h1 align="center"><font color="SteelBlue">${login}</font></h1>
						<br>
						
						
						<form action="controller" method="POST">
							<div style="text-align:center">
								<button style="vertical-align:middle" name="tochat" value="tochat"> 
									<font color="LimeGreen" size="5">To Chats!</font>
									<img src="/portal_content/img/icon.png" style="vertical-align:middle" width="30" height="30"/>							
								</button><br><br>
							</div>
						</form>
						

						<h4 align="center">
							Terms of Use: 
						</h4>
						<p> ========= ============ ========= ========= ======== ============ ========== ========= ======= ======== ===========
						=========== ======== ================= ========= ========== =========== ====== ======== ======== ========== =========
						========== ========== =========== ======= ========== ========= ========= ====== ====== ========== ====================
						</p>
						<p> ========= ============ ========= ========= ======== ============ ========== ========= ======= ======== ===========
						=========== ======== ================= ========= ========== =========== ====== ======== ======== ========== =========
						========== ========== =========== ======= ========== ========= ========= ====== ====== ========== ====================
						</p>
						<p> ========= ============ ========= ========= ======== ============ ========== ========= ======= ======== ===========
						=========== ======== ================= ========= ========== =========== ====== ======== ======== ========== =========
						========== ========== =========== ======= ========== ========= ========= ====== ====== ========== ====================
						</p>
				</td>
				
			</tr>
		</table>
	</body>
</html>