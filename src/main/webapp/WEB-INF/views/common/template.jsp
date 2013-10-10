<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script id="entry-template" type="text/x-handlebars-template">
{{#each content}}
<div class="list-group-item">
	<b><a id="entry-{{id}}" data-feed-id="{{feedId}}" data-title="{{{title}}}" data-author="{{author}}" data-published-time="{{format publishedTime}}" data-content="{{content}}" data-url="{{url}}">{{{title}}}</a></b> 
	<small>{{{author}}}</small>
	<small class="pull-right">{{format publishedTime}}</small>
</div>
{{/each}}
</script>
<script id="feed-template" type="text/x-handlebars-template">
{{#each data}}
<a href="${contextPath}/feed/{{title}}" class="list-group-item" data-feed="{{title}}">
	<img id="feed-img-{{id}}" alt="{{title}}" src="{{faviconUrl}}" width="16" height="16">
	<b>{{title}}</b>
	<span class="badge">{{entryCount}}</span>
</a>
{{/each}}
</script>
<script id="channel-template" type="text/x-handlebars-template">
<ul class="nav nav-tabs">
{{#each data}}
	<li {{#unless @index}}class="active"{{/unless}}>
		<a href="\#{{title}}" data-toggle="tab"><span class="glyphicon glyphicon-{{iconUrl}}"></span> {{title}}</a>
	</li>
{{/each}}
</ul>
<div class="tab-content">
{{#each data}}
	<div class="tab-pane tab-padding {{#unless @index}}active{{/unless}}" id="{{title}}">
		<div class="row">
		{{#each feeds}}
			<div class="col-xs-3">
				<div class="media">
  					<a class="pull-left" href="#">
    					<img class="media-object" src="{{logoUrl}}" width="50" height="50" class="img-rounded">
  					</a>
  					<div class="media-body media-icon">
    					<h5 class="media-heading"><b>{{title}}</b></h5>	
						<span id="feed-{{id}}" data-id="{{id}}" class="glyphicon glyphicon-plus pointer"></span>
  					</div>
				</div>
			</div>
		{{/each}}
		</div>
	</div>
{{/each}}
</div>
</script>
