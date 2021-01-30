package cn.mmooo

import spock.lang.Specification

class HelloControllerSpec extends Specification {

  def "test"() {

    expect:
    println("=--= hello client ")
    true

  }


}
