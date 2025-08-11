package srinivas.SOAP;





import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface SearchInterface{

	@WebMethod public String  listResults(String FirstName,String LastName,String City,String State);

}