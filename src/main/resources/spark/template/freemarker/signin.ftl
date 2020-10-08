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
    <form action="lobby" method="GET">
      <label for="currentUser">Name:</label>
      <input type="text" name="currentUser" minlength="1" pattern="[^ ][A-Za-z 0-9]{1,20}" required title="May only contain alpha-numeric
      characters and spaces up to 20 characters long, may not start wit a space" placeholder="Enter a name..."/>
      <button type="submit">sign in</button>
    </form>
    </#if>

 <#if currentUser??>
    <form action="game" method="GET">
          <button type="submit">start game</button>
        </form>
 </#if>

<div>
  <#if currentUser??>
      <#if names??>
          <ol>
          <#list names as user>
            <li>${user.name}</li>
          </#list>
          <ol>
        <#else>
               no players, sorry :(
      </#if>
    </#if>
</div>




  </div>

</div>
</body>

</html>
