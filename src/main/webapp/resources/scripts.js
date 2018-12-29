jQuery(function() {

	/*
	 * Trigger Navbar's Drop-Downs On Hover
	 */
	$('.dropdown').hover(function() {
		if($('.navbar-collapse.in').length===0) { // Check If The navbar Is Collapsed
			$(this).addClass('open');
        }
	},
	function() {
		if($('.navbar-collapse.in').length===0) {
			$(this).removeClass('open');
		}
	});

	/*
	 * Validate Search Form's Text Input
	 */
	$("#searchForm").on('submit', function(event) {
		if($("#searchFor").val().length<3) {
			event.preventDefault();
			bootbox.alert({
				title:'<strong>Search Tip</strong>',
				message: "You Have To Search For A Term With At Least 3 Characters."  
			});
		}
	});

	/*
	 * Checkbox Triggering On Label Click
	 */
	$('div.checkbox label').click(function(event) {
		if(event.target.type !== 'checkbox') {
			$(':checkbox', this).trigger('click');
	    }
	});

	/*
	 * Confirm Dialog For Anchors With Class .confirm-dialog and A Custom 'dialog' Attribute Containing The Confirm Question
	 */
	$(document).on("click", ".confirm-dialog", function(event) {
		var _this = $(this);
		var _accept = 'Delete';
		if(_this.attr('accept')!==null && _this.attr('accept')!=='') {
            _accept = _this.attr('accept');
        }
		event.preventDefault();
		bootbox.confirm({
			title:'<strong>Confirm</strong>',
			message: _this.attr('dialog'),
			buttons: {
				'cancel': {
					label:'Cancel',
					className:'btn-default'
				},
				'confirm': {
					label:_accept,
					className:'btn-danger'
				}
			},
			callback: function(result) {
				if(result) {
					location.href=_this.attr('href');
				}
			}
		});
	});

	/*
	 * Items' Form Add + Remove Input Fields Functionality
	 */
	$(document).on('click', '.description.toggle', function() { $(this).parent().parent().find("div.description").toggleClass("hidden"); });
	$(document).on('click', '.deleteComment', function() { $(this).parent().remove(); });
	$(document).on('click', '.deleteArtist', function() { $(this).parent().remove(); });
	$(document).on('click', '#addComment', function() { $("#hiddenComment").clone().toggleClass("hidden").insertBefore($("#addCommentDiv")); });
	$(document).on('click', '#addArtist', function() { $("#hiddenArtist").clone().toggleClass("hidden").insertBefore($("#addArtistDiv")); });

	/*
	 * TinyMCE Editor For Item + Artist Description
	 */
	tinymce.init({
		selector: "textarea",
	    plugins: ["autolink link"],
        toolbar: "undo redo | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link",
        entity_encoding: "raw"
	});

	/*
	 * Auto-Complete For Item Artists
	 */
	$(document).on("focus", ".autoComplete", function() {
		$(this).autocomplete({
	        source: '/myList/item/getArtists',
	        delay: 300,
	        minLength: 3
	    });
	});

	/*
	 * (Reset and) Hide Not Needed Item Form Fields
	 */
    var _musicCategories = [ "Popular Music", "Classical Music", "Greek Music" ];
	function hideNotNeededFields(category) {
		$("input#titleEll, input#year, select#subtitles, input#place, input#discs").each(function() {
			$(this).parent("div.form-group").show(); // Show All Fields In Order To Handle The Case Of Changing Selection...
		});
		// ...and Then Hide Whatever Should Be Hidden
		if(jQuery.inArray(category, _musicCategories)!==-1) {
			// Music Items Have Only One Title (The Original)
            $("label[for='titleEng']").text("Title");
            $("input#titleEng").attr("placeholder", "Title");
            $("input#titleEll, input#year, select#subtitles").each(function() {
            	if($(this).is('input:text')) {
            		$(this).val("");
				}
            	$(this).parent("div.form-group").hide();
			});
        } else if(category == "DVD Films") {
            $("input#place").val("");
			$("input#place").parent("div.form-group").hide();
		} else if(category == "DivX Films") {
			$("input#discs").val("1"); // The Default Value
            $("input#discs").parent("div.form-group").hide();
        }
	}
	// Add The Event Handlers For hideNotNeededFields()
	$(document).ready(function() {
        if($("form#item").length > 0 && $("#category :selected").text() != "Select Category") { // On Editing An Item
            hideNotNeededFields($("#category :selected").text());
        }
	});
    $(document).on("change", "select#category", function() { // On Category Change
		hideNotNeededFields($("#category :selected").text());
    });
});