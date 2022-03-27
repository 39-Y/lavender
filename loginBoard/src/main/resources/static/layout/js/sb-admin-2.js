(function($) {
  "use strict"; // Start of use strict
  // Toggle the side navigation
  $("#sidebarToggle, #sidebarToggleTop").on('click', function(e) {
    $("body").toggleClass("sidebar-toggled");
    $(".sidebar").toggleClass("toggled");
    if ($(".sidebar").hasClass("toggled")) {
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Close any open menu accordions when window is resized below 768px
  $(window).resize(function() {
    if ($(window).width() < 768) {
      $('.sidebar .collapse').collapse('hide');
    };
    
    // Toggle the side navigation when window is resized below 480px
    if ($(window).width() < 480 && !$(".sidebar").hasClass("toggled")) {
      $("body").addClass("sidebar-toggled");
      $(".sidebar").addClass("toggled");
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
  $('body.fixed-nav .sidebar').on('mousewheel DOMMouseScroll wheel', function(e) {
    if ($(window).width() > 768) {
      var e0 = e.originalEvent,
        delta = e0.wheelDelta || -e0.detail;
      this.scrollTop += (delta < 0 ? 1 : -1) * 30;
      e.preventDefault();
    }
  });

  // Scroll to top button appear
  $(document).on('scroll', function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('.scroll-to-top').fadeIn();
    } else {
      $('.scroll-to-top').fadeOut();
    }
  });

  // Smooth scrolling using jQuery easing
  $(document).on('click', 'a.scroll-to-top', function(e) {
    var $anchor = $(this);
    $('html, body').stop().animate({
      scrollTop: ($($anchor.attr('href')).offset().top)
    }, 1000, 'easeInOutExpo');
    e.preventDefault();
  });

})(jQuery); // End of use strict

//login Service
function isLoginBlank() {
				let loginForm = document.loginForm;
				let userId = loginForm.userId.value;
				let userPw = loginForm.userPw.value;
				if(userId == "" || userId.length == 0){
					document.getElementsByClassName("notInputPw")[0].style.display = "none";
					document.getElementsByClassName("notInputId")[0].style.display = "block";
				}
				else if(userPw == "" || userPw.length == 0){
					document.getElementsByClassName("notInputId")[0].style.display = "none";
					document.getElementsByClassName("notInputPw")[0].style.display = "block";
				}
				else{
					loginForm.method = "post";
					loginForm.action= "/lavender/nidlogin";
					loginForm.submit();
				}
			}
			
			function isRegisterOk() {
				let registerForm = document.registerForm;
				let space = /\s/gi;
				let id = registerForm.id.value.replace(space,'');
				let pw = registerForm.pw.value;
				let name = registerForm.name.value.replace(space,'');
				let email = registerForm.email.value.replace(space,'');
				let pwCheck= document.getElementById("pwCheck").value;
				let disabledBtn = document.getElementById('btnDuplicate').disabled;
				
				if(!disabledBtn){
					document.getElementsByClassName("regNotCheckedId")[0].style.display = "block";
				}
				else if(name == "" || name.length == 0){
					document.getElementsByClassName("regNotCheckedId")[0].style.display = "none";
					document.getElementsByClassName("regNotInputPw")[0].style.display = "none";
					document.getElementsByClassName("regNotInputName")[0].style.display = "block";
					document.getElementsByClassName("regNotInputEmail")[0].style.display = "none";
				}
				else if(email == "" || email.length == 0){
					document.getElementsByClassName("regNotInputPw")[0].style.display = "none";
					document.getElementsByClassName("regNotInputName")[0].style.display = "none";
					document.getElementsByClassName("regNotInputEmail")[0].style.display = "block";
				}
				else if(pw == "" || pw.length == 0){
					document.getElementsByClassName("regNotInputPw")[0].style.display = "block";
					document.getElementsByClassName("regNotInputName")[0].style.display = "none";
					document.getElementsByClassName("regNotInputEmail")[0].style.display = "none";
				}
				else if(pw.length<6){
					document.getElementsByClassName("regNotInputPw")[0].style.display = "none";
					document.getElementsByClassName("regNotEnoughPw")[0].style.display = "block";
				}
				else if(pw != pwCheck){
					document.getElementsByClassName("regNotmatchPw")[0].style.display = "block";
					document.getElementsByClassName("regNotEnoughPw")[0].style.display = "none";
				}

				else{
					registerForm.method = "post";
					registerForm.action= "/lavender/register";
					registerForm.submit();
				}
			}
			
			function isOverlapped(){
				let space = /\s/gi;
				let _id = $("#_id").val().replace(space,'');
				console.log(_id);
			    if(_id==''){
			   		document.getElementsByClassName("regNotInputId")[0].style.display = "block";
						document.getElementsByClassName("regNotCheckedId")[0].style.display = "none";
			   	 return;
			    }
			    $.ajax({
			       type:"post",
			       async:true,  
			       url:"/lavender/register/check",
			       dataType:"text",
			       data: {id:_id},
			    }).done(function (result){
								console.log("---result: " + result);
			          if(result =='useable'){
			       	   $('#btnDuplicate').val("사용할 수 있는 ID입니다");
			       	   $('#btnDuplicate').prop("disabled", true);
								 $('#_id').prop("readonly", true);
								 $('#message').hide();
			          }else{
			       	   $('#message').show();
			          }
			});  //end ajax	 
		}
		
		function isRightPw(){
			let modForm = document.modForm;
			let _id = modForm.id.value;
			let pwCheck = modForm.pwCheck.value;
			$.ajax({
				type:"post",
			  async:true,  
			  url:"/lavender/modmember/check",
			  dataType:"text",
			  data: {id:_id, pw:pwCheck},
			}).done(function(result){
				if(result == 'true'){
					$('#modForm').show();
					$('#pwCheck').prop("readonly", true);
				}
			});
		}
		
		function modMember(){
			let modForm = document.modForm;
			let space = /\s/gi;
			let pw = modForm.pw.value.replace(space,'');
			let _pw = modForm._pw.value.replace(space,'');
			let name = modForm.name.value.replace(space,'');
			let email = modForm.email.value.replace(space,'');
			if((pw.length>0)&&(pw.length<6)){
				$('#notMinPw').show();
			}
			else if(pw!=_pw){
				$('#notMatchPw').show();
				$('#notMinPw').hide();
			}
			else if((pw=="")||(pw==_pw)){
					modForm.name.value = name;
					modForm.email.value = email;
					modForm.method = "post";
					modForm.action= "/lavender/modmember";
					modForm.submit();
					}
			
		}
			let isImgFile = false;
			function imgPreview(input){
				if(input.files && input.files[0]){
					if(validFileType(input.files[0])){
						const reader = new FileReader();
						reader.onload = function(img) {
							$('#preview').attr('src', img.target.result);
						}
						reader.readAsDataURL(input.files[0]);
						isImgFile =validFileType(input.files[0]);
					}
				}
			}
			
			function setProfile(){
				if(!isImgFile){
					$('.regNotInputId').show();
					}
				else{
					profileForm.method = "post";
					profileForm.action= "/lavender/profile";
					profileForm.submit();
				}
			}
			const fileTypes = [
			  "image/apng",
			  "image/bmp",
			  "image/gif",
			  "image/jpeg",
			  "image/pjpeg",
			  "image/png",
			  "image/svg+xml",
			  "image/tiff",
			  "image/webp",
			  "image/x-icon"
			];

			function validFileType(file) {
			  return fileTypes.includes(file.type);
			}
			
			
			