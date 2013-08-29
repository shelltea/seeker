$(function() {
	$('body').tooltip({
		selector : "[data-toggle=tooltip]",
		container : "body"
	});
	
	var itemsDivHeight = $(window).height() - ($('#navbar').height() + $('#toolbar').height() + $('#localebar').height());
	$('#items').slimScroll({
		height : (itemsDivHeight - 35) + 'px',
		railVisible : true
	});
});