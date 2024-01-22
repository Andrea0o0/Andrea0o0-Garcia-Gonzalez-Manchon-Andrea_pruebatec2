let intervalState;
let intervalClick;

// Loop to change status or state from the buttons state waiting shift
function updateStatus() {
    let waitingButtons = document.querySelectorAll('.state-btn');

    let clickEvent = new MouseEvent('click', {
        bubbles: true,
        cancelable: true,
        view: window
    });
    clearInterval(intervalState);
    clearInterval(intervalClick);

    if (waitingButtons.length > 0) {
        let firstTwoButtons = Array.from(waitingButtons).slice(0, 2);
        firstTwoButtons.forEach(function (button) {
            console.log(button);
            intervalClick = setInterval(function () {
                button.dispatchEvent(clickEvent);
            }, 25000);
            intervalClick();
        });

        intervalState = setInterval(updateStatus, 30 * 1000);
    }
}


updateStatus();
