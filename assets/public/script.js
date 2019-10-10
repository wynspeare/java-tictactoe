const cells = document.querySelectorAll('.cell-contents')
const startGame = document.querySelector('.btn')
let modal = document.getElementById("myModal");
let closeModal = document.getElementsByClassName("close")[0];
let boardContainer = document.getElementsByClassName("board")[0];

function loadBoard(callback) {
 $.getJSON( "/board.json", function( data ) {
        console.log(data.board);
        callback(data.board)
      })
 }

function updateCells(board) {
    console.log(board);
    cells.forEach(function (cell, index) {
        cell.innerHTML = board[index];
    })
}

loadBoard(updateCells)

function clearCells(event) {
    cells.forEach(cell => {
        cell.innerHTML = " ";
    })
}

function markCell(event) {
    let cell = document.querySelector(`[id="${event.target.id}"]`)
    if (!isCellFilled(cell.innerHTML)) {
        cell.innerHTML = "X";
        let updatedBoard = getCellsValues();

        let reloadPromise = new Promise(function() {
            updateJsonBoard(updatedBoard);
        });
        reloadPromise.then(loadBoard(updateCells))
    } else {
        modal.style.display = "block";
    }
}

function getCellsValues() {
    let board = [];
    cells.forEach(function (cell, index) {
        board.push(cell.innerHTML);
    })
    return board;
}

function updateJsonBoard(updatedBoard) {
    var request = new XMLHttpRequest();
    request.open("POST", "/board.json", true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.send(JSON.stringify({
        board: updatedBoard
    }));
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