<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/roman.css"/>

    <script>
        function onload()
        {
            document.getElementById('if1').src="${pageContext.request.contextPath}/jsp/view.jsp";
        }
    </script>
 
</head>
<body onload='onload();'>
    <div class = output>
        <iframe  name='if1' id="if1">
        </iframe>
    </div>
 
</body>
</html>
