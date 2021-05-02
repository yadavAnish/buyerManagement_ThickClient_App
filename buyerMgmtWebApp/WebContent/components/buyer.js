$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
});



$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
 
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 

 $.ajax( 
 { 
 url : "buyersAPI", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : ("text"), 
 complete : function(response, status) 
 { 
 onItemSaveComplete(response.responseText, status); 
 } 
 }); 
});





$(document).on("click", ".btnUpdate", function(event)
{ 
$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
 $("#name").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#email").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#phone").val($(this).closest("tr").find('td:eq(2)').text()); 
});




$(document).on("click", ".btnRemove", function(event)
{ 
 $.ajax( 
 { 
 url : "buyersAPI", 
 type : "DELETE", 
 data : "id=" + $(this).data("id"), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});




function onItemSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
 $("#hidItemIDSave").val(""); 
 $("#formItem")[0].reset(); 
}







function onItemDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


function validateItemForm() {

	// Name-------------
if ($("#name").val().trim() == "") 
 { 
 return "Insert Buyers Name"; 
 } 
// Email-----------------------
if ($("#email").val().trim() == "") 
 { 
 return "Insert buyers email"; 
 } 
// Phone-------------------------------
if ($("#phone").val().trim() == "") 
 { 
 return "Insert Buyers  Phone."; 
 } 

return true; 
}
