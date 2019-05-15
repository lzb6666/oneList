layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var matters = '';
	$.ajax({
		url : "http://193.112.44.141:8001/matter",
		type : "get",
		dataType : 'jsonp',
//		dataType:'text', 
		crossDomain: true,
		data:"{}",
//		data:JSON.stringify(""),
		success : function(obj){
			console.log("success");
			console.log(obj);
			matters = obj;
			//执行加载数据的方法
			linksList();
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
                $("#p_test").innerHTML = "there is something wrong!";
                  alert(XMLHttpRequest.status);
                  alert(XMLHttpRequest.readyState);
                  alert(textStatus);
        }
	})

	function linksList(that){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			if(!that){
				currData = matters.concat().splice(curr*nums-nums, nums);
			}else{
				currData = that.concat().splice(curr*nums-nums, nums);
			}
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
					dataHtml += '<tr>'
			    	+'<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
			    	+'<td align="left">'+currData[i].linksName+'</td>'
			    	+'<td><a style="color:#1E9FFF;" target="_blank" href="'+currData[i].linksUrl+'">'+currData[i].linksUrl+'</a></td>'
			    	+'<td>'+currData[i].masterEmail+'</td>'
			    	+'<td>'+currData[i].linksTime+'</td>'
			    	+'<td>'+currData[i].showAddress+'</td>'
			    	+'<td>'
					+  '<a class="layui-btn layui-btn-mini links_edit"><i class="iconfont icon-edit"></i> 编辑</a>'
					+  '<a class="layui-btn layui-btn-danger layui-btn-mini links_del" data-id="'+data[i].linksId+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
			        +'</td>'
			    	+'</tr>';
				}
			}else{
				dataHtml = '<tr><td colspan="7">暂无数据</td></tr>';
			}
		    return dataHtml;
		}

		//分页
		var nums = 13; //每页出现的数据量
		if(that){
			matters = that;
		}
		laypage.render({
			cont : "page",
			pages : Math.ceil(matters.length/nums),
			jump : function(obj){
				$(".links_content").html(renderDate(matters,obj.curr));
				$('.links_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}
})
