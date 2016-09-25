<#import "adminTemplate.ftl" as layout />

<@layout.adminTemplate title="Danh sách bài viết">
                    <div class="col-lg-12">
                        <h1 class="page-header">Danh sách bài viết</h1>
                    </div>
             <div class="col-md-12"> 
            <table class="table table-hover">
            	<thead>
            		<tr>
            			<th>Ảnh</th>
            			<th>Danh mục</th>
            			<th width="300px">Tên bài viết</th>
            			<th>Mô tả</th>
            			<th  width="100px">Quản lý</th>
            		</tr>
            	</thead>
            	<tbody>
            		
            		<#if itemList??>
    					<#list itemList as item>
    					<tr>
    					<td>
    						<img src="/anh/${item.image}" height="75px" />
    					</td>
    					<td>${item.params}</td>
            			<td>${item.name}</td>
            			<td>${item.des}</td>
            			<td>
            				<a href="/admin/editarticle/${item.id}" title="Chỉnh sửa" class="btn btn-sm btn-primary"><i class="fa fa-pencil"></i></a>
            				<a href="/admin/deletearticle/${item.id}" title="Xóa" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></a>
            			</td>
            			</tr>
            			</#list>
					<#else>
						
					</#if>
            	</tbody>
            </table>
     </div>
</@layout.adminTemplate>