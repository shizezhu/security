<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
<%
	String contextPath = request.getContextPath();
	String[] plugins = request.getParameterValues("plugin") == null ? new String[0] : request.getParameterValues("plugin");
	for (String plugin : plugins) {
		switch (plugin) {
		case "beautifyhtml":
			out.println("<script src=\""+contextPath+"/js/plugins/beautifyhtml/beautifyhtml.js\"></script>");
			break;
		case "blueimp":
			out.println("<link href=\""+contextPath+"/css/plugins/blueimp/css/blueimp-gallery.min.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/blueimp/jquery.blueimp-gallery.min.js\"></script>");
			break;
		case "bootstrap-table":
			out.println("<link href=\""+contextPath+"/css/plugins/bootstrap-table/bootstrap-table.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/bootstrap-table/bootstrap-table.js\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/bootstrap-table/bootstrap-table-export.min.js\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js\"></script>");
			break;
		case "chartJs":
			out.println("<script src=\""+contextPath+"/js/plugins/chartJs/Chart.min.js\"></script>");
			break;
		case "chosen":
			out.println("<link href=\""+contextPath+"/css/plugins/chosen/chosen.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/chosen/chosen.jquery.js\"></script>");
			break;
		case "awesome-bootstrap-checkbox":
			out.println("<link href=\""+contextPath+"/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css\" rel=\"stylesheet\">");
			break;
		case "clockpicker":
			out.println("<link href=\""+contextPath+"/css/plugins/clockpicker/clockpicker.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/clockpicker/clockpicker.js\"></script>");
			break;
		case "codemirror":
			out.println("<link href=\""+contextPath+"/css/plugins/codemirror/codemirror.css\" rel=\"stylesheet\">");
			out.println("<link href=\""+contextPath+"/css/plugins/codemirror/ambiance.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/codemirror/codemirror.js\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/codemirror/mode/javascript/javascript.js\"></script>");
			break;
		case "colorpicker":
			out.println("<link href=\""+contextPath+"/css/plugins/colorpicker/css/bootstrap-colorpicker.min.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/colorpicker/bootstrap-colorpicker.min.js\"></script>");
			break;
		case "cropper":
			out.println("<link href=\""+contextPath+"/css/plugins/cropper/cropper.min.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/cropper/cropper.min.js\"></script>");
			break;
		case "datapicker":
			out.println("<link href=\""+contextPath+"/css/plugins/datapicker/datepicker3.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/datapicker/bootstrap-datepicker.js\"></script>");
			break;
		case "dataTables":
			out.println("<link href=\""+contextPath+"/css/plugins/dataTables/dataTables.bootstrap.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/dataTables/jquery.dataTables.js\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/dataTables/dataTables.bootstrap.js\"></script>");
			break;
		case "diff_match_patch":
			out.println("<script src=\""+contextPath+"/js/plugins/diff_match_patch/diff_match_patch.js\"></script>");
			break;
		case "layer":
			out.println("<script src=\""+contextPath+"/js/plugins/layer/layer.min.js\"></script>");
			break;
		case "laydate":
			out.println("<script src=\""+contextPath+"/js/plugins/layer/laydate/laydate.js\"></script>");
			break;
		case "layim":
			out.println("<script src=\""+contextPath+"/js/plugins/layer/layer.min.js\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/layer/layim/layim.css\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/layer/layim/layim.js\"></script>");
			break;
		case "dropzone":
			out.println("<link href=\""+contextPath+"/css/plugins/dropzone/basic.css\" rel=\"stylesheet\">");
			out.println("<link href=\""+contextPath+"/css/plugins/dropzone/dropzone.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/dropzone/dropzone.js\"></script>");
			break;
		case "easypiechart":
			out.println("<script src=\""+contextPath+"/js/plugins/easypiechart/jquery.easypiechart.js\"></script>");
			break;
		case "echarts":
			out.println("<script src=\""+contextPath+"/js/plugins/echarts/echarts-all.js\"></script>");
			break;
		case "fancybox":
			out.println("<link href=\""+contextPath+"/js/plugins/fancybox/jquery.fancybox.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/fancybox/jquery.fancybox.js\"></script>");
			break;
		case "flot":
			out.println("<script src=\""+contextPath+"/js/plugins/flot/jquery.flot.js\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/flot/jquery.flot.tooltip.min.js\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/flot/jquery.flot.resize.js\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/flot/jquery.flot.pie.js\"></script>");
			break;
		case "footable":
			out.println("<link href=\""+contextPath+"/css/plugins/footable/footable.core.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/footable/footable.all.min.js\"></script>");
			break;
		case "fullcalendar":
			out.println("<link href=\""+contextPath+"/css/plugins/fullcalendar/fullcalendar.css\" rel=\"stylesheet\">");
			out.println("<link href=\""+contextPath+"/css/plugins/fullcalendar/fullcalendar.print.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/fullcalendar/fullcalendar.min.js\"></script>");
			break;
		case "gritter":
			out.println("<link href=\""+contextPath+"/js/plugins/gritter/jquery.gritter.css\" rel=\"stylesheet\">");
			break;
		case "iCheck":
			out.println("<link href=\""+contextPath+"/css/plugins/iCheck/custom.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/iCheck/icheck.min.js\"></script>");
			break;
		case "ionRangeSlider":
			out.println("<link href=\""+contextPath+"/css/plugins/ionRangeSlider/ion.rangeSlider.css\" rel=\"stylesheet\">");
			out.println("<link href=\""+contextPath+"/css/plugins/ionRangeSlider/ion.rangeSlider.skinFlat.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/ionRangeSlider/ion.rangeSlider.min.js\"></script>");
			break;
		case "jasny":
			out.println("<link href=\""+contextPath+"/css/plugins/jasny/jasny-bootstrap.min.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/jasny/jasny-bootstrap.min.js\"></script>");
			break;
		case "jeditable":
			out.println("<script src=\""+contextPath+"/js/plugins/jeditable/jquery.jeditable.js\"></script>");
			break;
		case "jqgrid":
			out.println("<link href=\""+contextPath+"/css/plugins/jqgrid/ui.jqgrid.css?0820\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/jqgrid/i18n/grid.locale-cn.js?0820\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/jqgrid/jquery.jqGrid.min.js?0820\"></script>");
			break;
		case "jquery-form":
			out.println("<script src=\""+contextPath+"/js/plugins/jquery-form/jquery.form.js\"></script>");
			break;
		case "jquery-ui":
			out.println("<script src=\""+contextPath+"/js/jquery-ui-1.10.4.min.js\"></script>");
			break;
		case "jsKnob":
			out.println("<script src=\""+contextPath+"/js/plugins/jsKnob/jquery.knob.js\"></script>");
			break;
		case "jstree":
			out.println("<link href=\""+contextPath+"/css/plugins/jsTree/style.min.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/jsTree/jstree.js\"></script>");
			break;
		case "jvectormap":
			out.println("<script src=\""+contextPath+"/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js\"></script>");
			break;
		case "markdown":
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/css/plugins/markdown/bootstrap-markdown.min.css\"/>");
			out.println("<script type=\"text/javascript\" src=\""+contextPath+"/js/plugins/markdown/markdown.js\"></script>");
			out.println("<script type=\"text/javascript\" src=\""+contextPath+"/js/plugins/markdown/to-markdown.js\"></script>");
			out.println("<script type=\"text/javascript\" src=\""+contextPath+"/js/plugins/markdown/bootstrap-markdown.js\"></script>");
			out.println("<script type=\"text/javascript\" src=\""+contextPath+"/js/plugins/markdown/bootstrap-markdown.zh.js\"></script>");
			break;
		case "metisMenu":
			out.println("<script src=\""+contextPath+"/js/plugins/metisMenu/jquery.metisMenu.js\"></script>");
			break;
		case "morris":
			out.println("<link href=\""+contextPath+"/css/plugins/morris/morris-0.4.3.min.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/morris/raphael-2.1.0.min.js\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/morris/morris.js\"></script>");
			break;
		case "nestable":
			out.println("<script src=\""+contextPath+"/js/plugins/nestable/jquery.nestable.js\"></script>");
			break;
		case "nouslider":
			out.println("<link href=\""+contextPath+"/css/plugins/nouslider/jquery.nouislider.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/nouslider/jquery.nouislider.min.js\"></script>");
			break;
		case "peity":
			out.println("<script src=\""+contextPath+"/js/plugins/peity/jquery.peity.min.js\"></script>");
			break;
		case "plyr":
			out.println("<link rel=\"stylesheet\" href=\""+contextPath+"/css/plugins/plyr/plyr.css\">");
			out.println("<script src=\""+contextPath+"/js/plugins/plyr/plyr.js\"></script>");
			break;
		case "prettyfile":
			out.println("<script src=\""+contextPath+"/js/plugins/prettyfile/bootstrap-prettyfile.js\"></script>");
			break;
		case "rickshaw":
			out.println("<script src=\""+contextPath+"/js/plugins/rickshaw/vendor/d3.v3.js\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/rickshaw/rickshaw.min.js\"></script>");
			break;
		case "simditor":
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/css/plugins/simditor/simditor.css\"/>");
			out.println("<script type=\"text/javascript\" src=\""+contextPath+"/js/plugins/simditor/module.js\"></script>");
			out.println("<script type=\"text/javascript\" src=\""+contextPath+"/js/plugins/simditor/uploader.js\"></script>");
			out.println("<script type=\"text/javascript\" src=\""+contextPath+"/js/plugins/simditor/hotkeys.js\"></script>");
			out.println("<script type=\"text/javascript\" src=\""+contextPath+"/js/plugins/simditor/simditor.js\"></script>");
			break;
		case "slimscroll":
			out.println("<script src=\""+contextPath+"/js/plugins/slimscroll/jquery.slimscroll.min.js\"></script>");
			break;
		case "sparkline":
			out.println("<script src=\""+contextPath+"/js/plugins/sparkline/jquery.sparkline.min.js\"></script>");
			break;
		case "staps":
			out.println("<script src=\""+contextPath+"/js/plugins/staps/jquery.steps.min.js\"></script>");
			break;
		case "suggest":
			out.println("<script src=\""+contextPath+"/js/plugins/suggest/bootstrap-suggest.min.js\"></script>");
			break;
		case "summernote":
			out.println("<link href=\""+contextPath+"/css/plugins/summernote/summernote.css\" rel=\"stylesheet\">");
			out.println("<link href=\""+contextPath+"/css/plugins/summernote/summernote-bs3.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/summernote/summernote.min.js\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/summernote/summernote-zh-CN.js\"></script>");
			break;
		case "sweetalert":
			out.println("<link href=\""+contextPath+"/css/plugins/sweetalert/sweetalert.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/sweetalert/sweetalert.min.js\"></script>");
			break;
		case "switchery":
			out.println("<link href=\""+contextPath+"/css/plugins/switchery/switchery.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/switchery/switchery.js\"></script>");
			break;
		case "toastr":
			out.println("<link href=\""+contextPath+"/css/plugins/toastr/toastr.min.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/toastr/toastr.min.js\"></script>");
			break;
		case "treeview":
			out.println("<link href=\""+contextPath+"/css/plugins/treeview/bootstrap-treeview.css\" rel=\"stylesheet\">");
			out.println("<script src=\""+contextPath+"/js/plugins/treeview/bootstrap-treeview.js\"></script>");
			break;
		case "validate":
			out.println("<script src=\""+contextPath+"/js/plugins/validate/jquery.validate.min.js\"></script>");
			out.println("<script src=\""+contextPath+"/js/plugins/validate/messages_zh.min.js\"></script>");
			break;
		case "webuploader":
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/css/plugins/webuploader/webuploader.css\">");
			out.println("<script src=\""+contextPath+"/js/plugins/webuploader/webuploader.min.js\"></script>");
			break;
		default :
			break;
		}
	}
%>
<link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/comm.js"></script>
