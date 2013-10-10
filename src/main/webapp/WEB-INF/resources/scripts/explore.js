$(function() {
	// 计算Tab高度
	var tabsDivHeight = $(window).height() - ($('#navbar').height() + $('#localebar').height());
	$('#channel-tabs').height(tabsDivHeight - 25);

	$.get(contextPath + '/api/channels', function(data) {
		var template = Handlebars.compile($('#channel-template').html());
		var html = template(data);
		console.log(html);
		$('#channel-tabs').html(html);
	});
});