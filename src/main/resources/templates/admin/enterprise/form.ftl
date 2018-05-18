<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>编辑机构</title>
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
                    <h5>机构管理</h5>
                </div>
                <div class="ibox-content">
                    <p>进行机构信息设置</p>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>机构信息编辑</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/enterprise/edit">
                        <input type="hidden" id="id" name="id" value="${enterprise.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">机构名称：</label>
                            <div class="col-sm-8">
                                <input id="name" name="name" class="form-control" type="text" value="${enterprise.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">机构简称：</label>
                            <div class="col-sm-8">
                                <input id="shrotName" name="shrotName" class="form-control" type="text" value="${enterprise.shrotName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">机构分类：</label>
                            <div class="col-sm-8">
                                <select name="type" class="form-control">
                                    <option value="学校" <#if enterprise.type == "学校">selected="selected"</#if>>学校</option>
                                    <option value="企业" <#if enterprise.type == "企业">selected="selected"</#if>>企业</option>
                                    <option value="其他" <#if enterprise.type == "其他">selected="selected"</#if>>其他</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">机构性质：</label>
                            <div class="col-sm-8">
                                <select name="nature" class="form-control">
                                    <option value="国营企业" <#if enterprise.nature == "">selected="selected"</#if>>国营企业</option>
                                    <option value="私营企业" <#if enterprise.nature == "">selected="selected"</#if>>私营企业</option>
                                    <option value="事业单位" <#if enterprise.nature == "">selected="selected"</#if>>事业单位</option>
                                    <option value="合资企业" <#if enterprise.nature == "">selected="selected"</#if>>合资企业</option>
                                    <option value="外资企业" <#if enterprise.nature == "外资企业">selected="selected"</#if>>外资企业</option>
                                    <option value="其他" <#if enterprise.nature == "其他">selected="selected"</#if>>其他</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">机构人数：</label>
                            <div class="col-sm-8">
                                <input id="peopleCount" name="peopleCount" class="form-control" type="text" value="${enterprise.peopleCount}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">机构负责人：</label>
                            <div class="col-sm-8">
                                <input id="changeMan" name="changeMan" class="form-control" type="text" value="${enterprise.changeMan}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">机构电话：</label>
                            <div class="col-sm-8">
                                <input id="phone" name="phone" class="form-control" type="text" value="${enterprise.phone}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">机构邮箱：</label>
                            <div class="col-sm-8">
                                <input id="mail" name="mail" class="form-control" type="text" value="${enterprise.mail}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">机构门户网站：</label>
                            <div class="col-sm-8">
                                <input id="webSite" name="webSite" class="form-control" type="text" value="${enterprise.webSite}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">机构地址：</label>
                            <div class="col-sm-8">
                                <input id="address" name="address" class="form-control" type="text" value="${enterprise.address}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">机构介绍：</label>
                            <div class="col-sm-8">
                                <#--文本编辑器的位置-->
                                <textarea id="description" name="description" class="form-control" type="text">${enterprise.description}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">状态：</label>
                            <div class="col-sm-8">
                                <select name="isHide" class="form-control">
                                    <option value="0" <#if enterprise.isHide == 0>selected="selected"</#if>>显示</option>
                                    <option value="1" <#if enterprise.isHide == 1>selected="selected"</#if>>隐藏</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">备注：</label>
                            <div class="col-sm-8">
                                <input id="remark" name="remark" class="form-control" value="${enterprise.remark}">
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

        $("#frm").validate({
            rules: {
                name: {
                    required: true,
                    minlength: 2,
                    maxlength: 20
                },
                peopleCount: {
                    required: true,
                    number:true
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
                CKupdate();
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/admin/enterprise/edit",
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
