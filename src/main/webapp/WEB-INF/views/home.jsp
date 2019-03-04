<%@ page contentType="text/html;charset=euc-kr" %>
<head>
    <title>HOME</title>
    <link type="text/css" href="/resources/css/loginForm.css" rel="stylesheet"  />
    <script type="text/javascript" src="/resources/js/loginForm.js"></script>

    <script type="module">
        import 'https://cdn.jsdelivr.net/npm/vanilla-ripplejs@1.0.6';
    </script>
</head>

<body>
<div class="layer1">
    <header>
        <h1>Good Afternoon!</h1>
    </header>
    <form id="layer2" action="/login" method="get">
        <div class="input1">
            <div class="inputArea">
            </div>
        </div>
        <div class="input2">

        </div>

        <div>
            <button id="btn" class="submit_btn" type="submit" onmouseover="point_cursor()">iwantLogin
                <div class="rippleJS"></div>
            </button>
        </div>
    </form>

</div>

</body>

</html>