document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("joinForm");
    const btnJoin = document.getElementById("clientJoin");
var isIdAvailable = false;  // 중복 체크 결과를 저장할 변수

// 중복 체크 버튼 클릭 핸들러
document.getElementById("idCheck").addEventListener("click", function() {
    var userId = form.querySelector('input[name="userId"]').value.trim();
    if (!userId) {
        alert("아이디를 입력하세요.");
        form.querySelector('input[name="userId"]').focus();
        return;
    }

    fetch(`./idcheck?userId=${encodeURIComponent(userId)}`, {
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

document.querySelector('input[name="userId"]').addEventListener("input", function() {
    isIdAvailable = false;
});

    btnJoin.addEventListener("click", function() {
        // 각 필드의 값과 이름을 가져옵니다.
        const userId = form.userId;
        const password = form.password;
        const passwordConfirm = form.passwordConfirm;
        const name = form.name;
        const email = form.email;
        const authCode = form.authCode;
        const phone = form.phone;

        if (!isIdAvailable) {
        	alert("아이디 중복 체크를 완료하고, 사용 가능한 아이디인지 확인하세요.");
        	return;
    	}
        if (userId.value.trim() === "") {
            alert("아이디를 입력하세요.");
            userId.focus();
            return;
        }
        if (password.value.trim() === "") {
            alert("비밀번호를 입력하세요.");
            password.focus();
            return;
        }
        if (password.value.length < 6) {
            alert("비밀번호는 최소 6자 이상이어야 합니다.");
            password.focus();
            return;
        }
        if (passwordConfirm.value.trim() === "") {
            alert("비밀번호 확인을 입력하세요.");
            passwordConfirm.focus();
            return;
        }
        if (password.value !== passwordConfirm.value) {
            alert("비밀번호가 일치하지 않습니다.");
            passwordConfirm.focus();
            return;
        }
        if (name.value.trim() === "") {
            alert("이름을 입력하세요.");
            name.focus();
            return;
        }
        if (email.value.trim() === "") {
            alert("이메일을 입력하세요.");
            email.focus();
            return;
        }
        if (authCode.value.trim() === "") {
            alert("인증번호를 입력하세요.");
            authCode.focus();
            return;
        }
        if (phone.value.trim() === "") {
            alert("전화번호를 입력하세요.");
            phone.focus();
            return;
        }

		const formData = new FormData(form);
		const json = {};
        formData.forEach((value, key) => {
            if (key === "eventMail" || key === "eventSms") {
                json[key] = value === "on";
            } else {
                json[key] = value;
            }
        });
		fetch("./joinOk",{
			method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(json)
        })
        .then(response => response.json())  // JSON 형태의 응답을 받기 위해 파싱
        .then(data => {
            if (data.success) {
                alert("회원가입이 완료되었습니다.");
				location.href="./login";
            } else {
                alert("회원가입에 실패했습니다: " + data.message);
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("회원가입 중 오류가 발생했습니다.");
        });
    });
});
