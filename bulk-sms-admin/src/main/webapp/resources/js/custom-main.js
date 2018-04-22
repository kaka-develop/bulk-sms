function setActive(page,eId) {
	var title = document.getElementsByTagName("title")[0].innerHTML;
	if (title == page) {
		notActive();
		var contact = $(eId);
		contact.find('a').addClass("active");
		contact.addClass("active");
		contact.find('ul').css({
			display : "block"
		});
	}
}

function notActive() {
	$('#nav-accordion li').each(function() {
		$(this).find('a').removeClass("active");
	});
}

