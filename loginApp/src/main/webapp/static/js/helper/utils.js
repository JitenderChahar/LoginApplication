var Util = {
	initJavaGeeks : function() {
		Util.EnableCrossBrowserConsole();
		Login.loginBtnEventHandler();
	},

	EnableCrossBrowserConsole : function() {
		if (!window.console)
			console = {
				log : function() {
				},
				error : function() {
				},
				info : function() {
				},
				clear : function() {
				}
			};
	},
	bytesToSize : function(bytes, precision) {
		var KB = 1024;
		var MB = KB * 1024; // 1024 * 1024
		var GB = MB * 1024; // 1024 * 1024 * 1024
		var TB = GB * 1024; // 1024 * 1024 * 1024 * 1024
		var humanReadableBytes;

		if ((bytes < KB)) {
			humanReadableBytes = bytes + ' B';
		} else if ((bytes >= KB) && (bytes < MB)) {
			humanReadableBytes = (bytes / KB).toFixed(precision) + ' KB';
		} else if ((bytes >= MB) && (bytes < GB)) {
			humanReadableBytes = (bytes / MB).toFixed(precision) + ' MB';
		} else if ((bytes >= GB) && (bytes < TB)) {
			humanReadableBytes = (bytes / GB).toFixed(precision) + ' GB';
		} else if (bytes >= TB) {
			humanReadableBytes = (bytes / TB).toFixed(precision) + ' TB';
		}
		if (typeof humanReadableBytes === 'undefined') {
			humanReadableBytes = '0 B';
		}
		return humanReadableBytes;
	},
	getFormattedDate : function(now) {
		if (!now) {
			now = new Date();
		}
		var date = now.getDate();
		var month = now.getMonth() + 1;
		var year = (now.getFullYear() + '').substr(2, 3);

		var mm = ((month < 10) ? "0" : "") + month;
		var dd = ((date < 10) ? "0" : "") + date;
		var yy = ((year < 10) ? "0" : "") + year;

		var timeStr = mm + '/' + dd + '/' + yy;
		return timeStr;
	},
	getFormattedTime : function(now) {
		if (!now) {
			now = new Date();
		}
		var hours = now.getHours();
		var minutes = now.getMinutes();
		var seconds = now.getSeconds();

		var hh = ((hours < 10) ? "0" : "") + hours;
		var mm = ((minutes < 10) ? "0" : "") + minutes;
		var ss = ((seconds < 10) ? "0" : "") + seconds;

		var timeStr = hh + ':' + mm + ':' + ss;
		return timeStr;
	},
	formatKMBT : function(y) {
		var abs_y = Math.abs(y);
		if (abs_y >= 1000000000000) {
			return (y / 1000000000000).toFixed(2) + "T";
		} else if (abs_y >= 1000000000) {
			return (y / 1000000000).toFixed(2) + "B";
		} else if (abs_y >= 1000000) {
			return (y / 1000000).toFixed(2) + "M";
		} else if (abs_y >= 1000) {
			return (y / 1000).toFixed(2) + "K";
		} else if (abs_y < 1 && y > 0) {
			return y.toFixed(2);
		} else if (abs_y === 0) {
			return '';
		} else {
			return y;
		}
	},
	msecToTime : function(duration) {
		if (_.isUndefined(duration) || _.isNaN(duration)) {
			return 'N/A';
		} else {
			var milliseconds = /* parseInt((duration % 1000) / 100) */(duration % 1000), seconds = parseInt((duration / 1000) % 60), minutes = parseInt((duration / (1000 * 60)) % 60), hours = parseInt((duration / (1000 * 60 * 60)) % 24);

			hours = (hours < 10) ? "0" + hours : hours;
			minutes = (minutes < 10) ? "0" + minutes : minutes;
			seconds = (seconds < 10) ? "0" + seconds : seconds;

			return hours + ":" + minutes + ":" + seconds + "." + milliseconds;
		}
	},
	secToTime : function(duration) {
		if (_.isUndefined(duration) || _.isNaN(duration)) {
			return 'N/A';
		} else {
			var seconds = parseInt((duration) % 60), minutes = parseInt((duration / (60)) % 60), hours = parseInt((duration / (60 * 60)) % 24);

			hours = (hours < 10) ? "0" + hours : hours;
			minutes = (minutes < 10) ? "0" + minutes : minutes;
			seconds = (seconds < 10) ? "0" + seconds : seconds;

			return hours + ":" + minutes + ":" + seconds;
		}
	},
	secToTimeWithDays : function(duration) {
		if (_.isUndefined(duration) || _.isNaN(duration)) {
			return 'N/A';
		} else {
			var seconds = parseInt((duration) % 60), minutes = parseInt((duration / (60)) % 60), hours = parseInt((duration / (60 * 60)) % 24), days = Math
					.floor((duration / (60 * 60)) / 24);

			hours = (hours < 10) ? "0" + hours : hours;
			minutes = (minutes < 10) ? "0" + minutes : minutes;
			seconds = (seconds < 10) ? "0" + seconds : seconds;

			if (days > 0) {
				return days + " day(s) " + hours + ":" + minutes + ":"
						+ seconds;
			}
			return hours + ":" + minutes + ":" + seconds;
		}
	},
	secToString : function(duration) {
		var secString = 'N/A';
		var totalSeconds = '0';
		if (!(_.isUndefined(duration) || _.isNaN(duration))) {
			var seconds = parseInt((duration) % 60), minutes = parseInt((duration / (60)) % 60), hours = parseInt((duration / (60 * 60)) % 24);

			hours = (hours < 10) ? "0" + hours : hours;
			minutes = (minutes < 10) ? "0" + minutes : minutes;
			seconds = (seconds < 10) ? "0" + seconds : seconds;

			if (hours !== '00') {
				if (hours === '01') {
					secString = '1 Hour';
				} else {
					secString = +hours + ' Hours';
				}
				totalSeconds = +hours * 60 * 60;
			} else if (minutes !== '00') {
				if (minutes === '01') {
					secString = '1 Minute';
				} else {
					secString = +minutes + ' Minutes';
				}
				totalSeconds = +minutes * 60;
			} else if (seconds !== '00') {
				if (seconds === '01') {
					secString = '1 Second';
				} else {
					secString = +seconds + ' Seconds';
				}
				totalSeconds = +seconds;
			}
		}
		return [ totalSeconds, secString ];
	},
	formatNumber : function(number) {
		return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	},

	// INPUT VALIDATIONS
	isNumbericKey : function(event) {
		// Allow: backspace, delete, tab, escape, and enter
		if (event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9
				|| event.keyCode == 27 || event.keyCode == 13 ||
				// Allow: Ctrl
				(event.ctrlKey === true) || // && event.keyCode == 65) ||
				// Allow: home, end, left, right
				(event.keyCode >= 35 && event.keyCode <= 39)) {
			// let it happen, don't do anything
			return true;
		} else {
			// Ensure that it is a number and stop the keypress
			if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57)
					&& (event.keyCode < 96 || event.keyCode > 105)) {
				return false;
			}
			return true;
		}
	},
	numbericInputValidation : function(inputID, connectBtn) {
		$(inputID).off('keydown');
		var Util = this;
		$(inputID).off('keydown');
		$(inputID).on('keydown', function(event) {
			var isAllow = Util.isNumbericKey(event);
			if (!isAllow) {
				event.preventDefault();
			} else {
				// Allow
			}

			if (event.keyCode === 13) {
				if (typeof connectBtn !== 'undefined') {
					$(connectBtn).click();
				}
			}
		});
	},
	// SELECTABLE UTILS
	removeDotAndColon : function(inputAddr) {
		var outPutAddr = inputAddr.replace(/\./g, "");
		outPutAddr = outPutAddr.replace(/:/g, "");
		return outPutAddr;
	},

	checkRadioButton : function(value) {
		$('input:radio[name="attribute-radio"][value="' + value + '"]').prop(
				'checked', true);
	},
	setIfDefined : function(desti, available) {
		var source;
		if (typeof desti !== 'undefined') {
			source = desti;
		} else if (typeof available !== 'undefined') {
			source = available;
		} else {
			source = null;
		}
		return source;
	},

	validatePort : function(port) {
		var portRegex = /^\d{1,5}$/;
		if (typeof port === 'undefined' || !$.trim(port)
				|| port.match(portRegex) === null || port > 65535) {
			$("#error_message").text("Invalid Port number");
			return false;
		} else {
			return true;
		}
	},

	setCookie : function(c_name, value, exMinutes, path) {
		var exdate = new Date();
		exdate.setDate(exdate.getTime() + exMinutes * 60000);
		var c_value = escape(value)
				+ ((exMinutes == null) ? "" : "; expires="
						+ exdate.toUTCString());
		c_value = c_value + ((path == null) ? "" : "; path=" + path);
		document.cookie = c_name + "=" + c_value;
	},

	getCookie : function(c_name) {
		var c_value = document.cookie;
		var c_start = c_value.indexOf(" " + c_name + "=");
		if (c_start == -1) {
			c_start = c_value.indexOf(c_name + "=");
		}
		if (c_start == -1) {
			c_value = null;
		} else {
			c_start = c_value.indexOf("=", c_start) + 1;
			var c_end = c_value.indexOf(";", c_start);
			if (c_end == -1) {
				c_end = c_value.length;
			}
			c_value = unescape(c_value.substring(c_start, c_end));
		}
		return c_value;
	},

	enterKeyEventForDialog : function(container, btn) {
		$(container).off("keyup").on("keyup", function(e) {
			if (e.keyCode === 13) {
				$(btn).trigger('click');
			}
		});
	},

	createConstant : function(parentObject, propertyName, constantValue) {
		Object.defineProperty(parentObject, propertyName, {
			writable : false,
			enumerable : false,
			configurable : false,
			value : constantValue
		});
	},

	/**
	 * Compares two software version numbers (e.g. "1.7.1" or "1.2b").
	 * 
	 * This function was born in http://stackoverflow.com/a/6832721.
	 * 
	 * @param {string}
	 *            v1 The first version to be compared.
	 * @param {string}
	 *            v2 The second version to be compared.
	 * @param {object}
	 *            [options] Optional flags that affect comparison behavior:
	 *            <ul>
	 *            <li> <tt>lexicographical: true</tt> compares each part of
	 *            the version strings lexicographically instead of naturally;
	 *            this allows suffixes such as "b" or "dev" but will cause
	 *            "1.10" to be considered smaller than "1.2". </li>
	 *            <li> <tt>zeroExtend: true</tt> changes the result if one
	 *            version string has less parts than the other. In this case the
	 *            shorter string will be padded with "zero" parts instead of
	 *            being considered smaller. </li>
	 *            </ul>
	 * @returns {number|NaN}
	 *          <ul>
	 *          <li>0 if the versions are equal</li>
	 *          <li>a negative integer iff v1 < v2</li>
	 *          <li>a positive integer iff v1 > v2</li>
	 *          <li>NaN if either version string is in the wrong format</li>
	 *          </ul>
	 * 
	 * @copyright by Jon Papaioannou (["john", "papaioannou"].join(".") +
	 *            "@gmail.com")
	 * @license This function is in the public domain. Do what you want with it,
	 *          no strings attached.
	 */
	versionCompare : function(v1, v2, options) {
		var lexicographical = options && options.lexicographical, zeroExtend = options
				&& options.zeroExtend, v1parts = v1.split('.'), v2parts = v2
				.split('.');

		function isValidPart(x) {
			return (lexicographical ? /^\d+[A-Za-z]*$/ : /^\d+$/).test(x);
		}

		if (!v1parts.every(isValidPart) || !v2parts.every(isValidPart)) {
			return NaN;
		}

		if (zeroExtend) {
			while (v1parts.length < v2parts.length)
				v1parts.push("0");
			while (v2parts.length < v1parts.length)
				v2parts.push("0");
		}

		if (!lexicographical) {
			v1parts = v1parts.map(Number);
			v2parts = v2parts.map(Number);
		}

		for (var i = 0; i < v1parts.length; ++i) {
			if (v2parts.length == i) {
				return 1;
			}

			if (v1parts[i] == v2parts[i]) {
				continue;
			} else if (v1parts[i] > v2parts[i]) {
				return 1;
			} else {
				return -1;
			}
		}

		if (v1parts.length != v2parts.length) {
			return -1;
		}

		return 0;
	}
};
