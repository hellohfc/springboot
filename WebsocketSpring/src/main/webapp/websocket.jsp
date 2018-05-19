<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String wsPath = "ws://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


    <script type="text/javascript" src="js/jquery-1.12.2.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/sockjs.min.js" charset="utf-8"></script>



    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Java API for WebSocket (JSR-356)</title>
</head>
<body>


<script type="text/javascript">
    var websocket = null;

    alert("window is"+window);

    if ('WebSocket' in window) {
        websocket = new WebSocket("<%=wsPath%>socketServer")
    }
    else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("<%=wsPath%>socketServer");
    }
    else {
        //SockJS的连接
        websocket = new SockJS("<%=basePath%>sockjs/socketServer");

    }
    websocket.onopen = onOpen;
    websocket.onmessage = onMessage;
    websocket.onerror = onError;
    websocket.onclose = onClose;

    function onOpen(openEvt) {
        alert("open");
    }

    function onMessage(evt) {
        alert(evt.data);
    }
    function onError() {
    }
    function onClose() {
    }

    window.close = function () {
        websocket.onclose();
    }

    function doSend() {
        if (websocket.readyState == websocket.OPEN) {
            var msg = document.getElementById("inputMsg").value;
            websocket.send(msg);//调用后台handleTextMessage方法
            alert("发送成功!");
        } else {
            alert("连接失败!");
        }
    }
</script>
请输入：<textarea rows="3" cols="100" id="inputMsg" name="inputMsg"></textarea>
<button onclick="doSend()">发送</button>



</body>
</html>