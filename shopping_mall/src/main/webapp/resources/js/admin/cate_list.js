document.addEventListener('DOMContentLoaded', function() {
    // 카테고리 검색
    document.querySelector('.p_submit').addEventListener('click', function(event) {
        event.preventDefault();
        const searchCategory = document.querySelector('.p_select1').value;
        const searchKeyword = document.querySelector('.p_input1').value.trim();
                
        if (searchKeyword) {
            // TODO: 검색 로직 구현
            console.log(`Search by ${searchCategory} with keyword: ${searchKeyword}`);
        } else {
            alert("검색어를 입력해 주세요");
        }
    });

    // 전체 선택/해제
    document.querySelector('#selectAll').addEventListener('change', function(event) {
        const checkboxes = document.querySelectorAll('.cateCheckbox');
        checkboxes.forEach(checkbox => {
            checkbox.checked = event.target.checked;
        });
    });

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