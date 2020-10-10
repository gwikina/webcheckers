<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl" />

  <div class="body">

    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl" />

    <#if !currentUser??>
    <form action="lobby" method="POST">
      <label for="currentUser">Name:</label>
      <input type="text" name="currentUser" minlength="1" pattern="[A-Za-z0-9][A-Za-z0-9 ]{0,20}" required title="May only contain alpha-numeric
      characters and spaces up to 20 characters long, may not start wit a space" placeholder="Enter a name..."/>
      <button type="submit">sign in</button>
    </form>
    </#if>




  </div>

</div>
</body>

</html>
