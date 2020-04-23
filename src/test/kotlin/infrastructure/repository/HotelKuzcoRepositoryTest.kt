package infrastructure.repository

import domain.RoomsEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDate

@RunWith(MockitoJUnitRunner::class)
class HotelKuzcoRepositoryTest {

    private val dataSource = HotelKuzcoDataSource()

    private lateinit var repository: HotelKuzcoRepository

    @Test
    fun getRoomsInformations() {
        // Given
        repository = HotelKuzcoRepository(dataSource)

        // When
        val response = repository.getRoomsInformations()

        // Then
        assertThat(response).isEqualTo(
            RoomsEntity(
                chambers = listOf(
                    RoomsEntity.Room(
                        id = 101,
                        reservations = listOf(
                            RoomsEntity.Room.Reservation(
                                checkInDate = LocalDate.of(1970,1,1),
                                checkOutDate = LocalDate.of(1970,1,7)
                            )
                        ),
                        information = RoomsEntity.Room.Information(
                            floor = 1,
                            beds = listOf(
                                RoomsEntity.Room.Bed.King
                            ),
                            personCapacity = 2,
                            haveAC = true,
                            haveWiFi = true,
                            havePrivateBathroom = true,
                            haveAccessibility = true
                        )
                    ),
                    RoomsEntity.Room(
                        id = 202,
                        reservations = emptyList(),
                        information = RoomsEntity.Room.Information(
                            floor = 2,
                            beds = listOf(
                                RoomsEntity.Room.Bed.Queen
                            ),
                            personCapacity = 1,
                            haveAC = false,
                            haveWiFi = true,
                            havePrivateBathroom = true,
                            haveAccessibility = false
                        )
                    ),
                    RoomsEntity.Room(
                        id = 303,
                        reservations = emptyList(),
                        information = RoomsEntity.Room.Information(
                            floor = 3,
                            beds = listOf(
                                RoomsEntity.Room.Bed.Single,
                                RoomsEntity.Room.Bed.Single,
                                RoomsEntity.Room.Bed.Single
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
        )
    }
}