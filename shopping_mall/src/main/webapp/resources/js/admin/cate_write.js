document.addEventListener('DOMContentLoaded', function() {
    const lgMenuInput = document.querySelector('.cate_input2');
    const cateCodeInput = document.querySelector('.cate_input1');
    const cateNameInput = document.querySelector('.cate_input3');
    const useRadio = document.querySelector('input[name="useYn"]:checked');

    document.querySelector('input[value="카테고리 생성"]').addEventListener('click', function() {
		const cateCode = cateCodeInput.value.trim();
        const lgMenuCode = lgMenuInput.value.trim();
        const cateName = cateNameInput.value.trim();
        const useYn = useRadio.value;
		
		if (cateCode.length < 2) {
            alert("분류 코드를 최소 2자 이상 입력하세요.");
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
			categoryCode: cateCode,
            lgMenuCode: lgMenuCode,
            cateName: cateName,
            useYn: useYn
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
                location.href = 'cate_list.do';
            } else {
                alert(data.message || '');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('카테고리 생성 중 오류가 발생했습니다.');
        });
    });
});
