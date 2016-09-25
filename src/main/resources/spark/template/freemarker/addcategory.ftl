<#import "adminTemplate.ftl" as layout />

<@layout.adminTemplate title="Thêm bài viết">
                    <div class="col-lg-12">
                        <h1 class="page-header">Quản lý danh mục</h1>
                    </div>
                
	<div style="max-width:800px">
	<div class="col-md-12">
	<table class="table table-hover">
            	<thead>
            		<tr>
            			<th>Id</th>
            			<th>Tên danh mục</th>
            			<th>Mô tả</th>
            			<th width="90px"></th>
            		</tr>
            	</thead>
            	<tbody>
            	<#if categoryList??>
    <#list categoryList as cate>
    	<tr>
    	<td>${cate.id}</td>
		<td>${cate.name}</td>
        <td>${cate.des}</td>
        <td>
        	<button class="btn btn-sm btn-primary"><i class="fa fa-pencil" aria-hidden="true"></i></button>
            <button class="btn btn-sm btn-danger"><i class="fa fa-trash" aria-hidden="true"></i></button>
        </td>
        </tr>
	<#else>
		
	</#list>
	<#else>
		<tr>
		<td>Ko co</td>
		</tr>
	</#if>
            	</tbody>
            </table>
    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">Thêm danh mục</button>
	</div>
</div>	
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Thêm danh mục</h4>
      </div>
      <div class="modal-body">
        <form action="/admin/addcategory" class="form-horizontal" method="post">
  <div class="form-group">
    <label class="col-sm-3 control-label">Tên danh mục</label>
    <div class="col-sm-9">
      <input type="text" name="name" class="form-control" placeholder="">
    </div>
  </div>
  
  <div class="form-group">
    <label class="col-sm-3 control-label">Mô tả</label>
    <div class="col-sm-9">
    	<textarea name="des" class="form-control"></textarea>
    </div>
  </div>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-success">Thêm danh mục</button>
    </div>
  </div>
	</form>
      </div>
    </div>
  </div>
</div>
</@layout.adminTemplate>