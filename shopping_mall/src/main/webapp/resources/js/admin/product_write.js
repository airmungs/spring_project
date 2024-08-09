document.addEventListener('DOMContentLoaded', function() {
    // 페이지 로드 시 상품코드 자동 생성
    generateProductCode();

    // 할인율 또는 판매가격 입력 시 할인가격 자동 계산
    document.getElementById('discountRate').addEventListener('input', calculateDiscountedPrice);
    document.getElementById('salePrice').addEventListener('input', calculateDiscountedPrice);
});

// 상품코드 자동 생성
function generateProductCode() {
    const productCodeField = document.getElementById('productCode');
    if (productCodeField) {
        const randomCode = Math.floor(1000000 + Math.random() * 9000000); // 7자리 난수 생성
        productCodeField.value = randomCode;
    } else {
        console.error('상품 코드 입력 필드를 찾을 수 없습니다.');
    }
}

// 할인율에 따라 할인가격 자동 계산
function calculateDiscountedPrice() {
    const salePriceField = document.getElementById('salePrice');
    const discountRateField = document.getElementById('discountRate');
    const discountedPriceField = document.getElementById('discountedPrice');

    if (salePriceField && discountRateField && discountedPriceField) {
        const salePrice = parseFloat(salePriceField.value.replace(/,/g, '')) || 0;
        const discountRate = parseFloat(discountRateField.value) || 0;
        const discountedPrice = discountRate > 0 ? salePrice * (1 - (discountRate / 100)) : salePrice;
        discountedPriceField.value = Math.round(discountedPrice);
    } else {
        console.error('할인율, 판매가격 또는 할인가격 필드를 찾을 수 없습니다.');
    }
}

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
    if (formData.get('discountRate') && !formData.get('discountedPrice')) {
        calculateDiscountedPrice();
        formData.set('discountedPrice', document.getElementById('discountedPrice').value);
    }

    // 폼 데이터를 서버에 전송합니다.
     fetch('./save_product', {
        method: 'POST',
        body: formData
    })
    .then(response => {
	    if (response.ok) {
	        return response.json(); // JSON 응답을 처리
	    }
	    throw new Error('통신오류');
	})
    .then(responseData => {
        if (responseData.success) {
            alert('상품 등록 완료');
            location.href = "./product_list.do";
        } else {
            alert('상품 등록 실패: ' + responseData.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('상품 등록 중 오류 발생');
    });
}
