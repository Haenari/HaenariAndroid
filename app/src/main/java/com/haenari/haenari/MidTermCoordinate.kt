package com.haenari.haenari

// todo replace class
data class MidTermWeatherCoordinate(val region: String, val code: String)

val midTermLandWeatherCoordinate = listOf(
    MidTermWeatherCoordinate("서울","11B00000"),
    MidTermWeatherCoordinate("인천","11B00000"),
    MidTermWeatherCoordinate("경기도","11B00000"),
    MidTermWeatherCoordinate("강원도","11D10000"),
    MidTermWeatherCoordinate("강원도영서","11D10000"),
    MidTermWeatherCoordinate("강원도영동","11D20000"),
    MidTermWeatherCoordinate("대전","11C20000"),
    MidTermWeatherCoordinate("세종","11C20000"),
    MidTermWeatherCoordinate("충청남도","11C20000"),
    MidTermWeatherCoordinate("충청북도","11C10000"),
    MidTermWeatherCoordinate("광주","11F20000"),
    MidTermWeatherCoordinate("전라남도","11F20000"),
    MidTermWeatherCoordinate("전라북도","11F10000"),
    MidTermWeatherCoordinate("대구","11H10000"),
    MidTermWeatherCoordinate("경상북도","11H10000"),
    MidTermWeatherCoordinate("부산","11H20000"),
    MidTermWeatherCoordinate("울산","11H20000"),
    MidTermWeatherCoordinate("경상남도","11H20000"),
    MidTermWeatherCoordinate("제주도","11G00000"),
)

