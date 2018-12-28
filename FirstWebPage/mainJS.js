"use strict";

$("#menuBtn").click(function (event) {
	$(".menuContent").animate({
		left: '0px',
	}, 500);
	$(".cover").fadeIn();
});

