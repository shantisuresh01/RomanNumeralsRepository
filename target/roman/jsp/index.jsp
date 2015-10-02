<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/roman.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">

    $(document).ready(function(){
     $("#msgid").html("Roman Numeral Converter");
     $("#rangeid").html("For Numbers enter range 1 - 3999 ");
    });

    </script>
    <script type="text/javascript">
        function validateForm() {
            var txt = "";
            if (document.getElementById("number").checkValidity() == false) {
               txt = "Value too large";
            }
            $("#error_msg").html = txt;
        }

       function clearAndSubmitForm()
       {
           document.getElementById("result").value=''; 
           document.getElementById("roman_form").submit(); 
       }

    </script>
</head>
<body>

    <form name="roman_form" id="roman_form" action="${pageContext.request.contextPath}/romanengine" method="post" > 

    <div id="msgid" class=largefont>
    </div>
    <div id="rangeid" class=largefont>
    </div>
    <table>
        <tr>
            <td>
                <div>
                    <label for="question">Enter Value:</label>
                    <input type="text" name="question"  id="question" value="${param.question}" >
                </div>
            </td>
        </tr>
        <tr>
            <td>
                    <label for="result">Result:    </label>
                    <input type="text" name="result" disabled="disabled" value=<%= request.getAttribute("result") %> id="result" \>
            </td>
        </tr>
    </table>
    
    <div class="button">
        <button id="roman_button_id" type="button" onclick="clearAndSubmitForm()">Convert</button>
    </div>

    <p id="error_msg" value="${param.error_msg}" ></p>

    </form>

</body>
</html>
