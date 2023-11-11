package com.haenari.haenari.data.model.weather

data class UltraShortTermEntity(
    var response: UltraShortTermResponse? = UltraShortTermResponse()
)

data class UltraShortTermResponse(
    var header: UltraShortTermHeader? = UltraShortTermHeader(),
    var body: UltraShortTermBody? = UltraShortTermBody()
)

data class UltraShortTermHeader(
    var resultCode: String? = null,
    var resultMsg: String? = null
)

data class UltraShortTermBody(
    var dataType: String? = null,
    var items: UltraShortTermItems? = UltraShortTermItems(),
    var pageNo: Int? = null,
    var numOfRows: Int? = null,
    var totalCount: Int? = null
)

data class UltraShortTermItems(
    var item: ArrayList<UltraShortTermItem> = arrayListOf()
)

data class UltraShortTermItem(
    var baseDate: String? = null,
    var baseTime: String? = null,
    var category: String? = null,
    var nx: Int? = null,
    var ny: Int? = null,
    var obsrValue: String? = null
)