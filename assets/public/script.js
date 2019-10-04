//var jsonBoard = require('./board.json');
//    console.log(jsonBoard.board[1]);

//import * as data from './board.json';
//const {board} = data;
//console.log(board);

function readTextFile(file, callback) {
    var rawFile = new XMLHttpRequest();
    rawFile.overrideMimeType("application/json");
    rawFile.open("GET", file, true);
    rawFile.onreadystatechange = function() {
        if (rawFile.readyState === 4 && rawFile.status == "200") {
            callback(rawFile.responseText);
        }
    }
    rawFile.send(null);
}

const cells = document.querySelectorAll('.cell-contents')

readTextFile("/board.json", function(text){
    var data = JSON.parse(text);
    console.log(data.board)
    const board = data.board

    cells.forEach(function (cell, index) {
        cell.innerHTML = board[index];
    })
});
