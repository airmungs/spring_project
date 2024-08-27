document.addEventListener('DOMContentLoaded', () => {
    const currentPageElement = document.getElementById("currentPage");
    const totalPagesElement = document.getElementById("totalPages");

    if (currentPageElement && totalPagesElement) {
        const currentPage = parseInt(currentPageElement.value, 10);
        const totalPages = parseInt(totalPagesElement.value, 10);
        updatePaginationControls(currentPage, totalPages);
        createPageButtons(currentPage, totalPages);
    }

    searchProducts(new Event('searchProducts')); // 페이지가 로드된 직후 검색 실행
    
    const selectAllCheckbox = document.querySelector('input[name="selectAll"]');
    const productCheckboxes = document.querySelectorAll('input[name="productCheckbox"]');

    if (selectAllCheckbox) {
        selectAllCheckbox.addEventListener('change', () => selectAllItems(selectAllCheckbox));
    }

    productCheckboxes.forEach(checkbox => {
        checkbox.addEventListener('change', updateSelectAllCheckbox);
    });
});

function goToPage(pageNumber) {
    const totalPagesElement = document.getElementById('totalPages');
    if (!totalPagesElement) {
        console.error('총 페이지 수를 나타내는 요소를 찾을 수 없습니다.');
        return;
    }
    
    const totalPages = parseInt(totalPagesElement.value, 10);
    if (isNaN(totalPages)) {
        console.error('총 페이지 수가 올바르지 않습니다.');
        return;
    }

    if (pageNumber < 1 || pageNumber > totalPages) {
        console.warn('유효하지 않은 페이지 번호:', pageNumber);
        return;
    }

    const searchType = document.querySelector('#searchType') ? document.querySelector('#searchType').value : '';
    const searchKeyword = document.querySelector('#searchKeyword') ? document.querySelector('#searchKeyword').value : '';

    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/get_products_by_page', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('productItems').innerHTML = xhr.responseText;
            document.getElementById('currentPage').value = pageNumber;
            const totalPages = parseInt(document.getElementById('totalPages').value, 10);
            updatePaginationControls(pageNumber, totalPages);
            createPageButtons(pageNumber, totalPages);
            
            const productCheckboxes = document.querySelectorAll('input[name="productCheckbox"]');
            productCheckboxes.forEach(checkbox => {
                checkbox.addEventListener('change', updateSelectAllCheckbox);
            });
        }
    };
    xhr.send(`page=${pageNumber}&searchType=${encodeURIComponent(searchType)}&searchKeyword=${encodeURIComponent(searchKeyword)}`);
}

function updatePaginationControls(currentPage, totalPages) {
    const leftArrow = document.getElementById('leftArrow');
    const rightArrow = document.getElementById('rightArrow');

    if (leftArrow && rightArrow) {
        leftArrow.style.display = currentPage <= 1 ? 'none' : 'inline-block';
        rightArrow.style.display = currentPage >= totalPages ? 'none' : 'inline-block';
    }
}

function createPageButtons(currentPage, totalPages) {
    const pageButtonsDiv = document.getElementById('pageButtons');
    if (!pageButtonsDiv) return;
    
    pageButtonsDiv.innerHTML = ''; // 기존 버튼 제거
    
    const maxButtons = 5; // 화면에 표시할 최대 버튼 수
    let startPage = Math.max(1, currentPage - Math.floor(maxButtons / 2));
    let endPage = Math.min(totalPages, startPage + maxButtons - 1);

    if (endPage - startPage < maxButtons - 1) {
        startPage = Math.max(1, endPage - maxButtons + 1);
    }

    for (let i = startPage; i <= endPage; i++) {
        const button = document.createElement('li');
        button.innerHTML = `<a href="javascript:void(0);" onclick="goToPage(${i})">${i}</a>`;
        if (i === currentPage) {
            button.classList.add('active'); // 현재 페이지 버튼 강조
        }
        pageButtonsDiv.appendChild(button);
    }
}

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
                
                let currentPage = parseInt(document.getElementById("currentPage").value, 10);
                const totalPages = parseInt(document.getElementById("totalPages").value, 10);

                if (currentPage > 1 && currentPage > totalPages) {
                    currentPage -= 1;
                }

                goToPage(currentPage);
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

function openImagePopup(imagePath) {
    window.open(imagePath, 'popupImage', 'width=600,height=400,scrollbars=yes');
}

function selectAllItems(checkbox) {
    const isChecked = checkbox.checked;
    const checkboxes = document.querySelectorAll('input[name="productCheckbox"]');
    checkboxes.forEach(cb => cb.checked = isChecked);
}

function updateSelectAllCheckbox() {
    const selectAllCheckbox = document.querySelector('input[name="selectAll"]');
    const checkboxes = document.querySelectorAll('input[name="productCheckbox"]');
    const allChecked = Array.from(checkboxes).every(cb => cb.checked);
    selectAllCheckbox.checked = allChecked;
    selectAllCheckbox.indeterminate = !allChecked && Array.from(checkboxes).some(cb => cb.checked);
}

function editProduct(productId) {
    location.href = `product_edit.do?id=${productId}`;
}

function searchProducts(event) {
    event.preventDefault(); // 폼의 기본 동작(페이지 리로드)을 막음

    const form = document.getElementById('searchForm');
    const formData = new FormData(form);

    formData.append('page', '1');

    fetch('/search_product', {
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(html => {
        document.getElementById('productList').innerHTML = html;

        const currentPageElement = document.getElementById("currentPage");
        const totalPagesElement = document.getElementById("totalPages");

        if (currentPageElement && totalPagesElement) {
            const currentPage = parseInt(currentPageElement.value, 10);
            const totalPages = parseInt(totalPagesElement.value, 10);
            updatePaginationControls(currentPage, totalPages);
            createPageButtons(currentPage, totalPages);

            const productCheckboxes = document.querySelectorAll('input[name="productCheckbox"]');
            productCheckboxes.forEach(checkbox => {
                checkbox.addEventListener('change', updateSelectAllCheckbox);
            });
        } else {
            console.warn('currentPage 또는 totalPages 요소를 찾을 수 없습니다.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
