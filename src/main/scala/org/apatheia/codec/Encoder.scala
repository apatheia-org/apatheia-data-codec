package org.apatheia.codec

trait Encoder[T] {
  def toByteArray(t: T): Array[Byte]
}
