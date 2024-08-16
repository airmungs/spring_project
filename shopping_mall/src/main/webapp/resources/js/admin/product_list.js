document.addEventListener('DOMContentLoaded', () => {
    const currentPage = parseInt(document.getElementById("currentPage").value, 10);
    const totalPages = parseInt(document.getElementById("totalPages").value, 10);
    updatePagingControls(currentPage, totalPages);
});

// 검색
function searchProducts(event) {
    event.preventDefault(); // 폼의 기본 동작(페이지 리로드)을 막음

    const form = document.getElementById('searchForm');
    const formData = new FormData(form);

    fetch('/search_product', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json()) // JSON 응답을 받음
    .then(data => {
        document.getElementById('productListContainer').innerHTML = data.html; 
        updatePagingControls(data.currentPage, data.totalPages);
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
        fetch('/delete_products', {
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
                searchProducts(new Event('searchProducts')); // 현재 페이지를 새로고침하여 삭제된 상품 반영
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

// 첨부파일 이미지 팝업
function openImagePopup(imagePath) {
    window.open(imagePath, 'popupImage', 'width=600,height=400,scrollbars=yes');
}

// 체크박스 핸들링
function selectAllItems(checkbox) {
    const isChecked = checkbox.checked;
    const checkboxes = document.querySelectorAll('input[name="productCheckbox"]');
    checkboxes.forEach(cb => cb.checked = isChecked);
}

// 개별 체크박스 상태에 따라 'selectAll' 체크박스 업데이트
function updateSelectAllCheckbox() {
    const selectAllCheckbox = document.querySelector('input[name="selectAll"]');
    const checkboxes = document.querySelectorAll('input[name="productCheckbox"]');
    const allChecked = Array.from(checkboxes).every(cb => cb.checked);
    selectAllCheckbox.checked = allChecked;
}

// 상품 관리
function editProduct(productId) {
    location.href = `product_edit.do?id=${productId}`;
}

// 페이지 이동
function goToPage(pageNumber) {
    const formData = new FormData();
    formData.append('page', pageNumber);

    fetch('/get_products_by_page', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json()) // 서버에서 JSON 응답을 받음
    .then(data => {
        document.getElementById('productListContainer').innerHTML = data.html; // HTML 업데이트
        updatePagingControls(data.currentPage, data.totalPages); // 페이징 업데이트
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

// 페이지 이동 버튼 핸들링
function updatePagingControls(currentPage, totalPages) {
    const leftArrow = document.getElementById("leftArrow");
    const rightArrow = document.getElementById("rightArrow");
    const goFirstPage = document.getElementById("goFirstPage");
    const goLastPage = document.getElementById("goLastPage");
    const paginationContainer = document.getElementById('pagination');
    
    // 화살표 버튼 상태 업데이트
    leftArrow.style.pointerEvents = (currentPage > 1) ? 'auto' : 'none';
    leftArrow.style.opacity = (currentPage > 1) ? '1' : '0.5';

    rightArrow.style.pointerEvents = (currentPage < totalPages) ? 'auto' : 'none';
    rightArrow.style.opacity = (currentPage < totalPages) ? '1' : '0.5';

    goFirstPage.style.pointerEvents = (currentPage > 1) ? 'auto' : 'none';
    goFirstPage.style.opacity = (currentPage > 1) ? '1' : '0.5';

    goLastPage.style.pointerEvents = (currentPage < totalPages) ? 'auto' : 'none';
    goLastPage.style.opacity = (currentPage < totalPages) ? '1' : '0.5';

    // 페이지 버튼 생성
    const pageItems = paginationContainer.querySelectorAll('.page-button');
    pageItems.forEach(item => item.remove()); // 기존 페이지 버튼 제거

    for (let i = 1; i <= totalPages; i++) {
        const pageItem = document.createElement('li');
        const pageLink = document.createElement('a');

        pageLink.href = '#';
        pageLink.textContent = i;
        pageLink.className = 'page-button';

        if (i === currentPage) {
            pageLink.classList.add('active'); // 현재 페이지에 'active' 클래스 추가
        }

        pageLink.addEventListener('click', function(event) {
            event.preventDefault(); // 기본 링크 동작을 막음
            goToPage(i);
        });

        pageItem.appendChild(pageLink);
        paginationContainer.insertBefore(pageItem, rightArrow); // 오른쪽 화살표 앞에 삽입
    }
}
