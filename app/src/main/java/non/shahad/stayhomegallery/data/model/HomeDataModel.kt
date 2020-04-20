package non.shahad.stayhomegallery.data.model

import non.shahad.stayhomegallery.data.db.entity.Post

data class HomeDataModel(
    val posts : List<Post>,
    val title : String
)