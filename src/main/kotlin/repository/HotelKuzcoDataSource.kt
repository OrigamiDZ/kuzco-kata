package repository

import domain.JsonRoomStateResponse
import domain.JsonRoomStateResponse.Room
import domain.JsonRoomStateResponse.Room.Bed

class HotelKuzcoDataSource {

    fun getHotelData() = JsonRoomStateResponse(
        chambers = listOf(
            Room(
                roomNumber = 101,
                reservations = emptyList(),
                roomInformation = Room.Information(
                    floor = 1,
                    beds = listOf(
                        Bed(bedType = "King")
                    ),
                    personCapacity = 2,
                    haveAC = true,
                    haveWiFi = true,
                    havePrivateBathroom = true,
                    haveAccessibility = true
                )
            ),
            Room(
                roomNumber = 202,
                reservations = emptyList(),
                roomInformation = Room.Information(
                    floor = 2,
                    beds = listOf(
                        Bed(bedType = "Queen")
                    ),
                    personCapacity = 1,
                    haveAC = false,
                    haveWiFi = true,
                    havePrivateBathroom = true,
                    haveAccessibility = false
                )
            ),
            Room(
                roomNumber = 303,
                reservations = emptyList(),
                roomInformation = Room.Information(
                    floor = 3,
                    beds = listOf(
                        Bed(bedType = "Single"),
                        Bed(bedType = "Single"),
                        Bed(bedType = "Single")
                    ),
                    personCapacity = 3,
                    haveAC = true,
                    haveWiFi = false,
                    havePrivateBathroom = true,
                    haveAccessibility = false
                )
            )
        )
    )

}