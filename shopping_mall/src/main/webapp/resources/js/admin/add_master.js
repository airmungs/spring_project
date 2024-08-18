var form = document.getElementById("admin_form");
var isIdAvailable = false;  // 중복 체크 결과를 저장할 변수

// 중복 체크 버튼 클릭 핸들러
document.getElementById("id_check").addEventListener("click", function() {
    var adid = form.querySelector('input[name="adid"]').value.trim();
    if (!adid) {
        alert("아이디를 입력하세요.");
        form.querySelector('input[name="adid"]').focus();
        return;
    }

    fetch(`./idcheck?adid=${encodeURIComponent(adid)}`, {
        headers: {
            'Content-Type': 'text/plain; charset=UTF-8',
			'X-Requested-With': 'XMLHttpRequest'
        }
    })
    .then(response => {
        if (response.ok) {
            return response.text();
        } else if (response.status === 409) {
            return response.text().then(data => { throw new Error(data); });
        } else {
            throw new Error('서버에서 오류가 발생했습니다.');
        }
    })
    .then(data => {
        alert(data);
        isIdAvailable = data === "사용 가능한 아이디입니다.";  // 아이디 사용 가능 여부 설정
    })
    .catch(error => {
        alert(error.message);
    });
});

document.querySelector('input[name="adid"]').addEventListener("input", function() {
    isIdAvailable = false;
});

//핸드폰번호 maxlength
function limitInputLength(inputElement, maxLength) {
    inputElement.addEventListener('input', function() {
        const value = inputElement.value;
        if (value.length > maxLength) {
            inputElement.value = value.slice(0, maxLength);
        }
    });
}

// 회원가입 버튼 클릭 핸들러
document.getElementById("join_admin").addEventListener("click", function() {
    if (!isIdAvailable) {
        alert("아이디 중복 체크를 완료하고, 사용 가능한 아이디인지 확인하세요.");
        return;
    }

    var adid = form.querySelector('input[name="adid"]').value.trim();
    if (!adid) {
        alert("아이디를 입력하세요.");
        form.querySelector('input[name="adid"]').focus();
        return;
    }

    var adpass = form.querySelector('input[name="adpass"]').value;
    if (!adpass) {
        alert("패스워드를 입력하세요.");
        form.querySelector('input[name="adpass"]').focus();
        return;
    }

    var adpassConfirm = form.querySelector('input[name="adpass_confirm"]').value;
    if (adpass !== adpassConfirm) {
        alert("패스워드가 일치하지 않습니다.");
        form.querySelector('input[name="adpass_confirm"]').focus();
        return; 
    }

    var adname = form.querySelector('input[name="adname"]').value.trim();
    if (!adname) {
        alert("담당자 이름을 입력하세요.");
        form.querySelector('input[name="adname"]').focus();
        return;
    }

    var ademail = form.querySelector('input[name="ademail"]').value.trim();
    if (!ademail) {
        alert("담당자 이메일을 입력하세요.");
        form.querySelector('input[name="ademail"]').focus();
        return;
    }

    var hp1 = form.querySelector('input[name="hp1"]').value.trim();
    var hp2 = form.querySelector('input[name="hp2"]').value.trim();
    var hp3 = form.querySelector('input[name="hp3"]').value.trim();
    if (!hp1 || !hp2 || !hp3) {
        alert("휴대폰 번호를 올바르게 입력하세요.");
        if (!hp1) {
            form.querySelector('input[name="hp1"]').focus();
        } else if (!hp2) {
            form.querySelector('input[name="hp2"]').focus();
        } else {
            form.querySelector('input[name="hp3"]').focus();
        }
        return;
    }
	var isNumber = /^[0-9]+$/;
    if (!isNumber.test(hp1) || !isNumber.test(hp2) || !isNumber.test(hp3)) {
        alert("휴대폰 번호는 숫자만 입력 가능합니다.");
        return;
    }

    var phoneNumber = `${hp1}${hp2}${hp3}`;

    var adpart = form.querySelector('select[name="adpart"]').value;
    if (!adpart) {
        alert("담당자 부서를 선택하세요.");
        form.querySelector('select[name="adpart"]').focus();
        return;
    }

    var adposition = form.querySelector('select[name="adposition"]').value;
    if (!adposition) {
        alert("담당자 직책을 선택하세요.");
        form.querySelector('select[name="adposition"]').focus();
        return;
    }

    var formData = new FormData(form);
    var jsonData = {};
    formData.forEach((value, key) => {
        if (key === 'hp1' || key === 'hp2' || key === 'hp3') {
            return;
        }
        jsonData[key] = value;
    });
    jsonData['adtel'] = phoneNumber;
    var jsonString = JSON.stringify(jsonData);
    
    fetch('./add_masterok', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
			'X-Requested-With': 'XMLHttpRequest'
        },
        body: jsonString
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('관리자 등록 요청이 완료되었습니다. 전산 담당자 승인 후 로그인 할 수 있습니다.');
            location.href = "./";
            isIdAvailable = false;
        } else {
            alert('관리자 등록 실패: ' + data.message);
        }
    })
    .catch(error => {
        alert('서버 오류 발생: ' + error);
    });
});
