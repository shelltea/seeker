/*! nice Validator 0.6.0-beta
 * (c) 2012-2013 Jony Zhang <zj86@live.cn>, MIT Licensed
 * http://niceue.com/validator/
 */
!function(e,t){"use strict";function i(s,a){var l,u,o=this;return!o instanceof i?new i(s,a):(E(a)&&(a={valid:a}),a=a||{},u=L(s,"data-"+h+"-option"),u=u&&"{"===u.charAt(0)?Function("return "+u)():{},l=X[a.theme||u.theme||W.theme],o.options=e.extend({},W,l,u,a),o.$el=e(s),o.rules=new n(o.options.rules,!0),o.messages=new r(o.options.messages,!0),o.elements={},o.fields={},o.deferred={},o.errors={},o._init(),t)}function n(e,t){var i=t?t===!0?this:t:n.prototype;if(P(e))for(var r in e)i[r]=s(e[r])}function r(e,t){var i=t?t===!0?this:t:r.prototype;if(P(e))for(var n in e){if(!e[n])return;i[n]=e[n]}}function s(t){switch(e.type(t)){case"function":return t;case"array":return function(e){return t[0].test(e.value)||t[1]||!1};case"regexp":return function(e){return t.test(e.value)}}}function a(t){var i="";return e.map(t.split(" "),function(e){i+=","+("#"===e.charAt(0)?e:'[name="'+e+'"]')}),i.substring(1)}function l(t){if(t&&t.tagName){var i=t;switch(t.tagName){case"INPUT":case"SELECT":case"TEXTAREA":i=t.form||e(t).closest(".n-"+h);break;default:i=e(t).closest(".n-"+h)}return e(i).data(h)||e(i)[h]().data(h)}}function u(t){var i=l(t);i?(L(t,M)&&i._parse(t),e(t).trigger("focusin")):L(t,M,null)}function o(i,n){var r=e.trim(L(i,M+"-"+n));if(r)return r=Function("return "+r)(),r?s(r):t}function d(e,t,i){var n=t.msg;return P(n)&&i&&(n=n[i]),I(n)||(n=L(e,"data-msg-"+i)||L(e,"data-msg")||""),n}function f(e){var t;return e&&(t=S.exec(e)),t?t[1]:""}function c(e){return"INPUT"===e.tagName&&"checkbox"===e.type||"radio"===e.type}function g(e){return Date.parse(e.replace(/\.|\-/g,"/"))}var h="validator",p="n-ok",m="n-error",v="n-tip",y="n-loading",b="n-valid",k="n-invalid",_="msg-box",w="aria-invalid",M="data-rule",x="data-target",O="data-tip",$="data-inputstatus",A="novalidate",V=":verifiable",R=/(\w+)(?:\[(.*)\]$|\((.*)\)$)?/,T=/(?:([^:;\(\[]*):)?(.*)/,C=/[^\x00-\xff]/g,S=/^.*(top|right|bottom|left).*$/,F=/(?:(post|get):)?(.+)/i,j=/<|>/g,q=e.noop,N=e.proxy,E=e.isFunction,D=e.isArray,I=function(e){return"string"==typeof e},P=function(e){return e&&"[object Object]"===Object.prototype.toString.call(e)},H=!window.XMLHttpRequest,L=function(e,i,n){return n===t?e.getAttribute(i):(null===n?e.removeAttribute(i):e.setAttribute(i,""+n),t)},U=window.console||{log:q,info:q,warn:q},W={debug:0,timely:1,theme:"default",stopOnError:!1,ignore:"",msgWrapper:"span",msgMaker:function(e){var t,i={error:m,ok:p,tip:v,loading:y}[e.type];return t='<span class="msg-wrap '+i+'" role="alert">',t+=(e.arrow||"")+(e.icon||"")+'<span class="n-msg">'+e.msg+"</span>",t+="</span>"},msgIcon:'<span class="n-icon"></span>',msgArrow:"",msgClass:"",defaultMsg:"{0} is not valid.",loadingMsg:"Validating..."},X={"default":{formClass:"n-default",msgClass:"n-right",showOk:""}};e.fn[h]=function(t){var n=this,r=arguments;return n.is(":input")?n:(!n.is("form")&&(n=this.find("form")),!n.length&&(n=this),n.each(function(){if(I(t)){if("_"===t.charAt(0))return;var n=e(this).data(h);n&&n[t].apply(n,Array.prototype.slice.call(r,1))}else new i(this,t)}),this)},e.fn.isValid=function(e,i){var n,r,s=l(this[0]);return s?(i===t&&(i=!0),s.checkOnly=i,n=this.is(":input")?this:this.find(V),r=s._multiValidate(n,function(t){E(e)&&e.call(null,t),s.checkOnly=!1},!0),E(e)?this:r):!0},e.expr[":"].verifiable=function(e){var t=e.nodeName.toLowerCase();return("input"===t&&"submit"!==e.type&&"button"!==e.type&&"reset"!==e.type||"select"===t||"textarea"===t)&&e.disabled===!1&&null===L(e,A)},i.prototype={_init:function(){var t=this,i=t.options,n=t.fields,r=t.$el[0];if(D(i.groups)&&e.map(i.groups,function(i){if(!I(i.fields)||!E(i.callback))return null;var r=t.$el.find(a(i.fields)),s=function(){return i.callback.call(t,r)};e.extend(s,i),e.map(i.fields.split(" "),function(e){n[e]=n[e]||{},n[e].group=s})}),P(i.fields)&&e.each(i.fields,function(e,t){t&&(n[e]=I(t)?{rule:t}:t)}),t.$el.find(V).each(function(){t._parse(this)}),t.msgOpt={type:"error",pos:f(i.msgClass),cls:i.msgClass,style:i.msgStyle,icon:i.msgIcon,arrow:i.msgArrow,show:i.msgShow,hide:i.msgHide},i.valid||null===L(r,"action"))t.isAjaxSubmit=!0;else{var s=e[e._data?"_data":"data"](r,"events");t.isAjaxSubmit=s&&s.valid&&e.map(s.valid,function(e){return-1!==e.namespace.indexOf("form")?1:null}).length?!0:!1}t.$el.data(h)||(t.$el.on("submit."+h+" validate."+h,N(t,"_submit")).on("reset."+h,N(t,"_reset")).on("showtip."+h,N(t,"_showTip")).on("validated.field."+h,V,N(t,"_validatedField")).on("validated.rule."+h,V,N(t,"_validatedRule")).on("focusin."+h+" click."+h+" showtip."+h,V,N(t,"_focus")).on("focusout."+h+" validate."+h,V,N(t,"_blur")).on("click."+h,"input:radio,input:checkbox",N(t,"_click")),i.timely>=2&&t.$el.on("keyup."+h+" paste."+h,V,N(t,"_blur")).on("change."+h,"select",N(t,"_click")),t.$el.data(h,t).addClass("n-"+h+" "+i.formClass),L(r,A,!0))},_multiValidate:function(i,n,r){var s=this,a=s.options;return a.ignore&&(i=i.not(a.ignore)),s.isValid=!0,s.deferred={},i.each(function(e,i){var n=s.getField(i);if(n)return s._validate(i,n,r),!s.isValid&&a.stopOnError?!1:t}),e.when.apply(null,e.map(s.deferred,function(e){return e})).done(function(){n.call(s,s.isValid)}),e.isEmptyObject(s.deferred)?s.isValid:t},_submit:function(i,n){var r,s=this,a=s.options,l=i.target;if(L(l,"novalidateonce"))return L(l,"novalidateonce",null),t;if("only"!==n&&("validate"!==i.type||s.$el[0]===l)){if(s.submiting)return E(s.submiting)&&s.submiting.call(s),i.preventDefault(),t;if(E(a.beforeSubmit)&&a.beforeSubmit.call(s,l)===!1)return s.isAjaxSubmit&&i.preventDefault(),t;s._reset(),s.submiting=!0,r=s._multiValidate(s.$el.find(V),function(t){var i,n="focus.field",r=t||2===a.debug?"valid":"invalid";if(!t){var u=s.$el.find(":input."+k+":first");u.trigger(n),H&&u.trigger(n),i=e.map(s.errors,function(e){return e})}s.submiting=!1,E(a[r])&&a[r].call(s,l,i),s.$el.trigger(r+".form",[l,i]),t&&!s.isAjaxSubmit&&e(l).trigger("submit",["only"])}),(!r||s.isAjaxSubmit)&&i.preventDefault()}},_reset:function(t){var i=this;i.errors={},t&&i.$el.find(":verifiable").each(function(t,n){i.hideMsg(n),L(n,$,null),L(n,w,null),e(n).removeClass(b+" "+k)})},_focus:function(e){var t,i=e.target;if("showtip"!==e.type){if(this.submiting)return;if(""!==i.value&&("false"===L(i,w)||"tip"===L(i,$)))return}t=L(i,O),t&&this.showMsg(i,{msg:t,type:"tip"})},_blur:function(t,i){var n,r,s=this,a=s.options,l=t.target,u=t.type,o=100;if(!i&&"paste"!==u){if("validate"===u)r=!0,o=0;else{if(L(l,"notimely"))return;if(a.timely>=2&&"keyup"!==u)return}if(a.ignore&&e(l).is(a.ignore))return;if("keyup"===u){var d=t.keyCode,f={8:1,9:1,16:1,32:1,46:1};if(9===d&&!l.value)return;if(48>d&&!f[d])return;o=a.timely>=100?a.timely:500}}n=s.getField(l),n&&(o?(n.timeout&&clearTimeout(n.timeout),n.timeout=setTimeout(function(){s._validate(l,n,r)},o)):s._validate(l,n,r))},_click:function(e){this._blur(e,!0)},_showTip:function(e){var t=this;t.$el[0]===e.target&&t.$el.find(":verifiable["+O+"]").each(function(){t.showMsg(this,{msg:L(this,O),type:"tip"})})},_parse:function(e){var t,i=this,n=e.name,r=L(e,M);r&&L(e,M,null),(e.id&&"#"+e.id in i.fields||!e.name)&&(n="#"+e.id),n&&(t=i.fields[n]||{},t.rule=t.rule||r||"",t.rule&&(t.key=n,t.required=-1!==t.rule.indexOf("required"),t.must=t.must||!!t.rule.match(/match|checked/),t.required&&L(e,"aria-required",!0),("timely"in t&&!t.timely||!i.options.timely)&&L(e,"notimely",!0),I(t.target)&&L(e,x,t.target),I(t.tip)&&L(e,O,t.tip),i.fields[n]=i._parseRule(t)))},_parseRule:function(i){var n,r=T.exec(i.rule);if(r)return i.display=r[1],i.rules=[],n=(r[2]||"").split(";"),e.map(n,function(n){var r=R.exec(n);return r?(r[3]&&(r[2]=r[3]),i.rules.push({method:r[1],params:r[2]?e.trim(r[2]).split(", "):t}),t):null}),i.vid=0,i.rid=i.rules[0].method,i},_validatedField:function(t,i,n){var r=this,s=r.options,a=t.target,l=i.isValid=!!n.isValid,u=l?"valid":"invalid";i.old.ret=n,r.elements[i.key]=a,r.checkOnly||(n.key=i.key,n.rule=i.rid,l?n.type="ok":r.submiting&&(r.errors[i.key]=n.msg),E(i[u])&&i[u].call(r,a,n),e(a).attr(w,!l).addClass(l?b:k).removeClass(l?k:b).trigger(u+".field",[n,r]),(i.msgMaker||s.msgMaker)&&(!n.showOk&&n.msg||n.showOk&&s.showOk!==!1?r.showMsg(a,n,i):"tip"!==L(a,$)&&r.hideMsg(a,n)))},_validatedRule:function(i,n,r,s){n=n||a.getField(u);var a=this,l=a.options,u=i.target,o="",f=n.rid,c=!1,g=!1;if(s=s||{},r===!0||r===t?c=!0:(o=d(u,n,f),o||(I(r)?(o=r,r={error:o}):P(r)&&(r.error?o=r.error:(c=!0,r.ok&&I(r.ok)&&(g=!0),o=r.ok))),s.msg=(c?o:o||a.messages[f]||W.defaultMsg).replace("{0}",n.display||"")),c){if(s.isValid=!0,!g){var h=n.ok||L(u,"data-ok");h?(g=!0,s.msg=h):I(l.showOk)&&(g=!0,s.msg=l.showOk)}s.showOk=g,e(u).trigger("valid.rule",[f,s.msg])}else a.isValid=!1,e(u).trigger("invalid.rule",[f,s.msg]);l.debug&&U[c?"info":"warn"](n.vid+": "+f+" -> "+(s.msg||!0)),c&&n.old.value!==t&&n.old.value!==u.value?(n.vid=0,a._checkRule(u,n)):c&&n.vid<n.rules.length-1?(n.vid++,a._checkRule(u,n)):(n.vid=0,e(u).trigger("validated.field",[n,s]))},_checkRule:function(i,n){var r,s=this,a=n.key,l=n.rules[n.vid],u=l.method,d=l.params;if(!s.submiting||!s.deferred[a])if(n.rid=u,r=(o(i,u)||s.rules[u]||function(){return!0}).call(s,i,d,n),P(r)&&E(r.then)){var f=function(e){return I(e)||P(e)&&("error"in e||"ok"in e)?e:t};s.deferred[a]=r,!s.checkOnly&&s.showMsg(i,{type:"loading",msg:s.options.loadingMsg},n),r.then(function(r,a,l){var u,o=l.responseText,d=n.dataFilter||s.options.dataFilter;"json"===this.dataType?o=r:"{"===o.charAt(0)&&(o=e.parseJSON(o)||{}),E(d)?o=d(o):""===o?o=!0:(u=f(o),u===t&&(u=f(o.data)),o=u||!0),e(i).trigger("validated.rule",[n,o])},function(t,r){e(i).trigger("validated.rule",[n,r])}),n.isValid=t}else null===r?e(i).trigger("validated.field",[n,{isValid:!0}]):e(i).trigger("validated.rule",[n,r])},_validate:function(i,n,r){if(!i.disabled&&null===L(i,A)){n.rules||this._parse(i);var s,a,l=this,u=l.options,o=e(i),d={},f=n.group,g="tip"===L(i,$),h=n.isValid=!0;if(s=n.old=n.old||{},r=r||n.must,f&&(e.extend(d,f),a=f.call(l),a!==!0?(I(a)&&(a={error:a}),n.vid=0,n.rid="group",h=!1):(a=t,l.hideMsg(i,d))),!h||n.required||n.must||""!==i.value){if(!r&&s&&s.ret!==t&&s.value===i.value){if(s.ret.isValid||(l.isValid=!1),g)return;if(""!==i.value)return d=s.ret,o.trigger("validated.field",[n,d]),t}}else{if(g)return;if(l._focus({target:i}),s.value="",!c(i))return o.trigger("validated.field",[n,{isValid:!0}]),t}s.value=i.value,u.debug&&U.log(n.key),a!==t?o.trigger("validated.rule",[n,a,d]):n.rule&&l._checkRule(i,n)}},_getMsgOpt:function(t){return e.extend({},this.msgOpt,I(t)?{msg:t}:t)},getField:function(e){var t,i=this;return t=e.id&&"#"+e.id in i.fields||!e.name?"#"+e.id:e.name,L(e,M)&&i._parse(e),i.fields[t]},test:function(i,n){var r,s,a,l=this,u=R.exec(n);return u?(u[3]&&(u[2]=u[3]),s=u[1],a=u[2]?e.trim(u[2]).split(", "):t,s in l.rules&&(r=l.rules[s].call(l,i,a)),r===!0||r===t||!1):!0},getRangeMsg:function(e,t,i,n){if(t){var r=this,s=r.messages[i]||"",a=t[0].split("~"),l=a[0],u=a[1],o="rg",d=[""],f=+e===+e;if(2===a.length){if(l&&u){if(f&&e>=+l&&+u>=e)return!0;d=d.concat(a)}else if(l&&!u){if(f&&e>=+l)return!0;d.push(l),o="gt"}else if(!l&&u){if(f&&+u>=e)return!0;d.push(u),o="lt"}}else{if(e===+l)return!0;d.push(l),o="eq"}return s&&(n&&s[o+n]&&(o+=n),d[0]=s[o]),r.renderMsg.apply(null,d)}},renderMsg:function(){var e=arguments,t=e[0],i=e.length;if(t){for(;--i;)t=t.replace("{"+i+"}",e[i]);return t}},_getMsgDOM:function(t,i){var n,r,s,a=e(t);if(a.is(":input")?(s=i.target||L(t,x),s&&(s=this.$el.find(s),s.length&&(s.is(":input")?t=s.get(0):n=s)),n||(r=!c(t)&&t.id?t.id:t.name,n=this.$el.find(this.options.msgWrapper+"."+_+'[for="'+r+'"]'))):n=a,!n.length)if(a=this.$el.find(s||t),n=e("<"+this.options.msgWrapper+">").attr({"class":_+(i.cls?" "+i.cls:""),style:i.style||"","for":r}),c(t)){var l=a.parent();n.appendTo(l.is("label")?l.parent():l)}else n[i.pos&&"right"!==i.pos?"insertBefore":"insertAfter"](a);return n},showMsg:function(t,i,n){if(i=this._getMsgOpt(i),i.msg||i.showOk){t=e(t).get(0),e(t).is(":verifiable")&&(L(t,$,i.type),n=n||this.getField(t));var r=this._getMsgDOM(t,i),s=r[0].className;!S.test(s)&&r.addClass(i.cls),H&&"bottom"===i.pos&&(r[0].style.marginTop=e(t).outerHeight()+"px"),r.html(((n||{}).msgMaker||this.options.msgMaker).call(this,i)),r[0].style.display="",E(i.show)&&i.show.call(this,r,i.type)}},hideMsg:function(t,i){t=e(t).get(0),i=this._getMsgOpt(i);var n=this._getMsgDOM(t,i);n.length&&(E(i.hide)?i.hide.call(this,n,i.type):n[0].style.display="none")},mapMsg:function(t){var i=this;e.each(t,function(e,t){var n=i.elements[e]||i.$el.find(':input[name="'+e+'"]')[0];i.showMsg(n,t)})},setMsg:function(e){new r(e,this.messages)},setRule:function(t){new n(t,this.rules),e.map(this.fields,function(e){e.old={}})},setField:function(i,n){var r=this,s={};if(I(i)){if(null===n)return e.map(i.split(" "),function(e){e&&r.fields[e]&&(r.fields[e]=null)}),t;n&&(s[i]=n)}else P(i)&&(s=i);r.options.fields?e.extend(r.options.fields,s):r.options.fields=s,r._init()},holdSubmit:function(e){e===t&&(e=!0),this.submiting=e},destroy:function(){this._reset(!0),this.$el.off("."+h).removeData(h)}},e(document).on("focusin",":input["+M+"]",function(){u(this)}).on("click","input,button",function(){if(this.form)if("submit"===this.type&&null!==L(this,A))L(this.form,"novalidateonce",!0);else if(this.name&&c(this)){var t=this.form.elements[this.name][0];L(t,M)&&(u(t),e(t).trigger("validate"))}}).on("submit","form",function(t){if(null===L(this,A)){var i,n=e(this);n.data(h)||(i=n[h]().data(h),e.isEmptyObject(i.fields)?(L(this,A,!0),n.removeData(h)):"submit"===t.type&&i._submit(t))}}),new n({required:function(t,i){var n=e.trim(t.value),r=!0;if(i)if(1===i.length){if(!n&&!this.test(t,i[0]))return null}else"not"===i[0]&&e.map(i.slice(1),function(t){n===e.trim(t)&&(r=!1)});return r&&!!n},integer:function(e,t){var i,n="0|",r="[1-9]\\d*",s=t?t[0]:"*";switch(s){case"+":i=r;break;case"-":i="-"+r;break;case"+0":i=n+r;break;case"-0":i=n+"-"+r;break;default:i=n+"-?"+r}return i="^(?:"+i+")$",RegExp(i).test(e.value)||this.messages.integer[s]},match:function(t,i,n){var r,s,a,l,u,o,d,f="eq";if(i&&(1===i.length?a=i[0]:(f=i[0],a=i[1]),u="#"===a.charAt(0)?a:':input[name="'+a+'"]',o=this.$el.find(u)[0])){if(d=this.getField(o),r=t.value,s=o.value,n.init_match||(this.$el.on("valid.field."+h,u,function(){e(t).trigger("validate")}),n.init_match=d.init_match=1),!n.required&&""===r&&""===s)return null;if(i[2]&&("date"===i[2]?(r=g(r),s=g(s)):"time"===i[2]&&(r=+r.replace(":",""),s=+s.replace(":",""))),"eq"!==f&&!isNaN(+r)&&isNaN(+s))return!0;switch(l=this.messages.match[f].replace("{1}",d.display||a),f){case"lt":return+s>+r||l;case"lte":return+s>=+r||l;case"gte":return+r>=+s||l;case"gt":return+r>+s||l;case"neq":return r!==s||l;default:return r===s||l}}},range:function(e,t){return this.getRangeMsg(+e.value,t,"range")},checked:function(t,i,n){if(!c(t))return!0;var r,s;return s=this.$el.find('input[name="'+t.name+'"]').filter(function(){return!r&&c(this)&&(r=this),!this.disabled&&this.checked&&e(this).is(":visible")}).length,i?this.getRangeMsg(s,i,"checked"):!!s||d(r,n,"checked")||this.messages.required},length:function(e,t){var i=e.value,n=(t[1]?i.replace(C,"xx"):i).length;return t&&"~"===t[0].charAt(0)&&(t[0]="0"+t[0]),this.getRangeMsg(n,t,"length",t[1]?"_2":"")},remote:function(t,i){var n,r,s,a,l=this,u={};return i?(n=F.exec(i[0]),s=n[2],a=(n[1]||"POST").toUpperCase(),u[t.name]=t.value,i[1]&&e.map(i.slice(1),function(t){u[e.trim(t)]=l.$el.find(':input[name="'+t+'"]').val()}),u=e.param(u),"POST"===a&&(r=s.indexOf("?"),-1!==r&&(u+="&"+s.substring(r+1,s.length),s=s.substring(0,r))),e.ajax({url:s,type:a,data:u,async:!0,cache:!1})):!0},filter:function(e,t){var i=t?RegExp("["+t[0]+"]","g"):j;return e.value=e.value.replace(i,""),!0}}),i.config=function(t){e.each(t,function(e,t){"rules"===e?new n(t):"messages"===e?new r(t):W[e]=t})},i.setTheme=function(t,i){P(t)?e.each(t,function(e,t){X[e]=t}):I(t)&&P(i)&&(X[t]=i)},e[h]=i}(jQuery);
