<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Danh muc">
<div id="ja-containerwrap-fl">
   <div id="ja-container" class="clearfix">
      <div id="ja-mainbody" class="clearfix">
         <!-- BEGIN: CONTENT -->
         <div id="ja-content">
            <div id="ja-content-top">
               <div id="ja-content-bot" class="clearfix">
                  <div id="ja-current-content" class="clearfix">
                     <h1 class="componentheading">${cate.name}</h1>
                     <table class="blog" cellpadding="0" cellspacing="0">
                        <tr>
                           <td valign="top">
                              <table width="100%"  cellpadding="0" cellspacing="0">
                                 <tr>
                                    <td valign="top" width="100%" class="article_column">
                                       <#if itemList??>
                                       <#list itemList as cate>
                                       <div class="contentpaneopen">
                                          <h2 class="contentheading">
                                             <a href="/san-pham/${cate.id}" class="contentpagetitle">${cate.name}</a>
                                          </h2>
                                          <div class="article-content">
                                             <a href="/san-pham/${cate.id}"><img width="150px" src="/anh/${cate.image}" alt="" class="imgleading" /></a>
                                             <div class="plg_imagesized">
                                                ${cate.content}
                                             </div>
                                          </div>
                                       </div>
                                       <span class="article_separator">&nbsp;</span>
                                       <#else>
                                       </#list>
                                       <#else>
                                 <tr>
                                    <td>Ko co</td>
                                 </tr>
                                 </#if>
                                 </td>
                                 </tr>
                              </table>
                           </td>
                        </tr>
                        <tr>
                           <td valign="top" align="center">
                           </td>
                        </tr>
                        <tr>
                           <td valign="top" align="center">
                           </td>
                        </tr>
                     </table>
                  </div>
                  <div class="moduletable">
                     <style type="text/css">
                        .wraptocenter {
                        display: table-cell;
                        text-align: center;
                        vertical-align: middle;
                        width:145px;
                        height:145px;
                        border:1px solid #ccc;
                        }
                        .wraptocenter * {
                        vertical-align: middle;
                        }
                     </style>
                  </div>
               </div>
            </div>
         </div>
         <!-- END: CONTENT -->
      </div>
      <!-- BEGIN: RIGHT COLUMN -->
      <div id="ja-col2">
         <div class="ja-innerpad">
            <div class="module">
               <div>
                  <div>
                     <div>
                        <h3>Thảo dược quý</h3>
                        <p><a target="_blank" href="http://www.thaoduocrung.com/" rel="nofollow"><img alt="Thao duoc rung quy hiem" src="images/banners/banner-thaoduocrung.jpg" height="165" width="230" /></a></p>
                     </div>
                  </div>
               </div>
            </div>
            <div class="module">
               <div>
                  <div>
                     <div>
                        <h3>Cây thuốc quý</h3>
                        <div class="ja-sidenews-list clearfix">
                           <div class="ja-slidenews-item">
                              <a class="ja-title" href="cay-thuoc-quy/407-dong-trung-ha-thao-bi-khai-thac-nhieu-se-co-nguy-co-bi-tiet-chung.html">Đông trùng hạ thảo bị khai thác nhiều sẻ có nguy cơ bị tiệt chủng</a>
                              <img src="images/resized/images/stories/suc_khoe/ccthuoc1_45_40.jpg" alt="Đông trùng hạ thảo bị khai thác nhiều sẻ có nguy cơ bị tiệt chủng" title="Đông trùng hạ thảo bị khai thác nhiều sẻ có nguy cơ bị tiệt chủng" />		  		  			
                              Hoạt động khai thác quá mức có thể khiến đông trùng hạ thảo, loài nấm mà nhiều...		            
                           </div>
                           <div class="ja-slidenews-item">
                              <a class="ja-title" href="cay-thuoc-quy/363-treo-deo-loi-suoi-di-tim-thao-duoc-quy.html">Trèo đèo lội suối đi tìm thảo dược quý</a>
                              <img src="images/resized/images/stories/suc_khoe/atinct3_45_40.jpg" alt="Trèo đèo lội suối đi tìm thảo dược quý" title="Trèo đèo lội suối đi tìm thảo dược quý" />		  		  			
                              Bất chấp những cơn mưa đầu mùa tuôn xối xả, nhóm người vẫn cố sức vượt con...
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <div class="module">
               <div>
                  <div>
                     <div>
                        <h3>Bài thuốc hay</h3>
                        <div class="ja-sidenews-list clearfix">
                           <div class="ja-slidenews-item">
                              <a class="ja-title" href="bai-thuoc-hay/295-bai-thuoc-kiem-che-su-boc-hoa-cua-phu-nu-tuoi-mang-kinh.html">Bài thuốc kiềm chế sự  bốc hỏa của phụ nữ tuổi mãn kinh</a>
                              <img src="images/resized/images/stories/bai_thuoc/bt17_45_40.jpg" alt="Bài thuốc kiềm chế sự  bốc hỏa của phụ nữ tuổi mãn kinh" title="Bài thuốc kiềm chế sự  bốc hỏa của phụ nữ tuổi mãn kinh" />		  		  			
                              Thời kỳ tiền mãn kinh cơn bốc hoả thường xuyên xảy ra kèm theo những triệu chứng...		            
                           </div>
                           <div class="ja-slidenews-item">
                              <a class="ja-title" href="bai-thuoc-hay/294-tac-dung-chua-tri-cua-cay-tam-gui.html">Tác dụng chữa trị của cây Tầm Gửi</a>
                              <img src="images/resized/images/stories/bai_thuoc/bt16_45_40.jpg" alt="Tác dụng chữa trị của cây Tầm Gửi" title="Tác dụng chữa trị của cây Tầm Gửi" />		  		  			
                              Theo thông tin trên mạng thì có khoảng hơn 1.300 loài tầm gửi. Hầu hết các loài tầm...		            
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <div class="module">
               <div>
                  <div>
                     <div>
                        <h3>Thống kê truy cập</h3>
                        Hiện có&nbsp;19 khách&nbsp;Trực tuyến					
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <br />
      <!-- END: RIGHT COLUMN -->
   </div>
</div>
</@layout.masterTemplate>