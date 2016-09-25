<#macro adminTemplate title="Danh sach bai viet">
<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="description" content="">
      <meta name="author" content="">
      <title>Admin</title>
      <!-- Viewport mobile tag for sensible mobile support -->
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <!-- Bootstrap Core CSS -->
      <link href="/css/bootstrap.min.css" rel="stylesheet">
      <!-- Custom CSS -->
      <link href="/css/sb-admin.css" rel="stylesheet">
      <!-- Custom Fonts -->
      <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
      <!-- jQuery -->
      <script src="/js/jquery-1.9.1.min.js"></script>
      <script src="/js/tinymce/tinymce.min.js"></script>
      <script src="/js/jquery.validate.min.js"></script>
   </head>
   <body>
      <div id="wrapper">
         <!-- Navigation -->
         <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
               <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
               <span class="sr-only">Toggle navigation</span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               </button>
               <a class="navbar-brand" href="index.html">SB Admin</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
               	<#if user??>		    		
		    	<li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${user.username} <b class="caret"></b></a>
                  <ul class="dropdown-menu">                     
                     <li>
                        <a href="/logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                     </li>
                  </ul>
               	</li>
		  		<#else>
		  		</#if>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
               <ul class="nav navbar-nav side-nav">
                  <li <#if activeMenu??><#if activeMenu == "dashboard">class="active"</#if></#if>>
                     <a href="/admin"><i class="fa fa-fw fa-dashboard"></i> Danh sách bài viết</a>
                  </li>
                  <li <#if activeMenu??><#if activeMenu == "article">class="active"</#if></#if>>
                     <a href="/admin/addarticle"><i class="fa fa-fw fa-plus"></i> Bài viết</a>
                  </li>
                  <li <#if activeMenu??><#if activeMenu == "menu">class="active"</#if></#if>>
                     <a href="/admin/addmenu"><i class="fa fa-fw fa-bar-chart-o"></i> Menu</a>
                  </li>
                  <li <#if activeMenu??><#if activeMenu == "highlightarticle">class="active"</#if></#if>>
                     <a href="#"><i class="fa fa-fw fa-table"></i> Bài viết nổi bật</a>
                  </li>
                  <li <#if activeMenu??><#if activeMenu == "tag">class="active"</#if></#if>>
                     <a href="#"><i class="fa fa-fw fa-table"></i> Tag</a>
                  </li>
                  <li <#if activeMenu??><#if activeMenu == "footerinfo">class="active"</#if></#if>>
                     <a href="#"><i class="fa fa-fw fa-table"></i> Thông tin cuối trang</a>
                  </li>
               </ul>
            </div>
            <!-- /.navbar-collapse -->
         </nav>
         <div id="page-wrapper">
         	<#nested />
         	
         </div>
         <!-- /#page-wrapper -->
      </div>
      <!-- /#wrapper -->
      
      <!-- Bootstrap Core JavaScript -->
      <script src="/js/page.js"></script>
      <script src="/js/bootstrap.min.js"></script>
      <!--SCRIPTS END-->
   </body>
</html>
</#macro>