package com.bytecoder.DesignPatterns.LLD.bahavioral.ChainOfResponsibility.workflow;

import java.util.function.Function;

import com.bytecoder.DesignPatterns.LLD.bahavioral.ChainOfResponsibility.classLevel.BaseHandler;
import com.bytecoder.DesignPatterns.LLD.bahavioral.ChainOfResponsibility.classLevel.IPCheckHandler;
import com.bytecoder.DesignPatterns.LLD.bahavioral.ChainOfResponsibility.classLevel.TokenCheckHandler;

public class UserService {
    public void execute(Request request) {

//        we can also use Autowired with List<handlers> {composite object + list of handlers
//        Request.filter(f1).filter(f2).filter(f3).hanldle();
//        void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)


//        LinkedList of handlers


        System.out.println("start second");
        request.token = null;
        BaseHandler baseHandler = new BaseHandler(new TokenCheckHandler(new IPCheckHandler(null)));
        baseHandler.handle(request);
        System.out.println("done " + request);

         new BasicHandler().addNext(new IPHandler()).addNext(new TokenHandler()).handle(request);

//        Stream
        Function<String, String> f1 = (str) -> str + " fg ";
        System.out.println(f1.andThen((str) -> "ganesh " + str).apply("com/gschoudhary/io "));


    }


}
