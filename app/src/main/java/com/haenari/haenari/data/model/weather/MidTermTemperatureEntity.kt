package com.haenari.haenari.data.model.weather

data class MidTermTemperatureEntity(
    var response: MidTermTemperatureResponse? = MidTermTemperatureResponse()
)

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
    var taMin3: Int? = null,
    var taMin3Low: Int? = null,
    var taMin3High: Int? = null,
    var taMax3: Int? = null,
    var taMax3Low: Int? = null,
    var taMax3High: Int? = null,
    var taMin4: Int? = null,
    var taMin4Low: Int? = null,
    var taMin4High: Int? = null,
    var taMax4: Int? = null,
    var taMax4Low: Int? = null,
    var taMax4High: Int? = null,
    var taMin5: Int? = null,
    var taMin5Low: Int? = null,
    var taMin5High: Int? = null,
    var taMax5: Int? = null,
    var taMax5Low: Int? = null,
    var taMax5High: Int? = null,
    var taMin6: Int? = null,
    var taMin6Low: Int? = null,
    var taMin6High: Int? = null,
    var taMax6: Int? = null,
    var taMax6Low: Int? = null,
    var taMax6High: Int? = null,
    var taMin7: Int? = null,
    var taMin7Low: Int? = null,
    var taMin7High: Int? = null,
    var taMax7: Int? = null,
    var taMax7Low: Int? = null,
    var taMax7High: Int? = null,
    var taMin8: Int? = null,
    var taMin8Low: Int? = null,
    var taMin8High: Int? = null,
    var taMax8: Int? = null,
    var taMax8Low: Int? = null,
    var taMax8High: Int? = null,
    var taMin9: Int? = null,
    var taMin9Low: Int? = null,
    var taMin9High: Int? = null,
    var taMax9: Int? = null,
    var taMax9Low: Int? = null,
    var taMax9High: Int? = null,
    var taMin10: Int? = null,
    var taMin10Low: Int? = null,
    var taMin10High: Int? = null,
    var taMax10: Int? = null,
    var taMax10Low: Int? = null,
    var taMax10High: Int? = null,
)