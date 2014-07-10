package kyle;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.jboss.logging.Logger;

public class BooleanAdapter extends XmlAdapter<String, Boolean> {
  private static Logger log = Logger.getLogger(BooleanAdapter.class);
  public Boolean unmarshal(String s) {
    if(log.isDebugEnabled()) {
      log.debug("Unmarshalling my boolean: \"" + s + "\"");
    }
    if(s == null) {
      return false;
    }
    s = s.trim();
    s = s.toLowerCase();
    if(log.isDebugEnabled()) {
      log.debug("Sanitized boolean string: \"" + s + "\"");
    }
    if(s.equals("true")) {
      return true;
    } else {
      return false;
    }
  }

  public String marshal(Boolean b) {
    return b.toString();
  }
}
