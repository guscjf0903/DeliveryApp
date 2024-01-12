
$(document).ready(function () {
    getMenuData();
});

function getMenuData() {
    var loginId = sessionStorage.getItem("loginId");

    $.ajax({
        url: "/menu/getMenuData?loginId=" + encodeURIComponent(loginId),
        method: "GET",
        success: function (data) {
            displayMenuData(data);
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function displayMenuData(menuData) {
    var menuList = $("#menuList");
    menuList.empty();

    var selectedMenu = $("#selectedMenu");
    selectedMenu.empty();

    if (menuData.length === 0) {
        selectedMenu.append("<option value=''>메뉴가 없습니다.</option>");
        menuList.append("<p>No menu items found.</p>");
        return;
    }

    menuData.forEach(function (menuItem) {
        var menuItemHtml = "<p>" + menuItem.menuName + " - $" + menuItem.menuPrice + "</p>";
        menuList.append(menuItemHtml);

        var optionHtml = "<option value='" + menuItem.menuName + "'>" + menuItem.menuName + "</option>";
        selectedMenu.append(optionHtml);
    });
}

function deleteSelectedMenu() {
    var loginId = sessionStorage.getItem("loginId");
    var selectedMenu = $("#selectedMenu").val();
    console.log(selectedMenu)

    $.ajax({
        url: "/menu/deleteMenu?loginId=" + encodeURIComponent(loginId)+"&menuName="+encodeURIComponent(selectedMenu),
        method: "DELETE",
        success: function () {
            alert("삭제되었습니다.");
        },
        error: function (error) {
            console.log(error);
        }
    })

}
