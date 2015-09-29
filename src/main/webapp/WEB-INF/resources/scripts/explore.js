$(function () {
    var rootCategoryId;

    // 计算Tab高度
    var tabsDivHeight = $(window).height() - ($('#navbar').height());
    $('#channel-tabs').height(tabsDivHeight - 25);

    // 启用页面中的Tooltip
    $('.container, .navbar').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    });

    // 获取根目录
    $.get(contextPath + '/api/categories', function (data) {
        rootCategoryId = data.data.id;
    });

    // 获取频道和订阅源
    $.get(contextPath + '/api/channels', function (data) {
        var template = Handlebars.compile($('#channel-template').html());
        var html = template(data);
        $('#channel-tabs').html(html);
    });

    // 订阅/取消订阅
    var loadingImg = '<img src="' + resources + '/styles/jquery.validator/images/loading.gif">';
    $('#channel-tabs').on('click', 'span[id^="subscribe-feed-"]', function () {
        var spanIcon = $(this);
        var feedId = spanIcon.data('id');

        spanIcon.hide().after(loadingImg);

        $.post(contextPath + '/api/categories/' + rootCategoryId, JSON.stringify({
            id: feedId
        }), function (data) {
            if (data.success) {
                spanIcon.next().remove();
                $('#subscribed-feed-' + feedId).show();
            } else {
                spanIcon.show().next().remove();
            }
        });
    }).on('click', 'span[id^="unsubscribe-feed-"]', function () {
        var spanIcon = $(this);
        var feedId = spanIcon.data('id');

        spanIcon.hide().after(loadingImg);

        $.del(contextPath + '/api/categories/' + rootCategoryId, JSON.stringify({
            id: feedId
        }), function (data) {
            $('#subscribed-feed-' + feedId).hide();
            if (data.success) {
                spanIcon.next().remove();
                $('#subscribe-feed-' + feedId).show();
            } else {
                spanIcon.show().next().remove();
            }
        });
    }).on('mouseenter', 'span[id^="subscribed-feed-"]', function () {
        $(this).hide();
        $('#unsubscribe-feed-' + $(this).data('id')).show();
    }).on('mouseleave', 'span[id^="unsubscribe-feed-"]', function () {
        $(this).hide();
        $('#subscribed-feed-' + $(this).data('id')).show();
    });
});