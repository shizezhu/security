var errorIocn = "<i class='fa fa-times-circle'></i> ";
(function($) {
	
	var myLayer = {
			
			alert : function(text, icon, title) {
				return layer.alert(text || "提示", {
	                icon: icon || 6,
	                shadeClose: true,
	                title: title || "提示"
	            });
			},
			alertError : function() {
				var text = "错误";
				if (arguments.length == 1) {
					if (typeof arguments[0] == "string") {
						text = arguments[0];
					} else if (typeof arguments[0] == "object") {
						text = "状态码:" + arguments[0].code + ",消息:" + arguments[0].msg;
					}
				} else if (arguments.length == 2) {
					text = "状态码:" + arguments[0] + ",消息:" + arguments[1];
				}
				return myLayer.alert(text, 5, "错误");
			},
			alertSuccess : function() {
				var text = "成功";
				if (arguments.length == 1) {
					if (typeof arguments[0] == "string") {
						text = arguments[0];
					} else if (typeof arguments[0] == "object") {
						text = "状态码:" + arguments[0].code + ",消息:" + arguments[0].msg;
					}
				} else if (arguments.length == 2) {
					text = "状态码:" + arguments[0] + ",消息:" + arguments[1];
				}
				return myLayer.alert(text, 6, "成功");
			},
			alertWarning : function() {
				var text = "警告";
				if (arguments.length == 1) {
					if (typeof arguments[0] == "string") {
						text = arguments[0];
					} else if (typeof arguments[0] == "object") {
						text = "状态码:" + arguments[0].code + ",消息:" + arguments[0].msg;
					}
				} else if (arguments.length == 2) {
					text = "状态码:" + arguments[0] + ",消息:" + arguments[1];
				}
				return myLayer.alert(text, 1, "警告");
			},
			msg : function (text, icon, offset) {
				return layer.msg(text || '提示', {
					  offset: offset || 't',
					  icon: icon || 6
				});
			},
			successMsg : function(text) {
				return myLayer.msg(text, 6);
			},
			errorMsg : function(text) {
				return myLayer.msg(text, 5);
			},
			warningMsg : function(text) {
				return myLayer.msg(text, 1);
			},
			loading : function (text) {
				return layer.msg(text || "请稍候...", {
				  icon: 16,
				  shade: 0.01,
				  time : 0
				});
			},
			confirm : function(title, text, okFunc, icon) {
				var index = layer.confirm(text || "您确定要执行此操作吗?", {
					icon: icon || 0,
					title: title || "警告",
				    btn: ['确定','取消'], //按钮
				    shade: false //不显示遮罩
				}, function() {
					okFunc();
				});
				return index;
			},
			tips : function(content, element, tips, tipsMore, focus, time) {
				var index =  layer.tips(content, element, {
						tips: tips,
						tipsMore : tipsMore || false,
						time : time || 0
					}
				);
				var isfocus = focus || false;
				if (isfocus) {
					$(element).focus();
				}
				return index;
			},
			childFrameTips : function(layero, content, element, tips, tipsMore, focus, time) {
				window[layero.find('iframe')[0]['name']].$.myLayer.tips(content, element, tips, tipsMore, focus, time);
			},
			date : function(element) {
				var d = {
					elem: element,
		            format: 'YYYY-MM-DD',
		            min: '2010-01-01', //设定最小日期
		            max: laydate.now(), //最大日期为当前日期
		            start: laydate.now(),
		            istime: false,
		            istoday: true
				}
				laydate(d);
			},
			dateTime : function(element) {
				var d = {
					elem: element,
					format: 'YYYY-MM-DD hh:mm:ss',
		            min: '2010-01-01 00:00:00',//设定最小日期
		            max: laydate.now(), //最大日期为当前日期
		            start: laydate.now(),
		            istime: true,
		            istoday: true
				}
				laydate(d);
			},
			dateRange : function(startElement, endElement) {
				var s = {
						elem: startElement,
			            format: 'YYYY-MM-DD',
			            min: '2010-01-01', //设定最小日期
			            max: laydate.now(), //最大日期为当前日期
			            start: laydate.now(),
			            istime: false,
			            istoday: true,
			            choose: function (datas) {
			            	e.min = datas; //开始日选好后，重置结束日的最小日期
			            	e.start = datas //将结束日的初始值设定为开始日
			            }
					}
				var e = {
						elem: endElement,
			            format: 'YYYY-MM-DD',
			            min: '2010-01-01', //设定最小日期
			            max: laydate.now(), //最大日期为当前日期
			            start: laydate.now(),
			            istime: false,
			            istoday: true,
			            choose: function (datas) {
			            	s.max = datas; //结束日选好后，重置开始日的最大日期
			            }
					}
				laydate(s);
				laydate(e);
			},
			dateTimeRange : function(startElement, endElement) {
				var s = {
						elem: startElement,
						format: 'YYYY-MM-DD hh:mm:ss',
			            min: '2010-01-01 00:00:00',//设定最小日期
			            max: laydate.now(), //最大日期为当前日期
			            start: laydate.now(),
			            istime: true,
			            istoday: true,
			            choose: function (datas) {
			            	e.min = datas; //开始日选好后，重置结束日的最小日期
			            	e.start = datas //将结束日的初始值设定为开始日
			            }
					}
				var e = {
						elem: endElement,
						format: 'YYYY-MM-DD hh:mm:ss',
			            min: '2010-01-01 00:00:00',//设定最小日期
			            max: laydate.now(), //最大日期为当前日期
			            start: laydate.now(),
			            istime: true,
			            istoday: true,
			            choose: function (datas) {
			            	s.max = datas; //结束日选好后，重置开始日的最大日期
			            }
					}
				laydate(s);
				laydate(e);
			},
			closeSelf : function() {
				parent.layer.close(parent.layer.getFrameIndex(window.name));
			},
			open : function(title, width, hight, content, yesFuc) {
				return layer.open({
					id : 'layer',
					type : 2,
					title : title || '操作',
					area : [ width || '600px', hight || 'auto' ],
					content : content || "",
					btn : [ '确定', '取消' ],
					yes : yesFuc || function(index, layero) {
						layer.getChildFrame('body', index).find("form").mySubmit();
					}
				});
			}
	};
	
	var myBootstrapTable = {
			
			getValue : function(table) {
				var values =  $(table).bootstrapTable('getSelections');
				return values.length > 0 ? values[0] : null;
			},
			getValues : function(table) {
				return $(table).bootstrapTable('getSelections');
			},
			getId : function(table) {
				var ids = $.map($(table).bootstrapTable('getSelections'), function (row) {
					return row.id;
				});
				return ids.length > 0 ? ids[0] : null;
			},
			getIds : function(table) {
				var ids = $.map($(table).bootstrapTable('getSelections'), function (row) {
		            return row.id;
		        });
				return ids;
			},
			getData : function(table) {
				return $(table).bootstrapTable('getData');
			},
			getIndexByUniqueId : function(table, uniqueId) {
				var index = -1;
				var allTableData = myBootstrapTable.getData(table);
				for (var i = 0; i < allTableData.length; i++) {
				    if (allTableData[i].id == uniqueId){
				    	index = i;
				        break;
				    }
				}
				return index;
			},
			getIndexAll : function(table) {
				var index = [];
				var allTableData = myBootstrapTable.getData(table);
				for (var i = 0; i < allTableData.length; i++) {
					index.push(i);
				}
				return index;
			},
			formatter : {
				sequence : function (value, row, index) {
					return index + 1;
				},
				date : function (value, row, index) {
					if (!value) { return "-"; }
					return value.year + "-"  + value.monthValue + "-" + value.dayOfMonth;
				},
				dateTime : function (value, row, index) {
					if (!value) { return "-"; }
					return value.year + "-"  + value.monthValue + "-" + value.dayOfMonth + " " + value.hour + ":" + value.minute + ":" + value.second;

				},
				number : function(value, row, index) {
					if (!value) { return "-"; }
					try {
						n = 2;  
						s = value;
					    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";  
					    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];  
					    t = "";  
					    for (i = 0; i < l.length; i++) {  
					        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
					    }  
					    return t.split("").reverse().join("") + "." + r;  
					} catch (e) {
						return 0;
					}
				},
				percent : function(value, row, index) {
					if (!value) { return "-"; }
					if (value === 0) { return "0%"; }
					var num = 0;
					try {
						var m = Math.pow(10, 4);
						num = parseInt(value * 100 * m, 10) / m;
					} catch (e) {
						return "0%";
					}
					return num + "%";
				},
				status : function(value, row, index) {
					if (value == true) {
						return "有效";
					} else if (value == false) {
						return "无效";
					} else {
						return "-";
					}
				}
			},
			save : function(table, data) {
				$(table).bootstrapTable('append', data);
			},
			updateById : function(table, data) {
				$(table).bootstrapTable('updateByUniqueId', {id: data.id, row: data});
			},
			delById : function(table, id) {
				$(table).bootstrapTable('removeByUniqueId', id);
			}
	};
	
	var myAjax = function(option) {
		var layerIndex = null;
    	$.ajax({
			url : option.url || "",
			data : option.data || {},
			type : option.type || "get",
			dataType : option.dataType || "json",
			async : option.async == false ? false : true,
			beforeSend : function() {
				layerIndex = myLayer.loading();
			},
			complete : function() {
				layer.close(layerIndex);
			},
			success : option.success || new Function(),
			error : function() {
				myLayer.alertError("发生了异常");
			}
		});
	};
	
	var myAjaxForm = function(form, option) {
		if (!form || !option) {
			return;
		}
		$.validator.setDefaults({
	        highlight: function (element) {
	            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	        },
	        success: function (element) {
	            element.closest('.form-group').removeClass('has-error').addClass('has-success');
	        },
	        errorElement: "span",
	        errorPlacement: function (error, element) {
	            if (element.is(":radio") || element.is(":checkbox")) {
	                error.appendTo(element.parent().parent().parent());
	            } else {
	                error.appendTo(element.parent());
	            }
	        },
	        errorClass: "help-block m-b-none",
	        validClass: "help-block m-b-none"
	    });
		if (option.methods && option.methods.length > 0) {
			$.each(option.methods, function(i, o) {
				if (o && o.name) {
					$.validator.addMethod(o.name, o.method || function() {return false;}, o.message);
				}
			});
		}
		var layerIndex = null;
		$(form).validate({
            rules: option.rules || {},
            messages: option.messages || {},
            showErrors : option.showErrors,
            submitHandler : function(f) {
            	$(f).ajaxSubmit({
            		url : option.url || "",
            		data : option.data || {},
            		type : option.type || "post",
            		dataType : option.dataType || "json",
            		beforeSubmit : function(XHR) {
        				layerIndex = myLayer.loading();
        			},
        			complete : function() {
        				layer.close(layerIndex);
        			},
            		success : option.success || new Function(),
        			error : function() {
        				myLayer.alertError("发生了异常");
        			}
            	});
            }
        });
    };
    
    var mySubmit = function() {
		var $sm = $('<input type="submit" style="display: none;"/>');
		$(this).append($sm);
		$sm.click();
		$sm.remove();
	};
	
	var myResetForm = function() {
		$(this).find('.i-checks input[type="checkbox"]').iCheck("uncheck");
		$(this).resetForm();
	};
	
	$.extend({
		myLayer : myLayer,
		myBootstrapTable : myBootstrapTable,
		myAjax : myAjax,
		myAjaxForm : myAjaxForm
	});
	
	$.fn.extend({
		mySubmit : mySubmit,
		myResetForm : myResetForm
	});
	
})(jQuery);
