<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <!-- <meta http-equiv="refresh" content="10"> -->
    <script src="/js/activity.js"></script>
    <title>${role}</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div class="page">

    <h1>Home</h1>

    <div class="navigation">
        <a href="/" onclick="userMovingWithingTheSameDomain()">my home</a>
        <a href="/logout" onclick="userMovingWithingTheSameDomain()">Logout</a>
    </div>

    <div class="body">
        <p>${uname}, you are now viewing the ${role} dashboard</p>
    </div>

</div>
</body>
</html>