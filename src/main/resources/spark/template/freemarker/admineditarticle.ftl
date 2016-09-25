<#import "adminTemplate.ftl" as layout />

<@layout.adminTemplate title="Chỉnh sửa bài viết">
                    <div class="col-lg-12">
                        <h1 class="page-header">Chỉnh sửa bài viết</h1>
                    </div>
	<div style="max-width:800px">
		<form id="addItem" method='post' enctype='multipart/form-data' class="form-horizontal">
			<div class="form-group">
   				<label class="col-sm-2 control-label">Danh mục</label>
    			<div class="col-sm-7">
    				<select class="form-control" name="groupid" id="groupid">
      					<#if categoryList??>
    						<#list categoryList as cate>
    						<option value="${cate.id}">${cate.name}</option>
    						</#list>	
						<#else>
							<option value="0">Chưa có danh mục</option>
						</#if>
      				</select>
      				<input type="hidden" name="id" id="id" value="${editItem.id}">
    			</div>
    			<div class="col-sm-3 nopl">
    				<a href="/admin/category" class="btn btn-sm btn-primary">Thêm danh mục</a>
    			</div>
  			</div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Tên bài viết</label>
    <div class="col-sm-10">
      <input type="text" name="name" id="name" class="form-control" placeholder="" value="${editItem.name}" required>
    </div>
  </div>
  
  <div class="form-group">
    <label class="col-sm-2 control-label">Mô tả</label>
    <div class="col-sm-10">
    	<textarea rows="4" id="des" name="des" class="form-control" required>${editItem.des}</textarea>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Ảnh</label>
    <div class="col-sm-10">
    	<img src="/anh/${editItem.image}" height="100px" />
    	<input type="hidden" name="currentimage" value="${editItem.image}">
    	<small>Chọn ảnh khác để thay đổi!</small>
    	<div class="cb5"></div>
    	<input type="file" name="file">
    </div>
  </div>
  
  <div class="form-group">
    <label class="col-sm-2 control-label">Nội dung</label>
    <div class="col-sm-10">
      <textarea id="content" name="content" class="form-control">${editItem.content}</textarea>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox" checked> Hiển thị
        </label>
      </div>
    </div>
  </div> 
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-success">Cập nhật</button>
      <a href="/admin" class="btn btn-default">Hủy bỏ</a>
    </div>
  </div>
</form>
	</div>
	<script>
		tinymce.init({selector: '#content',
  		height: 300,
  		plugins: [
    		'advlist autolink lists link image charmap print preview anchor',
    		'searchreplace visualblocks code fullscreen',
    		'insertdatetime media table contextmenu paste code'
  		],
  		toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
  		content_css: '//www.tinymce.com/css/codepen.min.css'
		});
		
		$(function() {
			$("#addItem").validate();
			$("#groupid").val('${editItem.groupid}');
		});
</script>
</@layout.adminTemplate>