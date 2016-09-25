<#macro masterTemplate title="Welcome">
    <!DOCTYPE html
            PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
            "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <title>${title} | MiniTwit</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
        <link rel="stylesheet" type="text/css" href="/css/template.css">
        <link rel="stylesheet" type="text/css" href="/css/home.css">
        <link rel="stylesheet" type="text/css" href="/ja_menus/ja_cssmenu/ja.cssmenu.css">
        <script src="ja_menus/ja_cssmenu/ja.cssmenu.js" type="text/javascript"></script>
    </head>
    <body id="bd" class=" wide fs3" style="background-image: url(assets/images/background/bg.jpg);">
    	<div id="ja-wrapper">
         <!-- BEGIN: HEADER -->
         <div id="ja-header" class="clearfix">
            <h1 class="logo">
               <a href="index.html" title="Thế giới thảo dược"><span>Thế giới thảo dược</span></a>
            </h1>
            <!-- BEGIN: MAIN NAVIGATION -->
            <div id="ja-mainnav">
               <ul id="ja-cssmenu" class="clearfix">
                  <li class="active"><a href="/" class="menu-item0 active first-item" id="menu1" title="Trang chủ"><span class="menu-title">Trang chủ</span></a></li>
                  <#if menuList??>
                  	<#list menuList as menu>
						<#if menu.publish == 1>
							<li><a href="${menu.des}" title="${menu.name}"><span class="menu-title">${menu.name}</span></a></li>
						<#else>
							<li><a href="/tnd/${menu.id}" title="${menu.name}"><span class="menu-title">${menu.name}</span></a></li>
						</#if>
                  	<#else>
                                      </#list>
                                       <#else>
                                 </#if>
                  <!--<li ><a href="san-pham-thao-duoc.html" class="menu-item1" id="menu56" title="Sản phẩm thảo dược"><span class="menu-title">Sản phẩm thảo dược</span></a></li>
                  <li ><a href="suc-khoe-lam-dep.html" class="menu-item2" id="menu54" title="Sức khỏe - Làm đẹp"><span class="menu-title">Sức khỏe - Làm đẹp</span></a></li>
                  <li ><a href="cay-thuoc-quy.html" class="menu-item3" id="menu55" title="Cây thuốc quý"><span class="menu-title">Cây thuốc quý</span></a></li>
                  <li ><a href="bai-thuoc-hay.html" class="menu-item4" id="menu65" title="Bài thuốc hay"><span class="menu-title">Bài thuốc hay</span></a></li>
                  <li ><a href="lien-he.html" class="menu-item5" id="menu57" title="Liên hệ"><span class="menu-title">Liên hệ</span></a></li>
                  <li ><a href="sitemap.html" class="menu-item6 last-item" id="menu58" title="Sitemap"><span class="menu-title">Sitemap</span></a></li>-->
               </ul>
            </div>
            <!-- END: MAIN NAVIGATION -->
         </div>
         <!-- END: HEADER -->
		
		<#nested />
		<div id="ja-botnav">
            <ul id="mainlevel-nav">
               <li><a href="dinh-lang.html" class="mainlevel-nav" >Đinh Lăng</a></li>
               <li><a href="tam-that.html" class="mainlevel-nav" >Tam Thất</a></li>
               <li><a href="giao-co-lam.html" class="mainlevel-nav" >Giảo Cổ Lam</a></li>
               <li><a href="kim-ngan.html" class="mainlevel-nav" >Kim Ngân</a></li>
               <li><a href="co-muc.html" class="mainlevel-nav" >Cỏ mực</a></li>
               <li><a href="dam-duong-hoac.html" class="mainlevel-nav" >Dâm Dương Hoắc</a></li>
               <li><a href="nhuc-thung-dung.html" class="mainlevel-nav" >Nhục Thung Dung</a></li>
               <li><a href="xa-den.html" class="mainlevel-nav" >Xạ Đen</a></li>
               <li><a href="tao-meo-sapa.html" class="mainlevel-nav" >Táo Mèo Sapa</a></li>
               <li><a href="cay-ba-dau.html" class="mainlevel-nav" >Cây bả đậu</a></li>
               <li><a href="cay-bo-bo.html" class="mainlevel-nav" >Cây bo bo</a></li>
               <li><a href="cay-bau-dat.html" class="mainlevel-nav" >Cây bầu đất</a></li>
               <li><a href="cay-chan-vit.html" class="mainlevel-nav" >Cây chân vịt</a></li>
               <li><a href="cay-cau-dang.html" class="mainlevel-nav" >Cây câu đắng</a></li>
               <li><a href="cay-co-ngot.html" class="mainlevel-nav" >Cây cỏ ngọt</a></li>
               <li><a href="cay-la-gan.html" class="mainlevel-nav" >Cây Lá Gan</a></li>
               <li><a href="che-dang-cao-bang.html" class="mainlevel-nav" >Chè đắng Cao Bằng</a></li>
               <li><a href="kim-tien-thao.html" class="mainlevel-nav" >Kim Tiền Thảo</a></li>
               <li><a href="que.html" class="mainlevel-nav" >Quế</a></li>
               <li><a href="cay-ba-benh.html" class="mainlevel-nav" >Cây Bá Bệnh</a></li>
               <li><a href="nghe.html" class="mainlevel-nav" >Nghệ</a></li>
               <li><a href="toi.html" class="mainlevel-nav" >Tỏi</a></li>
            </ul>
        </div>
		<div id="ja-footer">
            <div class="advs bannergroup">
               <div class="banneritem">
                  <div style="text-align: center;">Copyright © 2015 <strong><a href="index.html"><font color="#C7D75A">Thế giới thảo dược</font></a> - <a href="index.html"><font color="#C7D75A">Thảo dược</font></a> - <a href="index.html"><font color="#C7D75A">Thảo dược quý</font></a> - <a href="index.html"><font color="#C7D75A">Thảo dược chữa bệnh</font></a></strong></div>
                  <div style="text-align: center;">Chúng tôi xây dựng blog Sức Khỏe, Làm đẹp, Thông tin Y học này với mục đích tập hợp kiến thức để học hỏi, trau dồi kỹ năng và giải trí với Nâng cao sức khỏe, chống lại bệnh tật.</div>
                  <div style="text-align: center;">Các bài viết trên blog này được Chúng tôi sưu tầm từ các báo điện tử nổi tiếng trong nước - Tất cả đều có ghi rõ nguồn.</div>
                  <div style="text-align: center;">Hiện, những thông tin trên này được sử dụng như một nhật ký học tập cá nhân. Chúng tôi không có mục đích phát hành thông tin một cách đại chúng.</div>
                  <div style="text-align: center;">Quý vị tuyệt đối không được áp dụng những hướng dẫn hoặc phương pháp điều trị trong blog này mà chưa có sự hướng dẫn hoặc đồng ý của bác sỹ. Thông tin trong blog chỉ là tham khảo.</div>
                  <div style="text-align: center;">Phát triển bởi <a href="http://www.thietkewebsitedep.com/" target="_blank"><font color="#C7D75A">Thiết Kế Website Đẹp</font></a></div>
               </div>
            </div>
         </div>
         <!-- END: FOOTER -->
       </div>
    </body>
    
    </html>
</#macro>