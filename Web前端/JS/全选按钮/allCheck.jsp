<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html> 
<head> 
<title>Test.html</title> 
<script type="text/javascript"> 
function cball() { 
for(var i=1;i<=6;i++) { 
if (f1.allcheckbox.checked) 
f1.children['m'+i].checked=true; 
else 
f1.children['m'+i].checked=false; 
} 
} 
function odd() { 
var numOfChecked = 0; 
for (var i=1;i<=6;i++) { 
if (f1.children['m'+i].checked) 
numOfChecked++; 
} 
if(numOfChecked==6) 
f1.allcheckbox.checked=true; 
else 
f1.allcheckbox.checked=false; 
} 
</script> 
</head> 

<body> 
<form name="f1" id="f1"> 
<input name="allcheckbox" type="checkbox" title='全选/取消' onclick="cball()">全选<br/> 
<input name="m1" type="checkbox" onclick='odd()'>M1<br/> 
<input name="m2" type="checkbox" onclick='odd()'>M2<br/> 
<input name="m3" type="checkbox" onclick='odd()'>M3<br/> 
<input name="m4" type="checkbox" onclick='odd()'>M4<br/> 
<input name="m5" type="checkbox" onclick='odd()'>M5<br/> 
<input name="m6" type="checkbox" onclick='odd()'>M6<br/> 
</form> 
</body> 
</html>