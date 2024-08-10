//검색
function searchProducts(event) {
    event.preventDefault(); // 폼의 기본 동작(페이지 리로드)을 막음

    var form = document.getElementById('searchForm');
    var formData = new FormData(form);

    fetch('/search_product', {
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(html => {
		document.getElementById('productList').innerHTML = html; 
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

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
//첨부파일 이미지 팝업
function openImagePopup(imagePath) {
    window.open(imagePath, 'popupImage', 'width=600,height=400,scrollbars=yes');
}

// 체크박스 핸들링
 const checkboxes = document.querySelectorAll('input[name="productCheckbox"]');
function selectAllItems(checkbox) {
    const isChecked = checkbox.checked;
    checkboxes.forEach(cb => cb.checked = isChecked);

    // 개별 체크박스에 이벤트 핸들러
    checkboxes.forEach(cb => {
        cb.addEventListener('change', updateSelectAllCheckbox);
    });
}
function updateSelectAllCheckbox() {
    const selectAllCheckbox = document.querySelector('input[name="selectAll"]');
    // 전체 체크박스 중 하나라도 체크되지 않은 경우, 전체 선택 체크박스를 해제
    const allChecked = Array.from(checkboxes).every(cb => cb.checked);
    selectAllCheckbox.checked = allChecked;
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
