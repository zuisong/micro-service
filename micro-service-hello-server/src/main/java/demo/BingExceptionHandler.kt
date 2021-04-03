package demo

import org.slf4j.*
import org.springframework.http.*
import org.springframework.validation.*
import org.springframework.web.bind.annotation.*
import java.lang.invoke.*
import java.util.*
import javax.servlet.http.*
import javax.validation.*


@ControllerAdvice
class BingExceptionHandler {
  val logger: Logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())

  @ExceptionHandler(BindException::class)
  @ResponseBody
  fun handleBindException(e: BindException): String {
    //打印校验住的所有的错误信息
    return e.bindingResult
      .fieldErrors
      .mapNotNull { it.field + " " + it.defaultMessage }
      .onEach {
        logger.info(it)
      }
      .joinToString(",", "参数错误：[", "]")
  }

  @ExceptionHandler(ConstraintViolationException::class)
  @ResponseBody
  fun handleBindException(e: ConstraintViolationException): String {
    //打印校验住的所有的错误信息
    return e.constraintViolations
      .mapNotNull { it.message }
      .onEach {
        logger.info(it)
      }
      .joinToString(",", "参数错误：[", "]")
  }

  @ExceptionHandler(Exception::class)
  fun handleException(req: HttpServletRequest?, e: Exception): ResponseEntity<*>? {
    logger.error(e.message, e)
    val errorMsg = if (e.message == null) e.javaClass.simpleName else e.message!!
    val error: Map<String, Any> = Collections.singletonMap("error", errorMsg)
    return ResponseEntity.status(500).body(error)
  }


}
