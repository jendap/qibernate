package com.github.jendap.qibernate.cxf;

import java.util.Collection;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.github.jendap.qibernate.cxf.domain.Cat;


@WebService(name = "CatServiceWSFoo")
//@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public interface CatServiceWS {
//	@WebMethod(operationName = "getStockQuote")
//	@RequestWrapper(targetNamespace = "http://demo.mycompany.com/types", className = "java.lang.String")
//	@ResponseWrapper(targetNamespace = "http://demo.mycompany.com/types", className = "org.eric.demo.Quote")
//	@WebResult(targetNamespace = "http://demo.mycompany.com/types", name = "updatedQuote")
//	public Collection<Cat> findByName(
//			@WebParam(targetNamespace = "http://demo.mycompany.com/types", name = "stockTicker", mode = Mode.IN) final String name);
	public Collection<Cat> findByName(@WebParam(name = "name") final String name);

	public Collection<Cat> findByAge(@WebParam(name = "from") final int from, @WebParam(name = "to") final int to);

	public String newCat(@WebParam(name = "cat") final Cat cat) throws RuntimeException;

	public String clapCheerleaders();
}
