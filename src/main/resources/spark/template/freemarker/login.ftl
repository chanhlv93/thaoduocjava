<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      </meta>
      <title>Login</title>
      <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
   </head>
   <body>
      <div class="container">
         <div class="row">
            <div class="col-md-4 col-md-offset-4" style="padding-top:200px">
               <div class="panel panel-default">
                  <div class="panel-heading">
                     <h3 class="panel-title">Please sign in</h3>
                  </div>
                  <div class="panel-body">
                     <form action="/login" method="post">
                        <fieldset>
                           <div class="form-group">
                              <input type="text"class="form-control" name="username" size="30" maxlength="50" placeholder="Username">
                           </div>
                           <div class="form-group">
                              <input type="password" class="form-control" placeholder="Password"  name="password" size="30">
                           </div>
                           <input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
                        </fieldset>
                     </form>
                     <#if message??>
                     <div class="success">
                        ${message}
                     </div>
                   </#if>
                   <#if error??>
                     <div class="error">
                        <strong>Error:</strong> ${error}
                     </div>
                   </#if>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </body>
</html>