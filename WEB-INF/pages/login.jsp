<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/assets/favicon.ico">
    <title>Ape's Console</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/resources/dist/css/bootstrap.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <!-- Custom styles for this template -->
	<link href="${pageContext.request.contextPath}/resources/jumbotron-narrow/jumbotron-narrow.css" rel="stylesheet">
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="${pageContext.request.contextPath}/resources/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="${pageContext.request.contextPath}/resources/assets/js/ie-emulation-modes-warning.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <div class="container">
      <div class="header clearfix"></a>
        <h1 class="text-muted">Ape's Console</h1>
      </div>

      <div class="jumbotron">
        <p class="lead">
		  <form class="form-signin" action="login.html" method="POST">
			<label class="sr-only">User Id</label>
			<input name="userId" type="text" class="form-control" placeholder="User Id" required autofocus>
			<label for="inputPassword" class="sr-only">Password</label></br/>
			<input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
			</br/>
			<select class="form-control" name="group">
				${groupList}
			</select></br/>
			<button class="btn btn-lg btn-primary btn-block btn-success" type="submit">Login</button>
		  </form>
		</p>
      </div>

      <footer class="footer">
        <p>&copy; Ape's Console</p>
      </footer>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="${pageContext.request.contextPath}/resources/assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="${pageContext.request.contextPath}/resources/dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${pageContext.request.contextPath}/resources/assets/js/ie10-viewport-bug-workaround.js"></script>
    <script src="${pageContext.request.contextPath}/resources/assets/offcanvas.js"></script>
  </body>
</html>
