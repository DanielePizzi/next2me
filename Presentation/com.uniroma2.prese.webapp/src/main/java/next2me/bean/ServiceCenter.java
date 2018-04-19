package next2me.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ServiceCenter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1704129427258036873L;
	private Map<String, Object> services;
	
	public ServiceCenter() {
		this.services = new HashMap<>();
	}
	
	public ServiceCenter(Map<String, Object> services) {
		this.services = new HashMap<>(services);
	}

	/**
	 * @return the services
	 */
	public Map<String, Object> getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(Map<String, Object> services) {
		this.services = services;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ServiceCenter [services=" + services + "]";
	}
	
}
