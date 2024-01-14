function saleTime() {
    var timeType;
    var loginId = sessionStorage.getItem("loginId");
    var Date = $('#fixDate').val();

    if ($('#lunch').prop('checked')) {
        timeType = 'lunch';
    } else if ($('#dinner').prop('checked')) {
        timeType = 'dinner';
    } else {
        alert('시간을 선택해주세요.');
        return false;
    }

    $.ajax({
        url: "/sales/time",
        method: "GET",
        data: {
            loginId: loginId,
            timeType: timeType,
            Date: Date
        },
        success: function (data) {
            displaySalesTimeData(data);
        },
        error: function (error) {
            console.log(error);
        }
    })
}
function displaySalesTimeData(data) {
    const salesTimeDisplay = $('#salesTimeDisplay');
    salesTimeDisplay.empty();

    var timeType;
    if ($('#lunch').prop('checked')) {
        timeType = '점심';
    } else if ($('#dinner').prop('checked')) {
        timeType = '저녁';
    }

    if (data.length === 0) {
        salesTimeDisplay.html("<p>No sales found.</p>");
        return false;
    } else {
        var salestime = getMonthText($('#fixDate').val());
        salesTimeDisplay.html(`${salestime} ${timeType} 매출 금액 : ${data.salesTotal}원`);
    }
}

function getMonthText(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 1을 더하고 두 자리로 패딩
    const day = date.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
}