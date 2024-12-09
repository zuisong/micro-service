package trace

import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import java.lang.invoke.MethodHandles
import java.util.*

object TraceUtils {
    private const val TRACE_ID = "tid"
    val log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())
    fun createTraceId() {
        var traceId = MDC.get(TRACE_ID)
        if (StringUtils.isBlank(traceId)) {
            traceId = UUID.randomUUID().toString().replace("-".toRegex(), "").lowercase(Locale.getDefault())
             log.debug("create traceId :{}", traceId);
            MDC.put(TRACE_ID, traceId)
        }
    }

    fun destroyTraceId() {
        MDC.remove(TRACE_ID)
    }
}
