var wo= new Worker('/js/activity_heartbeat.js');
wo.postMessage("message");

function showWorkMessage(user_role){

    const xhttp = new XMLHttpRequest();
    logToServerConsole("Before ready state change");
    xhttp.onreadystatechange = function (){
        let alert_msg = MSG_NOT_AUTHORIZED;
        let targetRole;
        let isCurrentUserAuth;
        if (this.readyState === 4 && this.status === 200) {
            targetRole = this.responseText;
            user_role = user_role.replace(/[']+/g,'');
            //logToServerConsole("Response recieved : "+this.responseText+" with target role:"+targetRole+"-with user role:"+user_role);

            isCurrentUserAuth = targetRole === "admin" || targetRole === user_role;

            //logToServerConsole("is Current Auth: "+isCurrentUserAuth);
            if (isCurrentUserAuth) {

                switch (user_role) {
                    case "vendor":
                        alert_msg = MSG_VENDOR_ROLE;
                        break;
                    case "customer":
                        alert_msg = MSG_CUSTOMER_ROLE;
                        break;
                    case "admin":
                        alert_msg = MSG_ADMIN_WORK;
                        break;
                }
            }
            logToServerConsole("Call to alert with message "+alert_msg);
            alert(alert_msg);
        }
    }
    xhttp.open("GET","/authorization");
    xhttp.send();
    return false;
}

function logToServerConsole(msg){
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/log", true);
    xhttp.send(msg);
}