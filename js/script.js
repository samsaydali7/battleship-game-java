function loadMunu() {
    document.getElementById("content").innerHTML = '<button class="menuItem" onclick="scoreBoredContent()">View Score Bored</button> <br> <button class="menuItem" onclick="GameInfosContent()">View Played Games</button>';
}
function fillGameStepsContent(id) {
    $.getJSON("./GameSteps.JSON", function (json) {
        document.getElementById("content").innerHTML = '<div id="grids"><div class="playerGrid" id="firstPlayerGrid"><h2>First player Grid</h2></div><div class="playerGrid" id="secondPlayerGrid"><h2>Second player Grid</h2></div></div><ol id="playersStepsList"></ol>';

        var obeject = JSON.parse(json[id]);
        console.log(json[id]);
        var openTAG = "<li>", closeTag = "</li>", item;
        for (i = 0; i < obeject["playersSteps"].length; i++) {
            document.getElementById("playersStepsList").innerHTML += openTAG + obeject["playersSteps"][i] + closeTag;
        }
        var lineOpenTag = "<div class='line'>", divCloseTag = "</div>", WaterSquare = '<div class="square" style="width:30px;height:30px;background-color:#7fb5c1"></div>', shipSquare = '<div class="square" style="width:30px;height:30px;background-color:#5f8e53"></div>';
        var firstPlayerGrid = obeject["firstPlayerGrid"], secondPlayerGrid = obeject["secondPlayerGrid"];
        for (i = 0; i < firstPlayerGrid.length; i++) {
            var line = "";
            for (j = 0; j < firstPlayerGrid[i].length; j++) {
                if (firstPlayerGrid[i][j] == "SHIP_PART") {
                    line += shipSquare;
                }
                else
                    line += WaterSquare;
            }
            document.getElementById("firstPlayerGrid").innerHTML += lineOpenTag + line + divCloseTag;
        }
        for (i = 0; i < secondPlayerGrid.length; i++) {
            var line = "";
            for (j = 0; j < secondPlayerGrid[i].length; j++) {
                if (secondPlayerGrid[i][j] == "SHIP_PART") {
                    line += shipSquare;
                }
                else
                    line += WaterSquare;
            }
            document.getElementById("secondPlayerGrid").innerHTML += lineOpenTag + line + divCloseTag;
        }
    });
}
function scoreBoredContent() {
    $.getJSON("./PlayerInfos.JSON", function (json) {
        document.getElementById("content").innerHTML = ' <h1>Score Boared</h1> <table align="center"> <thead> <tr> <th>Player ID</th> <th>Name</th> <th>Played Games</th> <th>Mark</th> </tr> </thead> <tbody id="tableBody"> <tr> </tr> </tbody> </table>';
        var array = [];
        for (i = 0; i < json.length; i++) {
            array.push(JSON.parse(json[i]));
        }
        array = bubbleSortPlayers(array);
        var tdOpenTag = "<td>", tdCloseTag = "</td>", trOpenTag = "<tr>", trCloseTag = "</tr>";
        array.forEach(function (element) {
            var row = trOpenTag +
                tdOpenTag + element['playerID'] + tdCloseTag +
                tdOpenTag + element['playerName'] + tdCloseTag +
                tdOpenTag + element['playedGames'] + tdCloseTag +
                tdOpenTag + element['mark'] + tdCloseTag +
                trCloseTag;
            document.getElementById('tableBody').innerHTML += row;
        }, this);
    });
}
function GameInfosContent() {
    $.getJSON("./GameInfos.JSON", function (json) {
        document.getElementById("content").innerHTML = ' <h1>Games Played</h1> <table> <thead> <tr> <th>Game ID</th> <th>Player 1</th> <th>Player 1\'s Mark</th> <th>Player 2</th> <th>Player 2\'s Mark</th> <th>Start Time</th> <th>End Time</th> <th>Winner</th> <th>View Steps</th> </tr> </thead> <tbody id="GamesPlayedBody"> </tbody> </table> <button onclick="sfp()">Sort By First Player Name</button> <button onclick="ssd()">Sort By Start Date</button> <button onclick="sed()">Sort By End Date</button>'
        var array = [];
        for (i = 0; i < json.length; i++) {
            array.push(JSON.parse(json[i]));
        }
        var tdOpenTag = "<td>", tdCloseTag = "</td>", trOpenTag = "<tr>", trCloseTag = "</tr>";
        document.getElementById('GamesPlayedBody').innerHTML = "";
        array.forEach(function (element) {
            var row = trOpenTag +
                tdOpenTag + element['gameID'] + tdCloseTag +
                tdOpenTag + element['firstPlayer'] + tdCloseTag +
                tdOpenTag + element['firstPlayerMark'] + tdCloseTag +
                tdOpenTag + element['secondPlayer'] + tdCloseTag +
                tdOpenTag + element['secondPlayerMark'] + tdCloseTag +
                tdOpenTag + element['startingDate'] + tdCloseTag +
                tdOpenTag + element['endingDate'] + tdCloseTag +
                tdOpenTag + element['winner'] + tdCloseTag +n
                tdOpenTag + '<button onclick="fillGameStepsContent(' + element['gameID'] + ')">visit</button>' + tdCloseTag +
                trCloseTag;
            document.getElementById('GamesPlayedBody').innerHTML += row;
        }, this);

    });
}
function compDates(date1, date2) { //returns true if date 1 > date 2
    var d1 = date1.split(':'), d2 = date2.split(':');
    if (d1[0] > d2[0]) {
        return true;
    }
    else if (d1[1] > d2[1] && d1[0] == d2[0]) {
        return true;
    }
    else if (d1[2] > d2[2] && d1[1] == d2[1] && d1[0] == d2[0]) {
        return true;
    }
    return false;
}
console.log(compDates('01:02:03', '00:10:10'));
function bubbleSortPlayers(a) {
    var swapped;
    do {
        swapped = false;
        for (var i = 0; i < a.length - 1; i++) {
            if (a[i]['mark'] < a[i + 1]['mark']) {
                var temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
                swapped = true;
            }
        }
    } while (swapped);
    return a;
}
function sortByStartDate(a) {
    var swapped;
    do {
        swapped = false;
        for (var i = 0; i < a.length - 1; i++) {
            if ((compDates(a[i]['startingDate'], a[i + 1]['startingDate']))) {
                var temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
                swapped = true;
            }
        }
    } while (swapped);
    return a;
}
function sortByEndDate(a) {
    var swapped;
    do {
        swapped = false;
        for (var i = 0; i < a.length - 1; i++) {
            if ((compDates(a[i]['endingDate'], a[i + 1]['endingDate']))) {
                var temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
                swapped = true;
            }
        }
    } while (swapped);
    return a;
}
function sortByFirstPlayer(a) {
    var swapped;
    do {
        swapped = false;
        for (var i = 0; i < a.length - 1; i++) {
            if ((compDates(a[i]['firstPlayer'], a[i + 1]['firstPlayer']))) {
                var temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
                swapped = true;
            }
        }
    } while (swapped);
    return a;
}
//sorting Button
function sfp() {
    $.getJSON("./GameInfos.JSON", function (json) {
        var array = [];
        for (i = 0; i < json.length; i++) {
            array.push(JSON.parse(json[i]));
        }
        array = sortByFirstPlayer(array);
        var tdOpenTag = "<td>", tdCloseTag = "</td>", trOpenTag = "<tr>", trCloseTag = "</tr>";
        document.getElementById('GamesPlayedBody').innerHTML = "";
        array.forEach(function (element) {
            var row = trOpenTag +
                tdOpenTag + element['gameID'] + tdCloseTag +
                tdOpenTag + element['firstPlayer'] + tdCloseTag +
                tdOpenTag + element['firstPlayerMark'] + tdCloseTag +
                tdOpenTag + element['secondPlayer'] + tdCloseTag +
                tdOpenTag + element['secondPlayerMark'] + tdCloseTag +
                tdOpenTag + element['startingDate'] + tdCloseTag +
                tdOpenTag + element['endingDate'] + tdCloseTag +
                tdOpenTag + element['winner'] + tdCloseTag +
                tdOpenTag + '<button onclick="fillGameStepsContent(' + element['gameID'] + ')">visit</button>' + tdCloseTag +
                trCloseTag;
            document.getElementById('GamesPlayedBody').innerHTML += row;
        }, this);

    });
}
//sorting Button
function ssd() {
    $.getJSON("./GameInfos.JSON", function (json) {
        var array = [];
        for (i = 0; i < json.length; i++) {
            array.push(JSON.parse(json[i]));
        }
        array = sortByStartDate(array);
        var tdOpenTag = "<td>", tdCloseTag = "</td>", trOpenTag = "<tr>", trCloseTag = "</tr>";
        document.getElementById('GamesPlayedBody').innerHTML = "";
        array.forEach(function (element) {
            var row = trOpenTag +
                tdOpenTag + element['gameID'] + tdCloseTag +
                tdOpenTag + element['firstPlayer'] + tdCloseTag +
                tdOpenTag + element['firstPlayerMark'] + tdCloseTag +
                tdOpenTag + element['secondPlayer'] + tdCloseTag +
                tdOpenTag + element['secondPlayerMark'] + tdCloseTag +
                tdOpenTag + element['startingDate'] + tdCloseTag +
                tdOpenTag + element['endingDate'] + tdCloseTag +
                tdOpenTag + element['winner'] + tdCloseTag +
                tdOpenTag + '<button onclick="fillGameStepsContent(' + element['gameID'] + ')">visit</button>' + tdCloseTag +
                trCloseTag;
            document.getElementById('GamesPlayedBody').innerHTML += row;
        }, this);

    });
}
//sorting Button
function sed() {
    $.getJSON("./GameInfos.JSON", function (json) {
        var array = [];
        for (i = 0; i < json.length; i++) {
            array.push(JSON.parse(json[i]));
        }
        array = sortByEndDate(array);
        var tdOpenTag = "<td>", tdCloseTag = "</td>", trOpenTag = "<tr>", trCloseTag = "</tr>";
        document.getElementById('GamesPlayedBody').innerHTML = "";
        array.forEach(function (element) {
            var row = trOpenTag +
                tdOpenTag + element['gameID'] + tdCloseTag +
                tdOpenTag + element['firstPlayer'] + tdCloseTag +
                tdOpenTag + element['firstPlayerMark'] + tdCloseTag +
                tdOpenTag + element['secondPlayer'] + tdCloseTag +
                tdOpenTag + element['secondPlayerMark'] + tdCloseTag +
                tdOpenTag + element['startingDate'] + tdCloseTag +
                tdOpenTag + element['endingDate'] + tdCloseTag +
                tdOpenTag + element['winner'] + tdCloseTag +
                tdOpenTag + '<button onclick="fillGameStepsContent(' + element['gameID'] + ')">visit</button>' + tdCloseTag +
                trCloseTag;
            document.getElementById('GamesPlayedBody').innerHTML += row;
        }, this);

    });
}