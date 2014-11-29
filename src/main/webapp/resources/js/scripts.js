validateSearchFor = function() {
	var isValid = true;
	if($("#searchFor").val().length<3) {
		isValid = false;
		$("<div><p>Search Term's Length Must Be At Least 3 Characters</p></div>").dialog({
	     	closeOnEscape:true,
			resizable:true,
			modal:true,
			width:450,
			height:170,
			title:"Warning",
			position: {
				my:'top',
				at:'center',
				of:$('div.wallpaper')
			},
	        buttons: {       	
	            Close: function () {
	                $(this).dialog("close");
	        	}
	    	}
		});
	}
	if(isValid) { $(".search-form").submit(); }
};

$(function() {
	
	$(document).on('click', '#showOptions', function() { $(".hideOptions").toggleClass("invisible"); });
	$(document).on('click', '#descriptionImage', function() { $(this).parent().parent().siblings(".description").toggleClass("invisible"); });
	$(document).on('click', '.deleteComment', function() { $(this).parent().remove(); });
	$(document).on('click', '.deleteArtist', function() { $(this).parent().remove(); });
	$(document).on('click', '#addComment', function() { $("#hiddenComment").clone().toggleClass("invisible").insertBefore($("#addCommentDiv")); });
	$(document).on('click', '#addArtist', function() { $("#hiddenArtist").clone().toggleClass("invisible").insertBefore($("#addArtistDiv")); });
	$('.flashMessage').delay(3000).fadeOut('slow');
	
	tinymce.init({
		selector: "textarea",
	    plugins: ["autolink link"],
        toolbar: "undo redo | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link",
        entity_encoding: "raw"
	});
		
	$(".dialog").dialog({
     	autoOpen:false,
     	closeOnEscape:true,
		resizable:false,
		modal:true,
		width:450,
		height:170,
		position: {
			my:'top',
			at:'center',
			of:$('div.wallpaper')
		},
        buttons: {
	        "Confirm": function() {
	        	$(this).dialog("close");
	        	window.location = $(this).data('link').href;
	        },	        	
            Cancel: function () {
                $(this).dialog("close");
        	}
    	}
	});
	$(".dialog-confirm").bind("click", function(event) {
		event.preventDefault();
		var link = this;
		var dialogId = $(this).attr("data-open");
		$("#" + dialogId)
    		.data('link', link)
    		.dialog("open");
    	return false;
	});

	$(document).on("focus", ".autoComplete", function() {	
		$(this).autocomplete({
	        source: 'get_artists',
	        minLength: 3
	    });
	});
	
	/*On Selection Of Music Category Hide Unwanted Fields and Clear Values Already Given*/
	$(document).on("change", "select#category", function() {
		var music = ["Music", "Popular Music", "Classical Music", "Greek Music"];
		if(jQuery.inArray($("#category :selected").text(), music)!=-1) {
			$("input#titleEll, input#year, select#subtitles").val("");
			$("input#titleEll, input#year, select#subtitles").parent("div").hide();
		}
		else {
			$("input#titleEll, input#year, select#subtitles").parent("div").show();
		}
	});
});