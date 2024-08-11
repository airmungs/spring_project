//검색
function searchCategories(event) {
    event.preventDefault(); // 폼의 기본 동작(페이지 리로드)을 막음

    var form = document.getElementById('searchForm');
    var formData = new FormData(form);

    fetch('/search_category', {
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(html => {
		document.getElementById('cateList').innerHTML = html; 
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

document.addEventListener('DOMContentLoaded', function() {
    // 카테고리 삭제
    document.querySelector('input[value="카테고리 삭제"]').addEventListener('click', function() {
        const checkboxes = document.querySelectorAll('.subpage_view2 .cateCheckbox');
        const selectedCategories = [];

        checkboxes.forEach((checkbox, index) => {
            if (checkbox.checked) {
                selectedCategories.push(index); // 또는 특정 데이터 속성 수집
            }
        });

        if (selectedCategories.length > 0) {
            if (confirm("선택한 카테고리를 삭제하시겠습니까?")) {
                // TODO: 삭제 로직 구현
                console.log(`Deleting categories: ${selectedCategories.join(', ')}`);
            }
        } else {
            alert("삭제할 카테고리를 선택해 주세요");
        }
    });
});

// 페이지 이동 로직 구현
function goToPage(pageNumber) {
    location.href = `product_list.do?page=${pageNumber}`;
}

// 체크박스 핸들링
function selectAllItems(checkbox) {
    const isChecked = checkbox.checked;
    const checkboxes = document.querySelectorAll('.cateCheckbox');
    checkboxes.forEach(cb => cb.checked = isChecked);

    // 개별 체크박스에 이벤트 핸들러
    checkboxes.forEach(cb => {
        cb.addEventListener('change', updateSelectAllCheckbox);
    });
}

// 개별 체크박스 상태에 따라 전체 선택 체크박스 업데이트
function updateSelectAllCheckbox() {
    const checkboxes = document.querySelectorAll('.cateCheckbox');
    const selectAllCheckbox = document.getElementById('selectAll');

    const allChecked = Array.from(checkboxes).every(cb => cb.checked);
    selectAllCheckbox.checked = allChecked;
}