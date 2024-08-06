function save_info() {
    // 폼을 가져옵니다.
    const form = document.getElementById('siteinfoForm');
    
    // 폼 필드를 유효성 검사합니다.
    const formData = new FormData(form);
    const data = {};
    
    formData.forEach((value, key) => {
        data[key] = value;
    });

    // 입력값 유효성 검사
    if (!data.siteTitle) {
        alert('홈페이지 제목을 입력해 주세요.');
        form.querySelector('input[name="siteTitle"]').focus();
        return;
    }

    if (!data.adminEmail || !validateEmail(data.adminEmail)) {
        alert('유효한 관리자 메일 주소를 입력해 주세요.');
        form.querySelector('input[name="adminEmail"]').focus();
        return;
    }

    if (data.usePoints === undefined) {
        alert('포인트 사용 여부를 선택해 주세요.');
        form.querySelector('input[name="usePoints"]').focus();
        return;
    }

    if (data.signupPoints && isNaN(data.signupPoints)) {
        alert('회원가입시 적립금은 숫자로 입력해 주세요.');
        form.querySelector('input[name="signupPoints"]').focus();
        return;
    }

    if (data.signupLevel && isNaN(data.signupLevel)) {
        alert('회원가입시 권한레벨은 숫자로 입력해 주세요.');
        form.querySelector('input[name="signupLevel"]').focus();
        return;
    }

    if (!data.companyName) {
        alert('회사명을 입력해 주세요.');
        form.querySelector('input[name="companyName"]').focus();
        return;
    }

    if (!data.businessPostcode) {
        alert('사업장 우편번호를 입력해 주세요.');
        form.querySelector('input[name="businessPostcode"]').focus();
        return;
    }

    if (!data.businessAddress) {
        alert('사업장 주소를 입력해 주세요.');
        form.querySelector('input[name="businessAddress"]').focus();
        return;
    }

    if (!data.informationManagerName) {
        alert('정보관리책임자명을 입력해 주세요.');
        form.querySelector('input[name="informationManagerName"]').focus();
        return;
    }

    if (!data.informationManagerEmail || !validateEmail(data.informationManagerEmail)) {
        alert('유효한 정보책임자 E-mail을 입력해 주세요.');
        form.querySelector('input[name="informationManagerEmail"]').focus();
        return;
    }

    if (data.bankName && !data.bankAccountNumber) {
        alert('무통장 은행이 입력되었으므로, 은행계좌번호를 입력해 주세요.');
        form.querySelector('input[name="bankAccountNumber"]').focus();
        return;
    }

    if (data.bankAccountNumber && !/^\d+(-\d+)*$/.test(data.bankAccountNumber)) {
        alert('은행계좌번호는 숫자와 "-"만 포함할 수 있습니다.');
        form.querySelector('input[name="bankAccountNumber"]').focus();
        return;
    }

    if (data.minPoints === undefined || isNaN(data.minPoints) || parseInt(data.minPoints) < 1000) {
        alert('결제 최소 포인트는 1000 이상으로 입력해 주세요.');
        form.querySelector('input[name="minPoints"]').focus();
        return;
    }

    if (data.maxPoints === undefined || isNaN(data.maxPoints) || parseInt(data.maxPoints) <= parseInt(data.minPoints)) {
        alert('결제 최대 포인트는 결제 최소 포인트보다 커야 합니다.');
        form.querySelector('input[name="maxPoints"]').focus();
        return;
    }

    if (!data.deliveryCompany) {
        alert('배송업체명을 입력해 주세요.');
        form.querySelector('input[name="deliveryCompany"]').focus();
        return;
    }

    if (!data.deliveryFee || isNaN(data.deliveryFee)) {
        alert('배송비 가격은 숫자로 입력해 주세요.');
        form.querySelector('input[name="deliveryFee"]').focus();
        return;
    }

    // 폼 데이터를 서버에 전송합니다.
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
            window.location.reload(); // 페이지를 새로고쳐서 입력된 값을 반영합니다.
        } else {
            alert('설정 저장 실패: ' + responseData.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('설정 저장 중 오류 발생');
    });
}

// 이메일 유효성 검사 함수
function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}
