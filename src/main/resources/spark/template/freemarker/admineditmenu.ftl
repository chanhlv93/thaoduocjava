<#import "adminTemplate.ftl" as layout />

<@layout.adminTemplate title="Chỉnh sửa menu">
<div class="col-lg-12">
   <h1 class="page-header">Chỉnh sửa menu</h1>
</div>
<div class="col-md-12">
   <div style="max-width:800px">
      <#if menu.publish?? && menu.publish == 1 >
      <form action="/admin/editmenupath" method="post" class="form-horizontal">
         <div class="form-group">
            <label class="col-sm-3 control-label">Tên menu</label>
            <div class="col-sm-9">
               <input type="text" id="name" name="name" class="form-control" value="${menu.name}">
               <input type="hidden" id="id" name="id" value="${menu.id}">
            </div>
         </div>
         <div class="form-group">
            <label class="col-sm-3 control-label">Số thứ tự</label>
            <div class="col-sm-9">
               <input type="text" name="itemorder" id="itemorder" class="form-control" value="${menu.itemorder}">
            </div>
         </div>
         <div class="form-group">
            <label class="col-sm-3 control-label">Chọn danh mục</label>
            <div class="col-sm-9">
               <select class="form-control" id="groupid" name="groupid">
                  <option value="0">Chọn danh mục</option>
                  <#if categoryList??>
                  <#list categoryList as cate>
                  <option value="${cate.id}">${cate.name}</option>
                  </#list>
                  </#if>
               </select>
            </div>
         </div>
         <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
               <button type="submit" class="btn btn-success">Cập nhật</button>
            </div>
         </div>
      </form>
      <script>
         $(function() {
         	$("#groupid").val('${menu.groupid}');
         });
      </script>
      <#else>
      <form action="/admin/editmenucontent" enctype='multipart/form-data' method="post" class="form-horizontal">
         <div class="form-group">
            <label class="col-sm-2 control-label">Tên menu</label>
            <div class="col-sm-10">
               <input type="text" class="form-control" name="name" id="name" value="${menu.name}">
               <input type="hidden" name="id" id="id" value="${menu.id}">
            </div>
         </div>
         <div class="form-group">
            <label class="col-sm-2 control-label">Số thứ tự</label>
            <div class="col-sm-10">
               <input type="text" name="itemorder" id="itemorder" class="form-control" value="${menu.itemorder}">
            </div>
         </div>
         <div class="form-group">
            <label class="col-sm-2 control-label">Mô tả</label>
            <div class="col-sm-10">
               <textarea rows="4" class="form-control" id="des" name="des">${menu.des}</textarea>
            </div>
         </div>
         <div class="form-group">
            <label class="col-sm-2 control-label">Ảnh</label>
            <div class="col-sm-10">
               <img src="/anh/${menu.image}" height="100px" />
               <input type="hidden" name="currentimage" value="${menu.image}">
               <small>Chọn ảnh khác để thay đổi!</small>
               <div class="cb5"></div>
               <input type="file" name="file">
            </div>
         </div>
         <div class="form-group">
            <label class="col-sm-2 control-label">Nội dung</label>
            <div class="col-sm-10">
               <textarea id="content" name="content" class="form-control">${menu.content}</textarea>
            </div>
         </div>
         <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
               <button type="submit" class="btn btn-success">Cập nhật</button>
            </div>
         </div>
      </form>
      <script>
         tinymce.init({selector: '#content',
         		height: 200,
         		plugins: [
           		'advlist autolink lists link image charmap print preview anchor',
           		'searchreplace visualblocks code fullscreen',
           		'insertdatetime media table contextmenu paste code'
         		],
         		toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
         		content_css: '//www.tinymce.com/css/codepen.min.css'
         });
      </script>
      </#if>
	</div>
</div>
</@layout.adminTemplate>