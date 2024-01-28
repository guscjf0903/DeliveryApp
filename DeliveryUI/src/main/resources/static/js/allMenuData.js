$(document).ready(function () {
    getAllMenuData();
});

function getAllMenuData() {
    $.ajax({
        url: "/menu/getAllMenuData",
        method: "GET",
        success: function (data) {
            displayAllMenuData(data);
        },
        error: function (error) {
            console.log(error);
        }
    })
}
function displayAllMenuData(data) {
    const allMenuList = $("#allMenuList");
    allMenuList.empty();

    for (const key in data) {
        if (data.hasOwnProperty(key)) {
            // 각 카테고리(김밥천국, 술집 등)에 대한 데이터 배열을 가져옴
            const categoryList = data[key];

            // 카테고리 제목을 추가
            allMenuList.append("<h3>" + key + "</h3>");

            // 각 데이터 배열을 순회
            for (let i = 0; i < categoryList.length; i++) {
                const menu = categoryList[i];
                // 각 메뉴의 이름과 가격을 출력
                allMenuList.append("<p>Menu Name: " + menu.menuName + ", Menu Price: " + menu.menuPrice + "</p>");
            }
        }
    }
}