<!DOCTYPE html>
<html lang="zh">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 题目编辑</title>
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
                    <h5>题目信息管理</h5>
                </div>
                <div class="ibox-content">
                    <p>进行题目设置：</p>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>题目编辑</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/questionBank/edit">
                        <input type="hidden" id="id" name="id" value="${questionBank.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">题目类型：</label>
                            <div class="col-sm-8">
                                <select name="type" class="form-control">
                                    <option value="1" <#if questionBank.isHide == 1>selected="selected"</#if>>单选题</option>
                                    <option value="2" <#if questionBank.isHide == 2>selected="selected"</#if>>判断题</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">题目：</label>
                            <div class="col-sm-8">
                                <textarea id="name" name="name" class="form-control" type="text">${questionBank.name}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">选项A：</label>
                            <div class="col-sm-8">
                                <input id="option1" name="option1" class="form-control" type="text" value="${questionBank.option1}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">选项B：</label>
                            <div class="col-sm-8">
                                <input id="option2" name="option2" class="form-control" type="text" value="${questionBank.option2}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">选项C：</label>
                            <div class="col-sm-8">
                                <input id="option3" name="option3" class="form-control" type="text" value="${questionBank.option3}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">选项D：</label>
                            <div class="col-sm-8">
                                <input id="option4" name="option4" class="form-control" type="text" value="${questionBank.option4}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">状态：</label>
                            <div class="col-sm-8">
                                <select name="isHide" class="form-control">
                                    <option value="0" <#if questionBank.isHide == 0>selected="selected"</#if>>显示</option>
                                    <option value="1" <#if questionBank.isHide == 1>selected="selected"</#if>>隐藏</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">答案：</label>
                            <div class="col-sm-8">
                                <input id="answer" name="answer" class="form-control" type="text" value="${questionBank.answer}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">答案解析：</label>
                            <div class="col-sm-8">
                                <textarea id="description" name="description" class="form-control" type="text">${questionBank.description}</textarea>
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
        $("#frm").validate({
            rules: {
                name: {
                    required: true,
                    minlength: 4,
                    maxlength: 30
                },
                answer: {
                    required: true,
                },
                isHide: {
                    required: true
                }
            },
            messages: {},
            submitHandler:function(form){
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/admin/questionBank/edit",
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
