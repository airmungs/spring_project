document.getElementById("adminlogin_form").addEventListener("submit", function(event){
    event.preventDefault(); // 폼의 기본 제출 동작을 막기
            
    var form = event.target;
	var adid = form.querySelector('input[name="adid"]').value.trim();
	var adpass= form.querySelector('input[name="adpass"]').value.trim();
   if (!adid || !adpass) {
        alert("아이디와 비밀번호를 입력하세요.");
        return;
    }

    var loginData = {
        adid: adid,
        adpass: adpass
    };

    fetch('./login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
			'X-Requested-With': 'XMLHttpRequest'
        },
        body: JSON.stringify(loginData)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            // adname을 확인하여 리디렉션할 URL을 결정
            if (data.admin.adname === "최고관리자") {
                location.href = "./admin_list.do";
            } else {
                location.href = "./shop_member_list.do";
            }
        } else {
            alert(data.message);
        }
    })
    .catch(error => {
        alert('서버 오류 발생: ' + error);
    });
});


