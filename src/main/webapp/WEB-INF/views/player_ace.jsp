<%--
  Created by IntelliJ IDEA.
  User: Lyubomyr
  Date: 02.02.2016
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body onLoad="showPlayer('${chUrl}');">
<script type="text/javascript" src="http://static.torrentstream.org/jsapi/js/lib/ts/core.js"></script>
<script type="text/javascript" src="http://static.torrentstream.org/jsapi/js/lib/jquery/jquery-1.7.min.js"></script>
<script type="text/javascript" src="http://static.torrentstream.org/jsapi/js/lib/jquery/jquery.mousewheel.js"></script>
<script type="text/javascript" src="http://static.torrentstream.org/jsapi/js/lib/jquery/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="http://static.torrentstream.org/jsapi/js/lib/jquery/jquery-ui-1.8.9.custom.min.js"></script>
<script type="text/javascript" src="http://static.torrentstream.org/jsapi/js/lib/jquery/jquery.jscrollpane.js"></script>
<script type="text/javascript" src="http://static.torrentstream.org/jsapi/js/lib/jquery/jquery.scrollText.js"></script>
<script type="text/javascript" src="http://static.torrentstream.org/jsapi/js/lib/cufon/cufon.js"></script>
<script type="text/javascript" src="http://static.torrentstream.org/jsapi/js/lib/cufon/a_LCDNova_400.font.js"></script>
<script type="text/javascript" src="http://static.torrentstream.org/jsapi/js/lib/ts/player.js"></script>
<script type="text/javascript" src="http://static.torrentstream.org/jsapi/js/lib/ts/controls.js"></script>

<link type="text/css" rel="stylesheet" href="http://static.torrentstream.org/jsapi/css/ts-buttons.css" />
<link type="text/css" rel="stylesheet" href="http://static.torrentstream.org/jsapi/css/ts-controls-white.css" />

<script type="text/javascript">
  var controls = new TorrentStream.Controls(null, {
    style: "ts-white-screen"
  });
  function showPlayer(contentId) {
    controls.showPlayer(function() {
      try {
        var player = new TorrentStream.Player(this.getPluginContainer(), {
          debug: true,
          onLoad: function() {
            this.registerEventHandler(controls);
            controls.attachPlayer(this);
            this.loadPlayer(contentId);
          }
        });
      }
      catch(e) {
        controls.onSystemMessage(e);
      }
    });
  }
</script>
<label id="chaUrl" name="chnUrl" value="${chUrl}">${chUrl}</label>

</body>
</html>
