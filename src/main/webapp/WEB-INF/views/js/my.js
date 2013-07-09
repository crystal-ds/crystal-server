/*!
 * Front end for Crystal A2C2
 * version 1.0
 * Requirement: jQuery v1.3.2 or later
 * Author: Arthur Liu（yikliu@umail.iu.edu)
 */
var ajaxServer =  'http://localhost:8080';
var rowCount = 4; // Tab1: each row has max of this many elements

var allModels;

var scoreModel;

var selectedModelId = -1;

var batch_id = -1;

var eme_inputs;
var sme_inputs;

var eme_form;
var sme_form;

var eme_submitter;
var sme_submitter;

var sme_results;

$(document).ready(function() {
	$('#Tab2SubmitInput').submit(function() {
		var submiting_form = $('#Tab2SubmitInput');
		eme_submitter = new FormSubmitter(submiting_form,
										  eme_inputs,
										  'eme',
										  selectedModelId);

		eme_submitter.formulatePostData();
		eme_submitter.post(emeRunSuccess);
	});

	$('#Tab3SubmitInput').submit(function() {
		var submiting_form = $('#Tab3SubmitInput');
		sme_submitter = new FormSubmitter(submiting_form,
										  sme_inputs,
										  'sme',
										  batch_id);
		sme_submitter.formulatePostData();
		sme_submitter.post(smeRunSuccess);
	});

	$('#rootwizard').bootstrapWizard({
		onNext: function(tab, navigation, index) {
				if(index == 1){
					if(selectedModelId == -1){
						alert('Please select a model to proceed');
						return false;
					}
				}
				if(index == 2){
					if(batch_id == -1){
						alert('EME model run not yet finished');
						return false;
					}
				}
		},

		onTabShow: function(tab, navigation, index) {
				var $total = navigation.find('li').length;
				var $current = index+1;
				var $percent = ($current/$total) * 100;
				$('#rootwizard').find('.bar').css({width:$percent+'%'});

				if($current == 2){
					if(selectedModelId != -1){
						var aModel=allModels[selectedModelId.toString()];

						eme_inputs=aModel["inputs"]; //store all inputs data

						var eme_target = $('#tab2_model_input_form');
						var eme_prefix = 'eme_' + selectedModelId.toString();

						eme_form = new FormGenerator(eme_inputs,eme_target, eme_prefix);
						eme_form.title = "Provide Your Input to EME Model";
						eme_form.generate();
					}
				}

				if($current == 3){
					if(null != scoreModel){
						var sme_target = $('#tab3_model_input_form');
						var sme_prefix = 'sme_' + batch_id.toString();
						sme_inputs = scoreModel["inputs"];
						sme_form = new FormGenerator(sme_inputs,sme_target, sme_prefix);
						sme_form.title = "Provide Your Input to SME Model";
						sme_form.generate();
					}
				}
			}
		}

	);

	$.ajax({
		type: 'GET',
		url: ajaxServer+'/crystal-a2c2/eme/models/',
		dataType: 'json',
		crossDomain:true,
		error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown);
    	},
		success: getAllModelSuccess
	});

});


function emeRunSuccess(data){
	batch_id = data["batchJob"];
	$.ajax({
		type: "GET",
		url: ajaxServer + '/crystal-a2c2/sme/models',
		contentType: 'application/json',
		success: function(data){
			//scoreModel = data[batch_id];
			scoreModel = data['1'];
			$('#rootwizard').bootstrapWizard('next');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert(textStatus);
		}
	});
}

function smeRunSuccess(data){
	sme_results = data;
	$('#rootwizard').bootstrapWizard('next');
}

function getAllModelSuccess(data){
	allModels = data;
	var count = Object.keys(data).length;
	var row = count <= rowCount ? 1 : count/rowCount;
	var str;
	var num;
	for (var i = 0; i < row ; i++){
		$('#tab1').append(
			$('<div>', {
				class: 'row-fluid',
				id:'row' + i
			})
		);
		for(var j = 0; j < rowCount && count !=0 ; j++){
			num = i*4 + j + 1;
			var title=allModels[num.toString()].name;
			str = '<div class="span' + 12/rowCount + '" id="model' + num + '">' + title + '<img src="image.jpg" class="img-rounded" alt="Model' + num + '"></div>';
			jQuery(str).appendTo('#row'+i);
			count--;
		}
		jQuery("<hr>").appendTo('#tab1');	//add a separate line after each row
	}

	$('[id^="model"]').click(function (){
		$('[id^="model"]').removeClass('seleted_model');
		$(this).addClass('seleted_model');
		selectedModelId = parseInt($(this).attr('id').substring(5));
	});
}


