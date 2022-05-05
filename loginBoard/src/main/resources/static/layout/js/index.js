let main = {
	init: function(){
		let _this = this;
		$('#btn-save').on('click', function(){
		_this.save();			
		});
	},
	
	save: function(){
		let data = {
			title: $('#title').val(),
			content: $('#content').val(),
			id: $('#id').val(),
			parentNO: $('#parentNO').val()
		};
		
		$.ajax({
			type: 'POST',
			url: '/articles/write',
			dataType: 'json',
			contentType:'application/json; charset=utf-8',
			data: JSON.stringify(data)
		}).done(function(){
			window.location.href='/articleNO로 보내라';
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}
	
	
	
	
}