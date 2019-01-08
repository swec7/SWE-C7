"use strict";
var count = 0;

String.prototype.beginsWith = function (string) {
  return(this.indexOf(string) === 0);
};

String.prototype.endsWith = function(string) {
var end = this.substring((this.length - string.length), this.length);
return (end === string);
};

function colorToHex(color) {
  if (color.substr(0, 1) === '#') {
      return color;
  }
  var digits = /(.*?)rgb\((\d+), (\d+), (\d+)\)/.exec(color);
  
  var red = parseInt(digits[2]);
  var green = parseInt(digits[3]);
  var blue = parseInt(digits[4]);
  
  var rgb = blue | (green << 8) | (red << 16);
  return digits[1] + '#' + rgb.toString(16);
};

function isChecked(element) {
	var isChecked = false;
	$.each(element.get(0).attributes, function(index, attr) {
		var name = attr.nodeName;
		if(name == "checked") {
			isChecked = true;
		}
	});
	return isChecked;
}

$(document).ready(function(){
	$("input[type='radio']").each(function(){
		var inputEle = $(this);
		inputEle.click(setBackgroundColor);
	});
});

function setBackgroundColor(ev) {
	var srcElement = $(ev.target);
	var color = srcElement.attr("onclickColor").toLowerCase();
	var oldColor = colorToHex(srcElement.parent().css("background-color"));
	srcElement.parent().css("background-color",color);
	if(oldColor != color) {
		var hasChecked = isChecked(srcElement);
		if(hasChecked) {
			count--;
		} else {
			count++;
		}
	}
	if(count == 0) {
		$("input[name='save']").each(function(){
			var saveBtn = $(this);
			saveBtn.css("background-image","");
			var text = saveBtn.attr("value");
			if(text.endsWith(" !")) {
				saveBtn.attr("value",text.substr(0,text.length-2));
			}
		});
	} else {
		$("input[name='save']").each(function(){
			var saveBtn = $(this);
			saveBtn.css("background-image","url('/HISinOne/images/icons/submit_active_red.gif')");
			var text = saveBtn.attr("value");
			if(!text.endsWith(" !")) {
				saveBtn.attr("value",text + " !");
			}
		});
	}
}
