package kyle;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class BooleanContainer
{
  @XmlJavaTypeAdapter(BooleanAdapter.class)
  public Boolean bool1 = null;
  @XmlJavaTypeAdapter(BooleanAdapter.class)
  public Boolean bool2 = null;
  @XmlJavaTypeAdapter(BooleanAdapter.class)
  public Boolean bool3 = null;
  @XmlJavaTypeAdapter(BooleanAdapter.class)
  public Boolean bool4 = null;
  @XmlJavaTypeAdapter(BooleanAdapter.class)
  public Boolean bool5 = null;
  @XmlJavaTypeAdapter(BooleanAdapter.class)
  public Boolean bool6 = null;
  @XmlJavaTypeAdapter(BooleanAdapter.class)
  public Boolean bool7 = null;
  @XmlJavaTypeAdapter(BooleanAdapter.class)
  public Boolean bool8 = null;
  @XmlJavaTypeAdapter(BooleanAdapter.class)
  public Boolean bool9 = null;
  @XmlJavaTypeAdapter(BooleanAdapter.class)
  public Boolean bool10 = null;
  @XmlJavaTypeAdapter(BooleanAdapter.class)
  public Boolean bool11 = null;
  @XmlJavaTypeAdapter(BooleanAdapter.class)
  public Boolean bool12 = null;
}
