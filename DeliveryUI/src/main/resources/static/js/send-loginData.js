function SendLogin() {
    var userName = document.querySelector('#userName').value;
    var password = document.querySelector('#password').value;

    var data = {
        userName: userName,
        password: password
    };

    fetch('/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.status === 200) {
                // 1. response의 body에 있는 값을 가져옴
                response.json().then(data => {
                    console.log(data);
                    saveLoginIdToSessionStorage(data.loginId);
                    alert('로그인이 완료되었습니다.');

                })} else {
                console.error('request failed');
                alert('로그인에 실패하였습니다.')
            }
        })
        .catch(error => {
            console.error('request failed', error);
            alert('로그인에 실패하였습니다.')
        });

    return false;
}
