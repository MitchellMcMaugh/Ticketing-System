/* global screenReaderText */
/**
 * Theme functions file.
 *
 * Contains handlers for navigation and widget area.
 */

( function( $ ) {

	// hoverintent setup
	function addHovering() {
		$(this).addClass("hovering");
	};
	function removeHovering() {
		$(this).removeClass("hovering");
	};
	var dropDownConfig = {
		interval: 50,
		sensitivity: 4,
		over: addHovering,
		timeout: 0,
		out: removeHovering
	};
	var coreNavLi = $(".head .primary-menu > li");
	coreNavLi.hoverIntent(dropDownConfig);	
	
	// toggle mobile menu
	$(".head .toggle").click(function(e) {
		e.preventDefault();
		$(".head .navigation").slideToggle();
		$(this).toggleClass("active");
	});

	// sticky post
	$(".news-item .sticky-post").parent().parent(".news-item").addClass('sticky-news-item');

	// hero cycle
	$(".promo-container").cycle({
		slides: "> div.promo",
		swipe: true,
		speed: 1000,
		timeout: 10000,
		prev: ".prev",
    	next: ".next"
	});

	// gallery
	$( '.swipebox' ).swipebox();

	// live search
	$('#liveSearchBox').keyup(function () {
		var searchTerm = $(this).val().toLowerCase();

		if (searchTerm === '') {
			$('.users .person').removeClass('hidden');
		}
		else {
			$('.users .person:not([data-value*="' + searchTerm + '"])').addClass('hidden');
			$('.users .person[data-value*="' + searchTerm + '"]').removeClass('hidden');
		}
	});

	// filters
	$(".filters .city").click(function(e) {
		e.preventDefault();
		var filterTerm = $(this).html().toLowerCase();
		if (filterTerm === '') {
			$('.users .person').removeClass('city-hide');
		}
		else {
			$('.users .person:not([data-city*="' + filterTerm + '"])').addClass('city-hide');
			$('.users .person[data-city*="' + filterTerm + '"]').removeClass('city-hide');
		}
	});
	$(".filters .all").click(function(e) {
		e.preventDefault();
		$('.users .person').removeClass('city-hide');
	});
	$(".filters a").click(function(e) {
		e.preventDefault();
		$(".filters a").removeClass('active');
		$(this).addClass('active');
	});

} )( jQuery );    