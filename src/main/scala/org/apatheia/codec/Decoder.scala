package org.apatheia.codec

trait Decoder[T] {
  def toObject(t: Array[Byte]): T
}
