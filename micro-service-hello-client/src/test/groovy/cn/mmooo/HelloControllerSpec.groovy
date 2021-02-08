package cn.mmooo

import spock.lang.Specification

import java.time.LocalDateTime

class HelloControllerSpec extends Specification {

  def "test1"() {
    expect:
    Thread.sleep(1000)
    println("=--= hello test1 " + LocalDateTime.now())
    true
  }

  def "test2"() {
    expect:
    Thread.sleep(1000)
    println("=--= hello test2 " + LocalDateTime.now())
    true
  }


}
