<%@ page contentType="text/html;charset=euc-kr" %>
<head>
    <title>LOGIN</title>
    <link type="text/css" href="/resources/css/loginForm.css" rel="stylesheet"  />
    <script type="text/javascript" src="/resources/js/loginForm.js"></script>

    <script type="module">
        import 'https://cdn.jsdelivr.net/npm/vanilla-ripplejs@1.0.6';
    </script>
</head>

<body>

<div class="layer1">
    <header>
        <h1>Great to see you again!</h1>
    </header>
    <form id="layer2" action="/loginVerify" method="post">
        <div class="input1">
            <div class="inputArea">
                <p id="_id" class="input_label">Username / email</p>
                <input type="text" id="id" class="input_layout" name="id" onkeyup="check_input_len(this.value, this.id)"
                       onfocusin="focus_in(this.value, this.id)" onfocusout="focus_out(this.id); id_validate();">
                <div id="id_extraInfo" class="extraInfo"></div>
            </div>
        </div>
        <div class="input2">
            <div class="inputArea" onmouseover="show_checkbox()" onmouseout="hide_checkbox()">
                <p id="_pwd" class="input_label">Password</p>
                <input type="password" id="pwd" class="input_layout" name="pwd" onkeyup="check_input_len(this.value, this.id)"
                       onfocusin="focus_in(this.value, this.id); show_checkbox(); pwd_validate_in();"
                       onfocusout="focus_out(this.id); hide_checkbox(); pwd_validate_out();">
                <div id="pwd_extraInfo" class="extraInfo">

                </div>
                <p id="show_check" class="check_input">
                    <label><input type="checkbox" onclick="show_or_hide(event)"> show</label>
                </p>
            </div>
        </div>

        <div>
            <button id="btn" class="submit_btn" type="submit" onmouseover="point_cursor()">Login
                <div class="rippleJS"></div>
            </button>
        </div>
    </form>

    <footer>
        <div class="line_1">
        <span>
          <a href="#">Forgot Password?</a>
        </span>
            <span>
          Don't have an account? <a href="#">Get started</a>
        </span>
        </div>
        <div>
            Looking for the Mac app? <a href="#">Download here</a>
        </div>
    </footer>
</div>

</body>

</html>
