package com.haenari.haenari.data.model.weather

data class ShortTermEntity(
    var response: ShortTermResponse? = ShortTermResponse()
) {
    fun getItems(): List<ShortTermItem> {
        return response?.body?.items?.item ?: emptyList()
    }
}

data class ShortTermResponse(
    var header: ShortTermHeader? = ShortTermHeader(),
    var body: ShortTermBody? = ShortTermBody()
)

data class ShortTermHeader(
    var resultCode: String? = null,
    var resultMsg: String? = null
)

data class ShortTermBody(
    var dataType: String? = null,
    var items: ShortTermItems? = ShortTermItems(),
    var pageNo: Int? = null,
    var numOfRows: Int? = null,
    var totalCount: Int? = null
)

data class ShortTermItems(
    var item: ArrayList<ShortTermItem> = arrayListOf()
)

data class ShortTermItem(
    var baseDate: String? = null,
    var baseTime: String? = null,
    var category: String? = null,
    var nx: Int? = null,
    var ny: Int? = null,
    var fcstDate: String? = null,
    var fcstTime: String? = null,
    var fcstValue: String? = null
)

