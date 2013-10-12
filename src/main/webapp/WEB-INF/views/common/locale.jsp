<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="localebar" class="container">
	<div>
		<div class="btn-group" data-toggle="buttons">
			<label id="zh_CN" class="btn btn-success btn-xs"> <input type="radio" name="locale"> 中文
			</label> <label id="en_US" class="btn btn-success btn-xs"> <input type="radio" name="locale"> English
			</label>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		var locale = $.cookie('locale');
		if (locale === undefined) {
			$('#zh_CN').addClass('active');
		} else {
			$('#' + locale).addClass('active');
		}

		$('#zh_CN,#en_US').click(function() {
			window.location = window.location.pathname + '?locale=' + $(this).attr('id');
		});
	});
</script>
