function saleMonth() {
    var userType;
    var loginId = sessionStorage.getItem("loginId");
    var startDate = $('#startDate').val();
    var endDate = $('#endDate').val();

    if($('#isStore').prop('checked')) {
        userType = 'store';
    } else if($('#isUser').prop('checked')) {
        userType = 'user';
    } else {
        alert('시간을 선택해주세요.');
        return false;
    }

    $.ajax({
        url: "/sales/date",
        method: "GET",
        data: {
            loginId: loginId,
            userType: userType,
            startDate: startDate,
            endDate: endDate
        },
        success: function (data) {
            displaySalesDateData(data);
        },
        error: function (error) {
            console.log(error);
            const salesDateDisplay = $('#salesDateDisplay');
            salesDateDisplay.empty();
            salesDateDisplay.html(error);
        }
    })

    return false;
}

function displaySalesDateData(data) {
    const salesDateDisplay = $('#salesDateDisplay');
    salesDateDisplay.empty();

    if(data.length === 0) {
        salesDateDisplay.html("<p>No sales found.</p>");
        return false;
    } else {
        var startDate = getMonthText($('#startDate').val());
        var endDate = getMonthText($('#endDate').val());
        salesDateDisplay.html(`${startDate} ~ ${endDate} 매출 금액 : ${data.salesTotal}원`);
    }

}

function getMonthText(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 1을 더하고 두 자리로 패딩
    const day = date.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
}