function saleTime() {
    var timeType;
    var loginId = sessionStorage.getItem("loginId");
    if (!loginId) {
        alert("로그인을 해주세요.");
        window.location.href = '/user/login';
    }
    var startDate = $('#timeStartDate').val();
    var endDate = $('#timeEndDate').val();


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
            startDate: startDate,
            endDate: endDate
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

    if (data.salesTotal == null) {
        salesTimeDisplay.html("<p>No sales found.</p>");
        return false;
    } else {
        var salesStartTime = getMonthText($('#timeStartDate').val());
        var salesEndTime = getMonthText($('#timeEndDate').val());
        salesTimeDisplay.html(`${salesStartTime} ~ ${salesEndTime} ${timeType} 매출 금액 : ${data.salesTotal}원`);
    }
}

function getMonthText(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 1을 더하고 두 자리로 패딩
    const day = date.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
}