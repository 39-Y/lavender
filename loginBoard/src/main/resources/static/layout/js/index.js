
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
		let saveUrl = "/articles/write"		
		let ajaxType="POST"
		let doneUrl = "/articles/"
		main.checkData(data, saveUrl, ajaxType, doneUrl);
	},
	
	update: function(){
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", [])
		let data = {
			title: $('#title').val(),
			content: $('textarea#content').val(),
			articleNO: $('#articleNO').val()
		};
		let updateUrl = "/articles/update"
		let ajaxType = "PUT"; 
		let doneUrl = "/articles/"
		main.checkData(data, updateUrl, ajaxType, doneUrl);
	},
	
	delete: function(){
		let data = $('#deleteNO').val();
		console.log("delete-no: "+data);
		let deleteUrl = "/articles/delete";
		let ajaxType="DELETE";
		let doneUrl = "/mainboard/1"
		main.send(data, deleteUrl, ajaxType, doneUrl);
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
				url: ajaxUrl,
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

