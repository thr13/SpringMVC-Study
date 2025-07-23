package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        log.info("call resolver", ex);

        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView();
                // 빈 ModelAndView 를 반환하면 뷰를 렌더링 하지 않고 정상 흐름으로 서블릿이 반환된다
            }

        } catch (IOException e) {
            log.error("resolver ex", e);
        }

        return null;
        // null 반환시 다음 ExceptionResolver 를 찾아서 실행함 만약 ExceptionResolver 가 없으면 예외 처리가 안되고 기존에 발생한 예외를 서블릿 밖으로 던진다
    }
}
