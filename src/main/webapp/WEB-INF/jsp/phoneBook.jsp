<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>

  <!--                                           -->
  <!-- Any title is fine                         -->
  <!--                                           -->
  <title>Wrapper HTML for App</title>

  <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/PhoneBook.css">

  <!--                                            -->
  <!-- This script is required bootstrap stuff.   -->
  <!--                                            -->
  <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/phoneBook/phoneBook.nocache.js"></script>
</head>

<!--                                           -->
<!-- The body can have arbitrary html, or      -->
<!-- you can leave the body empty if you want  -->
<!-- to create a completely dynamic ui         -->
<!--                                           -->
<body>

<h1>Sample Application</h1>

<p>
  This is an example of a host page for the App application.
  You can attach a Web Toolkit module to any HTML page you like,
  making it easy to add bits of AJAX functionality to existing pages
  without starting from scratch.
</p>

<table align="center">
  <tr>
    <td id="slot1"></td>
    <td id="slot2"></td>
  </tr>
</table>
</body>
</html>
