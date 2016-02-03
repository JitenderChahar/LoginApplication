var Login = {

	loginBtnEventHandler : function() {
		$("#loginBtn").off('click').on('click', function() {
			Login.bindEvents();
			Login.authenticate();
		});
	},

	bindEvents : function() {
		$(".content input").off("focus").on("focus", function() {
			$(this).css("box-shadow", "inset 0 1px 3px rgba(0, 0, 0, 0.50)");
		});

		$(".content input").off("click").on(
				"click",
				function() {
					$("#statusMessage").text("").removeClass("error").css(
							"display", "none");
				});
	},

	authenticate : function() {
		Login.hideLoginError();
		var formdata = Login.validateFormdata();
		if (formdata) {
			Login.executeLoginRequest(formdata);
		}
	},

	validateFormdata : function() {
		var statusMessageEL = $("#statusMessage");
		statusMessageEL.css("display", "none").addClass("status_message");

		var inputBoxs = $(".content input")

		$(".content input").prop("value", function(i, value) {
			if (value.replace(/^\s\s*/, '').replace(/\s\s*$/, '') === "") {
				$(this).css("box-shadow", "inset 0 0 7px 1px red");
			}
			return value;
		});

		for (var i = 0; i < inputBoxs.length; i++) {
			if ($(inputBoxs[i]).val() === "") {
				statusMessageEL.text("Please fill in highlighted details!")
						.addClass("error").css("display", "block");
				return false;
			}
		}

		return {
			"email" : $(".content input[name='username']").val(),
			"password" : $(".content input[name='password']").val()
		};

	},

	executeLoginRequest : function(formData, callback) {

		AjaxManager.sendRequest(Constant.loginUrl, {
			type : AjaxManager.POST,
			data : formData
		}, successHandler, failureHandler);
		function successHandler(data) {
			if (!data.statusCode) {
				var loginTmpl = _.template("<h1>Successfully logged in.</h1>");
				$("#appContainer").html(loginTmpl);
			} else {
				Login.showLoginError(data.message);
				$('#statusMessage').addClass("error");
			}
		}

		function failureHandler(data) {
			if (data.responseJSON.message) {
				Login.showLoginError(data.responseJSON.message);
			} else {
				Login.showLoginError("Cannot connect to server");
			}

			$('#statusMessage').addClass("error");
		}

	},
	showLoginError : function(msg) {
		$('#statusMessage').html(msg).show("slow");
	},
	hideLoginError : function() {
		$('#statusMessage').html('');
		$('#statusMessage').hide();
	}

};