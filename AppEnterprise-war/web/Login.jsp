<html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="static/bootstrap.min.css" type="text/css" />
<script src="static/jquery.min.js"></script>
<script src="static/bootstrap.min.js"></script>
<title>Login Front Controller</title>
</head>
<body class="container">

	<form class="form-horizontal col-md-12"
		action="/AppEnterpriseWeb/FrontControllerServlet?control=Login"
		method="POST">
		<fieldset>
			<!-- Form Name -->
			<legend>Login Front Controller</legend>
			<!-- Text input-->
			<div class="form-group">
				<label class="control-label col-md-4" for="usuario">Nome : </label>
				<div class="col-md-4">
					<input id="usuario" name="usuario" type="text"
						placeholder="USUARIO" required="" class="form-control">
				</div>
			</div>
			<!-- Text input-->
			<div class="form-group">
				<label class="control-label col-md-4" for="senha">Senha: </label>
				<div class="col-md-4">
					<input id="senha" name="senha" type="password" placeholder="SENHA"
						required="required" class="form-control">
				</div>
				<br />
				<c:if test="${msgErro ne null}">
					<span style="color: red;">${msgErro}</span>
				</c:if>
			</div>
			<div class="form-group">
				<input type="submit" value="Login" class="btn btn-info">
			</div>
			<div class="form-group">
				<a href="/AppEnterpriseWeb/cadastraUsuario.html" class="btn btn-info">Cadastrar
					Usuário</a>
			</div>
		</fieldset>
	</form>
</body>
</html>