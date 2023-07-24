package org.apatheia.codec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.nio.ByteBuffer
import org.apatheia.codec.Codec._

class CodecSpec extends AnyFlatSpec with Matchers {
  behavior of "Codec"

  case class Record(id: Int)

  implicit val recordDecoder: Decoder[Record] = new Decoder[Record] {
    override def toObject(t: Array[Byte]): Either[DecodingFailure, Record] =
      Right(
        Record(
          ByteBuffer.wrap(t).getInt()
        )
      )
  }

  implicit val recordEncoder: Encoder[Record] = new Encoder[Record] {
    override def toByteArray(t: Record): Array[Byte] =
      ByteBuffer.allocate(Integer.BYTES).putInt(t.id).rewind().array()
  }

  it should "encode an object into a byte array" in {
    Record(999).toByteArray shouldBe Array(0, 0, 3, -25)
  }

  it should "decode a byte array from an object" in {
    val data: Array[Byte] = Array[Byte](0, 0, 3, -25)
    data.toObject[Record] shouldBe Right(Record(999))
  }

}
