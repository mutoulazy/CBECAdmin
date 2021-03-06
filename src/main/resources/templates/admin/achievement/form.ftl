<!DOCTYPE html>
<html lang="zh">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 成果展示</title>
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
                    <h5>成果信息管理</h5>
                </div>
                <div class="ibox-content">
                    <p>进行成果设置：</p>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>成果编辑</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/achievement/edit">
                        <input type="hidden" id="id" name="id" value="${achievement.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">发布单位：</label>
                            <div class="col-sm-8">
                                <select name="enterprise.id" class="form-control">
                                <#list eList as r>
                                    <option value="${r.id}" <#if achievement.enterprise.id == r.id>selected="selected"</#if>>
                                    ${r.name}
                                    </option>
                                </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">标题：</label>
                            <div class="col-sm-8">
                                <input id="name" name="name" class="form-control" type="text" value="${achievement.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">显示图片：</label>
                            <div class="col-sm-8">
                                <#if achievement.image?exists>
                                    <img id="eImg" src="${achievement.image}" style="width: 200px; height:  200px;" alt="" class="backimg">
                                <#else>
                                    <img id="eImg" src="${ctx!}/assets/img/default.jpg" style="width: 200px; height:  200px;" alt="" class="backimg">
                                </#if>
                                <input id="imageFile" type="file" name="imageFile" size="80"/>
                                <button id="saveImg"  type="button">上传图片</button>
                                <input type="hidden" id="image" name="image" value="${achievement.image}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">状态：</label>
                            <div class="col-sm-8">
                                <select name="isHide" class="form-control">
                                    <option value="0" <#if achievement.isHide == 0>selected="selected"</#if>>显示</option>
                                    <option value="1" <#if achievement.isHide == 1>selected="selected"</#if>>隐藏</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">成果简介：</label>
                            <div class="col-sm-8">
                                <textarea id="introduction" name="introduction" class="form-control" type="text">${achievement.introduction}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">描述：</label>
                            <div class="col-sm-8">
                            <#--文本编辑器的位置-->
                                <textarea id="description" name="description" class="form-control" type="text">${achievement.description}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">成果附件：</label>
                            <div class="col-sm-8">
                                <input id="resourceFile" type="file" name="resourceFile" size="80"/>
                                <button id="saveFile"  type="button">上传文件</button>
                                <input type="hidden" id="resource" name="resource" value="${achievement.resource}">
                                <#if achievement.resource?exists>
                                    <a href="${ctx}/file/download?fileName=${achievement.resource}">下载文件</a>
                                </#if>
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
    /*CKFinder.setupCKEditor(editor, "ckfinder/");*/ //设置图片管理组件

    //处理CKEDITOR的值
    function CKupdate() {
        for (instance in CKEDITOR.instances)
            CKEDITOR.instances[instance].updateElement();
    }
    /**
     * ajaxFileUpload    JQuery异步上传插件
     */
    function ajaxImageUpload() {
        $.ajaxFileUpload
        (
                {
                    url: "/file/upload", //用于文件上传的服务器端请求地址
                    type: 'post',
                    data: {},
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: 'imageFile', //文件上传域的ID
                    dataType: 'JSON', //返回值类型 一般设置为json
                    success: function (data, status)  //服务器成功响应处理函数
                    {
                        var obj = JSON.parse(data);
                        var str = obj.data;
                        $("#eImg").attr("src", str);
                        $("#image").attr("value", str);
                        var test = $("#image").attr("value");
                        alert("上传图片成功");
                    },
                    error: function (data, status, e)//服务器响应失败处理函数
                    {
                        alert(e);
                    }
                }
        )
        return false;
    }

    function ajaxFileUpload() {
        $.ajaxFileUpload
        (
                {
                    url: "/file/uploadFile", //用于文件上传的服务器端请求地址
                    type: 'post',
                    data: {},
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: 'resourceFile', //文件上传域的ID
                    dataType: 'JSON', //返回值类型 一般设置为json
                    success: function (data, status)  //服务器成功响应处理函数
                    {
                        var obj = JSON.parse(data);
                        var str = obj.message;
                        $("#resource").attr("value", str);
                        var test = $("#resource").attr("value");
                        alert("上传文件成功");
                    },
                    error: function (data, status, e)//服务器响应失败处理函数
                    {
                        alert(e);
                    }
                }
        )
        return false;
    }
</script>


<!-- 全局js -->
<script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>
<#-- 文件异步上传js -->
<script type="text/javascript" src="/assets/js/ajaxfileupload.js"></script>

<!-- 自定义js -->
<script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

<!-- jQuery Validation plugin javascript-->
<script src="${ctx!}/assets/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${ctx!}/assets/js/plugins/validate/messages_zh.min.js"></script>
<script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>
<script src="${ctx!}/assets/js/plugins/layer/laydate/laydate.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        /**
         * 异步上传图片
         */
        $("#saveImg").click(function () {
            //效验上传图片类型
            var ths=$('#imageFile');
            if (ths.val().length <= 0) {
                alert("请选择上传图片");
                return false;
            } else if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(ths.val())){
                alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
                ths.val("");
                return false;
            }
            //效验成功调用异步上传函数
            ajaxImageUpload();
            return true;
        })

        /**
         * 异步上传文件
         */
        $("#saveFile").click(function () {
            var ths=$('#resourceFile');
            if (ths.val().length <= 0) {
                alert("请选择上传文件");
                return false;
            }
            //效验成功调用异步上传函数
            ajaxFileUpload();
            return true;
        })

        $("#frm").validate({
            rules: {
                name: {
                    required: true,
                    minlength: 4,
                    maxlength: 30
                },
                peopleCount: {
                    required: true,
                },
                pay: {
                    required: true
                },
                isHide: {
                    required: true
                },
                description: {
                    required: true,
                }
            },
            messages: {},
            submitHandler:function(form){
                /*使富文本编辑框内容可以修改*/
                CKupdate();

                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/admin/achievement/edit",
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
