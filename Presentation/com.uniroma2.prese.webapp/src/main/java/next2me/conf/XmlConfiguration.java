package next2me.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan
@ImportResource("config:applicationContext.xml")
public class XmlConfiguration {
	
}
