import java.io.File

class CRC32 {
    fun checksum(path: String): String {
        println("Calculating checksum")
        val dataArray = File(path).readBytes().map { b -> b.toInt() and 0xFF }
        val poly = 0x4C11D37
        var crcRegister = 0x00000000
        for (data in dataArray) {
            var d = data
            while (d != 0) {
                val dataLb = d and 0x1
                val crcLb = crcRegister and 0x1
                d = d shr 1
                crcRegister = if ((dataLb xor crcLb) == 0) {
                    crcRegister shr 1
                } else {
                    (crcRegister shr 1) xor poly
                }
            }
        }
        return crcRegister.toString(16)
    }
}