
document.getElementById("join_admin").addEventListener("click", function() {
    var form = document.getElementById("admin_form");
    var adpass = form.querySelector('input[name="adpass"]').value;
    var adpassConfirm = form.querySelector('input[name="adpass_confirm"]').value;
    if (adpass !== adpassConfirm) {
        alert("패스워드가 일치하지 않습니다.");
        return; // 요청 전송을 중지합니다.
    }
    // 전화번호 필드 가져오기
    var hp1 = form.querySelector('input[name="hp1"]').value;
    var hp2 = form.querySelector('input[name="hp2"]').value;
    var hp3 = form.querySelector('input[name="hp3"]').value;
    var phoneNumber = `${hp1}${hp2}${hp3}`;

    // FormData 객체 생성
    var formData = new FormData(form);

    // FormData를 JSON으로 변환
    var jsonData = {};
    formData.forEach((value, key) => {
        // 전화번호 필드를 처리
        if (key === 'hp1' || key === 'hp2' || key === 'hp3') {
            return; // 기존 필드를 무시
        }
        jsonData[key] = value;
    });

    // 전화번호를 jsonData에 추가
    jsonData['adtel'] = phoneNumber;

    // JSON 문자열로 변환
    var jsonString = JSON.stringify(jsonData);

    // POST 요청 보내기
    fetch('./add_masterok.do', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: jsonString
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('관리자 등록 성공');
            form.reset();  // 폼 리셋
        } else {
            alert('관리자 등록 실패: ' + data.message);
        }
    })
    .catch(error => {
        alert('서버 오류 발생: ' + error);
    });
});

