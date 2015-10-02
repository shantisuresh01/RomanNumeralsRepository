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
<!--
<body onload='onload();'>
-->
<body>
    <div class = output>
        <iframe  src="${pageContext.request.contextPath}/jsp/view.jsp" name="if1" id="if1" width="600" height="100%" align=middle frameBorder="0" scrolling="no">
        </iframe>
    </div>

</body>
</html>
