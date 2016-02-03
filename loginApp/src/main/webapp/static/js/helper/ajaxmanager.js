var AjaxManager = {

	// Request type constants
	GET : "GET",
	POST : "POST",
	UPDATE : 'UPDATE',
	DELETE : 'DELETE',

	// Send the ajax request
	sendRequest : function(url, customOption, successCallback, failCallback) {
		var defaultOptions = {
			type : this.GET,
			async : true,
			cache : true,
			data : {},
			dataType : 'json',
			contentType : 'application/json; charset=utf-8'
		};

		var options = $.extend({}, defaultOptions, customOption);

		// Actual send the ajax request
		var request = $.ajax({
			type : options.type,
			url : url,
			async : options.async,
			cache : options.cache,
			data : JSON.stringify(options.data),
			contentType : options.contentType,
			dataType : options.dataType,
			headers : options.header || {}
		}).done(function(data, textStatus, jqXHR) {
			successCallback && successCallback(data);
		}).fail(function(data, textStatus, errorThrown) {
			failCallback && failCallback(data, textStatus, errorThrown);
		});

		return request;
	},

	// This function will abort the ajax request
	abortAjaxRequest : function(request) {
		// Abort the ajax request, it is not completed
		if (request && request.readyState !== 4) {
			request.abort();
		}
	}
};
