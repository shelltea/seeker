$(function() {
	var rootCategoryId;

	// 计算Tab高度
	var tabsDivHeight = $(window).height() - ($('#navbar').height() + $('#localebar').height());
	$('#channel-tabs').height(tabsDivHeight - 25);

	// 获取根目录
	$.get(contextPath + '/api/categories', function(data) {
		rootCategoryId = data.data.id;
	});

	// 获取频道和订阅源
	$.get(contextPath + '/api/channels', function(data) {
		var template = Handlebars.compile($('#channel-template').html());
		var html = template(data);
		$('#channel-tabs').html(html);
	});

	// 订阅
	$('#channel-tabs').on('click', 'span[id^="feed-"]', function() {
		$.post(contextPath + '/api/categories/' + rootCategoryId, JSON.stringify({
			id : $(this).data('id')
		}), function(data) {
			console.log(data);
		});
	});
});