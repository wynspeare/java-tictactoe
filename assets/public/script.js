const cells = document.querySelectorAll('.cell-contents');
const startGameButton = document.getElementById('start-game-btn');
const playAgainButton = document.getElementById('play-again-btn');
const showWinnerButton = document.getElementById('show-winner');

let cellFilledModal = document.getElementById("cell-filled-modal");
let playAgainModal = document.getElementById("game-over-modal");

let closeModal = document.getElementsByClassName("close")[0];
let closePlayAgainModal = document.getElementsByClassName("close")[1];

let boardContainer = document.getElementsByClassName("board")[0];
let overlay = document.getElementById('overlay');


function loadBoard(callback) {
    $.ajax({
      dataType: "json",
      url: "/board.json",
    })
    .done(function (data) {
        callback(data)
    }, function (data) {
        isGameOver(data.gameStatus, data.winner, data.winCombo)
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

function isGameOver(gameStatus, winner, combo) {
    let modalText = document.getElementById('modal-text');
    if (gameStatus == "win") {
        modalText.innerHTML = `${winner} has WON!`
        playAgainModal.style.display = "block";
        overlay.style.display = "block";
        showWinningCombo(combo)
    } else if (gameStatus == "draw") {
        modalText.innerHTML = "It's a draw!"
        overlay.style.display = "show";
        playAgainModal.style.display = "block";
    } else {
        // Comp moved "here" - highlight cell?
    }
}

loadBoard(updateCells)

function clearCells(event) {
    cells.forEach(cell => {
        cell.innerHTML = " ";
        cell.style.background = "white"
    })
    overlay.style.display = "none";
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
    }, function (data) {
          isGameOver(data.gameStatus, data.winner, data.winCombo)
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
closePlayAgainModal.onclick = function() {
    playAgainModal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == cellFilledModal) {
        cellFilledModal.style.display = "none";
    }
}

function playNewGame(event) {
    clearCells(event)
    playAgainModal.style.display = "none";
}

function hideModal(event) {
    playAgainModal.style.display = "none";
}

function showWinningCombo(winCombo) {
    let finalBoard = getCellsValues();
    console.log(winCombo)
    cells.forEach(function (cell, index) {
        if ( winCombo.includes(index) ) {
            cell.style.background = "#d1e537"
        }
    })
}

startGameButton.addEventListener('click', clearCells)
playAgainButton.addEventListener('click', playNewGame)
showWinnerButton.addEventListener('click', hideModal)
boardContainer.addEventListener('click', markCell)