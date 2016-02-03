var globals = {};

function startApp() {
	console.log("App started..");
	globals.utils = Util;
	loadLoginContent();
	globals.utils.initJavaGeeks();
}

function loadLoginContent() {
	var DOM = $("#login-template").html();
	var loginTmpl = _.template(DOM);
	$("#appContainer").html(loginTmpl);
}
