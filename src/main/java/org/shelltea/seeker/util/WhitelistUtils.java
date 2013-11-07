/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.util;

import org.jsoup.safety.Whitelist;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public class WhitelistUtils {
	/**
	 * 移除各种连接，只包含文字、图片、表格、视频。
	 * 
	 * @return whitelist
	 */
	public static Whitelist textWithImages() {
		return new Whitelist()
				.addTags("b", "br", "caption", "cite", "code", "col", "colgroup", "dd", "div", "dl", "dt", "em", "h1",
						"h2", "h3", "h4", "h5", "h6", "i", "img", "li", "ol", "p", "pre", "small", "strike", "strong",
						"sub", "sup", "table", "tbody", "td", "tfoot", "th", "thead", "tr", "u", "ul", "embed")

				.addAttributes("col", "span", "width").addAttributes("colgroup", "span", "width")
				.addAttributes("img", "align", "alt", "height", "src", "title", "width")
				.addAttributes("ol", "start", "type").addAttributes("table", "summary", "width")
				.addAttributes("td", "abbr", "axis", "colspan", "rowspan", "width")
				.addAttributes("th", "abbr", "axis", "colspan", "rowspan", "scope", "width")
				.addAttributes("ul", "type").addAttributes("embed", "align", "height", "src", "type", "width")

				.addProtocols("img", "src", "http", "https");
	}
}
