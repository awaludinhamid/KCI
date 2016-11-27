<%-- 
    Document   : welcomepage
    Created on : Aug 08, 2016, 11:58:08 AM
    Author     : awal
--%>

<%@include file="support/header.jsp" %>
<!DOCTYPE html>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../../css/welcome.css"/>
  </head>
<body>
  <div class="container welcome-message">
    <div class="row">
      <h1>
        Selamat datang di aplikasi KCI
      </h1>
      <br/>
      <br/>
      <hr class="hr-top"/>
      <h3>
        <br/>
        Key Control Indicator (KCI) merupakan tools continuous audit FIFGROUP yang menyediakan analisis atas jaminan kecukupan kontrol dari operasional bisnis yang dijalankan.
        <br/>
        <br/>
      </h3>
      <hr class="hr-down"/>
      <br/>
      <p>
        <a class="btn btn-primary btn-lg" href="../../apps/main/dashboard" style="width: 180px">
          <span class="glyphicon glyphicon-tags"></span>
          &nbsp;Dashboard
        </a>
        <a class="btn btn-success btn-lg" href="../../apps/main/admin">
          <span class="glyphicon glyphicon-tags"></span>
          &nbsp;Admin Module <sup style="color: yellow"><i>New!</i></sup>
        </a>
      </p>
      <p>
        <a class="btn btn-default btn-lg" href="mailto:bagus.candra@fifgroup.astra.co.id">
          <span class="glyphicon glyphicon-pencil"></span>
          &nbsp;Daftar sebagai User
        </a>
      </p>
    </div>
  </div>
</body>
</html>
