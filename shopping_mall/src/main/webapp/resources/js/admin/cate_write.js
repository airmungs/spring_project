document.addEventListener('DOMContentLoaded', function() {
    let isCategoryCodeAvailable = false;

    const lgMenuInput = document.querySelector('.cate_input2');
    const cateCodeInput = document.querySelector('.cate_input1');
    const cateNameInput = document.querySelector('.cate_input3');
    const cateCodeMessage = document.getElementById('cateCodeMessage');

    // 분류 코드 입력 시 중복성 검사
    cateCodeInput.addEventListener('input', function() {
        const cateCode = cateCodeInput.value.trim();
        if (cateCode.length < 2) {
            cateCodeMessage.textContent = '분류 코드는 2자 이상이어야 합니다.';
            cateCodeMessage.className = 'message error';
            isCategoryCodeAvailable = false;
        } else {
			checkCategoryCode(cateCode);
        }
    });	

    // 카테고리 생성
    document.querySelector('input[value="카테고리 생성"]').addEventListener('click', function() {
        const cateCode = cateCodeInput.value.trim();
        const lgMenuCode = lgMenuInput.value.trim();
        const cateName = cateNameInput.value.trim();
		
        if (cateCode.length < 2) {
            alert("분류 코드를 최소 2자 이상 입력하세요.");
            cateCodeInput.focus();
            return;
        }

		if (!isCategoryCodeAvailable) {
            alert("중복되지 않는 분류코드를 입력하세요.");
			cateCodeInput.focus();
            return;
        }		

        if (lgMenuCode.length < 2) {
            alert("대메뉴 코드를 최소 2자 이상 입력하세요.");
            lgMenuInput.focus();
            return;
        }

        if (cateName.length === 0) {
            alert("대메뉴명을 입력하세요.");
            cateNameInput.focus();
            return;
        }

        const data = {
            category_code: cateCode,
            lg_menu_code: lgMenuCode,
            cate_name: cateName,
            use_yn: useYn
        };

        fetch('./create_cate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('카테고리가 성공적으로 생성되었습니다.');
                location.href = 'cate_list';
            } else {
                alert(data.message || '');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('카테고리 생성 중 오류가 발생했습니다.');
        });
    });

    // 카테고리 코드 중복 체크 함수
    function checkCategoryCode(cateCode) {
        fetch('./checkCategoryCode', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ categoryCode: cateCode })
        })
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                cateCodeMessage.textContent = '중복되는 분류 코드입니다.';
                cateCodeMessage.className = 'message error';
                isCategoryCodeAvailable = false;
            } else {
                cateCodeMessage.textContent = '사용 가능한 분류 코드입니다.';
                cateCodeMessage.className = 'message success';
                isCategoryCodeAvailable = true;
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('중복 체크 중 오류가 발생했습니다.');
            cateCodeMessage.textContent = '';
            cateCodeMessage.className = 'message error';
            isCategoryCodeAvailable = false;
        });
    }
});
