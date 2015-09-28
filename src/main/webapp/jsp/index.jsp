<html>
<head>
    <link rel="stylesheet" href="css/roman.css"/>
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">

    $(document).ready(function(){
     $("#msgid").html("Roman Numeral Converter, range 1 - 3999 ");
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
<body onLoad="clearForms()" onUnload="clearForms()">

    <form name="roman_form" id="roman_form" action="romanengine" onsubmit="return validateForm()" method="post" > 

    <div id="msgid" class=largefont>
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
                    <label for="numeral_result">Result:    </label>
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
