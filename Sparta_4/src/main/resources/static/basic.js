let targetId;

$(document).ready(function () {
    // id 가 query 인 녀석 위에서 엔터를 누르면 execSearch() 함수를 실행하라는 뜻입니다.
    $('#query').on('keypress', function (e) {
        if (e.key == 'Enter') {
            execSearch();
        }
    });
    $('#close').on('click', function () {
        $('#container').removeClass('active');
    })

    $('.nav div.nav-see').on('click', function () {
        $('div.nav-see').addClass('active');
        $('div.nav-search').removeClass('active');

        $('#see-area').show();
        $('#search-area').hide();
    })
    $('.nav div.nav-search').on('click', function () {
        $('div.nav-see').removeClass('active');
        $('div.nav-search').addClass('active');

        $('#see-area').hide();
        $('#search-area').show();
    })

    $('#see-area').show();
    $('#search-area').hide();

    showProduct();
})


function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

//////////////////////////////////////////////////////////////////////////////////////////
///// 여기 아래에서부터 코드를 작성합니다. ////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////

function execSearch() {
    /**
     * 검색어 input id: query
     * 검색결과 목록: #search-result-box
     * 검색결과 HTML 만드는 함수: addHTML
     */
    $('#search-result-box').empty();
    // 1. 검색창의 입력값을 가져온다.
    //변수를 하나 선언하고, 그 변수에 id가 "query"인 입력값을 가져오는 것은 .val()이다
    let query = $('#query').val();
    // 2. 검색창 입력값을 검사하고, 입력하지 않았을 경우 focus.
    if (query==''){ //만약 query가 빈 배열과 같다면,
        alert("검색어를 입력해주세요.");
        $('#query').focus(); //"여기 입력해라" 라고 주의를 집중시켜주기(테두리가 반짝반짝)
    }
    // 3. GET /api/search?query=${query} 요청
    //요청하는 방법은 jquery에서 .ajax
    $.ajax({
        type:'GET',
        url:`/api/serach?query=${query}`, //변수를 같이 넘겨줘야 하는데 query가 변숫값 query면 된다
        //문자열 중간에 변숫값을 넣고싶으면 백틱으로 감싸주고 ${}안에 변숫값을 넣는다
        //contentType과 data는 GET방식이 아닌 PUT,POST에서만 사용
        success: function(response) {//성공한 경우에 응답값이 response에 들어감(itemDto의 리스트가 들어가있음)
            for (let i=0;i<response.length;i++){
                let itemDto=response[i];
                //itemDto에 검색결과가 하나씩 들어가있을텐데, 그것을 HTML로 바꿔주는 함수가 addHTML
                let tempHtml = addHTML(itemDto); //결과값이 HTML로 돌아올 것이니 tempHtml에 담아주자
                //검색 결과 전체를 품고있는 div인 #search-result-box에 붙이자
                $('#search-result-box').append(tempHtml)

            }

        }
    })
    // 4. for 문마다 itemDto를 꺼내서 HTML 만들고 검색결과 목록에 붙이기!

}

function addHTML(itemDto) {
    /**
     * class="search-itemDto" 인 녀석 안에
     * image, title, lprice, addProduct 활용하기
     * 참고) onclick='addProduct(${JSON.stringify(itemDto)})'
     */
    return `<div className="search-itemDto">
        <div className="search-itemDto-left">
            <img src="${itemDto.image}" alt="">
        </div>
        <div className="search-itemDto-center">
            <div>${itemDto.title}</div>
            <div className="price">
                ${numberWithCommas(itemDto.lprice)}
                <span className="unit">원</span>
            </div>
        </div>
        <div className="search-itemDto-right">
            <img src="images/icon-save.png" alt="" onClick='addProduct(${JSON.stringify(itemDto)})'>
        </div>
    </div>`
}

function addProduct(itemDto) {
    //문자열화되어 넘어온 데이터(itemDto)가 자동으로 JSON으로 되어있다면?
    console.log(itemDto); //JSON 형태로 인쇄됨
    console.log(JSON.stringify(itemDto)); //문자열 형태로 인쇄됨
    //결론 : 자동으로 JSON화 되어있으니 다시 문자열 형태로 바꿔야 함
    /**
     * modal 뜨게 하는 법: $('#container').addClass('active');
     * data를 ajax로 전달할 때는 두 가지가 매우 중요
     * 1. contentType: "application/json",
     * 2. data: JSON.stringify(itemDto),
     */

    // 1. POST /api/products 에 관심 상품 생성 요청
    $.ajax({
        type:"POST",
        url:"/api/products",
        data:JSON.stringify(itemDto), //itemDto로 보낼 것인데 문자열화 해주기
        contentType:"application/json", //문자열이 갔으면 서버에서 이게 진짜 문자열인지 JSON인지 알아야 함
        success:function (response){
            console.log(response);
        }

    })
    // 2. 응답 함수에서 modal을 뜨게 하고, targetId 를 reponse.id 로 설정 (숙제로 myprice 설정하기 위함)
    $('#container').addClass('active'); //아이디가 container인 녀석을 찾아서 클래스에 'active'를 추가해준다
    targetId = response.id;
}

function showProduct() {
    /**
     * 관심상품 목록: #product-container
     * 검색결과 목록: #search-result-box
     * 관심상품 HTML 만드는 함수: addProductItem
     */
    // 1. GET /api/products 요청
    $.ajax({
        type: 'GET',
        url: '/api/products',
        success: function (response) {
            // 2. 관심상품 목록, 검색결과 목록 비우기
            $('#product-container').empty();
            $('#search-result-box').empty();
            // 3. for 문마다 관심 상품 HTML 만들어서 관심상품 목록에 붙이기!
            for (let i = 0; i < response.length; i++) {
                let product = response[i];
                let tempHtml = addProductItem(product);
                $('#product-container').append(tempHtml);
            }
        }
    })
}

function addProductItem(product) {
    // link, image, title, lprice, myprice 변수 활용하기
    return `<div id="product-container">
    <div class="product-card" onclick="window.location.href='${product.link}'"> 
<!--    //이 전체 요소를 눌렀을 때 어디로 가라-->

        <div class="card-header">
            <img src="${product.image}"
                 alt="">
        </div>
        <div class="card-body">
            <div class="title">
                ${product.title}
            </div>
            <div class="lprice">
                <span>${numberWithCommas(product.lprice)}</span>원
            </div>
            
            <div class="isgood ${product.lprice <= product.myprice ? '':'none'}">
             
        <!--   삼항 연산자 사용하기  -->
        <!--   (조건) ? (참일 경우 반환) : (거짓일 경우 반환);-->
<!--        참일 경우 isgood 클래스,  거짓일 경우 isgood none 클래스-->

                최저가
            </div>
        </div>
    </div>
</div>`;
}

function setMyprice() {
    /**
     * 숙제! myprice 값 설정하기.
     * 1. id가 myprice 인 input 태그에서 값을 가져온다.
     * 2. 만약 값을 입력하지 않았으면 alert를 띄우고 중단한다.
     * 3. PUT /api/product/${targetId} 에 data를 전달한다.
     *    주의) contentType: "application/json",
     *         data: JSON.stringify({myprice: myprice}),
     *         빠뜨리지 말 것!
     * 4. 모달을 종료한다. $('#container').removeClass('active');
     * 5, 성공적으로 등록되었음을 알리는 alert를 띄운다.
     * 6. 창을 새로고침한다. window.location.reload();
     */
}