<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
   <!-- <meta http-equiv="refresh" content="10"> -->
    <script src="/js/activity.js"></script>
    <script src="/js/main.js"></script>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
  <div class="page">
  
    <h1>Home</h1>
    <h1 hidden id="sit">Session will expire in 30 seconds. Do some activity to not let that happen</h1>
    <div class="navigation">
      <a href="/logout" >Logout</a>
    </div>
    
    <div class="body">
        <p>Welcome ${role}, ${uname}!</p>
        <table style="width:100%">
            <tr>
                <th>Role</th>
                <th>Description</th>

            </tr>
                <tr>
                    <td><a href="javascript:void(0)" onclick="showWorkMessage('\'admin\'')"><img src="/img/admin.jpg" alt="click for admin panel" style="width:150px;height:150px;"></a></td>
                    <td><a href="javascript:void(0)" onclick="showWorkMessage('\'admin\'')">Click for admin panel</a></td>
                </tr>
                  <tr>
                    <td> <a href="javascript:void(0)" onclick="showWorkMessage('\'customer\'')"><img src="/img/customer.jpg" alt="click for customer panel" style="width:150px;height:150px;"></a></td>
                    <td><a  href="javascript:void(0)" onclick="showWorkMessage('\'customer\'')">Click for customer panel</a></td>
                  </tr>

                  <tr>
                     <td> <a href="javascript:void(0)" onclick="showWorkMessage('\'vendor\'')"><img src="/img/vendor.png" alt="click for vendor panel" style="width:150px;height:150px;"></a></td>
                   <td><a  href="javascript:void(0)" onclick="showWorkMessage('\'vendor\'')">Click for vendor panel</a></td>
                    </tr>
                </table>
            </tr>
        </table>      
    </div>
    
  </div>
</body>
</html>
