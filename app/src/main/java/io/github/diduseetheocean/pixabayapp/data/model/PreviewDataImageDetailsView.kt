package io.github.diduseetheocean.pixabayapp.data.model

object PreviewDataImageDetailsView {
    val image = ImageModel(
        userName = "Chris",
        userImageURL = "",
        url = "https://cdn.pixabay.com/photo/2019/07/27/13/18/church-4366733_1280.jpg",
        likes = 777.toString(),
        downloads = 888.toString(),
        comments = 999.toString(),
        tags = listOf(
            "eifel",
            "tower",
            "clouds",
            "flowers",
        ).map { "#$it" },
        largeImageURL = "https://cdn.pixabay.com/photo/2019/07/27/13/18/church-4366733_1280.jpg",
    )

    val images = (0..20).map { image }
        .mapIndexed { index, imageUiModel ->
            imageUiModel.copy(userName = "User$index",
                imageId = index + 100L)
        }
}