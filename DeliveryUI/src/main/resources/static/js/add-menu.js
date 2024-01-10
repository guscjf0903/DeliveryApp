
function menuAdd() {
    var menuName = document.querySelector('#menuName').value;
    var menuPrice = document.querySelector('#menuPrice').value;
    var menuCategory = document.querySelector('#menuCategory').value;
    var loginId = document.querySelector('#loginId').value;

    var data = {
        menuName: menuName,
        menuPrice: menuPrice,
        menuCategory: menuCategory,
        loginId: loginId,
    };

    fetch('/menu/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.status === 200) {
                alert('메뉴가 추가되었습니다.');

            } else {
                console.error('request failed');
                alert('메뉴 추가에 실패하였습니다.')
            }
        })
        .catch(error => {
            console.error('request failed', error);
            alert('메뉴 추가에 실패하였습니다.')
        });

    return false;
}