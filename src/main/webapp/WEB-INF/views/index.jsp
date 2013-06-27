<!DOCTYPE html>
<html>
<head>
	<title>Crystal</title>
	<!-- Bootstrap -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<!--Bootstrap-->
	<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="assets/css/bootstrap-responsive.css">
	<link rel="stylesheet" type="text/plain" href="assets/less/responsive.less">
	<link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">

	<!--Jquey UI-->
	<link rel="stylesheet" type="text/css" href="css/custom-theme/jquery-ui-1.10.0.custom.css" />

	<!-- My Own-->
	<link rel="stylesheet" type="text/css" href="css/my.css" >

</head>
<body>
	<div class='container'>
		<div class="span12">
			<section id="wizard">
				<div class="page-header">
					<h1>Crystal</h1>
				</div>
				<div id="rootwizard">
					<div class="navbar">
						<div class="navbar-inner hidden-phone">
							<div class="container">
								<ul>
									<li><a href="#tab1" data-toggle="tab">Select Your Model</a></li>
									<li><a href="#tab2" data-toggle="tab">Provide Input to the Model</a></li>
									<li><a href="#tab3" data-toggle="tab">Run Results</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div id="bar" class="progress progress-striped active">
						<div class="bar"></div>
					</div>
					<div class="tab-content">
						<div class="tab-pane" id="tab1">
							<h2> Select Model </h2>
						</div>
						<div class="tab-pane container-fluid" id="tab2">
							<div class="row-fluid">
								<div class="span4">
									<img src="image.jpg" class="img-rounded" alt="ModelX">
								</div>
								<div class="span8">
									<div class="input_form">
										<form id="SubmitInput" class="form-horizontal">
											<fieldset id="tab2_model_input_form">
												<legend>Provide Your Input to Model</legend>
												<button id = "tab2_submit" type="submit" class="btn">Submit</button>
											</fieldset>
										</form>
									</div>
								</div>
							</div>
						</div>
						<div class="tab-pane" id="tab3">
							<div class="row-fluid">
								<div class="span4">
									<img src="image.jpg" class="img-rounded" alt="ModelX">
								</div>
								<div class="span8">
									<div class="sme_input_form">
										<form id="sme_SubmitInput" class="form-horizontal">
											<fieldset id="tab3_model_input_form">
												<legend>Provide Your Input to Model</legend>
												<button id = "tab3_submit" type="submit" class="btn">Submit</button>
											</fieldset>
										</form>
									</div>
								</div>
							</div>
						</div>
						<hr>
						<div id="bar" class="progress progress-striped active visible-phone">
							<div class="bar"></div>
						</div>
						<ul class="pager wizard">
							<li class="previous first" style="display:none;"><a href="#">First</a></li>
							<li class="previous"><a href="#">Previous</a></li>
							<li class="next last" style="display:none;"><a href="#">Last</a></li>
							<li class="next" id="mynext"><a href="#">Next</a></li>
						</ul>
					</div>
				</div>

			</section>
		</div>
	</div>

	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="assets/js/jquery-ui-1.10.0.custom.min.js"></script>

	<script type="text/javascript" src="js/jquery.bootstrap.wizard.js"></script>
	<script type="text/javascript" src="js/prettify.js"></script>
	<script type="text/javascript" src="js/my.js"></script>
</body>
</html>