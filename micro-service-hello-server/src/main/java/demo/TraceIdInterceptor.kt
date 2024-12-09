package demo

import jakarta.servlet.FilterChain
import jakarta.servlet.http.*
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import trace.TraceUtils.createTraceId
import trace.TraceUtils.destroyTraceId

@Component
@Order(1)
class TraceIdInterceptor : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        createTraceId()
        filterChain.doFilter(request, response)
        destroyTraceId()
    }


}

