function save_info() {
    const form = document.getElementById('siteinfoForm');
    const formData = new FormData(form);
    const data = {};

    formData.forEach((value, key) => {
        data[key] = value;
    });

    fetch('./saveSiteinfo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(responseData => {
        if (responseData.success) {
            alert('설정 저장 완료');
        } else {
            alert('설정 저장 실패: ' + responseData.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('설정 저장 중 오류 발생');
    });
}