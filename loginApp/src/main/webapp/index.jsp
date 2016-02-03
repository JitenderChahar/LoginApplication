<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- Title and its icon -->
<title>Java Geeks</title>
<link rel="shortcut icon" href="./static/images/icon/login.ico" />

<!-- CSS -->
<link rel="stylesheet" href="./static/css/main.css" />

<!-- JS -->
<script src="./static/js/lib/jquery/jquery.min.js"
	type="text/javascript"></script>
<script src="./static/js/lib/underscore/underscore-min.js"
	type="text/javascript"></script>
<script src="./static/js/lib/backbone/backbone-min.js"
	type="text/javascript"></script>
<script src="./static/js/lib/d3/d3.min.js" type="text/javascript"></script>

<!-- User Defined JS -->
<script src="./static/js/config/constant.js" type="text/javascript"></script>
<script src="./static/js/helper/ajaxmanager.js" type="text/javascript"></script>
<script src="./static/js/login/login.js" type="text/javascript"></script>
<script src="./static/js/helper/utils.js" type="text/javascript"></script>
<script src="./static/js/myApp.js" type="text/javascript"></script>
</head>
<body>
	<div id="wrapper">
		<div id="appContainer"></div>
		<div id="footer">
			<div class="box">
				<h5>Copyright © 2016 Java Geeks</h5>
			</div>
		</div>

	</div>
	<div class="gradient"></div>
	<script>
		$(document).ready(function() {
			startApp();
		});
	</script>
	<%@include file="./static/js/login/login.html"%>
</body>
</html>
