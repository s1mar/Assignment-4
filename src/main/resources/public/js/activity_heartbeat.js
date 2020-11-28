function heartBeat(){

    //Keep pinging the server after every 250 milliseconds
    setInterval(function (){
        const http_heartbeat = new XMLHttpRequest();
        http_heartbeat.open("GET","/clr",true);
        http_heartbeat.send();
    },250) //every 250 milliseconds

}
setTimeout(heartBeat(),0);