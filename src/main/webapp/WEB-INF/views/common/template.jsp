<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script id="entry-template" type="text/x-handlebars-template">
{{#each content}}
<div class="list-group-item">
	<b><a id="entry-{{id}}" data-title="{{title}}" data-content="{{content}}">{{title}}</a></b> <small>{{author}}</small> <small class="pull-right">{{format publishedTime}}</small>
</div>
{{/each}}
</script>
<script id="feed-template" type="text/x-handlebars-template">
{{#each data}}
<a href="${contextPath}/feed/{{title}}" class="list-group-item" data-feed="{{title}}">
	<img alt="{{title}}" src="{{faviconUrl}}" width="16" height="16">
	<b>{{title}}</b>
	<span class="badge">{{entryCount}}</span>
</a>
{{/each}}
</script>
