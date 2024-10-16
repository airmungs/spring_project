function updateLoginStatus(userId, status) {
    if (confirm(`정말로 ${status === 'Y' ? '해제' : '정지'}하시겠습니까?`)) {
        fetch('./updateLoginStatus', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ userId: userId, status: status })
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                location.reload();  // 페이지를 새로고침하여 상태를 반영합니다.
            } else {
                alert('상태 업데이트에 실패했습니다: ' + data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('상태 업데이트 중 오류가 발생했습니다.');
        });
    }
}

function useAgree() {
    const useAgreeText = document.querySelector('textarea[name="useAgree"]').value;
    fetch('./updateUseAgree', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ useAgree: useAgreeText })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('저장 완료');
        } else {
            alert('저장 실패: ' + data.message);
        }
    })
    .catch(error => {
        console.error('오류:', error);
        alert('저장 중 오류가 발생했습니다.');
    });
}

function infoAgree() {
    const infoAgreeText = document.querySelector('textarea[name="infoAgree"]').value;
    fetch('./updateInfoAgree', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ infoAgree: infoAgreeText })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('저장 완료');
        } else {
            alert('저장 실패: ' + data.message);
        }
    })
    .catch(error => {
        console.error('오류:', error);
        alert('저장 중 오류가 발생했습니다.');
    });
}
