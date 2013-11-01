package com.redhat.gss.asm;

import java.io.FileOutputStream;
import java.util.Random;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassAdapter;
import static org.objectweb.asm.Opcodes.*;

public class PermGenExploder
{
  public static void main(String[] args) throws Exception
  {
    explode();
  }

  public static void explode()
  {
    String name = "a";
    MyClassLoader mcl = new MyClassLoader();
    ClassWriter cw = new ClassWriter(0);
    createClassStructure(cw, name);
    byte[] b = cw.toByteArray();

    mcl.defineClass(name, b);
    // FileOutputStream fos = new FileOutputStream(name + ".class");
    // fos.write(b);
    // fos.close();

    while(true)
    {
      name = incrementName(name);
      ClassReader reader = new ClassReader(b);
      cw = new ClassWriter(reader, 0);
      ChangeNameAdapter adapter = new ChangeNameAdapter(cw, name);
      reader.accept(adapter, 0);
      b = cw.toByteArray();

      mcl.defineClass(name, b);
      // fos = new FileOutputStream(name + ".class");
      // fos.write(b);
      // fos.close();
    }
  }

  private static void createClassStructure(ClassWriter cw, String name)
  {
    cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, name, null, "java/lang/Object", null);
    for(int i=0; i<300; i++)
    {
      cw.visitField(
        ACC_PUBLIC + ACC_FINAL + ACC_STATIC, 
        "STRING"+i, 
        "Ljava/lang/String;", 
        null, 
        getRandomString(300)
      ).visitEnd();
    }
    cw.visitEnd();
  }

  public static String getRandomString(int length)
  {
    byte[] bb = new byte[length];
    Random r = new Random();
    for(int i=0; i<length; i++)
    {
      int k = r.nextInt(94)+32;
      if(k == 92) k++; //avoid backslashes
      bb[i] = (byte)k;
    }
    return new String(bb);
  }

  public static class ChangeNameAdapter extends ClassAdapter
  {
    private String newName;

    public ChangeNameAdapter(ClassVisitor cv, String newName) 
    {
      super(cv);
      this.newName = newName;
    }

    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
      cv.visit(version, access, newName, signature, superName, interfaces);
    }
  }

  private static String incrementName(String name)
  {
    char[] cc = name.toCharArray();
    for(int i=cc.length-1; i>=0; i--)
    {
      char c = cc[i];
      if(c == 'z')
      {
        cc[i] = 'a';
        if(i == 0)
        {
          char[] newcc = new char[cc.length+1];
          System.arraycopy(cc, 0, newcc, 1, cc.length);
          newcc[0] = 'a';
          cc = newcc;
        }
      }
      else
      {
        cc[i] = ++c;
        break;
      }
    }
    return new String(cc);
  }

  public static class MyClassLoader extends ClassLoader
  {
    public Class defineClass(String name, byte[] b) 
    {
      return defineClass(name, b, 0, b.length);
    }
  }
}
