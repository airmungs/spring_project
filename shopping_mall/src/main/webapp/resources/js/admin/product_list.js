// 선택된 상품 삭제
function deleteSelectedItems() {
    const checkboxes = document.querySelectorAll('input[name="productCheckbox"]:checked');
    const idsToDelete = Array.from(checkboxes).map(cb => cb.value);

    if (idsToDelete.length === 0) {
        alert('삭제할 상품을 선택해 주세요.');
        return;
    }

    if (confirm('선택한 상품을 삭제하시겠습니까?')) {
        fetch('/deleteProducts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ ids: idsToDelete })
        })
        .then(response => response.json())
        .then(result => {
            if (result.success) {
                alert('상품이 삭제되었습니다.');
                window.location.reload();
            } else {
                alert('상품 삭제 실패: ' + result.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('상품 삭제 중 오류 발생');
        });
    }
}

// 전체 선택/해제
function selectAllItems(checkbox) {
    const isChecked = checkbox.checked;
    const checkboxes = document.querySelectorAll('input[name="productCheckbox"]');
    checkboxes.forEach(cb => cb.checked = isChecked);
}

// 페이지 이동
function goToPage(pageNumber) {
    // 페이지 이동 로직 구현
    // 예를 들어:
    location.href = `product_list.do?page=${pageNumber}`;
}

// 상품 관리
function editProduct(productId) {
    location.href = `product_edit.do?id=${productId}`;
}

// 검색 폼 제출
document.getElementById('searchForm').addEventListener('submit', function(event) {
    event.preventDefault(); // 기본 제출 동작을 막음

    const formData = new FormData(this);
    const params = new URLSearchParams(formData).toString();

    location.href = `product_search.do?${params}`;
});
