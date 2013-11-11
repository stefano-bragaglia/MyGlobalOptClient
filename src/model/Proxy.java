/**
 * 
 */
package model;

import it.unibo.ai.ePolicy.GlobalOpt.IO.Input.GOParetoInputParam;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Input.GlobalOptInputParam;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GOParetoOutput;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GlobalOptOutput;
import it.unibo.ai.ePolicy.GlobalOpt.WS.Services.GlobalOptWSSEI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;

/**
 * @author stefano
 * 
 */
public class Proxy implements GlobalOptWSSEI {

	/**
	 * The namespace of the Global Optimiser service.
	 */
	private static final String NAMESPACE = "http://Services.WS.GlobalOpt.ePolicy.ai.unibo.it/";

	/**
	 * The URI where the Global Optimiser service is deployed.
	 */
	private static final String URI = "http://localhost:8080/GlobalOptWS/services/GlobalOpt";

	/**
	 * The proxy for the Global Optimiser service.
	 */
	private GlobalOptWSSEI proxy;

	/**
	 * The only instance on this proxy.
	 */
	private static GlobalOptWSSEI instance = null;

	/**
	 * Returns the only instance on this proxy.
	 * 
	 * @return the only instance on this proxy
	 */
	public static GlobalOptWSSEI getInstance() throws MalformedURLException {
		if (instance == null)
			instance = new Proxy();
		return instance;
	}

	/**
	 * Default constructor.
	 * 
	 * @throws MalformedURLException
	 */
	private Proxy() throws MalformedURLException {
		QName nameService = new QName(NAMESPACE, "GlobalOpt");
		QName namePort = new QName(NAMESPACE, "GlobalOptSimpleWSSEIPort");
		Service service = Service.create(new URL(URI + "/?wsdl"), nameService);
		service.addPort(namePort, SOAPBinding.SOAP11HTTP_BINDING, URI);
		proxy = service.getPort(GlobalOptWSSEI.class);
		Client client = ClientProxy.getClient(proxy);
		HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
		httpConduit.getClient().setReceiveTimeout(1000 * 60 * 30);
		assert invariant() : "Illegal state in Proxy()";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.unibo.ai.ePolicy.GlobalOpt.WS.Services.GlobalOptWSSEI#computePareto
	 * (it.unibo.ai.ePolicy.GlobalOpt.IO.Input.GOParetoInputParam)
	 */
	@Override
	public GOParetoOutput computePareto(GOParetoInputParam param) throws IOException, InterruptedException {
		if (param == null)
			throw new IllegalArgumentException("Illegal 'param' argument in Proxy.computePareto(GOParetoInputParam): "
					+ param);
		GOParetoOutput result = proxy.computePareto(param);
		assert invariant() : "Illegal state in Proxy.computePareto(GOParetoInputParam)";
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.unibo.ai.ePolicy.GlobalOpt.WS.Services.GlobalOptWSSEI#getVersion()
	 */
	@Override
	public String getVersion() {
		String result = proxy.getVersion();
		assert invariant() : "Illegal state in Proxy.getVersion()";
		return result;
	}

	/**
	 * Invariant check.
	 * 
	 * @return <code>true</code> if the state is valid, <code>false</code>
	 *         otherwise
	 */
	private boolean invariant() {
		return (proxy != null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.unibo.ai.ePolicy.GlobalOpt.WS.Services.GlobalOptWSSEI#invoke(it.unibo
	 * .ai.ePolicy.GlobalOpt.IO.Input.GlobalOptInputParam)
	 */
	@Override
	public GlobalOptOutput invoke(GlobalOptInputParam param) throws IOException, InterruptedException {
		if (param == null)
			throw new IllegalArgumentException("Illegal 'param' argument in Proxy.invoke(GlobalOptInputParam): "
					+ param);
		GlobalOptOutput result = proxy.invoke(param);
		assert invariant() : "Illegal state in Proxy.invoke(GlobalOptInputParam)";
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.unibo.ai.ePolicy.GlobalOpt.WS.Services.GlobalOptWSSEI#upperEcho(java
	 * .lang.String)
	 */
	@Override
	public String upperEcho(String text) {
		if (text == null)
			throw new IllegalArgumentException("Illegal 'text' argument in Proxy.upperEcho(String): " + text);
		String result = proxy.upperEcho(text);
		assert invariant() : "Illegal state in Proxy.upperEcho(String)";
		return result;
	}
}
