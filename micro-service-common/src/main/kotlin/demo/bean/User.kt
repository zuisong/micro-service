package demo.bean

import jakarta.validation.constraints.*


data class User(
  @field:NotEmpty
  var name: String? = null,
  var password: String? = null
)
