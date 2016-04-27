$(document).ready(function() {
    $(".tabLink").each(function(){
      $(this).click(function(){
        tabeId = $(this).attr('id');
        $(".tabLink").removeClass("activeLink");
        $(this).addClass("activeLink");
        $(".tabcontent").addClass("hide");
        $("#"+tabeId+"-1").removeClass("hide")   
        return false;	  
      });
    });  
  });
$(document).ready(function() {
	webPath= document.location.href;
	webPathSplit=webPath.split("#");
	webPathName= webPathSplit[1]+ "1"; /* Hack for check its blank or not */

	if(webPathName=='undefined1') {
	}else{
		openTab=webPathSplit[1];
        $(".tabLink").removeClass("activeLink");
        $("#"+openTab).addClass("activeLink");
        $(".tabcontent").addClass("hide");
        $("#"+openTab+"-1").removeClass("hide")   
	}

    $(".tabLink").each(function(){
      $(this).click(function(){
        tabeId = $(this).attr('id');
        $(".tabLink").removeClass("activeLink");
        $(this).addClass("activeLink");
        $(".tabcontent").addClass("hide");
        $("#"+tabeId+"-1").removeClass("hide")   
        //return false;	  
      });
    });  
  });
