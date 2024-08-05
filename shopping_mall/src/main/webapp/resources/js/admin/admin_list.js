function updateApprovalStatus(adid, newStatus, message) {
    if (confirm(message)) {
        fetch(`/update_admin_status.do?adid=${adid}&status=${newStatus}`, {
            method: 'GET',
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('상태가 성공적으로 업데이트되었습니다.');
                location.reload(); // 페이지를 새로고침하여 변경 사항을 반영합니다.
            } else {
                alert('상태 업데이트 실패: ' + data.message);
            }
        })
        .catch(error => {
            alert('서버 오류 발생: ' + error);
        });
    } else {
        return false;
    }
}