window.onload = function () {
    let button = document.querySelector('button');
    console.log(button);
}

function addMenuField() {
    // Create new menu and quantity input fields
    const menuField = document.createElement('input');
    menuField.type = 'text';
    menuField.placeholder = '메뉴 이름';
    menuField.required = true;

    const quantityField = document.createElement('input');
    quantityField.type = 'number';
    quantityField.placeholder = '수량';
    quantityField.required = true;

    // Append the new fields to the form
    const form = document.getElementById('orderForm');
    form.appendChild(menuField);
    form.appendChild(quantityField);
    form.appendChild(document.createElement('br'));
}

function sendOrder() {
    // Collect order data from all menu and quantity fields
    const menuFields = document.querySelectorAll('input[type="text"][placeholder="메뉴 이름"]');
    const quantityFields = document.querySelectorAll('input[type="number"][placeholder="수량"]');

    const menuOrders = [];

    menuFields.forEach((menuField, index) => {
        const menuName = menuField.value;
        const quantity = parseInt(quantityFields[index].value);
        menuOrders.push({ menuName, quantity });
    });

    // Create an object representing the order
    const orderData = {
        storeName: document.getElementById('storeName').value,
        loginId: parseInt(document.getElementById('loginId').value),
        menuOrders: menuOrders
    };

    // Send the data to the server using fetch
    fetch('/order/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(orderData),
    })
        .then(response => {
            if(response.status === 200) {
                // Order was successfully created
                alert('주문이 성공적으로 완료되었습니다.');
            } else {
                // Order was not created
                alert('주문이 실패하였습니다.');
            }
        })
        .catch(error => {
            // Handle errors
            console.error('Error sending data to the server:', error);
        });

    // Prevent the default form submission
    return false;
}