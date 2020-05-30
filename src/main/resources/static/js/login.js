
$(function() {
	jQuery.validator.addMethod("domainValidation", function(value, element) {
		return this.optional(element) || /^[\w\W]+@dhl.com$/.test(value);
	}, "Please specify the correct email");

	$("#signupForm").validate({
		rules: {
			eMail: {
				required: true,
				domainValidation: true
			},
			empID: {
				required: true
			},
			phoneNo: {
				required: true,
				number: true,
				minlength: 10,
				maxlength: 11
			}
		},
		messages: {
			eMail: "Please enter organization email address",
			empID: "Please Provide Employee ID",
			phoneNo: "PhoneNo length should be either 10 or 11"
		},
		tooltip_options: {
			eMail: { trigger: 'focus' }
		},
	});

});

// $('#loginform').submit(function(event) {
// 	event.preventDefault();
	
// 	var $form = $(this),
// 		url = $form.attr('action');

// 	$.ajax({
// 			type: "POST",
// 			url: url,
// 			// contentType: "application/json",
// 			data:$form.serialize(),
// 			success: function(result) {
// 				alert(result);
// 				//$.cookie('csvquery_token', result)
// 				$.ajaxSetup({
// 					headers:{'Authorization': 'Bearer ' + result}
// 					})
// 				$.get("/bookingQueue")
// 				//window.location = window.location.replace('/bookingQueue')	
// 			},
// 			errror: function(jqXHR, textStatus, errorThrown) {
// 				console.log('Could not get posts, server response: ' + textStatus + ': ' + errorThrown);
// 			}
// 		}); // ajax close
// });


