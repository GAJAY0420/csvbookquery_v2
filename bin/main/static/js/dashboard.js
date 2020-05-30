$(function() {
	var today = new Date().toString()
	$("#fromDatePicker").datepicker({
		autoclose : true,
		format : 'dd-mm-yyyy',
		endDate : '+1d',
		todayHighlight : true,
	});
	$('#fromDatePicker').datepicker('setDate', today);

	$('#toDatePicker').datepicker({
		autoclose : true,
		format : 'dd-mm-yyyy',
		endDate : '+1d',
		todayHighlight : true,
	}).on('changeDate', function() {
		$('#fromDatePicker').datepicker('setEndDate', $(this).val());
	});
	$('#toDatePicker').datepicker('setDate',today);
	
	$('#fromDatePicker').on("changeDate", function() {
		$('#toDatePicker').datepicker('setStartDate', $(this).val());
	});

	$('.cntLst').select2({
		dropdownAutoWidth : true,
		width : '100%'
	// width: 'auto'
	});

	$("input[name='bookingNo']").change(function() {
		var bookingNo = $('#bookingNo').val();

		if (!(bookingNo.length <= 6 || bookingNo.length == 15)) {
			alert("bookingNo length must be either 6 or 15");
			$('#bookingNo').focus();
			return false;
		}

		if (bookingNo.length == 6 && !$.isNumeric(bookingNo)) {
			alert("bookingNo must be numeric if length is 6 ")
			$('#bookingNo').focus();
			return false;
		}
	}); // bookingNo Validation

	var parentTable = $('#parentTable').DataTable({
		searching : false,
		// scrollCollapse: true,
		paging : false,
		defaultContent : " ",
	// "order": [[1, 'asc']]

	});

	var childTable = $('#childResultGrid').DataTable({
		searching : false,
		paging : false,
		/* scrollY : '50vh', */
		scrollCollapse : true,
	});

	$('#parentTable tbody').on('click', 'tr', function() {
		childTable.clear().draw();
		var data = parentTable.row(this).data();
		$.ajax({
			type : "POST",
			url : "eventDetails",
			data : {
				globalRefNo : data[1],
				country : $('#countryList option:selected').val()
			},
			dataType : 'json',
			success : function(result) {
				if (!$.trim(result)) {
					alert("Events Data Not Found For : " + data[1]);
				}
				let rowNo = 1
				$.each(result, function(index, jsonArr) {
					var valArr = [];
					valArr.push(rowNo)
					$.each(jsonArr, function(key, value) {
						valArr.push(value);
					});
					childTable.row.add(valArr).draw();
					rowNo++
				}) // FOR LOOP
			},
			error : function(errorObj) {
				alert("Events Data Not Found For : " + data[1])
			}

		});// ajax Close
	}); // function close

	$("#btn-reset").click(function() {
		document.location.reload(true);
	});

	$('#bookingForm').submit(function(event) {
		event.preventDefault();
		$("#btn-login").prop("disabled", true);
		var pGridTab = $('#parentTable').DataTable();
		var cGridTab = $('#childResultGrid').DataTable();
		cGridTab.clear().draw();
		pGridTab.clear().draw();
		$.ajax({
			type : "POST",
			url : "bookingQueue",
			data : $('#bookingForm').serialize(),
			dataType : 'json',
			success : function(result) {
				if (!$.trim(result)) {
					alert("Bookings Data Not Found !!");
				}
				let row = 1
				$.each(result, function(index, jsonArr) {
					var valArr = []
					valArr.push(row)
					$.each(jsonArr, function(key, value) {
						valArr.push(value);
					});
					pGridTab.row.add(valArr).draw();
					row++
				}) // INNER FOR LOOP
				$("#btn-login").prop("disabled", false);
			}, // success close
			error : function(errorObj) {
				alert("Bookings Data Not Found !!", errorObj)
//				document.location.reload(true);
				$("#btn-login").prop("disabled", false);
			}
		}); // ajax close
	});
}); // document close

