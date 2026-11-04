package sa.factory.petspetcare1289530.hardware

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

data class HardwareTelemetryFrame(
    val peripheralName: String,
    val connectionState: String,
    val livePayload: String,
    val timestamp: Long = System.currentTimeMillis()
)

class HardwareSensorDriver {
    private val peripheral = "ISO 11784/11785 134.2 kHz RFID Microchip Reader"

    fun observePeripheralStream(): Flow<HardwareTelemetryFrame> = flow {
        emit(HardwareTelemetryFrame(peripheral, "CONNECTING", "Handshake initializing..."))
        delay(300)
        emit(HardwareTelemetryFrame(peripheral, "ONLINE", "ISO 11784/11785 134.2 kHz RFID Microchip Reader: ONLINE | READY"))

        while (true) {
            delay(1500)
            val jitter = Random.nextInt(1, 10)
            emit(HardwareTelemetryFrame(peripheral, "ACTIVE_STREAMING", "ISO 11784/11785 134.2 kHz RFID Microchip Reader: ONLINE | READY | Frame #${System.currentTimeMillis() % 10000} (Jitter: +${jitter}ms)"))
        }
    }
}
