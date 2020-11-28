<html>

<head>
        <script type = "text/javascript" >
            function disableBackButton()
            {
                window.history.forward();
            }
        setTimeout("disableBackButton()", 0);
        window.onunload=function()
        {
            null
        };
        disableBackButton();
    </script>
</head>

<body>
   You have been logged out. <a href=/login>Please click here to login</a>

</body>


</html>