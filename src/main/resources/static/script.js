const boardElement = document.getElementById("board");
const depthSlider = document.getElementById("depthSlider");
const depthValue = document.getElementById("depthValue");
const algorithmSelect = document.getElementById("algorithmSelect");
const statusText = document.getElementById("status");

let board;
let gameOver;

function initBoard() {
    board = [
        [" ", " ", " "],
        [" ", " ", " "],
        [" ", " ", " "]
    ];
    gameOver = false;
    statusText.textContent = "";
    renderBoard();
}

depthSlider.addEventListener("input", () => {
    depthValue.textContent = depthSlider.value;
});

function renderBoard() {
    boardElement.innerHTML = "";

    for (let row = 0; row < 3; row++) {
        for (let col = 0; col < 3; col++) {

            const cell = document.createElement("div");
            cell.classList.add("cell");
            cell.textContent = board[row][col];

            cell.addEventListener("click", () => playerMove(row, col));

            boardElement.appendChild(cell);
        }
    }
}

async function playerMove(row, col) {

    if (gameOver) return;
    if (board[row][col] !== " ") return;

    board[row][col] = "O";
    renderBoard();

    if (checkWinner("O")) {
        statusText.textContent = "You win!";
        gameOver = true;
        return;
    }

    if (isDraw()) {
        statusText.textContent = "Draw!";
        gameOver = true;
        return;
    }

    await aiMove();
}

async function aiMove() {

    const depth = parseInt(depthSlider.value);
    const algorithm = algorithmSelect.value;

    const response = await fetch("/api/move", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            board: board,
            player: "X",
            depth: depth,
            algorithm: algorithm
        })
    });

    const result = await response.json();

    board[result.row][result.col] = "X";
    renderBoard();

    statusText.textContent =
        "Nodes visited: " + result.nodesVisited;

    if (checkWinner("X")) {
        statusText.textContent += " | Computer wins!";
        gameOver = true;
        return;
    }

    if (isDraw()) {
        statusText.textContent += " | Draw!";
        gameOver = true;
    }
}

function checkWinner(player) {

    for (let i = 0; i < 3; i++) {
        if (
            board[i][0] === player &&
            board[i][1] === player &&
            board[i][2] === player
        ) return true;

        if (
            board[0][i] === player &&
            board[1][i] === player &&
            board[2][i] === player
        ) return true;
    }

    if (
        board[0][0] === player &&
        board[1][1] === player &&
        board[2][2] === player
    ) return true;

    if (
        board[0][2] === player &&
        board[1][1] === player &&
        board[2][0] === player
    ) return true;

    return false;
}

function isDraw() {
    return board.flat().every(cell => cell !== " ");
}

function resetGame() {
    initBoard();
}

initBoard();