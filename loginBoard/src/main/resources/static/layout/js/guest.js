let guestMain={
  init:function(){
    let _this =this;
    $('#btn-guest-save').on('click', function(){
      _this.save();
    });
    $('#btn-guest-check').on('click', function(){
      _this.check();
    });
		$('#btn-guest-login').on('click', function(){
      _this.login();
    });
		$('#btn-guest-delete').on('click', function(){
      _this.delete();
		});
		$('#btn-guest-profile').prop("disabled", true).on('click', function(){
      _this.profile();
    });
  },

  save:function(){
		let data={
			id: $('#id').val(),
			pwd: $('#pwd').val()
		};
		let isBtnDisable = document.getElementById('btn-guest-check').disabled;
		if(!guestMain.pwdVerify(data.pwd) || !isBtnDisable)
			{
				if(!isBtnDisable){
					$('.regNot').text('');
					$('.regNotId').text('아이디 중복검사를 해주세요');
				}
				return;
			}
		guestMain.send(data,'/register/save','post','/profile');
  },

	check:function(){ 
		let data ={id: $('#id').val()};
		if(!guestMain.idVerify(data.id))
			{return;}
		$.ajax({
				type: 'post',
				url: "/register/check",
				contentType:'application/json; charset=utf-8',
				data: JSON.stringify(data),
				dataType: 'text'
			}).done(function(result){
					$('.regNotId').text('사용할 수 없는 아이디입니다.');
	        if(result == 'false'){
	     	    $('#btn-guest-check').val("사용할 수 있는 ID입니다").prop("disabled", true);
					  $('#id').prop("readonly", true);
						$('.regNotId').hide();
					}
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
	},
	
	login:function(){
		let data ={
			id: $('#id').val(),
			pwd: $('#pwd').val()
		}
		if(!guestMain.loginVerify(data)){
			return;
		}
		$('.regNot').text('');
		
		$.ajax({
				type: 'post',
				url: "/glogin",
				contentType:'application/json; charset=utf-8',
				data: JSON.stringify(data),
				dataType: 'text'
			}).done(function(result){
				if(result=='false'){
				//if(result=='null'){
					$('.regNotId').text('아이디와 비밀번호가 일치하지 않습니다');
					return;
				}
				window.location.href= '/';
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
	},
	
	delete:function(){
		let result = confirm('탈퇴 하시겠습니까? 모든 기록이 삭제됩니다.');
		if(result){
			$.ajax({
				type: "post",
				url: "/delete",
				contentType:'application/json; charset=utf-8',
				data: JSON.stringify("id"),
				dataType: 'text'
			}).done(function(){
				window.location.href= "/";
			}).fail(function(error){
				alert(JSON.stringify(error)+"/n"+"code: "+request.status+"/n"+request.responseText+"/n");
			});
		}
	},
	
	profile:function(){
		profileForm.method = "post";
		profileForm.action= "/profile";
		profileForm.submit();
	},
	
	send:function(data, ajaxUrl, ajaxType, doneUrl){
		$.ajax({
				type: ajaxType,
				url: ajaxUrl,
				contentType:'application/json; charset=utf-8',
				data: JSON.stringify(data),
				dataType: 'text'
			}).done(function(result){
				window.location.href= doneUrl;
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
	},
	
	idVerify:function(id){
		let noSpace = id.search(/\s/gi)<0 ? true : false;
		let enoughLength = id.length>3? true :false;
		if(!noSpace){
			$('.regNotId').text('아이디에 공백을 제거해주세요');
		}
		if(!enoughLength){
			$('.regNotId').text('아이디를 4자 이상으로 입력해주세요');
		}
		return noSpace&&enoughLength;
	},
	
	pwdVerify:function(pwd){
		let noSpace = guestMain.isPwdNoSpace(pwd);
		let enoughLength = guestMain.isPwdEnoughLength(pwd);
		let sameValue = guestMain.isPwdSameValue(pwd);
		return noSpace&&enoughLength&&sameValue;
	},
	
	loginVerify:function(data){
		let idResult = guestMain.idVerify(data.id);
		let pwdResult = guestMain.isPwdNoSpace(data.pwd)&&guestMain.isPwdEnoughLength(data.pwd);
		return idResult&&pwdResult;
	},
	
	isPwdNoSpace:function(pwd){
		let noSpace = pwd.search(/\s/gi)<0 ? true : false;
		if(!noSpace){
			$('.regNotPwd').text('비밀번호에 공백을 제거해주세요');
		}
		return noSpace;
	},
	
	isPwdEnoughLength:function(pwd){
		let enoughLength = pwd.length>3? true :false;
		if(!enoughLength){
			$('.regNotPwd').text('비밀번호를 4자리 이상 입력해 주세요');
		}
		return enoughLength;
	},
	
	isPwdSameValue:function(pwd){
		let sameValue = pwd == $('#_pwd').val()? true:false;
		if(!sameValue){
			$('.regNotPwd').text('비밀번호가 일치하지 않습니다');
		}
		return sameValue;
	},
	
	validFileType:function(file){
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
		return fileTypes.includes(file.type);
	},
	
	imgPreview:function(input){
		if(input.files && input.files[0]){
			if(!guestMain.validFileType(input.files[0])){
				alert("사용가능한 이미지 파일이 아닙니다!");
				return;
			}
			$("#btn-guest-profile").prop("disabled", false);
			const reader = new FileReader();
			reader.onload = function(img) {
				$('#preview').attr('src', img.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
};
guestMain.init();
