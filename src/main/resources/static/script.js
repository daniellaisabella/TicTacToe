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

    if (result.row !== undefined) {
        board[result.row][result.col] = "X";
        renderBoard();
    }

    if (result.winner) {
        statusText.textContent = "Winner: " + result.winner;
        gameOver = true;
    }
}

function resetGame() {
    statusText.textContent = "";
    initBoard();
}

initBoard();