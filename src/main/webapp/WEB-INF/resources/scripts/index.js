$(function () {
    var currentPage = 0;

    // 获取选择的Feed
    var feedTitle = location.pathname.split('/')[2];

    // 启用页面中的Tooltip
    $('.container, .navbar').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    });

    // 启用弹出框中的Tooltip
    $('.modal').tooltip({
        selector: "[data-toggle=tooltip]"
    });

    // 注册Handlebars Helper
    Handlebars.registerHelper('format', function (publishedTime) {
        return new Date(publishedTime).format('MM-dd hh:mm');
    });

    // 计算Entry列表高度
    var itemsDivHeight = $(window).height() - ($('#navbar').height() + $('#toolbar').height());
    $('#entries').slimScroll({
        height: (itemsDivHeight - 35) + 'px',
        alwaysVisible: true
    });

    // 获取Feed列表
    $.get(contextPath + '/api/feeds', function (data) {
        // 如果没有订阅任何源，自动跳转
        if (data.data.length === 0) {
            window.location = contextPath + '/explore';
        }

        var template = Handlebars.compile($('#feed-template').html());
        var html = template(data);
        $('#feeds').html(html);

        // 计算总数
        var entriesCount = 0;
        $.each(data.data, function (index, item) {
            entriesCount += item.entryCount;
        });

        $('#entries-count').html(entriesCount);

        // 根据选择的Feed控制列表激活样式
        if (feedTitle) {
            $('#feeds').find('a[data-feed="' + feedTitle + '"]').addClass('active');
        }
    });

    function loadPage(page) {
        var entrySize = Math.floor(itemsDivHeight / 45);

        // 根据选择的Feed获取Entry列表
        var entryUri = contextPath + '/api/entries?page=' + (page - 1) + '&size=' + entrySize;
        if (feedTitle) {
            entryUri += '&feedTitle=' + feedTitle;
        }

        $.get(entryUri, function (data) {
            if (data.data === null) {
                return;
            }

            var template = Handlebars.compile($('#entry-template').html());
            var html = template(data.data);
            $('#entries').html(html);

            // 控制分页
            currentPage = data.data.number + 1;
            if (data.data.firstPage) {
                $('#prev-page').attr('disabled', 'disabled');
            } else if (data.data.lastPage) {
                $('#next-page').attr('disabled', 'disabled');
            } else {
                $('#prev-page,#next-page').removeAttr('disabled');
            }
        });
    }

    // 默认加载第一页Entry
    loadPage(1);

    // 打开单个Entry
    $('#entries').on('click', 'a[id^="entry-"]', function () {
        $('#entry-modal').modal();
        $('#entry-title').html($(this).data('title'));
        $('#entry-author').html($(this).data('author'));
        $('#entry-publishedTime').html($(this).data('published-time'));
        $('#entry-content').html($(this).data('content')).slimScroll({
            height: ($(window).height() - 180) + 'px',
            scrollTo: 0
        });
        $('#entry-url').attr('href', $(this).data('url'));
        $('#entry-feed-img').attr('src', $('#feed-img-' + $(this).data('feed-id')).attr('src'));
    });

    // 刷新
    $('#refresh').click(function () {
        $.put(contextPath + '/api/entries/refresh', function (data) {
            location.reload(true);
        });
    });

    // 上一页
    $('#prev-page').click(function () {
        loadPage(currentPage - 1);
    });

    // 下一页
    $('#next-page').click(function () {
        loadPage(currentPage + 1);
    });
});