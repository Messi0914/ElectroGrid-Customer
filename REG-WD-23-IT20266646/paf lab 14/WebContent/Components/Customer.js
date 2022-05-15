//Hide the alerts on page load
$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	
	$("#alertError").hide();
});
// SAVE
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();
	 
	// Form validation
	var status = validateItemForm();
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
	 }	
	// If valid
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
		{
			url : "CustomerAPI",
			type : type,
			data : $("#formCustomer").serialize(),
			dataType : "text",
			complete : function(response, status)
			{
				onItemSaveComplete(response.responseText, status);
			}
		});
});




// UPDATE
$(document).on("click", ".btnUpdate", function(event)
{		
	
	 $("#hidItemIDSave").val($(this).data("cid"));
	 $("#customerName").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#customerAddress").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#customerNIC").val($(this).closest("tr").find('td:eq(2)').text());
	 $("#customerEmail").val($(this).closest("tr").find('td:eq(3)').text());
	 $("#customerPNO").val($(this).closest("tr").find('td:eq(4)').text());
});




//DELETE
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
		{
			url : "CustomerAPI",
			type : "DELETE",
			data : "cID=" + $(this).data("cid"),
			dataType : "text",
			complete : function(response, status)
			{
				onItemDeleteComplete(response.responseText, status);
			}
		});
});





// CLIENT-MODEL
function validateItemForm()
{
	// Name
	if ($("#customerName").val().trim() == "")
	 {
		return "Insert your Name.";
	 }
	// Address
	if ($("#customerAddress").val().trim() == "")
	 {
		return "Insert your Address.";
	 }
	//Nic
	if ($("#customerNIC").val().trim() == "")
	 {
		return "Insert your Nic.";
	 }
	
	//Email
	if ($("#customerEmail").val().trim() == "")
	 {
		return "Insert your Email Address.";
	 }
	
	//Phone
	if ($("#customerPNO").val().trim() == "")
	 {
		return "Insert your Phone Number.";
	 }
	
	
	return true;
}

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
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	
	else if (status == "error")
	{
		$("#alertError").text("Error while Inserting Customer.");
		$("#alertError").show();
	} 
	else
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
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Unable to delete due to an error.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}