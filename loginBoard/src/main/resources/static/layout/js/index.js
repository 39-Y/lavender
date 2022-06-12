let articleUrl = "/article/";
let mainboardUrl = "/mainboard/1";
let main = {
	init: function(){
		let _this = this;
		$('#btn-save').on('click', function(){
			_this.save();			
		});
		$('#btn-update').on('click', function(){
			_this.update();			
		});
		$('#btn-delete').on('click', function(){
			_this.delete();
		});
	},
	
	save: function(){
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", [])
		let data = {
			title: $('#title').val(),
			content: $('textarea#content').val(),
			id: $('#id').val(),
			parentNO: $('#parentNO').val()
		};
		main.checkData(data, "write", "POST", articleUrl);
	},
	
	update: function(){
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", [])
		let data = {
			title: $('#title').val(),
			content: $('textarea#content').val(),
			articleNO: $('#articleNO').val()
		};
		main.checkData(data, "update", "PUT", articleUrl);
	},
	
	delete: function(){
		let data = $('#deleteNO').val();
		main.send(data, "delete", "DELETE", mainboardUrl);
	},
	
	checkData: function(data, ajaxUrl, ajaxType, doneUrl){
		let content = data.content;
		let title = data.title;
		let _title = title.replace(/(\s*)/g, "");
		let _content = content.replace(/(\s*)/g, "")
													.replace(/<br>/g,"")
													.replace(/&nbsp;/g,"")
													.replace(/<p><\/p>/g,"");
		if(_title.length == 0 || _content.length == 0){
			if(_title.length == 0){
				alert("제목을 입력해주세요.");
			}
			else{
				alert("본문 내용을 입력해주세요.");
			}
		}
		else{
			main.send(data, ajaxUrl, ajaxType, doneUrl);
		}
	},

	send:function(data, ajaxUrl, ajaxType, doneUrl){
		$.ajax({
				type: ajaxType,
				url: "/articles/"+ajaxUrl,
				contentType:'application/json; charset=utf-8',
				data: JSON.stringify(data),
				dataType: 'text'
			}).done(function(result){
				window.location.href= doneUrl + result;
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
	}	
};

main.init();

