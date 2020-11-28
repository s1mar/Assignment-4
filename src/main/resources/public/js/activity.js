
//Add event listener for browser close window
var movingWithinTheSameDomain = false;
window.addEventListener("beforeunload", function(event) {
    var targetHost = new URL(event.target.URL).hostname;
    movingWithinTheSameDomain = targetHost === window.location.hostname;
    if(!movingWithinTheSameDomain)
    {userClosePageOrTab();}

});

function logToServerConsole(msg){
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/log", true);
    xhttp.send(msg);
}

function userClosePageOrTab(){
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/stinvalidaton", true);
    xhttp.send();
}

function userMovingWithingTheSameDomain(){
    movingWithinTheSameDomain = true;
    return true;
}

function activityWatcher(){
 
    //The number of seconds that have passed
    //since the user was active.
    var secondsSinceLastActivity = 0;
 
    //Five minutes. 60 x 5 = 300 seconds.
    //var maxInactivity = (60 * 5);
    var maxInactivity = 10; //1 minute
    //Setup the setInterval method to run
    //every second. 1000 milliseconds = 1 second.
    setInterval(function(){
        secondsSinceLastActivity++;
        console.log(secondsSinceLastActivity + ' seconds since the user was last active');
        //if the user has been inactive or idle for longer
        //then the seconds specified in maxInactivity
        if(secondsSinceLastActivity > maxInactivity){
            console.log('User has been inactive for more than ' + maxInactivity + ' seconds');
            //Redirect them to your logout.php page.
            var xhttp = new XMLHttpRequest();
            xhttp.open("GET", "/id", true);
            xhttp.send();
            //alert("Your session has expired!")
            //location.href = 'logout.php';
        }
    }, 1000);
 
    //The function that will be called whenever a user is active
    function activity(){
        //reset the secondsSinceLastActivity variable
        //back to 0
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
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
activityWatcher();

