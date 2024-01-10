function saleMonth() {
    var isStore = document.getElementById('isStore').checked;
    var isUser = document.getElementById('isUser').checked;
    var startDate = document.getElementById('startDate').value;
    var endDate = document.getElementById('endDate').value;

    var date = {
        isStore : isStore,
        isUser : isUser,
        startDate : startDate,
        endDate : endDate,
        loginId: parseInt(document.getElementById('loginId').value),
    }

    fetch('/sales/date', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(date),
    })
        .then(response => {
            if(response.ok) {
                return response.json().then(data => {
                    const salesDisplayElement = document.getElementById('salesDisplay');
                    const salesTotal = data.salesTotal;
                    const startDateText = getMonthText(date.startDate);
                    const endDateText  = (date.startDate !== date.endDate) ? getMonthText(date.endDate) : '';

                    salesDisplayElement.innerHTML = `${startDateText} ~ ${endDateText} 매출 금액 : ${salesTotal}원`;
                });
            } else {
                response.json().then(data => {
                    const salesDisplayElement = document.getElementById('salesDisplay');
                    const errorMessage = data.error;
                    salesDisplayElement.innerHTML = errorMessage;
                    console.error(errorMessage);
                    throw new Error(errorMessage);
                });
            }
        })
        .catch(error => {
            console.error(error.message);
        });

    // 폼이 제출되면 페이지가 새로고침되는 것을 방지
    return false;
}

function getMonthText(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 1을 더하고 두 자리로 패딩
    const day = date.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
}