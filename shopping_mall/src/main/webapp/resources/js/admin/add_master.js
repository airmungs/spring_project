var form = document.getElementById("admin_form");
document.getElementById("id_check").addEventListener("click",function(){
    var adid = form.querySelector('input[name="adid"]').value.trim();
    if (!adid) {
        alert("아이디를 입력하세요.");
        form.querySelector('input[name="adid"]').focus();
        return;
    }

	fetch(`/idcheck?adid=${encodeURIComponent(adid)}`)
	       .then(response => {
	           if (!response.ok) {
	               // 서버 오류 처리
	               throw new Error('서버에서 오류가 발생했습니다.');
	           }
	           return response.text();
	       })
	       .then(data => {
	           alert(data);
	       })
	       .catch(error => {
	           alert('서버 오류 발생: ' + error.message);
	       });
	
});




document.getElementById("join_admin").addEventListener("click", function() {
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
            alert('관리자 등록 요청이 완료되었습니다. 전산 담당자 승인 후 로그인 할 수 있습니다.');
            form.reset();  // 폼 리셋
        } else {
            alert('관리자 등록 실패: ' + data.message);
        }
    })
    .catch(error => {
        alert('서버 오류 발생: ' + error);
    });
});
