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
		<span style="font-size: 15px; color: #959a94;">Welcome</span> : <span style="font-size: 15px; color: #00cc33;">${user.name}</span>
      </div>
	  <ul id="tabs" class="nav nav-pills">
		${user.userLinks}
	  </ul>
	  <div class="panel" id="tablehall" style="display:none;">
          <table class="table" style="font-size: 25px;">
            <tbody>
              <tr>
                <td>Light 1</td>
                <td><input type="button" id="hall-light1" class="btn btn-lg btn-warning" value=""></td>
              </tr>
              <tr>
                <td>Light 2</td>
                <td><input type="button" id="hall-light2" class="btn btn-lg btn-warning" value=""></td>
              </tr>
              <tr>
                <td>AC</td>
                <td><input type="button" id="hall-ac" class="btn btn-lg btn-warning" value=""></td>
              </tr>
              <tr>
                <td>Fan</td>
                <td><input type="button" id="hall-fan" class="btn btn-lg btn-warning" value=""></td>
              </tr>
            </tbody>
          </table>
	  </div>
	  <div class="panel" id="tablemstrm" style="display:none;">
          <table class="table" style="font-size: 25px;">
            <tbody>
              <tr>
                <td>Light 1</td>
                <td><input type="button" id="mstrm-light1" class="btn btn-lg btn-warning" value=""></td>
              </tr>
              <tr>
                <td>AC</td>
                <td><input type="button" id="mstrm-ac" class="btn btn-lg btn-error" value=""></td>
              </tr>
              <tr>
                <td>Speaker</td>
                <td><input type="button" id="mstrm-speaker" class="btn btn-lg btn-success" value=""></td>
              </tr>
            </tbody>
          </table>
	  </div>
	  <div class="panel" id="tablegstrm" style="display:none;">
          <table class="table" style="font-size: 25px;">
            <tbody>
              <tr>
                <td>Light 1</td>
                <td><input type="button" id="gstrm-light1" class="btn btn-lg btn-warning" value=""></td>
              </tr>
              <tr>
                <td>Fan</td>
                <td><input type="button" id="gstrm-fan" class="btn btn-lg btn-warning" value=""></td>
              </tr>
            </tbody>
          </table>
	  </div>
	  <h4><a href="login.html"><span class="label label-danger">Log Out</span></h4>

	  <footer class="footer">
		<p>&copy; Ape's Console | Home Automation</p>		 
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
<script>
	function activate(tabId){
		$( "#tabs li" ).each(function( i ) {
			if(this.id != tabId){
				$('#table' + this.id).hide();
				$('#' + this.id).removeClass('active');
			}else{
				$('#table' + this.id).show();
				$('#'+ this.id).addClass('active');
			}
		});
	}
	
	var getStatus = function(ajaxHandler){
		jQuery.ajax({
		  url: "ajax.html" + "?deviceId=" + encodeURIComponent(ajaxHandler.deviceId) + "&action=" + encodeURIComponent(ajaxHandler.action),
		  success: function (response) {
			  ajaxHandler.handler(response);
	      },
		  error: function(e){
			ajaxHandler.handler('3');
		  }
		});
	}
	
	var decorate = function(element, status){
		switch(status){
		case '1':
			$(element).removeClass('btn-danger');
			$(element).removeClass('btn-warning');
			$(element).addClass('btn-success');			
			$(element).val('OFF');
			return;
		case '2':
			$(element).removeClass('btn-success');
			$(element).removeClass('btn-warning');
			$(element).addClass('btn-danger');
			$(element).val('ON');
			return;
		case '3':
			$(element).removeClass('btn-success');
			$(element).removeClass('btn-danger');
			$(element).addClass('btn-warning');
			$(element).val('NOT SURE');
			return;
		}
	}
	
	var addListener = function(id){
		$('#' + id).click(function(e){
			getStatus({
				deviceId : id,
				action: 'click',
				handler: function(status){
					decorate($('#' + id), status);
				}
			});
		});
	}
	
	var hallDeviceList = ['hall-light1', 'hall-light2', 'hall-ac', 'hall-fan'];
	var mstrmDeviceList = ['mstrm-light1', 'mstrm-ac', 'mstrm-speaker'];
	var gstrmDeviceList = ['gstrm-light1', 'gstrm-fan'];

	$.each(hallDeviceList, function( index, value ){
		addListener(value);
		getStatus({
			deviceId : value,
			action: 'fetch',
			handler: function(status){
				decorate($('#' + value), status);
			}
		});
	});

	$.each(mstrmDeviceList, function( index, value ){
		addListener(value);
		getStatus({
			deviceId : value,
			action: 'fetch',
			handler: function(status){
				decorate($('#' + value), status);
			}
		});
	});

	$.each(gstrmDeviceList, function( index, value ){
		addListener(value);
		getStatus({
			deviceId : value,
			action: 'fetch',
			handler: function(status){
				decorate($('#' + value), status);
			}
		});
	});
	
	
	var first = true;
	$( "#tabs li" ).each(function( i ) {
		if(first){
			activate(this.id);
			first = false;
		}
	});
</script>