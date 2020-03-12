<!DOCTYPE html>
<html>
<head>
    <title>传递信息测试</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<body>
    <div class="container">
        <div class="row">
            <h2>Form data</h2>
            <hr/>
            <p>This is a simple page showing the data you have just submitted</p>
            <pre><?php print_r($_POST); ?></pre>
        </div>
    </div>
</body>
</html>