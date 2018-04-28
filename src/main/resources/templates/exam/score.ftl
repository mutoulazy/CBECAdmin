<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>分数显示</title>
    <link rel="stylesheet" href="${ctx!}/assets/css/select.css" />
    <link rel="stylesheet" href="${ctx!}/assets/css/bootstrap-theme.css" />
    <link rel="stylesheet" href="${ctx!}/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${ctx!}/assets/skins/all.css" />
    <link rel="stylesheet" href="${ctx!}/assets/skins/flat/green.css" />
    <link rel="stylesheet" href="${ctx!}/assets/skins/remodal-1.1.0/remodal.css"/>
    <link rel="stylesheet" href="${ctx!}/assets/skins/remodal-1.1.0/remodal-default-theme.css"/>
</head>
<style>
    #back{
        background: url(${ctx!}/assets/img/back3.png) ;
        background-size: 100%;
        background-attachment: fixed;
        font-family: "微软雅黑";
        font-size: 30px;

    }
    #fontsize{
        font-weight: 600;
        font-size:18px ;
        line-height: 30px;

    }
    ul>li{
        font-size: 18px;
    }
    #ziti{
        font-size: 50px;
    }
    .score-1{
        margin-left: 37%;
        margin-top: 20%;
    }

</style>
<body id="back">

<section class="quanju " >
    <section class="score-1">
        <span style="text-align: center;">你已经完成了关于本试卷的考试</span><br><span style="text-align: center;padding-left: 10%;">成绩:${score}分</span>



    </section>

</section>



<!--js-->
<script type="text/javascript" src="${ctx!}/assets/js/jquery.min.js" ></script>
<script type="text/javascript" src="${ctx!}/assets/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="${ctx!}/assets/js/icheck.min.js" ></script>
<script type="text/javascript" src="${ctx!}/assets/js/common.js"></script>
<script type="text/javascript" src="${ctx!}/assets/skins/remodal-1.1.0/remodal.js"></script>

</body>
</html>
