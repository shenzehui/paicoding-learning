var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

/**
 * 逻辑说明：
 * 1. 建立连接 connect()
 * - 与后端定义的端点建立连接: new SockJS('/ws/hello');
 * - 连接建立成功之后，订阅Broker，接收消息回传: stompClient.subscribe('/topic/hello', 消息应答回调)
 * 2. 发送消息
 * - stompClient.send("/app/hello", xxx)`
 * 3. 断开连接
 * - stompClient.disconnect();
 *
 */
function connect() {
    // var socket = new SockJS($("#endpoint").val());
    var socket = new SockJS("/ws/hello");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/hello', function (greeting) {
            // 表示这个长连接，订阅了 "/topic/hello" , 这样后端像这个路径转发消息时，我们就可以拿到对应的返回
            console.log("resp: ", greeting.body)
            showGreeting(greeting.body);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    // 表示将消息转发到哪个目标，类似与http请求中的path路径，对应的是后端 @MessageMapping 修饰的方法
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").prepend("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
});