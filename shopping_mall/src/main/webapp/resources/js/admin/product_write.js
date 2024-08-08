// 상품 등록 폼 제출 함수
function submitProduct() {
    const form = document.getElementById('submitProductForm');
    const formData = new FormData(form);
    const data = {};

    formData.forEach((value, key) => {
        data[key] = value;
    });

    // 입력값 유효성 검사
    if (!data.productCode) {
        // 상품코드가 비어 있으면 자동 생성
        generateProductCode();
        data.productCode = document.getElementById('productCode').value;
    }

    if (!data.productCode) {
        alert('상품코드를 입력해 주세요.');
        form.querySelector('input[name="productCode"]').focus();
        return;
    }

    if (!data.productName) {
        alert('상품명을 입력해 주세요.');
        form.querySelector('input[name="productName"]').focus();
        return;
    }

    if (!data.salePrice || isNaN(data.salePrice)) {
        alert('판매가격을 숫자로 입력해 주세요.');
        form.querySelector('input[name="salePrice"]').focus();
        return;
    }

    if (!data.discountRate || isNaN(data.discountRate)) {
        alert('할인율을 숫자로 입력해 주세요.');
        form.querySelector('input[name="discountRate"]').focus();
        return;
    }

    if (!data.stockQuantity || isNaN(data.stockQuantity)) {
        alert('상품재고를 숫자로 입력해 주세요.');
        form.querySelector('input[name="stockQuantity"]').focus();
        return;
    }

    // 할인가격 자동 계산 및 검증
    if (data.discountRate && !data.discountedPrice) {
        calculateDiscountedPrice();
    }

    // 폼 데이터를 서버에 전송합니다.
    fetch('/save_product', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(responseData => {
        if (responseData.success) {
            alert('상품 등록 완료');
            window.location.reload(); // 페이지를 새로고쳐서 입력된 값을 반영합니다.
        } else {
            alert('상품 등록 실패: ' + responseData.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('상품 등록 중 오류 발생');
    });
}

// 상품코드 자동 생성
function generateProductCode() {
    const productCodeField = document.getElementById('productCode');
    const randomCode = Math.floor(1000000 + Math.random() * 9000000); // 7자리 난수 생성
    productCodeField.value = randomCode;
}

// 할인율에 따라 할인가격 자동 계산
function calculateDiscountedPrice() {
    const salePrice = parseFloat(document.getElementById('salePrice').value.replace(/,/g, '')) || 0;
    const discountRate = parseFloat(document.getElementById('discountRate').value) || 0;
    const discountedPrice = salePrice * (1 - (discountRate / 100));
    document.getElementById('discountedPrice').value = Math.round(discountedPrice);
}
