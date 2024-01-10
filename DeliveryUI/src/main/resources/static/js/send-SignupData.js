function toggleCompanyFields() {
    var storeCheckbox = document.querySelector('input[name=store]');
    var companyNameInput = document.querySelector('input[name=companyName]');
    var addressInput = document.querySelector('input[name=address]');

    if (storeCheckbox.checked) {
        companyNameInput.disabled = false;
        addressInput.disabled = false;
    } else {
        companyNameInput.disabled = true;
        addressInput.disabled = true;
    }
}