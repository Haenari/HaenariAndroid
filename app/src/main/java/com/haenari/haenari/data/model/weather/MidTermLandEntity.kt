package com.haenari.haenari.data.model.weather

data class MidTermLandEntity(
    var response: MidTermLandResponse? = MidTermLandResponse()
) {
    fun getItem(): MidTermLandItem? {
        return response?.body?.items?.item?.getOrNull(0)
    }
}

data class MidTermLandResponse(
    var header: MidTermLandHeader? = MidTermLandHeader(),
    var body: MidTermLandBody? = MidTermLandBody()
)

data class MidTermLandHeader(
    var resultCode: String? = null,
    var resultMsg: String? = null
)

data class MidTermLandBody(
    var dataType: String? = null,
    var items: MidTermLandItems? = MidTermLandItems(),
    var pageNo: Int? = null,
    var numOfRows: Int? = null,
    var totalCount: Int? = null
)

data class MidTermLandItems(
    var item: ArrayList<MidTermLandItem> = arrayListOf()
)

data class MidTermLandItem(
    var regId: String? = null,
    var rnSt3Am: Int? = null,
    var rnSt3Pm: Int? = null,
    var rnSt4Am: Int? = null,
    var rnSt4Pm: Int? = null,
    var rnSt5Am: Int? = null,
    var rnSt5Pm: Int? = null,
    var rnSt6Am: Int? = null,
    var rnSt6Pm: Int? = null,
    var rnSt7Am: Int? = null,
    var rnSt7Pm: Int? = null,
    var rnSt8: Int? = null,
    var rnSt9: Int? = null,
    var rnSt10: Int? = null,
    var wf3Am: String? = null,
    var wf3Pm: String? = null,
    var wf4Am: String? = null,
    var wf4Pm: String? = null,
    var wf5Am: String? = null,
    var wf5Pm: String? = null,
    var wf6Am: String? = null,
    var wf6Pm: String? = null,
    var wf7Am: String? = null,
    var wf7Pm: String? = null,
    var wf8: String? = null,
    var wf9: String? = null,
    var wf10: String? = null,
)
