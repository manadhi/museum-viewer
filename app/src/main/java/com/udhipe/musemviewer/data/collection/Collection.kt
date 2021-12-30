package com.udhipe.musemviewer.data.collection

data class Collection(
    var objectNumber: String,
    var title: String,
    var webImage: WebImage
) {
    data class WebImage(
        var url: String
    )
}
