<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jquery ajax</title>
<script type="text/javascript" src="jquery-1.2.6.js"></script>
<script language="javascript">

$(document).ready(function (){
  $('#send_ajax').click(function (){
     var params=$('input').serialize(); //���л�����ֵ
     $.ajax({
       url:'ajax_json.php', //��̨�������
       type:'post',         //���ݷ��ͷ�ʽ
       dataType:'json',     //�������ݸ�ʽ
       data:params,         //Ҫ���ݵ�����
       success:update_page //�ش�����(�����Ǻ�����)
     });
   });

//$.post()��ʽ��
$('#test_post').click(function (){
    $.post(
      'ajax_json.php',
      {
        username:$('#input1').val(),
        age:$('#input2').val(),
        sex:$('#input3').val(),
        job:$('#input4').val()
      },
      function (data) //�ش�����
      {
        var myjson='';
        eval_r('myjson=' + data + ';');
        $('#result').html("����:" + myjson.username + "<br/>����:" + myjson['job']);
      }
    );
   });

//$.get()��ʽ��
$('#test_get').click(function ()
{
    $.get(
      'ajax_json.php',
      {
        username:$("#input1").val(),
        age:$("#input2").val(),
        sex:$("#input3").val(),
        job:$("#input4").val()
      },
      function(data) //�ش�����
      {
        var myjson='';
        eval_r("myjson=" + data + ";");
        $("#result").html(myjson.job);
      }
    );
});
});

function update_page (json) //�ش�����ʵ�壬����ΪXMLhttpRequest.responseText
{
var str="����:"+json.username+"<br />";
str+="����:"+json.age+"<br />";
str+="�Ա�:"+json.sex+"<br />";
str+="����:"+json.job+"<br />";
str+="׷�Ӳ���:"+json.append;
$("#result").html(str);
}
</script>
<body>

<div id="result" style="background:orange;border:1px solid red;width:300px;height:200px;"></div>
<form id="formtest" action="" method="post">
    <p><span>��������:</span><input type="text" name="username" id="input1" /></p>
    <p><span>��������:</span><input type="text" name="age" id="input2" /></p>
    <p><span>�����Ա�:</span><input type="text" name="sex" id="input3" /></p>
    <p><span>���빤��:</span><input type="text" name="job" id="input4" /></p>
</form>
<button id="send_ajax">�ύ</button>
<button id="test_post">POST�ύ</button>
<button id="test_get">GET�ύ</button>
</body>
</html>