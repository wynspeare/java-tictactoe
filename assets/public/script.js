const cells = document.querySelectorAll('.cell-contents')

const startGameButton = document.getElementById('start-game-btn')
const playAgainButton = document.getElementById('play-again-btn')
const showWinnerButton = document.getElementById('show-winner')

const cellFilledModal = document.getElementById("cell-filled-modal")
const playAgainModal = document.getElementById("game-over-modal")

const closeModal = document.getElementsByClassName("close")[0]
const closePlayAgainModal = document.getElementsByClassName("close")[1]

const boardContainer = document.getElementsByClassName("board")[0]
const overlay = document.getElementById('overlay')


const loadBoard = callback => {
    $.ajax({
        dataType: "json",
        url: "/board.json",
    })
    .done(
        callback,
        data => isGameOver(data.gameStatus, data.winner, data.winCombo)
    )
    .fail(
        function (jqXHR, textStatus, error) {
            console.log(error)
     })
}

const updateCells = jsonData => cells.forEach((cell, index) => { cell.innerHTML = jsonData.board[index] })

const isGameOver = (gameStatus, winner, combo) => {
    let modalText = document.getElementById('modal-text')
    if (gameStatus == "win") {
        modalText.innerHTML = `${winner} has WON!`
        playAgainModal.style.display = "block"
        overlay.style.display = "block"
        showWinningCombo(combo)
    } else if (gameStatus == "draw") {
        modalText.innerHTML = "It's a draw!"
        overlay.style.display = "show"
        playAgainModal.style.display = "block"
    }
}

const clearCells = event => {
    cells.forEach(cell => {
        cell.innerHTML = " "
        cell.style.background = "white"
    })
    overlay.style.display = "none"
    let updatedBoard = getCellsValues()
    updateJsonBoard(updatedBoard)
}

const markCell = event => {
    let cell = document.querySelector(`[id="${event.target.id}"]`)
    if (cell != null) {
        if (!isCellFilled(cell.innerHTML)) {
            cell.innerHTML = "X"
            let updatedBoard = getCellsValues()
            updateJsonBoard(updatedBoard)
        } else {
            cellFilledModal.style.display = "block"
        }
    }
}

const getCellsValues = () => {
    let board = []
    cells.forEach((cell, index) => {
        board.push(cell.innerHTML)
    })
    return board
}

const updateJsonBoard = updatedBoard => {
    $.ajax({
        type: "POST",
        url: "/board.json",
        data: JSON.stringify({ board: updatedBoard }),
        contentType: "application/json charset=utf-8",
        dataType: "json"
    })
    .done(
        data => updateCells(data),
        function (data) {
              isGameOver(data.gameStatus, data.winner, data.winCombo)
          })
    .fail(function (jqXHR, textStatus, error) {
        console.log(error)
    })
}

const isCellFilled = cell => { return cell == "X" || cell == "O" }

closeModal.onclick = () => { cellFilledModal.style.display = "none" }

closePlayAgainModal.onclick = () => { playAgainModal.style.display = "none" }

window.onclick = event => {
    if (event.target == cellFilledModal) {
        cellFilledModal.style.display = "none"
    }
}

const playNewGame = event => {
    clearCells(event)
    playAgainModal.style.display = "none"
}

const hideModal = event => { playAgainModal.style.display = "none" }

const showWinningCombo = winCombo => {
    let finalBoard = getCellsValues()
    cells.forEach( (cell, index) => {
        if ( winCombo.includes(index) ) {
            cell.style.background = "#d1e537"
        }
    })
}

startGameButton.addEventListener('click', clearCells)
playAgainButton.addEventListener('click', playNewGame)
showWinnerButton.addEventListener('click', hideModal)
boardContainer.addEventListener('click', markCell)

loadBoard(updateCells)