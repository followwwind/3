// The root URL for the RESTful services
var rootURL = "http://localhost:8080/Tea/rest/teas";

var currentTea;

var isAdd = true;

var search =function() {
	var searchKey = $("#name").val();
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
};

var newTea=function () {
	$('#btnDelete').hide();
	currentTea = {};
	renderDetails(currentTea); // Display empty form
};

var findAll=function() {
	console.log('findAll');
	$.ajax({type: 'GET',url: rootURL,dataType: "json", success: renderList
	});
};

var findByName= function(searchKey) {
	console.log('findByName: ' + searchKey);
	$.ajax({
		type: 'GET',
		url: rootURL + '/search/' + searchKey,
		dataType: "json",
		success: renderList 
	});
};

var findById= function(id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: ' + data.name);
			currentTea = data;
			renderDetails(currentTea);
		}
	});
};

var add = function(){
	isAdd = true;
	$("#addForm")[0].reset();
	$("#add").modal("show");
}

var save = function(){
	if(isAdd){
		addTea();
	}else{
		updateTea();
	}
}

var addTea = function () {
	console.log('addTea');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Tea created successfully');
			$('#btnDelete').show();
			$('#teaId').val(data.id);
                        findAll();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addTea error: ' + textStatus);
		}
	});
};

var updateTea= function () {
	console.log('updateTea');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/',
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Tea updated successfully');
            findAll();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateTea error: ' + textStatus);
		}
	});
};

var deleteTea=function() {
	console.log('deleteTea');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/',
		success: function(data, textStatus, jqXHR){
			alert('Tea deleted successfully');
            findAll();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteTea error');
		}
	});
};

var renderList= function(list) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	//var list = data == null ? [] : (data instanceof Array ? data : [data]);
   // var list=data.tea;
	$('#teaList li').remove();
	$.each(list, function(index, tea) {
		$('#teaList').append('<li><a href="#" id="' + tea.id + '">'+tea.name+'</a></li>');
	});
};

var renderDetails=function(tea) {
	$('#teaId').val(tea.id);
	$('#name').val(tea.name);
	$('#grapes').val(tea.grapes);
	$('#country').val(tea.country);
	$('#region').val(tea.region);
	$('#year').val(tea.year);
	$('#pic').attr('src', 'pics/' + tea.picture);
	$('#description').val(tea.description);
};

// Helper function to serialize all the form fields into a JSON string
var formToJSON=function () {
	var teaId = $('#teaId').val();
	return JSON.stringify({
		"id": teaId == "" ? null : teaId, 
		"name": $('#name').val(), 
		"grapes": $('#grapes').val(),
		"country": $('#country').val(),
		"region": $('#region').val(),
		"year": $('#year').val(),
		"picture": currentTea.picture,
		"description": $('#description').val()
		});
};

//When the DOM is ready.
$(document).ready(function(){
	// Nothing to delete in initial application state
	$('#btnDelete').hide();

	// Register listeners
	$('#btnSearch').click(function() {
		search($('#searchKey').val());
		return false;
	});

	// Trigger search when pressing 'Return' on search key input field
	$('#searchKey').keypress(function(e){
		if(e.which == 13) {
			search($('#searchKey').val());
			e.preventDefault();
			return false;
	    }
	});

	$('#btnAdd').click(function() {
		newTea();
		return false;
	});

	$('#btnSave').click(function() {
		if ($('#teaId').val() == '')
			addTea();
		else
			updateTea();
		return false;
	});

	$('#btnDelete').click(function() {
		deleteTea();
		return false;
	});

	//$('#teaList a').on("click",function() {
	//	findById($(this).data('identity'));
	//});
	
	$(document).on("click", '#teaList a', function(){findById(this.id);});

	// Replace broken images with generic tea bottle
	//$("img").error(function(){
	 // $(this).attr("src", "pics/generic.jpg");

	//});
	//reset form
	$('#teaId').val("");
	$('#name').val("");
	$('#grapes').val("");
	$('#country').val("");
	$('#region').val("");
	$('#year').val("");
//	$('#pic').attr('src', "");
//	$('#description').val("");
	findAll();
});

