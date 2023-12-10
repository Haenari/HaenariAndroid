package com.haenari.haenari.data.model.weather

data class MidTermTemperatureEntity(
    var response: MidTermTemperatureResponse? = MidTermTemperatureResponse()
) {
    fun getItem(): MidTermTemperatureItem? {
        return response?.body?.items?.item?.getOrNull(0)
    }
}

data class MidTermTemperatureResponse(
    var header: MidTermTemperatureHeader? = MidTermTemperatureHeader(),
    var body: MidTermTemperatureBody? = MidTermTemperatureBody()
)

data class MidTermTemperatureHeader(
    var resultCode: String? = null,
    var resultMsg: String? = null
)

data class MidTermTemperatureBody(
    var dataType: String? = null,
    var items: MidTermTemperatureItems? = MidTermTemperatureItems(),
    var pageNo: Int? = null,
    var numOfRows: Int? = null,
    var totalCount: Int? = null
)

data class MidTermTemperatureItems(
    var item: ArrayList<MidTermTemperatureItem> = arrayListOf()
)

data class MidTermTemperatureItem(
    var regId: String? = null,
    var taMin3: Float? = null,
    var taMin3Low: Float? = null,
    var taMin3High: Float? = null,
    var taMax3: Float? = null,
    var taMax3Low: Float? = null,
    var taMax3High: Float? = null,
    var taMin4: Float? = null,
    var taMin4Low: Float? = null,
    var taMin4High: Float? = null,
    var taMax4: Float? = null,
    var taMax4Low: Float? = null,
    var taMax4High: Float? = null,
    var taMin5: Float? = null,
    var taMin5Low: Float? = null,
    var taMin5High: Float? = null,
    var taMax5: Float? = null,
    var taMax5Low: Float? = null,
    var taMax5High: Float? = null,
    var taMin6: Float? = null,
    var taMin6Low: Float? = null,
    var taMin6High: Float? = null,
    var taMax6: Float? = null,
    var taMax6Low: Float? = null,
    var taMax6High: Float? = null,
    var taMin7: Float? = null,
    var taMin7Low: Float? = null,
    var taMin7High: Float? = null,
    var taMax7: Float? = null,
    var taMax7Low: Float? = null,
    var taMax7High: Float? = null,
    var taMin8: Float? = null,
    var taMin8Low: Float? = null,
    var taMin8High: Float? = null,
    var taMax8: Float? = null,
    var taMax8Low: Float? = null,
    var taMax8High: Float? = null,
    var taMin9: Float? = null,
    var taMin9Low: Float? = null,
    var taMin9High: Float? = null,
    var taMax9: Float? = null,
    var taMax9Low: Float? = null,
    var taMax9High: Float? = null,
    var taMin10: Float? = null,
    var taMin10Low: Float? = null,
    var taMin10High: Float? = null,
    var taMax10: Float? = null,
    var taMax10Low: Float? = null,
    var taMax10High: Float? = null,
)