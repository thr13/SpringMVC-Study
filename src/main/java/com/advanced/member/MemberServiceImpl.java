package com.advanced.member;

import com.advanced.member.annotation.ClassAop;
import com.advanced.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop("test value")
    public String hello(String name) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
