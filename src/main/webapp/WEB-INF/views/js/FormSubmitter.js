function FormSubmitter(invoker, inputs, prefix, model_id){
	this.invoker = invoker;
	this.inputs = inputs;
	this.prefix = prefix;
	this.model_id = model_id;
	this.post_data = {};
};

FormSubmitter.prototype.formulatePostData = function(){
	var id_no = 0;
	this.post_data = {};
	var thisSubmitter = this;
	jQuery.each(thisSubmitter.inputs,function(){
		var thisValue;
		var input_type = this["type"];
		id_no++;
		var model_id = thisSubmitter.model_id.toString();
		var myid = thisSubmitter.prefix + '_' + model_id + '_' + id_no;
		if(document.getElementById(myid.toString())){
			var values = null;
			switch(input_type)
			{
				case "RANGESLIDER":
				  values = $('#'+myid).slider("option","values");
				  this["properties"]["high"] = values[1];
				  this["properties"]["low"] = values[0];
				  break;
				case "CHECKBOX":
				  values =  $('#'+myid).is(':checked');
				  this["properties"]["value"] = values;
				  this["properties"]["checked"] = values;
				  break;
				case "INTEGER":
				case "SIMPLE":
				case "STRING":
				  values = $('#'+myid).val();
				  this["properties"]["value"] = values;
				  break;
				default:
				  console.log("Unknown:" + input_type);
			}
			thisSubmitter.post_data[this["name"]] = this;
		}else{
			alert("Couldn't find control: " + myid.toString());
		}
	});
};

FormSubmitter.prototype.post = function(callback){
	if(this.post_data.length != 0){
		$.ajax({
			type: "POST",
			url: ajaxServer + '/crystal-a2c2/' + this.prefix + '/models/' + this.model_id + '/run',
			//data : '[{"InputNode1" : {"name" : "InputNode1","properties" : {"checked" : "true","value" : "true"},"id" : null,"type" : "CHECKBOX"},"InputNode2" : {"name" : "InputNode2","properties" : {"min" : "1","max" : "5","value" : "3"},"id" : null,"type" : "SIMPLE"},"InputNode3" : {"name" : "InputNode3","properties" : {"min" : "0", "max" : "10","high" : "7","low" : "5"}, "id" : null,"type" : "RANGESLIDER"}}]',
    		data : '[' + JSON.stringify(this.post_data) + ']',
    		contentType : 'application/json',
    		success: callback,
    		error : function(jqXHR, textStatus, errorThrown){
    			alert(errorThrown);
    		},
			dataType: 'json'
		});
	}
};

