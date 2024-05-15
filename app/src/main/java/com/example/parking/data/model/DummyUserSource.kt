package com.example.parking.data.model

object DummyUserSource {
    val dummyUser:List<User> = listOf(
        User(
            profile = "",
            name = "Saputra Ari Wijaya",
            phone="81249810903",
            role = Roles.PELANGGAN
        ),
        User(
            profile = "",
            name = "Pangeran Jonathan",
            phone="82171558690",
            role = Roles.PENJAGA
        )
    )
}