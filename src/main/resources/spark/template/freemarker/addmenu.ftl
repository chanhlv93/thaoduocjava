<#import "adminTemplate.ftl" as layout />

<@layout.adminTemplate title="Menu">
<div class="col-lg-12">
   <h1 class="page-header">Quản lý menu</h1>
</div>
<div style="max-width:1000px">
   <div class="col-md-12">
      <table class="table table-hover">
         <thead>
            <tr>
               <th>Tên menu</th>
               <th>Thể loại</th>
               <th>Đường dẫn</th>
               <th>Số thứ tự</th>
               <th width="90px"></th>
            </tr>
         </thead>
         <tbody>
            <#if menuList??>
            	<#list menuList as item>
            		<tr>
               <td>${item.name}</td>
               <td>
               	<#if item.publish?? && item.publish == 1 >Danh mục<#else>Trang nội dung</#if>
               </td>
               <td><#if item.publish?? && item.publish == 1 >${item.des}<#else>/tnd/${item.id}</#if></td>
               <td>${item.itemorder}</td>
               <td>
                  <a href="/admin/editmenu/${item.id}" class="btn btn-sm btn-primary"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                  <a onclick="confirmDelete('${item.name}')" href="/admin/deletemenu/${item.id}" class="btn btn-sm btn-danger"><i class="fa fa-trash" aria-hidden="true"></i></a>
               </td>
            </tr>
            	<#else>
				</#list>
				<#else>
			</#if>
         </tbody>
      </table>
      <button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">Thêm menu</button>
   </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
   <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel">Thêm menu</h4>
         </div>
         <div class="modal-body" style="min-height:300px">
            <div class="form-horizontal">
               <div class="form-group">
                  <label class="col-sm-2 control-label">Đường dẫn đến</label>
                  <div class="col-sm-10">
                     <select class="form-control" id="sltType">
                        <option value="1">Chọn đường dẫn</option>
                        <option value="1">Danh mục</option>
                        <option value="2">Trang nội dung</option>
                     </select>
                  </div>
               </div>
               <div class="cb10"></div>
               <div id="addcate" style="display:none">
                  <form action="/admin/addmenupath" method="post">
                  	 <div class="form-group">
                  	 	<label class="col-sm-2 control-label">Tên menu</label>
                  	 	<div class="col-sm-10">
                     		<input type="text" id="name" name="name" class="form-control" placeholder="">
                 		</div>
               		 </div>
               		 <div class="form-group">
                  	 	<label class="col-sm-2 control-label">Số thứ tự</label>
                  	 	<div class="col-sm-10">
                     		<input type="text" name="itemorder" id="itemorder" class="form-control">
                 		</div>
               		 </div>
                     <div class="form-group">
                        <label class="col-sm-2 control-label">Chọn danh mục</label>
                        <div class="col-sm-10">
                           <select class="form-control" id="groupid" name="groupid">
                              <option value="1">Chọn danh mục</option>
                              <#if categoryList??>
                              <#list categoryList as cate>
                              <option value="${cate.id}">${cate.name}</option>
                              </#list>
                              </#if>
                           </select>
                        </div>
                     </div>
                     <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                           <button type="submit" class="btn btn-success">Thêm menu</button>
                        </div>
                     </div>
                  </form>
               </div>
               <div id="addcontent" style="display:none">
                  <form action="/admin/addmenucontent" enctype='multipart/form-data' method="post">
                     <div class="form-group">
                  	 	<label class="col-sm-2 control-label">Tên menu</label>
                  	 	<div class="col-sm-10">
                     		<input type="text" class="form-control" name="name" id="name">
                 		</div>
               		 </div>
               		 <div class="form-group">
                  	 	<label class="col-sm-2 control-label">Số thứ tự</label>
                  	 	<div class="col-sm-10">
                     		<input type="text" name="itemorder" id="itemorder" class="form-control">
                 		</div>
               		 </div>
                     <div class="form-group">
                        <label class="col-sm-2 control-label">Mô tả</label>
                        <div class="col-sm-10">
                           <textarea rows="4" class="form-control" id="des" name="des"></textarea>
                        </div>
                     </div>
                     <div class="form-group">
                        <label class="col-sm-2 control-label">Ảnh</label>
                        <div class="col-sm-10">
                           <input type="file" name="file">
                        </div>
                     </div>
                     <div class="form-group">
                        <label class="col-sm-2 control-label">Nội dung</label>
                        <div class="col-sm-10">
                           <textarea id="content" name="content" class="form-control"></textarea>
                        </div>
                     </div>
                     <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                           <button type="submit" class="btn btn-success">Thêm menu</button>
                        </div>
                     </div>
                  </form>
               </div>
               <script>
               	  function confirmDelete(name) {
    				var r = confirm("Bạn có chắc muốn xóa " + name + "?");
    				if (r == true) {
        				return true;
    				} else {
        				event.preventDefault();
        				return false;
    				}
				  }
               
                  $("#sltType").on("change", function() {
                  	var choice = this.value;
                  	if (choice == "1") {
                  		$("#addcate").removeAttr("style");
                  		$("#addcontent").css("display","none");
                  	} else {
                  		$("#addcontent").removeAttr("style");
                  		$("#addcate").css("display","none");
                  	}
                  });
                  
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
               </script>
            </div>
         </div>
      </div>
   </div>
</div>
</@layout.adminTemplate>