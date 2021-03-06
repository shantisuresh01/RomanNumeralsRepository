<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/roman.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">

    $(document).ready(function(){
     $("#msgid").html("Roman Numeral Converter");
     $("#rangeid").html("Enter range 1 - 3999 ");
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
           document.getElementById("numeral_result").value=''; 
           document.getElementById("roman_form").submit(); 
       }

    </script>
</head>
<body>

    <form name="roman_form" id="roman_form" action="${pageContext.request.contextPath}/romanengine" onsubmit="return validateForm()" method="post" > 

    <div id="msgid" class=largefont>
    </div>
    <div id="rangeid" class=largefont>
    </div>
    <table>
        <tr>
            <td>
                <div>
                    <label for="number">Enter Number:</label>
                    <input type="number" name="number" id="number" value="${number}" min="1" max="3999" >
                </div>
            </td>
        </tr>
        <tr>
            <td>
                    <label for="numeral_result">Numeral:    </label>
                    <input type="text" name="numeral" disabled="disabled" value=<%= request.getAttribute("numeral") %> id="numeral_result" \>
            </td>
        </tr>
    </table>
    
    <div class="button">
        <button id="roman_button_id" type="button" onclick="clearAndSubmitForm()">Convert</button>
    </div>

    <p id="error_msg"><rp>

    </form>

</body>
</html>
