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

<div>
  <#if currentUser??>
      <h2>Players Online</h2>
      <#if names??>
          <ul>
          <#list names as user>
          <#if user!=currentUser && !(lobby.getGamePlayers()?seq_contains(user)) && !(lobby.getPlayers()?seq_contains(user))>
          <form action="game" method="Post">
            <li>${user.name}
                <button type="submit">start game with player ${user.name}</button>
                <input type="text" style="display:none" name="opponent" value="${user.name}"/>
            </li>
          </form>
          <#elseif user!=currentUser && lobby.getGamePlayers()?seq_contains(user) && !lobby.getPlayers()?seq_contains(user)>
          <form action="/spectator/game" method="post">
                      <li>${user.name}
                          <button type="submit">spectate game with player ${user.name}</button>
                          <input type="text" style="display:none" name="spectatedPlayer" value="${user.name}"/>
                      </li>
          </form>
          <#elseif user!=currentUser && lobby.getPlayers()?seq_contains(user)>
                (spectating game)
            <#else>
            <li>${user.name}</li>
           </#if>
          </#list>
          <ul>
        <#else>
               There are no players in the lobby
      </#if>
    </#if>


</div>
    <!-- TODO: future content on the Home:
            to start games,
            spectating active games,
            or replay archived games
    -->

  </div>

</div>
</body>

</html>
