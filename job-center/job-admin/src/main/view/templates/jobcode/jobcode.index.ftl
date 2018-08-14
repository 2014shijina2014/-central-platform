<!DOCTYPE html>
<html>
<head>
  	<title>任务调度中心</title>
  	<#import "common/common.macro.ftl" as netCommon>
	<@netCommon.commonStyle />
	<link rel="stylesheet" href="/plugins/codemirror/lib/codemirror.css">
	<link rel="stylesheet" href="/plugins/codemirror/addon/hint/show-hint.css">
	<style type="text/css">
		.CodeMirror {
      		font-size:16px;
            width: 100%;
      		height: 100%;
            /*bottom: 0;
            top: 0px;*/
            position: absolute;
		}
    </style>
</head>
<body class="skin-blue fixed layout-top-nav">

	<div class="wrapper">

        <header class="main-header">
            <nav class="navbar navbar-static-top">
                <div class="container">
					<#-- icon -->
                    <div class="navbar-header">
                        <a class="navbar-brand"><b>Web</b>IDE</a>
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                            <i class="fa fa-bars"></i>
                        </button>
                    </div>

                    <#-- left nav -->
                    <div class="collapse navbar-collapse pull-left" id="navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li class="active" ><a href="javascript:;"><#list GlueTypeEnum as item><#if item == jobInfo.glueType>${item.desc}</#if></#list> 任务：${jobInfo.jobDesc}<span class="sr-only">(current)</span></a></li>
                        </ul>
                    </div>

					<#-- right nav -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">版本回溯 <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li <#if jobLogGlues?exists && jobLogGlues?size gt 0 >style="display: none;"</#if> >
                                        <a href="javascript:;" class="source_version" version="version_now" glueType="${jobInfo.glueType}" >
                                            <#list GlueTypeEnum as item><#if item == jobInfo.glueType>${item.desc}</#if></#list>： ${jobInfo.glueRemark}
                                        </a>
                                    </li>
                                    <textarea id="version_now" style="display:none;" >${jobInfo.glueSource}</textarea>
									<#if jobLogGlues?exists && jobLogGlues?size gt 0 >
										<#list jobLogGlues as glue>
                                            <li>
                                                <a href="javascript:;" class="source_version" version="version_${glue.id}" glueType="${glue.glueType}" >
                                                    <#list GlueTypeEnum as item><#if item == glue.glueType>${item.desc}</#if></#list>： ${glue.glueRemark}
                                                </a>
                                            </li>
                                            <textarea id="version_${glue.id}" style="display:none;" >${glue.glueSource}</textarea>
										</#list>
									</#if>
                                </ul>
                            </li>
                            <li id="save" >
								<a href="javascript:;" >
									<i class="fa fa-fw fa-save" ></i>
                                    保存
								</a>
							</li>
                        </ul>
                    </div>

                </div>
            </nav>
        </header>

		<div class="content-wrapper" id="ideWindow" ></div>

		<!-- footer -->
		<#--<@netCommon.commonFooter />-->
	</div>

    <!-- 保存.模态框 -->
    <div class="modal fade" id="saveModal" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog ">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" ><i class="fa fa-fw fa-save"></i>保存</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal form" role="form" >
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">源码备注<font color="red">*</font></label>
                            <div class="col-sm-10"><input type="text" class="form-control" id="glueRemark" placeholder="请输入备注信息" maxlength="64" ></div>
                        </div>
                        <hr>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-6">
                                <button type="button" class="btn btn-primary ok" >保存</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
	
<@netCommon.commonScript />
<script src="/plugins/codemirror/lib/codemirror.js"></script>
<script src="/plugins/codemirror/mode/clike/clike.js"></script>
<script src="/plugins/codemirror/mode/shell/shell.js"></script>
<script src="/plugins/codemirror/mode/python/python.js"></script>
<script src="/plugins/codemirror/addon/hint/show-hint.js"></script>
<script src="/plugins/codemirror/addon/hint/anyword-hint.js"></script>
<script>
var id = '${jobInfo.id}';
var glueType = '${jobInfo.glueType}';
</script>
<script src="/js/jobcode.index.1.js"></script>

</body>
</html>
