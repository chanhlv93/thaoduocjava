 $(document).ready(function() {   
     function setHeight() {
         windowHeight = $(window).innerHeight();
         divHeight = $("#page-wrapper").height();
         if (divHeight < windowHeight) {
         	$('#page-wrapper').css('min-height', windowHeight - 50);
         }
     };   
     setHeight();
     $(window).resize(function() {
         setHeight();   
     }); 
 });