const cells = document.querySelectorAll('.cell-contents');
const startGameButton = document.getElementById('start-game-btn');
const playAgainButton = document.getElementById('play-again-btn');

let cellFilledModal = document.getElementById("cell-filled-modal");
let playAgainModal = document.getElementById("game-over-modal");

let closeModal = document.getElementsByClassName("close")[0];
let closePlayAgainModal = document.getElementsByClassName("close")[1];

let boardContainer = document.getElementsByClassName("board")[0];

function loadBoard(callback) {
    $.ajax({
      dataType: "json",
      url: "/board.json",
    })
    .done(function (data) {
        callback(data)

    }, function (data) {
        isGameOver(data.gameStatus, data.winner)
    })
    .fail(function (jqXHR, textStatus, error) {
        console.log(error)
    })
}

function updateCells(jsonData) {
    cells.forEach(function (cell, index) {
        cell.innerHTML = jsonData.board[index];
    })

}

function isGameOver(gameStatus, winner) {
    let modalText = document.getElementById('modal-text');
    if (gameStatus == "win") {
        modalText.innerHTML = `${winner} has WON!`
        playAgainModal.style.display = "block";
    } else if (gameStatus == "draw") {
        modalText.innerHTML = "It's a draw!"
        playAgainModal.style.display = "block";
    } else {
        // Comp moved "here"
    }
}

loadBoard(updateCells)

function clearCells(event) {
    cells.forEach(cell => {
        cell.innerHTML = " ";
    })
    let updatedBoard = getCellsValues();
    updateJsonBoard(updatedBoard);
}

function markCell(event) {
    let cell = document.querySelector(`[id="${event.target.id}"]`)
    if (cell.innerHTML != null) {
        if (!isCellFilled(cell.innerHTML)) {
            cell.innerHTML = "X";
            let updatedBoard = getCellsValues();
            updateJsonBoard(updatedBoard)
        } else {
            cellFilledModal.style.display = "block";
        }
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
    $.ajax({
        type: "POST",
        url: "/board.json",
        data: JSON.stringify({ board: updatedBoard }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        statusCode: {
            200: function() {
              console.log( "Post success!" );
            }
          }
    })
    .done(function (data) {
        updateCells(data)
    })
    .fail(function (jqXHR, textStatus, error) {
        console.log(error)
    })
}

function isCellFilled(cell) {
    return (cell == "X" || cell == "O" ? true : false)
}

closeModal.onclick = function() {
    cellFilledModal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == cellFilledModal) {
        cellFilledModal.style.display = "none";
    }
}

startGameButton.addEventListener('click', clearCells)
boardContainer.addEventListener('click', markCell)