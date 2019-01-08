"use strict";
function handleWMTT(ev) {
	var srcElement = $(ev.target);
	var id = srcElement.attr("showID");

	$("[name='qrCodeDiv']").each(function(){
		var qrCodeDiv = $(this);
		var qrCodeDivId = qrCodeDiv.attr("id");
		if(qrCodeDivId != id) {
			qrCodeDiv.css("display", "none");
		}
	});
	var wmtt = $("#"+id);
	if(wmtt.css("display") == "" || wmtt.css("display") == "none") {
		wmtt.css("display", "block");
		correctPossition(ev, wmtt[0]);
	} else {
		wmtt.css("display", "none");
	}
}

function correctPossition(ev, myWmtt) {
	if (typeof myWmtt != 'undefined' && myWmtt != null && myWmtt.style.display == 'block') {
		var freeSpace = 10;
		var x = (ev.pageX ? ev.pageX : window.event.x) + myWmtt.offsetParent.scrollLeft - myWmtt.offsetParent.offsetLeft;
		var y = (ev.pageY ? ev.pageY : window.event.y) + myWmtt.offsetParent.scrollTop - myWmtt.offsetParent.offsetTop;
		myWmtt.style.left = (x + freeSpace) + "px";
		myWmtt.style.top 	= (y + freeSpace) + "px";
		var height = myWmtt.clientHeight;
		var width = myWmtt.clientWidth;
		var windowHeight = top.innerHeight;
		var windowWidth = top.innerWidth;
		var myWindow = window;
		var offsetX = window.pageXOffset;
		var offsetY = window.pageYOffset;
		var rightPos = (x + freeSpace + width) - offsetX;
		var buttomPos = (y + freeSpace + height) - offsetY;
		if(rightPos > (windowWidth - 10)) {
			myWmtt.style.left = (x - freeSpace - width) + "px";
		}
		if(buttomPos > (windowHeight - 10)) {
			myWmtt.style.top = (y - freeSpace - height) + "px";
		}
	}
}

$("[name='qrCode']").each(function(){
	var previewImage = $(this);
	var id = previewImage.attr("showID");
	var titleText = previewImage.attr("titleText");
	previewImage.attr("title", titleText);
	previewImage.click(handleWMTT);
	var previewLink = previewImage.parent();
	previewLink.attr("href",null);
});
