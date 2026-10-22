import java.io.IO.println

fun id(hi: Int, lo: Int): Long = (hi.toLong() shl 32) or (lo.toLong() and 0xFFFFFFFFL)

fun main() {
    println ( "modelId ${id(288784246,84754739)}")
    println ( "_shootId ${id(272048587,295381737)}")
    println ( "_shootDummyId ${id(614158399,-327996881)}")
    println ( "_shootWithTargetId ${id(1963572121,467312018)}")
}