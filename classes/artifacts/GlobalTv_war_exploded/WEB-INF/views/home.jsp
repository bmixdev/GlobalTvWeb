<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <style>
        .grpbtn {
            width: 200px
        }
    </style>
</head>
<body onLoad="OnStart()">
<script th:inline="javascript">

    function OnStart() {
    }

    function providerChange() {
        sel = document.getElementById('provList');
        selectedProvider = sel.value;

        var messg = $('#provList').val();
        document.getElementById('provName').innerHTML = messg;
    }
    function channelOpen()
    {
        chUrl = document.getElementById('chaUrl').firstChild.nodeValue;
        console.log(chUrl);
        //$("#myAceModal").modal();
    }
</script>
<div class="row">
    <div class="col-sm-3" align="center" style="border-style: groove; padding:20px; width:300px">
        <h1>Playlist</h1>

        <form method="put" action="/selected_playlist">
            <table border="1">
                <select id="provList" name="plstnum" onChange="providerChange()">
                    <c:forEach var="plst" items="${playList}" varStatus="status">
                        <option name="action" value="${status.index+1}"
                                <c:choose>
                                    <c:when test="${status.index+1==selectedPlst}">
                                        selected="selected"
                                    </c:when>
                                </c:choose>
                                >${plst.name}</option>
                    </c:forEach>
                </select>
                <br><br>
                <label for="provName" id="provName" name="provName"></label>
                <br><br>
                <input type="submit" name="action" value="update" class="btn btn-warning">
                <br><br>
                <input type="submit" name="action" value="open" class="btn btn-success">
            </table>
        </form>
    </div>
    <c:choose>
        <c:when test="${!(grpSize==1 || grpSize==0)}">
            <div class="col-sm-3" align="center" style="border-style: groove; padding:20px; width:300px">
                <h1>select Group</h1>

                <form method="put" action="/selected_catlist">
                    <table border="1">
                        <input type="submit" name="ChangeCat" value="All" class="btn btn-primary btn-md grpbtn">
                        <br>
                        <c:forEach var="grpname" items="${grpList}" varStatus="status">
                            <input type="submit" name="ChangeCat" value="${grpname}"
                                   class="btn btn-primary btn-md grpbtn">
                            <br>
                        </c:forEach>
                        <br><br>
                    </table>
                </form>
            </div>
        </c:when>
    </c:choose>
    <c:choose>
        <c:when test="${chSize!=0}">
            <div class="col-sm-3" align="center" style="border-style: groove; padding:20px; width:400px">
                <h1>select Channel</h1>
                <p>${chSize} pcs</p>
                <form method="put" action="/selected_chlist">
                    <table border="1">
                        <select id="chList" name="chname" onChange="playlistChange()">
                            <c:forEach var="channel" items="${channelList}" varStatus="status">
                                <option value="${channel.id}"
                                        <c:choose>
                                            <c:when test="${channel.id==selectedChn}">
                                                selected="selected"
                                            </c:when>
                                        </c:choose>
                                        >${channel.name}</option>
                            </c:forEach>
                        </select>
                        <br><br>
                        <label id="chaUrl" name="chnUrl" value="${chUrl}">${chUrl}</label>
                        <br>
                        <input type="submit" name="action" value="GetData" class="btn btn-success"
                               onClick="channelOpen();"
                        <%--data-toggle="modal" data-target="#myAceModal"--%>>
                       <%-- <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myAceModal">Open</button>--%>
                    </table>
                </form>
            </div>
        </c:when>
    </c:choose>

    <script type="text/javascript">
        var plugin = document.getElementById("plugin");
        plugin.playlistLoadAsyncPlayer("acestream://201fc9f0ca0311d6b60087911f6a42910d026197");
    </script>

    <div class="col-sm-3" align="center" style="border-style: groove; padding:20px; width:300px">

        <embed id="plugin" type="application/x-acestream-plugin"
               width="800"
               height="600"
               fullscreencontrols="true"
               fscontrolsenable="true"
               fscontrols="default"
               nofscontrolsenable="true"
               nofscontrols="default"
               nofscontrolsheight="36"
               loopable="false" />

    </div>
</div>



<c:choose>
    <c:when test="${chUrl!=''}">

    </c:when>
</c:choose>
<!-- Modal -->
<div class="modal fade" id="myAceModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Modal Header</h4>
            </div>
            <div class="modal-body">
                <p>Some text in the modal.</p>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="myVlcModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Modal Header</h4>
            </div>
            <div class="modal-body">
                <p>Some text in the modal.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

</body>
</html>
