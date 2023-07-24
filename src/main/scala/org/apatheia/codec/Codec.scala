package org.apatheia.codec

object Codec {

  implicit class DataDecoder(data: Array[Byte]) {
    def toObject[T](implicit decoder: Decoder[T]): T = decoder.toObject(data)
  }

  implicit class DataEncoder[T](t: T) {
    def toByteArray(implicit encoder: Encoder[T]): Array[Byte] =
      encoder.toByteArray(t)
  }

}
