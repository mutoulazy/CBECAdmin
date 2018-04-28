<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>试卷页面</title>
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
        padding-top:-5%;
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
    .black{
        background:rgba(0,0,0,0.1);


        box-shadow:0 0 10px #696969 !important;
        padding-top:2%;
        padding-bottom:3%;

    }
    }
    .quanju{
        padding-top:2%;
        padding-bottom:3%;
    }
    .remodal{
        background:rgba(f,f,f,0.1);
        opacity: 0.8;

    }
</style>
<body id="back">

<section class="quanju black" >
    <!--header-->
    <section class="header">
        <div class="ziti" id="ziti">
            卷一
        </div>
    </section>
    <!--判断题-->
    <section class="panduan">
        <h1>判断题</h1>
    <#list JudgmentQuestion as item>
        <div >
            <h2>第 ${item_index+1} 题</h2>
            <article id="fontsize">
            ${item.name}
            </article>
            <div style="width: 100%;height: 100px;">
                <ul>
                    <li><input type="radio" id="${item.id}" name="${item.id}" value="是"><label>对</label></li>
                    <li><input type="radio" id="${item.id}" name="${item.id}" value="否"><label>错</label></li>
                </ul>
            </div>
            <div class="alert alert-success text-center hidden success-${item.id}">正确</div>
            <div class="alert alert-danger text-center hidden danger-${item.id}">错误&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;答案:<span>对</span></div>
        </div>
    </#list>

    </section>
    <!--选择题-->
    <section class="panduan ">
        <h1>选择题</h1>
    <#list ChoiceQuestion as item>
        <div class="col-xs-12">
            <h2>第 ${item_index+1} 题</h2>
            <article id="fontsize">
            ${item.name}
            </article>
            <ul>
                <li><input type="radio" name="${item.id}" value="A"><label>A</label>  <label>${item.option1}</label> </li>
                <li><input type="radio" name="${item.id}" value="B"><label>B</label>  <label>${item.option2}</label> </li>
                <li><input type="radio" name="${item.id}" value="C"><label>C</label>  <label>${item.option3}</label> </li>
                <li><input type="radio" name="${item.id}" value="D"><label>D</label>  <label>${item.option4}</label> </li>
            </ul>
        </div>
        <div class="clearfix"></div>
        <div class="alert alert-success text-center hidden success-${item.id}">正确</div>
        <div class="alert alert-danger text-center hidden danger-${item.id}">错误&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;答案:<span>对</span></div>
    </#list>
    </section>
</section>

<div id="mmm" style="background: #ccc; border-radius: 10px; opacity:0.5; width: 200px;  right: 0; top: 0; margin-right: 10px; margin-top: 10px;  position: fixed; ">
    <h1 style="font-size: 48px;text-align: center; margin-top: 10px"><spna id="time">${ExamTime}</spna>分钟</h1>
</div>

<div>
    <a id="testpaperBt" class="link link--highlighted btn btn-success btn-lg" href="#modal" style="opacity:0.8; position: fixed; right: 0; bottom: 0; margin-right: 10px; margin-bottom: 10px">提交试卷</a>
    <!-- <button id="testpaper" class="btn btn-success btn-lg">提交试卷</button> -->
</div>


<div class="remodal " data-remodal-id="modal" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc">
    <button data-remodal-action="close" class="remodal-close" aria-label="Close"></button>
    <div>
        <h2 id="modal1Title">是否提交试卷</h2>
        <p id="modal1Desc">
            你好！是否提交试卷。
        </p>
    </div>
    <br>
    <button data-remodal-action="cancel" class="remodal-cancel">取消</button>
    <button data-remodal-action="confirm" class="remodal-confirm" id="testpaper">提交</button>
</div>

<div class="remodal" data-remodal-id="modal-score" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc">
    <button data-remodal-action="close" class="remodal-close" aria-label="Close"></button>
    <div>
        <h2 id="modal1Title">考试成绩 <span id="score">0</span>分</h2>
    </div>
    <br>
    <button data-remodal-action="cancel" class="remodal-cancel">查看答案</button>
</div>

<!--js-->
<script type="text/javascript" src="${ctx!}/assets/js/jquery.min.js" ></script>
<script type="text/javascript" src="${ctx!}/assets/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="${ctx!}/assets/js/icheck.min.js" ></script>
<script type="text/javascript" src="${ctx!}/assets/js/common.js"></script>
<script type="text/javascript" src="${ctx!}/assets/skins/remodal-1.1.0/remodal.js"></script>

<script>
    $(document).ready(function(){
        $('input').iCheck({
            checkboxClass: 'icheckbox_square',
            radioClass: 'iradio_square',
            increaseArea: '20%' // optional
        });
    });

    var inst = $('[data-remodal-id=modal-score]').remodal();

    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });


    // 提交答案并且进行判断回显页面
    function submitPapers(arr) {
        ajaxSubmitJSON("/exam/submitpapers", JSON.stringify(arr),
                function(data) {
                    var score = 0;
                    $("#mmm").remove();
                    data.forEach(function(e) {
                        if(e.correct) {
                            score+=1;
                        }
                        $("#score").text(score);
                        inst.open();
                        console.log(e);
                        if(e.correct) {
                            $(".success-"+e.questionBankId).removeClass("hidden");
                        } else {
                            $(".danger-"+e.questionBankId).removeClass("hidden");
                            if(e.answer == '是') {
                                e.answer = "错";
                            }
                            if(e.answer == '否') {
                                e.answer = "对";
                            }
                            $(".danger-"+e.questionBankId+" span").text(e.answer);
                        }
                    })

                },
                function(data) {
                    alert("错误");
                }
        );
    }

    // 试卷获取试卷选项
    var arr = [];
    $("#testpaper").on("click", function(){
    <#list JudgmentQuestion as item>
        var item${item.id} = $('input[name="${item.id}"]:checked ');
        arr.push({
            'questionBankId': $('input[name="${item.id}"]').attr('name'),
            'answer': item${item.id}.val()
        });
    </#list>
    <#list ChoiceQuestion as item>
        var item${item.id} = $('input[name="${item.id}"]:checked ');
        arr.push({
            'questionBankId': $('input[name="${item.id}"]').attr('name'),
            'answer': item${item.id}.val()
        });
    </#list>
        console.log(arr);
        console.log(JSON.stringify({"list":arr}));
        $("#testpaperBt").remove();
        submitPapers(arr);
        clearInterval(timeSetInterval);
    });

    var timeSetInterval = setInterval(function() {
        var time = $("#time").text();
        time--;
        $("#time").text(time);
        if(time==0) {
            $("#testpaperBt").remove();
            submitPapers(arr);
            clearInterval(timeSetInterval);
        }

    },1000*60);
</script>
</body>
</html>
