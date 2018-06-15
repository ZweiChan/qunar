$(function () {
    $('#sumbit').click(function () {
        if ($('#from').val().length == 0 || $('#to').val().length == 0 || $('#datetimepicker1').val().length == 0) {
            $('#hint').text("不能为空").css({"background-color": "green"});
        } else {
            $.ajax({
                url: 'http://localhost:8080/getTicketInfo',
                type: 'POST',
                data: {startAddress:"上海", endAddress:"北京", date:"20180620"},
                dataType: 'json',
                success:function(data){
                    alert("success");
                    var json = eval(data); //数组
                    var dbody =$("#dbody");
                    $.each(json, function (index, item) {
                        //循环获取数据
                        document.write(json[index].id);
                        var id = json[index].id;//
                        var date = json[index].date;//
                        var startAddress = json[index].startAddress;//
                        var endAddress = json[index].endAddress;//
                        var startTime = json[index].startTime;//
                        var endTime= json[index].endTime;//
                        var company = json[index].company;//
                        var price = json[index].price;//
                        var dl = $('<dl></dl>');

                        dl.append('<dd class="dd0" style="display:none;">'+" "+id+'</dd>'
                            +'<dd class="dd1">'+company+'<span><h3>'+startTime+'</h3><h3>'+endTime+'</h3></span></dd>'
                            +'<dd class="dd2">'+"￥"+price+'</dd>'
                            +'<dd class="dd3"><a href="#">点击购票</a></dd>');
                        dbody.append(dl);
                    });
                    dbody.replaceWith(dbody);
                },
                error:function(err){
                    alert("error:"+err);
                }
            });
        }
    });
});