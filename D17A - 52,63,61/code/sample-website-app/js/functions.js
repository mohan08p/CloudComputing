;(function($, window, document, undefined) {
	var $win = $(window);
	var $doc = $(document);

	$doc.ready(function() {

		// Testimonial Slider
		$('.slider-testimoinals .slides').bxSlider({
			controls: false,
			auto: true,
			pause: 5000
		});


		// Navigation Scroll Animation
		$('.nav a').on('click', function(event) {
			event.preventDefault();
			var href = $(this).attr('href');
			var offsetTop = $(href).offset().top;

			$('html, body').animate({ scrollTop : offsetTop }, {
				queue: false,
				duration: 800
			});
		});


		// Map
		var geocoder;
		var map;
		var latlng;
		var address = $('#map').data('address');

		function initialize() {
			geocoder = new google.maps.Geocoder();
			latlng = new google.maps.LatLng(25.763665, -80.189397);
			var mapOptions = {
				zoom: 17,
				center: latlng,
				scrollwheel: false,
				disableDefaultUI: true
			};

			geocoder.geocode( { 'address': address}, function(results, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					map.setCenter(results[0].geometry.location);
					var marker = new google.maps.Marker({
							map: map,
							position: results[0].geometry.location
					});
					latlng = new google.maps.LatLng(results[0].geometry.location.k, results[0].geometry.location.D);
				}
			});

			map = new google.maps.Map(document.getElementById('map'),
					mapOptions);
		}

		google.maps.event.addDomListener(window, 'load', initialize);

		window.addEventListener('resize', function() {
			map.setCenter(latlng);
		}, false);

		window.addEventListener('orientationchange', function() {
			map.setCenter(latlng);
		}, false);

	});
})(jQuery, window, document);
