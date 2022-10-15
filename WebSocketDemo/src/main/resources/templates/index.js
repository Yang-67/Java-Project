const webSocketUrl = "ws://localhost:8081/websocket/yang";
const ws = new WebSocket(webSocketUrl)
//onopen事件监听
ws.addEventListener('open',e=>{
    console.log('与服务端连接打开->',e)
    ws.send("发送数据")
},false)
//onclose事件监听
ws.addEventListener('close',e=>{
    console.log('与服务端连接关闭->',e)
},false)
//onmessage事件监听
ws.addEventListener('message',e=>{
    console.log('来自服务端的消息->',e)
},false)
//onerror事件监听
ws.addEventListener('error',e=>{
    console.log('与服务端连接异常->',e)
},false)
