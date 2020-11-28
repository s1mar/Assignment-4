const MSG_BASE = "Doing Work ";
const MSG_ADMIN_WORK = MSG_BASE + "for Admin Role";
const MSG_VENDOR_ROLE = MSG_BASE + "for Vendor Role";
const MSG_CUSTOMER_ROLE = MSG_BASE + "for Customer Role";
const MSG_NOT_AUTHORIZED = "Not authorized";

function activityWatcher(){
 
    //The number of seconds that have passed
    //since the user was active.
    var secondsSinceLastActivity = 0;
 
    //Five minutes. 60 x 5 = 300 seconds.
    //var maxInactivity = (60 * 5);
    var maxInactivity = 60; //1 minute
    //Setup the setInterval method to run
    //every second. 1000 milliseconds = 1 second.

    const maxHalf = Math.floor(maxInactivity/2);
    var flagKeepOnCounting = true;
    setInterval(function(){
        if(flagKeepOnCounting)
        secondsSinceLastActivity++;
        console.log(secondsSinceLastActivity + ' seconds since the user was last active');

        //if the user has been inactive or idle for longer
        //then the seconds specified in maxInactivity


        if(secondsSinceLastActivity >= maxHalf){
            console.log("SLA: "+secondsSinceLastActivity+" ,Max Half: "+maxHalf);
            document.getElementById("sit").hidden = false;
        }

        if(secondsSinceLastActivity > maxInactivity){
            flagKeepOnCounting  = false;
            console.log('User has been inactive for more than ' + maxInactivity + ' seconds');
            var xhttp = new XMLHttpRequest();
            xhttp.open("GET", "/stinvalidaton", true);
            xhttp.send();
            alert("Your session has expired!")
            window.location.replace("http://www."+window.location.host+"/login");
        }
    }, 1000);
 
    //The function that will be called whenever a user is active
    function activity(){
        //reset the secondsSinceLastActivity variable
        //back to 0
        document.getElementById("sit").hidden = true;
        secondsSinceLastActivity = 0;
    }
 
    //An array of DOM events that should be interpreted as
    //user activity.
    var activityEvents = [
        'mousedown', 'mousemove', 'keydown',
        'scroll', 'touchstart'
    ];
 
    //add these events to the document.
    //register the activity function as the listener parameter.
    activityEvents.forEach(function(eventName) {
        document.addEventListener(eventName, activity, true);
    });


}

activityWatcher();

