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
const startGame = document.querySelector('.btn')
let modal = document.getElementById("myModal");
let closeModal = document.getElementsByClassName("close")[0];
let boardContainer = document.getElementsByClassName("board")[0];

readTextFile("/board.json", function(text){
    let data = JSON.parse(text);
    console.log(data.board)
    let board = data.board

    cells.forEach(function (cell, index) {
        cell.innerHTML = board[index];
    })

    function clearCells(event) {
        cells.forEach(cell => {
            cell.innerHTML = " ";
        })
    }

    function markCell(event) {
        let cell = document.querySelector(`[id="${event.target.id}"]`)

        console.log(isCellFilled(cell.innerHTML))
        if (!isCellFilled(cell.innerHTML)) {
            cell.innerHTML = "X";
            let cellIndex = parseInt(event.target.id);
            board[cellIndex - 1] = "X";
            console.log(board);
        // updateJsonBoard(board)
        } else {
            modal.style.display = "block";
        }
    }

    function updateJsonBoard(board) {
        // trigger a POST request with the updatedBoard
    }

    function isCellFilled(cell) {
       return (cell == "X" || cell == "O" ? true : false)
    }

    closeModal.onclick = function() {
      modal.style.display = "none";
    }

    window.onclick = function(event) {
      if (event.target == modal) {
        modal.style.display = "none";
      }
    }

    startGame.addEventListener('click', clearCells)
    boardContainer.addEventListener('click', markCell)
});