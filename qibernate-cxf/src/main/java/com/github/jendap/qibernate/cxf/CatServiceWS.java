package com.github.jendap.qibernate.cxf;

import com.github.jendap.qibernate.cxf.domain.Cat;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.Collection;

@WebService(name = "CatServiceWebService")
//@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public interface CatServiceWS {
    //	@WebMethod(operationName = "getFoo")
//	@RequestWrapper(targetNamespace = "http://foo.com/types", className = "java.lang.String")
//	@ResponseWrapper(targetNamespace = "http://foo.com/types", className = "com.foo.Response")
//	@WebResult(targetNamespace = "http://foo.com/types", name = "updatedQuote")
//	public Collection<Cat> findByName(@WebParam(targetNamespace = "http://foo.com/types",
//			name = "stockTicker", mode = Mode.IN) final String name);
    public Collection<Cat> findByName(@WebParam(name = "name") final String name);

    public Collection<Cat> findByAge(@WebParam(name = "from") final int from,
                                     @WebParam(name = "to") final int to);

    public String newCat(@WebParam(name = "cat") final Cat cat) throws RuntimeException;

    public String feedAllStarvingCats();
}
