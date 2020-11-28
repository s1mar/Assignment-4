<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
   <!-- <meta http-equiv="refresh" content="10"> -->
    <script src="/js/activity.js"></script>
    <title>Home</title>
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
        <p>Welcome ${role}, ${uname}!</p>
        <table style="width:100%">
            <tr>
                <th>Role</th>
                <th>Description</th>

            </tr>
                <tr>
                    <td><a href="/admin" onclick="userMovingWithingTheSameDomain()"><img src="/img/admin.jpg" alt="click for admin panel" style="width:150px;height:150px;"></a></td>
                    <td><a href="/admin" onclick="userMovingWithingTheSameDomain()">Click for admin panel</a></td>
                </tr>
                  <tr>
                    <td> <a href="/customer" onclick="userMovingWithingTheSameDomain()"><img src="/img/customer.jpg" alt="click for customer panel" style="width:150px;height:150px;"></a></td>
                    <td><a href="/customer" onclick="userMovingWithingTheSameDomain()">Click for customer panel</a></td>
                  </tr>

                  <tr>
                     <td> <a href="/vendor" onclick="userMovingWithingTheSameDomain()"><img src="/img/vendor.png" alt="click for vendor panel" style="width:150px;height:150px;"></a></td>
                   <td><a href="/vendor" onclick="userMovingWithingTheSameDomain()">Click for vendor panel</a></td>
                    </tr>
                </table>
            </tr>
        </table>      
    </div>
    
  </div>
</body>
</html>
