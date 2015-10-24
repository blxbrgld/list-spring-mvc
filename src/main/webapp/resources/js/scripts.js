jQuery(function() {
	/*
	 * Trigger Navbar's Drop-Downs On Hover
	 */
	$('.dropdown').hover(function() {
		if($('.navbar-collapse.in').length==0) { //Check If The navbar Is Collapsed
			$(this).addClass('open');
        }
	},
	function() {
		if($('.navbar-collapse.in').length==0) {
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
		if(_this.attr('accept')!=null && _this.attr('accept')!='') _accept = _this.attr('accept');
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
	 * Hide Unwanted Form Fields For Music Items
	 */
	var _musicCategories = ["Music", "Popular Music", "Classical Music", "Greek Music"];
	$(document).ready(function() { //On Editing An Existing Item
		if(jQuery.inArray($("#category :selected").text(), _musicCategories)!=-1) {
			$("input#titleEll, input#year, select#subtitles").attr("disabled", true);
		}
	})
	$(document).on("change", "select#category", function() { //On Selection Of A Category
		if(jQuery.inArray($("#category :selected").text(), _musicCategories)!=-1) {
			$("input#titleEll, input#year, select#subtitles").val("");
			$("input#titleEll, input#year, select#subtitles").attr("disabled", true);
		}
		else {
			$("input#titleEll, input#year, select#subtitles").attr("disabled", false);
		}
	});
});