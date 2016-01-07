<!DOCTYPE html>
<html>
    <head>
        <title>Posições por Usuário - <%=request.getParameter("login")%></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="static/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" href="static/bootstrap.min.css">
        <script src="static/bootstrap.min.js"></script>
        <script src="static/ol.js"></script>
        <link rel="stylesheet" href="static/ol.css" type="text/css">
        <script src="static/pontos.js"></script>
        <style>
            #MeuMapa{
                width:100%;
                height:800px;
            }
        </style>

    </head>
    <body onload="init()">
        <input type="hidden" value="<%=request.getParameter("login")%>" id="loginUsuario"/>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <div id="MeuMapa" class="map"><div id="popup"></div></div>
                </div>
            </div>
        </div>    
    </body>
</html>