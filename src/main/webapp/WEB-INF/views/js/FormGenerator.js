function FormGenerator(inputs, target, prefix) {
	this.inputs = inputs;
	this.target = target;
	this.prefix = prefix;
	this.title = 'unknown';
};

FormGenerator.prototype.clearAll =  function(){
	this.target.empty();
};

FormGenerator.prototype.generate = function(){
	var id_no = 0;
	this.clearAll();
	var thisForm = this; //save context
	thisForm.makeLegend(thisForm.title);
	jQuery.each(thisForm.inputs, function() {
		var input_type = this["type"];
		var input_name = this["name"];
		var input_id = this["id"];  //not used for now, since dummy data id is null for all.
		var input_properties = this["properties"];
		id_no++;
		var myid = thisForm.prefix + "_" + id_no;
		var lbl = input_name;
		switch(input_type)
		{
			case "CHECKBOX":
			  var checked = input_properties["checked"];
			  thisForm.makeCheckbox(myid, lbl, checked);
			  break;
			case "INTEGER":
			case "SIMPLE":
			  var max = parseInt(input_properties["max"]);
			  var min = parseInt(input_properties["min"]);
			  var value = parseInt(input_properties["value"]);
			  thisForm.makeTextInput(myid,lbl,min,max,value);
			  break;
			case "RANGESLIDER":
			  var max = parseInt(input_properties["max"]);
			  var min = parseInt(input_properties["min"]);
			  var high = parseInt(input_properties["high"]);
			  var low = parseInt(input_properties["low"]);
			  thisForm.makeSlider(myid, lbl, min, max, high, low);
			  break;
			case "STRING":
			  var regex = input_properties["regex"];
			  var value = input_properties["value"];
			  thisForm.makeStringInput(myid,lbl,regex,value);
			  break;
			default:
			  console.log("Unknown:" + input_type);
		}
	});
	thisForm.makeSubmitButton();
};

FormGenerator.prototype.makeLegend = function(title){
	var html = '<legend>' + title + '</legend>';
	this.target.append(html);
};

FormGenerator.prototype.makeSubmitButton = function(){
	var html = '<button id = "tab2_submit" type="submit" class="btn">Submit</button>';
	this.target.append(html);
};

FormGenerator.prototype.makeCheckbox = function(id, lbl, defa){
	var checked = '';
	if(defa){
		checked='checked';
	}
	var html = '<div class="control-group"><label">'+lbl+'</label><label class="checkbox"><input id="'+ id +'" type="checkbox"'+checked+'>'+lbl+'</label><span class="help-block">Check for true; Uncheck for false</span></div>';
	this.target.append(html);
};

FormGenerator.prototype.makeStringInput = function(id,lbl,regex,value){
	var html = '<div class="control-group"><label for="inputSuccess">'+lbl+'</label><div><input type="text" id="'+id+'"><span class="help-block" id="text_help'+id+'"></span></div>';
	this.target.append(html);
	$('#'+id).val(value);
	$('#'+id).blur(function() {
  		var reg = new RegExp('('+regex+')'+'\\b');
  		var curVal = $(this).val();
  		if(reg.test(curVal)){
  			$('#text_help'+id).html('');
  		}else{
  			$('#text_help'+id).html('Only '+regex +' is allowed');
  		}
  	});
};

FormGenerator.prototype.makeSlider = function(id, lbl, minimum, maximum, highest, lowest){
	var html = '<div class="control-group"><label">'+lbl+'</label><div id="'+id+'"></div><span class="help-block" id="slider_range"></span></div>';
	this.target.append(html);
	$('#'+id).slider({
        range: true,
        min: minimum,
        max: maximum,
        values: [lowest, highest],
	    slide: function(event, ui) {
			var values = ui.values;
			for(var i = 0; i < 2; i++){
				$('#tooltip'+i).html(values[i].toString());
			}
	    },
	    create: function(event, ui) {
	    	var values = $('#'+id).slider("option","values");
	    	var tooltips = $(this).find(".tooltip");
	    	if(tooltips.length == 0){ //no tooltip yet
	    		var handlers = $(this).find(".ui-slider-handle");
	    		if(handlers.length == 2){
	    			jQuery.each(handlers,function(idx, hdl){
	    				$(this).append(getToolTip(idx,values[idx]));
					});
	    		}
	    	}
	    }
    });
};

FormGenerator.prototype.makeTextInput = function(id, lbl, min, max, v){
	var html = '<div class="control-group"><label for="inputSuccess">'+lbl+'</label><div><input type="text" id="'+id+'"><span class="help-block" id="text_help'+id+'"></span></div>';
	this.target.append(html);

	$('#'+id).val(v.toString());
	$('#'+id).blur(function() {
		var reg = new RegExp('^'+'\\d'+'+$'); //Integer only
  		var curVal = $(this).val();
  		if(reg.test(curVal)){
  			var value = parseInt($(this).val());
  			if(value < min || value > max){
  				$('#text_help'+id).html('Value Must Between ['+ min + ', '+max+']');
  			}else{
  				$('#text_help'+id).html('');
  			}
  		}
  		else{
  			$('#text_help'+id).html('Only Number is Allowed');
  		}
  	});
};

function getToolTip(id, value){
	var tooltip = '<div class = "mytooltip" id="tooltip'+id+'">'+value+'</div>';
	return tooltip;
}

