<!DOCTYPE html>
<html lang="zh">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 招聘计划</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>招聘计划管理</h5>
                </div>
                <div class="ibox-content">
                    <p>进行计划设置：</p>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>计划编辑</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/recruit/edit">
                        <input type="hidden" id="id" name="id" value="${recruit.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">招聘单位：</label>
                            <div class="col-sm-8">
                                <select name="enterprise.id" class="form-control">
                                <#list eList as r>
                                    <option value="${r.id}" <#if recruit.enterprise.id == r.id>selected="selected"</#if>>
                                        ${r.name}
                                    </option>
                                </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">招聘标题：</label>
                            <div class="col-sm-8">
                                <input id="name" name="name" class="form-control" type="text" value="${recruit.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">截至日期：</label>
                            <div class="col-sm-8">
                                <input id="endTime" name="endTime" readonly="readonly" class="laydate-icon form-control layer-date" value="${recruit.endTime}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">招聘类型：</label>
                            <div class="col-sm-8">
                                <select name="type" class="form-control">
                                    <option value="兼职" <#if recruit.type == "兼职">selected="selected"</#if>>兼职</option>
                                    <option value="全职" <#if recruit.type == "全职">selected="selected"</#if>>全职</option>
                                    <option value="实习" <#if recruit.type == "实习">selected="selected"</#if>>实习</option>
                                    <option value="项目" <#if recruit.type == "项目">selected="selected"</#if>>项目</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">工作经验：</label>
                            <div class="col-sm-8">
                                <select name="experience" class="form-control">
                                    <option value="不限" <#if recruit.experience == "不限">selected="selected"</#if>>不限</option>
                                    <option value="一年以上" <#if recruit.experience == "一年以上">selected="selected"</#if>>一年以上</option>
                                    <option value="一年到三年" <#if recruit.experience == "一年到三年">selected="selected"</#if>>一年到三年</option>
                                    <option value="三年以上" <#if recruit.experience == "三年以上">selected="selected"</#if>>三年以上</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">学历：</label>
                            <div class="col-sm-8">
                                <select name="education" class="form-control">
                                    <option value="不限" <#if recruit.education == "不限">selected="selected"</#if>>不限</option>
                                    <option value="大专" <#if recruit.education == "大专">selected="selected"</#if>>大专</option>
                                    <option value="本科" <#if recruit.education == "本科">selected="selected"</#if>>本科</option>
                                    <option value="研究生以上" <#if recruit.education == "研究生以上">selected="selected"</#if>>研究生以上</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">招聘人数：</label>
                            <div class="col-sm-8">
                                <input id="peopleCount" name="peopleCount" class="form-control" value="${recruit.peopleCount}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">薪资水平：</label>
                            <div class="col-sm-8">
                                <input id="pay" name="pay" class="form-control" value="${recruit.pay}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">工作地点：</label>
                            <div class="col-sm-8">
                                <input id="address" name="address" class="form-control" value="${recruit.address}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">福利：</label>
                            <div class="col-sm-8">
                                <input id="welfare" name="welfare" class="form-control" value="${recruit.welfare}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">状态：</label>
                            <div class="col-sm-8">
                                <select name="isHide" class="form-control">
                                    <option value="0" <#if recruit.isHide == 0>selected="selected"</#if>>显示</option>
                                    <option value="1" <#if recruit.isHide == 1>selected="selected"</#if>>隐藏</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">描述：</label>
                            <div class="col-sm-8">
                                <#--文本编辑器的位置-->
                                <textarea id="description" name="description" class="form-control" type="text">${recruit.description}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" type="submit">提交</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>

<#-- ueditor编辑器 -->
<script type="text/javascript" src="/assets/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
    //替换指定name的textarea为富文本编辑器
    var editor = CKEDITOR.replace('description'); //显示编辑器
    CKFinder.setupCKEditor(editor, "ckfinder/"); //设置图片管理组件

    //处理CKEDITOR的值
    function CKupdate() {
        for (instance in CKEDITOR.instances)
            CKEDITOR.instances[instance].updateElement();
    }
</script>


<!-- 全局js -->
<script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>

<!-- 自定义js -->
<script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

<!-- jQuery Validation plugin javascript-->
<script src="${ctx!}/assets/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${ctx!}/assets/js/plugins/validate/messages_zh.min.js"></script>
<script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>
<script src="${ctx!}/assets/js/plugins/layer/laydate/laydate.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        //外部js调用
        laydate({
            elem: '#endTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });

        $("#frm").validate({
            rules: {
                name: {
                    required: true,
                    minlength: 4,
                    maxlength: 20
                },
                sourceKey: {
                    required: true,
                    minlength: 4,
                    maxlength: 40
                },
                type: {
                    required: true
                },
                sourceUrl: {
                    required: true
                },
                level: {
                    required: true,
                    number:true
                },
                sort: {
                    number:true,
                    required: true
                },
                icon: {
                    maxlength: 40
                },
                isHide: {
                    required: true
                },
                description: {
                    required: true,
                    maxlength: 40
                }
            },
            messages: {},
            submitHandler:function(form){
                /*使富文本编辑框内容可以修改*/
                CKupdate();
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/admin/recruit/edit",
                    data: $(form).serialize(),
                    success: function(msg){
                        layer.msg(msg.message, {time: 2000},function(){
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index);
                        });
                    }
                });
            }
        });
    });
</script>

</body>

</html>
