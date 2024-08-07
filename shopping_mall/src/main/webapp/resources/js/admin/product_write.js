document.addEventListener('DOMContentLoaded', function() {
    // 상품코드 자동 생성
    generateProductCode();

    // 할인율 입력 시 할인가격 자동 계산
    document.getElementById('discountRate').addEventListener('input', function() {
        calculateDiscountedPrice();
    });

    // 판매가격 입력 시 할인가격 자동 계산
    document.getElementById('salePrice').addEventListener('input', function() {
        calculateDiscountedPrice();
    });
});

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
    document.getElementById('discountedPrice').value =  Math.round(discountedPrice);
}

// 상품코드 중복 확인 함수 (서버와 연동 필요)
function checkProductCode() {
    const productCode = document.getElementById('productCode').value;

    if (!productCode) {
        alert('상품코드를 입력해주세요.');
        return;
    }

    fetch(`/checkProductCode?code=${productCode}`)
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                alert('이 상품코드는 이미 존재합니다.');
            } else {
                alert('이 상품코드는 사용 가능합니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('상품코드 중복 확인 중 오류가 발생했습니다.');
        });
}

// 상품 등록 폼 제출 함수
function submitProduct() {
    // 폼 제출 로직을 여기에 추가합니다.
    alert('상품 등록 기능은 추가 구현이 필요합니다.');
}
