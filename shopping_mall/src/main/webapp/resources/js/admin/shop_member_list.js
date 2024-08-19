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
                alert('상태가 업데이트되었습니다.');
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

function useAgree(){

}
function infoAgree(){
	
}
