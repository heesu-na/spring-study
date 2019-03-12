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
        <h1>Good Afternoon!
            <%
                String id = (String) session.getAttribute("id");
                if(id != null) {
            %>
        </h1>
        <div>
            <p>
                <span>ID : </span>
                ${sessionScope.id}
            </p>
            <p>
                <span>Email : </span>
                ${sessionScope.email}
            </p>
        </div> <%}%>
    </header>
    <div class="input1">
        <div class="inputArea"></div>
    </div>
    <div class="input2">
        <% if(id != null) { %>
            <form id="layer2" action="/logout" method="get">
                <div>
                    <button id="btn" class="submit_btn" type="submit" onmouseover="point_cursor()">iwantLogout
                        <div class="rippleJS"></div>
                    </button>
                </div>
            </form>
        <% } else { %>
            <form id="layer2" action="/login" method="get">
                <div>
                    <button id="btn" class="submit_btn" type="submit" onmouseover="point_cursor()">iwantLogin
                        <div class="rippleJS"></div>
                    </button>
                </div>
            </form>
        <% } %>
    </div>
</div>
</body>

</html>