val midTermTemperatureCoordinates = listOf(
    MidTermWeatherCoordinate("백령도", "11A00101"),
    MidTermWeatherCoordinate("서울", "11B10101"),
    MidTermWeatherCoordinate("과천", "11B10102"),
    MidTermWeatherCoordinate("광명", "11B10103"),
    MidTermWeatherCoordinate("강화", "11B20101"),
    MidTermWeatherCoordinate("김포", "11B20102"),
    MidTermWeatherCoordinate("인천", "11B20201"),
    MidTermWeatherCoordinate("시흥", "11B20202"),
    MidTermWeatherCoordinate("안산", "11B20203"),
    MidTermWeatherCoordinate("부천", "11B20204"),
    MidTermWeatherCoordinate("의정부", "11B20301"),
    MidTermWeatherCoordinate("고양", "11B20302"),
    MidTermWeatherCoordinate("양주", "11B20304"),
    MidTermWeatherCoordinate("파주", "11B20305"),
    MidTermWeatherCoordinate("동두천", "11B20401"),
    MidTermWeatherCoordinate("연천", "11B20402"),
    MidTermWeatherCoordinate("포천", "11B20403"),
    MidTermWeatherCoordinate("가평", "11B20404"),
    MidTermWeatherCoordinate("구리", "11B20501"),
    MidTermWeatherCoordinate("남양주", "11B20502"),
    MidTermWeatherCoordinate("양평", "11B20503"),
    MidTermWeatherCoordinate("하남", "11B20504"),
    MidTermWeatherCoordinate("수원", "11B20601"),
    MidTermWeatherCoordinate("안양", "11B20602"),
    MidTermWeatherCoordinate("오산", "11B20603"),
    MidTermWeatherCoordinate("화성", "11B20604"),
    MidTermWeatherCoordinate("성남", "11B20605"),
    MidTermWeatherCoordinate("평택", "11B20606"),
    MidTermWeatherCoordinate("의왕", "11B20609"),
    MidTermWeatherCoordinate("군포", "11B20610"),
    MidTermWeatherCoordinate("안성", "11B20611"),
    MidTermWeatherCoordinate("용인", "11B20612"),
    MidTermWeatherCoordinate("이천", "11B20701"),
    MidTermWeatherCoordinate("광주", "11B20702"),
    MidTermWeatherCoordinate("여주", "11B20703"),
    MidTermWeatherCoordinate("충주", "11C10101"),
    MidTermWeatherCoordinate("진천", "11C10102"),
    MidTermWeatherCoordinate("음성", "11C10103"),
    MidTermWeatherCoordinate("제천", "11C10201"),
    MidTermWeatherCoordinate("단양", "11C10202"),
    MidTermWeatherCoordinate("청주", "11C10301"),
    MidTermWeatherCoordinate("보은", "11C10302"),
    MidTermWeatherCoordinate("괴산", "11C10303"),
    MidTermWeatherCoordinate("증평", "11C10304"),
    MidTermWeatherCoordinate("추풍령", "11C10401"),
    MidTermWeatherCoordinate("영동", "11C10402"),
    MidTermWeatherCoordinate("옥천", "11C10403"),
    MidTermWeatherCoordinate("서산", "11C20101"),
    MidTermWeatherCoordinate("태안", "11C20102"),
    MidTermWeatherCoordinate("당진", "11C20103"),
    MidTermWeatherCoordinate("홍성", "11C20104"),
    MidTermWeatherCoordinate("보령", "11C20201"),
    MidTermWeatherCoordinate("서천", "11C20202"),
    MidTermWeatherCoordinate("천안", "11C20301"),
    MidTermWeatherCoordinate("아산", "11C20302"),
    MidTermWeatherCoordinate("예산", "11C20303"),
    MidTermWeatherCoordinate("대전", "11C20401"),
    MidTermWeatherCoordinate("공주", "11C20402"),
    MidTermWeatherCoordinate("계룡", "11C20403"),
    MidTermWeatherCoordinate("세종", "11C20404"),
    MidTermWeatherCoordinate("부여", "11C20501"),
    MidTermWeatherCoordinate("청양", "11C20502"),
    MidTermWeatherCoordinate("금산", "11C20601"),
    MidTermWeatherCoordinate("논산", "11C20602"),
    MidTermWeatherCoordinate("철원", "11D10101"),
    MidTermWeatherCoordinate("화천", "11D10102"),
    MidTermWeatherCoordinate("인제", "11D10201"),
    MidTermWeatherCoordinate("양구", "11D10202"),
    MidTermWeatherCoordinate("춘천", "11D10301"),
    MidTermWeatherCoordinate("홍천", "11D10302"),
    MidTermWeatherCoordinate("원주", "11D10401"),
    MidTermWeatherCoordinate("횡성", "11D10402"),
    MidTermWeatherCoordinate("영월", "11D10501"),
    MidTermWeatherCoordinate("정선", "11D10502"),
    MidTermWeatherCoordinate("평창", "11D10503"),
    MidTermWeatherCoordinate("대관령", "11D20201"),
    MidTermWeatherCoordinate("태백", "11D20301"),
    MidTermWeatherCoordinate("속초", "11D20401"),
    MidTermWeatherCoordinate("고성", "11D20402"),
    MidTermWeatherCoordinate("양양", "11D20403"),
    MidTermWeatherCoordinate("강릉", "11D20501"),
    MidTermWeatherCoordinate("동해", "11D20601"),
    MidTermWeatherCoordinate("삼척", "11D20602"),
    MidTermWeatherCoordinate("울릉도", "1.10E+102"),
    MidTermWeatherCoordinate("독도", "1.10E+103"),
    MidTermWeatherCoordinate("전주", "11F10201"),
    MidTermWeatherCoordinate("익산", "11F10202"),
    MidTermWeatherCoordinate("정읍", "11F10203"),
    MidTermWeatherCoordinate("완주", "11F10204"),
    MidTermWeatherCoordinate("장수", "11F10301"),
    MidTermWeatherCoordinate("무주", "11F10302"),
    MidTermWeatherCoordinate("진안", "11F10303"),
    MidTermWeatherCoordinate("남원", "11F10401"),
    MidTermWeatherCoordinate("임실", "11F10402"),
    MidTermWeatherCoordinate("순창", "11F10403"),
    MidTermWeatherCoordinate("군산", "21F10501"),
    MidTermWeatherCoordinate("김제", "21F10502"),
    MidTermWeatherCoordinate("고창", "21F10601"),
    MidTermWeatherCoordinate("부안", "21F10602"),
    MidTermWeatherCoordinate("함평", "21F20101"),
    MidTermWeatherCoordinate("영광", "21F20102"),
    MidTermWeatherCoordinate("진도", "21F20201"),
    MidTermWeatherCoordinate("완도", "11F20301"),
    MidTermWeatherCoordinate("해남", "11F20302"),
    MidTermWeatherCoordinate("강진", "11F20303"),
    MidTermWeatherCoordinate("장흥", "11F20304"),
    MidTermWeatherCoordinate("여수", "11F20401"),
    MidTermWeatherCoordinate("광양", "11F20402"),
    MidTermWeatherCoordinate("고흥", "11F20403"),
    MidTermWeatherCoordinate("보성", "11F20404"),
    MidTermWeatherCoordinate("순천시", "11F20405"),
    MidTermWeatherCoordinate("광주", "11F20501"),
    MidTermWeatherCoordinate("장성", "11F20502"),
    MidTermWeatherCoordinate("나주", "11F20503"),
    MidTermWeatherCoordinate("담양", "11F20504"),
    MidTermWeatherCoordinate("화순", "11F20505"),
    MidTermWeatherCoordinate("구례", "11F20601"),
    MidTermWeatherCoordinate("곡성", "11F20602"),
    MidTermWeatherCoordinate("순천", "11F20603"),
    MidTermWeatherCoordinate("흑산도", "11F20701"),
    MidTermWeatherCoordinate("목포", "21F20801"),
    MidTermWeatherCoordinate("영암", "21F20802"),
    MidTermWeatherCoordinate("신안", "21F20803"),
    MidTermWeatherCoordinate("무안", "21F20804"),
    MidTermWeatherCoordinate("성산", "11G00101"),
    MidTermWeatherCoordinate("제주", "11G00201"),
    MidTermWeatherCoordinate("성판악", "11G00302"),
    MidTermWeatherCoordinate("서귀포", "11G00401"),
    MidTermWeatherCoordinate("고산", "11G00501"),
    MidTermWeatherCoordinate("이어도", "11G00601"),
    MidTermWeatherCoordinate("추자도", "11G00800"),
    MidTermWeatherCoordinate("울진", "11H10101"),
    MidTermWeatherCoordinate("영덕", "11H10102"),
    MidTermWeatherCoordinate("포항", "11H10201"),
    MidTermWeatherCoordinate("경주", "11H10202"),
    MidTermWeatherCoordinate("문경", "11H10301"),
    MidTermWeatherCoordinate("상주", "11H10302"),
    MidTermWeatherCoordinate("예천", "11H10303"),
    MidTermWeatherCoordinate("영주", "11H10401"),
    MidTermWeatherCoordinate("봉화", "11H10402"),
    MidTermWeatherCoordinate("영양", "11H10403"),
    MidTermWeatherCoordinate("안동", "11H10501"),
    MidTermWeatherCoordinate("의성", "11H10502"),
    MidTermWeatherCoordinate("청송", "11H10503"),
    MidTermWeatherCoordinate("김천", "11H10601"),
    MidTermWeatherCoordinate("구미", "11H10602"),
    MidTermWeatherCoordinate("군위", "11H10603"),
    MidTermWeatherCoordinate("고령", "11H10604"),
    MidTermWeatherCoordinate("성주", "11H10605"),
    MidTermWeatherCoordinate("대구", "11H10701"),
    MidTermWeatherCoordinate("영천", "11H10702"),
    MidTermWeatherCoordinate("경산", "11H10703"),
    MidTermWeatherCoordinate("청도", "11H10704"),
    MidTermWeatherCoordinate("칠곡", "11H10705"),
    MidTermWeatherCoordinate("울산", "11H20101"),
    MidTermWeatherCoordinate("양산", "11H20102"),
    MidTermWeatherCoordinate("부산", "11H20201"),
    MidTermWeatherCoordinate("창원", "11H20301"),
    MidTermWeatherCoordinate("김해", "11H20304"),
    MidTermWeatherCoordinate("통영", "11H20401"),
    MidTermWeatherCoordinate("사천", "11H20402"),
    MidTermWeatherCoordinate("거제", "11H20403"),
    MidTermWeatherCoordinate("고성", "11H20404"),
    MidTermWeatherCoordinate("남해", "11H20405"),
    MidTermWeatherCoordinate("함양", "11H20501"),
    MidTermWeatherCoordinate("거창", "11H20502"),
    MidTermWeatherCoordinate("합천", "11H20503"),
    MidTermWeatherCoordinate("밀양", "11H20601"),
    MidTermWeatherCoordinate("의령", "11H20602"),
    MidTermWeatherCoordinate("함안", "11H20603"),
    MidTermWeatherCoordinate("창녕", "11H20604"),
    MidTermWeatherCoordinate("진주", "11H20701"),
    MidTermWeatherCoordinate("산청", "11H20703"),
    MidTermWeatherCoordinate("하동", "11H20704"),
    MidTermWeatherCoordinate("사리원", "11I10001"),
    MidTermWeatherCoordinate("신계", "11I10002"),
    MidTermWeatherCoordinate("해주", "11I20001"),
    MidTermWeatherCoordinate("개성", "11I20002"),
    MidTermWeatherCoordinate("장연(용연)", "11I20003"),
    MidTermWeatherCoordinate("신의주", "11J10001"),
    MidTermWeatherCoordinate("삭주(수풍)", "11J10002"),
    MidTermWeatherCoordinate("구성", "11J10003"),
    MidTermWeatherCoordinate("자성(중강)", "11J10004"),
    MidTermWeatherCoordinate("강계", "11J10005"),
    MidTermWeatherCoordinate("희천", "11J10006"),
    MidTermWeatherCoordinate("평양", "11J20001"),
    MidTermWeatherCoordinate("진남포(남포)", "11J20002"),
    MidTermWeatherCoordinate("안주", "11J20004"),
    MidTermWeatherCoordinate("양덕", "11J20005"),
    MidTermWeatherCoordinate("청진", "11K10001"),
    MidTermWeatherCoordinate("웅기(선봉)", "11K10002"),
    MidTermWeatherCoordinate("성진(김책)", "11K10003"),
    MidTermWeatherCoordinate("무산(삼지연)", "11K10004"),
    MidTermWeatherCoordinate("함흥", "11K20001"),
    MidTermWeatherCoordinate("장진", "11K20002"),
    MidTermWeatherCoordinate("북청(신포)", "11K20003"),
    MidTermWeatherCoordinate("혜산", "11K20004"),
    MidTermWeatherCoordinate("풍산", "11K20005"),
    MidTermWeatherCoordinate("원산", "11L10001"),
    MidTermWeatherCoordinate("고성(장전)", "11L10002"),
    MidTermWeatherCoordinate("평강", "11L10003")
)







